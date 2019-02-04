/**
 * 工艺管理管理初始化
 */
var Processing = {
    id: "ProcessingTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Processing.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '工艺id', field: 'procId', visible: true, align: 'center', valign: 'middle'},
            {title: '工艺名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '工艺描述', field: 'procDesc', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'comment', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Processing.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Processing.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加工艺管理
 */
Processing.openAddProcessing = function () {
    var index = layer.open({
        type: 2,
        title: '添加工艺管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/processing/processing_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看工艺管理详情
 */
Processing.openProcessingDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '工艺管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/processing/processing_update/' + Processing.seItem.procId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除工艺管理
 */
Processing.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/processing/delete", function (data) {
            Feng.success("删除成功!");
            Processing.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("processingId",this.seItem.procId);
        ajax.start();
    }
};

/**
 * 查询工艺管理列表
 */
Processing.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Processing.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Processing.initColumn();
    var table = new BSTable(Processing.id, "/processing/list", defaultColunms);
    table.setPaginationType("client");
    Processing.table = table.init();
});
