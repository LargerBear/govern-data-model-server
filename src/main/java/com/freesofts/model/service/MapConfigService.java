package com.freesofts.model.service;

import com.freesofts.model.model.MapConfig;
import com.freesofts.model.vo.MapConfigQueryVO;

import java.util.HashMap;
import java.util.List;

/**
 * Description: </br>
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * <p>
 * 杭州孚立计算机软件有限公司
 *
 * @author LargerBear</ br>
 * date: 2023/4/27 10:53</br>
 * @since JDK 1.8
 */
public interface MapConfigService {

	/**
	 * 新增
	 * @param mapConfig
	 * @return
	 */
	String insert(MapConfig mapConfig);

	/**
	 * 查询列表
	 * @param pageNum
	 * @param pageSize
	 * @param queryVO
	 * @return
	 */
	List<HashMap> list(int pageNum, int pageSize, MapConfigQueryVO queryVO);

	/**
	 * 总数
	 * @param queryVO
	 * @return
	 */
	Long totalCount(MapConfigQueryVO queryVO);

	/**
	 * 详情
	 * @param id
	 * @return
	 */
	HashMap detail(String id);


	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteByIds(List<String> ids);

	/**
	 * 读取文件json内容
	 * @param id
	 * @return
	 */
	String json(String id);
}
