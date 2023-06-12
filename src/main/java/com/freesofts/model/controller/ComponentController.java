package com.freesofts.model.controller;

import com.freesofts.common.response.BizResponseData;
import com.freesofts.model.model.Component;
import com.freesofts.model.service.ComponentService;
import com.freesofts.model.vo.ComponentQueryVO;
import com.freesofts.model.vo.ComponentVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/component")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComponentController {

    private final ComponentService componentService;

    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @param componentQueryVO
     * @return
     */
    @GetMapping("/list/{pageSize}/{pageNum}")
    @PreAuthorize("hasAuthority('desktop:component:access')")
    public BizResponseData<?> list(@PathVariable int pageNum, @PathVariable int pageSize, ComponentQueryVO componentQueryVO) {

        return componentService.list(pageNum, pageSize, componentQueryVO).getResponseData();
    }

    /**
     * 部件新增
     *
     * @param categoryId
     * @param component
     * @return
     */
    @PostMapping("/{categoryId}")
    @PreAuthorize("hasAuthority('desktop:component:save')")
    public BizResponseData<?> create(
            @PathVariable String categoryId,
            @RequestBody @Validated Component component
    ) {

        return componentService.insert(categoryId, component).nonNull();
    }

    /**
     * 复制
     *
     * @param componentId
     * @return
     */
    @PostMapping("/copyComponent/{componentId}")
    @PreAuthorize("hasAuthority('desktop:component:copyId')")
    public BizResponseData<?> copyPage(
            @PathVariable String componentId
    ) {

        return componentService.copyComponent(componentId).nonNull();
    }

    /**
     * 部件删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('desktop:component:delete')")
    public BizResponseData<?> deleteByIds(@RequestBody List<String> ids) {

        return componentService.deleteByIds(ids).greaterThan(0);
    }

    /**
     * 编辑属性
     *
     * @param component
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('desktop:component:update')")
    public BizResponseData<?> updateById(
            @RequestBody @Validated(Component.WhenUpdate.class) Component component
    ) {

        return componentService.updateById(component).getResponseData();
    }

    /**
     * 修改构建内容
     *
     * @param componentId
     * @param component
     * @return
     */
    @PutMapping("/{componentId}")
    @PreAuthorize("hasAuthority('desktop:component:desgin')")
    public BizResponseData<?> designById(
            @PathVariable String componentId,
            @ApiParam("状态") @RequestBody Component component
    ) {

        return componentService.updateOptions(componentId, component.getOptions()).getResponseData();
    }

    /**
     * 发布
     *
     * @param id
     * @return
     */
    @PutMapping("/releaseById/{id}")
    @PreAuthorize("hasAuthority('desktop:component:release')")
    public BizResponseData<?> releaseById(
            @PathVariable String id,
            @ApiParam("状态") @RequestBody Component component
    ) {

        return componentService.releaseById(id, component.getState()).getResponseData();
    }

    /**
     * 部件详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('desktop:component:access')")
    public BizResponseData<?> getById(@PathVariable String id) {

        return componentService.getById(id).nonNull();
    }

    /**
     * 预览
     *
     * @param id
     * @return
     */
    @GetMapping("/getOptions/{id}")
    @PreAuthorize("hasAuthority('desktop:component:access')")
    public BizResponseData<?> getOptions(@PathVariable String id) {

        return componentService.getOptions(id).nonNull();
    }


    @ApiOperation("修改部件静态数据及图片地址")
    @PostMapping("/update/static-data/{id}")
    public BizResponseData<?> updateStaticData(@PathVariable("id") String id,
                                               @RequestBody ComponentVO componentVO) {
        return componentService.updateStaticData(id, componentVO).beEqualTo(1);
    }

}
