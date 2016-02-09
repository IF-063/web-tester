package com.softserve.webtester.mapper;

import java.util.LinkedHashSet;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.model.DbValidation;
import com.softserve.webtester.model.Request;

@Repository
public interface DbValidationMapper {
    
    @Insert("INSERT INTO DbValidation(sqlQuery, expectedValue, requestId) " 
	    + "VALUES(#{sqlQuery}, #{expectedValue}, #{request.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(DbValidation dbValidation);
    
    @Insert("<script>INSERT INTO DbValidation(sqlQuery, expectedValue, requestId) VALUES "
	    + "<foreach collection='dbValidations' item='dbValidation' separator=','> "
	    + "(#{dbValidation.sqlQuery}, #{dbValidation.expectedValue}, #{id}) "
	    + "</foreach></script>")
    int saveByRequest(Request request);
    
    @Select("SELECT id, sqlQuery, expectedValue FROM DbValidation WHERE id = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.BIGINT),
	       @Result(property = "sqlQuery", column = "sqlQuery", jdbcType = JdbcType.LONGVARCHAR),
	       @Result(property = "expectedValue", column = "expectedValue", jdbcType = JdbcType.VARCHAR)
    })
    DbValidation load(long id);
    
    @Select("SELECT id, sqlQuery, expectedValue FROM DbValidation WHERE requestId = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.BIGINT),
	       @Result(property = "sqlQuery", column = "sqlQuery", jdbcType = JdbcType.LONGVARCHAR),
	       @Result(property = "expectedValue", column = "expectedValue", jdbcType = JdbcType.VARCHAR)
    })
    LinkedHashSet<DbValidation> loadByRequestId(long id);
    
    @Update("UPDATE DbValidation SET sqlQuery = #{sqlQuery}, expectedValue = #{expectedValue}, " 
	    + "requestId = #{request.id} WHERE id = #{id}")
    int update(DbValidation dbValidation);
    
    @Delete("DELETE FROM DbValidation WHERE id = #{id}")
    int delete(long id);
    
    @Delete("DELETE FROM DbValidation WHERE requestId = #{id}")
    int deleteByRequestId(long id);

}