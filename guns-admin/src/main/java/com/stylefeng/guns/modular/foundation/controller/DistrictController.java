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

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.foundation.service.IDistrictService;
import com.stylefeng.guns.modular.foundation.warpper.DistrictWarpper;
import com.stylefeng.guns.modular.system.model.District;

/**
 * 区域配置控制器
 *
 * @author fengshuonan
 * @Date 2018-10-11 20:41:05
 */
@Controller
@RequestMapping("/district")
public class DistrictController extends BaseController {

	private String PREFIX = "/foundation/district/";

	@Autowired
	private IDistrictService districtService;

	/**
	 * 跳转到区域配置首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "district.html";
	}

	/**
	 * 跳转到添加区域配置
	 */
	@RequestMapping("/district_add")
	public String districtAdd() {
		return PREFIX + "district_add.html";
	}

	/**
	 * 跳转到修改区域配置
	 */
	@RequestMapping("/district_update/{districtId}")
	public String districtUpdate(@PathVariable Integer districtId, Model model) {
		District district = districtService.selectById(districtId);
		model.addAttribute("item", district);
		if (district.getPid() != 0) {
			model.addAttribute("pName", ConstantFactory.me().getDistrictName(district.getPid()));
		} else {
			model.addAttribute("pName", "全国");
		}
		LogObjectHolder.me().set(district);
		return PREFIX + "district_edit.html";
	}

	/**
	 * 获取区域配置列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String name) {
		List<Map<String, Object>> list = this.districtService.list(name);
		return super.warpObject(new DistrictWarpper(list));
	}

	/**
	 * 新增区域配置
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(District district) {
		district.setCreateTime(new Date());
		district.setUpdateTime(new Date());
		districtService.insert(district);
		return SUCCESS_TIP;
	}

	/**
	 * 获取区域的tree列表
	 */
	@Permission
	@RequestMapping(value = "/tree")
	@ResponseBody
	public List<ZTreeNode> tree() {
		List<ZTreeNode> tree = this.districtService.tree();
		tree.add(createParent());
		return tree;
	}

	/**
	 * 删除区域配置
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer districtId) {
		districtService.deleteDistrict(districtId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改区域配置
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(District district) {
		district.setUpdateTime(new Date());
		districtService.updateById(district);
		return SUCCESS_TIP;
	}

	/**
	 * 区域配置详情
	 */
	@RequestMapping(value = "/detail/{districtId}")
	@ResponseBody
	public Object detail(@PathVariable("districtId") Integer districtId) {
		return districtService.selectById(districtId);
	}

	private static ZTreeNode createParent() {
		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setChecked(true);
		zTreeNode.setId(0L);
		zTreeNode.setName("全国");
		zTreeNode.setOpen(true);
		zTreeNode.setpId(0L);
		zTreeNode.setLevel(0l);
		return zTreeNode;
	}
}
