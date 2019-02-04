package com.stylefeng.guns.modular.foundation.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.foundation.service.IDistrictService;
import com.stylefeng.guns.modular.system.dao.DistrictMapper;
import com.stylefeng.guns.modular.system.model.District;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-11
 */
@Service
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements IDistrictService {
	
	@Resource
    private DistrictMapper districtMapper;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ZTreeNode> tree() {
		return this.baseMapper.tree();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> list(String name) {
		return this.baseMapper.list(name);
	}

	@Override
	public void deleteDistrict(Integer districtId) {
		District district = districtMapper.selectById(districtId);

        Wrapper<District> wrapper = new EntityWrapper<>();
        wrapper = wrapper.eq("pid", district.getId());
        List<District> subDistricts = districtMapper.selectList(wrapper);
        for (District temp : subDistricts) {
        	deleteDistrict(temp.getId());
        }

        district.deleteById();
		
	}

}
