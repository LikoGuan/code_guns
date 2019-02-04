package com.stylefeng.guns.modular.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.dao.OrganizationMapper;
import com.stylefeng.guns.modular.system.model.Organization;
import com.stylefeng.guns.modular.system.service.IOrganizationService;

@Service
@Transactional
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements IOrganizationService {

    @Resource
    private OrganizationMapper organizationMapper;

    @Override
    public void deleteOrganization(Integer organizationId) {
    	Organization organization = organizationMapper.selectById(organizationId);

        Wrapper<Organization> wrapper = new EntityWrapper<>();
        wrapper = wrapper.like("pids", "%[" + organization.getId() + "]%");
        List<Organization> subOrganizations = organizationMapper.selectList(wrapper);
        for (Organization temp : subOrganizations) {
            temp.deleteById();
        }

        organization.deleteById();
    }

    @Override
    public List<ZTreeNode> tree() {
        return this.organizationMapper.tree();
    }

    @Override
    public List<Map<String, Object>> list(String condition) {
        return this.organizationMapper.list(condition);
    }
}
