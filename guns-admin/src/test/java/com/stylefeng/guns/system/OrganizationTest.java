package com.stylefeng.guns.system;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.system.dao.OrganizationMapper;
import com.stylefeng.guns.modular.system.model.Organization;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 字典服务测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class OrganizationTest extends BaseJunit {

    @Resource
    OrganizationMapper organizationMapper;

    @Test
    public void addDeptTest() {
    	Organization org = new Organization();
    	org.setFullname("测试fullname");
    	org.setNum(5);
    	org.setPid(1);
    	org.setSimplename("测试");
    	org.setTips("测试tips");
    	org.setAddress("地址");
    	org.setContactPerson("联系人");
    	org.setContactPhone("9090980");
    	org.setVersion(1);
        Integer insert = organizationMapper.insert(org);
        assertEquals(insert, new Integer(1));
    }

    @Test
    public void updateTest() {
        Organization org = this.organizationMapper.selectById(24);
        org.setTips("哈哈");
        boolean flag = org.updateById();
        assertTrue(flag);
    }

    @Test
    public void deleteTest() {
    	Organization org = this.organizationMapper.selectById(24);
        Integer integer = organizationMapper.deleteById(org);
        assertTrue(integer > 0);
    }

    @Test
    public void listTest() {
        List<Map<String, Object>> list = this.organizationMapper.list("总公司");
        assertTrue(list.size() > 0);
    }
}
