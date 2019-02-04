/**
 * 制造商等级管理初始化
 */
var ManufacturerRank = {
    id: "ManufacturerRankTable",	// 表格id
    seItem: null,		// 选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ManufacturerRank.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '制造商名称', field: 'orgName', visible: true, align: 'center', valign: 'middle'},
            {title: '等级名称', field: 'rank_name', visible: true, align: 'center', valign: 'middle'},
            {title: '制造商等级', field: 'rank', visible: true, align: 'center', valign: 'middle'},
            {title: '制造商类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ManufacturerRank.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ManufacturerRank.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加制造商等级
 */
ManufacturerRank.openAddManufacturerRank = function () {
    var index = layer.open({
        type: 2,
        title: '添加制造商',
        area: ['830px', '420px'], // 宽高
        fix: false, // 不固定
        maxmin: true,
        content: Feng.ctxPath + '/manufacturerRank/manufacturerRank_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看制造商等级详情
 */
ManufacturerRank.openManufacturerRankDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '制造商等级详情',
            area: ['830px', '420px'], // 宽高
            fix: false, // 不固定
            maxmin: true,
            content: Feng.ctxPath + '/manufacturerRank/manufacturerRank_update/' + ManufacturerRank.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除制造商等级
 */
ManufacturerRank.delete = function () {
    if (this.check()) {
    	var operation = function() {
          var ajax = new $ax(Feng.ctxPath + "/manufacturerRank/delete", function (data) {
            Feng.success("删除成功!");
            ManufacturerRank.table.refresh();
          }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
          });
          ajax.set("manufacturerRankId", ManufacturerRank.seItem.id);
          ajax.start();
        };
        Feng.confirm("是否刪除该记录?", operation);
    }
};

/**
 * 查询制造商等级列表
 */
ManufacturerRank.search = function () {
	ManufacturerRank.table.refresh({query: ManufacturerRank.formParams()});
};

/**
 * 查询表单提交参数对象
 * 
 * @returns {{}}
 */
ManufacturerRank.formParams = function() {
    var queryData = {};
    queryData['name'] = $("#name").val();
    return queryData;
}

$(function () {
    var defaultColunms = ManufacturerRank.initColumn();
    var table = new BSTable(ManufacturerRank.id, "/manufacturerRank/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(ManufacturerRank.formParams());
    ManufacturerRank.table = table.init();
});
