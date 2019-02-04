package com.stylefeng.guns.modular.foundation.service.impl;

import com.stylefeng.guns.modular.system.model.ServiceDistrict;
import com.stylefeng.guns.modular.system.dao.ServiceDistrictMapper;
import com.stylefeng.guns.modular.foundation.service.IServiceDistrictService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-14
 */
@Service
public class ServiceDistrictServiceImpl extends ServiceImpl<ServiceDistrictMapper, ServiceDistrict> implements IServiceDistrictService {

	@Override
	public List<Map<String, Object>> getServiceDistrictList(Page<ServiceDistrict> page, Long orgid, String orderByField,
			boolean asc) {
		final Long orgId = (orgid == null || orgid == 0L) ? null : orgid;
		return this.baseMapper.getServiceDistrictList(page, orgId, orderByField, asc);
	}

}
