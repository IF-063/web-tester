package com.softserve.webtester.mapper;

import com.softserve.webtester.model.BuildVersion;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Repository
public interface BuildVersionMapper {

    /**
     * Saves {@link BuildVersion} instance to database
     * @param buildVersion
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Insert("INSERT INTO BuildVersion (name, description) VALUES (#{name}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveBuildVersion(BuildVersion buildVersion);

    /**
     * Loads {@link BuildVersion} instance from database by its identifier
     * @param id
     * @return BuildVersion instance
     * @throws DataAccessException
     */
    @Select("SELECT id, name, description FROM BuildVersion WHERE id = #{id} AND deleted = 0")
    @Results({ @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR)
    })
    BuildVersion loadBuildVersionById(int id);

    /**
     * Loads BuildVersion name from database by its identifier
     * @param id
     * @return BuildVersion name
     * @throws DataAccessException
     */
    @Select("SELECT name FROM BuildVersion WHERE id = #{id} AND deleted = 0")
    String loadBuildVersionName(int id);
    
    /**
     * Loads all {@link BuildVersion} instances from the database which aren't marked as "deleted"
     * @return Set of BuildVersion instaces
     * @throws DataAccessException
     */
    @Select("SELECT id, name, description from BuildVersion WHERE deleted = 0")
    @Results({ @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR)
    })
    List<BuildVersion> loadAllBuildVersions();

    /**
     * Updates {@link BuildVersion} instance in the database
     * @param buildVersion
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Update("UPDATE BuildVersion SET name = #{name}, description = #{description}, " +
            "deleted = #{deleted} WHERE id = #{id}")
    int updateBuildVersion(BuildVersion buildVersion);

    /**
     * Deletes (Soft Delete) {@link BuildVersion} instance from the database by id
     * @param id
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Update("UPDATE BuildVersion SET deleted = 1 WHERE id = #{id}")
    int deleteBuildVersion(int id);

    /**
     * Deletes {@link BuildVersion} instance from the database by id. This method will not be used
     * @param id
     * @return number of rows affected by the statement
     * @throws DataAccessException
     */
    @Delete("DELETE FROM BuildVersion WHERE id = #{id}")
    int hardDeleteBuildVersion(int id);

    /**
     * Checks the unique of build version's name.
     *
     * @param name name of {@link BuildVersion} should be checked
     * @param exclusionId id of {@link BuildVersion} should be excluded
     * @return true, if name is unique
     */
    @Select("SELECT IF(count(*) > 0, false, true) FROM BuildVersion WHERE name = #{name} AND id != #{exclusionId}")
    boolean isBuildVersionNameFree(@Param("name") String name, @Param("exclusionId") int exclusionId);
}