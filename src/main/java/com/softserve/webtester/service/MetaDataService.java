package com.softserve.webtester.service;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.webtester.mapper.ApplicationMapper;
import com.softserve.webtester.mapper.ServiceMapper;
import com.softserve.webtester.model.Application;
import com.softserve.webtester.model.Service;

/**
 * MetaDataService class implements CRUD operation on {@link Applications} &
 * {@link Service} instance in the database.<br>
 * The service uses Spring DataSourceTransactionManager for managing transaction
 * with the database and log4j for logging.
 * 
 * @author Roman Zolotar
 * @version 1.0
 */

@org.springframework.stereotype.Service
@Transactional
public class MetaDataService {

	private static final Logger LOGGER = Logger.getLogger(MetaDataService.class);

	@Autowired
	private ApplicationMapper applicationMapper;

	@Autowired
	private ServiceMapper serviceMapper;

	// TODO Anton Mykytiuk add Labels and BuildVersion mappers

	public Set<Application> applicationLoadAll(String loadAll) {
		try {
			return applicationMapper.loadAll();
		} catch (DataAccessException e) {
			LOGGER.error("Unable to read requested instances: " + e);
			throw e;
		}
	}

	public Application applicationLoad(int id) {
		try {
			return applicationMapper.load(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to read requested instance, request id: " + id, e);
			throw e;
		}
	}

	public void applicationUpdate(Application application) {
		try {
			applicationMapper.update(application);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to update requested instance", e);
			throw e;
		}
	}

	public void applicationDelete(int id) {
		try {
			applicationMapper.delete(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to delete requested instance", e);
			throw e;
		}
	}

	public void applicationSave(Application application) {
		try {
			applicationMapper.save(application);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to delete requested instance", e);
			throw e;
		}
	}

	public Set<Service> serviceLoadAll(String loadAll) {
		try {
			return serviceMapper.loadAll();
		} catch (DataAccessException e) {
			LOGGER.error("Unable to read requested instances: " + e);
			throw e;
		}
	}

	public Service serviceLoad(int id) {
		try {
			return serviceMapper.load(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to read requested instance, request id: " + id, e);
			throw e;
		}
	}

	public void serviceUpdate(Service service) {
		try {
			serviceMapper.update(service);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to update requested instance", e);
			throw e;
		}
	}

	public void serviceDelete(int id) {
		try {
			serviceMapper.delete(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to delete requested instance", e);
			throw e;
		}
	}

	public void serviceSave(Service service) {
		try {
			serviceMapper.save(service);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to delete requested instance", e);
			throw e;
		}
	}
}
