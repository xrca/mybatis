package com.xrca.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xrca
 * @description 补贴
 * @date 2020-07-22 23:10
 */
@Data
public class Subsidy {
    // Id
    private Long recordId;

    // 补贴人
    private String personName;

    // 补贴人所在公司
    private String companyName;

    // 补贴人所在区
    private String area;

    // 第一个月是否收到补贴 1-是 0-否
    private Integer firstMonth;

    // 第二个月是否收到补贴 1-是 0-否
    private Integer secondMonth;

    // 第三个月是否收到补贴 1-是 0-否
    private Integer thirdMonth;

    // 当季的补贴总额
    private BigDecimal totalMoney;

    // 年份
    private String year;

    // 季度
    private String quarter;
}
