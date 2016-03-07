package com.softserve.webtester.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.webtester.exception.ResourceNotFoundException;
import com.softserve.webtester.mapper.DbValidationMapper;
import com.softserve.webtester.mapper.HeaderMapper;
import com.softserve.webtester.mapper.LabelMapper;
import com.softserve.webtester.mapper.RequestMapper;
import com.softserve.webtester.mapper.VariableMapper;
import com.softserve.webtester.model.DbValidation;
import com.softserve.webtester.model.Header;
import com.softserve.webtester.model.Label;
import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.Variable;

/**
 * RequestService class implements CRUD operation on {@link Request} instance in the database.<br>
 * The service uses Spring DataSourceTransactionManager for managing transaction with the database and log4j for
 * logging.
 * 
 * @author Taras Oglabyak
 * @version 1.4
 */
@Service
public class RequestService {
    
    private static final Logger LOGGER = Logger.getLogger(RequestService.class);
    
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private DbValidationMapper dbValidationMapper;

    @Autowired
    private HeaderMapper headerMapper;

    @Autowired
    private VariableMapper variableMapper;

    @Autowired
    private LabelMapper labelMapper;
    
    @Value("${request.timeout.default:25}")
    private int defaultTimeout;
    
    @Value("${request.name.duplicate.suffix:_duplicate}")
    private String duplicateSuffix;
    
    @Autowired
    @Qualifier("requestNameCountPattern")
    private Pattern requestNameCountPattern;
    
    public int getDefaultTimeout() {
        return defaultTimeout;
    }

    /**
     * Saves {@link Request} instance to the database.
     * 
     * @param request {@link Request} instance should be saved in the database
     * @return id of saved request
     * @throws DuplicateKeyException if the request with the name exists in the database.
     * @throws DataAccessException
     */
    @Transactional
    public int save(Request request) {
	try {

	    // Save request instance to Request table
	    requestMapper.save(request);
	    int id = request.getId();

	    // Save all dbValidations, variables, headers and labels for the request instance to the database
	    saveRequestComponents(request);
	    LOGGER.info("Successfully saved request instance in the database, request id: " + id);
	    return id;
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to save request instance, request id: " + request.getId(), e);
	    throw e;
	}
    }

    /**
     * Loads {@link Request} instance with headers, dbValidations, labels and variables.
     * 
     * @param id identifier of Request instance
     * @return {@link Request} instance
     * @throws ResourceNotFoundException if Request instance is null
     * @throws DataAccessException
     */
    @Transactional
    public Request load(int id) {
	try {
	    Request request = requestMapper.load(id);
	    if (request == null)
		throw new ResourceNotFoundException("Request not found, id: " + id);
	    return request;
	} catch (ResourceNotFoundException e) {
	    LOGGER.error("Request not found, id: " + id, e);
	    throw e;
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to load request instance, request id: " + id, e);
	    throw e;
	}
    }

    /**
     * Loads all stored {@link Request} instances with their main information.
     * 
     * @param requestNameFilter using for filtering instances, which name starts with the parameter
     * @param applicationFilter using for filtering instances, which application's identifiers are in the array
     * @param serviceFilter using for filtering instances, which service's identifiers are in the array
     * @param labelFilter using for filtering instances, which label's identifiers are in the array
     * @return List of {@link Request} instances
     * @throws DataAccessException
     */
    @Transactional
    public List<Request> loadAll(String requestNameFilter, int[] applicationFilter, int[] serviceFilter, 
	    			 int[] labelFilter) {
	try {
	    return requestMapper.loadAll(requestNameFilter, applicationFilter, serviceFilter, labelFilter);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to load request instances", e);
	    throw e;
	}
    }

    /**
     * Updates {@link Request} instance in the database.
     * 
     * @param request {@link Request} instance to be saved
     * @return the number of rows affected by the statement
     * @throws DuplicateKeyException if the request with the name exists in the database.
     * @throws DataAccessException
     */
    @Transactional
    public int update(Request request) {
	try {
	    int id = request.getId();

	    // Update request instance in Request table
	    requestMapper.update(request);

	    // Delete all dbValidations, variables, headers and labels for the request instance in the database
	    dbValidationMapper.deleteByRequestId(id);
	    variableMapper.deleteByRequestId(id);
	    headerMapper.deleteByRequestId(id);
	    labelMapper.deleteByRequestId(id);

	    // Save all dbValidations, variables, headers and labels for the request instance to the database
	    saveRequestComponents(request);
	    LOGGER.info("Successfully updated request instance in the database, request id: " + id);
	    return id;
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to update request instance, request id: " + request.getId(), e);
	    throw e;
	}
    }

    /**
     * Deletes {@link Request} instance from the database.
     * 
     * @param id identifier of request to delete
     * @return the number of rows affected by the statement
     * @throws DataAccessException
     */
    @Transactional
    public int delete(int id) {
	try {
	    return requestMapper.delete(id);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to delete request instance, request id: " + id, e);
	    throw e;
	}
    }
    
    /**
     * Deletes {@link Request} instances from the database.
     * 
     * @param requestIdArray identifiers of request to delete
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Transactional
    public int delete(int[] requestIdArray) {
	try {
	   return requestMapper.deleteRequests(requestIdArray);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to delete request instances, requests id: " + requestIdArray, e);
	    throw e;
	}
    }
    
    /**
     * Checks the unique of request's name.
     * 
     * @param name name of {@link Request} should be checked
     * @param exclusionId id of {@link Request} should be excluded
     * @return true, if name is unique
     * @throws DataAccessException
     */
    @Transactional
    public boolean isRequestNameFree(String name, int exclusionId) {
	try {
	    return requestMapper.isRequestNameFree(name, exclusionId);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to check request name, requests name: " + name, e);
	    throw e;
	}
    }
    
    /**
     * Creates duplicate existing {@link Request} 
     * 
     * @param fromId identifier of Request should be duplicated
     * @return duplicate of existing Request instance
     * @throws DataAccessException
     */
    @Transactional
    public Request createDuplicate(int fromId) {
	try {
	    Request request = load(fromId);
	    request.setId(0);
	    request.getHeaders().forEach(i -> i.setId(0));
	    request.getVariables().forEach(i -> i.setId(0));
	    request.getDbValidations().forEach(i -> i.setId(0));
	    request.setName(request.getName() + duplicateSuffix);
	    return request;
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to duplicate the request, requests id: " + fromId, e);
	    throw e;
	}
    }
  
    /**
     * Generates unique name for {@link Request} instance.
     * 
     * @param request existing Request which name should be duplicated
     * @return generated request name
     */
    @SuppressWarnings("unused")
    private String createDuplicateName(Request request) {
	String name = request.getName();
	Matcher m = null;
	while (!isRequestNameFree(name, request.getId())) {
	    int _position = name.lastIndexOf("_");
	    if (_position == -1) {
		name += "_1";
	    } else {
		m = requestNameCountPattern.matcher(name);
		if (m.find()) {
		    int curValue = Integer.parseInt(m.group().substring(1));
		    name = name.substring(0, _position) + "_" + ++curValue;
		} else {
		    name += "_1";
		}
	    }
	}
	return name;
    }
    
    /**
     * Saves all dbValidations, variables, headers and labels for the request instance to the database.
     */
    private void saveRequestComponents(Request request) {
	List<DbValidation> dbValidations = request.getDbValidations();
	if (dbValidations != null && !dbValidations.isEmpty()) {
	    dbValidationMapper.saveByRequest(request);
	}
	List<Variable> variables = request.getVariables();
	if (variables!=null && !variables.isEmpty()) {
	    variableMapper.saveByRequest(request);
	}
	List<Header> headers = request.getHeaders();
	if (headers!=null && !headers.isEmpty()) {
	    headerMapper.saveByRequest(request);
	}
	List<Label> labels = request.getLabels();
	if (labels!=null && !labels.isEmpty()) {
	    labelMapper.saveByRequest(request);
	}
    }
}