package com.stylefeng.guns.modular.Accessory.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.foundation.service.IProcessingService;
import com.stylefeng.guns.modular.foundation.service.ISuppliersService;
import com.stylefeng.guns.modular.foundation.service.IBrandService;
import com.stylefeng.guns.modular.system.domain.MeasureUnit;
import com.stylefeng.guns.modular.system.domain.WeightUnit;
import com.stylefeng.guns.modular.system.model.Brand;
import com.stylefeng.guns.modular.system.model.Processing;
import com.stylefeng.guns.modular.system.model.Suppliers;
import com.stylefeng.guns.modular.system.vo.AccessoryVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Accessory;
import com.stylefeng.guns.modular.Accessory.service.IAccessoryService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 工艺配件管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-24 19:50:16
 */
@Controller
@RequestMapping("/accessory")
public class AccessoryController extends BaseController {

    private String PREFIX = "/Accessory/accessory/";

    @Autowired
    private IAccessoryService accessoryService;

    @Autowired
    private IProcessingService processingService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private ISuppliersService suppliersService;

    /**
     * 跳转到工艺配件管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "accessory.html";
    }

    /**
     * 跳转到添加工艺配件管理
     */
    @RequestMapping("/accessory_add")
    public String accessoryAdd(Model model) {
        List<Processing> processingList = processingService.selectList(null);
        model.addAttribute("processingList", processingList);
        List<Brand> brandList = brandService.selectList(null);
        model.addAttribute("brandList", brandList);
        List<Suppliers> suppliersList = suppliersService.selectList(null);
        model.addAttribute("suppliersList", suppliersList);
        model.addAttribute("measureUnitList", MeasureUnit.values());
        model.addAttribute("weightUnitList", WeightUnit.values());
        return PREFIX + "accessory_add.html";
    }

    /**
     * 跳转到修改工艺配件管理
     */
    @RequestMapping("/accessory_update/{accessoryId}")
    public String accessoryUpdate(@PathVariable Integer accessoryId, Model model) {
        Accessory accessory = accessoryService.selectById(accessoryId);
        model.addAttribute("item",accessory);
        List<Processing> processingList = processingService.selectList(null);
        model.addAttribute("processingList", processingList);
        List<Brand> brandList = brandService.selectList(null);
        model.addAttribute("brandList", brandList);
        List<Suppliers> suppliersList = suppliersService.selectList(null);
        model.addAttribute("suppliersList", suppliersList);
        model.addAttribute("measureUnitList", MeasureUnit.values());
        model.addAttribute("weightUnitList", WeightUnit.values());
        LogObjectHolder.me().set(accessory);
        return PREFIX + "accessory_edit.html";
    }

    /**
     * 获取工艺配件管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<AccessoryVO> accessoryVOList = new ArrayList<>();

        List<Accessory> accessoryList = accessoryService.selectList(null);
        if (CollectionUtils.isNotEmpty(accessoryList)) {
            List<Processing> processingList = processingService.selectList(null);
            Map<Integer, String> processingMap = processingList.stream().collect(Collectors.toMap(Processing::getProcId, Processing::getName));
            List<Brand> brandList = brandService.selectList(null);
            Map<Integer, String> brandMap = brandList.stream().collect(Collectors.toMap(Brand::getBrandId, Brand::getBrandName));
            List<Suppliers> suppliersList = suppliersService.selectList(null);
            Map<Integer, String> suppliersMap = suppliersList.stream().collect(Collectors.toMap(Suppliers::getSuppliersId, Suppliers::getSuppliersName));
            Map<Integer, String> measureUnitMap = Arrays.stream(MeasureUnit.values()).collect(Collectors.toMap(MeasureUnit::getId, MeasureUnit::getDesc));
            Map<Integer, String> weightUnitMap = Arrays.stream(WeightUnit.values()).collect(Collectors.toMap(WeightUnit::getId, WeightUnit::getDesc));

            for (Accessory accessory : accessoryList) {
                AccessoryVO accessoryVO = new AccessoryVO();
                accessoryVO.setAccessoryId(accessory.getAccessoryId());
                accessoryVO.setProcName(processingMap.get(accessory.getProcId()));
                accessoryVO.setName(accessory.getName());
                accessoryVO.setAccessoryDesc(accessory.getAccessoryDesc());
                accessoryVO.setWidth(accessory.getWidth());
                accessoryVO.setLength(accessory.getLength());
                accessoryVO.setThickness(accessory.getThickness());
                accessoryVO.setMeasureUnit(measureUnitMap.get(accessory.getMeasureUnit()));
                accessoryVO.setUnitWeight(weightUnitMap.get(accessory.getUnitWeight()));
                accessoryVO.setWeight(accessory.getWeight());
                accessoryVO.setCreateTime(accessory.getCreateTime());
                accessoryVO.setIsValidate(accessory.getIsValidate());
                accessoryVO.setLastUpdate(accessory.getLastUpdate());
                accessoryVO.setBrand(brandMap.get(accessory.getBrandId()));
                accessoryVO.setSupplier(suppliersMap.get(accessory.getSupplierId()));
                accessoryVO.setMaterialThumb(accessory.getMaterialThumb());
                accessoryVO.setComment(accessory.getComment());
                accessoryVOList.add(accessoryVO);
            }
        }

        return accessoryVOList;
    }

    /**
     * 新增工艺配件管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Accessory accessory) {
        Date now = new Date();
        accessory.setCreateTime(now);
        accessory.setLastUpdate(now);
        accessoryService.insert(accessory);
        return SUCCESS_TIP;
    }

    /**
     * 删除工艺配件管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer accessoryId) {
        accessoryService.deleteById(accessoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改工艺配件管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Accessory accessory) {
        accessory.setLastUpdate(new Date());
        accessoryService.updateById(accessory);
        return SUCCESS_TIP;
    }

    /**
     * 工艺配件管理详情
     */
    @RequestMapping(value = "/detail/{accessoryId}")
    @ResponseBody
    public Object detail(@PathVariable("accessoryId") Integer accessoryId) {
        return accessoryService.selectById(accessoryId);
    }
}
