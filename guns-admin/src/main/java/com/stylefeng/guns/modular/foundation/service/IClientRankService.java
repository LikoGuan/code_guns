package com.stylefeng.guns.modular.foundation.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.ClientRank;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-10
 */
public interface IClientRankService extends IService<ClientRank> {
	
	 /**
     * 获取客户列表列表
     */
    List<Map<String, Object>> getClientRankList(Page<ClientRank> page, String name, String orderByField, boolean asc);

}
