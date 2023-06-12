package com.freesofts.model.service;

import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.plugins.TreeBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ListBizResult;
import com.freesofts.common.response.result.types.ObjectBizResult;
import com.freesofts.model.model.Category;
import com.freesofts.model.vo.QueryVO;

import java.util.List;
import java.util.Map;

/**
 * @author zhouwei
 */
public interface PageCategoryService {
    /**
     * 页面分类列表展示
     */
    ListBizResult<Category> categoryList();

    /**
     * 新增分类
     */
    ObjectBizResult insertPageCategory(Category category);

    /**
     * 分类树展示
     *
     * @param pid
     * @return 分类树
     */
    TreeBizResult selectCategoryTree(String pid);

    /**
     * 分页列表
     */
    DatagridBizResult<Category> list(String id, int pageSize, int pageNum, QueryVO queryVO);

    DatagridBizResult<Map<String, Object>> getPageListByKeyword(int pageNum, int pageSize, String keyword, String pid);


    ObjectBizResult updateById(Category category);

    /**
     * 查询详情
     */
    GenericBizResult<Category> getById(String id);

    ObjectBizResult listParents(String id);


    IntegerBizResult deleteByIds(List<String> ids);
}
