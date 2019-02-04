package com.stylefeng.guns.modular.foundation.service.impl;

import com.stylefeng.guns.modular.system.model.ManufacturerRank;
import com.stylefeng.guns.modular.system.dao.ManufacturerRankMapper;
import com.stylefeng.guns.modular.foundation.service.IManufacturerRankService;
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
 * @since 2018-10-10
 */
@Service
public class ManufacturerRankServiceImpl extends ServiceImpl<ManufacturerRankMapper, ManufacturerRank> implements IManufacturerRankService {

	@Override
	public List<Map<String, Object>> getManufacturerRankList(Page<ManufacturerRank> page, String name,
			String orderByField, boolean asc) {
		return this.baseMapper.getManufacturerRankList(page, name, orderByField, asc);
	}

}
