package com.softserve.webtester.mapper;

import java.util.List;

import com.softserve.webtester.model.ResultHistory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.model.Label;
import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.RequestCollection;

@Repository
public interface LabelMapper {

    /**
     * Saves {@link Label} instance to database
     * @param label
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Insert("INSERT INTO Label (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveLabel(Label label);

    /**
     * Loads {@link Label} instance from database by its identifier
     * @param id
     * @return Label instance
     * @throws DataAccessException
     */
    @Select("SELECT id, name FROM Label WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR)
    })
    Label loadLabelById(int id);

    /**
     * Loads all {@link Label} instances from the database
     * @return List of Label instaces
     * @throws DataAccessException
     */
    @Select("SELECT * from Label")
    @Results(value = {
            @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR)
    })
    List<Label> loadAllLabels();

    /**
     * Updates {@link Label} instance in the database. This method will not be used
     * @param label
     * @return
     * @throws DataAccessException
     */
    @Update("UPDATE Label SET name = #{name} WHERE id = #{id}")
    int updateLabel(Label label);


    /**
     * Deletes {@link Label} instance from the database by id
     * @param id
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Delete("DELETE FROM Label WHERE id = #{id}")
    int deleteLabel(int id);

    /**
     * Checks the unique of label's name.
     *
     * @param name name of {@link Label} should be checked
     * @param exclusionId id of {@link Label} should be excluded
     * @return true, if name is unique
     */
    @Select("SELECT IF(count(*) > 0, false, true) FROM Label WHERE name = #{name} AND id != #{exclusionId}")
    boolean isLabelNameFree(@Param("name") String name, @Param("exclusionId") int exclusionId);
    
    /**
     * Saves records to {@code Request_Label} junction table in the database by the Request.<br>
     * Using SQL batch insert this method saves all RequestId - LabelId relations in the database.
     * 
     * @param request {@link Request} instance, whose dbValidations should be saved
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Insert("<script>INSERT INTO Request_Label(requestId, labelId) VALUES "
	    + "<foreach collection='labels' item='label' separator=','> "
	    + "(#{id}, #{label.id}) </foreach></script>")
    int saveByRequest(Request request);

    /**
     * Saves records to {@code ResultHistory_Label} junction table in the database by the Request.<br>
     * Using SQL batch insert this method saves all ResultHistoryId - LabelId relations in the database.
     *
     * @param request {@link Request} instance, whose dbValidations should be saved
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */

    @Insert("<script>INSERT INTO ResultHistory_Label(resultHistoryId, labelId) VALUES "
            + "<foreach collection='labels' item='label' separator=','> "
            + "(#{id}, #{label.id}) </foreach></script>")
    int saveByResultHistory(ResultHistory resultHistory);
    
    /**
     * Saves records to {@code RequestCollection_Label} junction table in the database by the Request.<br>
     * Using SQL batch insert this method saves all RequestCollectionId - LabelId relations in the database.
     * 
     * @param request {@link RequestCollection} instance, whose dbValidations should be saved
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Insert("<script>INSERT INTO RequestCollection_Label(requestCollectionId, labelId) VALUES "
	    + "<foreach collection='labels' item='label' separator=','> "
	    + "(#{id}, #{label.id}) </foreach></script>")
    int saveByRequestCollection(RequestCollection requestCollection);
    
    /**
     * Loads all {@link Label} instances for the Request from the database.
     * 
     * @param id identifier of {@link Request} instance, whose labels should be loaded
     * @return List of Label instances
     * @throws DataAccessException
     */
    @Select("SELECT l.id, l.name FROM Label l INNER JOIN Request_Label rl ON rl.labelId = l.id "
    	    + "WHERE rl.requestId = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
	       @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR)
    })
    List<Label> loadByRequestId(int id);
    
    /**
     * Loads all {@link Label} instances for the RequestCollection from the database.
     * 
     * @param id identifier of {@link RequestCollection} instance, whose labels should be loaded
     * @return List of Label instances
     * @throws DataAccessException
     */
    @Select("SELECT l.id, l.name FROM Label l INNER JOIN RequestCollection_Label rcl ON rcl.labelId = l.id "
    	    + "WHERE rcl.requestCollectionId = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
	       @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR)
    })
    List<Label> loadByRequestCollectionId(int id);
    
    /**
     * Deletes all RequestId - LabelId relations from {@code Request_Label} junction table in the database for the
     * Request instance using its identifier.
     * 
     * @param id identifier of {@link Request} instance, whose labels should be deleted
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Delete("DELETE FROM Request_Label WHERE requestId = #{id}")
    int deleteByRequestId(int id);
    
    /**
     * Deletes all RequestCollectionId - LabelId relations from {@code Request_Label} junction table in the database for the
     * RequestCollection instance using its identifier.
     * 
     * @param id identifier of {@link RequestCollection} instance, whose labels should be deleted
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Delete("DELETE FROM RequestCollection_Label WHERE requestCollectionId = #{id}")
    int deleteByRequestCollectionId(int id);

}