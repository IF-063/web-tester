package com.softserve.webtester.service;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.webtester.mapper.EnvironmentMapper;
import com.softserve.webtester.model.Environment;

/**
 *  EnvironmentService class implements CRUD operations on Environment instance in the database
 *
 */
@Service
@Transactional
public class EnvironmentService {
	
	private static final Logger LOGGER = Logger.getLogger(Environment.class);
	
	@Autowired
	private EnvironmentMapper environmentMapper;
	
	/**
	 * @see EnvironmentMapper#load(int) method
	 * @throws DataAccessException
	 */
	public Environment load(int id) {
		try {
			return environmentMapper.load(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to load environment instance, id = " + id, e);
			throw e;
		}
	}
	
	/**
	 * @see EnvironmentMapper#loadAll() method
	 * @throws DataAccessException
	 */
	public Set<Environment> loadAll() {
		try {
			return environmentMapper.loadAll();
		} catch (DataAccessException e) {
			LOGGER.error("Unable to load environment instances ", e);
			throw e;
		}
	}
	
	/**
	 * @see EnvironmentMapper#save(Environment environment) method
	 * @throws DataAccessException
	 */
	public int save(Environment environment) {
		try {
			environmentMapper.save(environment);
			return environment.getId();
		} catch (DataAccessException e) {
			LOGGER.error("Unable to save environment instance, id " + environment.getId(), e);
			throw e;
		}
	}	

	/**
	 * @see EnvironmentMapper#update(Environment environment) method
	 * @throws DataAccessException
	 */
	public int update(Environment environment) {
		try {
			environmentMapper.update(environment);
			return environment.getId();
		} catch (DataAccessException e) {
			LOGGER.error("Unable to update environment instance, id " + environment.getId(), e);
			throw e;
		}
	}	
	
	/**
	 * @see EnvironmentMapper#delete(Environment environment) method
	 * @throws DataAccessException
	 */
	public int delete(Environment environment) {
		try {
			environmentMapper.delete(environment);
			return environment.getId();
		} catch (DataAccessException e) {
			LOGGER.error("Unable to delete environment instance, id " + environment.getId(), e);
			throw e;
		}
	}	
}
