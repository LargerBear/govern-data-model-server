package com.freesofts.model.service;

import com.freesofts.common.response.result.plugins.TreeBizResult;

/**
 * Description: </br>
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * <p>
 * 杭州孚立计算机软件有限公司
 *
 * @author LargerBear</ br>
 * date: 2023/4/27 9:41</br>
 * @since JDK 1.8
 */
public interface MapOrgService {


	/**
	 * 获取组织树数据
	 * @param nodeId
	 * @return
	 */
	TreeBizResult selectTreeData(String nodeId);
}
