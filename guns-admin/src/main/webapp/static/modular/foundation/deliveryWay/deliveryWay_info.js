/**
 * 初始化物流方式详情对话框
 */
var DeliveryWayInfoDlg = {
    deliveryWayInfoData : {},
    validateFields: {
    	name: {
            validators: {
                notEmpty: {
                    message: '物流方式名称不能为空'
                }
            }
        },
        code: {
            validators: {
                notEmpty: {
                    message: '物流方式代码不能为空'
                }
            }
        },
        serviceDistId: {
            validators: {
                notEmpty: {
                    message: '服务区域不能为空'
                }
            }
        },
        sortOrder: {
            validators: {
                notEmpty: {
                    message: '物流方式排序不能为空'
                },
                integer: {
                	message: '物流方式排序必须为正整数'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
DeliveryWayInfoDlg.clearData = function() {
    this.deliveryWayInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DeliveryWayInfoDlg.set = function(key, val) {
    this.deliveryWayInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DeliveryWayInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DeliveryWayInfoDlg.close = function() {
    parent.layer.close(window.parent.DeliveryWay.layerIndex);
}

/**
 * 收集数据
 */
DeliveryWayInfoDlg.collectData = function() {
    this
    .set('id')
    .set('serviceDistId')
    .set('code')
    .set('name')
    .set('desc')
    .set('sortOrder');
}

/**
 * 提交添加
 */
DeliveryWayInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/deliveryWay/add", function(data){
        Feng.success("添加成功!");
        window.parent.DeliveryWay.table.refresh();
        DeliveryWayInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.deliveryWayInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DeliveryWayInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/deliveryWay/update", function(data){
        Feng.success("修改成功!");
        window.parent.DeliveryWay.table.refresh();
        DeliveryWayInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.deliveryWayInfoData);
    ajax.start();
}


DeliveryWayInfoDlg.validate = function () {
    $('#deliveryWayForm').data("bootstrapValidator").resetForm();
    $('#deliveryWayForm').bootstrapValidator('validate');
    return $("#deliveryWayForm").data('bootstrapValidator').isValid();
};

$(function() {
	Feng.initValidator("deliveryWayForm", DeliveryWayInfoDlg.validateFields);
	
	if ($("#serviceDistIdValue").val() != null) {
	   $("#serviceDistId").val($("#serviceDistIdValue").val());
	}
});
