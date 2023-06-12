package com.freesofts.model.mapper;

import com.freesofts.model.model.LabelPage;
import com.freesofts.model.model.Page;
import com.freesofts.model.model.PageCategory;
import com.freesofts.model.model.PageLabel;
import com.freesofts.model.vo.PageQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PageMapper {

    int insert(@Param("page") Page page,
               @Param("pageCategory") PageCategory pageCategory);

    int insertCopy(@Param("id") String id, @Param("pageId") String pageId, @Param("createdBy") String createdBy, @Param("creatorName") String creatorName);

    int insertCategory(@Param("pageCategory") PageCategory pageCategory);

    int deleteByIds(@Param("ids") List<String> ids,@Param("pageIds") List<String> pageIds);

    int deleteCategory(@Param("pageIds") List<String> pageIds);

    int update(@Param("page") Page page);

    int updateOptions(@Param("pageId") String pageId, @Param("data") String data,@Param("filePath") String filePath);

    Long selectCount(@Param("pageQueryVO") PageQueryVO pageQueryVO);

    List<Page> selectPageList(
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("pageQueryVO") PageQueryVO pageQueryVO
    );

    Page selectById(@Param("id") String id);

    String selectCategoryId(@Param("pageId") String pageId);

    String selectOptions(@Param("id") String id);

    /**
     * 插入标签,插入标签页面中间表
     */
    int insertLabelPage(@Param("labelPages") List<LabelPage> labelPages,
                        @Param("pageLabels") List<PageLabel> pageLabels);

    int insertLabelPages(@Param("labelPages") List<LabelPage> labelPages);
    /**
     * 插入标签页面中间表
     */
    int insertPageLabel(@Param("pageLabels") List<PageLabel> pageLabels);

    /**
     * 根据页面id集合查询标签名称
     */
    List<String> selectTagsByPageId(@Param("ids") List<String> ids);

    List<LabelPage> selectTagsIdAndNameByPageId(@Param("id") String id);

    /**
     * 根据Id集合删除标签表
     */
    int deleteLabelPageByIds(@Param("ids") List<String> ids);
    int deletePageLabelByPageIds(@Param("ids") List<String> ids);


    /**
     * 根据页面id集合查询标签id集合
     */
    List<String> selectTagsIdsByPageId(@Param("ids") List<String> ids);
    List<String> selectTagsIdByPageId(@Param("id") String id);

    int updateLabelPage(@Param("id") String id, @Param("tagsName") String tagsName);

    String selectCategoryName(@Param("cId") String cId);

    int updateCategoryPage(@Param("pageCategory") PageCategory pageCategory);

    /**
     * @param id  页面id
     * @param lock  0:未锁定 1:锁定
     * @param password 锁定密码
     * @return  影响行数
     */
    int updateLock(@Param("id") String id,@Param("lock") int lock,@Param("password") String password);

    /**
     *  根据页面id集合查询页面
     * @param ids   页面id集合
     * @return      影响行数
     */
    List<Page> selectByIds(@Param("ids") List<String> ids);
}
