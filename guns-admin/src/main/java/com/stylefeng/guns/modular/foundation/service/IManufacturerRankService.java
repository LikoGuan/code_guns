package com.stylefeng.guns.modular.foundation.service;

import com.stylefeng.guns.modular.system.model.ClientRank;
import com.stylefeng.guns.modular.system.model.ManufacturerRank;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-10
 */
public interface IManufacturerRankService extends IService<ManufacturerRank> {
	
	/**
     * 获取客户列表列表
     */
    List<Map<String, Object>> getManufacturerRankList(Page<ManufacturerRank> page, String name, String orderByField, boolean asc);

}
