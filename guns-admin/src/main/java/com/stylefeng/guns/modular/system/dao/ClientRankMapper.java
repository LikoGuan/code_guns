package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.ClientRank;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-10
 */
public interface ClientRankMapper extends BaseMapper<ClientRank> {
	
	/**
     * 获取登录日志
     */
    List<Map<String, Object>> getClientRankList(@Param("page") Page<ClientRank> page, 
                                           @Param("name") String name, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);

}
