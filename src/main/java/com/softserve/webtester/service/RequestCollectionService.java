package com.softserve.webtester.service;


import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.softserve.webtester.mapper.LabelMapper;
import com.softserve.webtester.mapper.RequestCollectionMapper;
import com.softserve.webtester.mapper.RequestMapper;
import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.RequestCollection;

/**
 * RequestCollectionService class implements CRUD operation on {@link RequestCollection} instance in the database.<br>
 * The service uses Spring DataSourceTransactionManager for managing transaction with the database and log4j for
 * logging.
 * 
 * @author Yura Lubinec
 * @version 1.0
 */
@Service
@Transactional
public class RequestCollectionService {
    
    private static final Logger LOGGER = Logger.getLogger(RequestCollectionService.class);
    
    @Autowired
    private RequestCollectionMapper requestCollectionMapper;
    
    @Autowired
    private RequestMapper requestMapper;
    
    @Autowired
    private LabelMapper labelMapper;
    
    /**
     * Saves {@link RequestCollection} instance to the database.
     * 
     * @param request {@link RequestCollection} instance should be saved in the database
     * @return id of saved requestCollection
     * @throws DuplicateKeyException if the request with the name exists in the database.
     * @throws DataAccessException
     */
    public int save(RequestCollection requestCollection){
	try {
	    requestCollectionMapper.save(requestCollection);
	    int id = requestCollection.getId();
	    saveRequestsToCollection(requestCollection);
	    saveLabelsToCollection(requestCollection);
	    return id;
	} catch (DataAccessException e){
	    LOGGER.error("Unable to save RequestCollection instance"+ requestCollection.getId(), e);
	    throw e;
	}
    }    
    
    /**
     * Loads all stored {@link RequestCollection} instances with their main information.
     * 
     * @return List of {@link RequestCollection} instances
     * @throws DataAccessException
     */
    public List<RequestCollection> loadAll(){
	try {
	    return requestCollectionMapper.loadAll();
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to load RequestCollections",e);
	    throw e;
	}
    }
    
    /**
     * Loads {@link RequestCollection} instance with headers, dbValidations, labels and variables.
     * 
     * @param id identifier of RequestCollection instance
     * @return {@link RequestCollection} instance
     * @throws DataAccessException
     */
    public RequestCollection load(int id){
	try {
	    return requestCollectionMapper.load(id);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to load RequestCollection,RequestCollection id:" + id,e);
	    throw e;
	}
    }
    
    /**
     * Updates {@link RequestCollection} instance should be updated in the database.
     * 
     * @param request {@link RequestCollection} instance to be saved
     * @return id of updated RequestCollection
     * @throws DuplicateKeyException if the request with the name exists in the database.
     * @throws DataAccessException
     */
    public int update(RequestCollection requestCollection){ 
	try {
	    requestCollectionMapper.update(requestCollection);
	    int id = requestCollection.getId();
	    deleteRequestFromCollectionId(id);
	    labelMapper.deleteByRequestCollectionId(id);
	    saveRequestsToCollection(requestCollection);
	    saveLabelsToCollection(requestCollection);
	    return id;
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to update RequestCollection id:" + requestCollection.getId(), e);
	    throw e;
	}
    }
    
    /**
     * Deletes {@link RequestCollection} instance from the database.
     * 
     * @param id identifier of requestCollection to delete
     * @return the number of rows affected by the statement
     * @throws DataAccessException
     */
    public int detele(int id){
	try {
	    return requestCollectionMapper.detele(id);
	} catch (DataAccessException e){
	    LOGGER.error("Unable to delete RequestCollection id:" + id, e);
	    throw e;
	}    
    }
    
    /**
     * Deletes {@link RequestCollection_Request} instance from the database.
     * 
     * @param rId, rcId identifiers of RequestCollection_Request instance should be deleted
     * @return the number of rows affected by the statement
     * @throws DataAccessException
     */
    public int deleteFromCollection(int rId, int rcId){
	try {
	    return requestCollectionMapper.deleteFromCollection(rId, rcId);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to delete request from RequestCollection, Request id:" + rId + ", RequestCollection id:" + rcId, e);
	    throw e;
	}
    }
    
    /**
     * Invoke this method to save requests for the RequestCollection instance to the
     * database
     */
    private void saveRequestsToCollection(RequestCollection requestCollection){
	try {
	    requestMapper.saveByCollection(requestCollection);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to add request to RequestCollection", e);
	    throw e;
	}
    }
    
    /**
     * Invoke this method to save labels for the RequestCollection instance to the
     * database
     */
    private void saveLabelsToCollection(RequestCollection requestCollection){
	try {
	    if (!requestCollection.getLabels().isEmpty()) {
		labelMapper.saveByRequestCollection(requestCollection);
	    }
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to add label to RequestCollection", e);
	    throw e;
	}
    }
    
    /**
     * Invoke this method to delete request of the RequestCollection instance from the
     * database
     */
    private void deleteRequestFromCollectionId(int id){
	try {
	    requestMapper.deleteByRequestCollectionId(id);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to delete requests from RequestCollection,RequestCollection id: "+id, e);
	    throw e;
	}
    }
    
    
    
    

}
