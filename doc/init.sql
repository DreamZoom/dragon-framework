-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.5.4-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 db_container.account 结构
CREATE TABLE IF NOT EXISTS `account` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT uuid() COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  `sort` int(11) NOT NULL DEFAULT 99 COMMENT '排序',
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `tell` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `nickname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `source` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '来源',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态',
  `root` int(11) NOT NULL DEFAULT 0 COMMENT '是否为根账户',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `tell` (`tell`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='账户';

-- 正在导出表  db_container.account 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `create_time`, `update_time`, `sort`, `username`, `password`, `email`, `avatar`, `tell`, `nickname`, `source`, `status`, `root`) VALUES
	('46cfd9263c07517a3bf252a04c08d60d', '2022-01-20 14:08:41', '2022-01-20 14:22:20', 99, 'cms', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, '内容管理', NULL, 1, 0),
	('7ba6ff95d09c16b65e4013930817a5e7', '2022-01-20 14:14:35', '2022-01-20 14:22:32', 99, 'admin', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, NULL, NULL, 1, 0),
	('be244ade7a2ce0649066af80837f607a', '2022-01-20 14:22:47', '2022-01-20 14:22:47', 99, 'wxllzf', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, NULL, NULL, 1, 0),
	('c75ec0b47819c2e9d72dffd37dd7ac0d', '2022-01-07 17:30:38', '2022-01-20 14:02:22', 99, 'root', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, '超级账户', NULL, 1, 1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- 导出  表 db_container.account_role 结构
CREATE TABLE IF NOT EXISTS `account_role` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT uuid() COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  `sort` int(11) NOT NULL DEFAULT 99 COMMENT '排序',
  `account_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户id',
  `role_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_account_role_account` (`account_id`),
  KEY `FK_account_role_role` (`role_id`),
  CONSTRAINT `FK_account_role_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_account_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='基本表结构';

-- 正在导出表  db_container.account_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` (`id`, `create_time`, `update_time`, `sort`, `account_id`, `role_id`) VALUES
	('62d37c19-79b9-11ec-aec4-854beab2bea2', '2022-01-20 14:22:47', '2022-01-20 14:22:47', 99, 'be244ade7a2ce0649066af80837f607a', 'fa8c2a68fa8e35704387e569aea50d97');
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;

-- 导出  表 db_container.article 结构
CREATE TABLE IF NOT EXISTS `article` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT uuid() COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  `sort` int(11) NOT NULL DEFAULT 99 COMMENT '排序',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='文章';

-- 正在导出表  db_container.article 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` (`id`, `create_time`, `update_time`, `sort`, `title`, `content`) VALUES
	('41067e21e47500e46e3a90f9a51d2382', '2022-01-21 16:17:46', '2022-01-21 16:18:50', 99, '龙神之章2', '<p data-id="pd157317-fMaT0lVF">随风潜入也</p>'),
	('d645be195f6ef7f13a1d97f2b48cba9d', '2022-01-21 16:17:41', '2022-01-21 16:18:44', 99, '龙神之章', '<p data-id="pd157317-fMaT0lVF">随风潜入也</p>');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;

-- 导出  表 db_container.base_template 结构
CREATE TABLE IF NOT EXISTS `base_template` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT uuid() COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  `sort` int(11) NOT NULL DEFAULT 99 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='基本表结构';

-- 正在导出表  db_container.base_template 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `base_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_template` ENABLE KEYS */;

-- 导出  表 db_container.category 结构
CREATE TABLE IF NOT EXISTS `category` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT uuid() COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  `sort` int(11) NOT NULL DEFAULT 99 COMMENT '排序',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `parent_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上级分类id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='文章分类';

-- 正在导出表  db_container.category 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `create_time`, `update_time`, `sort`, `name`, `parent_id`) VALUES
	('2e0b49520ade83ecaf09b2052405186a', '2022-01-21 17:03:41', '2022-01-21 17:03:41', 99, '科技', NULL),
	('35b72642b44bec87d7ff285541484a89', '2022-01-21 17:04:39', '2022-01-21 17:04:39', 99, '生活', NULL),
	('3afa564789165cff89335a20c6f40852', '2022-01-21 17:04:53', '2022-01-21 17:04:53', 99, '杂谈', NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- 导出  表 db_container.permission 结构
CREATE TABLE IF NOT EXISTS `permission` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT uuid() COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  `sort` int(11) NOT NULL DEFAULT 99 COMMENT '排序',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名',
  `title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限标题',
  `apis` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'API列表',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `key` (`title`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='权限';

-- 正在导出表  db_container.permission 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` (`id`, `create_time`, `update_time`, `sort`, `name`, `title`, `apis`) VALUES
	('98759a589b0f68c879e75dc30c132f2d', '2022-01-18 10:35:13', '2022-01-20 14:13:46', 99, 'AccountFullAccess', '账户管理权限', '["cn.dragon.cloud.passport.service.AccountService#save","cn.dragon.cloud.passport.service.AccountService#find","cn.dragon.cloud.passport.service.AccountService#delete","cn.dragon.cloud.passport.service.AccountService#queryAll","cn.dragon.cloud.passport.service.PermissionService#find","cn.dragon.cloud.passport.service.PermissionService#delete","cn.dragon.cloud.passport.service.PermissionService#save","cn.dragon.cloud.passport.service.PermissionService#queryAllPermissions","cn.dragon.cloud.passport.service.PermissionService#queryPermissions","cn.dragon.cloud.passport.service.PermissionService#queryAll","cn.dragon.cloud.passport.service.PermissionService#queryApiList","cn.dragon.cloud.passport.service.RoleService#find","cn.dragon.cloud.passport.service.RoleService#delete","cn.dragon.cloud.passport.service.RoleService#save","cn.dragon.cloud.passport.service.RoleService#queryRoles","cn.dragon.cloud.passport.service.RoleService#queryAll","cn.dragon.cloud.passport.service.RoleService#queryAllRoles","cn.dragon.cloud.passport.service.AccountService#resetPassword"]'),
	('c7c130222222b10e80df5505f78c1350', '2022-01-18 10:52:58', '2022-01-19 09:35:51', 99, 'AdministratorAccess', '管理员权限', '[]');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;

-- 导出  表 db_container.role 结构
CREATE TABLE IF NOT EXISTS `role` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT uuid() COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  `sort` int(11) NOT NULL DEFAULT 99 COMMENT '排序',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名',
  `description` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='基本表结构';

-- 正在导出表  db_container.role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `create_time`, `update_time`, `sort`, `name`, `description`) VALUES
	('fa8c2a68fa8e35704387e569aea50d97', '2022-01-17 13:39:08', '2022-01-20 10:49:33', 99, '管理员', '系统管理员');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- 导出  表 db_container.role_permission 结构
CREATE TABLE IF NOT EXISTS `role_permission` (
  `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT uuid() COMMENT '编号',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  `sort` int(11) NOT NULL DEFAULT 99 COMMENT '排序',
  `role_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色Id',
  `permission_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限Id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_role_permission_role` (`role_id`),
  KEY `FK_role_permission_permission` (`permission_id`),
  CONSTRAINT `FK_role_permission_permission` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_role_permission_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='基本表结构';

-- 正在导出表  db_container.role_permission 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` (`id`, `create_time`, `update_time`, `sort`, `role_id`, `permission_id`) VALUES
	('993e6384-799b-11ec-aec4-854beab2bea2', '2022-01-20 10:49:33', '2022-01-20 10:49:33', 99, 'fa8c2a68fa8e35704387e569aea50d97', 'c7c130222222b10e80df5505f78c1350'),
	('993e7552-799b-11ec-aec4-854beab2bea2', '2022-01-20 10:49:33', '2022-01-20 10:49:33', 99, 'fa8c2a68fa8e35704387e569aea50d97', '98759a589b0f68c879e75dc30c132f2d');
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
