package com.stylefeng.guns.modular.foundation.warpper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

public class MaterialWarpper extends BaseControllerWarpper {

	public MaterialWarpper(List<Map<String, Object>> list) {
		super(list);
	}

	@Override
	public void warpTheMap(Map<String, Object> map) {
		Integer catId = (Integer) map.get("cat_id");

		map.put("cat_name", CategoryWarpper.categoryName(catId));
		map.put("measureUnit_name",
				ConstantFactory.me().getDictsByName("计量单位", (Integer)(map.get("measure_unit"))));
		map.put("unitWeight_name",
				ConstantFactory.me().getDictsByName("重量单位", (Integer) map.get("unit_weight")));
		map.put("brand", ConstantFactory.me().getBrandName((Integer)map.get("brand_id")));
		map.put("supplier", ConstantFactory.me().getSupplierName((Integer)map.get("supplier_id")));
		
		map.put("is_real", (Boolean)(map.get("is_real")) ? "是" : "否");
		map.put("is_merchandise", (Boolean)(map.get("is_merchandise")) ? "是" : "否");
	}

}
