
-- ----------------------------
-- Table structure for client_rank
-- ----------------------------
DROP TABLE IF EXISTS `client_rank`;
CREATE TABLE `client_rank` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `client_id` INT NOT NULL COMMENT '客户编号',
  `rank_name` VARCHAR(100) NOT NULL COMMENT '等级名称',
  `discount` INT NOT NULL COMMENT '折扣(80表示为80%)',
  `client_type` INT NOT NULL DEFAULT 1 COMMENT '客户类型(1 企业，0 个人)',
  PRIMARY KEY (`id`));

-- ----------------------------
-- Menu initialization records for client_rank
-- ----------------------------
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1049768563787628546', 'clientRank', 'foundation', '[0],[foundation],', '客户等级', '', '/clientRank', '0', '2', '1', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1049768563787628547', 'clientRank_list', 'clientRank', '[0],[foundation],[clientRank],', '客户等级列表', '', '/clientRank/list', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1049768563787628548', 'clientRank_add', 'clientRank', '[0],[foundation],[clientRank],', '客户等级添加', '', '/clientRank/add', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1049768563787628549', 'clientRank_update', 'clientRank', '[0],[foundation],[clientRank],', '客户等级更新', '', '/clientRank/update', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1049768563787628550', 'clientRank_delete', 'clientRank', '[0],[foundation],[clientRank],', '客户等级删除', '', '/clientRank/delete', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1049768563787628551', 'clientRank_detail', 'clientRank', '[0],[foundation],[clientRank],', '客户等级详情', '', '/clientRank/detail', '99', '3', '0', NULL, '1', '0');
 
-- ----------------------------
-- Table structure for manufacturer_rank
-- ---------------------------- 
DROP TABLE IF EXISTS `manufacturer_rank`; 
CREATE TABLE `manufacturer_rank` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `manufacturer_id` INT NOT NULL COMMENT '生产商编号',
  `rank_name` VARCHAR(100) NOT NULL COMMENT '名称',
  `rank` INT NOT NULL COMMENT '制造商等级',
  `type_id` INT NOT NULL COMMENT '生产商类别Id',
  PRIMARY KEY (`id`));

-- ----------------------------
-- Menu initialization records for manufacturer_rank
-- ----------------------------
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1049877584213315585', 'manufacturerRank', 'foundation', '[0],[foundation],', '制造商等级', '', '/manufacturerRank', '1', '2', '1', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1049877584213315586', 'manufacturerRank_list', 'manufacturerRank', '[0],[foundation],[manufacturerRank],', '制造商等级列表', '', '/manufacturerRank/list', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1049877584213315587', 'manufacturerRank_add', 'manufacturerRank', '[0],[foundation],[manufacturerRank],', '制造商等级添加', '', '/manufacturerRank/add', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1049877584213315588', 'manufacturerRank_update', 'manufacturerRank', '[0],[foundation],[manufacturerRank],', '制造商等级更新', '', '/manufacturerRank/update', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1049877584213315589', 'manufacturerRank_delete', 'manufacturerRank', '[0],[foundation],[manufacturerRank],', '制造商等级删除', '', '/manufacturerRank/delete', '99', '3', '0', NULL, '1', '0');

  
-- ----------------------------
-- Table structure for manufacturer_type
-- ----------------------------  
DROP TABLE IF EXISTS `manufacturer_type`; 
CREATE TABLE `manufacturer_type` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `type_name` VARCHAR(100) NOT NULL COMMENT '名称',
  `type_desc` VARCHAR(45) NULL COMMENT '描述',
  PRIMARY KEY (`id`));
  
INSERT INTO `manufacturer_type` VALUES ('1', '制造商类型1', '');
INSERT INTO `manufacturer_type` VALUES ('2', '制造商类型2', '');
INSERT INTO `manufacturer_type` VALUES ('3', '制造商类型3', '');

-- ----------------------------
-- Table structure for service_district
-- ----------------------------
DROP TABLE IF EXISTS `service_district`;  
CREATE TABLE `service_district` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `orgid` INT NOT NULL COMMENT '组织id',
  `district_id` INT NOT NULL COMMENT '地区id',
  `comment` VARCHAR(255) NULL COMMENT '备注',
  PRIMARY KEY (`id`));

-- ----------------------------
-- Menu initialization records for service_district
-- ---------------------------- 
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051301596042813442', 'serviceDistrict', 'foundation', '[0],[foundation],', '服务区域', '', '/serviceDistrict', '2', '2', '1', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051301596042813443', 'serviceDistrict_list', 'serviceDistrict', '[0],[foundation],[serviceDistrict],', '服务区域列表', '', '/serviceDistrict/list', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051301596042813444', 'serviceDistrict_add', 'serviceDistrict', '[0],[foundation],[serviceDistrict],', '服务区域添加', '', '/serviceDistrict/add', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051301596042813445', 'serviceDistrict_update', 'serviceDistrict', '[0],[foundation],[serviceDistrict],', '服务区域更新', '', '/serviceDistrict/update', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051301596042813446', 'serviceDistrict_delete', 'serviceDistrict', '[0],[foundation],[serviceDistrict],', '服务区域删除', '', '/serviceDistrict/delete', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051301596042813447', 'serviceDistrict_detail', 'serviceDistrict', '[0],[foundation],[serviceDistrict],', '服务区域详情', '', '/serviceDistrict/detail', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051301596042813448', 'serviceDistrict_detail', 'serviceDistrict', '[0],[foundation],[serviceDistrict],', '服务区域详情', '', '/serviceDistrict/detail', '99', '3', '0', NULL, '1', '0');

-- ----------------------------
-- Table structure for district
-- ----------------------------
DROP TABLE IF EXISTS `district`; 
CREATE TABLE `district` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pid` INT NULL COMMENT '父id',
  `district_name` VARCHAR(45) NOT NULL COMMENT '地区名称',
  `short_name` VARCHAR(45) NULL COMMENT '简称',
  `level` INT NOT NULL COMMENT '地区级别(省为1级，依次内推)',
  `sort_order` INT NULL COMMENT '排序顺序',
  `create_time` TIMESTAMP NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL COMMENT '更新时间',
  `longitude` DECIMAL NULL COMMENT '经度',
  `latitude` DECIMAL NULL COMMENT '纬度',
  `is_activated` INT NOT NULL COMMENT '激活状态(1 启用 0 未启用）',
  PRIMARY KEY (`id`));
  
ALTER TABLE `district` 
CHANGE COLUMN `is_activated` `is_activated` INT(11) NOT NULL DEFAULT 1 COMMENT '激活状态(1 启用 0 未启用）' ;

ALTER TABLE `district` 
CHANGE COLUMN `longitude` `longitude` DECIMAL(10,5) NULL DEFAULT NULL COMMENT '经度' ,
CHANGE COLUMN `latitude` `latitude` DECIMAL(10,5) NULL DEFAULT NULL COMMENT '纬度' ;
  
-- ----------------------------
-- Menu initialization records for district
-- ---------------------------- 
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1050290172764045314', 'district', 'foundation', '[0],[foundation],', '区域配置', '', '/district', '3', '2', '1', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1050290172764045315', 'district_list', 'district', '[0],[foundation],[district],', '区域配置列表', '', '/district/list', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1050290172764045316', 'district_add', 'district', '[0],[foundation],[district],', '区域配置添加', '', '/district/add', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1050290172764045317', 'district_update', 'district', '[0],[foundation],[district],', '区域配置更新', '', '/district/update', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1050290172764045318', 'district_delete', 'district', '[0],[foundation],[district],', '区域配置删除', '', '/district/delete', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1050290172764045319', 'district_detail', 'district', '[0],[foundation],[district],', '区域配置详情', '', '/district/detail', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1050290172764045320', 'district_tree', 'district', '[0],[foundation],[district],', '区域配置树', '', '/district/tree', '99', '3', '0', NULL, '1', '0');

-- ----------------------------
-- Table structure for delivery_way
-- ----------------------------
DROP TABLE IF EXISTS `delivery_way`; 
CREATE TABLE `delivery_way` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `service_dist_id` INT NOT NULL COMMENT '服务区域id',
  `code` VARCHAR(45) NULL COMMENT '代码',
  `name` VARCHAR(45) NOT NULL COMMENT '名称',
  `desc` VARCHAR(255) NULL COMMENT '描述',
  `sort_order` INT NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`));
  
-- ----------------------------
-- Menu initialization records for delivery_way
-- ---------------------------- 
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051358170304176129', 'deliveryWay', 'foundation', '[0],[foundation],', '物流方式', '', '/deliveryWay', '4', '2', '1', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051358170304176130', 'deliveryWay_list', 'deliveryWay', '[0],[foundation],[deliveryWay],', '物流方式列表', '', '/deliveryWay/list', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051358170304176131', 'deliveryWay_add', 'deliveryWay', '[0],[foundation],[deliveryWay],', '物流方式添加', '', '/deliveryWay/add', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051358170304176132', 'deliveryWay_update', 'deliveryWay', '[0],[foundation],[deliveryWay],', '物流方式更新', '', '/deliveryWay/update', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051358170304176133', 'deliveryWay_delete', 'deliveryWay', '[0],[foundation],[deliveryWay],', '物流方式删除', '', '/deliveryWay/delete', '99', '3', '0', NULL, '1', '0');
INSERT INTO `sys_menu` (`id`, `code`, `pcode`, `pcodes`, `name`, `icon`, `url`, `num`, `levels`, `ismenu`, `tips`, `status`, `isopen`) VALUES ('1051358170304176134', 'deliveryWay_detail', 'deliveryWay', '[0],[foundation],[deliveryWay],', '物流方式详情', '', '/deliveryWay/detail', '99', '3', '0', NULL, '1', '0');

  




