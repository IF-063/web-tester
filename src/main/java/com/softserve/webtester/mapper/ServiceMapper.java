package com.softserve.webtester.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import com.softserve.webtester.model.Service;

/**
 * ServiceMapper is MyBatis mapper interface for CRUD operations.
 * @author Roman Zolotar
 * @version 1.0
 */

public interface ServiceMapper {
	final String getAll = "SELECT * FROM Application";
	final String getById = "SELECT * FROM Application WHERE ID = #{id}";
	final String deleteById = "DELETE from Application WHERE ID = #{id}";
	final String insert = "INSERT INTO Application (NAME, DESCRIPTION, DELETED) VALUES (#{name}, #{description}, #{deleted})";
	final String update = "UPDATE Application SET DELETED = #{deleted}, NAME = #{name}, DESCRIPTION = #{description} WHERE ID = #{id}";

	@Select(getAll)
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "deleted", column = "DELETED") })

	List getAll();

	@Select(getById)
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "deleted", column = "DELETED") })

	Service getById(int id);

	@Update(update)
	void update(Service service);

	@Delete(deleteById)
	void delete(int id);

	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(Service service);
}
