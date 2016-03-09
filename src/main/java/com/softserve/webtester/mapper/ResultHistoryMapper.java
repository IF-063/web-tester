package com.softserve.webtester.mapper;

import com.softserve.webtester.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * MyBatis ResultHistoryMapper mapper for performing CRUD operations on ResultHistory database instance.
 *
 */

@Repository
public interface ResultHistoryMapper {

    @Insert("INSERT INTO ResultHistory VALUES(NULL, #{status}, #{application.id}, #{serviceId}, #{request.id}," +
            " #{requestName}, #{requestDescription}, #{url}, #{responseType}, #{requestBody}, " +
            "#{statusLine}, #{timeStart}, #{expectedResponseTime}, #{responseTime}, #{expectedResponse}," +
            " #{actualResponse}, #{message}, #{runId}, #{requestCollection.id}, #{buildVersion.id}")

    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(ResultHistory resultHistory);

    /*@Select("SELECT id, status, applicationId, serviceId, requestId, requestName, requestDescription, url," +
            "responseType, requestBody, statusLine, timeStart, expectedResponseTime, responseTime," +
            "expectedResponse, actualResponse, message, runId, requestCollectionId, buildVersionId " +
            "FROM ResultHistory WHERE id = #{id}")*/

    @Select("SELECT id, status, applicationId, serviceId, requestId, requestName, requestDescription, url," +
            "responseType, requestBody, statusLine, timeStart, expectedResponse, actualResponse, message," +
            " runId, requestCollectionId, buildVersionId FROM ResultHistory WHERE id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "status", column = "status", jdbcType = JdbcType.VARCHAR),
            @Result(property = "application", column = "applicationId",
                    one = @One(select = "com.softserve.webtester.mapper.ApplicationMapper.load")),
            @Result(property = "service", column = "serviceId",
                    one = @One(select = "com.softserve.webtester.mapper.ServiceMapper.load")),
            @Result(property = "request", column = "requestId",
                    one = @One(select = "com.softserve.webtester.mapper.RequestMapper.load")),
            @Result(property = "requestName", column = "requestName", jdbcType = JdbcType.VARCHAR),
            @Result(property = "requestDescription", column = "requestDescription", jdbcType = JdbcType.VARCHAR),
            @Result(property = "url", column = "url", jdbcType = JdbcType.VARCHAR),
            @Result(property = "responseType", column = "responseType", jdbcType = JdbcType.VARCHAR),
            @Result(property = "requestBody", column = "requestBody", jdbcType = JdbcType.LONGVARCHAR),
            @Result(property = "statusLine", column = "statusLine", jdbcType = JdbcType.LONGVARCHAR),
            @Result(property = "timeStart", column = "timeStart", jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "expectedResponseTime", column = "expectedResponseTime", jdbcType = JdbcType.INTEGER),
            @Result(property = "responseTime", column = "responseTime", jdbcType = JdbcType.INTEGER),
            @Result(property = "expectedResponse", column = "expectedResponse", jdbcType = JdbcType.LONGVARCHAR),
            @Result(property = "actualResponse", column = "actualResponse", jdbcType = JdbcType.LONGVARCHAR),
            @Result(property = "message", column = "message", jdbcType = JdbcType.LONGVARCHAR),
            @Result(property = "runId", column = "runId", jdbcType = JdbcType.INTEGER),
            @Result(property = "buildVersion", column = "buildVersionId",
                    one = @One(select = "com.softserve.webtester.mapper.BuildVersionMapper.loadBuildVersionById")),
            @Result(property = "requestCollection", column = "requestCollectionId",
                    one = @One(select = "com.softserve.webtester.mapper.RequestCollectionMapper.load")),
            @Result(property = "labels", column = "id",
                    many = @Many(select = "com.softserve.webtester.mapper.LabelMapper.loadByResultHistoryId")),
            @Result(property = "headerHistories", column = "id",
                    many = @Many(select = "com.softserve.webtester.mapper.HeaderHistoryMapper.loadByResultHistoryId")),
            @Result(property = "dbValidationHistories", column = "id",
                    many = @Many(select = "com.softserve.webtester.mapper.DbValidationHistoryMapper.loadByResultHistoryId"))
    })
    ResultHistory loadById(int id);

    @Select("SELECT id, status, applicationId, serviceId, requestName, message, requestDescription, timeStart " +
            "FROM ResultHistory")
    @Results({
            @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "status", column = "status", jdbcType = JdbcType.VARCHAR),
            @Result(property = "application", column = "applicationId",
                    one = @One(select = "com.softserve.webtester.mapper.ApplicationMapper.load")),
            @Result(property = "service", column = "serviceId",
                    one = @One(select = "com.softserve.webtester.mapper.ServiceMapper.load")),
            @Result(property = "requestName", column = "requestName", jdbcType = JdbcType.VARCHAR),
            @Result(property = "message", column = "message", jdbcType = JdbcType.LONGVARCHAR),
            @Result(property = "requestDescription", column = "requestDescription", jdbcType = JdbcType.VARCHAR),
            @Result(property = "timeStart", column = "timeStart", jdbcType = JdbcType.TIMESTAMP)
    })
    List<ResultHistory> loadAll();

    @Delete("DELETE FROM ResultHistory WHERE id = #{id}")
    int detele(int id);

    @Delete("DELETE FROM ResultHistory")
    int deteleAll();
}