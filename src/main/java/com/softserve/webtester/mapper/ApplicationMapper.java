package com.softserve.webtester.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.model.Application;


/**
 * ApplicationMapper is MyBatis mapper interface for CRUD operations.
 * @author Roman Zolotar
 * @version 1.1
 */

@Repository
public interface ApplicationMapper {
	final String loadAll = "SELECT * FROM Application";
	final String load = "SELECT * FROM Application WHERE ID = #{id}";
	final String deleteById = "DELETE from Application WHERE ID = #{id}";
	final String insert = "INSERT INTO Application (NAME, DESCRIPTION, DELETED) VALUES (#{name}, #{description}, #{deleted})";
	final String update = "UPDATE Application SET DELETED = #{deleted}, NAME = #{name}, DESCRIPTION = #{description} WHERE ID = #{id}";

	@Select(loadAll)
	@Results(value = { 	@Result(property = "id", column = "ID"), 
						@Result(property = "name", column = "NAME"),
						@Result(property = "description", column = "DESCRIPTION"),
						@Result(property = "deleted", column = "DELETED") })

	Set<Application> loadAll();

	@Select(load)
	@Results(value = { 	@Result(property = "id", column = "ID"), 
						@Result(property = "name", column = "NAME"),
						@Result(property = "description", column = "DESCRIPTION"),
						@Result(property = "deleted", column = "DELETED") })

	Application load(int id);

	@Update(update)
	void update(Application application);

	@Delete(deleteById)
	void delete(int id);

	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void save(Application application);
}
