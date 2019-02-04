package com.stylefeng.guns.modular.foundation.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.foundation.service.IManufacturerProcService;
import com.stylefeng.guns.modular.foundation.service.IProcessingService;
import com.stylefeng.guns.modular.system.vo.ManufacturerProcVO;
import com.stylefeng.guns.modular.system.model.ManufacturerProc;
import com.stylefeng.guns.modular.system.model.Organization;
import com.stylefeng.guns.modular.system.model.Processing;
import com.stylefeng.guns.modular.system.service.IOrganizationService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 生产商工艺管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-23 21:10:49
 */
@Controller
@RequestMapping("/manufacturerProc")
public class ManufacturerProcController extends BaseController {

    private String PREFIX = "/foundation/manufacturerProc/";

    @Autowired
    private IManufacturerProcService manufacturerProcService;

    @Autowired
    private IProcessingService processingService;

    @Autowired
    private IOrganizationService organizationService;

    /**
     * 跳转到生产商工艺管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "manufacturerProc.html";
    }

    /**
     * 跳转到添加生产商工艺管理
     */
    @RequestMapping("/manufacturerProc_add")
    public String manufacturerProcAdd(Model model) {
        List<Processing> processingList = processingService.selectList(null);
        model.addAttribute("processingList", processingList);
        return PREFIX + "manufacturerProc_add.html";
    }

    /**
     * 跳转到修改生产商工艺管理
     */
    @RequestMapping("/manufacturerProc_update/{manufacturerProcId}")
    public String manufacturerProcUpdate(@PathVariable Integer manufacturerProcId, Model model) {
        ManufacturerProc manufacturerProc = manufacturerProcService.selectById(manufacturerProcId);
        model.addAttribute("item", manufacturerProc);
        List<Processing> processingList = processingService.selectList(null);
        model.addAttribute("processingList", processingList);
        Organization organization = organizationService.selectById(manufacturerProc.getOrgId());
        model.addAttribute("orgName", organization.getFullname());
        LogObjectHolder.me().set(manufacturerProc);
        return PREFIX + "manufacturerProc_edit.html";
    }

    /**
     * 获取生产商工艺管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<ManufacturerProcVO> manufacturerProcVOList = new ArrayList<>();

        List<ManufacturerProc> manufacturerProcList = manufacturerProcService.selectList(null);
        if (CollectionUtils.isNotEmpty(manufacturerProcList)) {
            List<Processing> processingList = processingService.selectList(null);
            Map<Integer, String> processingMap = processingList.stream().collect(Collectors.toMap(Processing::getProcId, Processing::getName));
            List<Organization> organizationList = organizationService.selectList(null);
            Map<Integer, String> organisationMap = organizationList.stream().collect(Collectors.toMap(Organization::getId, Organization::getFullname));
            for (ManufacturerProc manufacturerProc : manufacturerProcList) {
                ManufacturerProcVO manufacturerProcVO = new ManufacturerProcVO();
                BeanUtils.copyProperties(manufacturerProc, manufacturerProcVO);
                manufacturerProcVO.setOrgName(organisationMap.get(manufacturerProc.getOrgId()));
                manufacturerProcVO.setProcName(processingMap.get(manufacturerProc.getProcId()));
                manufacturerProcVOList.add(manufacturerProcVO);
            }
        }

        return manufacturerProcVOList;
    }

    /**
     * 新增生产商工艺管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ManufacturerProc manufacturerProc) {
        manufacturerProcService.insert(manufacturerProc);
        return SUCCESS_TIP;
    }

    /**
     * 删除生产商工艺管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer manufacturerProcId) {
        manufacturerProcService.deleteById(manufacturerProcId);
        return SUCCESS_TIP;
    }

    /**
     * 修改生产商工艺管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ManufacturerProc manufacturerProc) {
        manufacturerProcService.updateById(manufacturerProc);
        return SUCCESS_TIP;
    }

    /**
     * 生产商工艺管理详情
     */
    @RequestMapping(value = "/detail/{manufacturerProcId}")
    @ResponseBody
    public Object detail(@PathVariable("manufacturerProcId") Integer manufacturerProcId) {
        return manufacturerProcService.selectById(manufacturerProcId);
    }
}
