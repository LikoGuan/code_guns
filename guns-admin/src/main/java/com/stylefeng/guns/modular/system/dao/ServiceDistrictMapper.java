package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.ServiceDistrict;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-14
 */
public interface ServiceDistrictMapper extends BaseMapper<ServiceDistrict> {
	
	List<Map<String, Object>> getServiceDistrictList(@Param("page") Page<ServiceDistrict> page, 
            @Param("orgid") Long orgid, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);

}
