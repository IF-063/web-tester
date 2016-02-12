package com.softserve.webtester.mapper;

import com.softserve.webtester.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import java.util.LinkedHashSet;

/**
 * MyBatis EnvironmentHistoryMapper mapper for performing CRUD operations on EnvironmentHistory database instance.
 *
 */

@Repository
public interface EnvironmentHistoryMapper {

    @Insert("INSERT INTO EnvironmentHistory VALUES(NULL, #{resultHistory.id}, #{name}, #{baseURL}, #{dbURL}, " +
            "#{dbPort}, #{dbName}, #{environment.id},)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(EnvironmentHistory environmentHistory);

    @Select("SELECT id, name, baseURL, dbURL, dbPort, dbName, environmentId FROM EnvironmentHistory WHERE id = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "baseURL", column = "baseURL", jdbcType = JdbcType.VARCHAR),
            @Result(property = "dbURL", column = "dbURL", jdbcType = JdbcType.VARCHAR),
            @Result(property = "dbPort", column = "dbPort", jdbcType = JdbcType.INTEGER),
            @Result(property = "dbName", column = "dbName", jdbcType = JdbcType.VARCHAR),
            @Result(property = "environment", column = "environmentId",
                    one = @One(select = "com.softserve.webtester.mapper.EnvironmentMapper.load"))
    })
    EnvironmentHistory load(int id);

    @Update("UPDATE EnvironmentHistory SET resultHistoryId = #{resultHistory.id}, name = #{name}, baseURL = #{baseURL}, "
            + "dbURL = #{dbURL}, dbPort = #{dbPort}, dbName = #{dbName}," +
            "environmentId = #{environment.id} WHERE id = #{id}")
    int update(EnvironmentHistory environmentHistory);

    @Delete("DELETE FROM EnvironmentHistory WHERE id = #{id}")
    int delete(int id);

    @Delete("DELETE FROM EnvironmentHistory WHERE resultHistoryId = #{id}")
    int deleteByResultHistoryId(int id);
}
