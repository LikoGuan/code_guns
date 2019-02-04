/**
 * 工艺配件管理管理初始化
 */
var Accessory = {
    id: "AccessoryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Accessory.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '附件id', field: 'accessoryId', visible: true, align: 'center', valign: 'middle'},
            {title: '工艺', field: 'procName', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '配件描述', field: 'accessoryDesc', visible: true, align: 'center', valign: 'middle'},
            {title: '宽度', field: 'width', visible: true, align: 'center', valign: 'middle'},
            {title: '长度', field: 'length', visible: true, align: 'center', valign: 'middle'},
            {title: '厚度', field: 'thickness', visible: true, align: 'center', valign: 'middle'},
            {title: '计量单位', field: 'measureUnit', visible: true, align: 'center', valign: 'middle'},
            {title: '单位重量', field: 'unitWeight', visible: true, align: 'center', valign: 'middle'},
            {title: '重量', field: 'weight', visible: true, align: 'center', valign: 'middle'},
            {title: '添加时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否有效', field: 'isValidate', visible: true, align: 'center', valign: 'middle', formatter: function(v, r, i) {if (v == "1") {return "是";} else {return "否";}}},
            {title: '最后更新时间', field: 'lastUpdate', visible: true, align: 'center', valign: 'middle'},
            {title: '品牌', field: 'brand', visible: true, align: 'center', valign: 'middle'},
            {title: '生产商', field: 'supplier', visible: true, align: 'center', valign: 'middle'},
            {title: '缩略图', field: 'materialThumb', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'comment', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Accessory.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Accessory.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加工艺配件管理
 */
Accessory.openAddAccessory = function () {
    var index = layer.open({
        type: 2,
        title: '添加工艺配件管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/accessory/accessory_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看工艺配件管理详情
 */
Accessory.openAccessoryDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '工艺配件管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/accessory/accessory_update/' + Accessory.seItem.accessoryId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除工艺配件管理
 */
Accessory.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/accessory/delete", function (data) {
            Feng.success("删除成功!");
            Accessory.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("accessoryId",this.seItem.accessoryId);
        ajax.start();
    }
};

/**
 * 查询工艺配件管理列表
 */
Accessory.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Accessory.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Accessory.initColumn();
    var table = new BSTable(Accessory.id, "/accessory/list", defaultColunms);
    table.setPaginationType("client");
    Accessory.table = table.init();
});
