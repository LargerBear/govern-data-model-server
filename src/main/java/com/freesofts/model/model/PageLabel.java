package com.freesofts.model.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * 页面标签关系表
 *
 * @TableName desktop_page_label
 */
@Data
@Accessors(chain = true)
@ApiModel("页面标签关系表")
public class PageLabel implements Serializable {
    /**
     * 标签id
     */
    @NotBlank(message = "[标签id]不能为空")
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("标签id")
    @Length(max = 32, message = "编码长度不能超过32")
    private String labelId;
    /**
     * 页面标签
     */
    @NotBlank(message = "[页面标签]不能为空")
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("页面标签")
    @Length(max = 32, message = "编码长度不能超过32")
    private String pageId;
    /**
     * 主键id
     */
    @NotBlank(message = "[主键id]不能为空")
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("主键id")
    @Length(max = 32, message = "编码长度不能超过32")
    private String id;
}
