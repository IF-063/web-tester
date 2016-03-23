package com.softserve.webtester.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import com.softserve.webtester.dto.ReportDataDTO;


@Repository
public interface ReportMapper {
    
    @Select({ "<script>SELECT rh.serviceId, rh.buildVersionId, AVG(rh.responseTime) FROM ResultHistory rh ",
        "WHERE rh.serviceId = #{serviceId} AND rh.buildVersionId IN",
        "<foreach collection='buildVersionId' item='item' index='index' open='(' separator=',' close=')'>",
        " #{item} </foreach>",
        "GROUP BY rh.serviceId,rh.buildVersionId </script>" })
    @Results({ @Result(property = "serviceName", column = "serviceId",    
                one = @One (select = "com.softserve.webtester.mapper.ServiceMapper.loadServiceName")),
        @Result(property = "responseTime", column = "responseTime", jdbcType = JdbcType.INTEGER),
        @Result(property = "buildVersionName", column = "buildVersionId", 
                many = @Many(select = "com.softserve.webtester.mapper.BuildVersionMapper.loadBuildVersionName") ) })
    ReportDataDTO loadAVG(@Param(value = "serviceId") int serviceId, 
                          @Param(value = "buildVersionId") int [] buildVersionId);
    
    @Select({ "<script>SELECT rh.serviceId, rh.buildVersionId, MAX(rh.responseTime) FROM ResultHistory rh ",
        "WHERE rh.serviceId = #{serviceId} AND rh.buildVersionId IN",
        "<foreach collection='buildVersionId' item='item' index='index' open='(' separator=',' close=')'>",
        " #{item} </foreach>",
        "GROUP BY rh.serviceId,rh.buildVersionId </script>" })
    @Results({ @Result(property = "serviceName", column = "serviceId",    
                one = @One (select = "com.softserve.webtester.mapper.ServiceMapper.loadServiceName")),
        @Result(property = "responseTime", column = "responseTime", jdbcType = JdbcType.INTEGER),
        @Result(property = "buildVersionName", column = "buildVersionId", 
                many = @Many(select = "com.softserve.webtester.mapper.BuildVersionMapper.loadBuildVersionName") ) })
    ReportDataDTO loadMAX(@Param(value = "serviceId") int serviceId, 
                          @Param(value = "buildVersionId") int [] buildVersionId);
        
        


}
