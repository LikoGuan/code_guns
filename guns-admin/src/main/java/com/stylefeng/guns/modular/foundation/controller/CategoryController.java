package com.stylefeng.guns.modular.foundation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.foundation.service.ICategoryService;
import com.stylefeng.guns.modular.foundation.warpper.CategoryWarpper;
import com.stylefeng.guns.modular.system.model.Category;

/**
 * 材料分类控制器
 *
 * @author fengshuonan
 * @Date 2018-10-25 20:49:37
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

	private String PREFIX = "/foundation/category/";

	@Autowired
	private ICategoryService categoryService;

	/**
	 * 跳转到材料分类首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "category.html";
	}

	/**
	 * 跳转到添加材料分类
	 */
	@RequestMapping("/category_add")
	public String categoryAdd() {
		return PREFIX + "category_add.html";
	}

	/**
	 * 跳转到修改材料分类
	 */
	@RequestMapping("/category_update/{categoryId}")
	public String categoryUpdate(@PathVariable Integer categoryId, Model model) {
		Category category = categoryService.selectById(categoryId);
		model.addAttribute("item", category);
		model.addAttribute("pName", CategoryWarpper.categoryName(category.getPid()));
		LogObjectHolder.me().set(category);
		return PREFIX + "category_edit.html";
	}

	/**
	 * 获取材料分类列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String name) {
		List<Map<String, Object>> list = categoryService.list(name);
		return super.warpObject(new CategoryWarpper(list));
	}

	/**
	 * 获取材料分类树
	 */
	@Permission
	@RequestMapping(value = "/tree")
	@ResponseBody
	public List<ZTreeNode> tree() {
		List<ZTreeNode> tree = this.categoryService.tree();
		tree.add(createParent());
		return tree;
	}

	/**
	 * 新增材料分类
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(Category category) {
		categoryService.insert(category);
		return SUCCESS_TIP;
	}

	/**
	 * 删除材料分类
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer categoryId) {
		categoryService.deleteCategory(categoryId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改材料分类
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Category category) {
		categoryService.updateById(category);
		return SUCCESS_TIP;
	}

	/**
	 * 材料分类详情
	 */
	@RequestMapping(value = "/detail/{categoryId}")
	@ResponseBody
	public Object detail(@PathVariable("categoryId") Integer categoryId) {
		return categoryService.selectById(categoryId);
	}

	private static ZTreeNode createParent() {
		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setChecked(true);
		zTreeNode.setId(0L);
		zTreeNode.setName("顶级分类");
		zTreeNode.setOpen(true);
		zTreeNode.setpId(0L);
		zTreeNode.setLevel(0l);
		return zTreeNode;
	}
}
