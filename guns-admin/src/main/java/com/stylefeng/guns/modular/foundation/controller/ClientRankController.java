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

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.foundation.service.IClientRankService;
import com.stylefeng.guns.modular.foundation.warpper.ClientRankWarpper;
import com.stylefeng.guns.modular.system.model.ClientRank;

/**
 * 客户等级控制器
 *
 * @author fengshuonan
 * @Date 2018-10-10 10:08:24
 */
@Controller
@RequestMapping("/clientRank")
public class ClientRankController extends BaseController {

    private String PREFIX = "/foundation/clientRank/";

    @Autowired
    private IClientRankService clientRankService;

    /**
     * 跳转到客户等级首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "clientRank.html";
    }

    /**
     * 跳转到添加客户等级
     */
    @RequestMapping("/clientRank_add")
    public String clientRankAdd() {
        return PREFIX + "clientRank_add.html";
    }

    /**
     * 跳转到修改客户等级
     */
    @RequestMapping("/clientRank_update/{clientRankId}")
    public String clientRankUpdate(@PathVariable Integer clientRankId, Model model) {
        ClientRank clientRank = clientRankService.selectById(clientRankId);
        model.addAttribute("item",clientRank);
        model.addAttribute("orgName", ConstantFactory.me().getOrganizationName(clientRank.getClientId()));
        LogObjectHolder.me().set(clientRank);
        return PREFIX + "clientRank_edit.html";
    }

    /**
     * 获取客户等级列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name) {
    	Page<ClientRank> page = new PageFactory<ClientRank>().defaultPage();
    	List<Map<String, Object>> result = clientRankService.getClientRankList(page, name, page.getOrderByField(), page.isAsc());
    	page.setRecords((List<ClientRank>) new ClientRankWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 新增客户等级
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ClientRank clientRank) {
        clientRankService.insert(clientRank);
        return SUCCESS_TIP;
    }

    /**
     * 删除客户等级
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer clientRankId) {
        clientRankService.deleteById(clientRankId);
        return SUCCESS_TIP;
    }

    /**
     * 修改客户等级
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ClientRank clientRank) {
        clientRankService.updateById(clientRank);
        return SUCCESS_TIP;
    }

    /**
     * 客户等级详情
     */
    @RequestMapping(value = "/detail/{clientRankId}")
    @ResponseBody
    public Object detail(@PathVariable("clientRankId") Integer clientRankId) {
        return clientRankService.selectById(clientRankId);
    }
}
