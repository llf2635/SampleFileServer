/*
 Navicat Premium Data Transfer

 Source Server         : yyds
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : fileserver

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 24/10/2021 20:53:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `size` bigint NULL DEFAULT NULL COMMENT '文件大小',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件地址',
  `upload_time` datetime NULL DEFAULT NULL COMMENT '上传时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('0223f2d585314293a914f8b38a609310', '2021/10/22/0223f2d585314293a914f8b38a609310刻晴.jpg', 587899, 'image/jpeg', 'https://server-client.oss-cn-hangzhou.aliyuncs.com/2021/10/22/0223f2d585314293a914f8b38a609310刻晴.jpg', '2021-10-22 20:55:09', '2021-10-22 20:55:09');
INSERT INTO `file` VALUES ('33c8ebcb67dd40f09b0351688b2a3661', '2021/10/24/33c8ebcb67dd40f09b0351688b2a366185446834_p0_master1200.jpg', 548592, 'image/jpeg', 'https://server-client.oss-cn-hangzhou.aliyuncs.com/2021/10/24/33c8ebcb67dd40f09b0351688b2a366185446834_p0_master1200.jpg', '2021-10-24 10:32:04', '2021-10-24 10:32:04');
INSERT INTO `file` VALUES ('603b25fadafe48c497e630c941092c5b', '2021/10/24/603b25fadafe48c497e630c941092c5b85446834_p0_master1200.jpg', 548592, 'image/jpeg', 'https://server-client.oss-cn-hangzhou.aliyuncs.com/2021/10/24/603b25fadafe48c497e630c941092c5b85446834_p0_master1200.jpg', '2021-10-24 10:33:43', '2021-10-24 10:33:43');
INSERT INTO `file` VALUES ('b16a7d323a3a4c31ba951ed323002c63', '2021/10/23/b16a7d323a3a4c31ba951ed323002c63测试EasyExcel.xlsx', 6616, 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 'https://server-client.oss-cn-hangzhou.aliyuncs.com/2021/10/23/b16a7d323a3a4c31ba951ed323002c63测试EasyExcel.xlsx', '2021-10-23 20:31:59', '2021-10-23 20:31:59');
INSERT INTO `file` VALUES ('e677c69e29914300a9592665c819ee5b', '2021/10/22/e677c69e29914300a9592665c819ee5b93237930_p0_master1200.jpg', 561944, 'image/jpeg', 'https://server-client.oss-cn-hangzhou.aliyuncs.com/2021/10/22/e677c69e29914300a9592665c819ee5b93237930_p0_master1200.jpg', '2021-10-22 14:27:13', '2021-10-22 14:27:13');
INSERT INTO `file` VALUES ('f00349715b5b45e082c234f80b2451be', '2021/10/24/f00349715b5b45e082c234f80b2451be300146.jpg', 2795834, 'image/jpeg', 'https://server-client.oss-cn-hangzhou.aliyuncs.com/2021/10/24/f00349715b5b45e082c234f80b2451be300146.jpg', '2021-10-24 10:34:15', '2021-10-24 10:34:15');

SET FOREIGN_KEY_CHECKS = 1;
