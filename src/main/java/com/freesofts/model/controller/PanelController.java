package com.freesofts.model.controller;

import com.freesofts.common.response.BizResponseData;
import com.freesofts.model.model.Panel;
import com.freesofts.model.service.PanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/panel")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PanelController {

    private final PanelService panelService;

    @GetMapping("/list/{pageSize}/{pageNum}")
    public BizResponseData<?> list(@PathVariable int pageNum, @PathVariable int pageSize, Panel panel) {

        return panelService.list(pageNum, pageSize, panel).getResponseData();
    }

    @PostMapping("/{categoryId}")
    public BizResponseData<?> create(
            @PathVariable String categoryId,
            @RequestBody @Validated Panel panel
    ) {

        return panelService.insert(categoryId, panel).nonNull();
    }

    @DeleteMapping
    public BizResponseData<?> deleteByIds(@RequestBody List<String> ids) {

        return panelService.deleteByIds(ids).greaterThan(0);
    }

    @PutMapping
    public BizResponseData<?> updateById(@RequestBody @Validated(Panel.WhenUpdate.class) Panel panel)  {

        return panelService.updateById(panel).getResponseData();
    }

    @PutMapping("/{panelId}")
    public BizResponseData<?> designById(
            @PathVariable String panelId,
            @RequestBody String data
    )  {

        return panelService.updatePanel(panelId, data).getResponseData();
    }

    @GetMapping("/{id}")
    public BizResponseData<?> getById(@PathVariable String id) {

        return panelService.getById(id).nonNull();
    }

}
