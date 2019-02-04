/**
 * 初始化互斥工艺管理详情对话框
 */
var ProcessingExclInfoDlg = {
    processingExclInfoData : {}
};

/**
 * 清除数据
 */
ProcessingExclInfoDlg.clearData = function() {
    this.processingExclInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProcessingExclInfoDlg.set = function(key, val) {
    this.processingExclInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProcessingExclInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ProcessingExclInfoDlg.close = function() {
    parent.layer.close(window.parent.ProcessingExcl.layerIndex);
}

/**
 * 收集数据
 */
ProcessingExclInfoDlg.collectData = function() {
    this
    .set('id')
    .set('procaId')
    .set('procbId')
    .set('comment');
}

/**
 * 提交添加
 */
ProcessingExclInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/processingExcl/add", function(data){
        Feng.success("添加成功!");
        window.parent.ProcessingExcl.table.refresh();
        ProcessingExclInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.processingExclInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ProcessingExclInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/processingExcl/update", function(data){
        Feng.success("修改成功!");
        window.parent.ProcessingExcl.table.refresh();
        ProcessingExclInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.processingExclInfoData);
    ajax.start();
}

$(function() {
    if($("#procaIdValue").val() == undefined){
        $("#procaId").val(0);
    }else{
        $("#procaId").val($("#procaIdValue").val());
    }

    if($("#procbIdValue").val() == undefined){
            $("#procbId").val(0);
        }else{
            $("#procbId").val($("#procbIdValue").val());
        }
});
