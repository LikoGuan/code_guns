package com.stylefeng.guns.modular.foundation.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Material;

/**
 * <p>
 * 材料表 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-28
 */
public interface IMaterialService extends IService<Material> {
	
	List<Map<String, Object>> getMaterialList(Page<Material> page, String name, String orderByField, boolean asc);
}
