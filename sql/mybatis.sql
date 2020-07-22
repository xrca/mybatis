CREATE TABLE `subsidy` (
  `record_id` bigint(12) NOT NULL AUTO_INCREMENT,
  `person_name` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `first_month` int(2) DEFAULT '0',
  `second_month` int(2) DEFAULT '0',
  `third_month` int(2) DEFAULT '0',
  `total_money` decimal(10,2) DEFAULT '0.00',
  `year` varchar(255) DEFAULT NULL,
  `quarter` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=82324 DEFAULT CHARSET=utf8mb4;