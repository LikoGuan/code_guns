package com.stylefeng.guns.modular.foundation.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.foundation.service.IProcessingService;
import com.stylefeng.guns.modular.system.vo.ProcessingExclVO;
import com.stylefeng.guns.modular.system.model.Processing;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.ProcessingExcl;
import com.stylefeng.guns.modular.foundation.service.IProcessingExclService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 互斥工艺管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-22 18:09:07
 */
@Controller
@RequestMapping("/processingExcl")
public class ProcessingExclController extends BaseController {

    private String PREFIX = "/foundation/processingExcl/";

    @Autowired
    private IProcessingExclService processingExclService;

    @Autowired
    private IProcessingService processingService;

    /**
     * 跳转到互斥工艺管理首页
     */
    @RequestMapping("")
    public String index(Model model) {
//        List<Processing> processingList = processingService.selectList(null);
//        Map map = processingList.stream().collect(Collectors.toMap(Processing::getProcId, Processing::getName));
//        model.addAttribute("processingMap", map);
        return PREFIX + "processingExcl.html";
    }

    /**
     * 跳转到添加互斥工艺管理
     */
    @RequestMapping("/processingExcl_add")
    public String processingExclAdd(Model model) {
        List<Processing> processingList = processingService.selectList(null);
        model.addAttribute("processingList", processingList);
        return PREFIX + "processingExcl_add.html";
    }

    /**
     * 跳转到修改互斥工艺管理
     */
    @RequestMapping("/processingExcl_update/{processingExclId}")
    public String processingExclUpdate(@PathVariable Integer processingExclId, Model model) {
        ProcessingExcl processingExcl = processingExclService.selectById(processingExclId);
        model.addAttribute("item",processingExcl);
        List<Processing> processingList = processingService.selectList(null);
        model.addAttribute("processingList", processingList);
        LogObjectHolder.me().set(processingExcl);
        return PREFIX + "processingExcl_edit.html";
    }

    /**
     * 获取互斥工艺管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
//        return processingExclService.selectList(null);
        List<ProcessingExclVO> processingExclVOList = new ArrayList<>();

        List<ProcessingExcl> processingExclList = processingExclService.selectList(null);
        if (CollectionUtils.isNotEmpty(processingExclList)) {
            List<Processing> processingList = processingService.selectList(null);
            Map<Integer, String> map = processingList.stream().collect(Collectors.toMap(Processing::getProcId, Processing::getName));
            for (ProcessingExcl processingExcl : processingExclList) {
                ProcessingExclVO processingExclVO = new ProcessingExclVO();
                processingExclVO.setId(processingExcl.getId());
                processingExclVO.setProcAId(processingExcl.getProcaId());
                processingExclVO.setProcBId(processingExcl.getProcbId());
                processingExclVO.setComment(processingExcl.getComment());
                processingExclVO.setProcAName(map.get(processingExcl.getProcaId()));
                processingExclVO.setProcBName(map.get(processingExcl.getProcbId()));
                processingExclVOList.add(processingExclVO);
            }
        }

        return processingExclVOList;
    }

    /**
     * 新增互斥工艺管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ProcessingExcl processingExcl) {
        processingExclService.insert(processingExcl);
        return SUCCESS_TIP;
    }

    /**
     * 删除互斥工艺管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer processingExclId) {
        processingExclService.deleteById(processingExclId);
        return SUCCESS_TIP;
    }

    /**
     * 修改互斥工艺管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ProcessingExcl processingExcl) {
        processingExclService.updateById(processingExcl);
        return SUCCESS_TIP;
    }

    /**
     * 互斥工艺管理详情
     */
    @RequestMapping(value = "/detail/{processingExclId}")
    @ResponseBody
    public Object detail(@PathVariable("processingExclId") Integer processingExclId) {
        return processingExclService.selectById(processingExclId);
    }
}
