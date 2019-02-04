/**
 * 初始化区域配置详情对话框
 */
var DistrictInfoDlg = {
    districtInfoData : {},
    zTreeInstance : null,
    validateFields: {
    	districtName: {
            validators: {
                notEmpty: {
                    message: '区域名称不能为空'
                }
            }
        },
        sortOrder: {
            validators: {
                notEmpty: {
                    message: '区域排序不能为空'
                },
                integer: {
                	message: '区域排序必须为正整数'
                }
            }
        },
        pName: {
            validators: {
                notEmpty: {
                    message: '父级区域不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
DistrictInfoDlg.clearData = function() {
    this.districtInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DistrictInfoDlg.set = function(key, val) {
    this.districtInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DistrictInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DistrictInfoDlg.close = function() {
    parent.layer.close(window.parent.District.layerIndex);
}

/**
 * 点击地区ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
DistrictInfoDlg.onClickDistrict = function(e, treeId, treeNode) {
    $("#pName").attr("value", DistrictInfoDlg.zTreeInstance.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
    $("#level").attr("value", treeNode.level + 1);
}

/**
 * 显示地区选择的树
 *
 * @returns
 */
DistrictInfoDlg.showDistrictSelectTree = function() {
    var pName = $("#pName");
    var pNameOffset = $("#pName").offset();
    $("#parentDistrictMenu").css({
        left : pNameOffset.left + "px",
        top : pNameOffset.top + pName.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏地区选择的树
 */
DistrictInfoDlg.hideDistrictSelectTree = function() {
    $("#parentDistrictMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}

/**
 * 收集数据
 */
DistrictInfoDlg.collectData = function() {
    this
    .set('id')
    .set('pid')
    .set('districtName')
    .set('shortName')
    .set('level')
    .set('sortOrder')
    .set('longitude')
    .set('latitude')
    .set('isActivated');
}

/**
 * 验证数据
 */
DistrictInfoDlg.validate = function () {
    $('#districtInfoForm').data("bootstrapValidator").resetForm();
    $('#districtInfoForm').bootstrapValidator('validate');
    return $("#districtInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
DistrictInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/district/add", function(data){
        Feng.success("添加成功!");
        window.parent.District.table.refresh();
        DistrictInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.districtInfoData);
    ajax.start();
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentDistrictMenu" || $(
            event.target).parents("#parentDistrictMenu").length > 0)) {
    	DistrictInfoDlg.hideDistrictSelectTree();
    }
}

/**
 * 提交修改
 */
DistrictInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/district/update", function(data){
        Feng.success("修改成功!");
        window.parent.District.table.refresh();
        DistrictInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.districtInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("districtInfoForm", DistrictInfoDlg.validateFields);

    var ztree = new $ZTree("parentDistrictMenuTree", "/district/tree");
    ztree.bindOnClick(DistrictInfoDlg.onClickDistrict);
    ztree.init();
    DistrictInfoDlg.zTreeInstance = ztree;
});
