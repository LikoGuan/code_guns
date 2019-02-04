package com.stylefeng.guns.modular.foundation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.foundation.service.IManufacturerRankService;
import com.stylefeng.guns.modular.foundation.warpper.ManufacturerRankWarpper;
import com.stylefeng.guns.modular.system.model.ManufacturerRank;

/**
 * 制造商等级控制器
 *
 * @author fengshuonan
 * @Date 2018-10-10 17:21:36
 */
@Controller
@RequestMapping("/manufacturerRank")
public class ManufacturerRankController extends BaseController {

    private String PREFIX = "/foundation/manufacturerRank/";

    @Autowired
    private IManufacturerRankService manufacturerRankService;

    /**
     * 跳转到制造商等级首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "manufacturerRank.html";
    }

    /**
     * 跳转到添加制造商等级
     */
    @RequestMapping("/manufacturerRank_add")
    public String manufacturerRankAdd(Model model) {
    	model.addAttribute("typeList", ConstantFactory.me().getManufacturerTypeList());
        return PREFIX + "manufacturerRank_add.html";
    }

    /**
     * 跳转到修改制造商等级
     */
    @RequestMapping("/manufacturerRank_update/{manufacturerRankId}")
    public String manufacturerRankUpdate(@PathVariable Integer manufacturerRankId, Model model) {
        ManufacturerRank manufacturerRank = manufacturerRankService.selectById(manufacturerRankId);
        model.addAttribute("item",manufacturerRank);
        model.addAttribute("orgName", ConstantFactory.me().getOrganizationName(manufacturerRank.getManufacturerId()));
        model.addAttribute("typeList", ConstantFactory.me().getManufacturerTypeList());
        LogObjectHolder.me().set(manufacturerRank);
        return PREFIX + "manufacturerRank_edit.html";
    }

    /**
     * 获取制造商等级列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name) {
    	Page<ManufacturerRank> page = new PageFactory<ManufacturerRank>().defaultPage();
    	List<Map<String, Object>> result = manufacturerRankService.getManufacturerRankList(page, name, page.getOrderByField(), page.isAsc());
    	page.setRecords((List<ManufacturerRank>) new ManufacturerRankWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增制造商等级
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ManufacturerRank manufacturerRank) {
        manufacturerRankService.insert(manufacturerRank);
        return SUCCESS_TIP;
    }

    /**
     * 删除制造商等级
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer manufacturerRankId) {
        manufacturerRankService.deleteById(manufacturerRankId);
        return SUCCESS_TIP;
    }

    /**
     * 修改制造商等级
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ManufacturerRank manufacturerRank) {
        manufacturerRankService.updateById(manufacturerRank);
        return SUCCESS_TIP;
    }

    /**
     * 制造商等级详情
     */
    @RequestMapping(value = "/detail/{manufacturerRankId}")
    @ResponseBody
    public Object detail(@PathVariable("manufacturerRankId") Integer manufacturerRankId) {
        return manufacturerRankService.selectById(manufacturerRankId);
    }
}
