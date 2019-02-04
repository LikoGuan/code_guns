/**
 * 初始化制造商等级详情对话框
 */
var ManufacturerRankInfoDlg = {
    manufacturerRankInfoData : {},
    validateFields: {
    	orgName: {
            validators: {
                notEmpty: {
                    message: '制造商不能为空'
                }
            }
        },
        rankName: {
            validators: {
                notEmpty: {
                    message: '制造商等级名称不能为空'
                }
            }
        },
        rank: {
            validators: {
                notEmpty: {
                    message: '制造商等级不能为空'
                },
                integer: {
                	message: '制造商等级必须为正整数'
                }
            }
        },
        typeId: {
            validators: {
                notEmpty: {
                    message: '制造商类型不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ManufacturerRankInfoDlg.clearData = function() {
    this.manufacturerRankInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ManufacturerRankInfoDlg.set = function(key, val) {
    this.manufacturerRankInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ManufacturerRankInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ManufacturerRankInfoDlg.close = function() {
    parent.layer.close(window.parent.ManufacturerRank.layerIndex);
}

/**
 * 收集数据
 */
ManufacturerRankInfoDlg.collectData = function() {
    this
    .set('id')
    .set('manufacturerId')
    .set('rankName')
    .set('rank')
    .set('typeId');
}

/**
 * 显示组织选择的树
 *
 * @returns
 */
ManufacturerRankInfoDlg.showOrganizationSelectTree = function () {
	Feng.showInputTree("orgName", "organizationContent");
};


/**
 * 隐藏组织选择的树
 */
ManufacturerRankInfoDlg.hideOrganizationSelectTree = function () {
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
ManufacturerRankInfoDlg.onClickOrganization = function (e, treeId, treeNode) {
    $("#orgName").attr("value", instance.getSelectedVal());
    $("#manufacturerId").attr("value", treeNode.id);
};

ManufacturerRankInfoDlg.validate = function () {
    $('#manufacturerRankForm').data("bootstrapValidator").resetForm();
    $('#manufacturerRankForm').bootstrapValidator('validate');
    return $("#manufacturerRankForm").data('bootstrapValidator').isValid();
};



/**
 * 提交添加
 */
ManufacturerRankInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/manufacturerRank/add", function(data){
        Feng.success("添加成功!");
        window.parent.ManufacturerRank.table.refresh();
        ManufacturerRankInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.manufacturerRankInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ManufacturerRankInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/manufacturerRank/update", function(data){
        Feng.success("修改成功!");
        window.parent.ManufacturerRank.table.refresh();
        ManufacturerRankInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.manufacturerRankInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("manufacturerRankForm", ManufacturerRankInfoDlg.validateFields);

    $("#typeId").val($("#typeIdValue").val());

    var ztree = new $ZTree("treeDemo", "/organization/tree");
    ztree.bindOnClick(ManufacturerRankInfoDlg.onClickOrganization);
    ztree.init();
    instance = ztree;

});
