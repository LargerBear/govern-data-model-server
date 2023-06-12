package com.freesofts.model.controller;

import com.freesofts.common.response.BizResponseData;
import com.freesofts.model.model.Page;
import com.freesofts.model.service.PageService;
import com.freesofts.model.vo.OptionsVO;
import com.freesofts.model.vo.PageQueryVO;
import com.freesofts.model.vo.PageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/page")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PageController {
    private final PageService pageService;

    /**
     * 页面查询
     *
     * @param pageNum
     * @param pageSize
     * @param pageQueryVO
     * @return
     */
    @GetMapping("/list/{pageSize}/{pageNum}")
    @PreAuthorize("hasAuthority('desktop:page:access')")
    public BizResponseData<?> list(@PathVariable int pageNum, @PathVariable int pageSize, PageQueryVO pageQueryVO) throws InvocationTargetException, IllegalAccessException {
        return pageService.list(pageNum, pageSize, pageQueryVO).getResponseData();
    }

    /**
     * 新建页面
     *
     * @param pageVO 页面VO
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('desktop:page:save')")
    public BizResponseData<?> create(
            @RequestBody @Validated PageVO pageVO
    ) {
        return pageService.insert(pageVO).nonNull();
    }

    /**
     * 复制
     *
     * @param pageId
     * @return
     */
    @PostMapping("/copyPage/{pageId}")
    @PreAuthorize("hasAuthority('desktop:page:copyId')")
    public BizResponseData<?> copyPage(
            @PathVariable String pageId
    ) {
        return pageService.copyPage(pageId).nonNull();
    }

    /**
     * 删除页面
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('desktop:page:delete')")
    public BizResponseData<?> deleteByIds(@RequestBody List<String> ids) {
        return pageService.deleteByIds(ids).getResponseData();
    }

    /**
     * 编辑属性
     *
     * @param pageVO
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('desktop:page:update')")
    public BizResponseData<?> updateById(@RequestBody @Validated(Page.WhenUpdate.class) PageVO pageVO) {
        return pageService.updateById(pageVO).getResponseData();
    }

    /**
     * 设计页面
     *
     * @param optionsVO
     * @return
     */
    @PutMapping("/{pageId}")
    @PreAuthorize("hasAuthority('desktop:page:design')")
    public BizResponseData<?> designById(
            @PathVariable String pageId,
            @RequestBody OptionsVO optionsVO
    ) {
        return pageService.updatePage(pageId, optionsVO).getResponseData();
    }

    /**
     * 页面详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('desktop:page:access')")
    public BizResponseData<?> getById(@PathVariable String id) {
        return pageService.getById(id).nonNull();
    }

    /**
     * 预览
     *
     * @param id
     * @return
     */
    @GetMapping("/getOptions/{id}")
    @PreAuthorize("hasAuthority('desktop:page:access')")
    public BizResponseData<?> getOptions(@PathVariable String id) {
        return pageService.getOptions(id).nonNull();
    }

    /**
     * 锁定页面
     *
     * @param id       页面ID
     * @param lock     锁定状态
     * @param password 密码
     * @return 锁定状态
     */
    @PostMapping("/lock/{id}/{lock}/{password}")
//    @PreAuthorize("hasAuthority('desktop:page:lock')")
    public BizResponseData<?> lock(@PathVariable String id, @PathVariable int lock, @PathVariable String password) {
        return pageService.lock(id, lock, password).beEqualTo(1);
    }
}
