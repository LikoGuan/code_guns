/**
 * 初始化工艺管理详情对话框
 */
var ProcessingInfoDlg = {
    processingInfoData : {}
};

/**
 * 清除数据
 */
ProcessingInfoDlg.clearData = function() {
    this.processingInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProcessingInfoDlg.set = function(key, val) {
    this.processingInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProcessingInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ProcessingInfoDlg.close = function() {
    parent.layer.close(window.parent.Processing.layerIndex);
}

/**
 * 收集数据
 */
ProcessingInfoDlg.collectData = function() {
    this
    .set('procId')
    .set('name')
    .set('procDesc')
    .set('comment');
}

/**
 * 提交添加
 */
ProcessingInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/processing/add", function(data){
        Feng.success("添加成功!");
        window.parent.Processing.table.refresh();
        ProcessingInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.processingInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ProcessingInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/processing/update", function(data){
        Feng.success("修改成功!");
        window.parent.Processing.table.refresh();
        ProcessingInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.processingInfoData);
    ajax.start();
}

$(function() {

});
