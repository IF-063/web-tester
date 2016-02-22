package com.softserve.webtester.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.webtester.mapper.ApplicationMapper;
import com.softserve.webtester.mapper.BuildVersionMapper;
import com.softserve.webtester.mapper.LabelMapper;
import com.softserve.webtester.mapper.ServiceMapper;
import com.softserve.webtester.model.Application;
import com.softserve.webtester.model.BuildVersion;
import com.softserve.webtester.model.Label;
import com.softserve.webtester.model.Service;

/**
 * MetaDataService class implements CRUD operation on {@link Application},
 * {@link Service}, {@link BuildVersion} and {@link Label} instances in the database.
 * The service uses Spring DataSourceTransactionManager for managing transaction
 * with the database and log4j for logging.
 * 
 * @author Roman Zolotar, Anton Mykytiuk
 * @version 1.1
 */

@org.springframework.stereotype.Service
@Transactional
public class MetaDataService {

	private static final Logger LOGGER = Logger.getLogger(MetaDataService.class);

	@Autowired
	private ApplicationMapper applicationMapper;

	@Autowired
	private ServiceMapper serviceMapper;

	@Autowired
    private BuildVersionMapper buildVersionMapper;

    @Autowired
    private LabelMapper labelMapper;
    
    										//APPLICATION
    
    /**
     * Loads all {@link Application} instances from database
     * @return List of {@link Application} instances
     * @throws DataAccessException
     */

	public List<Application> applicationLoadAll() {
		try {
			return applicationMapper.loadAll();
		} catch (DataAccessException e) {
			LOGGER.error("Unable to read Application table: ", e);
			throw e;
		}
	}

	/**
     * Loads {@link Application} instance from database
     * @param id
     * @return id of Loaded Application
     * @throws DataAccessException
     */
	
	public Application applicationLoad(int id) {
		try {
			return applicationMapper.load(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to read line from Application table with next id: " + id, e);
			throw e;
		}
	}

	/**
     * Updates {@link Application} instance in database
     * @param application object
     * @throws DataAccessException
     */
	
	public void applicationUpdate(Application application) {
		try {
			applicationMapper.update(application);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to update line in Application table with next id: " + application.getId(), e);
			throw e;
		}
	}
	
	/**
     * Deletes {@link Application} instance from database
     * @param id
     * @throws DataAccessException
     */

	public void applicationDelete(int id) {
		try {
			applicationMapper.delete(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to delete line from Application table with next id: " + id, e);
			throw e;
		}
	}
	
	/**
     * Saves {@link Application} instance in database
     * @param application object
     * @throws DataAccessException
     */

	public void applicationSave(Application application) {
		try {
			applicationMapper.save(application);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to create line in Application table with next id: ", e);
			throw e;
		}
	}
	
											//SERVICE
	
	/**
     * Loads all {@link Service} instances from database
     * @return List of {@link Service} instances
     * @throws DataAccessException
     */

	public List<Service> serviceLoadAll() {
		try {
			return serviceMapper.loadAll();
		} catch (DataAccessException e) {
			LOGGER.error("Unable to read Service table: ", e);
			throw e;
		}
	}
	
	/**
     * Loads {@link Service} instance from database
     * @param id
     * @return id of Loaded Service
     * @throws DataAccessException
     */

	public Service serviceLoad(int id) {
		try {
			return serviceMapper.load(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to read line from Service table with next id: " + id, e);
			throw e;
		}
	}
	
	/**
     * Updates {@link Service} instance in database
     * @param service object
     * @throws DataAccessException
     */

	public void serviceUpdate(Service service) {
		try {
			serviceMapper.update(service);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to update line in Service table with next id: " + service.getId(), e);
			throw e;
		}
	}
	
	/**
     * Deletes {@link Service} instance from database
     * @param id
     * @throws DataAccessException
     */

	public void serviceDelete(int id) {
		try {
			serviceMapper.delete(id);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to delete line from Service table with next id: " + id, e);
			throw e;
		}
	}
	
	/**
     * Saves {@link Service} instance in database
     * @param service object
     * @throws DataAccessException
     */

	public void serviceSave(Service service) {
		try {
			serviceMapper.save(service);
		} catch (DataAccessException e) {
			LOGGER.error("Unable to create line in Service table: ", e);
			throw e;
		}
	}

                                                //BUILD_VERSIONS

    /**
     * Saves {@link BuildVersion} instance to database
     * @param buildVersion
     * @return id of saved BuildVersion
     * @throws DuplicateKeyException if the buildVersion with the same name exists in the database
     * @throws DataAccessException
     */
    public int saveBuildVersion(BuildVersion buildVersion) {
        try {
            buildVersionMapper.saveBuildVersion(buildVersion);
            int id = buildVersion.getId();
            LOGGER.info("Successfully saved BuildVersion instance in the database, buildVersion id: " + id);
            return id;
        } catch (DataAccessException e) {
            LOGGER.error("Unable to save BuildVersion instance, buildVersion id: " + buildVersion.getId(), e);
            throw e;
        }
    }

    /**
     * Loads {@link BuildVersion} instance from database
     * @param id
     * @return {@link BuildVersion} instance
     * @throws DataAccessException
     */
    public BuildVersion loadBuildVersionById(int id) {
        try {
            return buildVersionMapper.loadBuildVersionById(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load BuildVersion instance, buildVersion id: " + id, e);
            throw e;
        }
    }

    /**
     * Loads all stored {@link BuildVersion} instances with their main information.
     * @return Set of {@link BuildVersion} instances
     * @throws DataAccessException
     */
    List<BuildVersion> loadAllBuildVersions() {
        try {
            return buildVersionMapper.loadAllBuildVersions();
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load BuildVersion instances", e);
            throw e;
        }
    }

    /**
     * Updates {@link BuildVersion} instance should be updated in the database.
     * @param buildVersion
     * @return the number of rows affected by the statement
     * @throws DuplicateKeyException if the buildVersion with the same name exists in the database.
     * @throws DataAccessException
     */
    int updateBuildVersion(BuildVersion buildVersion) {
        try {
            buildVersionMapper.updateBuildVersion(buildVersion);
            int id = buildVersion.getId();
            LOGGER.info("Successfully updated BuildVersion instance in the database, buildVersion id: " + id);
            return id;
        } catch (DataAccessException e) {
            LOGGER.error("Unable to update BuildVersion instance, buildVersion id " + buildVersion.getId(), e);
            throw e;
        }
    }

    /**
     * Deletes {@link BuildVersion} instance from the database.
     * @param id
     * @return the number of rows affected by the statement
     * @throws DataAccessException
     */
    int deleteBuildVersion(int id) {
        try {
            return buildVersionMapper.deleteBuildVersion(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete BuildVersion instance, request id: " + id, e);
            throw e;
        }
    }

                                                    //LABELS

    /**
     * Saves {@link Label} instance to database
     * @param label
     * @return id of saved Label
     * @throws DuplicateKeyException if the Label with the same name exists in the database
     * @throws DataAccessException
     */
    public int saveLabel(Label label) {
        try {
            labelMapper.saveLabel(label);
            int id = label.getId();
            LOGGER.info("Successfully saved Label instance in the database, label id: " + id);
            return id;
        } catch (DataAccessException e) {
            LOGGER.error("Unable to save Label instance, label id: " + label.getId(), e);
            throw e;
        }
    }

    /**
     * Loads {@link Label} instance from database
     * @param id
     * @return {@link Label} instance
     * @throws DataAccessException
     */
    public Label loadLabelById(int id) {
        try {
            return labelMapper.loadLabelById(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load Label instance, label id: " + id, e);
            throw e;
        }
    }

    /**
     * Loads all stored {@link Label} instances with their main information.
     * @return Set of {@link Label} instances
     * @throws DataAccessException
     */
   public List<Label> loadAllLabels() {
        try {
            return labelMapper.loadAllLabels();
        } catch (DataAccessException e) {
            LOGGER.error("Unable to load Label instances", e);
            throw e;
        }
    }

    /**
     * Deletes {@link Label} instance from the database.
     * @param id
     * @return the number of rows affected by the statement
     * @throws DataAccessException
     */
   public int deleteLabel(int id) {
        try {
            return labelMapper.deleteLabel(id);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to delete Label instance, request id: " + id, e);
            throw e;
        }
    }

}
