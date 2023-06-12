package com.freesofts.model.service;

import cn.hutool.core.lang.tree.Tree;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ListBizResult;
import com.freesofts.model.model.LabelPage;
import com.freesofts.model.vo.QueryVO;

import java.util.List;

/**
 * @author zhouwei
 */
public interface PageLabelService {
    /**
     * 页面标签列表展示
     */
    ListBizResult<LabelPage> labelList(String categoryId);

    /**
     * 新建页面标签管理
     */
//    IntegerBizResult insert(LabelManage labelManage);

    /**
     * 目录树
     */
    ListBizResult<Tree<String>> labelTree();

    /**
     * 根据分类id查询标签分页
     */
    DatagridBizResult<LabelPage> list( int pageNum,int pageSize, QueryVO queryVO);


    IntegerBizResult save(LabelPage labelPage);

    IntegerBizResult deleteByIds(List<String> ids);

    IntegerBizResult updateById(LabelPage labelPage);

    /**
     * 查询详情
     */
    GenericBizResult<LabelPage> getById(String id);

    /**
     * 根据父节点查询所有子节点
     * @param id
     * @return
     */
    ListBizResult<LabelPage> labelSons(String id);
}
