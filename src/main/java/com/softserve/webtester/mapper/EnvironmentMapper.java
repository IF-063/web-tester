package com.softserve.webtester.mapper;

import java.util.LinkedHashSet;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.model.Environment;

@Repository
public interface EnvironmentMapper {

	@InsertProvider(type = EnvironmentSqlProvider.class, method = "insertSql")
	int save(Environment environment);
	
	@SelectProvider (type = EnvironmentSqlProvider.class, method = "selectSql")
	@Results({
		@Result(id = true, property = "id", column = "id", jdbcType = JdbcType.BIGINT),
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
	
	@SelectProvider (type = EnvironmentSqlProvider.class, method = "selectAllSql")
	@Results({
		@Result(id = true, property = "id", column = "id", jdbcType = JdbcType.BIGINT),
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
	
	@UpdateProvider (type = EnvironmentSqlProvider.class, method = "updateSql")
	int update(Environment environment);
	
	@UpdateProvider (type = EnvironmentSqlProvider.class, method = "deleteSql")
	int delete(Environment environment);	
}
