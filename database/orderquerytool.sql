/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : orderquerytool

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 15/09/2023 21:17:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '表单ID',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户ID',
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单编号',
  `order_time` datetime NULL DEFAULT NULL COMMENT '下单时间',
  `order_amount` decimal(65, 2) NULL DEFAULT NULL COMMENT '订单金额',
  `is_delete` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 'e5cf2d1c-9205-4af6-9055-2b878c2e340d', '2154696', '2797-05-26 18:28:24', 5000.00, 1);
INSERT INTO `order` VALUES (2, 'e5cf2d1c-9205-4af6-9055-2b878c2e340d', '2154641', '2023-09-06 15:29:52', 9600.00, 0);
INSERT INTO `order` VALUES (3, 'e5cf2d1c-9205-4af6-9055-2b878c2e340d', '2154641', '2023-09-27 15:29:52', 9610.00, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户ID',
  `user_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'e5cf2d1c-9205-4af6-9055-2b878c2e340d', 'tyza66', '孙达明', '7132f04074c4b8955383155cda268c45850b56cc', 'shun_@outlook.com');
INSERT INTO `user` VALUES (3, '97890b67-cbd3-4543-8cb4-015517051e35', '123', '孙小明', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '123@163.com');
INSERT INTO `user` VALUES (4, 'cf93f1cf-02f7-4a76-8789-f7b2d1ca72dd', '1234', '孙小1', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '121@163.com');

SET FOREIGN_KEY_CHECKS = 1;
