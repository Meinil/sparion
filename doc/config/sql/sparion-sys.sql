/*
 Navicat Premium Dump SQL

 Source Server         : 【本地】mysql
 Source Server Type    : MySQL
 Source Server Version : 80403 (8.4.3)
 Source Host           : localhost:3306
 Source Schema         : sparion-sys

 Target Server Type    : MySQL
 Target Server Version : 80403 (8.4.3)
 File Encoding         : 65001

 Date: 27/03/2025 09:52:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL COMMENT '主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `parent_id` bigint DEFAULT '0' COMMENT '父级ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `route_name` varchar(50) DEFAULT '' COMMENT '路由名称',
  `redirect` varchar(200) DEFAULT NULL COMMENT '重定向地址',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '菜单类型（1-目录 2-菜单 3-按钮 4-接口）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单图标',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` bigint DEFAULT '0' COMMENT '删除标志（0代表存在 非代表删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1, '系统管理', 0, 1, '/system', 'LAYOUT', NULL, '', '/system/index', 1, 0, '1', '0', '0', NULL, 'BarsOutlined', '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (101, '用户管理', 1, 1, 'user', '/system/user/index', NULL, '', NULL, 1, 0, '2', '0', '0', 'system:user:list', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (102, '角色管理', 1, 2, 'role', '/system/role/role', NULL, '', NULL, 1, 0, '2', '0', '0', 'system:role:list', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (103, '菜单管理', 1, 3, 'menu', '/system/menu/menu', NULL, '', NULL, 1, 0, '2', '0', '0', 'system:menu:list', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (104, '字典管理', 1, 4, 'dict', '/dashboard/console/console', NULL, '', NULL, 1, 0, '2', '0', '0', 'system:dict:list', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 1);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1001, '用户查询', 101, 1, '', '', NULL, '', NULL, 1, 0, '3', '0', '0', 'system:user:query', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1002, '用户新增', 101, 2, '', '', NULL, '', NULL, 1, 0, '3', '0', '0', 'system:user:add', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1003, '用户修改', 101, 3, '', '', NULL, '', NULL, 1, 0, '3', '0', '0', 'system:user:edit', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1004, '用户删除', 101, 4, '', '', NULL, '', NULL, 1, 0, '3', '0', '0', 'system:user:remove', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1005, '用户导出', 101, 5, '', '', NULL, '', NULL, 1, 0, '3', '0', '0', 'system:user:export', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1006, '用户导入', 101, 6, '', '', NULL, '', NULL, 1, 0, '3', '0', '0', 'system:user:import', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1007, '重置密码', 101, 7, '', '', NULL, '', NULL, 1, 0, '3', '0', '0', 'system:user:resetPwd', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1008, '角色查询', 102, 1, '', '', NULL, '', NULL, 1, 0, '3', '0', '0', 'system:role:query', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1009, '角色新增', 102, 2, '', '', NULL, '', NULL, 1, 0, '3', '0', '0', 'system:role:add', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1010, '角色修改', 102, 3, '', '', NULL, '', NULL, 1, 0, '3', '0', '0', 'system:role:edit', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1011, '角色删除', 102, 4, '', '', NULL, '', NULL, 1, 0, '3', '0', '0', 'system:role:remove', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
INSERT INTO `sys_menu` (`id`, `title`, `parent_id`, `order_num`, `path`, `component`, `query`, `route_name`, `redirect`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1012, '角色导出', 102, 5, '', '', NULL, '', NULL, 1, 0, '3', '0', '0', 'system:role:export', NULL, '', 1, '2025-03-09 11:02:50', 1, '2025-03-09 11:02:50', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL COMMENT '主键',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `role_sort` int DEFAULT NULL COMMENT '角色排序',
  `status` char(1) DEFAULT NULL COMMENT '角色状态（0正常 1停用）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `can_del` char(1) DEFAULT NULL COMMENT '能否删除 1-可以删除 0-不能删除',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` bigint DEFAULT '0' COMMENT '删除标志（0代表存在 非0代表删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `role_code`, `role_name`, `role_sort`, `status`, `remark`, `can_del`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1, 'super_admin', '系统管理员', 1, '1', '管理员', '0', NULL, NULL, 1, '2025-03-10 19:40:03', 0);
INSERT INTO `sys_role` (`id`, `role_code`, `role_name`, `role_sort`, `status`, `remark`, `can_del`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1899763414277169153, '1', '2', NULL, '1', NULL, '1', 1, '2025-03-12 18:04:29', 1, '2025-03-12 18:04:31', 1741773871);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL COMMENT '主键',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` bigint DEFAULT '0' COMMENT '删除标志（0代表存在 非0代表删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL COMMENT '主键',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `username` varchar(16) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `status` char(1) DEFAULT NULL COMMENT '帐号状态（0正常 1停用）',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` bigint DEFAULT '0' COMMENT '删除标志（0代表存在 非0代表删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `nickname`, `username`, `password`, `status`, `email`, `phone`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1, '系统管理员', 'admin', '$2a$10$7YJFAjU9ABabVBUOp9b0teBnC8gxN2EukxEg4ZyqBxVPG5HctzKcK', '1', 'admin@sparion.com', NULL, 1, '2025-03-05 20:41:38', 1, '2025-03-05 20:41:38', 0);
INSERT INTO `sys_user` (`id`, `nickname`, `username`, `password`, `status`, `email`, `phone`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1650806311189837, 'test', 'test', '$2a$10$7YJFAjU9ABabVBUOp9b0teBnC8gxN2EukxEg4ZyqBxVPG5HctzKcK', '1', NULL, NULL, NULL, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint DEFAULT '0' COMMENT '删除标志（0代表存在 非0代表删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del_flag`) VALUES (1, 1, 1, NULL, NULL, NULL, NULL, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
