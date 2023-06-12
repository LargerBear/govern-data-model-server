package com.freesofts.model.controller;

import com.freesofts.common.response.BizResponseData;
import com.freesofts.model.model.LabelPage;
import com.freesofts.model.service.PageLabelService;
import com.freesofts.model.vo.QueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 页面标签管理
 *
 * @Author: 周伟
 * @CreateTime: 2023-02-20  10:31
 * @Version: 1.0
 */
@Api("页面标签管理")
@RestController
@RequestMapping("/page/label")
public class PageLabelController {
    @Resource
    private PageLabelService pageLabelService;

    @ApiOperation(value = "页面标签分页展示")
    @PreAuthorize("hasAuthority('desktop:label:access')")
    @GetMapping("/list/{pageSize}/{pageNum}")
    public BizResponseData<?> list(@ApiParam("起始页") @PathVariable int pageNum,
                                   @ApiParam("页数据量") @PathVariable int pageSize,
                                   @ApiParam("查询条件") QueryVO queryVO) {
        return pageLabelService.list(pageNum, pageSize, queryVO).getResponseData();
    }

    @ApiOperation(value = "页面标签列表展示")
    @PreAuthorize("hasAuthority('desktop:label:access')")
    @GetMapping("/list/{categoryId}")
    public BizResponseData<?> labelList(@PathVariable("categoryId") String categoryId) {
        return pageLabelService.labelList(categoryId).isNotNull();
    }

    @ApiOperation(value = "新增页面标签")
    @PreAuthorize("hasAuthority('desktop:label:save')")
    @PostMapping
    public BizResponseData<?> create(@ApiParam("新增参数") @RequestBody @Validated LabelPage labelPage) {
        return pageLabelService.save(labelPage).beEqualTo(1);
    }

    @ApiOperation(value = "批量删除页面标签")
    @PreAuthorize("hasAuthority('desktop:label:delete')")
    @DeleteMapping
    public BizResponseData<?> deleteByIds(@ApiParam("id集合") @RequestBody @NotNull List<String> ids) {
        return pageLabelService.deleteByIds(ids).greaterThan(0);
    }

    @ApiOperation(value = "修改页面标签")
    @PreAuthorize("hasAuthority('desktop:label:update')")
    @PutMapping
    public BizResponseData<?> updateById(
            @ApiParam("编辑参数") @RequestBody @Validated LabelPage labelPage) {
        return pageLabelService.updateById(labelPage).beEqualTo(1);
    }

    @ApiOperation(value = "根据id查询详情")
    @PreAuthorize("hasAuthority('desktop:label:access')")
    @GetMapping("/{id}")
    public BizResponseData<?> getById(@ApiParam("编辑器id") @PathVariable String id) {
        return pageLabelService.getById(id).nonNull();
    }

    @ApiOperation(value = "根据id查询下列所有标签")
    @PreAuthorize("hasAuthority('desktop:label:access')")
    @GetMapping("/label-sons/{id}")
    public BizResponseData<?> getLabelSons(@ApiParam("标签父id") @PathVariable String id) {
        return pageLabelService.labelSons(id).isNotNull();
    }
}
