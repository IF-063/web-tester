package com.softserve.webtester.mapper;

import com.softserve.webtester.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MyBatis HeaderMapper mapper for performing CRUD operation on HeaderHistory database instance.
 */

@Repository
public interface HeaderHistoryMapper {

    @Insert("INSERT INTO HeaderHistory VALUES(NULL, #{name}, #{value}, #{resultHistory.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(HeaderHistory headerHistory);

    @Insert("<script>INSERT INTO HeaderHistory(name, value, resultHistoryId) VALUES "
            + "<foreach collection='headerHistories' item='headerHistory' separator=','> "
            + "(#{headerHistory.name}, #{headerHistory.value}, #{id}) "
            + "</foreach></script>")
    int saveByResultHistory(ResultHistory resultHistory);

    @Select("SELECT id, name, value FROM HeaderHistory WHERE id = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "value", column = "value", jdbcType = JdbcType.VARCHAR)
    })
    HeaderHistory load(int id);

    @Select("SELECT id, name, value FROM HeaderHistory WHERE resultHistoryId = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "value", column = "value", jdbcType = JdbcType.VARCHAR)
    })
    List<HeaderHistory> loadByResultHistoryId(int id);

    @Update("UPDATE HeaderHistory SET name = #{name}, value = #{value}, "
            + "resultHistoryId = #{resultHistory.id} WHERE id = #{id}")
    int update(HeaderHistory headerHistory);

    @Delete("DELETE FROM HeaderHistory WHERE id = #{id}")
    int delete(int id);

    @Delete("DELETE FROM HeaderHistory WHERE resultHistoryId = #{id}")
    int deleteByResultHistoryId(int id);
}