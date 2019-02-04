package com.stylefeng.guns.modular.system.vo;

/**
 * Created by likoguan on 23/10/18.
 */
public class ManufacturerProcVO {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 组织id
     */
    private Integer orgId;
    /**
     * 组织全称
     */
    private String orgName;
    /**
     * 工艺id
     */
    private Integer procId;
    /**
     * 工艺名
     */
    private String procName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getProcId() {
        return procId;
    }

    public void setProcId(Integer procId) {
        this.procId = procId;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }
}
