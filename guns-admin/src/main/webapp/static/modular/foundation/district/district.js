/**
 * 区域配置管理初始化
 */
var District = {
    id: "DistrictTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
District.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '地区名称', field: 'district_name', visible: true, align: 'center', valign: 'middle'},
            {title: '地区级别', field: 'level', visible: true, align: 'center', valign: 'middle'},
            {title: '排序顺序', field: 'sort_order', visible: true, align: 'center', valign: 'middle'}
        ];
};

/**
 * 检查是否选中
 */
District.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        District.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加区域配置
 */
District.openAddDistrict = function () {
    var index = layer.open({
        type: 2,
        title: '添加区域配置',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/district/district_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看区域配置详情
 */
District.openDistrictDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '区域配置详情',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/district/district_update/' + District.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除区域配置
 */
District.delete = function () {
    if (this.check()) {
    	var operation = function() {
          var ajax = new $ax(Feng.ctxPath + "/district/delete", function (data) {
            Feng.success("删除成功!");
            District.table.refresh();
          }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
          });
          ajax.set("districtId", District.seItem.id);
          ajax.start();
    	};
 	
    	Feng.confirm("是否刪除该记录?", operation);
    }
};

/**
 * 查询区域配置列表
 */
District.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    District.table.refresh({query: queryData});
};

$(function () {
	var defaultColunms = District.initColumn();
    var table = new BSTreeTable(District.id, "/district/list", defaultColunms);
    table.setExpandColumn(1);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("pid");
    table.setExpandAll(true);
    table.init();
    District.table = table;
});
