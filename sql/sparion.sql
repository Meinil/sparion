/*
 Navicat Premium Data Transfer

 Source Server         : 虚拟机
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 192.168.117.128:3306
 Source Schema         : sparion

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 05/10/2022 20:23:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bot_info
-- ----------------------------
DROP TABLE IF EXISTS `bot_info`;
CREATE TABLE `bot_info`  (
  `id` bigint NOT NULL COMMENT '主键',
  `qq_account_id` bigint NULL DEFAULT NULL COMMENT 'qq账户id',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '机器人状态',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新人的用户id',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标志（0代表未删除，1代表已删除）',
  `version` datetime NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '机器人主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bot_qq_account
-- ----------------------------
DROP TABLE IF EXISTS `bot_qq_account`;
CREATE TABLE `bot_qq_account`  (
  `id` bigint NOT NULL COMMENT '主键',
  `qq` bigint NULL DEFAULT NULL COMMENT 'qq号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `secret_key` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '秘钥',
  `device` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'device.json的路径',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT 'qq账户状态\r\n0-已禁用\r\n1-已启用',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新人的用户id',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标志（0代表未删除，1代表已删除）',
  `version` datetime NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '机器人主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info`  (
  `id` bigint NOT NULL COMMENT '主键',
  `path` bigint NULL DEFAULT NULL COMMENT '文件存储的路径',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件uuid',
  `originName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件原始名字',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新人的用户id',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标志（0代表未删除，1代表已删除）',
  `version` datetime NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_perms
-- ----------------------------
DROP TABLE IF EXISTS `sys_perms`;
CREATE TABLE `sys_perms`  (
  `id` bigint NOT NULL,
  `perms_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名称',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '权限状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '权限状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新人的用户id',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标志（0代表未删除，1代表已删除）',
  `version` datetime NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新人的用户id',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标志（0代表未删除，1代表已删除）',
  `version` datetime NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role_perms
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_perms`;
CREATE TABLE `sys_role_perms`  (
  `id` bigint NOT NULL COMMENT '主键',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  `perms_id` bigint NULL DEFAULT NULL COMMENT '菜单id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新人的用户id',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标志（0代表未删除，1代表已删除）',
  `version` datetime NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限连接标' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL COMMENT '主键',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新人的用户id',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标志（0代表未删除，1代表已删除）',
  `version` datetime NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '更新人的用户id',
  `deleted` tinyint NULL DEFAULT NULL COMMENT '删除标志（0代表未删除，1代表已删除）',
  `version` datetime NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
