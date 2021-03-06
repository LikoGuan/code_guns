/**
 * 材料属性管理管理初始化
 */
var MaterialAttr = {
    id: "MaterialAttrTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MaterialAttr.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},

            {title: '材料id', field: 'materialId', visible: true, align: 'center', valign: 'middle'},
            {title: '属性名称', field: 'materialAttr', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'comment', visible: true, align: 'center', valign: 'middle'}
    ];
};
//{title: '主键id', field: 'attrId', visible: true, align: 'center', valign: 'middle'},

/**
 * 检查是否选中
 */
MaterialAttr.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MaterialAttr.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加材料属性管理
 */
MaterialAttr.openAddMaterialAttr = function () {
    var index = layer.open({
        type: 2,
        title: '添加材料属性管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/materialAttr/materialAttr_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看材料属性管理详情
 */
MaterialAttr.openMaterialAttrDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '材料属性管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/materialAttr/materialAttr_update/' + MaterialAttr.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除材料属性管理
 */
MaterialAttr.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/materialAttr/delete", function (data) {
            Feng.success("删除成功!");
            MaterialAttr.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("materialAttrId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询材料属性管理列表
 */
MaterialAttr.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MaterialAttr.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MaterialAttr.initColumn();
    var table = new BSTable(MaterialAttr.id, "/materialAttr/list", defaultColunms);
    table.setPaginationType("client");
    MaterialAttr.table = table.init();
});
