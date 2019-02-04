package com.stylefeng.guns.modular.foundation.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.domain.MeasureUnit;
import com.stylefeng.guns.modular.system.vo.PackageVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Package;
import com.stylefeng.guns.modular.foundation.service.IPackageService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 材料包装管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-25 20:29:49
 */
@Controller
@RequestMapping("/package")
public class PackageController extends BaseController {

    private String PREFIX = "/foundation/package/";

    @Autowired
    private IPackageService packageService;

    /**
     * 跳转到材料包装管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "package.html";
    }

    /**
     * 跳转到添加材料包装管理
     */
    @RequestMapping("/package_add")
    public String packageAdd(Model model) {
        model.addAttribute("measureUnitList", MeasureUnit.values());
        return PREFIX + "package_add.html";
    }

    /**
     * 跳转到修改材料包装管理
     */
    @RequestMapping("/package_update/{packageId}")
    public String packageUpdate(@PathVariable Integer packageId, Model model) {
        Package pkg = packageService.selectById(packageId);
        model.addAttribute("item",pkg);
        model.addAttribute("measureUnitList", MeasureUnit.values());
        LogObjectHolder.me().set(pkg);
        return PREFIX + "package_edit.html";
    }

    /**
     * 获取材料包装管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<PackageVO> packageVOList = new ArrayList<>();

        List<Package> packageList = packageService.selectList(null);
        if (CollectionUtils.isNotEmpty(packageList)) {
            Map<Integer, String> measureUnitMap = Arrays.stream(MeasureUnit.values()).collect(Collectors.toMap(MeasureUnit::getId, MeasureUnit::getDesc));
            for (Package pkg : packageList) {
                PackageVO packageVO = new PackageVO();
                packageVO.setPackId(pkg.getPackId());
                packageVO.setPackName(pkg.getPackName());
                packageVO.setPackImg(pkg.getPackImg());
                packageVO.setMeasureUnit(measureUnitMap.get(pkg.getMeasureUnit()));
                packageVO.setPackageLimitation(pkg.getPackageLimitation());
                packageVO.setUnitFee(pkg.getUnitFee());
                packageVO.setPackDesc(pkg.getPackDesc());
                packageVOList.add(packageVO);
            }
        }

        return packageVOList;
    }

    /**
     * 新增材料包装管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Package pkg) {
        packageService.insert(pkg);
        return SUCCESS_TIP;
    }

    /**
     * 删除材料包装管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer packageId) {
        packageService.deleteById(packageId);
        return SUCCESS_TIP;
    }

    /**
     * 修改材料包装管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Package pkg) {
        packageService.updateById(pkg);
        return SUCCESS_TIP;
    }

    /**
     * 材料包装管理详情
     */
    @RequestMapping(value = "/detail/{packageId}")
    @ResponseBody
    public Object detail(@PathVariable("packageId") Integer packageId) {
        return packageService.selectById(packageId);
    }
}
