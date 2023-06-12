package com.freesofts.model.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class Component implements Serializable {

    @Size(max = 32, message = "id 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String id;

    @ApiModelProperty("部件名称")
    @NotBlank(message = "部件名称不能为空")
    @Size(max = 100, message = "name 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String name;

    @ApiModelProperty("部件类型")
    @Size(max = 50, message = "type 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String type;

    @ApiModelProperty("配置选项")
    @Size(max = 4000, message = "options 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String options;

    @ApiModelProperty("排序")
    @DecimalMin(value = "1", message = "必须大于或等于1")
    private Long sort;

    @ApiModelProperty("描述")
    @Size(max = 200, message = "description 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String description;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("创建人名")
    private String creatorName;

    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String createdDate;

    @ApiModelProperty("最后修改人")
    private String lastModifiedBy;

    @ApiModelProperty("最后修改人姓名")
    private String lastModifiedName;

    @ApiModelProperty("最后修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String lastModifiedDate;

    @ApiModelProperty("发布状态: 0 未发布、1 已发布")
    private String state;

    @ApiModelProperty("是否删除: 0 否、1 是")
    private int isDel;

    @ApiModelProperty("静态数据")
    private String partData;

    @ApiModelProperty("文件地址")
    private String filePath;

    public interface WhenUpdate {

    }

}
