package com.freesofts.model.mapper;

import com.freesofts.model.model.LabelManage;
import com.freesofts.model.model.LabelPage;
import com.freesofts.model.vo.QueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhouwei
 */
@Mapper
public interface PageLabelMapper {
    /**
     * 分页标签列表
     */
    List<LabelPage> selectPageLabelList(@Param("categoryId") String categoryId);

    /**
     * 查询所有数据
     */
    List<LabelManage> selectAll();

    /**
     * 查询最大编码
     */
    String findMaxUniqueCode();

    /**
     * 根据唯一编码查询数据库
     */
    LabelPage selectByUniqueCode(@Param("uniqueCode") String uniqueCode);

    /**
     * 新增标签
     */
    int insertLabelPage(@Param("labelPage") LabelPage labelPage);

    /**
     * 根据分类id查询标签数量
     */
    long selectCount(@Param("categoryId") String categoryId);

    List<LabelPage> selectListByCategoryId(@Param("pageStart") int pageStart,
                                           @Param("pageSize") int pageSize,
                                           QueryVO queryVO);

    int deleteByIds(@Param("ids") List<String> ids);

    int update(@Param("labelPage") LabelPage labelPage);

    LabelPage selectById(String id);

    List<LabelPage> selectLabelSons(@Param("id") String id);
}
