package com.stylefeng.guns.modular.system.controller;

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
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.dictmap.OrganizationDict;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Organization;
import com.stylefeng.guns.modular.system.service.IOrganizationService;
import com.stylefeng.guns.modular.system.warpper.OrganizationWarpper;

/**
 * 组织控制器
 *
 * @author fengshuonan
 * @Date 2017年2月17日20:27:22
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {

    private String PREFIX = "/system/organization/";

    @Autowired
    private IOrganizationService organizationService;

    /**
     * 跳转到部门管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "organization.html";
    }

    /**
     * 跳转到添加部门
     */
    @RequestMapping("/organization_add")
    public String organizationAdd() {
        return PREFIX + "organization_add.html";
    }

    /**
     * 跳转到修改部门
     */
    @Permission
    @RequestMapping("/organization_update/{organizationId}")
    public String organizationUpdate(@PathVariable Integer organizationId, Model model) {
    	Organization organization = organizationService.selectById(organizationId);
        model.addAttribute(organization);
        model.addAttribute("pName", ConstantFactory.me().getOrganizationName(organization.getPid()));
        LogObjectHolder.me().set(organization);
        return PREFIX + "organization_edit.html";
    }

    /**
     * 获取部门的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.organizationService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 新增部门
     */
    @BussinessLog(value = "添加部门", key = "simplename", dict = OrganizationDict.class)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public Object add(Organization org) {
        if (ToolUtil.isOneEmpty(org, org.getSimplename())) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //完善pids,根据pid拿到pid的pids
        organizationSetPids(org);
        return this.organizationService.insert(org);
    }

    /**
     * 获取所有部门列表
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list = this.organizationService.list(condition);
        return super.warpObject(new OrganizationWarpper(list));
    }

    /**
     * 部门详情
     */
    @RequestMapping(value = "/detail/{organizationId}")
    @Permission
    @ResponseBody
    public Object detail(@PathVariable("organizationId") Integer organizationId) {
        return organizationService.selectById(organizationId);
    }

    /**
     * 修改部门
     */
    @BussinessLog(value = "修改部门", key = "simplename", dict = OrganizationDict.class)
    @RequestMapping(value = "/update")
    @Permission
    @ResponseBody
    public Object update(Organization org) {
        if (ToolUtil.isEmpty(org) || org.getId() == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        organizationSetPids(org);
        organizationService.updateById(org);
        return SUCCESS_TIP;
    }

    /**
     * 删除部门
     */
    @BussinessLog(value = "删除部门", key = "organizationId", dict = OrganizationDict.class)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public Object delete(@RequestParam Integer organizationId) {

        //缓存被删除的部门名称
        LogObjectHolder.me().set(ConstantFactory.me().getOrganizationName(organizationId));

        organizationService.deleteOrganization(organizationId);

        return SUCCESS_TIP;
    }

    private void organizationSetPids(Organization org) {
        if (ToolUtil.isEmpty(org.getPid()) || org.getPid().equals(0)) {
        	org.setPid(0);
        	org.setPids("[0],");
        } else {
            int pid = org.getPid();
            Organization temp = organizationService.selectById(pid);
            String pids = temp.getPids();
            org.setPid(pid);
            org.setPids(pids + "[" + pid + "],");
        }
    }
}
