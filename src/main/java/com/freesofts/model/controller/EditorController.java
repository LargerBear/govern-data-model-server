package com.freesofts.model.controller;

import com.freesofts.common.response.BizResponseData;
import com.freesofts.model.model.Editor;
import com.freesofts.model.service.EditorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhouwei
 */
@RestController
@RequestMapping("/editor")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EditorController {

    private final EditorService editorService;

    @ApiOperation(value = "构造器列表展示")
    @PreAuthorize("hasAuthority('desktop:page:access')")
    @GetMapping("/list/{pageSize}/{pageNum}")
    public BizResponseData<?> list(@ApiParam("起始页") @PathVariable int pageNum,
                                   @ApiParam("页数据量") @PathVariable int pageSize,
                                   @ApiParam("查询条件") Editor editor) {

        return editorService.list(pageNum, pageSize, editor).getResponseData();
    }

    @ApiOperation(value = "构造器列表展示不分页")
    @PreAuthorize("hasAuthority('desktop:page:access')")
    @GetMapping("/list/no-page")
    public BizResponseData<?> list(@ApiParam("查询条件") Editor editor) {

        return editorService.listNoPage(editor).isNotNull();
    }

    @ApiOperation(value = "新增编辑器")
    @PreAuthorize("hasAuthority('desktop:page:save')")
    @PostMapping
    public BizResponseData<?> create(@ApiParam("新增参数") @RequestBody @Validated Editor editor) {

        return editorService.insert(editor).beEqualTo(1);
    }

    @ApiOperation(value = "批量删除编辑器")
    @PreAuthorize("hasAuthority('desktop:page:delete')")
    @DeleteMapping
    public BizResponseData<?> deleteByIds(@ApiParam("id集合") @RequestBody List<String> ids) {

        return editorService.deleteByIds(ids).greaterThan(0);
    }

    @ApiOperation(value = "修改编辑器")
    @PreAuthorize("hasAuthority('desktop:page:update')")
    @PutMapping
    public BizResponseData<?> updateById(
            @ApiParam("编辑参数") @RequestBody @Validated(Editor.WhenUpdate.class) Editor editor
    )  {

        return editorService.updateById(editor).getResponseData();
    }

    @ApiOperation(value = "根据id查询详情")
    @PreAuthorize("hasAuthority('desktop:page:access')")
    @GetMapping("/{id}")
    public BizResponseData<?> getById(@ApiParam("编辑器id")@PathVariable String id) {

        return editorService.getById(id).nonNull();
    }

}
