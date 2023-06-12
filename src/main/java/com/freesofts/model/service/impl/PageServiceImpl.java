package com.freesofts.model.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.freesofts.common.response.result.impl.BoolBizResult;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ObjectBizResult;
import com.freesofts.common.utils.UUIdGenId;
import com.freesofts.model.mapper.PageMapper;
import com.freesofts.model.model.LabelPage;
import com.freesofts.model.model.Page;
import com.freesofts.model.model.PageCategory;
import com.freesofts.model.model.PageLabel;
import com.freesofts.model.service.PageService;
import com.freesofts.model.vo.OptionsVO;
import com.freesofts.model.vo.PageQueryVO;
import com.freesofts.model.vo.PageVO;
import com.freesofts.threader.AppUserManager;
import com.freesofts.threader.LoginAppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author zhouwei
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final PageMapper pageMapper;

    @Override
    public DatagridBizResult<Page> list(int pageNum, int pageSize, PageQueryVO pageQueryVO) throws InvocationTargetException, IllegalAccessException {
        LoginAppUser user = AppUserManager.getLoginAppUser();
        pageQueryVO.setTenantId(user.getExFieldTwo());
        Long total = pageMapper.selectCount(pageQueryVO);
        List<Page> list = pageMapper.selectPageList((pageNum - 1) * pageSize, pageSize, pageQueryVO);
        // 查询标签
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(page -> {
                List<String> tags = pageMapper.selectTagsByPageId(Collections.singletonList(page.getId()));
                page.setTags(tags);
            });
        }
        return new DatagridBizResult<>(total, list);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ObjectBizResult insert(PageVO pageVO) {
        ObjectBizResult.ObjectBizResultBuilder builder = ObjectBizResult.builder();
        LoginAppUser user = AppUserManager.getLoginAppUser();
        pageVO.setId(UUIdGenId.genId());
        pageVO.setCreatedBy(user.getId());
        pageVO.setCreatorName(user.getName());
        // 复制参数
        Page page = new Page();
        BeanUtils.copyProperties(pageVO, page);
        // 添加租户信息
        page.setTenantId(user.getExFieldTwo());
        // 新增页面主表
        PageCategory pageCategory = new PageCategory();
        pageCategory.setId(UUIdGenId.genId());
        pageCategory.setCategoryId(pageVO.getCategoryId());
        pageCategory.setPageId(page.getId());
        int row = pageMapper.insert(page, pageCategory);
        // 插入标签,发生异常只回滚标签部分
        try {
            insertLabelPage(pageVO);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        }
        if (row == 0) {
            return builder.message("页面新增失败").object(null).build();
        }
        return builder.object(page.getId()).build();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ObjectBizResult copyPage(String pageId) {
        // 查看页面是否被锁定
        boolean lockPage = isLockPage(Collections.singletonList(pageId));
        if (!lockPage) {
            return ObjectBizResult.builder().message("页面已被锁定").build();
        }

        String id = UUIdGenId.genId();
        List<PageLabel> pageLabels = new ArrayList<>();
        LoginAppUser user = AppUserManager.getLoginAppUser();
        int row = pageMapper.insertCopy(id, pageId, user.getId(), user.getName());
        if (row > 0) {
            PageCategory pageCategory = new PageCategory();
            pageCategory.setId(UUIdGenId.genId());
            pageCategory.setCategoryId(pageMapper.selectCategoryId(pageId));
            pageCategory.setPageId(id);
            row = pageMapper.insertCategory(pageCategory);
            PageVO pageVO = new PageVO();
            pageVO.setId(id);
            List<String> tagsId = pageMapper.selectTagsIdsByPageId(Collections.singletonList(pageId));
            tagsId.forEach(tagId -> {
                PageLabel pageLabel = new PageLabel().setLabelId(tagId).setPageId(id).setId(UUIdGenId.genId());
                pageLabels.add(pageLabel);
            });
            pageMapper.insertPageLabel(pageLabels);
        }
        if (row > 0) {
            return ObjectBizResult.builder().object(id).build();
        }
        return ObjectBizResult.builder().message("复制页面失败").object(null).build();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public BoolBizResult deleteByIds(List<String> ids) {
        // 查看页面是否被锁定
        boolean lockPage = isLockPage(ids);
        if (!lockPage) {
            return BoolBizResult.builder().message("页面已被锁定").build();
        }

        if (ids == null || ids.size() == 0) {
            BoolBizResult.builder().message("请选择数据！").build();
        }
        // 删除主表和分类表
        try {
            pageMapper.deleteByIds(ids, ids);
            // 删除标签
            deleteLabelPage(ids);
            return BoolBizResult.builder().bool(true).build();
        } catch (RuntimeException e) {
            return BoolBizResult.builder().build();
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public BoolBizResult<Object> updateById(PageVO pageVO) {
        // 查看页面是否被锁定
        boolean lockPage = isLockPage(Collections.singletonList(pageVO.getId()));
        if (!lockPage) {
            return BoolBizResult.builder().message("页面已被锁定").build();
        }
        BoolBizResult.BoolBizResultBuilder<Object> builder = BoolBizResult.builder();
        LoginAppUser user = AppUserManager.getLoginAppUser();
        Page page = new Page();
        BeanUtils.copyProperties(pageVO, page);
        page.setLastModifiedBy(user.getId());
        page.setLastModifiedName(user.getName());
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        page.setLastModifiedDate(sim.format(new Date()));
        int result = pageMapper.update(page);
        if (result == 1) {
            // 修改分类
            PageCategory pageCategory = new PageCategory();
            // 根据页面id修改中间表
            pageCategory.setCategoryId(pageVO.getCategoryId());
            pageCategory.setPageId(pageVO.getId());
            // 修改标签和分类，发生异常不回滚其他
            try {
                pageMapper.updateCategoryPage(pageCategory);
                updateLagePage(pageVO);
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
            builder.bool(true).value(pageVO.getId());
        } else {
            builder.message("更新失败");
        }
        return builder.build();
    }

    @Override
    public BoolBizResult<Object> updatePage(String pageId, OptionsVO optionsVO) {
        // 查看页面是否被锁定
        boolean lockPage = isLockPage(Collections.singletonList(pageId));
        if (!lockPage) {
            return BoolBizResult.builder().message("页面已被锁定").build();
        }

        BoolBizResult.BoolBizResultBuilder<Object> builder = BoolBizResult.builder();
        String data = optionsVO.getOptions();
        String filePath = optionsVO.getFilePath();
        int result = pageMapper.updateOptions(pageId, data, filePath);
        if (result == 1) {
            return builder.bool(true).message("更新成功").build();
        }
        return builder.message("更新失败").build();
    }

    @Override
    public GenericBizResult<PageVO> getById(String id) {
        Page page = pageMapper.selectById(id);
        PageVO pageVO = new PageVO();
        BeanUtils.copyProperties(page, pageVO);
        // 根据页面id查询分类id
        // 添加标签
        String cId = pageMapper.selectCategoryId(id);
        String categoryName = pageMapper.selectCategoryName(cId);
        pageVO.setCategoryId(cId);
        pageVO.setCategoryName(categoryName);
        pageVO.setOldTags(selectTags(id));
        return GenericBizResult.<PageVO>builder().t(pageVO).build();
    }

    @Override
    public GenericBizResult<String> getOptions(String id) {
        return GenericBizResult.<String>builder().t(pageMapper.selectOptions(id)).build();
    }

    @Override
    public IntegerBizResult lock(String id, int lock, String password) {
        IntegerBizResult.IntegerBizResultBuilder builder = IntegerBizResult.builder();
        // 查询页面是否存在
        Page page = pageMapper.selectById(id);
        if (page == null) {
            return builder.message("页面不存在").build();
        }
        // 加锁
        if (lock == 0 && page.getLock() == 0) {
            int res = pageMapper.updateLock(id, lock, password);
            return builder.value(res).build();
        }
        // 解锁
        if (page.getLock() == 1 && lock == 1) {
            // 加密
            if (page.getPassword().equals(password)) {
                int res = pageMapper.updateLock(id, lock, password);
                return builder.value(res).build();
            } else {
                return builder.message("密码错误").build();
            }
        }
        return builder.message("操作失败").build();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void insertLabelPage(PageVO pageVO) throws RuntimeException {
        // 获取登录信息
        LoginAppUser user = AppUserManager.getLoginAppUser();
        List<LabelPage> labelPages = new ArrayList<>();
        List<PageLabel> pageLabels = new ArrayList<>();
        // 转为json
        JSONObject tagsJson = JSONObject.parseObject(pageVO.getNewTags());
        // 转为数组
        List<String> strings = (List<String>) tagsJson.get("new");
        for (String s : strings) {
            LabelPage labelPage = new LabelPage()
                    .setId(UUIdGenId.genId())
                    .setCreatedBy(user.getId())
                    .setCreatorName(user.getName())
                    .setCreatedDate(new Date())
                    .setName(s)
                    .setCategoryId(pageVO.getCategoryId());
            labelPages.add(labelPage);
            // 存为中间表
            PageLabel pageLabel = new PageLabel().setPageId(pageVO.getId()).setId(UUIdGenId.genId()).setLabelId(labelPage.getId());
            pageLabels.add(pageLabel);
        }
        // 已存在标签转为数组
        List<JSONObject> oldTags = (List<JSONObject>) tagsJson.get("old");
        oldTags.forEach(tag -> {
            PageLabel pageLabel = new PageLabel().setPageId(pageVO.getId()).setId(UUIdGenId.genId()).setLabelId(tag.getString("id"));
            pageLabels.add(pageLabel);
        });
        //插入标签表
        if (!CollectionUtils.isEmpty(labelPages)) {
            pageMapper.insertLabelPages(labelPages);
        }
        if (!CollectionUtils.isEmpty(pageLabels)) {
            pageMapper.insertPageLabel(pageLabels);
        }
    }

    /**
     * 删除标签
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteLabelPage(List<String> pageIds) {
        // 根据pageIds查询标签id集合
        List<String> ids = pageMapper.selectTagsIdsByPageId(pageIds);
        if (!CollectionUtils.isEmpty(ids)) {
            pageMapper.deletePageLabelByPageIds(pageIds);
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void updateLagePage(PageVO pageVO) throws RuntimeException {
        // 这个List只有一个值
        List<String> pageId = Collections.singletonList(pageVO.getId());
        if (!CollectionUtils.isEmpty(pageId)) {
            deleteLabelPage(pageId);
            insertLabelPage(pageVO);
        }
    }

    /**
     * 查询标签
     */
    public List<LabelPage> selectTags(String pageId) {
        return pageMapper.selectTagsIdAndNameByPageId(pageId);
    }

    /**
     *  判断页面是否被锁定
     * @param ids 页面id集合
     * @return  true:未锁定 false:锁定
     */
    public boolean isLockPage(List<String> ids) {
        // 查询页面是否被锁定
        List<Page> pages = pageMapper.selectByIds(ids);
        if (!CollectionUtils.isEmpty(pages)) {
            List<String> lockPages = new ArrayList<>();
            pages.forEach(page -> {
                if (page.getLock() == 1) {
                    lockPages.add(page.getName());
                }
            });
            return lockPages.size() <= 0;
        }
        return true;
    }
}
