/**
 * 组织管理初始化
 */
var Organization = {
    id: "OrganizationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Organization.initColumn = function () {
	return [
	        {field: 'selectItem', radio: true},
	            {title: '主键id', field: 'id', align: 'center', valign: 'middle'},
	            {title: '简称', field: 'simplename', align: 'center', valign: 'middle'},
	            {title: '全称', field: 'fullname', align: 'center', valign: 'middle'},
	            {title: '联系人', field: 'contact_person', align: 'center', valign: 'middle'},
	            {title: '联系电话', field: 'contact_phone', align: 'center', valign: 'middle'},
	            {title: '排序', field: 'num', align: 'center', valign: 'middle'}
	    ];
};

/**
 * 检查是否选中
 */
Organization.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	Organization.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加组织
 */
Organization.openAddOrganization = function () {
    var index = layer.open({
        type: 2,
        title: '添加组织',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/organization/organization_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看组织详情
 */
Organization.openOrganizationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '组织管理详情',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/organization/organization_update/' + Organization.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除组织
 */
Organization.delete = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/organization/delete", function () {
                Feng.success("删除成功!");
                Organization.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("organizationId", Organization.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否刪除该组织?", operation);
    }
};

/**
 * 查询组织列表
 */
Organization.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Organization.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Organization.initColumn();
    var table = new BSTreeTable(Organization.id, "/organization/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("pid");
    table.setExpandAll(true);
    table.init();
    Organization.table = table;
});
