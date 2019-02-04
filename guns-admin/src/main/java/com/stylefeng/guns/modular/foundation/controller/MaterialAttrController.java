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
import com.stylefeng.guns.modular.system.model.MaterialAttr;
import com.stylefeng.guns.modular.foundation.service.IMaterialAttrService;

/**
 * 材料属性管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-26 20:52:07
 */
@Controller
@RequestMapping("/materialAttr")
public class MaterialAttrController extends BaseController {

    private String PREFIX = "/foundation/materialAttr/";

    @Autowired
    private IMaterialAttrService materialAttrService;

    /**
     * 跳转到材料属性管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "materialAttr.html";
    }

    /**
     * 跳转到添加材料属性管理
     */
    @RequestMapping("/materialAttr_add")
    public String materialAttrAdd() {
        return PREFIX + "materialAttr_add.html";
    }

    /**
     * 跳转到修改材料属性管理
     */
    @RequestMapping("/materialAttr_update/{materialAttrId}")
    public String materialAttrUpdate(@PathVariable Integer materialAttrId, Model model) {
        MaterialAttr materialAttr = materialAttrService.selectById(materialAttrId);
        model.addAttribute("item",materialAttr);
        LogObjectHolder.me().set(materialAttr);
        return PREFIX + "materialAttr_edit.html";
    }

    /**
     * 获取材料属性管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return materialAttrService.selectList(null);
    }

    /**
     * 新增材料属性管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MaterialAttr materialAttr) {
        materialAttrService.insert(materialAttr);
        return SUCCESS_TIP;
    }

    /**
     * 删除材料属性管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer materialAttrId) {
        materialAttrService.deleteById(materialAttrId);
        return SUCCESS_TIP;
    }

    /**
     * 修改材料属性管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MaterialAttr materialAttr) {
        materialAttrService.updateById(materialAttr);
        return SUCCESS_TIP;
    }

    /**
     * 材料属性管理详情
     */
    @RequestMapping(value = "/detail/{materialAttrId}")
    @ResponseBody
    public Object detail(@PathVariable("materialAttrId") Integer materialAttrId) {
        return materialAttrService.selectById(materialAttrId);
    }
}
