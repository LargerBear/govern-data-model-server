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
import java.util.List;

@Getter
@Setter
public class Page implements Serializable {

    @Size(max = 32, message = "id 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String id;

    @ApiModelProperty("页面名称")
    @NotBlank(message = "页的名称不能为空")
    @Size(max = 100, message = "name 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String name;

    @ApiModelProperty("页面类型")
    @Size(max = 50, message = "type 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String type;

    @ApiModelProperty("配置选项")
    private String options;

    @ApiModelProperty("排序")
    @DecimalMin(value = "1", message = "必须大于或等于1")
    private Long sort;

    @ApiModelProperty("描述")
    @Size(max = 200, message = "description 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String description;

    @ApiModelProperty("文件路径")
    private String filePath;

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

    @ApiModelProperty("标签数组")
    private List<String> tags;

    @ApiModelProperty("租户id")
    private String tenantId;

    @ApiModelProperty("页面锁")
    private Integer lock;

    @ApiModelProperty("锁密码")
    private String password;

    public interface WhenUpdate {

    }

}
