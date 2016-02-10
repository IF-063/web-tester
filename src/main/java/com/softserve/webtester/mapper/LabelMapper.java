package com.softserve.webtester.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

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
     * Deletes all RequestId - LabelId relations from {@code Request_Label} junction table in the database for the
     * Request instance using its identifier.
     * 
     * @param id identifier of {@link Request} instance, whose labels should be deleted
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Delete("DELETE FROM Request_Label WHERE requestId = #{id}")
    int deleteByRequestId(long id);

}
