package com.freesofts.model.mapper;

import com.freesofts.model.model.MapConfig;
import com.freesofts.model.vo.MapConfigQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
 * date: 2023/4/27 9:43</br>
 * @since JDK 1.8
 */
@Mapper
public interface MapConfigMapper {


	/**
	 * 新增
	 * @param mapConfig
	 * @return
	 */
	int insert(@Param("mapConfig") MapConfig mapConfig);

	/**
	 * 列表
	 * @param pageStart
	 * @param pageSize
	 * @param queryVO
	 * @return
	 */
	List<HashMap> selectPageList(@Param("pageStart") int pageStart,
	                             @Param("pageSize") int pageSize,
	                             @Param("query") MapConfigQueryVO queryVO);


	/**
	 * 统计
	 * @param queryVO
	 * @return
	 */
	Long selectCount(@Param("query") MapConfigQueryVO queryVO);

	/**
	 * 详情
	 * @param id
	 * @return
	 */
	HashMap detail(@Param("id") String id);

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteByIds(@Param("ids") List<String> ids);
}
