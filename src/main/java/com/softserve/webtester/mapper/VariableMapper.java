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

import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.Variable;
import com.softserve.webtester.model.VariableDataTypeHandler;

@Repository
public interface VariableMapper {
    
    @Insert("INSERT INTO Variable(name, value, isSql, isRandom, dataType, length, requestId) "
	    + "VALUES(#{name}, #{value}, #{isSql}, #{isRandom}, #{dataType}, #{length}, #{request.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Variable variable);
    
    @Insert("<script>INSERT INTO Variable(name, value, isSql, isRandom, dataType, length, requestId) VALUES "
	    + "<foreach collection='variables' item='variable' separator=','> "
	    + "(#{variable.name}, #{variable.value}, #{variable.isSql}, #{variable.isRandom}, "
	    + "#{variable.dataType}, #{variable.length}, #{id}) "
	    + "</foreach></script>")
    int saveByRequest(Request request);
    
    @Select("SELECT id, name, value, isSql, isRandom, dataType, length FROM Variable WHERE id = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.BIGINT),
	       @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
	       @Result(property = "value", column = "value", jdbcType = JdbcType.LONGVARCHAR),
	       @Result(property = "isSql", column = "isSql", jdbcType = JdbcType.BIT),
	       @Result(property = "isRandom", column = "isRandom", jdbcType = JdbcType.BIT),
	       @Result(property = "dataType", column = "dataType", typeHandler = VariableDataTypeHandler.class),
	       @Result(property = "length", column = "length", jdbcType = JdbcType.INTEGER),
    })
    Variable load(long id);
    
    @Select("SELECT id, name, value, isSql, isRandom, dataType, length FROM Variable WHERE requestId = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.BIGINT),
	       @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
	       @Result(property = "value", column = "value", jdbcType = JdbcType.LONGVARCHAR),
	       @Result(property = "isSql", column = "isSql", jdbcType = JdbcType.BIT),
	       @Result(property = "isRandom", column = "isRandom", jdbcType = JdbcType.BIT),
	       @Result(property = "dataType", column = "dataType", typeHandler = VariableDataTypeHandler.class),
	       @Result(property = "length", column = "length", jdbcType = JdbcType.INTEGER),
    })
    LinkedHashSet<Variable> loadByRequestId(long id);
    
    @Update("UPDATE Variable SET name = #{name}, value = #{value}, " 
	    + "isSql = #{isSql}, isRandom = #{isRandom}, dataType = #{dataType}, " 
	    + "length = #{length}, requestId = #{request.id} WHERE id = #{id}")
    int update(Variable variable);
    
    @Delete("DELETE FROM Variable WHERE id = #{id}")
    int delete(long id);
    
    @Delete("DELETE FROM Variable WHERE requestId = #{id}")
    int deleteByRequestId(long id);

}