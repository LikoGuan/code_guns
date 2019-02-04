/**
 * 互斥工艺管理管理初始化
 */
var ProcessingExcl = {
    id: "ProcessingExclTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ProcessingExcl.initColumn = function () {
//    return [
//        {field: 'selectItem', radio: true},
//            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
//            {title: '工艺A', field: 'procaId', visible: true, align: 'center', valign: 'middle'},
//            {title: '工艺B', field: 'procbId', visible: true, align: 'center', valign: 'middle'},
//            {title: '备注', field: 'comment', visible: true, align: 'center', valign: 'middle'}
//    ];
    return [
            {field: 'selectItem', radio: true},
                {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
                {title: '工艺A', field: 'procAName', visible: true, align: 'center', valign: 'middle'},
                {title: '工艺B', field: 'procBName', visible: true, align: 'center', valign: 'middle'},
                {title: '备注', field: 'comment', visible: true, align: 'center', valign: 'middle'}
        ];
};

/**
 * 检查是否选中
 */
ProcessingExcl.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ProcessingExcl.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加互斥工艺管理
 */
ProcessingExcl.openAddProcessingExcl = function () {
    var index = layer.open({
        type: 2,
        title: '添加互斥工艺管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/processingExcl/processingExcl_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看互斥工艺管理详情
 */
ProcessingExcl.openProcessingExclDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '互斥工艺管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/processingExcl/processingExcl_update/' + ProcessingExcl.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除互斥工艺管理
 */
ProcessingExcl.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/processingExcl/delete", function (data) {
            Feng.success("删除成功!");
            ProcessingExcl.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("processingExclId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询互斥工艺管理列表
 */
ProcessingExcl.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ProcessingExcl.table.refresh({query: queryData});
};

/**
 * 获取processing列表
 */
//ProcessingExcl.getProcessingList = function () {
//    var ajax = new $ax(
//        Feng.ctxPath + "/processing/list",
//        function (data) {
//            Feng.success(data);
//        },
//        function (data) {
//            Feng.error("获取工艺列表失败!" + data.responseJSON.message + "!");
//        }
//    );
//    ajax.start();
//}

$(function () {
//    ProcessingExcl.getProcessingList();
    var defaultColunms = ProcessingExcl.initColumn();
    var table = new BSTable(ProcessingExcl.id, "/processingExcl/list", defaultColunms);
    table.setPaginationType("client");
    ProcessingExcl.table = table.init();
});
