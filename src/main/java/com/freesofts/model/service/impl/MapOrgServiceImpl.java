package com.freesofts.model.service.impl;

import com.freesofts.common.response.result.plugins.TreeBizResult;
import com.freesofts.common.webtree.Node;
import com.freesofts.model.mapper.MapOrgMapper;
import com.freesofts.model.service.MapOrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: </br>
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * <p>
 * 杭州孚立计算机软件有限公司
 *
 * @author LargerBear</ br>
 * date: 2023/4/27 9:42</br>
 * @since JDK 1.8
 */
@Service
public class MapOrgServiceImpl implements MapOrgService {

	@Resource
	private MapOrgMapper mapOrgMapper;

	@Override
	public TreeBizResult selectTreeData(String nodeId) {
		ArrayList<Node> nodes = new ArrayList<>();
		// Query the data in the organization table
		Map<String, Object> slots = new HashMap<>();
		slots.put("icon", "icon-org");
		mapOrgMapper.selectTreeData(nodeId).forEach(item -> {
			Node node = new Node(item.getId(), item.getParentId(), item.getTitle(), slots,
					item.getChildSize() <= 0);
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("datatype", item.getChildSize() <= 0 ? "user" : "mixed");
			node.setAttribute(attributes);
			nodes.add(node);
		});
		return TreeBizResult.builder().nodes(nodes).isHierarchy(false).build();
	}
}
