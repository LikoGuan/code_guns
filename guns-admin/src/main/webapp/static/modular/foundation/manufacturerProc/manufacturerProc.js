/**
 * 生产商工艺管理管理初始化
 */
var ManufacturerProc = {
    id: "ManufacturerProcTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ManufacturerProc.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '组织', field: 'orgName', visible: true, align: 'center', valign: 'middle'},
            {title: '工艺', field: 'procName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ManufacturerProc.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ManufacturerProc.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加生产商工艺管理
 */
ManufacturerProc.openAddManufacturerProc = function () {
    var index = layer.open({
        type: 2,
        title: '添加生产商工艺管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/manufacturerProc/manufacturerProc_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看生产商工艺管理详情
 */
ManufacturerProc.openManufacturerProcDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '生产商工艺管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/manufacturerProc/manufacturerProc_update/' + ManufacturerProc.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除生产商工艺管理
 */
ManufacturerProc.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/manufacturerProc/delete", function (data) {
            Feng.success("删除成功!");
            ManufacturerProc.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("manufacturerProcId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询生产商工艺管理列表
 */
ManufacturerProc.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ManufacturerProc.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ManufacturerProc.initColumn();
    var table = new BSTable(ManufacturerProc.id, "/manufacturerProc/list", defaultColunms);
    table.setPaginationType("client");
    ManufacturerProc.table = table.init();
});
