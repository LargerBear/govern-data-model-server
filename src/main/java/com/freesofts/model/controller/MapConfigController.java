package com.freesofts.model.controller;

import com.freesofts.common.response.BizResponseData;
import com.freesofts.common.response.result.plugins.DatagridBizResult;
import com.freesofts.common.response.result.types.IntegerBizResult;
import com.freesofts.common.response.result.types.ObjectBizResult;
import com.freesofts.model.model.MapConfig;
import com.freesofts.model.service.MapConfigService;
import com.freesofts.model.vo.MapConfigQueryVO;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @author xz
 * @date
 */
@Api(tags = "map-config")
@ApiSupport(author = "xz")
@Slf4j
@RestController
@RequestMapping("/map-config")
public class MapConfigController {

    @Resource
    private MapConfigService mapConfigService;


    /**
     * 新增
     * @param mapConfig
     * @return
     */
    @PostMapping
    public BizResponseData<?> insert(@RequestBody @Valid MapConfig mapConfig) {
        String result = mapConfigService.insert(mapConfig);
        return ObjectBizResult.builder().object(result).build().nonNull();
    }

    /**
     * 根据orgId 查询分页
     * @param queryVO
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/{pageSize}/{pageNum}")
    public BizResponseData<?> list(MapConfigQueryVO queryVO,
                @ApiParam(value = "数据页码", required = true) @PathVariable("pageNum") int pageNum,
                @ApiParam(value = "每页记录数", required = true) @PathVariable("pageSize") int pageSize){
        List<HashMap> list = mapConfigService.list(pageNum, pageSize, queryVO);
        Long total = mapConfigService.totalCount(queryVO);
        return new DatagridBizResult<>(total, list).getResponseData();
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BizResponseData<?> detail(@PathVariable("id") String id){
        HashMap detail = mapConfigService.detail(id);
        return ObjectBizResult.builder().object(detail).build().nonNull();
    }


    /**
     * 删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public BizResponseData<?> deleteByIds(@RequestBody List<String> ids) {
        return IntegerBizResult.builder().value(mapConfigService.deleteByIds(ids)).build().greaterThanAndEquals(1);
    }


    /**
     * 详情json数据
     * @param id
     * @return
     */
    @GetMapping("/{id}/json")
    public BizResponseData<?> json(@PathVariable("id") String id){
        String json = mapConfigService.json(id);
        return ObjectBizResult.builder().object(json).build().nonNull();
    }


}
