package com.freesofts.model.mapper;

import com.freesofts.model.model.Component;
import com.freesofts.model.model.ComponentCategory;
import com.freesofts.model.vo.ComponentQueryVO;
import com.freesofts.model.vo.ComponentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ComponentMapper {

    int insert(@Param("component") Component component);

    int insertCopy(@Param("id") String id, @Param("componentId") String componentId, @Param("createdBy") String createdBy, @Param("creatorName") String creatorName,@Param("name") String name);

    String selectName(@Param("componentId") String componentId);

    int insertCategory(@Param("componentCategory") ComponentCategory componentCategory);

    int deleteByIds(@Param("ids") List<String> ids);

    int update(@Param("component") Component component);

    Long selectCount(@Param("componentQueryVO") ComponentQueryVO componentQueryVO);

    List<ComponentVO> selectComponentList(
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("componentQueryVO") ComponentQueryVO componentQueryVO
    );

    ComponentVO selectById(@Param("id") String id);

    String selectCategoryId(@Param("componentId") String componentId);

    int updateOptions(@Param("componentId") String componentId, @Param("data") String data);

    String selectOptions(@Param("id") String id);

    int updateState(@Param("id") String id, @Param("state") String state);

    int updateStaticData(@Param("id") String id,@Param("componentVO") ComponentVO componentVO);
}
