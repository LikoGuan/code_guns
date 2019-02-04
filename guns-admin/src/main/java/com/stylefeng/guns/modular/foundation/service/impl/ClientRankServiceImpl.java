package com.stylefeng.guns.modular.foundation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.foundation.service.IClientRankService;
import com.stylefeng.guns.modular.system.dao.ClientRankMapper;
import com.stylefeng.guns.modular.system.model.ClientRank;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-10
 */
@Service
public class ClientRankServiceImpl extends ServiceImpl<ClientRankMapper, ClientRank> implements IClientRankService {

	@Override
	public List<Map<String, Object>> getClientRankList(Page<ClientRank> page, String name, String orderByField,
			boolean asc) {
	      return this.baseMapper.getClientRankList(page, name, orderByField, asc);
	}
	
	

}
