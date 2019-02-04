package com.stylefeng.guns.core.common.constant.factory;

import java.util.List;

import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.model.ManufacturerType;
import com.stylefeng.guns.modular.system.model.ServiceDistrict;

/**
 * 常量生产工厂的接口
 *
 * @author fengshuonan
 * @date 2017-06-14 21:12
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    String getUserNameById(Integer userId);

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    String getUserAccountById(Integer userId);

    /**
     * 通过角色ids获取角色名称
     */
    String getRoleName(String roleIds);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Integer roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    String getSingleRoleTip(Integer roleId);

    /**
     * 获取组织名称
     */
    String getOrganizationName(Integer orgId);

    /**
     * 获取菜单的名称们(多个)
     */
    String getMenuNames(String menuIds);

    /**
     * 获取菜单名称
     */
    String getMenuName(Long menuId);

    /**
     * 获取菜单名称通过编号
     */
    String getMenuNameByCode(String code);

    /**
     * 获取字典名称
     */
    String getDictName(Integer dictId);

    /**
     * 获取通知标题
     */
    String getNoticeTitle(Integer dictId);

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    String getDictsByName(String name, Integer val);

    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);

    /**
     * 获取用户登录状态
     */
    String getStatusName(Integer status);

    /**
     * 获取菜单状态
     */
    String getMenuStatusName(Integer status);

    /**
     * 查询字典
     */
    List<Dict> findInDict(Integer id);

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    String getCacheObject(String para);

    /**
     * 获取子组织id
     */
    List<Integer> getSubOrganizationId(Integer orgId);

    /**
     * 获取所有父组织id
     */
    List<Integer> getParentOrganizationIds(Integer orgId);
    
    /**
     * 获取客户类型
     */
    String getClientRankType(Integer clientType);
    
    String getManufacturerType(Integer typeId);
    
    List<ManufacturerType> getManufacturerTypeList();
    
    String getDistrictName(Integer id);
    
    ServiceDistrict getServiceDistrict(Integer sdId);
    
    String getCategoryName(Integer id);
    
    String getBrandName(Integer id);
    
    String getSupplierName(Integer id);

}
