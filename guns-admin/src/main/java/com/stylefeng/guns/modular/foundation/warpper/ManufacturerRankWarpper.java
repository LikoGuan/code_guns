package com.stylefeng.guns.modular.foundation.warpper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

public class ManufacturerRankWarpper extends BaseControllerWarpper {

    public ManufacturerRankWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
    	map.put("orgName", ConstantFactory.me().getOrganizationName((Integer) map.get("manufacturer_id")));
        map.put("typeName", ConstantFactory.me().getManufacturerType((Integer) map.get("type_id")));
    }
}
