/**
 * 材料清单管理初始化
 */
var Material = {
    id: "MaterialTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Material.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '分类名称', field: 'cat_name', visible: true, align: 'center', valign: 'middle'},
            {title: '材料名称', field: 'material_name', visible: true, align: 'center', valign: 'middle'},
            {title: '计量单位', field: 'measureUnit_name', visible: true, align: 'center', valign: 'middle'},
            {title: '关键词', field: 'keywords', visible: true, align: 'center', valign: 'middle'},
            {title: '宽度', field: 'width', visible: true, align: 'center', valign: 'middle'},
            {title: '长度', field: 'length', visible: true, align: 'center', valign: 'middle'},
            {title: '厚度', field: 'thickness', visible: true, align: 'center', valign: 'middle'},
            {title: '单位重量', field: 'unitWeight_name', visible: true, align: 'center', valign: 'middle'},
            {title: '包装方式', field: 'package', visible: true, align: 'center', valign: 'middle'},
            {title: '是否实物', field: 'is_real', visible: true, align: 'center', valign: 'middle'},
            {title: '是否为普通商品', field: 'is_merchandise', visible: true, align: 'center', valign: 'middle'},
            {title: '品牌', field: 'brand', visible: true, align: 'center', valign: 'middle'},
            {title: '生产商', field: 'supplier', visible: true, align: 'center', valign: 'middle'},
            {title: '缩略图', field: 'materialThumb', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Material.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Material.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加材料清单
 */
Material.openAddMaterial = function () {
    var index = layer.open({
        type: 2,
        title: '添加材料清单',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/material/material_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看材料清单详情
 */
Material.openMaterialDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '材料清单详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/material/material_update/' + Material.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除材料清单
 */
Material.delete = function () {
    if (this.check()) {
    	var operation = function() {
          var ajax = new $ax(Feng.ctxPath + "/material/delete", function (data) {
             Feng.success("删除成功!");
             Material.table.refresh();
          }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
          });
          ajax.set("materialId",this.seItem.id);
          ajax.start();
    	};
    	
    	Feng.confirm("是否刪除该记录?", operation);
    }
};

/**
 * 查询材料清单列表
 */
Material.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    Material.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Material.formParams = function() {
    var queryData = {};
    queryData['name'] = $("#name").val();
    return queryData;
}

$(function () {
    var defaultColunms = Material.initColumn();
    var table = new BSTable(Material.id, "/material/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Material.formParams());
    Material.table = table.init();
});
