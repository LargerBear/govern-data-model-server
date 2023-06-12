package com.freesofts.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *@Author: 周伟
 *@CreateTime: 2023-03-08  09:22
 *@Version: 1.0
 */
@Data
@AllArgsConstructor
public class NodeVO {
    private String key ;
    private String pkey ;
    private String title ;
    private Boolean isLeaf ;
    private String uniqueCode;
}
