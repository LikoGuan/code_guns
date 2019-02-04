/**
 * 初始化服务区域详情对话框
 */
var ServiceDistrictInfoDlg = {
    serviceDistrictInfoData : {},
    validateFields: {
    	orgName: {
            validators: {
                notEmpty: {
                    message: '组织信息不能为空'
                }
            }
        },
        districtName: {
            validators: {
                notEmpty: {
                    message: '地区信息不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ServiceDistrictInfoDlg.clearData = function() {
    this.serviceDistrictInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServiceDistrictInfoDlg.set = function(key, val) {
    this.serviceDistrictInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ServiceDistrictInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ServiceDistrictInfoDlg.close = function() {
    parent.layer.close(window.parent.ServiceDistrict.layerIndex);
}

/**
 * 收集数据
 */
ServiceDistrictInfoDlg.collectData = function() {
    this
    .set('id')
    .set('orgid')
    .set('districtId')
    .set('comment');
}

/**
 * 显示部门选择的树
 *
 * @returns
 */
ServiceDistrictInfoDlg.showOrganizationSelectTree = function () {
	Feng.showInputTree("orgName", "organizationContent");
};


/**
 * 隐藏部门选择的树
 */
ServiceDistrictInfoDlg.hideOrganizationSelectTree = function () {
    $("#organizationContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 显示地区选择的树
 *
 * @returns
 */
ServiceDistrictInfoDlg.showDistrictSelectTree = function () {
	Feng.showInputTree("districtName", "districtContent");
};


/**
 * 隐藏地区选择的树
 */
ServiceDistrictInfoDlg.hideDistrictSelectTree = function () {
    $("#districtContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

ServiceDistrictInfoDlg.onClickOrganization = function (e, treeId, treeNode) {
    $("#orgName").attr("value", instance.getSelectedVal());
    $("#orgid").attr("value", treeNode.id);
};

ServiceDistrictInfoDlg.onClickDistrict = function (e, treeId, treeNode) {
    $("#districtName").attr("value", districtInstance.getSelectedVal());
    $("#districtId").attr("value", treeNode.id);
};

ServiceDistrictInfoDlg.validate = function () {
    $('#serviceDistrictForm').data("bootstrapValidator").resetForm();
    $('#serviceDistrictForm').bootstrapValidator('validate');
    return $("#serviceDistrictForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
ServiceDistrictInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serviceDistrict/add", function(data){
        Feng.success("添加成功!");
        window.parent.ServiceDistrict.table.refresh();
        ServiceDistrictInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serviceDistrictInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ServiceDistrictInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/serviceDistrict/update", function(data){
        Feng.success("修改成功!");
        window.parent.ServiceDistrict.table.refresh();
        ServiceDistrictInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.serviceDistrictInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("serviceDistrictForm", ServiceDistrictInfoDlg.validateFields);

    var ztree = new $ZTree("organizationContentTree", "/organization/tree");
    ztree.bindOnClick(ServiceDistrictInfoDlg.onClickOrganization);
    ztree.init();
    instance = ztree;
    
    var districtZtree = new $ZTree("districtContentTree", "/district/tree");
    districtZtree.bindOnClick(ServiceDistrictInfoDlg.onClickDistrict);
    districtZtree.init();
    districtInstance = districtZtree;
    
    table.setPaginationType("server");
    table.setQueryParams(ClientRank.formParams());
});
