package com.stylefeng.guns.modular.foundation.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Processing;
import com.stylefeng.guns.modular.foundation.service.IProcessingService;

/**
 * 工艺管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-22 17:53:11
 */
@Controller
@RequestMapping("/processing")
public class ProcessingController extends BaseController {

    private String PREFIX = "/foundation/processing/";

    @Autowired
    private IProcessingService processingService;

    /**
     * 跳转到工艺管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "processing.html";
    }

    /**
     * 跳转到添加工艺管理
     */
    @RequestMapping("/processing_add")
    public String processingAdd() {
        return PREFIX + "processing_add.html";
    }

    /**
     * 跳转到修改工艺管理
     */
    @RequestMapping("/processing_update/{processingId}")
    public String processingUpdate(@PathVariable Integer processingId, Model model) {
        Processing processing = processingService.selectById(processingId);
        model.addAttribute("item",processing);
        LogObjectHolder.me().set(processing);
        return PREFIX + "processing_edit.html";
    }

    /**
     * 获取工艺管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return processingService.selectList(null);
    }

    /**
     * 新增工艺管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Processing processing) {
        processingService.insert(processing);
        return SUCCESS_TIP;
    }

    /**
     * 删除工艺管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer processingId) {
        processingService.deleteById(processingId);
        return SUCCESS_TIP;
    }

    /**
     * 修改工艺管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Processing processing) {
        processingService.updateById(processing);
        return SUCCESS_TIP;
    }

    /**
     * 工艺管理详情
     */
    @RequestMapping(value = "/detail/{processingId}")
    @ResponseBody
    public Object detail(@PathVariable("processingId") Integer processingId) {
        return processingService.selectById(processingId);
    }
}
