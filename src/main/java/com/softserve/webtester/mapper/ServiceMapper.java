package com.softserve.webtester.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.model.Service;

/**
 * ServiceMapper is MyBatis mapper interface for {@link Service} CRUD
 * operations.
 * 
 * @author Roman Zolotar
 * @version 1.3
 */

@Repository
public interface ServiceMapper {
    final String LOAD_ALL = "SELECT * FROM Service";
    final String LOAD_ALL_WITHOUT_DELETED = "SELECT * FROM Service WHERE DELETED = 0";
    final String LOAD = "SELECT * FROM Service WHERE ID = #{id}";
    final String LOADNAME = "SELECT NAME FROM Service WHERE ID = #{id}";    
    final String DELETE_BY_ID = "DELETE from Service WHERE ID = #{id}";
    final String INSERT = "INSERT INTO Service (NAME, DESCRIPTION, SLA, DELETED) VALUES (#{name}, #{description}, #{sla}, #{deleted})";
    final String UPDATE = "UPDATE Service SET DELETED = #{deleted}, NAME = #{name}, DESCRIPTION = #{description}, SLA = #{sla} WHERE ID = #{id}";
    final String IS_SERVICE_NAME_FREE = "SELECT IF(count(*) > 0, false, true) FROM Service WHERE name = #{name} AND id != #{exclusionId}";

    @Select(LOAD_ALL)
    @Results(value = { @Result(property = "id", column = "ID"), 
            @Result(property = "name", column = "NAME"),
            @Result(property = "description", column = "DESCRIPTION"),
            @Result(property = "sla", column = "SLA"),
            @Result(property = "deleted", column = "DELETED") })

    List<Service> loadAll();

    @Select(LOAD_ALL_WITHOUT_DELETED)
    @Results(value = { @Result(property = "id", column = "ID"), 
            @Result(property = "name", column = "NAME"),
            @Result(property = "description", column = "DESCRIPTION"),
            @Result(property = "sla", column = "SLA"),
            @Result(property = "deleted", column = "DELETED") })

    List<Service> loadAllWithoutDeleted();

    @Select(LOAD)
    @Results(value = { @Result(property = "id", column = "ID"), 
            @Result(property = "name", column = "NAME"),
            @Result(property = "description", column = "DESCRIPTION"),
            @Result(property = "sla", column = "SLA"),
            @Result(property = "deleted", column = "DELETED") })

    Service load(int id);

    @Select(LOADNAME)
    String loadServiceName(int id);

    @Update(UPDATE)
    void update(Service service);

    @Delete(DELETE_BY_ID)
    void delete(int id);

    @Insert(INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Service service);

    @Select(IS_SERVICE_NAME_FREE)
    boolean isServiceNameFree(@Param("name") String name, @Param("exclusionId") int exclusionId);
}
