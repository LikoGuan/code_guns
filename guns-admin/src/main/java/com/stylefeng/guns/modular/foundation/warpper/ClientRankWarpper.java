package com.stylefeng.guns.modular.foundation.warpper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

public class ClientRankWarpper extends BaseControllerWarpper {

    public ClientRankWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
    	map.put("orgName", ConstantFactory.me().getOrganizationName((Integer) map.get("client_id")));
        map.put("clientTypeName", ConstantFactory.me().getClientRankType((Integer) map.get("client_type")));
    }
}
