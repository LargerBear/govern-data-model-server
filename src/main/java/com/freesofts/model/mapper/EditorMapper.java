package com.freesofts.model.mapper;

import com.freesofts.model.model.Editor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EditorMapper {
    /**
     * 根据ids查询Editor列表
     *
     * @param id
     * @return
     */
    Editor selectById(@Param("id") String id);

    /**
     * 根据ids删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") List<String> ids);

    /**
     * 修改
     *
     * @param editor
     * @return
     */
    int updateById(@Param("editor") Editor editor);

    /**
     * 新增
     *
     * @param editor
     * @return
     */
    int insert(@Param("editor") Editor editor);

    /**
     * 列表
     *
     * @param editor
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Editor> selectPageList(@Param("editor") Editor editor,
                                 @Param("pageNum") int pageNum,
                                 @Param("pageSize") int pageSize);

    /**
     * 列表数目
     *
     * @param editor
     * @return
     */
    long pageListNum(@Param("editor") Editor editor);

    /**
     * 列表不分页
     * @param editor 查询条件
     * @return
     */
    List<Editor> selectEditorListNoPage(@Param("editor") Editor editor);
}
