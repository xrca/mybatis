package com.xrca.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xrca
 * @description log实体
 * @date 2020-08-08 9:59
 */
@Data
public class InvoiceLog {
    private Long id;

    private String response;

    private LocalDateTime updateTime;
}
