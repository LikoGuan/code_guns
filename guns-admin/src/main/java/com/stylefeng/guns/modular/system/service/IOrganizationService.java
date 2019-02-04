package com.stylefeng.guns.modular.system.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.Organization;

/**
 * 组织服务
 *
 * @author fengshuonan
 * @date 2017-04-27 17:00
 */
public interface IOrganizationService extends IService<Organization> {

    /**
     * 删除组织
     */
    void deleteOrganization(Integer organizationId);

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();

    /**
     * 获取所有组织列表
     */
    List<Map<String, Object>> list(@Param("condition") String condition);
}
