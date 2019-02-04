/**
 * 初始化材料分类详情对话框
 */
var CategoryInfoDlg = {
    categoryInfoData : {},
    zTreeInstance : null,
    validateFields: {
    	name: {
            validators: {
                notEmpty: {
                    message: '分类名称不能为空'
                }
            }
        },
        sortOrder: {
            validators: {
                notEmpty: {
                    message: '分类排序不能为空'
                },
                integer: {
                	message: '分类排序必须为正整数'
                }
            }
        },
        pName: {
            validators: {
                notEmpty: {
                    message: '父级分类不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
CategoryInfoDlg.clearData = function() {
    this.categoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CategoryInfoDlg.set = function(key, val) {
    this.categoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CategoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CategoryInfoDlg.close = function() {
    parent.layer.close(window.parent.Category.layerIndex);
}

CategoryInfoDlg.onClickCategory = function(e, treeId, treeNode) {
    $("#pName").attr("value", CategoryInfoDlg.zTreeInstance.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
}

/**
 * 显示分类选择的树
 *
 * @returns
 */
CategoryInfoDlg.showCategorySelectTree = function() {
    var pName = $("#pName");
    var pNameOffset = $("#pName").offset();
    $("#parentCategoryMenu").css({
        left : pNameOffset.left + "px",
        top : pNameOffset.top + pName.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏分类选择的树
 */
CategoryInfoDlg.hideCategorySelectTree = function() {
    $("#parentCategoryMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}

/**
 * 收集数据
 */
CategoryInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('keywords')
    .set('desc')
    .set('pid')
    .set('sortOrder')
    .set('isShow');
}

/**
 * 验证数据
 */
CategoryInfoDlg.validate = function () {
    $('#categoryInfoForm').data("bootstrapValidator").resetForm();
    $('#categoryInfoForm').bootstrapValidator('validate');
    return $("#categoryInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
CategoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/category/add", function(data){
        Feng.success("添加成功!");
        window.parent.Category.table.refresh();
        CategoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.categoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CategoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/category/update", function(data){
        Feng.success("修改成功!");
        window.parent.Category.table.refresh();
        CategoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.categoryInfoData);
    ajax.start();
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentCategoryMenu" || $(
            event.target).parents("#parentCategoryMenu").length > 0)) {
    	CategoryInfoDlg.hideCategorySelectTree();
    }
}

$(function() {
	Feng.initValidator("categoryInfoForm", CategoryInfoDlg.validateFields);

    var ztree = new $ZTree("parentCategoryMenuTree", "/category/tree");
    ztree.bindOnClick(CategoryInfoDlg.onClickCategory);
    ztree.init();
    CategoryInfoDlg.zTreeInstance = ztree;
});
