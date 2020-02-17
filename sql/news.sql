/*
 Navicat Premium Data Transfer

 Source Server         : news
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : 192.168.11.248:3306
 Source Schema         : news

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 20/01/2020 18:22:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_articleannex
-- ----------------------------
DROP TABLE IF EXISTS `t_articleannex`;
CREATE TABLE `t_articleannex`  (
  `articleid` bigint(20) NOT NULL COMMENT '文章编号',
  `fid` bigint(20) NOT NULL COMMENT '文件编号',
  PRIMARY KEY (`articleid`, `fid`) USING BTREE,
  INDEX `article_key`(`articleid`) USING BTREE COMMENT '文件索引'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `fid` bigint(20) NOT NULL COMMENT '文件id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件路径',
  `type` tinyint(4) DEFAULT NULL COMMENT '0:文档 1:图片',
  `uploadtime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上传时间',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_honour
-- ----------------------------
DROP TABLE IF EXISTS `t_honour`;
CREATE TABLE `t_honour`  (
  `hid` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '荣誉id',
  `imageid` bigint(20) DEFAULT NULL COMMENT '荣誉图片，若无则默认\r\n（联系前端做匿名人物图片）',
  `pepole` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '荣誉人物',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '荣誉标题',
  `coment` blob NOT NULL COMMENT '荣誉类容',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布人',
  `publishdate` datetime(0) NOT NULL COMMENT '发布时间',
  `pageview` bigint(255) DEFAULT NULL COMMENT '浏览次数',
  PRIMARY KEY (`hid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '荣誉中亚' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_knowledgeshare
-- ----------------------------
DROP TABLE IF EXISTS `t_knowledgeshare`;
CREATE TABLE `t_knowledgeshare`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `titile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `coment` blob NOT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publishdate` datetime(0) NOT NULL,
  `reviewstatus` tinyint(4) DEFAULT 0 COMMENT '0:未通过 1：通过',
  `reviewer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `reviewdate` datetime(0) DEFAULT NULL,
  `goodnum` int(11) DEFAULT 0 COMMENT '点赞数',
  `badnum` int(11) DEFAULT 0 COMMENT '点差数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '新闻id',
  `imageid` bigint(20) DEFAULT NULL COMMENT '新闻轮播图片',
  `titile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `ntid` bigint(20) NOT NULL COMMENT '新闻类型id',
  `content` blob NOT NULL COMMENT '内容',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `publishdate` datetime(0) NOT NULL COMMENT '发布时间',
  `reviewstatus` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0:未通过 1：通过',
  `reviewer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '审核人',
  `reviewdate` datetime(0) DEFAULT NULL COMMENT '审核通过时间',
  `pageview` bigint(20) DEFAULT 0 COMMENT '浏览次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新闻' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_newstype
-- ----------------------------
DROP TABLE IF EXISTS `t_newstype`;
CREATE TABLE `t_newstype`  (
  `ntid` bigint(20) NOT NULL COMMENT '新闻类型id',
  `newsTName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '新闻类型名称',
  PRIMARY KEY (`ntid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新闻类型名称' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `ntid` bigint(20) NOT NULL COMMENT '公告类型',
  `content` blob NOT NULL COMMENT '内容',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `publisdate` datetime(0) NOT NULL COMMENT '发布时间',
  `reviewstatus` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0：未通过 1：通过',
  `reviewer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核人',
  `reviewdate` datetime(0) DEFAULT NULL COMMENT '审核日期',
  `pageview` bigint(255) DEFAULT 0 COMMENT '浏览量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_noticetype
-- ----------------------------
DROP TABLE IF EXISTS `t_noticetype`;
CREATE TABLE `t_noticetype`  (
  `ntid` bigint(20) NOT NULL COMMENT '通告类型id',
  `atName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通告类型名称',
  PRIMARY KEY (`ntid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通告类型' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_orgtrain
-- ----------------------------
DROP TABLE IF EXISTS `t_orgtrain`;
CREATE TABLE `t_orgtrain`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '培训文章id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `coment` blob NOT NULL COMMENT '内容',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publishdate` datetime(0) NOT NULL,
  `dept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '培训专栏' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project`  (
  `contractnum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合同编号',
  `projectnum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目号',
  `projecttoken` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工作令',
  `status` tinyint(4) DEFAULT NULL COMMENT '项目状态',
  `planstart` datetime(0) DEFAULT NULL COMMENT '计划开始时间',
  `planstop` datetime(0) DEFAULT NULL COMMENT '计划完工期',
  `reallycomplete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`contractnum`, `projectnum`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_quality
-- ----------------------------
DROP TABLE IF EXISTS `t_quality`;
CREATE TABLE `t_quality`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `coment` blob NOT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publisdate` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '质量专栏' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_regulations
-- ----------------------------
DROP TABLE IF EXISTS `t_regulations`;
CREATE TABLE `t_regulations`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `coment` blob NOT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `publishdate` datetime(0) NOT NULL,
  `dept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '规章制度' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_sys_module
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_module`;
CREATE TABLE `t_sys_module`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `modulename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent` bigint(20) NOT NULL,
  `updatetime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `delflag` tinyint(4) DEFAULT 0 COMMENT '0:未删除 1：删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_module
-- ----------------------------
INSERT INTO `t_sys_module` VALUES (2, '系统管理', 0, '2020-01-20 15:41:32', 0);
INSERT INTO `t_sys_module` VALUES (3, '角色管理', 2, '2020-01-20 15:42:15', 0);
INSERT INTO `t_sys_module` VALUES (4, '用户管理', 2, '2020-01-20 15:41:02', 0);

-- ----------------------------
-- Table structure for t_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `moduleid` bigint(20) NOT NULL COMMENT '0表示属于系统，不属于具体某个模块',
  `url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 全部(0),读取(1),添加(2),修改(3),删除(4);',
  `mustneed` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_permission
-- ----------------------------
INSERT INTO `t_sys_permission` VALUES (1, '用户登录', 4, '/user/signin', '0', 1);
INSERT INTO `t_sys_permission` VALUES (2, '用户退出/注销', 4, '/user/logout', '0', 1);
INSERT INTO `t_sys_permission` VALUES (3, '用户新增', 4, '/user/add', '2', 0);
INSERT INTO `t_sys_permission` VALUES (4, '用户更新', 4, '/user/update', '3', 0);
INSERT INTO `t_sys_permission` VALUES (5, '用户分页获取', 4, '/user/lists', '1', 0);
INSERT INTO `t_sys_permission` VALUES (6, '用户密码校验', 4, '/user/validate', '1', 1);
INSERT INTO `t_sys_permission` VALUES (7, '【权限管理】获取权限列表', 0, '/sysPermission/lists', '1', 0);
INSERT INTO `t_sys_permission` VALUES (8, '【权限管理】获取系统已有接口列表', 0, '/sysPermission/urlLists', '1', 0);
INSERT INTO `t_sys_permission` VALUES (9, '【角色管理】分页获取角色列表', 0, '/sysRole/lists', '1', 0);
INSERT INTO `t_sys_permission` VALUES (10, '【角色管理】添加一个角色', 3, '/sysRole/add', '2', 0);
INSERT INTO `t_sys_permission` VALUES (11, '【角色管理】删除一个角色（通过角色id）', 3, '/sysRole/delete', '4', 0);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `descr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, 'admin', '超级管理员');
INSERT INTO `t_sys_role` VALUES (2, 'normal', '普通用户');

-- ----------------------------
-- Table structure for t_sys_roleperms
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_roleperms`;
CREATE TABLE `t_sys_roleperms`  (
  `roleid` bigint(20) NOT NULL,
  `permissionid` bigint(20) NOT NULL,
  PRIMARY KEY (`roleid`, `permissionid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `realname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `passwd` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roleid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES (1, 'admin', '超级管理员', 'c21b86df46a5c99e2ced72d9eb4c4623', 1);
INSERT INTO `t_sys_user` VALUES (3, 'test2', '测试用户2', '94bb030724f1b85c2ac6fdad8647cc6d', NULL);

SET FOREIGN_KEY_CHECKS = 1;
