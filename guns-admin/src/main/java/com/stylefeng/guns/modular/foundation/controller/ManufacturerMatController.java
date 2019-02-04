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
import com.stylefeng.guns.modular.system.model.ManufacturerMat;
import com.stylefeng.guns.modular.foundation.service.IManufacturerMatService;

/**
 * 生产商材料管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-25 21:06:47
 */
@Controller
@RequestMapping("/manufacturerMat")
public class ManufacturerMatController extends BaseController {

    private String PREFIX = "/foundation/manufacturerMat/";

    @Autowired
    private IManufacturerMatService manufacturerMatService;

    /**
     * 跳转到生产商材料管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "manufacturerMat.html";
    }

    /**
     * 跳转到添加生产商材料管理
     */
    @RequestMapping("/manufacturerMat_add")
    public String manufacturerMatAdd() {
        return PREFIX + "manufacturerMat_add.html";
    }

    /**
     * 跳转到修改生产商材料管理
     */
    @RequestMapping("/manufacturerMat_update/{manufacturerMatId}")
    public String manufacturerMatUpdate(@PathVariable Integer manufacturerMatId, Model model) {
        ManufacturerMat manufacturerMat = manufacturerMatService.selectById(manufacturerMatId);
        model.addAttribute("item",manufacturerMat);
        LogObjectHolder.me().set(manufacturerMat);
        return PREFIX + "manufacturerMat_edit.html";
    }

    /**
     * 获取生产商材料管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return manufacturerMatService.selectList(null);
    }

    /**
     * 新增生产商材料管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ManufacturerMat manufacturerMat) {
        manufacturerMatService.insert(manufacturerMat);
        return SUCCESS_TIP;
    }

    /**
     * 删除生产商材料管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer manufacturerMatId) {
        manufacturerMatService.deleteById(manufacturerMatId);
        return SUCCESS_TIP;
    }

    /**
     * 修改生产商材料管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ManufacturerMat manufacturerMat) {
        manufacturerMatService.updateById(manufacturerMat);
        return SUCCESS_TIP;
    }

    /**
     * 生产商材料管理详情
     */
    @RequestMapping(value = "/detail/{manufacturerMatId}")
    @ResponseBody
    public Object detail(@PathVariable("manufacturerMatId") Integer manufacturerMatId) {
        return manufacturerMatService.selectById(manufacturerMatId);
    }
}
