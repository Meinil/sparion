/*
 Navicat Premium Dump SQL

 Source Server         : 【本地】mysql
 Source Server Type    : MySQL
 Source Server Version : 80403 (8.4.3)
 Source Host           : localhost:3306
 Source Schema         : sparion-res

 Target Server Type    : MySQL
 Target Server Version : 80403 (8.4.3)
 File Encoding         : 65001

 Date: 27/03/2025 09:52:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS `sparion-resource`
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE `sparion-resource`;

-- ----------------------------
-- Table structure for res_file
-- ----------------------------
DROP TABLE IF EXISTS `res_file`;
CREATE TABLE `res_file` (
  `id` bigint NOT NULL COMMENT '主键',
  `file_name` varchar(128) DEFAULT NULL COMMENT '文件名称',
  `file_suffix` varchar(8) DEFAULT NULL COMMENT '文件后缀名',
  `path` varchar(255) DEFAULT NULL COMMENT '文件存储路径',
  `storage_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '存储类型 1-永久 2-临时',
  `storage_model` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '存储方式 1-本地 2-minio',
  `hash` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件hash值',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` bigint DEFAULT '0' COMMENT '删除标志（0代表存在 非0代表删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文件信息表';

SET FOREIGN_KEY_CHECKS = 1;
