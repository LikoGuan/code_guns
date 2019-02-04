package com.stylefeng.guns.modular.foundation.service.impl;

import com.stylefeng.guns.modular.system.model.Material;
import com.stylefeng.guns.modular.system.dao.MaterialMapper;
import com.stylefeng.guns.modular.foundation.service.IMaterialService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 材料表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-28
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {

	public List<Map<String, Object>> getMaterialList(Page<Material> page, String name, String orderByField,
			boolean asc) {
		return this.baseMapper.getMaterialList(page, name, orderByField, asc);
	}

}
