package com.softserve.webtester.mapper;

import com.softserve.webtester.model.BuildVersion;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;

@Repository
public interface BuildVersionMapper {

    final String INSERT = "INSERT INTO buildversion (name, description) VALUES (#{name}, #{description})";
    final String SELECT_BY_ID = "SELECT id, name, description FROM buildversion WHERE id = #{id} AND deleted = 0";
    final String SELECT_ALL = "SELECT id, name, description from buildversion WHERE deleted = 0";
    final String UPDATE = "UPDATE buildversion SET name = #{name}, description = #{description}, deleted = #{deleted}" +
            " WHERE id = #{id}";
    final String DELETE = "UPDATE buildversion SET deleted = 1 WHERE id = #{id}";
    final String HARD_DELETE = "DELETE FROM buildversion WHERE id = #{id}";

    @Insert(INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(BuildVersion buildVersion);

    @Select(SELECT_BY_ID)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description")
    })
    BuildVersion loadById(int id);

    @Select(SELECT_ALL)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description")
    })
    LinkedHashSet<BuildVersion> loadAll();

    @Update(UPDATE)
    void update(BuildVersion buildVersion);

    @Update(DELETE)
    void delete(int id);

    @Delete(HARD_DELETE)
    void hardDelete(int id);

}
