package com.freesofts.model.service;

import com.freesofts.common.response.result.impl.BoolBizResult;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ObjectBizResult;
import com.freesofts.model.model.Panel;

import java.util.List;

public interface PanelService {

    DatagridBizResult<Panel> list(int pageNum, int pageSize, Panel panel);

    ObjectBizResult insert(String categoryId, Panel panel);

    IntegerBizResult deleteByIds(List<String> ids);

    BoolBizResult<Object> updateById(Panel panel);

    BoolBizResult<Object> updatePanel(String panelId, String data);

    GenericBizResult<Panel> getById(String id);
    
}
