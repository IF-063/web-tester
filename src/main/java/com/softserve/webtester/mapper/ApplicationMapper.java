package com.softserve.webtester.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.model.Application;


/**
 * ApplicationMapper is MyBatis mapper interface for {@link Application} CRUD operations.
 * @author Roman Zolotar
 * @version 1.2
 */

@Repository
public interface ApplicationMapper {
	final String LOAD_ALL = "SELECT * FROM Application";
	final String LOAD = "SELECT * FROM Application WHERE ID = #{id}";
	final String DELETE_BY_ID = "DELETE from Application WHERE ID = #{id}";
	final String INSERT = "INSERT INTO Application (NAME, DESCRIPTION, DELETED) VALUES (#{name}, #{description}, #{deleted})";
	final String UPDATE = "UPDATE Application SET DELETED = #{deleted}, NAME = #{name}, DESCRIPTION = #{description} WHERE ID = #{id}";

	@Select(LOAD_ALL)
	@Results(value = { 	@Result(property = "id", column = "ID"), 
						@Result(property = "name", column = "NAME"),
						@Result(property = "description", column = "DESCRIPTION"),
						@Result(property = "deleted", column = "DELETED") })

	List<Application> loadAll();

	@Select(LOAD)
	@Results(value = { 	@Result(property = "id", column = "ID"), 
						@Result(property = "name", column = "NAME"),
						@Result(property = "description", column = "DESCRIPTION"),
						@Result(property = "deleted", column = "DELETED") })

	Application load(int id);

	@Update(UPDATE)
	void update(Application application);

	@Delete(DELETE_BY_ID)
	void delete(int id);

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void save(Application application);
}
