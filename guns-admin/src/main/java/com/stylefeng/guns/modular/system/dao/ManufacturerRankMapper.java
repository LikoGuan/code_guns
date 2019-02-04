package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.ManufacturerRank;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-10
 */
public interface ManufacturerRankMapper extends BaseMapper<ManufacturerRank> {
	
	List<Map<String, Object>> getManufacturerRankList(@Param("page") Page<ManufacturerRank> page, 
            @Param("name") String name, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);
    
}
