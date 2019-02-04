/**
 * 初始化客户等级详情对话框
 */
var ClientRankInfoDlg = {
    clientRankInfoData : {},
    validateFields: {
    	orgName: {
            validators: {
                notEmpty: {
                    message: '客户不能为空'
                }
            }
        },
        rankName: {
            validators: {
                notEmpty: {
                    message: '客户等级不能为空'
                }
            }
        },
        discount: {
            validators: {
                notEmpty: {
                    message: '折扣不能为空'
                },
                integer: {
                	message: '折扣必须为正整数'
                }
            }
        },
        clientType: {
            validators: {
                notEmpty: {
                    message: '客户类型不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ClientRankInfoDlg.clearData = function() {
    this.clientRankInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClientRankInfoDlg.set = function(key, val) {
    this.clientRankInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClientRankInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ClientRankInfoDlg.close = function() {
    parent.layer.close(window.parent.ClientRank.layerIndex);
}

/**
 * 收集数据
 */
ClientRankInfoDlg.collectData = function() {
    this
    .set('id')
    .set('clientId')
    .set('rankName')
    .set('discount')
    .set('clientType');
}

/**
 * 显示部门选择的树
 *
 * @returns
 */
ClientRankInfoDlg.showOrganizationSelectTree = function () {
	Feng.showInputTree("orgName", "organizationContent");
};


/**
 * 隐藏部门选择的树
 */
ClientRankInfoDlg.hideOrganizationSelectTree = function () {
    $("#organizationContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 点击组织input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
ClientRankInfoDlg.onClickOrganization = function (e, treeId, treeNode) {
    $("#orgName").attr("value", instance.getSelectedVal());
    $("#clientId").attr("value", treeNode.id);
};

ClientRankInfoDlg.validate = function () {
    $('#clientRankForm').data("bootstrapValidator").resetForm();
    $('#clientRankForm').bootstrapValidator('validate');
    return $("#clientRankForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
ClientRankInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/clientRank/add", function(data){
        Feng.success("添加成功!");
        window.parent.ClientRank.table.refresh();
        ClientRankInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.clientRankInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ClientRankInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/clientRank/update", function(data){
        Feng.success("修改成功!");
        window.parent.ClientRank.table.refresh();
        ClientRankInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.clientRankInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("clientRankForm", ClientRankInfoDlg.validateFields);

    $("#clientType").val($("#clientTypeValue").val());

    var ztree = new $ZTree("treeDemo", "/organization/tree");
    ztree.bindOnClick(ClientRankInfoDlg.onClickOrganization);
    ztree.init();
    instance = ztree;
});
