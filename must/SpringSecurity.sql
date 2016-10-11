/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50536
Source Host           : 127.0.0.1:3306
Source Database       : test-1

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2016-10-11 14:11:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_app_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_app_menu`;
CREATE TABLE `t_app_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '菜单名称',
  `parentId` int(11) NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `menuCode` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '列表前样式',
  `menuUrl` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单URL',
  `urlTarget` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '页面打开位置',
  `navMenu` int(11) NOT NULL DEFAULT '0' COMMENT '0:不显示在导航菜单中,1:显示在导航菜单中',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `remark` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `createTime` datetime NOT NULL,
  `lastUpdate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_app_menu
-- ----------------------------
INSERT INTO `t_app_menu` VALUES ('1', '系统主体', '0', '无样式', '^/home/.*$', '系统主体', '0', '0', '系统主体', '2016-10-11 10:53:56', '2016-10-11 10:53:58');
INSERT INTO `t_app_menu` VALUES ('2', '首页', '0', 'icon-home', '/homePage', '首页', '1', '1', '备注', '2013-03-28 00:00:00', '2013-03-28 00:00:00');
INSERT INTO `t_app_menu` VALUES ('3', '系统管理', '0', 'icon-briefcase', '', '系统管理', '1', '2', '备注', '2016-10-04 20:07:50', '2016-10-04 20:07:52');
INSERT INTO `t_app_menu` VALUES ('4', '角色管理', '3', 'icon-group', '/role', '角色管理', '1', '3', '备注', '2016-10-04 20:09:23', '2016-10-04 20:09:25');
INSERT INTO `t_app_menu` VALUES ('5', '权限管理', '3', 'icon-cogs', '/menu', '权限管理', '1', '4', '备注', '2016-10-04 20:54:19', '2016-10-04 20:54:21');

-- ----------------------------
-- Table structure for t_app_role
-- ----------------------------
DROP TABLE IF EXISTS `t_app_role`;
CREATE TABLE `t_app_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `createTime` datetime DEFAULT NULL COMMENT '角色创建时间',
  `lastUpdate` datetime DEFAULT NULL COMMENT '角色最近修改时间',
  `status` int(255) DEFAULT NULL COMMENT '0:禁用,1:启用',
  `descn` varchar(200) DEFAULT NULL COMMENT '角色说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_app_role
-- ----------------------------
INSERT INTO `t_app_role` VALUES ('1', 'ROLE_ADMIN', '2016-09-30 11:21:04', '2016-09-30 11:21:06', '1', '管理员角色');
INSERT INTO `t_app_role` VALUES ('2', 'ROLE_USER', '2016-09-30 11:21:13', '2016-09-30 11:21:10', '1', '用户角色');

-- ----------------------------
-- Table structure for t_app_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_app_role_menu`;
CREATE TABLE `t_app_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `menuId` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_app_role_menu
-- ----------------------------
INSERT INTO `t_app_role_menu` VALUES ('1', '1', '1');
INSERT INTO `t_app_role_menu` VALUES ('2', '1', '2');
INSERT INTO `t_app_role_menu` VALUES ('3', '1', '3');
INSERT INTO `t_app_role_menu` VALUES ('4', '1', '4');
INSERT INTO `t_app_role_menu` VALUES ('5', '1', '5');
INSERT INTO `t_app_role_menu` VALUES ('6', '2', '1');
INSERT INTO `t_app_role_menu` VALUES ('7', '2', '2');

-- ----------------------------
-- Table structure for t_app_user
-- ----------------------------
DROP TABLE IF EXISTS `t_app_user`;
CREATE TABLE `t_app_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '账户',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `useremail` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastUpdate` datetime DEFAULT NULL COMMENT '末次修改时间',
  `status` int(255) DEFAULT NULL COMMENT '''0:禁用,1:启用''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_app_user
-- ----------------------------
INSERT INTO `t_app_user` VALUES ('1', 'admin', '79d6b085952ec8faf9d3d2aa84d699906fea20f3a820b115ea24f79d0325a34948f32a690654b792', '568237200@qq.com', '2016-09-30 11:47:22', '2016-09-30 11:47:25', '1');

-- ----------------------------
-- Table structure for t_app_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_app_user_role`;
CREATE TABLE `t_app_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户id',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_app_user_role
-- ----------------------------
INSERT INTO `t_app_user_role` VALUES ('1', '1', '1');
INSERT INTO `t_app_user_role` VALUES ('2', '1', '2');
