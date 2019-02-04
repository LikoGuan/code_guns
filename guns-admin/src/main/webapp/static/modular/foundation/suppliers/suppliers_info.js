/**
 * 初始化供应商管理详情对话框
 */
var SuppliersInfoDlg = {
    suppliersInfoData : {}
};

/**
 * 清除数据
 */
SuppliersInfoDlg.clearData = function() {
    this.suppliersInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SuppliersInfoDlg.set = function(key, val) {
    this.suppliersInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SuppliersInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SuppliersInfoDlg.close = function() {
    parent.layer.close(window.parent.Suppliers.layerIndex);
}

/**
 * 收集数据
 */
SuppliersInfoDlg.collectData = function() {
    this
    .set('suppliersId')
    .set('suppliersName')
    .set('suppliersDesc')
    .set('isValidate');
}

/**
 * 提交添加
 */
SuppliersInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/suppliers/add", function(data){
        Feng.success("添加成功!");
        window.parent.Suppliers.table.refresh();
        SuppliersInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.suppliersInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SuppliersInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/suppliers/update", function(data){
        Feng.success("修改成功!");
        window.parent.Suppliers.table.refresh();
        SuppliersInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.suppliersInfoData);
    ajax.start();
}

$(function() {
    //初始化是否是菜单
    if($("#isValidateValue").val() == undefined){
        $("#isValidate").val(0);
    }else{
        $("#isValidate").val($("#isValidateValue").val());
    }
});
