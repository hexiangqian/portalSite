/*
 Navicat MySQL Data Transfer

 Source Server         : local_news
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : news

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 26/02/2020 22:39:10
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
-- Records of t_articleannex
-- ----------------------------
INSERT INTO `t_articleannex` VALUES (6631070294603730944, 6629966040509976576);
INSERT INTO `t_articleannex` VALUES (6631070294603730944, 6630686027428139008);
INSERT INTO `t_articleannex` VALUES (6631093757338980352, 6629966040509976576);
INSERT INTO `t_articleannex` VALUES (6631093757338980352, 6630686027428139008);

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `fid` bigint(20) NOT NULL COMMENT '文件id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '0:文档 1:图片',
  `uploadtime` datetime(0) NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_file
-- ----------------------------
INSERT INTO `t_file` VALUES (0, 'news_default.jpg', '/static/news/news_img_default.jpg', 1, '2020-02-23 01:38:13');

-- ----------------------------
-- Table structure for t_honour
-- ----------------------------
DROP TABLE IF EXISTS `t_honour`;
CREATE TABLE `t_honour`  (
  `hid` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '荣誉id',
  `imageid` bigint(20) NULL DEFAULT NULL COMMENT '荣誉图片，若无则默认\r\n（联系前端做匿名人物图片）',
  `pepole` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '荣誉人物',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '荣誉标题',
  `coment` blob NOT NULL COMMENT '荣誉类容',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布人',
  `publishdate` datetime(0) NOT NULL COMMENT '发布时间',
  `pageview` bigint(255) NULL DEFAULT NULL COMMENT '浏览次数',
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
  `reviewstatus` tinyint(4) NULL DEFAULT 0 COMMENT '0:未通过 1：通过',
  `reviewer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reviewdate` datetime(0) NULL DEFAULT NULL,
  `goodnum` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  `badnum` int(11) NULL DEFAULT 0 COMMENT '点差数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '新闻id',
  `imageid` bigint(20) NULL DEFAULT NULL COMMENT '新闻轮播图片',
  `titile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `ntid` bigint(20) NOT NULL COMMENT '新闻类型id',
  `content` blob NOT NULL COMMENT '内容',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `publishdate` datetime(0) NOT NULL COMMENT '发布时间',
  `reviewstatus` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0:未通过 1：通过',
  `reviewer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '审核人',
  `reviewdate` datetime(0) NULL DEFAULT NULL COMMENT '审核通过时间',
  `pageview` bigint(20) NOT NULL DEFAULT 0 COMMENT '浏览次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6631093757338980353 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新闻' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_newstype
-- ----------------------------
DROP TABLE IF EXISTS `t_newstype`;
CREATE TABLE `t_newstype`  (
  `ntid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '新闻类型id',
  `newsTName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新闻类型名称',
  PRIMARY KEY (`ntid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新闻类型名称' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_newstype
-- ----------------------------
INSERT INTO `t_newstype` VALUES (0, '重大新闻');
INSERT INTO `t_newstype` VALUES (1, '突发新闻');
INSERT INTO `t_newstype` VALUES (2, '内部新闻');
INSERT INTO `t_newstype` VALUES (3, '国内新闻');
INSERT INTO `t_newstype` VALUES (7, '测试新闻类型2');

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
  `reviewer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `reviewdate` datetime(0) NULL DEFAULT NULL COMMENT '审核日期',
  `pageview` bigint(255) NULL DEFAULT 0 COMMENT '浏览量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_noticetype
-- ----------------------------
DROP TABLE IF EXISTS `t_noticetype`;
CREATE TABLE `t_noticetype`  (
  `ntid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '通告类型id',
  `atName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通告类型名称',
  PRIMARY KEY (`ntid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通告类型' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_noticetype
-- ----------------------------
INSERT INTO `t_noticetype` VALUES (1, '管理类');
INSERT INTO `t_noticetype` VALUES (2, '培训类');
INSERT INTO `t_noticetype` VALUES (3, '制度类');
INSERT INTO `t_noticetype` VALUES (4, '情况通报');
INSERT INTO `t_noticetype` VALUES (5, '批评通报');
INSERT INTO `t_noticetype` VALUES (6, '表彰通报 ');
INSERT INTO `t_noticetype` VALUES (7, '测试新闻类型2');

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
  `dept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '培训专栏' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project`  (
  `contractnum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合同编号',
  `projectnum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目号',
  `projecttoken` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作令',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '项目状态',
  `planstart` datetime(0) NULL DEFAULT NULL COMMENT '计划开始时间',
  `planstop` datetime(0) NULL DEFAULT NULL COMMENT '计划完工期',
  `reallycomplete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
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
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publishdate` datetime(0) NOT NULL,
  `dept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '规章制度' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dept`;
CREATE TABLE `t_sys_dept`  (
  `dptid` int(11) NOT NULL,
  `dptname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`dptid`, `dptname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_dept
-- ----------------------------
INSERT INTO `t_sys_dept` VALUES (0, '软件部');
INSERT INTO `t_sys_dept` VALUES (1, '市场部');
INSERT INTO `t_sys_dept` VALUES (2, '财务部');
INSERT INTO `t_sys_dept` VALUES (3, '系统部');
INSERT INTO `t_sys_dept` VALUES (4, '硬件部');
INSERT INTO `t_sys_dept` VALUES (5, '总经办');
INSERT INTO `t_sys_dept` VALUES (6, '生产部');
INSERT INTO `t_sys_dept` VALUES (7, '采购部');
INSERT INTO `t_sys_dept` VALUES (8, '项目管理部');
INSERT INTO `t_sys_dept` VALUES (9, '技术服务中心');

-- ----------------------------
-- Table structure for t_sys_module
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_module`;
CREATE TABLE `t_sys_module`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `modulename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent` bigint(20) NOT NULL,
  `updatetime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `delflag` tinyint(4) NULL DEFAULT 0 COMMENT '0:未删除 1：删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_module
-- ----------------------------
INSERT INTO `t_sys_module` VALUES (1, '系统管理', 0, '2020-02-24 17:14:44', 0);
INSERT INTO `t_sys_module` VALUES (2, '用户管理', 1, '2020-02-24 17:14:53', 0);
INSERT INTO `t_sys_module` VALUES (3, '角色管理', 1, '2020-02-24 17:14:49', 0);
INSERT INTO `t_sys_module` VALUES (4, '新闻类型', 1, '2020-02-24 21:54:43', 0);
INSERT INTO `t_sys_module` VALUES (5, '通告类型', 1, '2020-02-24 21:54:59', 0);
INSERT INTO `t_sys_module` VALUES (6, '内容发布', 0, '2020-02-24 21:55:28', 0);
INSERT INTO `t_sys_module` VALUES (7, '新闻发布', 6, '2020-02-24 21:55:42', 0);
INSERT INTO `t_sys_module` VALUES (8, '通告发布', 6, '2020-02-24 21:55:55', 0);
INSERT INTO `t_sys_module` VALUES (9, '培训发布', 6, '2020-02-24 21:58:22', 0);
INSERT INTO `t_sys_module` VALUES (10, '制度文档', 6, '2020-02-24 21:58:35', 0);
INSERT INTO `t_sys_module` VALUES (11, '质量建设', 6, '2020-02-24 21:58:46', 0);
INSERT INTO `t_sys_module` VALUES (12, '项目管理', 6, '2020-02-24 21:59:01', 0);
INSERT INTO `t_sys_module` VALUES (13, '荣誉管理', 6, '2020-02-24 21:59:10', 0);
INSERT INTO `t_sys_module` VALUES (14, '内容审核', 0, '2020-02-24 21:59:21', 0);
INSERT INTO `t_sys_module` VALUES (15, '新闻审核', 14, '2020-02-24 21:59:30', 0);
INSERT INTO `t_sys_module` VALUES (16, '通告审核', 14, '2020-02-24 21:59:45', 0);
INSERT INTO `t_sys_module` VALUES (17, '分享审核', 14, '2020-02-24 21:59:56', 0);

-- ----------------------------
-- Table structure for t_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `moduleid` bigint(20) NOT NULL COMMENT '-1表示属于系统，不属于具体某个模块',
  `url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mustneed` tinyint(4) NOT NULL DEFAULT 0 COMMENT '1：游客可访问 0：登录访问',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_permission
-- ----------------------------
INSERT INTO `t_sys_permission` VALUES (1, '【用户管理】用户登录', 2, '/back//user/signin', 1);
INSERT INTO `t_sys_permission` VALUES (2, '【用户管理】用户退出/注销', 2, '/back//user/logout', 1);
INSERT INTO `t_sys_permission` VALUES (3, '【用户管理】用户新增', 2, '/back//user/add', 0);
INSERT INTO `t_sys_permission` VALUES (4, '【用户管理】用户更新', 2, '/back//user/update', 0);
INSERT INTO `t_sys_permission` VALUES (5, '【用户管理】用户分页获取', 2, '/back//user/lists', 0);
INSERT INTO `t_sys_permission` VALUES (6, '【用户管理】用户密码校验', 2, '/back/user/validate', 1);
INSERT INTO `t_sys_permission` VALUES (7, '【用户管理】用户密码更新', 2, '/back/user/updatePasswd', 0);
INSERT INTO `t_sys_permission` VALUES (8, '【用户管理】获取指定用户已绑定角色列表', 2, '/back/user/specUserRoles', 0);
INSERT INTO `t_sys_permission` VALUES (9, '【用户管理】获取指定用户未绑定的角色列表', 2, '/back/user/specUserUnEnableRoles', 0);
INSERT INTO `t_sys_permission` VALUES (10, '【用户管理】给指定用户绑定角色', 2, '/back/user/bindSpecUserRole', 0);
INSERT INTO `t_sys_permission` VALUES (11, '【用户管理】给指定用户解除绑定角色', 2, '/back/user/unBindSpecUserRole', 0);
INSERT INTO `t_sys_permission` VALUES (12, '【角色管理】分页获取角色列表', 3, '/back/sysRole/lists', 0);
INSERT INTO `t_sys_permission` VALUES (13, '【角色管理】添加一个角色', 3, '/back/sysRole/add', 0);
INSERT INTO `t_sys_permission` VALUES (14, '【角色管理】删除一个角色（通过角色id）', 3, '/back/sysRole/delete', 0);
INSERT INTO `t_sys_permission` VALUES (15, '【角色管理】获取指定角色已绑定的模块列表', 3, '/back/sysRole/specRoleEnableMoudles', 0);
INSERT INTO `t_sys_permission` VALUES (16, '【角色管理】获取指定角色未拥有的一级模块列表', 3, '/back/sysRole/specRoleUnEnableRootMoudles', 0);
INSERT INTO `t_sys_permission` VALUES (17, '【角色管理】获取某角色指定模块未拥有的子模块列表', 3, '/back/sysRole/specRoleUnEnableChildMoudles', 0);
INSERT INTO `t_sys_permission` VALUES (18, '【角色管理】绑定指定角色的模块', 3, '/back/sysRole/bindSpecRoleMoudle', 0);
INSERT INTO `t_sys_permission` VALUES (19, '【角色管理】解除指定角色绑定的模块', 3, '/back/sysRole/unBindSpecRoleMoudle', 0);
INSERT INTO `t_sys_permission` VALUES (20, '【系统设置/新闻类型】获取新闻类型列表', 4, '/back/settings/newsType/getTypes', 0);
INSERT INTO `t_sys_permission` VALUES (21, '【系统设置/新闻类型】添加新闻类型', 4, '/back/settings/newsType/add', 0);
INSERT INTO `t_sys_permission` VALUES (22, '【系统设置/新闻类型】更新新闻类型', 4, '/back/settings/newsType/update', 0);
INSERT INTO `t_sys_permission` VALUES (23, '【系统设置/通告类型】获取通告类型列表', 5, '/back/settings/noticeType/getTypes', 0);
INSERT INTO `t_sys_permission` VALUES (24, '【系统设置/通告类型】添加通告类型', 5, '/back/settings/noticeType/add', 0);
INSERT INTO `t_sys_permission` VALUES (25, '【系统设置/通告类型】更新通告类型', 5, '/back/settings/noticeType/update', 0);
INSERT INTO `t_sys_permission` VALUES (26, '【模块】获取一级模块列表', -1, '/back/sysModule/getRootModules', 1);
INSERT INTO `t_sys_permission` VALUES (27, '【模块】获取指定模块子模块列表', -1, '/back/sysModule/getChildModules', 1);
INSERT INTO `t_sys_permission` VALUES (28, '【模块】获取当前用户可用一级模块列表', -1, '/back/sysModule/getCurrentUserRootModules', 1);
INSERT INTO `t_sys_permission` VALUES (29, '【模块】获取当前用户指定模块可用子模块列表', -1, '/back/sysModule/getCurrentUserChildModules', 1);
INSERT INTO `t_sys_permission` VALUES (30, '【新闻管理】标题是否重复校验', 6, '/news/existTitle', 0);
INSERT INTO `t_sys_permission` VALUES (31, '【新闻管理】发布新闻', 6, '/news/addNews', 0);
INSERT INTO `t_sys_permission` VALUES (32, '【新闻管理】分页获取新闻列表(游客)', 6, '/news/getNews', 1);
INSERT INTO `t_sys_permission` VALUES (33, '【新闻管理】分页获取新闻列表—未审核', 6, '/news/getNewsUnReview', 0);
INSERT INTO `t_sys_permission` VALUES (34, '【文件管理】文件上传', -1, '/files/upload', 0);
INSERT INTO `t_sys_permission` VALUES (35, '【文件管理】文件下载', -1, '/files/download', 0);
INSERT INTO `t_sys_permission` VALUES (36, '【文件管理】文件删除', -1, '/files/delete', 0);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `descr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, 'admin', '超级管理员');
INSERT INTO `t_sys_role` VALUES (2, 'normal', '普通用户');
INSERT INTO `t_sys_role` VALUES (3, 'reviewer', '审核者');

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
-- Records of t_sys_roleperms
-- ----------------------------
INSERT INTO `t_sys_roleperms` VALUES (2, 20);
INSERT INTO `t_sys_roleperms` VALUES (2, 21);
INSERT INTO `t_sys_roleperms` VALUES (2, 22);

-- ----------------------------
-- Table structure for t_sys_roleusers
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_roleusers`;
CREATE TABLE `t_sys_roleusers`  (
  `roleid` bigint(20) NOT NULL COMMENT '角色id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`userid`, `roleid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-用户 关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_roleusers
-- ----------------------------
INSERT INTO `t_sys_roleusers` VALUES (2, 2);
INSERT INTO `t_sys_roleusers` VALUES (2, 3);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `realname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `passwd` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deptid` bigint(20) NULL DEFAULT NULL,
  `deleteflag` tinyint(255) NULL DEFAULT 0 COMMENT '0 未删除 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES (1, 'admin', '超级管理员', 'c21b86df46a5c99e2ced72d9eb4c4623', -1, 0);
INSERT INTO `t_sys_user` VALUES (2, 'baixuepeng', '白雪鹏', 'c21b86df46a5c99e2ced72d9eb4c4623', 0, 0);
INSERT INTO `t_sys_user` VALUES (3, 'heping', '何平', 'c21b86df46a5c99e2ced72d9eb4c4623', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
