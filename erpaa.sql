/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : erp

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 19/10/2020 18:04:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `time` int(11) NULL DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限编码',
  `name` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限名称',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(如：sys:user:add)',
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问地址URL',
  `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源请求类型',
  `pid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级菜单权限名称',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '排序',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '菜单权限类型(1:目录;2:菜单;3:按钮)',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态1:正常 0：禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(4) NULL DEFAULT 1 COMMENT '是否删除(1未删除；0已删除)',
  `component` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('013095aa-0f4d-4c32-b30e-229a587e52ad', 'btn-dept-add', '新增部门权限', 'sys:dept:add', '/api/dept', 'POST', '8f393e44-b585-\r\n4875-866d-71f88fea9659', 100, 3, 1, '2020-01-08 15:44:18', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('03dc1cbf-ef44-4326-a7dc-10fe16d22dad', 'btn-log-list', '查询日志列表权限', 'sys:log:list', '/api/logs', 'POST', '0545d9d1-\r\nc82c-44c2-85e5-0b6ac4042515', 100, 3, 1, '2020-01-08 16:12:14', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('0545d9d1-c82c-44c2-85e5-0b6ac4042515', '', '日志管理', '', '/system/log', '', '4caeb861-354c-45f6-98b4-59f486beb511', 100, 2, 1, '2020-01-08 13:57:12', NULL, 1, '/system/log/index');
INSERT INTO `sys_permission` VALUES ('145cb90b-d205-40f6-8a2d-703f41ed1feb', 'btn-user-delete', '删除用户权限', 'sys:user:delete', '/api/user', 'DELETE', '9ce621a0-ee2c-4cf6-b7bd-012a1a01ba63', 100, 3, 1, '2020-01-08 15:42:50', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('15bb6aff-2e3b-490aa21f-0167ae0ebc0d', 'btn-log-delete', '删除日志权限', 'sys:log:delete', '/api/log', 'DELETE', '0545d9d1-\r\nc82c-44c2-85e5-0b6ac4042515', 100, 3, 1, '2020-01-08 16:12:53', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('27acf73b-2fcb-451bbdc4-e11e5ab41e2a', 'btn-dept-delete', '删除部门权限', 'sys:dept:delete', '/api/dept/*', 'DELETE', '8f393e44-b585-4875-866d-71f88fea9659', 100, 3, 1, '2020-01-08 15:45:52', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('290c0240-0914-487cb4e9-6635bf5ebfec', '', '菜单权限管理', '', '/organization/menu', 'GET', '346df872-8964-4455-8afd-ffa6308fb18a', 99, 2, 1, '2020-01-06 21:55:59', '2020-01-08 09:10:59', 1, '/organization/menu/index');
INSERT INTO `sys_permission` VALUES ('2ae13993-9501-46d5-8473-fe45fee57f3b', 'btn-user-add', '新增用户权限', 'sys:user:add', '/api/user', 'POST', '9ce621a0-ee2c-\r\n4cf6-b7bd-012a1a01ba63', 100, 3, 1, '2020-01-08 15:40:36', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('2eeaa020-74d5-4c4b-9849-2cf4bd68fed9', 'btn-role-update', '更新角色权限', 'sys:role:update', '/api/role', 'PUT', 'c198d1cbad4d-4001-9375-9ec8ee04053d', 100, 3, 1, '2020-01-08 16:09:55', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('2f9a3f67-6ef3-4eacb9a1-c0e898718d0c', 'btn-permission-delete', '删除菜单权限', 'sys:permission:delete', '/api/permission', 'DELETE', '290c0240-0914-487c-b4e9-6635bf5ebfec', 100, 3, 1, '2020-01-08 15:48:37', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('346df872-8964-4455-8afd-ffa6308fb18a', '', '组织管理', '', '/organization', '', '0', 100, 1, 1, '2020-01-06 21:53:55', NULL, 1, 'organization');
INSERT INTO `sys_permission` VALUES ('390ded0e-9f48-40a7-a841-791c203f22ae', 'btn-permission-list', '查询菜单权限列表权限', 'sys:permission:list', '/api/permissions', 'POST', '290c0240-0914-487c-b4e9-6635bf5ebfec', 100, 3, 1, '2020-01-08 15:46:36', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('39313e6a-14ed-4224-a91e-ef6a10ba54cd', 'btn-dept-list', '查询部门信息列表权限', 'sys:dept:list', '/api/depts', 'POST', '8f393e44-b585-4875-866d-71f88fea9659', 100, 3, 1, '2020-01-08 15:43:36', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('41412d6d-d86a-4cfd-9aa7-d51f9ced0dfe', '', '测试删除', '', '', '', '346df872-8964-4455-8afd-ffa6308fb18a', 100, 1, 1, '2020-01-08 09:30:53', '2020-01-08 09:31:01', 0, NULL);
INSERT INTO `sys_permission` VALUES ('47697e92-e199-4420-a2c2-09ec1b08cb9d', 'btn-user-list', '查询用户信息列表权限', 'sys:user:list', '/api/users', 'POST', '9ce621a0-ee2c-4cf6-b7bd-012a1a01ba63', 100, 3, 1, '2020-01-08 15:39:55', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('4caeb861-354c-45f6-98b4-59f486beb511', '', '系统管理', '', '/system', '', '0', 98, 1, 1, '2020-01-08 13:55:56', NULL, 1, 'system');
INSERT INTO `sys_permission` VALUES ('65734896-90c5-4b48-b9e8-dee47a74a297', 'btn-role-delete', '删除角色权限', 'sys:role:delete', '/api/role/*', 'DELETE', 'c198d1cb-ad4d-4001-9375-9ec8ee04053d', 100, 3, 1, '2020-01-08 16:11:22', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('7141c2e9-6d50-46b6-94e8-100466b7249f', '', 'SQL监控', '', '/druid/sql.html', 'GET', '4caeb861-354c-45f6-98b4-59f486beb511', 96, 2, 1, '2020-01-08 14:30:01', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('84b9b525-aa44-4b16-9900-adca26115a37', 'btn-role-add', '新增角色权限', 'sys:role:add', '/api/role', 'POST', 'c198d1cb-ad4d-\r\n4001-9375-9ec8ee04053d', 100, 3, 1, '2020-01-08 15:50:00', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('8f393e44-b585-4875-866d-71f88fea9659', '', '部门管理', '', '/organization/dept', '', '346df872-8964-4455-8afd-ffa6308fb18a', 97, 2, 1, '2020-01-07 18:28:31', NULL, 1, '/organization/dept/index');
INSERT INTO `sys_permission` VALUES ('90b3be91-5e9d-42f8-81fb-0c9ef3014faa', 'btn-role-detail', '角色详情权限', 'sys:role:detail', '/api/role/*', 'GET', 'c198d1cbad4d-4001-9375-9ec8ee04053d', 100, 3, 1, '2020-01-08 16:10:32', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('9ce621a0-ee2c-4cf6-b7bd-012a1a01ba63', '', '用户管理', '', '/organization/user', '', '346df872-8964-4455-8afd-ffa6308fb18a', 96, 2, 1, '2020-01-07 19:49:37', '2020-01-08 10:01:38', 1, '/organization/user/index');
INSERT INTO `sys_permission` VALUES ('b7348d63-c4d3-406d-9e46-543346674275', 'btn-dept-update', '更新部门信息权限', 'sys:dept:update', '/api/dept', 'PUT', '8f393e44-b585-4875-866d-71f88fea9659', 100, 3, 1, '2020-01-08 15:44:59', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('bb5ca869-0303-4fc0-b067-936cba7d1cc8', 'btn-permission-update', '更新菜单权限', 'sys:permission:update', '/api/permission', 'PUT', '290c0240-0914-487c-b4e9-6635bf5ebfec', 100, 3, 1, '2020-01-08 15:47:56', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('c198d1cb-ad4d-4001-9375-9ec8ee04053d', '', '角色管理', '', '/organization/role/index', '', '346df872-8964-4455-8afd-ffa6308fb18a', 100, 2, 1, '2020-01-06 22:33:55', '2020-01-08 09:13:30', 1, '/Organization/Role');
INSERT INTO `sys_permission` VALUES ('d41c2bc3-454c-4f62-84fe-97825d5cf8a7', 'btn-user-update-role', '赋予用户角色权限', 'sys:user:role:update', '/api/user/roles', 'PUT', '9ce621a0-ee2c-4cf6-b7bd-012a1a01ba63', 100, 3, 1, '2020-01-08 15:41:20', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('d60faf3e-9a72-49d5-b02d-a67bfeff07fa', 'btn-user-update', '列表更新用户信息权限', 'sys:user:update', '/api/user', 'PUT', '9ce621a0-ee2c-4cf6-b7bd-012a1a01ba63', 100, 3, 1, '2020-01-08 15:42:06', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('de8e2328-4313-477e-9644-3ca93799cc76', 'btn-role-add', '角色管理-新增角色', 'sys:role:add', '/api/role', 'POST', 'c198d1cbad4d-4001-9375-9ec8ee04053d', 100, 3, 1, '2020-01-08 15:28:09', '2020-01-08 15:29:31', 1, NULL);
INSERT INTO `sys_permission` VALUES ('e136cc74-9817-4ef1-b181-8f1afd7e102c', 'btn-permission-add', '新增菜单权限', 'sys:permission:add', '/api/permission', 'POST', '290c0240-0914-487c-b4e9-6635bf5ebfec', 100, 3, 1, '2020-01-08 15:47:16', NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES ('f9f4d9f4-a2f5-430c-9f2d-6c8e650a8c39', 'btn-role-list', '查询角色列表权限', 'sys:role:list', '/api/roles', 'POST', 'c198d1cbad4d-4001-9375-9ec8ee04053d', 100, 3, 1, '2020-01-08 15:49:20', NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态(1:正常0:弃用)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(4) NULL DEFAULT 1 COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '一号角色', NULL, 1, NULL, NULL, 1);
INSERT INTO `sys_role` VALUES ('2', '二号角色', NULL, 1, NULL, NULL, 1);
INSERT INTO `sys_role` VALUES ('3', '三号角色', NULL, 1, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '013095aa-0f4d-4c32-b30e-229a587e52ad', NULL);
INSERT INTO `sys_role_permission` VALUES ('10', '1', '2eeaa020-74d5-4c4b-9849-2cf4bd68fed9', NULL);
INSERT INTO `sys_role_permission` VALUES ('11', '1', '2f9a3f67-6ef3-4eacb9a1-c0e898718d0c', NULL);
INSERT INTO `sys_role_permission` VALUES ('12', '1', '346df872-8964-4455-8afd-ffa6308fb18a', NULL);
INSERT INTO `sys_role_permission` VALUES ('13', '1', '390ded0e-9f48-40a7-a841-791c203f22ae', NULL);
INSERT INTO `sys_role_permission` VALUES ('14', '1', '39313e6a-14ed-4224-a91e-ef6a10ba54cd', NULL);
INSERT INTO `sys_role_permission` VALUES ('15', '1', '41412d6d-d86a-4cfd-9aa7-d51f9ced0dfe', NULL);
INSERT INTO `sys_role_permission` VALUES ('16', '1', '47697e92-e199-4420-a2c2-09ec1b08cb9d', NULL);
INSERT INTO `sys_role_permission` VALUES ('17', '1', '4caeb861-354c-45f6-98b4-59f486beb511', NULL);
INSERT INTO `sys_role_permission` VALUES ('18', '1', '65734896-90c5-4b48-b9e8-dee47a74a297', NULL);
INSERT INTO `sys_role_permission` VALUES ('19', '1', '7141c2e9-6d50-46b6-94e8-100466b7249f', NULL);
INSERT INTO `sys_role_permission` VALUES ('2', '1', '03dc1cbf-ef44-4326-a7dc-10fe16d22dad', NULL);
INSERT INTO `sys_role_permission` VALUES ('20', '1', '84b9b525-aa44-4b16-9900-adca26115a37', NULL);
INSERT INTO `sys_role_permission` VALUES ('21', '1', '8f393e44-b585-4875-866d-71f88fea9659', NULL);
INSERT INTO `sys_role_permission` VALUES ('22', '1', '90b3be91-5e9d-42f8-81fb-0c9ef3014faa', NULL);
INSERT INTO `sys_role_permission` VALUES ('23', '1', '9ce621a0-ee2c-4cf6-b7bd-012a1a01ba63', NULL);
INSERT INTO `sys_role_permission` VALUES ('24', '1', 'b7348d63-c4d3-406d-9e46-543346674275', NULL);
INSERT INTO `sys_role_permission` VALUES ('25', '1', 'bb5ca869-0303-4fc0-b067-936cba7d1cc8', NULL);
INSERT INTO `sys_role_permission` VALUES ('26', '1', 'c198d1cb-ad4d-4001-9375-9ec8ee04053d', NULL);
INSERT INTO `sys_role_permission` VALUES ('27', '1', 'd41c2bc3-454c-4f62-84fe-97825d5cf8a7', NULL);
INSERT INTO `sys_role_permission` VALUES ('28', '1', 'd60faf3e-9a72-49d5-b02d-a67bfeff07fa', NULL);
INSERT INTO `sys_role_permission` VALUES ('29', '1', 'de8e2328-4313-477e-9644-3ca93799cc76', NULL);
INSERT INTO `sys_role_permission` VALUES ('3', '1', '0545d9d1-c82c-44c2-85e5-0b6ac4042515', NULL);
INSERT INTO `sys_role_permission` VALUES ('30', '1', 'e136cc74-9817-4ef1-b181-8f1afd7e102c', NULL);
INSERT INTO `sys_role_permission` VALUES ('31', '1', 'f9f4d9f4-a2f5-430c-9f2d-6c8e650a8c39', NULL);
INSERT INTO `sys_role_permission` VALUES ('4', '1', '145cb90b-d205-40f6-8a2d-703f41ed1feb', NULL);
INSERT INTO `sys_role_permission` VALUES ('5', '1', '15bb6aff-2e3b-490aa21f-0167ae0ebc0d', NULL);
INSERT INTO `sys_role_permission` VALUES ('6', '1', '24b7b13c-f00f-4e6ba221-fe2d780e4d4f', NULL);
INSERT INTO `sys_role_permission` VALUES ('7', '1', '27acf73b-2fcb-451bbdc4-e11e5ab41e2a', NULL);
INSERT INTO `sys_role_permission` VALUES ('8', '1', '290c0240-0914-487cb4e9-6635bf5ebfec', NULL);
INSERT INTO `sys_role_permission` VALUES ('9', '1', '2ae13993-9501-46d5-8473-fe45fee57f3b', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户名称',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密盐值',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码密文',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `dept_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门id',
  `real_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实名称',
  `nick_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱(唯一)',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '账户状态(1.正常 2.锁定 )',
  `sex` tinyint(4) NULL DEFAULT 1 COMMENT '性别(1.男 2.女)',
  `deleted` tinyint(4) NULL DEFAULT 1 COMMENT '是否删除(1未删除；0已删除)',
  `create_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `create_where` tinyint(4) NULL DEFAULT 1 COMMENT '创建来源(1.web 2.android 3.ios )',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'aaa', 'aaa', '851836affb5d9b3dd7938c9b7ff7cb15', '12306', NULL, NULL, NULL, NULL, 1, 1, 1, NULL, NULL, 1, NULL, NULL);
INSERT INTO `sys_user` VALUES ('2', 'bbb', 'bbb', '48485d8507b907859952cce52b7faf56', NULL, NULL, NULL, NULL, NULL, 1, 1, 1, NULL, NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', '2020-10-10 15:27:26');
INSERT INTO `sys_user_role` VALUES ('3', '2', '3', '2020-10-15 15:27:47');

SET FOREIGN_KEY_CHECKS = 1;
