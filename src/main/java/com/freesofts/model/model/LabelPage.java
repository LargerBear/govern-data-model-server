package com.freesofts.model.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
* 页面标签表
* @TableName desktop_label_page
*/
@Data
@Accessors(chain = true)
@ApiModel("页面标签表")
public class LabelPage implements Serializable {

    /**
    * 主键id
    */
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("主键id")
    @Length(max= 32,message="编码长度不能超过32")
    private String id;
    /**
    * 标签名称
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("标签名称")
    @Length(max= 255,message="编码长度不能超过255")
    private String name;
    /**
    * 排序（默认100）
    */
    @ApiModelProperty("排序（默认100）")
    private Integer sort;
    /**
    * 创建人
    */
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("创建人")
    @Length(max= 32,message="编码长度不能超过32")
    private String createdBy;
    /**
    * 创建人名
    */
    @Size(max= 25,message="编码长度不能超过25")
    @ApiModelProperty("创建人名")
    @Length(max= 25,message="编码长度不能超过25")
    private String creatorName;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createdDate;
    /**
    * 最后修改人
    */
    @Size(max= 25,message="编码长度不能超过25")
    @ApiModelProperty("最后修改人")
    @Length(max= 25,message="编码长度不能超过25")
    private String lastModifiedBy;
    /**
    * 最后修改人名
    */
    @Size(max= 25,message="编码长度不能超过25")
    @ApiModelProperty("最后修改人名")
    @Length(max= 25,message="编码长度不能超过25")
    private String lastModifiedName;
    /**
    * 最后修改时间
    */
    @ApiModelProperty("最后修改时间")
    private Date lastModifiedDate;

    @NotBlank(message = "分类id不能为空")
    private String categoryId;

    private String uniqueCode;
}
