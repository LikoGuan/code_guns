package com.stylefeng.guns.modular.foundation.controller;

import java.util.Date;
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
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.foundation.service.IPackageService;
import com.stylefeng.guns.modular.foundation.service.ISuppliersService;
import com.stylefeng.guns.modular.foundation.service.IBrandService;
import com.stylefeng.guns.modular.foundation.service.IMaterialService;
import com.stylefeng.guns.modular.foundation.warpper.CategoryWarpper;
import com.stylefeng.guns.modular.foundation.warpper.MaterialWarpper;
import com.stylefeng.guns.modular.system.model.Brand;
import com.stylefeng.guns.modular.system.model.Material;
import com.stylefeng.guns.modular.system.model.Suppliers;

/**
 * 材料清单控制器
 *
 * @author fengshuonan
 * @Date 2018-10-28 20:30:28
 */
@Controller
@RequestMapping("/material")
public class MaterialController extends BaseController {

	private String PREFIX = "/foundation/material/";

	@Autowired
	private IMaterialService materialService;

	@Autowired
	private ISuppliersService suppliersService;

	@Autowired
	private IBrandService brandService;

	@Autowired
	private IPackageService packageService;

	/**
	 * 跳转到材料清单首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "material.html";
	}

	/**
	 * 跳转到添加材料清单
	 */
	@RequestMapping("/material_add")
	public String materialAdd(Model model) {
		List<Brand> brandList = brandService.selectList(null);
		model.addAttribute("brandList", brandList);
		List<Suppliers> suppliersList = suppliersService.selectList(null);
		model.addAttribute("suppliersList", suppliersList);
		model.addAttribute("packageList", packageService.selectList(null));
		return PREFIX + "material_add.html";
	}

	/**
	 * 跳转到修改材料清单
	 */
	@RequestMapping("/material_update/{materialId}")
	public String materialUpdate(@PathVariable Integer materialId, Model model) {
		Material material = materialService.selectById(materialId);
		model.addAttribute("item", material);
		model.addAttribute("categoryName", CategoryWarpper.categoryName(material.getCatId()));
		List<Brand> brandList = brandService.selectList(null);
		model.addAttribute("brandList", brandList);
		List<Suppliers> suppliersList = suppliersService.selectList(null);
		model.addAttribute("suppliersList", suppliersList);
		model.addAttribute("packageList", packageService.selectList(null));
		LogObjectHolder.me().set(material);
		return PREFIX + "material_edit.html";
	}

	/**
	 * 获取材料清单列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String name) {
		Page<Material> page = new PageFactory<Material>().defaultPage();
		List<Map<String, Object>> result = materialService.getMaterialList(page, name, page.getOrderByField(),
				page.isAsc());
		page.setRecords((List<Material>) new MaterialWarpper(result).warp());
		return super.packForBT(page);
	}

	/**
	 * 新增材料清单
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(Material material) {
		material.setCreateTime(new Date());
		material.setLastUpdate(new Date());
		materialService.insert(material);
		return SUCCESS_TIP;
	}

	/**
	 * 删除材料清单
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer materialId) {
		materialService.deleteById(materialId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改材料清单
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Material material) {
		material.setLastUpdate(new Date());
		materialService.updateById(material);
		return SUCCESS_TIP;
	}

	/**
	 * 材料清单详情
	 */
	@RequestMapping(value = "/detail/{materialId}")
	@ResponseBody
	public Object detail(@PathVariable("materialId") Integer materialId, Model model) {
		return materialService.selectById(materialId);
	}
}
