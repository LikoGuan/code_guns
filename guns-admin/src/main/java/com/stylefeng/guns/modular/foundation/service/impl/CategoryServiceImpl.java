package com.stylefeng.guns.modular.foundation.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.foundation.service.ICategoryService;
import com.stylefeng.guns.modular.system.dao.CategoryMapper;
import com.stylefeng.guns.modular.system.model.Category;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
	
	@Resource
    private CategoryMapper categoryMapper;
	
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
	public void deleteCategory(Integer categoryId) {
		Category category = categoryMapper.selectById(categoryId);

        Wrapper<Category> wrapper = new EntityWrapper<>();
        wrapper = wrapper.eq("pid", category.getId());
        List<Category> subCategories = categoryMapper.selectList(wrapper);
        for (Category temp : subCategories) {
        	deleteCategory(temp.getId());
        }

        category.deleteById();
		
	}

}
