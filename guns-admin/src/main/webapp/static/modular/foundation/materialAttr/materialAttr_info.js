/**
 * 初始化材料属性管理详情对话框
 */
var MaterialAttrInfoDlg = {
    materialAttrInfoData : {}
};

/**
 * 清除数据
 */
MaterialAttrInfoDlg.clearData = function() {
    this.materialAttrInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MaterialAttrInfoDlg.set = function(key, val) {
    this.materialAttrInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MaterialAttrInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MaterialAttrInfoDlg.close = function() {
    parent.layer.close(window.parent.MaterialAttr.layerIndex);
}

/**
 * 收集数据
 */
MaterialAttrInfoDlg.collectData = function() {
    this
    .set('attrId')
    .set('materialId')
    .set('materialAttr')
    .set('comment');
}

/**
 * 提交添加
 */
MaterialAttrInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/materialAttr/add", function(data){
        Feng.success("添加成功!");
        window.parent.MaterialAttr.table.refresh();
        MaterialAttrInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.materialAttrInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MaterialAttrInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/materialAttr/update", function(data){
        Feng.success("修改成功!");
        window.parent.MaterialAttr.table.refresh();
        MaterialAttrInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.materialAttrInfoData);
    ajax.start();
}

$(function() {

});
