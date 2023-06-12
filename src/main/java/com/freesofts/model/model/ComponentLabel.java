package com.freesofts.model.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 部件标签关系表
 *
 * @TableName desktop_component_label
 */
@Data
@ApiModel("部件标签关系表")
public class ComponentLabel implements Serializable {
    /**
     * 标签id
     */
    @NotBlank(message = "[标签id]不能为空")
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("标签id")
    @Length(max = 32, message = "编码长度不能超过32")
    private String labelId;
    /**
     * 部件id
     */
    @NotBlank(message = "[部件id]不能为空")
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("部件id")
    @Length(max = 32, message = "编码长度不能超过32")
    private String componentId;
    /**
     * 主键id
     */
    @NotBlank(message = "[主键id]不能为空")
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("主键id")
    @Length(max = 32, message = "编码长度不能超过32")
    private String id;
}
