package com.freesofts.model.service.impl;

import com.freesofts.common.response.result.impl.BoolBizResult;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ObjectBizResult;
import com.freesofts.model.mapper.ComponentMapper;
import com.freesofts.model.model.Component;
import com.freesofts.model.service.ComponentService;
import com.freesofts.model.vo.ComponentQueryVO;
import com.freesofts.model.vo.ComponentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ComponentServiceImpl implements ComponentService {
    @Resource
    private ComponentMapper componentMapper;


    @Override
    public DatagridBizResult<ComponentVO> list(int pageNum, int pageSize, ComponentQueryVO componentQueryVO) {
        return null;
    }

    @Override
    public ObjectBizResult insert(String categoryId, Component component) {
        return null;
    }

    @Override
    public ObjectBizResult copyComponent(String componentId) {
        return null;
    }

    @Override
    public IntegerBizResult deleteByIds(List<String> ids) {
        return null;
    }

    @Override
    public BoolBizResult<Object> updateById(Component component) {
        return null;
    }

    @Override
    public BoolBizResult<Object> updateOptions(String componentId, String data) {
        return null;
    }

    @Override
    public BoolBizResult<Object> releaseById(String id, String state) {
        return null;
    }

    @Override
    public GenericBizResult<ComponentVO> getById(String id) {
        return null;
    }

    @Override
    public GenericBizResult<String> getOptions(String id) {
        return null;
    }

    @Override
    public IntegerBizResult updateStaticData(String id, ComponentVO componentVO) {
        return null;
    }
}
