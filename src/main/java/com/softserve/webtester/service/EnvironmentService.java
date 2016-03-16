package com.softserve.webtester.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Collator;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.webtester.mapper.EnvironmentMapper;
import com.softserve.webtester.model.Environment;

/**
 * EnvironmentService class implements CRUD operations on Environment instance
 * in the database
 *
 */
@Service
@Transactional
public class EnvironmentService {

    private static final Logger LOGGER = Logger.getLogger(Environment.class);

    @Value("${environment.timemultiplier.default}")
    private float defaultTimeMultiplier;

    @Autowired
    private EnvironmentMapper environmentMapper;

    public float getDefaultTimeMultiplier() {
        return defaultTimeMultiplier;
    }

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
    public List<Environment> loadAll() {
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

    /**
     * @see EnvironmentMapper#isNameFree(Environment environment) method
     * @throws DataAccessException
     */
    public int isNameFree(Environment environment) {
        try {
            return environmentMapper.isNameFree(environment);
        } catch (DataAccessException e) {
            LOGGER.error("Unable to check environment name, environments name: " + environment.getName(), e);
            throw e;
        }
    }

    public Connection getConnection(Environment environment) throws Exception {
        String jdbcDriver = environment.getDbType().getDbDriver();
        String connectionUrl = String.format(environment.getDbType().getConnectionPattern(), environment.getDbUrl(),
                environment.getDbPort(), environment.getDbName(), environment.getDbUsername(),
                environment.getDbPassword());

        Connection connection = null;

        try {
            //Setting Locale for thin jdbc oracle driver
            /*if (environment.getDbType().name() == "ORACLE") {
                Locale ukrainian = new Locale("uk", "UA");
                Locale.setDefault(ukrainian);
            }*/
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        }

        return connection;
    }

    public String checkConnection(Environment environment) throws Exception {
        Connection connection = null;
        Statement statement = null;
        String sql = "Select 1 from dual";
        String result = null;
        try {
            connection = getConnection(environment);
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result = rs.getString("1");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
}
