package com.softserve.webtester.mapper;

import org.apache.ibatis.jdbc.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class EnvironmentSqlProvider {

	private static final String TABLE_NAME = "Environment";

	public String insertSql() {
		return new SQL() {
			{
				INSERT_INTO(TABLE_NAME);
				VALUES("name, baseUrl, dbUrl, dbPort", "${name}, ${baseUrl}, ${dbUrl}, ${dbPort}");
				VALUES("dbName, dbUsername, dbPassword", "${dbName}, ${dbUsername}, ${dbPassword}");
				VALUES("timeMultiplier", "${timeMultiplier}");
			}
		}.toString();
	}

	public String selectSql() {
		return new SQL() {
			{
				SELECT("id, name, baseUrl, dbUrl, dbPort, dbName");
				SELECT("dbUsername, dbPassword, timeMultiplier, deleted");
				FROM(TABLE_NAME);
				WHERE("id = ${id}");
				WHERE("deleted = b'0'");
			}
		}.toString();
	}

	public String selectAllSql() {
		return new SQL() {
			{
				SELECT("id, name, baseUrl, dbUrl, dbPort, dbName");
				SELECT("dbUsername, dbPassword, timeMultiplier, deleted");
				FROM(TABLE_NAME);
				WHERE("deleted = b'0'");
			}
		}.toString();
	}

	public String updateSql() {
		return new SQL() {{
		    UPDATE (TABLE_NAME);
			SET ("name = ${name}, baseUrl = ${baseUrl}, dbUrl = ${dbUrl}");
			SET ("dbPort = ${dbPort}, dbName = ${dbPort}");
		    SET ("dbUsername  = ${dbUsername}, dbPassword = ${dbPassword}");
		    SET ("timeMultiplier= = ${timeMultiplier}");
		    WHERE ("id = ${id}");		    
		  }}.toString();
	}
	
	public String deleteSql() {
		return new SQL() {{
		    UPDATE (TABLE_NAME);
			SET ("deleted = b'1'");
			WHERE ("id = ${id}");		    
		  }}.toString();
	}
}
