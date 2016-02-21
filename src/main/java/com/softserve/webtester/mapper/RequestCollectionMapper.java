package com.softserve.webtester.mapper;


import java.util.List;
import org.apache.ibatis.annotations.*;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.type.JdbcType;
import com.softserve.webtester.model.RequestCollection;

/**
 * RequestCollectionMapper is MyBatis mapper interface for CRUD operation on {@link RequestCollection} instance in the database.
 *
 */
@Repository
public interface RequestCollectionMapper {
    
    /**
     * Saves {@link Request—ollection} instance to database.
     * 
     * @param requestCollection Request—ollection instance should be saved in the database
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */    
    @Insert("INSERT INTO RequestCollection (name, description) VALUES (#{name}, #{description})")  
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(RequestCollection requestCollection);
    
    /**
     * Loads all {@link RequestCollection} instances from the database.<br>
     * This method loads only main information about requestCollection instance.
     * 
     * @return List of RequestCollection instances
     * @throws DataAccessException
     */    
    @Select("SELECT * FROM RequestCollection")
    @Results({	@Result(property = "id", column = "ID", jdbcType = JdbcType.INTEGER),
		@Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
		@Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR),
		@Result(property = "labels", column ="id",
		        many = @Many(select = "com.softserve.webtester.mapper.LabelMapper.loadByRequestCollectionId"))
    })
    List<RequestCollection> loadAll();
    
    /**
     * Loads {@link RequestCollection} instance from database by its identifier.
     * 
     * @param id identifier of RequestCollection instance
     * @return RequestCollection instance
     * @throws DataAccessException
     */    
    @Select("SELECT * FROM RequestCollection WHERE id = #{id}")
    @Results({	@Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
		@Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
		@Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR),
		@Result(property = "labels", column ="id",
		        many = @Many(select = "com.softserve.webtester.mapper.LabelMapper.loadByRequestCollectionId")),
		@Result(property = "requests", column ="id",
			many = @Many(select = "com.softserve.webtester.mapper.RequestMapper.loadByRequestCollectionId"))           
    })
    RequestCollection load(int id);
    
    /**
     * Updates {@link RequestCollection} instances in the database.
     * 
     * @param requestCollection RequestCollection instance should be updated
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */    
    @Update("UPDATE RequestCollection SET name = #{name}, description = #{description}, WHERE id = #{id}")
    int update(RequestCollection requestCollection);
    
    /**
     * Deletes {@link RequestCollection} instance from the database.
     * 
     * @param id identifier of RequestCollection instance should be deleted
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Delete("DELETE FROM RequestCollection WHERE id = #{id}")
    int detele(int id);
    
    /**
     * Deletes {@link RequestCollection_Request} instance from the database.
     * 
     * @param rId, rcId identifiers of RequestCollection_Request instance should be deleted
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Delete("DELETE FROM RequestCollection_Request WHERE requestId = #{rId} and requestCollectionId = #{rcId}")
    int deleteFromCollection(int rId, int rcId);
}
