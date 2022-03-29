/*
 Navicat Premium Data Transfer

 Source Server         : mac
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : cloud

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 16/03/2020 23:50:00
*/

CREATE DATABASE  `HK_BIGDATA_OAUTH2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE HK_BIGDATA_OAUTH2;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL COMMENT '客户端标识',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '接入资源列表',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '客户端秘钥',
  `scope` varchar(255) DEFAULT NULL COMMENT '指定客户端的权限范围(read,write,trust)可指定多个用逗号隔开',
  `authorized_grant_types` varchar(255) DEFAULT NULL COMMENT '指定客户端支持的grant_type',
  `web_server_redirect_uri` varchar(255) DEFAULT NULL COMMENT '指定客户端的重定向URL',
  `authorities` varchar(255) DEFAULT NULL COMMENT '指定客户端所拥有的Spring Security权限值,可指定多个用逗号隔开',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '指定客户端access_token的有效时间值,单位秒',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '指定客户端refresh_token的有效时间值,单位秒',
  `additional_information` longtext COMMENT '预留字段,在OAuth2流程中没有实际使用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据的创建时间',
  `archived` tinyint(4) DEFAULT NULL,
  `trusted` tinyint(4) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL COMMENT '设置用户是否自动Approve操作,默认为false',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='接入客户端信息';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('client-1', 'resource-1', '$2a$10$HHzdDxRXzOMPMIChuYBd1uuxLNOilKcs2jwv93TSPPm7qUNAC.8/W', 'ROLE_FYSTOCK', 'client_credentials,password,authorization_code,implicit,refresh_token', NULL, NULL, 7200, 259200, NULL, CURRENT_TIMESTAMP(), 0, 0, 'false');
INSERT INTO `oauth_client_details` VALUES ('client-2', 'resource-2', '$2a$10$HHzdDxRXzOMPMIChuYBd1uuxLNOilKcs2jwv93TSPPm7qUNAC.8/W', 'ROLE_ADMIN', 'client_credentials,password,authorization_code,implicit,refresh_token', NULL, NULL, 7200, 259200, NULL, CURRENT_TIMESTAMP(), 0, 0, 'false');
COMMIT;

-- ----------------------------
-- Table structure for oauth_code
-- oauth_code：存放授权码（Authorization Code）
-- 即当client的grant type支持authorization_code时才有记录。主要操作类为JdbcAuthorizationCodeServices。
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(255) DEFAULT NULL,
  `authentication` blob,
  KEY `code_index` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `pass_word` varchar(255) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user  /*密码明文：123456 和 cubp2021*/
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'heyong', '$2a$10$WopDlEz.onEfPmU4OIu8EuHzWaXGB2G9XJXO.zik5Lv1sbZHUOI.q', 'FYSTOCK');
INSERT INTO `user` VALUES (2, 'eric.he', '$2a$10$WopDlEz.onEfPmU4OIu8EuHzWaXGB2G9XJXO.zik5Lv1sbZHUOI.q', 'ADMIN');
INSERT INTO `user` VALUES (3, 'fystock', '$2a$10$WopDlEz.onEfPmU4OIu8EuHzWaXGB2G9XJXO.zik5Lv1sbZHUOI.q', 'FYSTOCK');
INSERT INTO `user` VALUES (4, 'cubp', '$2a$10$yhAX2ODUb8GaJCmMEc/sneW98B0B0ZxtvHOTWWU22VKRv.GjqJtaq', 'FYSTOCK,ADMIN');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
