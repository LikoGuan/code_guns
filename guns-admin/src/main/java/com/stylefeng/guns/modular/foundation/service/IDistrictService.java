package com.stylefeng.guns.modular.foundation.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.District;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-11
 */
public interface IDistrictService extends IService<District> {
	
	 /**
     * 获取地区ztree的节点列表
     */
    List<ZTreeNode> tree();
    
    void deleteDistrict(Integer districtId);
    
    List<Map<String, Object>> list(String name);

}
