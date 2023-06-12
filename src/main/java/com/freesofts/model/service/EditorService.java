package com.freesofts.model.service;

import com.freesofts.common.response.result.impl.BoolBizResult;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ListBizResult;
import com.freesofts.model.model.Editor;

import java.util.List;

public interface EditorService {

    DatagridBizResult<Editor> list(int pageNum, int pageSize, Editor editor);

    IntegerBizResult insert(Editor editor);

    IntegerBizResult deleteByIds(List<String> ids);

    BoolBizResult<Object> updateById(Editor editor);

    BoolBizResult<Object> updateEditor(Editor editor);

    GenericBizResult<Editor> getById(String id);

    ListBizResult<Editor> listNoPage(Editor editor);
}
