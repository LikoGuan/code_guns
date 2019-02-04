package com.stylefeng.guns.modular.foundation.warpper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.modular.system.model.ServiceDistrict;

public class DeliveryWayWarpper extends BaseControllerWarpper {

	public DeliveryWayWarpper(List<Map<String, Object>> list) {
		super(list);
	}

	@Override
	public void warpTheMap(Map<String, Object> map) {
		final ServiceDistrict sd = ConstantFactory.me().getServiceDistrict((Integer) map.get("service_dist_id"));
		if (sd == null) {
			map.put("orgName", "--");
			map.put("districtName", "--");
			return;
		}
		map.put("orgName", ConstantFactory.me().getOrganizationName(sd.getOrgid()));
		map.put("districtName", ConstantFactory.me().getDistrictName(sd.getDistrictId()));
	}

}
