package com.softserve.webtester.mapper;

import java.util.LinkedHashSet;

import org.apache.ibatis.annotations.*;

import com.softserve.webtester.model.Service;

/**
 * ServiceMapper is MyBatis mapper interface for CRUD operations.
 * @author Roman Zolotar
 * @version 1.1
 */

public interface ServiceMapper {
	final String loadAll = "SELECT * FROM Application";
	final String load = "SELECT * FROM Application WHERE ID = #{id}";
	final String delete = "DELETE from Application WHERE ID = #{id}";
	final String insert = "INSERT INTO Application (NAME, DESCRIPTION, DELETED) VALUES (#{name}, #{description}, #{deleted})";
	final String update = "UPDATE Application SET DELETED = #{deleted}, NAME = #{name}, DESCRIPTION = #{description} WHERE ID = #{id}";

	@Select(loadAll)
	@Results(value = { 	@Result(property = "id", column = "ID"), 
						@Result(property = "name", column = "NAME"),
						@Result(property = "description", column = "DESCRIPTION"),
						@Result(property = "deleted", column = "DELETED") })

	LinkedHashSet<Service> loadAll();

	@Select(load)
	@Results(value = { 	@Result(property = "id", column = "ID"), 
						@Result(property = "name", column = "NAME"),
						@Result(property = "description", column = "DESCRIPTION"),
						@Result(property = "deleted", column = "DELETED") })

	Service load(int id);

	@Update(update)
	void update(Service service);

	@Delete(delete)
	void delete(int id);

	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(Service service);
}
