package com.softserve.webtester.mapper;

import com.softserve.webtester.model.Label;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;

@Repository
public interface AMLabelMapper {

    final String INSERT = "INSERT INTO label (name) VALUES (#{name})";
    final String SELECT_BY_ID = "SELECT id, name FROM label WHERE id = #{id}";
    final String SELECT_ALL = "SELECT * from label";
    final String UPDATE = "UPDATE label SET name = #{name} WHERE id = #{id}";
    final String DELETE = "DELETE FROM label WHERE id = #{id}";

    @Insert(INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Label label);

    @Select(SELECT_BY_ID)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    Label loadById(int id);

    @Select(SELECT_ALL)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    LinkedHashSet<Label> loadAll();

    @Update(UPDATE)
    void update(Label buildVersion);


    @Delete(DELETE)
    void delete(int id);
}
