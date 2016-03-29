package com.softserve.webtester.mapper;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.dto.ReportDataDTO;
import com.softserve.webtester.dto.StatisticDataDTO;

@Repository
public interface ReportMapper {

    @Select({ "<script>SELECT serviceId, buildVersionId, AVG(responseTime) as responseTime FROM ResultHistory",
            "WHERE serviceId = #{serviceId} AND buildVersionId IN",
            "<foreach collection='buildVersionId' item='item' index='index' open='(' separator=',' close=')'>",
            " #{item} </foreach>", "GROUP BY buildVersionId </script>" })
    @Results({
            @Result(property = "serviceName", column = "serviceId", 
                    one = @One(select = "com.softserve.webtester.mapper.ServiceMapper.loadServiceName") ),
            @Result(property = "responseTime", column = "responseTime", jdbcType = JdbcType.INTEGER),
            @Result(property = "buildVersionName", column = "buildVersionId", 
                    one = @One(select = "com.softserve.webtester.mapper.BuildVersionMapper.loadBuildVersionName") ) })
    List<ReportDataDTO> loadAvg(@Param(value = "serviceId") int serviceId,
            @Param(value = "buildVersionId") int[] buildVersionId);

    @Select({ "<script>SELECT serviceId, buildVersionId, AVG(responseTime) as responseTime FROM ResultHistory",
            "WHERE serviceId = #{serviceId} AND buildVersionId IN",
            "<foreach collection='buildVersionId' item='item' index='index' open='(' separator=',' close=')'>",
            " #{item} </foreach>", "GROUP BY buildVersionId </script>" })
    @Results({
            @Result(property = "serviceName", column = "serviceId",
                    one = @One(select = "com.softserve.webtester.mapper.ServiceMapper.loadServiceName") ),
            @Result(property = "responseTime", column = "responseTime", jdbcType = JdbcType.INTEGER),
            @Result(property = "buildVersionName", column = "buildVersionId", 
                    one = @One(select = "com.softserve.webtester.mapper.BuildVersionMapper.loadBuildVersionName") ) })
    List<ReportDataDTO> loadMax(@Param(value = "serviceId") int serviceId,
            @Param(value = "buildVersionId") int[] buildVersionId);

    @Select({ "SELECT AVG(responseTime) from (SELECT AVG(responseTime) as responseTime FROM ResultHistory"
            + " WHERE serviceId = #{serviceId} GROUP BY buildVersionId ORDER BY buildVersionId DESC LIMIT 3) as temp;"
           })
    int loadAvarage(int serviceId);


    
    
    
    
    @Select({ "<script>select list.res ", 
              "from (select avg(t1.responseTime) as res, t1.buildVersionId ",
              "from ResultHistory t1 where t1.serviceId = #{serviceId} and t1.buildVersionId in",
              "<foreach collection='buildVersionId' item='item' index='index' open='(' separator=',' close=')'>",
              " #{item} </foreach>", " group by t1.buildVersionId) list ", "right join ",
              "(select t2.buildVersionId from ResultHistory t2 where t2.buildVersionId in",
              "<foreach collection='buildVersionId' item='item' index='index' open='(' separator=',' close=')'>",
              " #{item} </foreach>", " group by t2.buildVersionId) q ",
              "on q.buildVersionId=list.buildVersionId;</script>" })
    List<Integer> loadAvgStatistic(@Param(value = "serviceId") int serviceId,
                                   @Param(value = "buildVersionId") int[] buildVersionId);

    @Select("select  id, name, sla from Service where id=#{id}")
    @Results({ @Result(property = "serviceName", column = "name", jdbcType = JdbcType.VARCHAR),
               @Result(property = "sla", column = "sla", jdbcType = JdbcType.INTEGER),
               @Result(property = "averageResponseTime", column = "id", 
                       one = @One(select = "com.softserve.webtester.mapper.ReportMapper.loadAvarage")) })
    StatisticDataDTO loadStatisticDataDTO(int id);
}