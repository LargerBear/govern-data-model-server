package com.freesofts.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 * 版权所有：
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * <p>
 * 杭州孚立计算机软件有限公司
 *
 * @author 付欣
 * date: 2023/2/28
 * @since JDK 1.8
 */
@Data
public class ComponentVO {

    private String id;

    @ApiModelProperty("部件名称")
    private String name;

    @ApiModelProperty("部件类型")
    private String type;

    @ApiModelProperty("类型名称")
    private String typeName;

    @ApiModelProperty("配置选项")
    private String options;

    @ApiModelProperty("排序")
    private Long sort;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("创建人名")
    private String creatorName;

    @ApiModelProperty("创建时间")
    private String createdDate;

    @ApiModelProperty("最后修改人")
    private String lastModifiedBy;

    @ApiModelProperty("最后修改人姓名")
    private String lastModifiedName;

    @ApiModelProperty("最后修改时间")
    private String lastModifiedDate;

    @ApiModelProperty("静态数据")
    private String partData;

    @ApiModelProperty("文件地址")
    private String filePath;

    @ApiModelProperty("发布状态: 0 未发布、1 已发布")
    private String state;

}
