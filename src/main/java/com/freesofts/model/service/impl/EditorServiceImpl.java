package com.freesofts.model.service.impl;

import com.freesofts.common.response.result.impl.BoolBizResult;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ListBizResult;
import com.freesofts.common.utils.UUIdGenId;
import com.freesofts.model.mapper.EditorMapper;
import com.freesofts.model.model.Editor;
import com.freesofts.model.service.EditorService;
import com.freesofts.threader.AppUserManager;
import com.freesofts.threader.LoginAppUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zhouwei
 */
@Service
public class EditorServiceImpl implements EditorService {
    @Resource
    private EditorMapper editorMapper;

    @Override
    public DatagridBizResult<Editor> list(int pageNum, int pageSize, Editor editor) {
        List<Editor> editors = editorMapper.selectPageList(editor, (pageNum - 1) * pageSize, pageSize);
        long num = editorMapper.pageListNum(editor);
        return DatagridBizResult.<Editor>builder().list(editors).total(num).build();
    }

    @Override
    public IntegerBizResult insert(Editor editor) {
        // 获取登录信息
        LoginAppUser user = AppUserManager.getLoginAppUser();
        editor.setId(UUIdGenId.genId())
                .setCreatedBy(user.getId())
                .setCreatorName(user.getName())
                .setCreatedDate(new Date());
        return IntegerBizResult.builder().value(editorMapper.insert(editor)).build();
    }

    @Override
    public IntegerBizResult deleteByIds(List<String> ids) {
        return IntegerBizResult.builder().value(editorMapper.deleteByIds(ids)).build();
    }

    @Override
    public BoolBizResult<Object> updateById(Editor editor) {
        // 获取登录信息
        LoginAppUser user = AppUserManager.getLoginAppUser();
        editor.setLastModifiedBy(user.getId())
                .setLastModifiedName(user.getName())
                .setLastModifiedDate(new Date());
        int update = editorMapper.updateById(editor);
        if (update == 1) {
            return BoolBizResult.builder().bool(true).message("修改成功").value(editor).build();
        }
        return BoolBizResult.builder().message("修改失败").build();
    }

    @Override
    public BoolBizResult<Object> updateEditor(Editor editor) {
        // TODO 编辑器自定义
        return null;
    }

    @Override
    public GenericBizResult<Editor> getById(String id) {
        return GenericBizResult.<Editor>builder().t(editorMapper.selectById(id)).build();
    }

    @Override
    public ListBizResult<Editor> listNoPage(Editor editor) {
       return ListBizResult.<Editor>builder().list(editorMapper.selectEditorListNoPage(editor)).build();
    }
}
