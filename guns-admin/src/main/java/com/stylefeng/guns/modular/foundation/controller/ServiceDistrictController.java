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
import com.stylefeng.guns.modular.foundation.service.IServiceDistrictService;
import com.stylefeng.guns.modular.foundation.warpper.ServiceDistrictWarpper;
import com.stylefeng.guns.modular.system.model.ServiceDistrict;

/**
 * 服务区域控制器
 *
 * @author fengshuonan
 * @Date 2018-10-14 15:40:07
 */
@Controller
@RequestMapping("/serviceDistrict")
public class ServiceDistrictController extends BaseController {

	private String PREFIX = "/foundation/serviceDistrict/";

	@Autowired
	private IServiceDistrictService serviceDistrictService;

	/**
	 * 跳转到服务区域首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "serviceDistrict.html";
	}

	/**
	 * 跳转到添加服务区域
	 */
	@RequestMapping("/serviceDistrict_add")
	public String serviceDistrictAdd() {
		return PREFIX + "serviceDistrict_add.html";
	}

	/**
	 * 跳转到修改服务区域
	 */
	@RequestMapping("/serviceDistrict_update/{serviceDistrictId}")
	public String serviceDistrictUpdate(@PathVariable Integer serviceDistrictId, Model model) {
		ServiceDistrict serviceDistrict = serviceDistrictService.selectById(serviceDistrictId);
		model.addAttribute("item", serviceDistrict);
		model.addAttribute("orgName", ConstantFactory.me().getOrganizationName(serviceDistrict.getOrgid()));
		model.addAttribute("districtName", ConstantFactory.me().getDistrictName(serviceDistrict.getDistrictId()));
		LogObjectHolder.me().set(serviceDistrict);
		return PREFIX + "serviceDistrict_edit.html";
	}

	/**
	 * 获取服务区域列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) Long orgid) {
		Page<ServiceDistrict> page = new PageFactory<ServiceDistrict>().defaultPage();
		List<Map<String, Object>> result = serviceDistrictService.getServiceDistrictList(page, orgid,
				page.getOrderByField(), page.isAsc());
		page.setRecords((List<ServiceDistrict>) new ServiceDistrictWarpper(result).warp());
		return super.packForBT(page);
	}

	/**
	 * 新增服务区域
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(ServiceDistrict serviceDistrict, Model model) {
		serviceDistrictService.insert(serviceDistrict);
		return SUCCESS_TIP;
	}

	/**
	 * 删除服务区域
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer serviceDistrictId) {
		serviceDistrictService.deleteById(serviceDistrictId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改服务区域
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(ServiceDistrict serviceDistrict) {
		serviceDistrictService.updateById(serviceDistrict);
		return SUCCESS_TIP;
	}

	/**
	 * 服务区域详情
	 */
	@RequestMapping(value = "/detail/{serviceDistrictId}")
	@ResponseBody
	public Object detail(@PathVariable("serviceDistrictId") Integer serviceDistrictId) {
		return serviceDistrictService.selectById(serviceDistrictId);
	}

}
