package com.softserve.webtester.mapper;

import java.util.LinkedHashSet;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.type.JdbcType;
import com.softserve.webtester.model.Request;
import com.softserve.webtester.model.RequestCollection;

@Repository
public interface RequestCollectionMapper {
	
    @Insert("INSERT INTO RequestCollection (name, description) VALUES (#{name}, #{description})")  
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(RequestCollection RequestCollection);
	
    @Select("SELECT*FROM RequestCollection")
    @Results({	@Result(property = "id", column = "ID", jdbcType = JdbcType.INTEGER),
		@Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
		@Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR)			
    })
    LinkedHashSet<RequestCollection> loadAll();
	
    @Select("SELECT*FROM RequestCollection WHERE id = #{id}")
    @Results({	@Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
		@Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
		@Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR),
		@Result(property = "requests", column ="id", javaType = LinkedHashSet.class,
			many = @Many(select = "com.softserve.webtester.mapper.RequestMapper.loadByRequestCollectionId"))           
    })
    RequestCollection load(int id);
	
    @Update("UPDATE RequestCollection SET name = #{name}, description = #{description}, WHERE id = #{id}")
    int update(RequestCollection requestCollection);
	
    @Delete("DELETE FROM RequestCollection WHERE id = #{id}")
    int detele(int id);
	
    @Delete("DELETE FROM RequestCollection_Request WHERE requestId = #{rId} and requestCollectionId = #{rcId}")
    int deleteFromCollection(int rId, int rcId);
	
	

}
