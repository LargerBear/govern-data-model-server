package com.freesofts.model.service.impl;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.plugins.TreeBizResult;
import com.freesofts.common.response.result.types.GenericBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ListBizResult;
import com.freesofts.common.response.result.types.ObjectBizResult;
import com.freesofts.common.utils.Structure;
import com.freesofts.common.utils.UUIdGenId;
import com.freesofts.common.webtree.Node;
import com.freesofts.model.mapper.PageCategoryMapper;
import com.freesofts.model.model.Category;
import com.freesofts.model.service.PageCategoryService;
import com.freesofts.model.vo.QueryVO;
import com.freesofts.threader.AppUserManager;
import com.freesofts.threader.LoginAppUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 周伟
 * @CreateTime: 2023-02-20  10:44
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
public class PageCategoryServiceImpl implements PageCategoryService {
    public final static int LEVEL_CODE_DIGIT = 3;
    private final PageCategoryMapper pageCategoryMapper;

    /**
     * 页面分类列表展示
     */
    @Override
    public ListBizResult<Category> categoryList() {
        return ListBizResult.<Category>builder().list(pageCategoryMapper.categoryList()).build();
    }

    /**
     * 新增分类
     */
    @Override
    public ObjectBizResult insertPageCategory(Category category) {
        // 添加唯一编码，规则为：目录名称前6位首字母+两位随机数0-9，A-Z
        StringBuilder code = new StringBuilder();
        String pinyins = PinyinUtil.getPinyin(category.getName(), ",").toUpperCase(Locale.ROOT);
        if (!StringUtils.equals(pinyins, category.getName())) {
            Arrays.stream(pinyins.split(",")).collect(Collectors.toList()).stream().map(pinyin -> pinyin.substring(0, 1)).collect(Collectors.toList()).forEach(code::append);
        } else {
            code.append(category.getName().toUpperCase(Locale.ROOT));
        }
        //0-9随机数
        code.append(new Random().nextInt(10));
        //A-Z随机数
        char english = (char) ('a' + Math.random() * ('z' - 'a' + 1));
        code.append(english);
        category.setUniqueCode(code.toString());
        //判断是否是顶级节点
        if (StringUtils.isBlank(category.getParentId())) {
            //todo 建一个接口管理要保存的固定的值
            category.setParentId("root");
        }
        //初始化创建信息
        LoginAppUser user = AppUserManager.getLoginAppUser();
        // 判断是否是外部租户
        String tenantId = user.getExFieldTwo();
        if (StringUtils.isNotBlank(tenantId)) {
            category.setTenantId(tenantId);
        }

        category.setId(UUIdGenId.genId())
                .setCreatorName(user.getName())
                .setCreatedBy(user.getId())
                .setCreatedDate(new Date());
        //创建levelCode
        //1.获取pid
        String pid = category.getParentId();
        //2.查询levelCode
        String pLevelCode = pageCategoryMapper.selectPLevelCode(pid);
        if (StringUtils.equals(null, pLevelCode)) {
            pLevelCode = "";
        }
        //3.查询所有子节点的levelCode
        List<String> cLevelCodes = pageCategoryMapper.selectCLevelCodes(pid);
        //4.获取最大节点树
        int max = Structure.getMaxAtSiblingsLvCode(cLevelCodes, LEVEL_CODE_DIGIT);
        //5.创建levelCode
        String levelCode = Structure.produceLevelCode(pLevelCode, max, LEVEL_CODE_DIGIT);
        category.setLevelCode(levelCode);
        //新增
        int res = pageCategoryMapper.insert(category);
        if (res == 0) {
            return ObjectBizResult.builder().message("插入数据失败").build();
        }
        return ObjectBizResult.builder().object(new Node(category.getId(), category.getParentId(), category.getName())).build();
    }

    @Override
    public TreeBizResult selectCategoryTree(String pid) {
        // 判断是否是外部租户
        //初始化创建信息
        LoginAppUser user = AppUserManager.getLoginAppUser();
        // 判断是否是外部租户
        String tenantId = user.getExFieldTwo();

        return TreeBizResult.builder().nodes(pageCategoryMapper.selectTreeByLevelCode(pid, tenantId).stream()
                        .map(item -> new Node(item.getId(), item.getParentId(), item.getTitle(), item.getChildSize() <= 0)).collect(Collectors.toList()))
                .isHierarchy(false).build();
    }

    @Override
    public DatagridBizResult<Category> list(String id, int pageSize, int pageNum, QueryVO queryVO) {
        String levelCode = "";
        if (!StringUtils.equals(id, "root")) {
            levelCode = pageCategoryMapper.selectLevelCodeById(id);
        }
        // 判断是否是外部租户
        //初始化创建信息
        LoginAppUser user = AppUserManager.getLoginAppUser();
        // 判断是否是外部租户
        String tenantId = user.getExFieldTwo();
        if (StringUtils.isNotBlank(tenantId)) {
            queryVO.setTenantId(tenantId);
        }
        List<Category> categories = pageCategoryMapper.selectList(levelCode, (pageNum - 1) * pageSize, pageSize, queryVO, LEVEL_CODE_DIGIT + levelCode.length());
        long count = pageCategoryMapper.selectCount(levelCode, queryVO, LEVEL_CODE_DIGIT + levelCode.length());
        return DatagridBizResult.<Category>builder().list(categories).total(count).build();
    }

    @Override
    public DatagridBizResult<Map<String, Object>> getPageListByKeyword(int pageNum, int pageSize, String keyword, String pid) {
        long total = pageCategoryMapper.selectCountByKeyword(keyword, pid);
        List<Map<String, Object>> list = pageCategoryMapper.selectPageListByKeyword((pageNum - 1) * pageSize, pageSize, keyword, pid);
        List<Map<String, Object>> rslist = Structure.getTreeQueryData(list, set -> pageCategoryMapper.selectPosiForKeyword(set), LEVEL_CODE_DIGIT);
        return DatagridBizResult.<Map<String, Object>>builder().total(total).list(rslist).build();
    }

    @Override
    public GenericBizResult<Category> getById(String id) {
        return GenericBizResult.<Category>builder().t(pageCategoryMapper.selectById(id)).build();
    }

    @Override
    public ObjectBizResult listParents(String id) {
        List<HashMap> list = pageCategoryMapper.selectNameById(id);
        return ObjectBizResult.builder().object(list).build();
    }

    @Override
    public IntegerBizResult deleteByIds(List<String> ids) {
        // 删除分类及其子分类
        List<String> levelCodes = pageCategoryMapper.selectLevelCodes(ids);
        // 判断该分类下是否有数据

        int res = pageCategoryMapper.deleteByLevelCodes(levelCodes);
        return IntegerBizResult.builder().value(res).build();
    }

    @Override
    public ObjectBizResult updateById(Category category) {
        LoginAppUser user = AppUserManager.getLoginAppUser();
        category.setCreatorName(user.getName()).setCreatedDate(new Date()).setCreatedBy(user.getId());
        int res = pageCategoryMapper.update(category);
        if (res == 0) {
            return ObjectBizResult.builder().message("修改失败").build();
        }
        return ObjectBizResult.builder().object(new Node(category.getId(), category.getParentId(), category.getName())).build();
    }
}
