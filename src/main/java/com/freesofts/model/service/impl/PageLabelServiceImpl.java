package com.freesofts.model.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ListBizResult;
import com.freesofts.common.utils.UUIdGenId;
import com.freesofts.model.mapper.PageLabelMapper;
import com.freesofts.model.model.LabelPage;
import com.freesofts.model.service.PageLabelService;
import com.freesofts.model.vo.QueryVO;
import com.freesofts.threader.AppUserManager;
import com.freesofts.threader.LoginAppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 周伟
 * @CreateTime: 2023-02-20  11:01
 * @Version: 1.0
 */
@Slf4j
@Service
public class PageLabelServiceImpl implements PageLabelService {
    @Resource
    private PageLabelMapper pageLabelMapper;

    /**
     * 页面标签列表展示
     */
    @Override
    public ListBizResult<LabelPage> labelList(String categoryId) {
        return ListBizResult.<LabelPage>builder().list(pageLabelMapper.selectPageLabelList(categoryId)).build();
    }

    /**
     * 目录树
     */
    @Override
    public ListBizResult<Tree<String>> labelTree() {
        List<Tree<String>> treeNodes = TreeUtil.build(pageLabelMapper.selectAll(), "root", null,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setWeight(treeNode.getLevel());
                    tree.setName(treeNode.getName());
                });
        return ListBizResult.<Tree<String>>builder().list(treeNodes).build();
    }

    @Override
    public DatagridBizResult<LabelPage> list(int pageNum, int pageSize, QueryVO queryVO) {
        long count = pageLabelMapper.selectCount(queryVO.getCategoryId());
        List<LabelPage> list = pageLabelMapper.selectListByCategoryId((pageNum - 1) * pageSize, pageSize, queryVO);
        return DatagridBizResult.<LabelPage>builder().total(count).list(list).build();
    }

    @Override
    public IntegerBizResult save(LabelPage labelPage) {
        //设置唯一编码
        String maxUniqueCode = pageLabelMapper.findMaxUniqueCode();
        Integer uniqueCode = Integer.parseInt(maxUniqueCode) + 1;
        String code = String.format("%06d", uniqueCode);
        //唯一性校验
        LabelPage label = pageLabelMapper.selectByUniqueCode(code);
        Optional.ofNullable(label).orElseGet(() -> labelPage.setUniqueCode(code));
        //初始化创建人信息
        LoginAppUser user = AppUserManager.getLoginAppUser();
        labelPage.setId(UUIdGenId.genId())
                .setCreatorName(user.getName())
                .setCreatedBy(user.getId())
                .setCreatedDate(new Date());
        return IntegerBizResult.builder().value(pageLabelMapper.insertLabelPage(labelPage)).build();
    }

    @Override
    public IntegerBizResult deleteByIds(List<String> ids) {
        return IntegerBizResult.builder().value(pageLabelMapper.deleteByIds(ids)).build();
    }

    @Override
    public IntegerBizResult updateById(LabelPage labelPage) {
        LoginAppUser user = AppUserManager.getLoginAppUser();
        labelPage.setCreatorName(user.getName()).setCreatedDate(new Date()).setCreatedBy(user.getId());
        return IntegerBizResult.builder().value(pageLabelMapper.update(labelPage)).build();
    }

    @Override
    public GenericBizResult<LabelPage> getById(String id) {
        return GenericBizResult.<LabelPage>builder().t(pageLabelMapper.selectById(id)).build();
    }

    @Override
    public ListBizResult<LabelPage> labelSons(String id) {
        List<LabelPage> labelSons = pageLabelMapper.selectLabelSons(id);
        return ListBizResult.<LabelPage>builder().list(labelSons).build();
    }
}
