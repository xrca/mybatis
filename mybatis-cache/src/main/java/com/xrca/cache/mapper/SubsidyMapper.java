package com.xrca.cache.mapper;

import com.xrca.cache.entity.Subsidy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xrca
 * @description 补贴Mapper
 * @date 2020/12/28 11:04
 */
public interface SubsidyMapper {
    // 根据Id查询数据
    Subsidy selectById(@Param("recordId") Long recordId);

    List<Subsidy> selectAll(@Param("id") Long recordId, String name, String name2);

    // 插入
    int insertOne(Subsidy subsidy);

    int insertTest();

    default String init() {
        System.out.println("init");
        return "ok";
    }
}
