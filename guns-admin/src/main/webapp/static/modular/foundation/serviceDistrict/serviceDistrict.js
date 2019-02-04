/**
 * 服务区域管理初始化
 */
var ServiceDistrict = {
    id: "ServiceDistrictTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ServiceDistrict.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '组织名称', field: 'orgName', visible: true, align: 'center', valign: 'middle'},
            {title: '地区名称', field: 'districtName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ServiceDistrict.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ServiceDistrict.seItem = selected[0];
        return true;
    }
};

/**
 * 显示组织选择的树
 *
 * @returns
 */
ServiceDistrict.showOrganizationSelectTree = function () {
	Feng.showInputTree("orgName", "organizationContent");
};


/**
 * 隐藏部门选择的树
 */
ServiceDistrict.hideOrganizationSelectTree = function () {
    $("#organizationContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 显示地区选择的树
 *
 * @returns
 */
ServiceDistrict.showDistrictSelectTree = function () {
	Feng.showInputTree("districtName", "districtContent");
};


/**
 * 隐藏地区选择的树
 */
ServiceDistrict.hideDistrictSelectTree = function () {
    $("#districtContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

ServiceDistrict.onClickOrganization = function (e, treeId, treeNode) {
    $("#orgName").attr("value", instance.getSelectedVal());
    $("#orgid").attr("value", treeNode.id);
};

ServiceDistrict.onClickDistrict = function (e, treeId, treeNode) {
    $("#districtName").attr("value", districtInstance.getSelectedVal());
    $("#districtId").attr("value", treeNode.id);
};

/**
 * 点击添加服务区域
 */
ServiceDistrict.openAddServiceDistrict = function () {
    var index = layer.open({
        type: 2,
        title: '添加服务区域',
        area: ['800px', '300px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/serviceDistrict/serviceDistrict_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看服务区域详情
 */
ServiceDistrict.openServiceDistrictDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '服务区域详情',
            area: ['800px', '300px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/serviceDistrict/serviceDistrict_update/' + ServiceDistrict.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除服务区域
 */
ServiceDistrict.delete = function () {
    if (this.check()) {
    	var operation = function() {
          var ajax = new $ax(Feng.ctxPath + "/serviceDistrict/delete", function (data) {
              Feng.success("删除成功!");
              ServiceDistrict.table.refresh();
          }, function (data) {
              Feng.error("删除失败!" + data.responseJSON.message + "!");
          });
          ajax.set("serviceDistrictId",ServiceDistrict.seItem.id);
          ajax.start();
    	};
    	
    	Feng.confirm("是否刪除该记录?", operation);
    }
};

/**
 * 查询客户等级列表
 */
ServiceDistrict.search = function () {
	ServiceDistrict.table.refresh({query: ServiceDistrict.formParams()});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
ServiceDistrict.formParams = function() {
    var queryData = {};
    queryData['orgid'] = $("#orgid").val();
    return queryData;
}

$(function () {
    var defaultColunms = ServiceDistrict.initColumn();
    var table = new BSTable(ServiceDistrict.id, "/serviceDistrict/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(ServiceDistrict.formParams());
    ServiceDistrict.table = table.init();
    
    var ztree = new $ZTree("organizationContentTree", "/organization/tree");
    ztree.bindOnClick(ServiceDistrict.onClickOrganization);
    ztree.init();
    instance = ztree;
    
    var defaultColunms = ServiceDistrict.initColumn();
    var table = new BSTable(ClientRank.id, "/serviceDistrict/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(ServiceDistrict.formParams());
    ServiceDistrict.table = table.init();
});
