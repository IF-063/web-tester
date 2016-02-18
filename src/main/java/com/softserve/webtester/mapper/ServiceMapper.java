package com.softserve.webtester.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.model.Service;


/**
 * ServiceMapper is MyBatis mapper interface for {@link Service} CRUD operations.
 * @author Roman Zolotar
 * @version 1.1
 */

@Repository
public interface ServiceMapper {
	final String LOAD_ALL = "SELECT * FROM Service";
	final String LOAD = "SELECT * FROM Service WHERE ID = #{id}";
	final String DELETE_BY_ID = "DELETE from Service WHERE ID = #{id}";
	final String INSERT = "INSERT INTO Service (NAME, DESCRIPTION, DELETED) VALUES (#{name}, #{description}, #{deleted})";
	final String UPDATE = "UPDATE Service SET DELETED = #{deleted}, NAME = #{name}, DESCRIPTION = #{description} WHERE ID = #{id}";

	@Select(LOAD_ALL)
	@Results(value = { 	@Result(property = "id", column = "ID"), 
						@Result(property = "name", column = "NAME"),
						@Result(property = "description", column = "DESCRIPTION"),
						@Result(property = "deleted", column = "DELETED") })

	List<Service> loadAll();

	@Select(LOAD)
	@Results(value = { 	@Result(property = "id", column = "ID"), 
						@Result(property = "name", column = "NAME"),
						@Result(property = "description", column = "DESCRIPTION"),
						@Result(property = "deleted", column = "DELETED") })

	Service load(int id);

	@Update(UPDATE)
	void update(Service service);

	@Delete(DELETE_BY_ID)
	void delete(int id);

	@Insert(INSERT)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void save(Service service);
}
