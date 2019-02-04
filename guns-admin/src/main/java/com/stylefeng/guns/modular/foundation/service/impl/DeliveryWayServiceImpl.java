package com.stylefeng.guns.modular.foundation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.foundation.service.IDeliveryWayService;
import com.stylefeng.guns.modular.system.dao.DeliveryWayMapper;
import com.stylefeng.guns.modular.system.model.DeliveryWay;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-14
 */
@Service
public class DeliveryWayServiceImpl extends ServiceImpl<DeliveryWayMapper, DeliveryWay> implements IDeliveryWayService {
	
	@Override
	public List<Map<String, Object>> getDeliveryWayList(Page<DeliveryWay> page, Long orgid,
			String orderByField, boolean asc) {
		Long orgId = (orgid == null || orgid == 0L) ? null : orgid;
		return this.baseMapper.getDeliveryWayList(page, orgId, orderByField, asc);
	}

}
