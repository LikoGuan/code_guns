/**
 * 客户等级管理初始化
 */
var ClientRank = {
    id: "ClientRankTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ClientRank.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '客户名称', field: 'orgName', visible: true, align: 'center', valign: 'middle'},
            {title: '等级名称', field: 'rank_name', visible: true, align: 'center', valign: 'middle'},
            {title: '折扣', field: 'discount', visible: true, align: 'center', valign: 'middle'},
            {title: '客户类型', field: 'clientTypeName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ClientRank.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ClientRank.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加客户等级
 */
ClientRank.openAddClientRank = function () {
    var index = layer.open({
        type: 2,
        title: '添加客户等级',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/clientRank/clientRank_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看客户等级详情
 */
ClientRank.openClientRankDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '客户等级详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/clientRank/clientRank_update/' + ClientRank.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除客户等级
 */
ClientRank.delete = function () {
    if (this.check()) {
        var operation = function () {
        	var ajax = new $ax(Feng.ctxPath + "/clientRank/delete", function (data) {
               Feng.success("删除成功!");
               ClientRank.table.refresh();
            }, function (data) {
               Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("clientRankId", ClientRank.seItem.id);
            ajax.start();
        };
        
        Feng.confirm("是否刪除该记录?", operation);
    }
};

/**
 * 查询客户等级列表
 */
ClientRank.search = function () {
	ClientRank.table.refresh({query: ClientRank.formParams()});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
ClientRank.formParams = function() {
    var queryData = {};
    queryData['name'] = $("#name").val();
    return queryData;
}

$(function () {
    var defaultColunms = ClientRank.initColumn();
    var table = new BSTable(ClientRank.id, "/clientRank/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(ClientRank.formParams());
    ClientRank.table = table.init();
});
