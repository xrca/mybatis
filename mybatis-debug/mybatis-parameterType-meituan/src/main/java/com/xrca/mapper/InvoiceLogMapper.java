package com.xrca.mapper;

import com.xrca.entity.InvoiceLog;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * @author xrca
 * @description log查询
 * @date 2020-08-08 10：24
 */
public interface InvoiceLogMapper {
    
    /**
     * @Author xrca
     * @Description 根据id查询
     * @Date 2020-08-08 10:26
     * @Param [id]
     * @return com.xrca.entity.InvoiceLog
     **/
    InvoiceLog selectById(@Param("id") Long id);

    /**
     * @Author xrca
     * @Description 根据id更新记录，也是此模块讨论的主要问题
     * @Date 2020-08-08 10:34
     * @Param [id, response, updateTime]
     * @return int
     **/
    int updateResponse(@Param("id") long id, @Param("response") String response, @Param("updateTime") LocalDateTime updateTime);

    /**
     * @Author xrca
     * @Description 去除parameterType，让mybatis自动识别类型
     * @Date 2020-08-08 10:50
     * @Param [id, response, updateTime]
     * @return int
     **/
    int updateResponse2(@Param("id") long id, @Param("response") String response, @Param("updateTime") LocalDateTime updateTime);
}
