package com.freesofts.model.service.impl;

import com.freesofts.common.utils.UUIdGenId;
import com.freesofts.model.mapper.MapConfigMapper;
import com.freesofts.model.model.MapConfig;
import com.freesofts.model.service.MapConfigService;
import com.freesofts.model.vo.MapConfigQueryVO;
import com.freesofts.files.client.FileClient;
import com.freesofts.files.vo.result.FileResultVO;
import com.freesofts.threader.AppUserManager;
import com.freesofts.threader.LoginAppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
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
@Service
@Slf4j
public class MapConfigServiceImpl implements MapConfigService {

	@Resource
	private MapConfigMapper mapConfigMapper;

	@Resource
	private FileClient fileClient;

	@Override
	public String insert(MapConfig mapConfig) {
		LoginAppUser user = AppUserManager.getLoginAppUser();
		mapConfig.setId(UUIdGenId.genId());
		mapConfig.setCreatedBy(user.getId());
		mapConfig.setCreatorName(user.getName());
		mapConfig.setId(UUIdGenId.genId());
		int result = mapConfigMapper.insert(mapConfig);
		if (result > 0){
			return mapConfig.getId();
		}
		return null;
	}

	@Override
	public List<HashMap> list(int pageNum, int pageSize, MapConfigQueryVO queryVO) {
		List<HashMap> result = mapConfigMapper.selectPageList((pageNum - 1) * pageSize, pageSize, queryVO);
		return result;
	}

	@Override
	public Long totalCount(MapConfigQueryVO queryVO) {
		return mapConfigMapper.selectCount(queryVO);
	}

	@Override
	public HashMap detail(String id) {
		return mapConfigMapper.detail(id);
	}

	@Override
	public int deleteByIds(List<String> ids) {
		return mapConfigMapper.deleteByIds(ids);
	}

	@Override
	public String json(String id) {
		List<FileResultVO> files = fileClient.getFileDataList(id, "default");
		if (CollectionUtils.isEmpty(files)){
			log.error("文件为空");
		}
		byte[] bytes = fileClient.read(files.get(0).getId());
		String result = new String(bytes, StandardCharsets.UTF_8);
		return result;
	}



}
