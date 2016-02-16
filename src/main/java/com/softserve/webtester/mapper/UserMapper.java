package com.softserve.webtester.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.model.User;

/**
 * UserMapper is MyBatis mapper interface for CRUD operation on {@link User} instance in the database.
 *
 */
@Repository
public interface UserMapper {

    /**
     * Loads {@link User} instance from database by its identifier.
     * 
     * @param userId id of User instance
     * @return User instance
     * @throws DataAccessException
     */
    @Select("SELECT id, username, password, firstName, lastName FROM User WHERE id = #{userId}")
    @Results({ @Result(id = true, property = "id", column = "id", jdbcType = JdbcType.INTEGER),
	       @Result(property = "username", column = "username", jdbcType = JdbcType.VARCHAR),
	       @Result(property = "password", column = "password", jdbcType = JdbcType.VARCHAR),
	       @Result(property = "firstName", column = "firstName", jdbcType = JdbcType.VARCHAR),
	       @Result(property = "lastName", column = "lastName", jdbcType = JdbcType.VARCHAR)
    })
    User load(String userId);
    
    /**
     * Updates {@link User} instance in the database.
     * 
     * @param user user instance should be updated
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Update("UPDATE User SET username = #{username}, password = #{password}, firstName = #{firstName}, "
    	    + "lastName = #{lastName} WHERE id = #{id}")
    int update(User user);
    
}