/**
 * 初始化生产商工艺管理详情对话框
 */
var ManufacturerProcInfoDlg = {
    manufacturerProcInfoData : {}
};

/**
 * 清除数据
 */
ManufacturerProcInfoDlg.clearData = function() {
    this.manufacturerProcInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ManufacturerProcInfoDlg.set = function(key, val) {
    this.manufacturerProcInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ManufacturerProcInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ManufacturerProcInfoDlg.close = function() {
    parent.layer.close(window.parent.ManufacturerProc.layerIndex);
}

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
ManufacturerProcInfoDlg.onClickOrganization = function (e, treeId, treeNode) {
    $("#orgName").attr("value", instance.getSelectedVal());
    $("#orgId").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
ManufacturerProcInfoDlg.showOrganizationSelectTree = function () {
	Feng.showInputTree("orgName", "organizationContent");
};


/**
 * 隐藏部门选择的树
 */
ManufacturerProcInfoDlg.hideOrganizationSelectTree = function () {
    $("#organizationContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 收集数据
 */
ManufacturerProcInfoDlg.collectData = function() {
    this
    .set('id')
    .set('orgId')
    .set('procId');
}

/**
 * 提交添加
 */
ManufacturerProcInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/manufacturerProc/add", function(data){
        Feng.success("添加成功!");
        window.parent.ManufacturerProc.table.refresh();
        ManufacturerProcInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.manufacturerProcInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ManufacturerProcInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/manufacturerProc/update", function(data){
        Feng.success("修改成功!");
        window.parent.ManufacturerProc.table.refresh();
        ManufacturerProcInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.manufacturerProcInfoData);
    ajax.start();
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
        UserInfoDlg.hideOrganizationSelectTree();
    }
}

$(function() {
        var ztree = new $ZTree("treeDemo", "/organization/tree");
        ztree.bindOnClick(ManufacturerProcInfoDlg.onClickOrganization);
        ztree.init();
        instance = ztree;

        if($("#procIdValue").val() == undefined){
                $("#procId").val(0);
        }else{
                $("#procId").val($("#procIdValue").val());
        }
});
