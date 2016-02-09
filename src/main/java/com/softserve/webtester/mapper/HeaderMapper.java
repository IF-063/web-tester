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

import com.softserve.webtester.model.Header;
import com.softserve.webtester.model.Request;

@Repository
public interface HeaderMapper {
    
    @Insert("INSERT INTO Header(name, value, requestId) VALUES(#{name}, #{value}, #{request.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Header header);
    
    @Insert("<script>INSERT INTO Header(name, value, requestId) VALUES "
	    + "<foreach collection='headers' item='header' separator=','> "
	    + "(#{header.name}, #{header.value}, #{id}) "
	    + "</foreach></script>")
    int saveByRequest(Request request);
    
    @Select("SELECT id, name, value FROM Header WHERE id = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.BIGINT),
	       @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
	       @Result(property = "value", column = "value", jdbcType = JdbcType.VARCHAR)
    })
    Header load(long id);
    
    @Select("SELECT id, name, value FROM Header WHERE requestId = #{id}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.BIGINT),
	       @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
	       @Result(property = "value", column = "value", jdbcType = JdbcType.VARCHAR)
    })
    LinkedHashSet<Header> loadByRequestId(long id);

    @Update("UPDATE Header SET name = #{name}, value = #{value}, requestId = #{request.id} WHERE id = #{id}")
    int update(Header header);
    
    @Delete("DELETE FROM Header WHERE id = #{id}")
    int delete(long id);
    
    @Delete("DELETE FROM Header WHERE requestId = #{id}")
    int deleteByRequestId(long id);

}