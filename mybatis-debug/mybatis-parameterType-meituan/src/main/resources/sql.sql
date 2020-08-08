# 创建测试表
CREATE TABLE `invoice_log` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `response` varchar(255) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

# 插入测试数据
INSERT INTO `invoice_log`(`id`, `response`, `update_time`) VALUES (1, 'success', '2020-08-05 09:54:29');
INSERT INTO `invoice_log`(`id`, `response`, `update_time`) VALUES (2, 'fail', '2020-08-06 09:54:42');
