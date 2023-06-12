package com.freesofts.model.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@ApiModel("地图json配置表")
public class MapConfig implements Serializable {

    @ApiModelProperty("主键id")
    @Size(max = 32, message = "id 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String id;

    @ApiModelProperty("组织id")
    @Size(max = 32, message = "组织id 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String orgId;


    @ApiModelProperty("地图名称")
    @NotBlank(message = "分类名称不能为空")
    @Size(max = 50, message = "name 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String mapName;

    private String createdBy;

    private String creatorName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    private String lastModifiedBy;

    private String lastModifiedName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate;

    private String tenantId;
    
    public interface WhenUpdate {

    }

}
