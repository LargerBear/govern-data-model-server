package com.freesofts.model.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Panel implements Serializable {

    @Size(max = 32, message = "id 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String id;

    @NotBlank(message = "面板名称不能为空")
    @Size(max = 100, message = "name 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String name;

    @Size(max = 50, message = "type 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String type;

    @Size(max = 4000, message = "options 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String options;

    @DecimalMin(value = "1", message = "必须大于或等于1")
    private Long sort;

    @Size(max = 200, message = "description 长度必须在 {min} - {max} 之间", groups = WhenUpdate.class)
    private String description;

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

    public interface WhenUpdate {

    }

}
