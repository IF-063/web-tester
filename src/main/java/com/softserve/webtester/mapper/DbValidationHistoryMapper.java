package com.softserve.webtester.mapper;

import com.softserve.webtester.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MyBatis DbValidationHistoryMapper mapper for performing CRUD operations on DbValidationHistory database instance.
 */

@Repository
public interface DbValidationHistoryMapper {

    @Insert("INSERT INTO DBValidationHistory VALUES(NULL, #{sqlQuery}, #{expectedValue}, #{actualValue}, #{resultHistory.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(DBValidationHistory dbValidationHistory);

    @Insert("<script>INSERT INTO DBValidationHistory(sqlQuery, expectedValue, actualValue, resultHistoryId) VALUES "
            + "<foreach collection='dbValidationHistories' item='dbValidationHistory' separator=','> "
            + "(#{dbValidationHistory.sqlQuery}, #{dbValidation.expectedValue}, #{dbValidation.actualValue}, #{id}) "
            + "</foreach></script>")
    int saveByResultHistory(ResultHistory resultHistory);

    @Select("SELECT id, sqlQuery, expectedValue, actualValue FROM DBValidationHistory WHERE id = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "sqlQuery", column = "sqlQuery", jdbcType = JdbcType.LONGVARCHAR),
            @Result(property = "expectedValue", column = "expectedValue", jdbcType = JdbcType.VARCHAR),
            @Result(property = "actualValue", column = "actualValue", jdbcType = JdbcType.VARCHAR)
    })
    DBValidationHistory load(int id);

    @Select("SELECT id, sqlQuery, expectedValue, actualValue FROM DBValidationHistory WHERE resultHistoryId = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "sqlQuery", column = "sqlQuery", jdbcType = JdbcType.LONGVARCHAR),
            @Result(property = "expectedValue", column = "expectedValue", jdbcType = JdbcType.VARCHAR),
            @Result(property = "actualValue", column = "actualValue", jdbcType = JdbcType.VARCHAR)
    })
    List<DBValidationHistory> loadByResultHistoryId(int id);

    @Update("UPDATE DBValidationHistory SET sqlQuery = #{sqlQuery}, expectedValue = #{expectedValue}, "
            + "actualValue = #{actualValue}, resultHistoryId = #{resultHistory.id} WHERE id = #{id}")
    int update(DBValidationHistory dBValidationHistory);

    @Delete("DELETE FROM DBValidationHistory WHERE id = #{id}")
    int delete(int id);

    @Delete("DELETE FROM DbValidation WHERE resultHistoryId = #{id}")
    int deleteByResultHistoryId(int id);
}
