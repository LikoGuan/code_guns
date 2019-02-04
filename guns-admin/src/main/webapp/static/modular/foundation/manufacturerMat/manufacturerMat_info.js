/**
 * 初始化生产商材料管理详情对话框
 */
var ManufacturerMatInfoDlg = {
    manufacturerMatInfoData : {}
};

/**
 * 清除数据
 */
ManufacturerMatInfoDlg.clearData = function() {
    this.manufacturerMatInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ManufacturerMatInfoDlg.set = function(key, val) {
    this.manufacturerMatInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ManufacturerMatInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ManufacturerMatInfoDlg.close = function() {
    parent.layer.close(window.parent.ManufacturerMat.layerIndex);
}

/**
 * 收集数据
 */
ManufacturerMatInfoDlg.collectData = function() {
    this
    .set('id')
    .set('orgId')
    .set('materialId');
}

/**
 * 提交添加
 */
ManufacturerMatInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/manufacturerMat/add", function(data){
        Feng.success("添加成功!");
        window.parent.ManufacturerMat.table.refresh();
        ManufacturerMatInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.manufacturerMatInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ManufacturerMatInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/manufacturerMat/update", function(data){
        Feng.success("修改成功!");
        window.parent.ManufacturerMat.table.refresh();
        ManufacturerMatInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.manufacturerMatInfoData);
    ajax.start();
}

$(function() {

});
