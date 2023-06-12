package com.freesofts.model.service.impl;

import com.freesofts.common.response.result.impl.BoolBizResult;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ObjectBizResult;
import com.freesofts.model.model.Panel;
import com.freesofts.model.service.PanelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanelServiceImpl implements PanelService {

    @Override
    public DatagridBizResult<Panel> list(int pageNum, int pageSize, Panel panel) {
        return null;
    }

    @Override
    public ObjectBizResult insert(String categoryId, Panel panel) {
        return null;
    }

    @Override
    public IntegerBizResult deleteByIds(List<String> ids) {
        return null;
    }

    @Override
    public BoolBizResult<Object> updateById(Panel panel) {
        return null;
    }

    @Override
    public BoolBizResult<Object> updatePanel(String panelId, String data) {
        return null;
    }

    @Override
    public GenericBizResult<Panel> getById(String id) {
        return null;
    }

}
