package com.softserve.webtester.mapper;

import java.util.LinkedHashSet;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.RequestCollection;
import com.softserve.webtester.model.RequestMethodHandler;
import com.softserve.webtester.model.ResponseTypeHandler;

/**
 * RequestMapper is MyBatis mapper interface for CRUD operation on {@link Request} instance in the database.
 *
 */
@Repository
public interface RequestMapper {
    
    /**
     * Saves {@link Request} instance to database.
     * 
     * @param request Request instance should be saved in the database
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Insert("INSERT INTO Request VALUES(NULL, #{name}, #{description}, #{requestMethod}, #{application.id}, "
	    + "#{service.id}, #{endpoint}, #{requestBody}, #{responseType}, #{expectedResponse}, #{timeout})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Request request);

    @Insert("<script>INSERT INTO RequestCollection_Request(requestCollectionId, requestId) VALUES "
	    + "<foreach collection='requests' item='request' separator=','> "
	    + "(#{id}, #{reques.id}) </foreach></script>")
    int saveByCollection(RequestCollection collecion); 

    /**
     * Loads {@link Request} instance from database by its identifier.
     * 
     * @param id identifier of Request instance
     * @return Request instance
     * @throws DataAccessException
     */
    @Select("SELECT id, name, description, requestMethod, applicationId, serviceId, endpoint, requestBody, "
	    + "responseType, expectedResponse, timeout FROM Request WHERE id = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
	       @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
	       @Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR),
	       @Result(property = "requestMethod", column = "requestMethod", typeHandler = RequestMethodHandler.class),
	       @Result(property = "application", column = "applicationId", 
	       	       one = @One(select = "com.softserve.webtester.mapper.ApplicationMapper.load")),
	       @Result(property = "service", column = "serviceId", 
	               one = @One(select = "com.softserve.webtester.mapper.ServiceMapper.load")),
	       @Result(property = "labels", column ="id",
	               many = @Many(select = "com.softserve.webtester.mapper.LabelMapper.loadByRequestId")),
	       @Result(property = "endpoint", column = "endpoint", jdbcType = JdbcType.VARCHAR),
	       @Result(property = "headers", column ="id",
	               many = @Many(select = "com.softserve.webtester.mapper.HeaderMapper.loadByRequestId")),
	       @Result(property = "requestBody", column = "requestBody", jdbcType = JdbcType.LONGVARCHAR),
	       @Result(property = "responseType", column = "responseType", typeHandler = ResponseTypeHandler.class),
	       @Result(property = "expectedResponse", column = "expectedResponse", jdbcType = JdbcType.LONGVARCHAR),
	       @Result(property = "timeout", column = "timeout", jdbcType = JdbcType.INTEGER),
	       @Result(property = "variables", column = "id",
	               many = @Many(select = "com.softserve.webtester.mapper.VariableMapper.loadByRequestId")),
	       @Result(property = "dbValidations", column = "id",
	               many = @Many(select = "com.softserve.webtester.mapper.DbValidationMapper.loadByRequestId")),
    })
    Request load(int id);
    
    /**
     * Loads all {@link Request} instances from the database.<br>
     * This method loads only main information about request instance.
     * 
     * @return Set of Request instances
     * @throws DataAccessException
     */
    @Select("SELECT id, name, applicationId, serviceId, endpoint FROM Request")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
	       @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
	       @Result(property = "application", column = "applicationId", 
	       	       one = @One(select = "com.softserve.webtester.mapper.ApplicationMapper.load")),
	       @Result(property = "service", column = "serviceId", 
	               one = @One(select = "com.softserve.webtester.mapper.ServiceMapper.load")),
	       @Result(property = "endpoint", column = "endpoint", jdbcType = JdbcType.VARCHAR),
    })
    LinkedHashSet<Request> loadAll();
    
    @Select("SELECT r.id, r.name, r.applicationId, r.serviceId, r.endpoint FROM Request r "
	    + "INNER JOIN RequestCollection_Request cr ON r.id = cr.requestId WHERE cr.requestCollectionId = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
	       @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
	       @Result(property = "application", column = "applicationId", 
	       	       one = @One(select = "com.softserve.webtester.mapper.ApplicationMapper.load")),
	       @Result(property = "service", column = "serviceId",
	               one = @One(select = "com.softserve.webtester.mapper.ServiceMapper.load")),
	       @Result(property = "endpoint", column = "endpoint", jdbcType = JdbcType.VARCHAR),
    })
    LinkedHashSet<Request> loadByRequestCollectionId(int id);
    
    /**
     * Updates {@link Request} instances in the database.
     * 
     * @param request Request instance should be updated
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Update("UPDATE Request SET name = #{name}, description = #{description}, " 
	    + "requestMethod = #{requestMethod}, applicationId = #{application.id}, " 
	    + "serviceId = #{service.id}, endpoint = #{endpoint}, requestBody = #{requestBody}, " 
	    + "responseType = #{responseType}, expectedResponse = #{expectedResponse}, timeout = #{timeout} "
	    + "WHERE id = #{id}")
    int update(Request request);
    
    /**
     * Deletes {@link Request} instance from the database.
     * 
     * @param id identifier of Request instance should be deleted
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Delete("DELETE FROM Request WHERE id = #{id}")
    int detele(int id);
    
    @Delete("DELETE FROM RequestCollection_Request WHERE requestCollectionId = #{id}")
    int deleteByRequestCollectionId(int id);
    
}