/**
 * 初始化材料包装管理详情对话框
 */
var PackageInfoDlg = {
    packageInfoData : {}
};

/**
 * 清除数据
 */
PackageInfoDlg.clearData = function() {
    this.packageInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PackageInfoDlg.set = function(key, val) {
    this.packageInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PackageInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PackageInfoDlg.close = function() {
    parent.layer.close(window.parent.Package.layerIndex);
}

/**
 * 收集数据
 */
PackageInfoDlg.collectData = function() {
    this
    .set('packId')
    .set('packName')
    .set('packImg')
    .set('measureUnit')
    .set('unitFee')
    .set('packageLimitation')
    .set('packDesc');
}

/**
 * 提交添加
 */
PackageInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/package/add", function(data){
        Feng.success("添加成功!");
        window.parent.Package.table.refresh();
        PackageInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.packageInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PackageInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/package/update", function(data){
        Feng.success("修改成功!");
        window.parent.Package.table.refresh();
        PackageInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.packageInfoData);
    ajax.start();
}

$(function() {
if($("#measureUnitValue").val() == undefined){
        $("#measureUnit").val(0);
    }else{
        $("#measureUnit").val($("#measureUnitValue").val());
    }
});
