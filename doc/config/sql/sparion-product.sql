SET FOREIGN_KEY_CHECKS = 0;
SET NAMES utf8mb4;
-- sparion-product DDL
CREATE DATABASE `sparion-product`
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;;
use `sparion-product`;
-- sparion-product.prod_soft DDL
CREATE TABLE `sparion-product`.`prod_soft` (`id` BIGINT NOT NULL Comment "主键",
`name` VARCHAR(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL Comment "软件名称",
`code` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL Comment "软件编码",
`version` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL Comment "软件版本",
`publish_time` DATETIME NULL Comment "发布时间",
`file_ids` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' Comment "关联文件id列表，逗号分隔",
`create_by` BIGINT NULL Comment "创建者",
`create_time` DATETIME NULL Comment "创建时间",
`update_by` BIGINT NULL Comment "更新者",
`update_time` DATETIME NULL Comment "更新时间",
`del_flag` BIGINT NULL DEFAULT 0 Comment "删除标志（0代表存在 非0代表删除）",
UNIQUE INDEX `uk_prod_soft_code`(`code` ASC) USING BTREE,
PRIMARY KEY (`id`)) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic COMMENT = "软件信息表";
SET FOREIGN_KEY_CHECKS = 1;
