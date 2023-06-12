package com.freesofts.model.vo;

import lombok.Data;

/**
 * Description:页面管理查询条件
 * 版权所有：
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * <p>
 * 杭州孚立计算机软件有限公司
 *
 * @author 付欣
 * date: 2023/2/14
 * @since JDK 1.8
 */
@Data
public class PageQueryVO {
    /**
     * 关键字
     */
    private String key;
    /**
     * 页面名字
     */
    private String name;
    /**
     * 页面类型
     */
    private String type;
    /**
     * 开始时间
     */
    private String startDate;
    /**
     * 结束时间
     */
    private String endDate;
    /**
     * 租户id
     */
    private String tenantId;
}
