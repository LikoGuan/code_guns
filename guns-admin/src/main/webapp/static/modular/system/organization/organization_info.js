/**
 * 初始化组织详情对话框
 */
var OrganizationInfoDlg = {
	organizationInfoData : {},
    zTreeInstance : null,
    validateFields: {
        simplename: {
            validators: {
                notEmpty: {
                    message: '组织名称不能为空'
                }
            }
        },
        fullname: {
            validators: {
                notEmpty: {
                    message: '组织全称不能为空'
                }
            }
        },
        pName: {
            validators: {
                notEmpty: {
                    message: '组织名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
OrganizationInfoDlg.clearData = function() {
    this.organizationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrganizationInfoDlg.set = function(key, val) {
    this.organizationInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrganizationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OrganizationInfoDlg.close = function() {
    parent.layer.close(window.parent.Organization.layerIndex);
}

/**
 * 点击组织ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
OrganizationInfoDlg.onClickOrganization = function(e, treeId, treeNode) {
    $("#pName").attr("value", OrganizationInfoDlg.zTreeInstance.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
}

/**
 * 显示组织选择的树
 *
 * @returns
 */
OrganizationInfoDlg.showOrganizationSelectTree = function() {
    var pName = $("#pName");
    var pNameOffset = $("#pName").offset();
    $("#parentOrganizationMenu").css({
        left : pNameOffset.left + "px",
        top : pNameOffset.top + pName.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏组织选择的树
 */
OrganizationInfoDlg.hideOrganizationSelectTree = function() {
    $("#parentOrganizationMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}

/**
 * 收集数据
 */
OrganizationInfoDlg.collectData = function() {
    this
    .set('id')
    .set('num')
    .set('pid')
    .set('pids')
    .set('simplename')
    .set('fullname')
    .set('tips')
    .set('address')
    .set('contactPerson')
    .set('contactPhone')
    .set('version');
}

/**
 * 验证数据是否为空
 */
OrganizationInfoDlg.validate = function () {
    $('#organizationInfoForm').data("bootstrapValidator").resetForm();
    $('#organizationInfoForm').bootstrapValidator('validate');
    return $("#organizationInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加组织
 */
OrganizationInfoDlg.addSubmit = function() {

	this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/organization/add", function(data){
        Feng.success("添加成功!");
        window.parent.Organization.table.refresh();
        OrganizationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.organizationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OrganizationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/organization/update", function(data){
        Feng.success("修改成功!");
        window.parent.Organization.table.refresh();
        OrganizationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.organizationInfoData);
    ajax.start();
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentOrganizationMenu" || $(
            event.target).parents("#parentOrganizationMenu").length > 0)) {
    	OrganizationInfoDlg.hideOrganizationSelectTree();
    }
}

$(function() {
    Feng.initValidator("organizationInfoForm", OrganizationInfoDlg.validateFields);

    var ztree = new $ZTree("parentOrganizationMenuTree", "/organization/tree");
    ztree.bindOnClick(OrganizationInfoDlg.onClickOrganization);
    ztree.init();
    OrganizationInfoDlg.zTreeInstance = ztree;
});
