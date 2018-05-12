CREATE TABLE `ba_area` (
  `area_id` varchar(30) NOT NULL,
  `area_name` varchar(255) DEFAULT NULL,
  `area_code` varchar(50) DEFAULT NULL,
  `parent_id` varchar(30) DEFAULT NULL,
  `simple_name` varchar(255) DEFAULT NULL,
  `pin_yin` varchar(255) DEFAULT NULL,
  `pre_pin_yin` varchar(10) DEFAULT NULL,
  `simple_py` varchar(50) DEFAULT NULL,
  `level` int(1) DEFAULT NULL,
  `zip_code` varchar(10) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `lon` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  KEY `ba_area_1` (`area_name`,`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

