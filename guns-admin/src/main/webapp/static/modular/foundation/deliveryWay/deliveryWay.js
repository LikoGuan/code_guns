/**
 * 物流方式管理初始化
 */
var DeliveryWay = {
    id: "DeliveryWayTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DeliveryWay.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '组织名称', field: 'orgName', visible: true, align: 'center', valign: 'middle'},
            {title: '服务区域', field: 'districtName', visible: true, align: 'center', valign: 'middle'},
            {title: '服务名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '服务代码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort_order', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
DeliveryWay.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DeliveryWay.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加物流方式
 */
DeliveryWay.openAddDeliveryWay = function () {
    var index = layer.open({
        type: 2,
        title: '添加物流方式',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/deliveryWay/deliveryWay_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看物流方式详情
 */
DeliveryWay.openDeliveryWayDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '物流方式详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/deliveryWay/deliveryWay_update/' + DeliveryWay.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 显示组织选择的树
 *
 * @returns
 */
DeliveryWay.showOrganizationSelectTree = function () {
	Feng.showInputTree("orgName", "organizationContent");
};

DeliveryWay.onClickOrganization = function (e, treeId, treeNode) {
    $("#orgName").attr("value", instance.getSelectedVal());
    $("#orgid").attr("value", treeNode.id);
};

/**
 * 删除物流方式
 */
DeliveryWay.delete = function () {
    if (this.check()) {
    	var operation = function() {
          var ajax = new $ax(Feng.ctxPath + "/deliveryWay/delete", function (data) {
              Feng.success("删除成功!");
              DeliveryWay.table.refresh();
          }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
          });
          ajax.set("deliveryWayId", DeliveryWay.seItem.id);
          ajax.start();
    	};
    	
    	Feng.confirm("是否刪除该记录?", operation);
    }
};

/**
 * 查询物流方式列表
 */
DeliveryWay.search = function () {
    var queryData = {};
    queryData['orgid'] = $("#orgid").val();
    DeliveryWay.table.refresh({query: queryData});
};


DeliveryWay.formParams = function() {
    var queryData = {};
    queryData['orgid'] = $("#orgid").val();
    return queryData;
}

$(function () {
    var defaultColunms = DeliveryWay.initColumn();
    var table = new BSTable(DeliveryWay.id, "/deliveryWay/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(DeliveryWay.formParams());
    DeliveryWay.table = table.init();
    
    var ztree = new $ZTree("organizationContentTree", "/organization/tree");
    ztree.bindOnClick(DeliveryWay.onClickOrganization);
    ztree.init();
    instance = ztree;
});
