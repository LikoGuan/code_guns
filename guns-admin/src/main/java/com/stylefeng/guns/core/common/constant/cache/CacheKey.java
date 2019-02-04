package com.stylefeng.guns.core.common.constant.cache;

/**
 * 缓存标识前缀集合,常用在ConstantFactory类中
 *
 * @author fengshuonan
 * @date 2017-04-25 9:37
 */
public interface CacheKey {

    /**
     * 角色名称(多个)
     */
    String ROLES_NAME = "roles_name_";

    /**
     * 角色名称(单个)
     */
    String SINGLE_ROLE_NAME = "single_role_name_";

    /**
     * 角色英文名称
     */
    String SINGLE_ROLE_TIP = "single_role_tip_";

    /**
     * 部门名称
     */
    String DEPT_NAME = "dept_name_";
   
    /**
     * 字典Cache Key前缀
     */
    String DICT_NAME = "dict_key_";
    
    /**
     * 制造商类型
     */
    String MANUFACTURER_TYPE_NAME = "manufacturer_type_";
    
    String MANUFACTURER_TYPE_LIST_NAME = "manufacturer_list";
    
    String DISTRICT_ID_NAME = "district_type_";
    
    String CATEGORY_ID_NAME = "category_type_";
    
    String SERVICE_DISTRICT_ID_NAME = "service_district_type_";
    
    String BRAND_ID_NAME = "brand_type_";
    
    String SUPPLIER_ID_NAME = "supplier_type_";
}
