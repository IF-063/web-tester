package com.softserve.webtester.mapper;

import java.util.LinkedHashSet;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.model.Environment;

/**
 * 
 * MyBatisMapper provides database CRUD operations for Environment object
 *
 */
@Repository
public interface EnvironmentMapper {

	/**
	 * Saves Environment instance to database.
	 * Uses insertSql method from {@link EnvironmentSqlProvider}
	 * @param Environment environment
	 * @return number of rows affected by the statement
	 * @see com.softserve.webtester.mapper.EnvironmentSqlProvider.insertSql
	 */
	@InsertProvider(type = EnvironmentSqlProvider.class, method = "insertSql")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int save(Environment environment);
	
	/**
	 * Selects Environment entity from database.
	 * Uses selectSql method from {@link EnvironmentSqlProvider}
	 * @param id identifier of Environment instance
	 * @return Environment instance
	 */
	@SelectProvider (type = EnvironmentSqlProvider.class, method = "selectSql")
	@Results({
		@Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
		@Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
		@Result(property = "baseUrl", column = "baseUrl", jdbcType = JdbcType.VARCHAR),
		@Result(property = "dbUrl", column = "dbUrl", jdbcType = JdbcType.VARCHAR),
		@Result(property = "dbPort", column = "dbPort", jdbcType = JdbcType.VARCHAR),
		@Result(property = "dbName", column = "dbName", jdbcType = JdbcType.VARCHAR),
		@Result(property = "dbUsername", column = "dbUsername", jdbcType = JdbcType.VARCHAR),
		@Result(property = "dbPassword", column = "dbPassword", jdbcType = JdbcType.VARCHAR),
		@Result(property = "timeMultiplier", column = "timeMultiplier", jdbcType = JdbcType.DOUBLE),
		@Result(property = "deleted", column = "deleted", jdbcType = JdbcType.BIT)
	})	
	Environment load(int id);
	
	/**
	 * Selects all Environment entities from database.
	 * Uses selectAllSql method from {@link EnvironmentSqlProvider}
	 * @return List of Environment instances
	 */
	@SelectProvider (type = EnvironmentSqlProvider.class, method = "selectAllSql")
	@Results({
		@Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
		@Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
		@Result(property = "baseUrl", column = "baseUrl", jdbcType = JdbcType.VARCHAR),
		@Result(property = "dbUrl", column = "dbUrl", jdbcType = JdbcType.VARCHAR),
		@Result(property = "dbPort", column = "dbPort", jdbcType = JdbcType.VARCHAR),
		@Result(property = "dbName", column = "dbName", jdbcType = JdbcType.VARCHAR),
		@Result(property = "dbUsername", column = "dbUsername", jdbcType = JdbcType.VARCHAR),
		@Result(property = "dbPassword", column = "dbPassword", jdbcType = JdbcType.VARCHAR),
		@Result(property = "timeMultiplier", column = "timeMultiplier", jdbcType = JdbcType.DOUBLE),
		@Result(property = "deleted", column = "deleted", jdbcType = JdbcType.BIT)
	})	
	LinkedHashSet<Environment> loadAll();
	
	/**
	 * Updates Environment instance in the database.
	 * Uses updateSql method from {@link EnvironmentSqlProvider}
	 * @param Environment environment
	 * @return number of rows affected by the statement
	 */
	@UpdateProvider (type = EnvironmentSqlProvider.class, method = "updateSql")
	int update(Environment environment);
	
	/**
	 * Deletes Environment instance from database.
	 * Uses deleteSql method from {@link EnvironmentSqlProvider}
	 * @param Environment environment
	 * @return number of rows affected by the statement
	 */
	@UpdateProvider (type = EnvironmentSqlProvider.class, method = "deleteSql")
	int delete(Environment environment);	
}
