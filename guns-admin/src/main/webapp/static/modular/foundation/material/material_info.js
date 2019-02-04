/**
 * 初始化材料清单详情对话框
 */
var MaterialInfoDlg = {
    materialInfoData : {},
    zTreeInstance : null,
    validateFields: {
    	categoryName: {
            validators: {
                notEmpty: {
                    message: '分类名称不能为空'
                }
            }
        },
        materialName: {
            validators: {
                notEmpty: {
                    message: '分类名称不能为空'
                }
            }
        },
        measureUnit: {
            validators: {
                notEmpty: {
                    message: '计量单位不能为空'
                }
            }
        },
        width: {
            validators: {
            	digits: {
                	message: '宽度必须为数字'
                }
            }
        },
        length: {
            validators: {
            	digits: {
                	message: '长度必须为数字'
                }
            }
        },
        thickness: {
            validators: {
            	digits: {
                	message: '厚度必须为数字'
                }
            }
        },
        unitWeight: {
            validators: {
            	digits: {
                	message: '重量单位不能为空'
                }
            }
        },
        packId: {
            validators: {
                notEmpty: {
                    message: '包装方式不能为空'
                }
            }
        },
        brandId: {
            validators: {
                notEmpty: {
                    message: '品牌不能为空'
                }
            }
        },
        supplierId: {
            validators: {
                notEmpty: {
                    message: '生产商不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
MaterialInfoDlg.clearData = function() {
    this.materialInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MaterialInfoDlg.set = function(key, val) {
    this.materialInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MaterialInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MaterialInfoDlg.close = function() {
    parent.layer.close(window.parent.Material.layerIndex);
}

/**
 * 收集数据
 */
MaterialInfoDlg.collectData = function() {
    this
    .set('materialId')
    .set('catId')
    .set('materialName')
    .set('measureUnit')
    .set('keywords')
    .set('materialDesc')
    .set('width')
    .set('length')
    .set('thickness')
    .set('unitWeight')
    .set('packId')
    .set('isReal')
    .set('isMerchandise')
    .set('createTime')
    .set('isValidate')
    .set('lastUpdate')
    .set('brandId')
    .set('supplierId')
    .set('materialThumb')
    .set('comment');
}

/**
 * 验证数据
 */
MaterialInfoDlg.validate = function () {
    $('#materialInfoForm').data("bootstrapValidator").resetForm();
    $('#materialInfoForm').bootstrapValidator('validate');
    return $("#materialInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 显示分类选择的树
 *
 * @returns
 */
MaterialInfoDlg.showCategorySelectTree = function () {
	var pName = $("#categoryName");
    var pNameOffset = $("#categoryName").offset();
    $("#categoryMenu").css({
        left : pNameOffset.left + "px",
        top : pNameOffset.top + pName.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏分类选择的树
 */
MaterialInfoDlg.hideCategorySelectTree = function() {
    $("#categoryMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}

MaterialInfoDlg.onClickCategory = function(e, treeId, treeNode) {
    $("#categoryName").attr("value", MaterialInfoDlg.zTreeInstance.getSelectedVal());
    $("#catId").attr("value", treeNode.id);
}

/**
 * 提交添加
 */
MaterialInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/material/add", function(data){
        Feng.success("添加成功!");
        window.parent.Material.table.refresh();
        MaterialInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.materialInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MaterialInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/material/update", function(data){
        Feng.success("修改成功!");
        window.parent.Material.table.refresh();
        MaterialInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.materialInfoData);
    ajax.start();
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "categoryMenu" || $(
            event.target).parents("#categoryMenu").length > 0)) {
    	MaterialInfoDlg.hideCategorySelectTree();
    }
}

$(function() {
	Feng.initValidator("materialInfoForm", MaterialInfoDlg.validateFields);
	
	$("#measureUnit").val($("#measureUnitValue").val());
	$("#unitWeight").val($("#unitWeightValue").val());
	$("#packId").val($("#packIdValue").val());
	$("#isReal").val($("#isRealValue").val());
	$("#isMerchandise").val($("#isMerchandiseValue").val());
	$("#brandId").val($("#brandIdValue").val());
	$("#supplierId").val($("#supplierIdValue").val());

    var ztree = new $ZTree("categoryMenuTree", "/category/tree");
    ztree.bindOnClick(MaterialInfoDlg.onClickCategory);
    ztree.init();
    MaterialInfoDlg.zTreeInstance = ztree;
});
