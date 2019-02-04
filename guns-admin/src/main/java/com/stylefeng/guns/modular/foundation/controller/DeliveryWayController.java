package com.stylefeng.guns.modular.foundation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.foundation.service.IDeliveryWayService;
import com.stylefeng.guns.modular.foundation.service.IServiceDistrictService;
import com.stylefeng.guns.modular.foundation.transfer.ServiceDistrictDto;
import com.stylefeng.guns.modular.foundation.warpper.DeliveryWayWarpper;
import com.stylefeng.guns.modular.system.model.DeliveryWay;
import com.stylefeng.guns.modular.system.model.ServiceDistrict;

/**
 * 物流方式控制器
 *
 * @author fengshuonan
 * @Date 2018-10-14 19:24:55
 */
@Controller
@RequestMapping("/deliveryWay")
public class DeliveryWayController extends BaseController {

    private String PREFIX = "/foundation/deliveryWay/";

    @Autowired
    private IDeliveryWayService deliveryWayService;
    
    @Autowired
    private IServiceDistrictService serviceDistrictService;

    /**
     * 跳转到物流方式首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "deliveryWay.html";
    }

    /**
     * 跳转到添加物流方式
     */
    @RequestMapping("/deliveryWay_add")
    public String deliveryWayAdd(Model model) {
    	loadServiceDistrictList(model);
        return PREFIX + "deliveryWay_add.html";
    }

    /**
     * 跳转到修改物流方式
     */
    @RequestMapping("/deliveryWay_update/{deliveryWayId}")
    public String deliveryWayUpdate(@PathVariable Integer deliveryWayId, Model model) {
        DeliveryWay deliveryWay = deliveryWayService.selectById(deliveryWayId);
        model.addAttribute("item",deliveryWay);
        loadServiceDistrictList(model);
        LogObjectHolder.me().set(deliveryWay);
        return PREFIX + "deliveryWay_edit.html";
    }

    /**
     * 获取物流方式列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) Long orgid) {
    	System.err.println(orgid);
    	Page<DeliveryWay> page = new PageFactory<DeliveryWay>().defaultPage();
    	List<Map<String, Object>> result = deliveryWayService.getDeliveryWayList(page, orgid, page.getOrderByField(), page.isAsc());
    	page.setRecords((List<DeliveryWay>) new DeliveryWayWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增物流方式
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DeliveryWay deliveryWay) {
        deliveryWayService.insert(deliveryWay);
        return SUCCESS_TIP;
    }

    /**
     * 删除物流方式
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer deliveryWayId) {
        deliveryWayService.deleteById(deliveryWayId);
        return SUCCESS_TIP;
    }

    /**
     * 修改物流方式
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DeliveryWay deliveryWay) {
        deliveryWayService.updateById(deliveryWay);
        return SUCCESS_TIP;
    }

    /**
     * 物流方式详情
     */
    @RequestMapping(value = "/detail/{deliveryWayId}")
    @ResponseBody
    public Object detail(@PathVariable("deliveryWayId") Integer deliveryWayId) {
        return deliveryWayService.selectById(deliveryWayId);
    }
    
	private void loadServiceDistrictList(Model model) {
		List<ServiceDistrict> districtList = serviceDistrictService.selectList(new EntityWrapper<ServiceDistrict>());
		List<ServiceDistrictDto> convertedList = new ArrayList<>();
		for (ServiceDistrict sd : districtList) {
			ServiceDistrictDto dto = new ServiceDistrictDto();
			BeanUtils.copyProperties(sd, dto);
			dto.setOrgName(ConstantFactory.me().getOrganizationName(sd.getOrgid()));
			dto.setDistrictName(ConstantFactory.me().getDistrictName(sd.getDistrictId()));
			dto.setDisplayName();
			convertedList.add(dto);
		}
		model.addAttribute("serviceDistrictList", convertedList);
	}
}
