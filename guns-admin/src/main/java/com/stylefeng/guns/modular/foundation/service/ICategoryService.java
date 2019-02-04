package com.stylefeng.guns.modular.foundation.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.Category;

public interface ICategoryService extends IService<Category> {

	List<ZTreeNode> tree();

	List<Map<String, Object>> list(String name);

	void deleteCategory(Integer categoryId);

}
