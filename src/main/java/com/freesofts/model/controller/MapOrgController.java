package com.freesofts.model.controller;

import com.freesofts.common.response.BizResponseData;
import com.freesofts.model.service.MapOrgService;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xz
 * @date
 */
@Api(tags = "map-org")
@ApiSupport(author = "xz")
@Slf4j
@RestController
@RequestMapping("/map-org")
public class MapOrgController {

    @Resource
    private MapOrgService mapOrgService;



    /**
     * 组织树数据
     *
     * @param orgId 组织ID
     * @return BizResponseData
     */
    @GetMapping("/tree/data/{orgId}")
    public BizResponseData<List<Map<String, Object>>> getUserNodeToMixed(@PathVariable String orgId) {
        return mapOrgService.selectTreeData(orgId).getResponseData();
    }


}
