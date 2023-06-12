package com.freesofts.model.vo;

import lombok.Data;

/**
 * 通用查询
 *
 *@Author: 周伟
 *@CreateTime: 2023-02-22  14:36
 *@Version: 1.0
 */
@Data
public class QueryVO {
    /**
     * 关键字
     */
    private String name;
    /**
     * 分类id
     */
    private String categoryId;
    /**
     * 租户id
     */
    private String tenantId;
}
