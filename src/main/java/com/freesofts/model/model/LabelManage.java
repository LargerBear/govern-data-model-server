package com.freesofts.model.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
* 页面标签管理表
* @author zhouwei
 * @TableName desktop_label_manage
*/
@Data
@Accessors(chain = true)
public class LabelManage implements Serializable {

    /**
    * 主键id
    */
    @NotBlank(message="[主键id]不能为空")
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
    * 父id
    */
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("父id")
    @Length(max= 32,message="编码长度不能超过32")
    private String parentId;
    /**
    * 目录层级
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("目录层级")
    @Length(max= 255,message="编码长度不能超过255")
    private Integer level;
    /**
    * 唯一编码
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("唯一编码")
    @NotBlank(message = "唯一编码不能为空")
    @Length(max= 255,message="编码长度不能超过255")
    private String uniqueCode;
    /**
    * 状态
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("状态")
    @Length(max= 255,message="编码长度不能超过255")
    private String state;
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
}
