package com.stylefeng.guns.modular.foundation.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.ServiceDistrict;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-14
 */
public interface IServiceDistrictService extends IService<ServiceDistrict> {
	
	List<Map<String, Object>> getServiceDistrictList(Page<ServiceDistrict> page, Long orgid, String orderByField, boolean asc);

}
