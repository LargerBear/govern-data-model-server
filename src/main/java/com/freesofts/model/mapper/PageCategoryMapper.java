package com.freesofts.model.mapper;

import com.freesofts.common.dto.BasicNodeDto;
import com.freesofts.model.model.Category;
import com.freesofts.model.vo.QueryVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhouwei
 */
@Mapper
public interface PageCategoryMapper {
    /**
     * 页面分类展示
     */
    List<Category> categoryList();

    /**
     * 新增目录
     */
    int insert(@Param("category") Category category);

    /**
     * 根据pid查询对应的levelCode
     */
    String selectPLevelCode(@Param("pid") String pid);

    /**
     * 查询父节点下所有字节的levelCode
     */
    List<String> selectCLevelCodes(@Param("pid") String pid);

    /**
     * 查询父节点下所有字节的id
     *
     * @param pid      父节点id
     * @param tenantId 租户id
     * @return 树形结构
     */
    List<BasicNodeDto> selectTreeByLevelCode(@Param("pid") String pid, @Param("tenantId") String tenantId);

    List<Category> selectList(@Param("levelCode") String levelCode,
                              @Param("pageStart") int pageStart,
                              @Param("pageSize") int pageSize,
                              @Param("queryVO") QueryVO queryVO,
                              @Param("length") int length);

    long selectCount(@Param("levelCode") String levelCode, @Param("queryVO") QueryVO queryVO, @Param("length") int length);

    Long selectCountByKeyword(@Param("keyword") String keyword,
                              @Param("pid") String pid);

    @MapKey("id")
    List<Map<String, Object>> selectPageListByKeyword(
            @Param("pagestart") int pagestart,
            @Param("pagesize") int pagesize,
            @Param("keyword") String keyword,
            @Param("pid") String pid
    );

    @MapKey("levelCode")
    List<Map<String, String>> selectPosiForKeyword(@Param("list") Set<String> lvCodeSet);

    int deleteByIds(@Param("ids") List<String> ids);

    int update(@Param("category") Category category);

    Category selectById(String id);

    String selectLevelCodeById(String id);

    List<HashMap> selectNameById(@Param("id") String id);

    int deleteCategorys(@Param("ids") List<String> ids);

    List<String> selectLevelCodes(@Param("ids") List<String> ids);

    int deleteByLevelCodes(@Param("levelCodes") List<String> levelCodes);
}
