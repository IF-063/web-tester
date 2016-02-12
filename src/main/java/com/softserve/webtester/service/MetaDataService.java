package com.softserve.webtester.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.webtester.mapper.ApplicationMapper;
import com.softserve.webtester.mapper.ServiceMapper;
import com.softserve.webtester.model.Application;

/**
 * MetaDataService class implements CRUD operation on {@link Applications} &
 * {@link Service} instance in the database.<br>
 * The service uses Spring DataSourceTransactionManager for managing transaction
 * with the database and log4j for logging.
 * 
 * @author Roman Zolotar
 * @version 1.0
 */

@Service
@Transactional
public class MetaDataService {

	private static final Logger LOGGER = Logger.getLogger(MetaDataService.class);

	@Autowired
	private ApplicationMapper applicationMapper;

	@Autowired
	private ServiceMapper serviceMapper;

	// TODO Anton Mykytiuk add Labels and BuildVersion mappers

	public Set<Application> appLoadAll(String loadAll) {
		try {
			return applicationMapper.loadAll();
		} catch (DataAccessException e) {
			LOGGER.error("Unable to read requested instances: " + e);
			throw e;
		}
	}

	public Application appLoad(int id) {
		try {
			return applicationMapper.load(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to read requested instance, request id: " + id, e);
			throw e;
		}
	}

	public void appUpdate(Application application) {
		try {
			applicationMapper.update(application);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to update requested instance", e);
			throw e;
		}
	}

	public void appDeleteById(int id) {
		try {
			applicationMapper.delete(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to delete requested instance", e);
			throw e;
		}
	}

	public void appInsert(Application application) {
		try {
			applicationMapper.insert(application);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to delete requested instance", e);
			throw e;
		}
	}

	public Set<com.softserve.webtester.model.Service> servLoadAll(String loadAll) {
		try {
			return serviceMapper.loadAll();
		} catch (DataAccessException e) {
			LOGGER.error("Unable to read requested instances: " + e);
			throw e;
		}
	}

	public com.softserve.webtester.model.Service servLoad(int id) {
		try {
			return serviceMapper.load(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to read requested instance, request id: " + id, e);
			throw e;
		}
	}

	public void servUpdate(com.softserve.webtester.model.Service service) {
		try {
			serviceMapper.update(service);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to update requested instance", e);
			throw e;
		}
	}

	public void sevDeleteById(int id) {
		try {
			serviceMapper.delete(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to delete requested instance", e);
			throw e;
		}
	}

	public void servInsert(com.softserve.webtester.model.Service service) {
		try {
			serviceMapper.insert(service);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to delete requested instance", e);
			throw e;
		}
	}
}
