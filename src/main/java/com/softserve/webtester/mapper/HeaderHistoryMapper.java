package com.softserve.webtester.mapper;

import com.softserve.webtester.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MyBatis HeaderMapper mapper for performing CRUD operation on HeaderHistory database instance.
 */

@Repository
public interface HeaderHistoryMapper {


    /**
     * Saving {@link HeaderHistory} instance to DB.<br>
     * @param headerHistory DbValidation instance should be saved in DB
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Insert("INSERT INTO HeaderHistory VALUES(NULL, #{name}, #{value}, #{resultHistory.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(HeaderHistory headerHistory);

    /**
     * Saves {@link HeaderHistory} instances for the Request in DB.<br>
     * Using SQL batch insert this method saves all HeaderHistories.
     * @param resultHistory {@link ResultHistory} instance, whose HeaderHistories should be saved
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Insert("<script>INSERT INTO HeaderHistory(name, value, resultHistoryId) VALUES "
            + "<foreach collection='headerHistories' item='headerHistory' separator=','> "
            + "(#{headerHistory.name}, #{headerHistory.value}, #{id}) "
            + "</foreach></script>")
    int saveByResultHistory(ResultHistory resultHistory);

    /**
     * Loads {@link HeaderHistory} instance from DB by its identifier.
     * @param id identifier of HeaderHistory instance
     * @return HeaderHistory instance
     * @throws DataAccessException
     */
    @Select("SELECT id, name, value FROM HeaderHistory WHERE id = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "value", column = "value", jdbcType = JdbcType.VARCHAR)
    })
    HeaderHistory load(int id);

    /**
     * Loads all {@link HeaderHistory} instances for the ResultHistory from DB.
     * @param id identifier of {@link ResultHistory} instance, whose headerHistories should be loaded
     * @return List of HeaderHistory instances
     * @throws DataAccessException
     */
    @Select("SELECT id, name, value FROM HeaderHistory WHERE resultHistoryId = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "value", column = "value", jdbcType = JdbcType.VARCHAR)
    })
    List<HeaderHistory> loadByResultHistoryId(int id);

    /**
     * Updates {@link HeaderHistory} instance in the database.
     * @param headerHistory HeaderHistory instance should be updated
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Update("UPDATE HeaderHistory SET name = #{name}, value = #{value}, "
            + "resultHistoryId = #{resultHistory.id} WHERE id = #{id}")
    int update(HeaderHistory headerHistory);

    /**
     * Deletes {@link HeaderHistory} instance from DB.
     * @param id identifier of HeaderHistory instance to be be deleted
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Delete("DELETE FROM HeaderHistory WHERE id = #{id}")
    int delete(int id);

    /**
     * Deletes {@link HeaderHistory} instances from the database.
     * @param id identifier of {@link ResultHistory} instance, whose headerHistories should be deleted
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Delete("DELETE FROM HeaderHistory WHERE resultHistoryId = #{id}")
    int deleteByResultHistoryId(int id);
}