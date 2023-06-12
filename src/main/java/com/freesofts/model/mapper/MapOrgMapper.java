package com.freesofts.model.mapper;

import com.freesofts.common.dto.BasicNodeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface MapOrgMapper {


	List<BasicNodeDto> selectTreeData(@Param("pkey") String pkey);
}
