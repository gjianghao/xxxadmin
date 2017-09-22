/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : sn-admin

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-22 17:42:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL,
  `href` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` char(1) NOT NULL DEFAULT '0',
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '系统管理', null, null, '2017-09-21 14:20:01', '0', '超级管理员拥有系统管理');
INSERT INTO `sys_resource` VALUES ('2', '用户管理', '1', 'sysUser/toView', '2017-09-21 14:20:09', '0', '用来管理用户');
INSERT INTO `sys_resource` VALUES ('3', '角色管理', '1', 'sysRole/toView', '2017-09-21 14:20:18', '0', '用来管理角色');
INSERT INTO `sys_resource` VALUES ('4', '资源管理', '1', 'sysResource/toView', '2017-09-21 18:32:44', '0', '用来管理访问的资源xxx');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `status` char(1) NOT NULL DEFAULT '0',
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '0', '一切权利都在我的手中');
INSERT INTO `sys_role` VALUES ('2', '商务', '0', '我是小小的商务啊');
INSERT INTO `sys_role` VALUES ('3', '运营', '0', '我是小小的运营');
INSERT INTO `sys_role` VALUES ('6', '技术', '0', '负责技术开发');

-- ----------------------------
-- Table structure for `sys_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_id` int(10) DEFAULT NULL,
  `resource_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('51', '2', '1');
INSERT INTO `sys_role_resource` VALUES ('52', '2', '3');
INSERT INTO `sys_role_resource` VALUES ('53', '2', '4');
INSERT INTO `sys_role_resource` VALUES ('54', '1', '1');
INSERT INTO `sys_role_resource` VALUES ('55', '1', '2');
INSERT INTO `sys_role_resource` VALUES ('56', '1', '3');
INSERT INTO `sys_role_resource` VALUES ('57', '1', '4');
INSERT INTO `sys_role_resource` VALUES ('59', '6', '1');
INSERT INTO `sys_role_resource` VALUES ('60', '6', '4');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '20d8f66e87a46e21de78ea4efd661069', '2017-09-20 09:58:44');
INSERT INTO `sys_user` VALUES ('14', 'king', 'guojianghao', '20d8f66e87a46e21de78ea4efd661069', '2017-09-22 17:08:20');
INSERT INTO `sys_user` VALUES ('15', '张聪明', 'zhangcongming', '20d8f66e87a46e21de78ea4efd661069', '2017-09-22 17:08:41');
INSERT INTO `sys_user` VALUES ('16', '姚志祥', 'yaozhixiang', '20d8f66e87a46e21de78ea4efd661069', '2017-09-22 17:08:59');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('11', '1', '1');
INSERT INTO `sys_user_role` VALUES ('13', '14', '6');
INSERT INTO `sys_user_role` VALUES ('14', '15', '6');
INSERT INTO `sys_user_role` VALUES ('15', '16', '6');
