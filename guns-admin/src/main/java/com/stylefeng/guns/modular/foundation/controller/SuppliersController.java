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
import com.stylefeng.guns.modular.system.model.Suppliers;
import com.stylefeng.guns.modular.foundation.service.ISuppliersService;

/**
 * 供应商管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-22 15:54:01
 */
@Controller
@RequestMapping("/suppliers")
public class SuppliersController extends BaseController {

    private String PREFIX = "/foundation/suppliers/";

    @Autowired
    private ISuppliersService suppliersService;

    /**
     * 跳转到供应商管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "suppliers.html";
    }

    /**
     * 跳转到添加供应商管理
     */
    @RequestMapping("/suppliers_add")
    public String suppliersAdd() {
        return PREFIX + "suppliers_add.html";
    }

    /**
     * 跳转到修改供应商管理
     */
    @RequestMapping("/suppliers_update/{suppliersId}")
    public String suppliersUpdate(@PathVariable Integer suppliersId, Model model) {
        Suppliers suppliers = suppliersService.selectById(suppliersId);
        model.addAttribute("item",suppliers);
        LogObjectHolder.me().set(suppliers);
        return PREFIX + "suppliers_edit.html";
    }

    /**
     * 获取供应商管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return suppliersService.selectList(null);
    }

    /**
     * 新增供应商管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Suppliers suppliers) {
        suppliersService.insert(suppliers);
        return SUCCESS_TIP;
    }

    /**
     * 删除供应商管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer suppliersId) {
        suppliersService.deleteById(suppliersId);
        return SUCCESS_TIP;
    }

    /**
     * 修改供应商管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Suppliers suppliers) {
        suppliersService.updateById(suppliers);
        return SUCCESS_TIP;
    }

    /**
     * 供应商管理详情
     */
    @RequestMapping(value = "/detail/{suppliersId}")
    @ResponseBody
    public Object detail(@PathVariable("suppliersId") Integer suppliersId) {
        return suppliersService.selectById(suppliersId);
    }
}
