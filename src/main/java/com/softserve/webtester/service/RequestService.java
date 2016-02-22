package com.softserve.webtester.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 * @version 1.2
 */
@Service
@Transactional
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

    /**
     * Saves {@link Request} instance to the database.
     * 
     * @param request {@link Request} instance should be saved in the database
     * @return id of saved request
     * @throws DuplicateKeyException if the request with the name exists in the database.
     * @throws DataAccessException
     */
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
     * @throws DataAccessException
     */
    public Request load(int id) {
	try {
	    return requestMapper.load(id);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to load request instance, request id: " + id, e);
	    throw e;
	}
    }

    /**
     * Loads all stored {@link Request} instances with their main information.
     * 
     * @return List of {@link Request} instances
     * @throws DataAccessException
     */
    public List<Request> loadAll() {
	try {
	    return requestMapper.loadAll();
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to load request instances", e);
	    throw e;
	}
    }

    /**
     * Updates {@link Request} instance should be updated in the database.
     * 
     * @param request {@link Request} instance to be saved
     * @return the number of rows affected by the statement
     * @throws DuplicateKeyException if the request with the name exists in the database.
     * @throws DataAccessException
     */
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
     * @throws DataAccessException
     */
    public void delete(int[] requestIdArray) {
	try {
	    requestMapper.deleteRequests(requestIdArray);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to delete request instances, requests id: " + requestIdArray, e);
	    throw e;
	}
    }
    
    /**
     * Invoke this method to save all dbValidations, variables, headers and labels for the request instance to the
     * database
     */
    private void saveRequestComponents(Request request) {
	List<DbValidation> dbValidations = request.getDbValidations();
	if (dbValidations!=null && !dbValidations.isEmpty()) {
	   boolean b = dbValidations.removeIf(element -> element.getSqlQuery()==null || element.getSqlQuery().isEmpty());
	   System.out.println("dbv: "  + b);
	   dbValidationMapper.saveByRequest(request);
	}
	List<Variable> variables = request.getVariables();
	if (request.getVariables()!=null && !request.getVariables().isEmpty()) {
	    boolean b = variables.removeIf(element -> element.getName()==null || element.getName().isEmpty());
	    System.out.println("var: "  + b);
	    variableMapper.saveByRequest(request);
	}
	List<Header> headers = request.getHeaders();
	if (request.getHeaders()!=null && !request.getHeaders().isEmpty()) {
	    boolean b = headers.removeIf(element -> element.getName()==null || element.getName().isEmpty());
	    System.out.println("hea: "  + b);
	    headerMapper.saveByRequest(request);
	}
	List<Label> labels = request.getLabels();
	if (request.getLabels()!=null && !request.getLabels().isEmpty()) {
	    boolean b = labels.removeIf(element -> element.getName()==null || element.getName().isEmpty());
	    System.out.println("lab: "  + b);
	    labelMapper.saveByRequest(request);
	}
    }
}