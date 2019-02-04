package com.stylefeng.guns.modular.foundation.warpper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.util.ToolUtil;

public class ServiceDistrictWarpper extends BaseControllerWarpper {

	public ServiceDistrictWarpper(List<Map<String, Object>> list) {
		super(list);
	}

	@Override
	public void warpTheMap(Map<String, Object> map) {
		Integer orgid = (Integer) map.get("orgid");
		if (ToolUtil.isEmpty(orgid) || orgid.equals(0)) {
			map.put("orgName", "--");
		} else {
			map.put("orgName", ConstantFactory.me().getOrganizationName(orgid));
		}

		Integer districtId = (Integer) map.get("district_id");
		if (ToolUtil.isEmpty(districtId) || districtId.equals(0)) {
			map.put("districtName", "--");
		} else {
			map.put("districtName", ConstantFactory.me().getDistrictName(districtId));
		}
	}

}
