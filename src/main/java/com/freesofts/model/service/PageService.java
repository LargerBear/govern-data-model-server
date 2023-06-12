package com.freesofts.model.service;

import com.freesofts.common.response.result.impl.BoolBizResult;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ObjectBizResult;
import com.freesofts.model.model.Page;
import com.freesofts.model.vo.OptionsVO;
import com.freesofts.model.vo.PageQueryVO;
import com.freesofts.model.vo.PageVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface PageService {
    /**
     * 数据列表
     *
     * @param pageNum     数据页码
     * @param pageSize    每页记录数
     * @param pageQueryVO 查询条件
     * @return
     */
    DatagridBizResult<Page> list(int pageNum, int pageSize, PageQueryVO pageQueryVO) throws InvocationTargetException, IllegalAccessException;

    /**
     * 创建页面
     *
     * @param pageVO
     * @return
     */
    ObjectBizResult insert(PageVO pageVO);

    /**
     * 复制页面
     *
     * @param pageId 页面id
     * @return
     */
    ObjectBizResult copyPage(String pageId);

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return
     */
    BoolBizResult deleteByIds(List<String> ids);

    /**
     * 更新页面属性
     *
     * @param pageVO
     * @return
     */
    BoolBizResult<Object> updateById(PageVO pageVO);

    /**
     * 更新配置选项
     *
     * @param pageId    页面id
     * @param optionsVO
     * @return
     */
    BoolBizResult<Object> updatePage(String pageId, OptionsVO optionsVO);

    /**
     * 获取详情
     *
     * @param id 唯一id
     * @return
     */
    GenericBizResult<PageVO> getById(String id);

    /**
     * 获取配置选项
     */
    GenericBizResult<String> getOptions(String id);

    /**
     * 锁定页面/解锁页面
     *
     * @param id       页面id
     * @param lock     0:锁定 1:解锁
     * @param password 密码
     * @return 是否锁定成功
     */
    IntegerBizResult lock(String id, int lock, String password);
}
