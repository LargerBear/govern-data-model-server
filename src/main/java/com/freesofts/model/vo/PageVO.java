package com.freesofts.model.vo;

import com.freesofts.model.model.LabelPage;
import com.freesofts.model.model.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: 周伟
 * @CreateTime: 2023-02-20  16:48
 * @Version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class PageVO extends Page {
    @ApiModelProperty("分类名称")
    private String categoryName;
    @ApiModelProperty("分类id")
    private String categoryId;
    @ApiModelProperty("添加的标签，包括新标签和旧标签")
    private String newTags;
    @ApiModelProperty("老标签")
    private List<LabelPage> oldTags;
}
