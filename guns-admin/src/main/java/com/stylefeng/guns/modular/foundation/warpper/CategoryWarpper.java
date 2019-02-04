package com.stylefeng.guns.modular.foundation.warpper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.util.ToolUtil;

public class CategoryWarpper extends BaseControllerWarpper {

	public CategoryWarpper(List<Map<String, Object>> list) {
		super(list);
	}

	@Override
	public void warpTheMap(Map<String, Object> map) {
		Integer pid = (Integer) map.get("pid");

		map.put("pName", categoryName(pid));
	}

	public static String categoryName(Integer id) {
		if (ToolUtil.isEmpty(id) || id.equals(0)) {
			return "顶级分类";
		}
		return ConstantFactory.me().getCategoryName(id);
	}

}
