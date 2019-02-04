/**
 * 初始化工艺配件管理详情对话框
 */
var AccessoryInfoDlg = {
    accessoryInfoData : {}
};

/**
 * 清除数据
 */
AccessoryInfoDlg.clearData = function() {
    this.accessoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AccessoryInfoDlg.set = function(key, val) {
    this.accessoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AccessoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AccessoryInfoDlg.close = function() {
    parent.layer.close(window.parent.Accessory.layerIndex);
}

/**
 * 收集数据
 */
AccessoryInfoDlg.collectData = function() {
    this
    .set('accessoryId')
    .set('procId')
    .set('name')
    .set('accessoryDesc')
    .set('width')
    .set('length')
    .set('thickness')
    .set('measureUnit')
    .set('unitWeight')
    .set('weight')
    .set('createTime')
    .set('isValidate')
    .set('lastUpdate')
    .set('brandId')
    .set('supplierId')
    .set('materialThumb')
    .set('comment');
}

/**
 * 提交添加
 */
AccessoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/accessory/add", function(data){
        Feng.success("添加成功!");
        window.parent.Accessory.table.refresh();
        AccessoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.accessoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AccessoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/accessory/update", function(data){
        Feng.success("修改成功!");
        window.parent.Accessory.table.refresh();
        AccessoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.accessoryInfoData);
    ajax.start();
}

$(function() {

    if($("#procIdValue").val() == undefined){
        $("#procId").val(0);
    }else{
        $("#procId").val($("#procIdValue").val());
    }

    if($("#measureUnitValue").val() == undefined){
        $("#measureUnit").val(0);
    }else{
        $("#measureUnit").val($("#measureUnitValue").val());
    }

    if($("#unitWeightValue").val() == undefined){
        $("#unitWeight").val(0);
    }else{
        $("#unitWeight").val($("#unitWeightValue").val());
    }

    if($("#isValidateValue").val() == undefined){
        $("#isValidate").val(0);
    }else{
        $("#isValidate").val($("#isValidateValue").val());
    }

    if($("#brandIdValue").val() == undefined){
        $("#brandId").val(0);
    }else{
        $("#brandId").val($("#brandIdValue").val());
    }

    if($("#supplierIdValue").val() == undefined){
        $("#supplierId").val(0);
    }else{
        $("#supplierId").val($("#supplierIdValue").val());
    }

});
