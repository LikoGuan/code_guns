/**
 * 供应商管理管理初始化
 */
var Suppliers = {
    id: "SuppliersTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Suppliers.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {
                title: '主键id',
                field: 'suppliersId',
                visible: true,
                align: 'center',
                valign: 'middle'
            },
            {
                title: '供应商名称',
                field: 'suppliersName',
                visible: true,
                align: 'center',
                valign: 'middle'
            },
            {
                title: '供应商描述',
                field: 'suppliersDesc',
                visible: true,
                align: 'center',
                valign: 'middle'
            },
            {
                title: '有效',
                field: 'isValidate',
                visible: true,
                align: 'center',
                valign: 'middle',
                formatter: function(v, r, i) {
                    if (v == "1") {
                        return "是";
                    } else {
                        return "否";
                    }
                }
            }
    ];
};

/**
 * 检查是否选中
 */
Suppliers.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Suppliers.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加供应商管理
 */
Suppliers.openAddSuppliers = function () {
    var index = layer.open({
        type: 2,
        title: '添加供应商管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/suppliers/suppliers_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看供应商管理详情
 */
Suppliers.openSuppliersDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '供应商管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/suppliers/suppliers_update/' + Suppliers.seItem.suppliersId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除供应商管理
 */
Suppliers.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/suppliers/delete", function (data) {
            Feng.success("删除成功!");
            Suppliers.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("suppliersId",this.seItem.suppliersId);
        ajax.start();
    }
};

/**
 * 查询供应商管理列表
 */
Suppliers.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Suppliers.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Suppliers.initColumn();
    var table = new BSTable(Suppliers.id, "/suppliers/list", defaultColunms);
    table.setPaginationType("client");
    Suppliers.table = table.init();
});
