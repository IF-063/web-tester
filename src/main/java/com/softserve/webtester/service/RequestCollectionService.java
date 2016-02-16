package com.softserve.webtester.service;


import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.softserve.webtester.mapper.LabelMapper;
import com.softserve.webtester.mapper.RequestCollectionMapper;
import com.softserve.webtester.mapper.RequestMapper;
import com.softserve.webtester.model.RequestCollection;

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
    
    List<RequestCollection> loadAll(){
	try {
	    return requestCollectionMapper.loadAll();
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to load RequestCollections",e);
	    throw e;
	}
    }
    
    public RequestCollection load(int id){
	try {
	    return requestCollectionMapper.load(id);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to load RequestCollection,RequestCollection id:" + id,e);
	    throw e;
	}
    }
    
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
    
    public int detele(int id){
	try {
	    return requestCollectionMapper.detele(id);
	} catch (DataAccessException e){
	    LOGGER.error("Unable to delete RequestCollection id:" + id, e);
	    throw e;
	}    
    }
    
    public int deleteFromCollection(int rId, int rcId){
	try {
	    return requestCollectionMapper.deleteFromCollection(rId, rcId);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to delete request from RequestCollection, Request id:" + rId + ", RequestCollection id:" + rcId, e);
	    throw e;
	}
    }
    
    private void saveRequestsToCollection(RequestCollection requestCollection){
	try {
	    requestMapper.saveByCollection(requestCollection);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to add request to RequestCollection", e);
	    throw e;
	}
    }
    
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
        
    private void deleteRequestFromCollectionId(int id){
	try {
	    requestMapper.deleteByRequestCollectionId(id);
	} catch (DataAccessException e) {
	    LOGGER.error("Unable to delete requests from RequestCollection,RequestCollection id: "+id, e);
	    throw e;
	}
    }
    
    
    
    

}
