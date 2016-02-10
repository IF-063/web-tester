package com.softserve.webtester.mapper;

import java.util.LinkedHashSet;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.model.Label;
import com.softserve.webtester.model.Request;

@Repository
public interface LabelMapper {

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
     * Loads all {@link Label} instances for the Request from the database.
     * 
     * @param id identifier of {@link Request} instance, whose labels should be loaded
     * @return Set of Label instances
     * @throws DataAccessException
     */
    @Select("SELECT l.id, l.name FROM Label l INNER JOIN Request_Label rl ON rl.labelId = l.id "
    	    + "WHERE rl.requestId = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
	       @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR)
    })
    LinkedHashSet<Label> loadByRequestId(int id);
    
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

}