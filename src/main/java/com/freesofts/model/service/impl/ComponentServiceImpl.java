package com.freesofts.model.service.impl;

import com.freesofts.common.response.result.impl.BoolBizResult;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ObjectBizResult;
import com.freesofts.common.utils.UUIdGenId;
import com.freesofts.model.mapper.ComponentMapper;
import com.freesofts.model.model.Component;
import com.freesofts.model.model.ComponentCategory;
import com.freesofts.model.service.ComponentService;
import com.freesofts.model.vo.ComponentQueryVO;
import com.freesofts.model.vo.ComponentVO;
import com.freesofts.threader.AppUserManager;
import com.freesofts.threader.LoginAppUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ComponentServiceImpl implements ComponentService {
    @Resource
    private ComponentMapper componentMapper;

    @Override
    public DatagridBizResult<ComponentVO> list(int pageNum, int pageSize, ComponentQueryVO componentQueryVO) {
        Long total = componentMapper.selectCount(componentQueryVO);
        List<ComponentVO> list = componentMapper.selectComponentList((pageNum - 1) * pageSize, pageSize, componentQueryVO);
        return new DatagridBizResult<>(total, list);
    }

    @Override
    public ObjectBizResult insert(String categoryId, Component component) {
        LoginAppUser user = AppUserManager.getLoginAppUser();
        component.setId(UUIdGenId.genId());
        component.setCreatedBy(user.getId());
        component.setCreatorName(user.getName());
        //新增主表
        int row = componentMapper.insert(component);
        //新增分类中间表
        if (row > 0) {
            ComponentCategory componentCategory = new ComponentCategory();
            componentCategory.setId(UUIdGenId.genId());
            componentCategory.setCategoryId(categoryId);
            componentCategory.setComponentId(component.getId());
            row = componentMapper.insertCategory(componentCategory);
        }
        if (row > 0) {
            return ObjectBizResult.builder().object(component.getId()).build();
        } else {
            return ObjectBizResult.builder().object(null).build();
        }
    }

    @Override
    public ObjectBizResult copyComponent(String componentId) {
        String id = UUIdGenId.genId();
        LoginAppUser user = AppUserManager.getLoginAppUser();
        String name = componentMapper.selectName(componentId);
        if (!name.contains("复制") && !name.isEmpty()) {
            name = name + "-复制";
        }
        int row = componentMapper.insertCopy(id, componentId, user.getId(), user.getName(), name);
        if (row > 0) {
            ComponentCategory pageCategory = new ComponentCategory();
            pageCategory.setId(UUIdGenId.genId());
            pageCategory.setCategoryId(componentMapper.selectCategoryId(componentId));
            pageCategory.setComponentId(id);
            row = componentMapper.insertCategory(pageCategory);
        }
        if (row > 0) {
            return ObjectBizResult.builder().object(id).build();
        } else {
            return ObjectBizResult.builder().object(null).build();
        }
    }

    @Override
    public IntegerBizResult deleteByIds(List<String> ids) {
        if (ids == null || ids.size() == 0) {
            IntegerBizResult.builder().message("请选择数据！").build();
        }
        //删除主表
        int row = componentMapper.deleteByIds(ids);
        return IntegerBizResult.builder().value(row).build();
    }

    @Override
    public BoolBizResult<Object> updateById(Component component) {
        BoolBizResult.BoolBizResultBuilder<Object> builder = BoolBizResult.builder();
        LoginAppUser user = AppUserManager.getLoginAppUser();
        component.setLastModifiedBy(user.getId());
        component.setLastModifiedName(user.getName());
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        component.setLastModifiedDate(sim.format(new Date()));
        int result = componentMapper.update(component);
        if (result == 1) {
            builder.bool(true);
        } else {
            builder.bool(false).message("更新失败");
        }
        return builder.build();
    }

    @Override
    public BoolBizResult<Object> updateOptions(String componentId, String data) {
        BoolBizResult.BoolBizResultBuilder<Object> builder = BoolBizResult.builder();
        int result = componentMapper.updateOptions(componentId, data);
        if (result == 1) {
            builder.bool(true);
        } else {
            builder.bool(false).message("更新失败");
        }
        return builder.build();
    }

    @Override
    public BoolBizResult<Object> releaseById(String id, String state) {
        BoolBizResult.BoolBizResultBuilder<Object> builder = BoolBizResult.builder();
        int result = componentMapper.updateState(id, state);
        if (result == 1) {
            builder.bool(true);
        } else {
            builder.bool(false).message("更新失败");
        }
        return builder.build();
    }

    @Override
    public GenericBizResult<ComponentVO> getById(String id) {
        return GenericBizResult.<ComponentVO>builder().t(componentMapper.selectById(id)).build();
    }

    @Override
    public GenericBizResult<String> getOptions(String id) {
        return GenericBizResult.<String>builder().t(componentMapper.selectOptions(id)).build();
    }

    @Override
    public IntegerBizResult updateStaticData(String id, ComponentVO componentVO) {
        return IntegerBizResult.builder().value(componentMapper.updateStaticData(id, componentVO)).build();
    }
}
