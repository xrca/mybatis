package com.xrca.mapper;

import com.xrca.entity.Subsidy;
import org.apache.ibatis.annotations.Param;

/**
 * @author xrca
 * @description 补贴Mapper
 * @date 2020-07-22 23:18
 */
public interface SubsidyMapper {
    // 根据Id查询数据
    Subsidy selectById(@Param("recordId") Long recordId);
}
