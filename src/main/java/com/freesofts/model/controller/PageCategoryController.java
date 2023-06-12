package com.freesofts.model.controller;

import com.freesofts.common.response.BizResponseData;
import com.freesofts.model.model.Category;
import com.freesofts.model.service.PageCategoryService;
import com.freesofts.model.vo.QueryVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 分类管理
 *
 *@Author: 周伟
 *@CreateTime: 2023-02-20  10:32
 *@Version: 1.0
 */
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class PageCategoryController {

    private final PageCategoryService pageCategoryService;

    @ApiOperation(value = "页面分类列表展示")
    @PreAuthorize("hasAuthority('desktop:label:access')")
    @GetMapping("/list")
    public BizResponseData<?> labelList(){
        return pageCategoryService.categoryList().isNotNull();
    }

    @ApiOperation(value = "新增页面分类")
    @PreAuthorize("hasAuthority('desktop:label:save')")
    @PostMapping
    public BizResponseData<?> insertPageCategory(@RequestBody @ApiParam("分类表") Category category){
        return pageCategoryService.insertPageCategory(category).nonNull();
    }

    @ApiOperation(value = "分类树展示")
    @PreAuthorize("hasAuthority('desktop:label:access')")
    @GetMapping("/tree")
    public BizResponseData<?> categoryTree(@ApiParam("父id、可以为空") @RequestParam("pid") String pid) {
        return pageCategoryService.selectCategoryTree(pid).getResponseData();
    }

    @ApiOperation(value = "分类分页列表展示")
    @PreAuthorize("hasAuthority('desktop:label:access')")
    @GetMapping("/list/{id}/{pageSize}/{pageNum}")
    public BizResponseData<?> categoryList(@PathVariable("id") String id,
                                           @PathVariable("pageSize") int pageSize,
                                           @PathVariable("pageNum") int pageNum,
                                           QueryVO queryVO){

        return pageCategoryService.list(id,pageSize,pageNum,queryVO).getResponseData();
    }

    @ApiOperation(value = "目录树查询")
    @PreAuthorize("hasAuthority('desktop:label:access')")
    @GetMapping("/keyword/list/{pageSize}/{pageNum}")
    public BizResponseData<?> getPageListByKeyword(
            @PathVariable int pageNum,
            @PathVariable int pageSize,
            @RequestParam @NotBlank String keyword,
            @RequestParam String pid
    ) {

        return pageCategoryService.getPageListByKeyword(pageNum, pageSize, keyword,pid).getResponseData();
    }


    @ApiOperation(value = "查询详情")
    @PreAuthorize("hasAuthority('desktop:label:access')")
    @GetMapping("/{id}")
    public BizResponseData<?> detail(@PathVariable String id) {
        return pageCategoryService.getById(id).nonNull();
    }

    @ApiOperation(value = "修改分类")
    @PreAuthorize("hasAuthority('desktop:label:update')")
    @PutMapping
    public BizResponseData<?> updateById(
            @ApiParam("编辑参数") @RequestBody @Validated Category category
    )  {
        return pageCategoryService.updateById(category).nonNull();
    }

    @ApiOperation(value = "根据id查询所有父集")
    @PreAuthorize("hasAuthority('desktop:label:access')")
    @GetMapping("/list-parents/{id}")
    public BizResponseData<?> listParents(@PathVariable("id") String id) {
        return pageCategoryService.listParents(id).nonNull();
    }

    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('desktop:label:delete')")
    @DeleteMapping("/delete")
    public BizResponseData<?> deleteByIds(@RequestBody List<String> ids) {
        return pageCategoryService.deleteByIds(ids).greaterThanAndEquals(1);
    }

}
