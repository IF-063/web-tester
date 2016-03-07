package com.softserve.webtester.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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
	
	public Connection  getConnection(Environment environment) throws Exception{
		
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		String DB_URL = "jdbc:mysql://" + environment.getDbUrl() + "/" + environment.getDbName();
		
		String USER = environment.getDbUsername();
		String PASS = environment.getDbPassword();
		 
		Connection connection = null;
		 
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException se) {
			throw se;			
		} catch (Exception e) {
			throw e;
		}
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		/*dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://sql4.freemysqlhosting.net:3306/sql4101108");
		dataSource.setUsername("sql4101108");
		dataSource.setPassword("amuCx6E29c");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
		dataSource.setUsername("roo");
		dataSource.setPassword("MySQL");*/
		return connection;
	}
	
	public String checkConnection(Environment environment) throws Exception{
		Connection connection = null;
		Statement statement = null;
		String sql = "Select 1 from dual";
		String result = null;
		try {
			connection = getConnection(environment);
			statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()){
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
