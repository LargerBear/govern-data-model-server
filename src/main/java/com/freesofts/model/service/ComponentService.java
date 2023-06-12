package com.freesofts.model.service;

import com.freesofts.common.response.result.impl.BoolBizResult;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ObjectBizResult;
import com.freesofts.model.model.Component;
import com.freesofts.model.vo.ComponentQueryVO;
import com.freesofts.model.vo.ComponentVO;

import java.util.List;

public interface ComponentService {

    /**
     * 查询
     *
     * @param pageNum          数据页码
     * @param pageSize         每页记录数
     * @param componentQueryVO 查询条件
     * @return
     */
    DatagridBizResult<ComponentVO> list(int pageNum, int pageSize, ComponentQueryVO componentQueryVO);

    /**
     * 创建
     *
     * @param categoryId 分类id
     * @param component  部件数据
     * @return
     */
    ObjectBizResult insert(String categoryId, Component component);

    /**
     * 复制部件
     *
     * @param componentId 部件id
     * @return
     */
    ObjectBizResult copyComponent(String componentId);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    IntegerBizResult deleteByIds(List<String> ids);

    /**
     * 修改属性
     *
     * @param component 部件
     * @return
     */
    BoolBizResult<Object> updateById(Component component);

    /**
     * 修改构建内容
     *
     * @param componentId 部件id
     * @param data        配置
     * @return
     */
    BoolBizResult<Object> updateOptions(String componentId, String data);

    /**
     * 发布
     *
     * @param id 数据id
     * @return
     */
    BoolBizResult<Object> releaseById(String id, String state);

    /**
     * 详情
     *
     * @param id 数据id
     * @return
     */
    GenericBizResult<ComponentVO> getById(String id);

    /**
     * 获取配置选项
     *
     * @param id 数据id
     * @return
     */
    GenericBizResult<String> getOptions(String id);

    /**
     * 修改静态数据等
     * @param id 部件id
     * @param componentVO 修改数据（静态数据、图片路径）
     * @return 修改行数
     */
    IntegerBizResult updateStaticData(String id, ComponentVO componentVO);

}
