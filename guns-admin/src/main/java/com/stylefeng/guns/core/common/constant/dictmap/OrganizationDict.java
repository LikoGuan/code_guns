package com.stylefeng.guns.core.common.constant.dictmap;

import com.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 部门的映射
 *
 * @author fengshuonan
 * @date 2017-05-06 15:01
 */
public class OrganizationDict extends AbstractDictMap {

    @Override
    public void init() {
        put("organizationId", "组织名称");
        put("num", "组织排序");
        put("pid", "上级名称");
        put("simplename", "组织简称");
        put("fullname", "组织全称");
        put("tips", "备注");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("organization", "getOrganizationName");
        putFieldWrapperMethodName("pid", "getOrganizationName");
    }
}
