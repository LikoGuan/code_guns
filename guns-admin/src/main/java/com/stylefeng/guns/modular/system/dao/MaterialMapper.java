package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Material;

/**
 * <p>
 * 材料表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-28
 */
public interface MaterialMapper extends BaseMapper<Material> {
	
	List<Map<String, Object>> getMaterialList(@Param("page") Page<Material> page, 
            @Param("name") String name, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);

}
