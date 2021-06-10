-- Adminer 4.7.7 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `yi_ip_flights`;
CREATE TABLE `yi_ip_flights` (
  `id_fly` int NOT NULL AUTO_INCREMENT,
  `fly_id` varchar(45) DEFAULT NULL,
  `fly_date` text,
  `relation` text,
  `outcome_state` set('POLETEO','CEKA SE','NEPOZNATO') DEFAULT 'NEPOZNATO',
  `income_state` set('SLETEO','CEKA SE','NEPOZNATO') DEFAULT 'NEPOZNATO',
  PRIMARY KEY (`id_fly`),
  UNIQUE KEY `fly_id` (`fly_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `yi_ip_flights` (`id_fly`, `fly_id`, `fly_date`, `relation`, `outcome_state`, `income_state`) VALUES
(18,	'АС-ТТ-001-ОП-ЛТ',	'31.12.2021.',	'АС-ТТ-001-ОП',	'NEPOZNATO',	'NEPOZNATO'),
(17,	'АС-101-ОП-002',	'26.5.2021.',	'АС-101-ОП',	'NEPOZNATO',	'NEPOZNATO'),
(15,	'АС-101-ОП-001',	'19.5.2021.',	'АС-101-ОП',	'NEPOZNATO',	'NEPOZNATO'),
(19,	'АС-101-ОП-003',	'10.6.2021.',	'АС-101-ОП',	'NEPOZNATO',	'NEPOZNATO'),
(20,	'АС-ТТ-002-ОП-ЛТ',	'5.6.2021.',	'АС-ТТ-001-ОП',	'NEPOZNATO',	'NEPOZNATO'),
(21,	'АС-ТТ-003-ОП-ЛТ',	'5.6.2021.',	'АС-ТТ-001-ОП',	'NEPOZNATO',	'NEPOZNATO'),
(22,	'АС-101-ОП-004',	'5.6.2021.',	'АС-101-ОП',	'NEPOZNATO',	'NEPOZNATO'),
(23,	'АС-102-ОП-001',	'5.6.2021.',	'АС-102-ОП',	'NEPOZNATO',	'NEPOZNATO'),
(24,	'АС-102-ОП-002',	'6.6.2021.',	'АС-102-ОП',	'POLETEO',	'SLETEO'),
(26,	'АС-ТТ-004-ОП-ЛТ',	'15.6.2021.',	'АС-ТТ-001-ОП',	'NEPOZNATO',	'NEPOZNATO'),
(27,	'АС-101-ОП-005',	'5.6.2021.',	'АС-101-ОП',	'CEKA SE',	'NEPOZNATO'),
(28,	'АС-101-ОП-006',	'7.6.2021.',	'АС-101-ОП',	'CEKA SE',	'CEKA SE'),
(29,	'АС-101-ОП-007',	'15.6.2021.',	'АС-101-ОП',	'POLETEO',	'SLETEO'),
(30,	'АС-ТТ-00S-ОП-ЛТ',	'15.6.2021.',	'АС-ТТ-001-ОП',	'NEPOZNATO',	'NEPOZNATO');

DROP TABLE IF EXISTS `yi_ip_reservations`;
CREATE TABLE `yi_ip_reservations` (
  `id_reservation` int NOT NULL AUTO_INCREMENT,
  `reservation_id` varchar(100) NOT NULL,
  `fly_id` varchar(100) NOT NULL,
  `place_count` int DEFAULT NULL,
  `article_description` text,
  `article_transport_file` varchar(300) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `date_reservation` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `reservation_state` set('NEW','ACCEPT','FORBIDEN') DEFAULT 'NEW',
  `reservation_closed` set('YES_CANCELED','NO_CANCELED') DEFAULT 'NO_CANCELED',
  PRIMARY KEY (`id_reservation`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `yi_ip_reservations` (`id_reservation`, `reservation_id`, `fly_id`, `place_count`, `article_description`, `article_transport_file`, `username`, `date_reservation`, `reservation_state`, `reservation_closed`) VALUES
(2,	'АС-101-ОП-РВ.001',	'АС-101-ОП-001',	5,	'Не превози терет. ',	'',	'petar',	'2021-06-02 13:10:19',	'ACCEPT',	'NO_CANCELED'),
(4,	'АС-101-ОП-РВТ.001',	'АС-101-ОП-002',	0,	'Превоз прехрамбене робе.',	'%D0%90%D0%A1-101-%D0%9E%D0%9F-%D0%A0%D0%92%D0%A2.001_AST-001-Prehrana-PoljodelacFirm.pdf',	'petar',	'2021-06-02 13:10:19',	'ACCEPT',	'NO_CANCELED'),
(5,	'АС-101-ОП-РВ.002',	'АС-101-ОП-001',	3,	'Не превози терет. ',	'',	'zeljko',	'2021-06-02 13:10:19',	'ACCEPT',	'NO_CANCELED'),
(6,	'АС-101-ОП-РВТ.002',	'АС-101-ОП-002',	0,	'Превоз машинских дијелова. ',	'',	'zeljko',	'2021-06-02 13:10:19',	'ACCEPT',	'NO_CANCELED'),
(10,	'АС-ТТ-001-ОП-ЛТ.РВТ.001',	'АС-ТТ-001-ОП-ЛТ',	0,	'Превоз прехрамбене робе.',	'%D0%90%D0%A1-%D0%A2%D0%A2-001-%D0%9E%D0%9F-%D0%9B%D0%A2.%D0%A0%D0%92%D0%A2.001_AST-001-Prehrana-PoljodelacFirm.odt',	'petar',	'2021-06-02 13:10:19',	'ACCEPT',	'NO_CANCELED'),
(21,	'RVT.074718',	'АС-ТТ-001-ОП-ЛТ',	-1,	'Превоз рачунарске опреме. ',	'',	'petar',	'2021-06-02 13:10:19',	'NEW',	'NO_CANCELED'),
(19,	'RV.014842',	'АС-101-ОП-002',	48,	'',	'',	'petar',	'2021-06-02 13:10:19',	'FORBIDEN',	'NO_CANCELED'),
(26,	'RV.055550',	'АС-101-ОП-002',	22,	'',	'',	'petar',	'2021-06-02 13:10:19',	'NEW',	'NO_CANCELED');

DROP TABLE IF EXISTS `yi_ip_users_user_accounts_nianses`;
CREATE TABLE `yi_ip_users_user_accounts_nianses` (
  `id_nianse` int NOT NULL AUTO_INCREMENT,
  `nianse_id` varchar(100) DEFAULT NULL,
  `nianse_type` set('TRANSPORT','TRAVEL','') NOT NULL,
  `username` varchar(100) NOT NULL,
  PRIMARY KEY (`id_nianse`),
  UNIQUE KEY `nianse_id` (`nianse_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `yi_ip_users_user_accounts_nianses` (`id_nianse`, `nianse_id`, `nianse_type`, `username`) VALUES
(1,	'petar.PROFILE.TRAVEL',	'TRAVEL',	'petar'),
(2,	'petar.PROFILE.TRANSPORT',	'TRANSPORT',	'petar'),
(3,	'marko.PROFILE.TRAVEL',	'TRAVEL',	'marko'),
(4,	'marko.PROFILE.TRANSPORT',	'',	'marko'),
(5,	'zeljko.PROFILE.TRAVEL',	'TRAVEL',	'zeljko'),
(6,	'zeljko.PROFILE.TRANSPORT',	'TRANSPORT',	'zeljko');

DROP TABLE IF EXISTS `yi_locations`;
CREATE TABLE `yi_locations` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `location_name` varchar(200) DEFAULT NULL,
  `location_address` varchar(1000) DEFAULT NULL,
  `location_note` text,
  `location_country` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `yi_locations` (`location_id`, `location_name`, `location_address`, `location_note`, `location_country`) VALUES
(135,	'Москва',	'',	'',	'RU'),
(136,	'Осло',	'',	'',	'NO'),
(137,	'Солун',	'',	'',	'GR'),
(138,	'Бања Лука',	'',	'',	'RS'),
(139,	'Париз',	'',	'',	'FR'),
(140,	'Београд',	'',	'',	'RS'),
(141,	'Љубљана',	'',	'',	'SI'),
(146,	'Загреб',	'',	'',	'HR'),
(133,	'Франкфурт',	'',	'',	'DE'),
(134,	'Лондон',	'',	'',	'GB'),
(132,	'Ниш',	'',	'',	'RS');

DROP TABLE IF EXISTS `yi_traffic_relations`;
CREATE TABLE `yi_traffic_relations` (
  `id_traffic_relacion` int NOT NULL AUTO_INCREMENT,
  `rt_word` varchar(100) NOT NULL,
  `place` varchar(1000) NOT NULL,
  `rt_number` varchar(100) NOT NULL DEFAULT '',
  `rt_type` varchar(100) NOT NULL DEFAULT '',
  `rt_subtype` varchar(100) NOT NULL DEFAULT '',
  `relation` text NOT NULL,
  `income` text NOT NULL,
  `outcome` text NOT NULL,
  `direction` varchar(100) NOT NULL DEFAULT '',
  `location` varchar(200) NOT NULL DEFAULT '',
  `destination` varchar(200) NOT NULL DEFAULT '',
  `rt_notes` text NOT NULL,
  PRIMARY KEY (`id_traffic_relacion`),
  UNIQUE KEY `rt_word` (`rt_word`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `yi_traffic_relations` (`id_traffic_relacion`, `rt_word`, `place`, `rt_number`, `rt_type`, `rt_subtype`, `relation`, `income`, `outcome`, `direction`, `location`, `destination`, `rt_notes`) VALUES
(82,	'АС-101-ПП',	'Солун',	'101',	'АВИОСАОБРАЋАЈ',	'ПУТНИЧКИ',	'ЉУБЉАНА - СОЛУН',	'уторак, четвртак, 13:00',	'уторак, четвртак 12:00',	'долазак',	'',	'Љубљана',	'\r\nБоинг 747\r\nОлимпик Аирлајнес\r\n1. разред\r\nПословна класа\r\n\r\n'),
(81,	'АС-101-ОП',	'Љубљана',	'101',	'АВИОСАОБРАЋАЈ',	'ПУТНИЧКИ',	'ЉУБЉАНА - СОЛУН',	'уторак, четвртак, 13:00',	'уторак, четвртак 12:00',	'одлазак',	'',	'Солун',	'\r\nБоинг 747\r\nОлимпик Аирлајнес\r\n1. разред\r\nПословна класа\r\n\r\n'),
(84,	'АС-102-ПП',	'Солун',	'102',	'АВИОСАОБРАЋАЈ',	'ПУТНИЧКИ',	'СОЛУН - ЉУБЉАНА',	'сриједа, петак 11:00',	'сриједа, петак 09:00',	'одлазак',	'',	'Љубљана',	'\r\nБоинг 747\r\nОлимпик Аирлајнес\r\n1. разред\r\nПословна класа\r\n\r\n'),
(83,	'АС-102-ОП',	'Љубљана',	'102',	'АВИОСАОБРАЋАЈ',	'ПУТНИЧКИ',	'СОЛУН - ЉУБЉАНА',	'сриједа, петак 11:00',	'сриједа, петак 09:00',	'долазак',	'',	'Солун',	'\r\nБоинг 747\r\nОлимпик Аирлајнес\r\n1. разред\r\nПословна класа\r\n\r\n'),
(89,	'АС-ТТ-001-ОП',	'Београд',	'ТТ-001',	'АВИОСАОБРАЋАЈ',	'ТЕРЕТНИ',	'БАЊА ЛУКА - БЕОГРАД',	'31.1.2021 23:00',	'1.1.2022. 14:00',	'долазак',	'',	'Бања Лука',	'\r\nБоинг 1000\r\nЈатоспеис Аирлинес\r\nТеретни авион\r\n\r\n'),
(90,	'АС-ТТ-001-ПП',	'Бања Лука',	'ТТ-001',	'АВИОСАОБРАЋАЈ',	'ТЕРЕТНИ',	'БАЊА ЛУКА - БЕОГРАД',	'31.1.2021 23:00',	'1.1.2022. 14:00',	'одлазак',	'',	'Београд',	'\r\nБоинг 1000\r\nЈатоспеис Аирлинес\r\nТеретни авион\r\n\r\n');

DROP TABLE IF EXISTS `yi_users`;
CREATE TABLE `yi_users` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `passwordcode` varchar(1000) DEFAULT NULL,
  `user_data` int DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `fk_yi_users_yi_users_info_idx` (`user_data`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `yi_users` (`id_user`, `username`, `passwordcode`, `user_data`) VALUES
(38,	'mitar',	'9170738052$LUSaB5jLMGj9HzHfnJ1HJujWXEcFZjmSXGiBkDUmtrc=',	38),
(15,	'jovan',	'9427207104$z/LnoYjwGj6xbMcbCWK2yhOmB258HkbFW15Fq/QUJbE=',	15),
(16,	'marko',	'2000248034$kMD/NDujlX7x2DaP9v/SZlnolVzILnMP0zoIOdRq6lw=',	16),
(13,	'petar',	'3506609273$6IV4Lrb3t+jk/3e3pUfzj+JjHSCDmUvAfLxPouQy3z4=',	13),
(28,	'miljan',	'1212714587$/AA9jTSlJi0O+GfnPPOtHzRjRXjzGEJphdHkCVECoGc=',	28),
(22,	'milan',	'5819247105$Fx3xzqLye2aj8PUa0NyKsI3VOU97p4TzcsPPMhpdrX8=',	22),
(29,	'nikolina',	'1424702055$nHH7I25xBCLSWZwp8FxpSSF0a1xp08Tp0al83uQi13g=',	29),
(37,	'marjana',	'8364207128$eHh/+lwCbRsSOl3kLL18jfHoDQoFB1XMPBIr+SXangU=',	37),
(36,	'zeljko',	'2208689107$OqSVGz/8xP4STUOg4I4fG0bu9tquWNFwvNrFSBwsMpA=',	36),
(52,	'risto',	'9170738052$3pWIDaEU9ylfPi7Ml6d1v7MBP+H1foQ+ud/vtRqe10Q=',	52),
(53,	'dijana',	'2772202743$YJLLxzdiON9hs5mePKWIQYC9ZlgbYxTTJF1/SbCo5j8=',	NULL),
(54,	'marijan',	'1846200363$tCeX6/qMVOHk6x2xrKAkzUxII5Dgq7Uh5GAP4LjmENU=',	54);

DELIMITER ;;

CREATE TRIGGER `yi_users_BI` BEFORE INSERT ON `yi_users` FOR EACH ROW
BEGIN 
	IF NEW.username = 'root' THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Forbiden username for registration';
	END IF;
END;;

CREATE TRIGGER `yi_users_BU` BEFORE UPDATE ON `yi_users` FOR EACH ROW
BEGIN 
	IF NEW.username = 'root' THEN 
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Forbiden username for registration';
	END IF;
END;;

CREATE TRIGGER `yi_users_AD` AFTER DELETE ON `yi_users` FOR EACH ROW
BEGIN 
	DELETE FROM yi_users_info WHERE yi_user_data = OLD.id_user;
    DELETE FROM yi_users_role WHERE id_user_role = OLD.id_user; 
END;;

DELIMITER ;

DROP TABLE IF EXISTS `yi_users_communications_ip_aerolines_company`;
CREATE TABLE `yi_users_communications_ip_aerolines_company` (
  `id_communication` int NOT NULL AUTO_INCREMENT,
  `communication_id` varchar(100) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  `contact_email` varchar(200) DEFAULT '',
  PRIMARY KEY (`id_communication`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `communication_id` (`communication_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `yi_users_communications_ip_aerolines_company` (`id_communication`, `communication_id`, `username`, `contact_email`) VALUES
(1,	'EMAIL:petar.petrovic@gmail.com',	'petar',	'petar.petrovic@gmail.com'),
(2,	'EMAIL:zeljko.zeljkovic@outlook.com',	'zeljko',	'zeljko.zeljkovic@outlook.com');

DROP TABLE IF EXISTS `yi_users_info`;
CREATE TABLE `yi_users_info` (
  `yi_user_data` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `second_name` varchar(50) NOT NULL,
  `user_notes` text NOT NULL,
  PRIMARY KEY (`yi_user_data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `yi_users_info` (`yi_user_data`, `first_name`, `second_name`, `user_notes`) VALUES
(13,	'Петар',	'Петровић',	'Бања Лука'),
(15,	'Јован',	'Јовановић',	'Брчко'),
(16,	'Марко',	'Марковић',	'Модрича'),
(22,	'Милан',	'Милановић',	'Требиње'),
(28,	'Миљан',	'Миљановић',	'Шамац.'),
(29,	'Николина',	'Николић',	'Требиње.'),
(36,	'Жељко',	'Жељковић',	'Београд. '),
(37,	'Маријана',	'Марјановић',	'Загреб.'),
(38,	'Митар',	'Митровић',	'Невесиње. '),
(52,	'Ристо',	'Ристић',	'Гацко.'),
(54,	'Marijan',	'Marijanovic',	'Maranjanovici. ');

DROP TABLE IF EXISTS `yi_users_locations_ip_aerolines_company`;
CREATE TABLE `yi_users_locations_ip_aerolines_company` (
  `id_location` int NOT NULL AUTO_INCREMENT,
  `location_id` varchar(100) DEFAULT NULL,
  `location_ref` varchar(200) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  `location_address` text,
  `location_country` text,
  `location_note` text,
  PRIMARY KEY (`id_location`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `location_id` (`location_id`),
  KEY `location_found_edge` (`location_ref`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `yi_users_locations_ip_aerolines_company` (`id_location`, `location_id`, `location_ref`, `username`, `location_address`, `location_country`, `location_note`) VALUES
(1,	'petar',	NULL,	'petar',	'Хелсинки',	'FI',	'Улица снијега, 15, Хелсинки'),
(2,	'zeljko',	NULL,	'zeljko',	'Рим',	'IT',	'Главна улица, 14, Рим');

DROP TABLE IF EXISTS `yi_users_messages_ip_aeroline_company`;
CREATE TABLE `yi_users_messages_ip_aeroline_company` (
  `id_message` int NOT NULL AUTO_INCREMENT,
  `message_id` varchar(100) NOT NULL,
  `message_value` text,
  `vreme` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `vreme_promene` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(100) DEFAULT NULL,
  `observed` tinyint(1) NOT NULL DEFAULT '0',
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_message`),
  UNIQUE KEY `message_id` (`message_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `yi_users_messages_ip_aeroline_company` (`id_message`, `message_id`, `message_value`, `vreme`, `vreme_promene`, `username`, `observed`, `email`) VALUES
(1,	'MESSAGE_2021-05-22-06-25-41_petar',	'Данас је леп мајски дан. ',	'2021-05-22 04:25:41',	'2021-05-22 04:25:41',	'petar',	1,	NULL),
(2,	'MESSAGE_2021-05-22-06-26-29_marko',	'Данас је сунчан мајски дан.',	'2021-05-22 04:26:29',	'2021-05-22 04:26:29',	'marko',	0,	NULL),
(3,	'MESSAGE_2021-05-22-06-26-47_petar',	'Лепота. ',	'2021-05-22 04:26:47',	'2021-05-22 04:26:47',	'petar',	1,	NULL),
(4,	'MESSAGE_2021-05-22-06-31-58_petar',	'Ово је још нека порука. ',	'2021-05-22 04:31:58',	'2021-05-22 04:31:58',	'petar',	0,	NULL),
(5,	'MESSAGE_2021-05-22-06-41-29_zeljko',	'Internet Programiranje. Dovrsavanje projektnog. ',	'2021-05-22 04:41:29',	'2021-05-22 04:41:29',	'zeljko',	0,	NULL),
(6,	'MESSAGE_2021-05-22-06-44-05_zeljko',	'Internet Programiranje. Dovrsavanje projektnog. </textare>',	'2021-05-22 04:44:05',	'2021-05-22 04:44:05',	'zeljko',	1,	NULL),
(7,	'MESSAGE_2021-05-22-06-44-06_zeljko',	'Internet Programiranje. Dovrsavanje projektnog. </textare>',	'2021-05-22 04:44:06',	'2021-05-22 04:44:06',	'zeljko',	0,	NULL),
(8,	'MESSAGE_2021-05-22-06-44-42_zeljko',	'Internet Programiranje. Dovrsavanje projektnog. </textarea> Dalje.',	'2021-05-22 04:44:42',	'2021-05-22 04:44:42',	'zeljko',	0,	NULL),
(9,	'MESSAGE_2021-05-22-08-18-32_petar',	'Huawei poruka.',	'2021-05-22 06:18:32',	'2021-05-22 06:18:32',	'petar',	0,	NULL),
(10,	'MESSAGE_2021-05-22-08-18-58_petar',	'Huawei poruka.\r\nXiaomni poruka.\r\nOppo poruka.',	'2021-05-22 06:18:58',	'2021-05-22 07:32:38',	'petar',	0,	NULL),
(11,	'MESSAGE_2021-05-22-08-21-35_zeljko',	'Prva recenica. \r\nDruga recenica. ',	'2021-05-22 06:21:35',	'2021-05-22 06:21:35',	'zeljko',	0,	NULL),
(14,	'MESSAGE_2021-05-22-16-27-56_petar',	'Hello Client Server Application. \r\nHello Service and MVC Application. \r\nHello Dekstop Web Console Application.\r\nHello Distributed and Node Application. \r\nПоздрав за програмере. ',	'2021-05-22 14:27:56',	'2021-05-22 14:27:56',	'petar',	0,	NULL),
(17,	'MSG_2021-06-05-14-42-14_001',	'Ово је прва порука послана са начелне странице. \r\nОво је њен други ред. ',	'2021-06-05 12:42:14',	'2021-06-05 12:42:14',	'',	0,	'neki.email@gmail.com'),
(18,	'MSG_2021-06-05-18-41-29_001',	'Ово је друга порука. \r\nОво је њен други ред. ',	'2021-06-05 16:41:29',	'2021-06-05 16:41:29',	'',	0,	'neki.email@gmail.com');

DROP TABLE IF EXISTS `yi_users_role`;
CREATE TABLE `yi_users_role` (
  `id_user_role` int NOT NULL AUTO_INCREMENT,
  `application` varchar(100) NOT NULL,
  `key` varchar(100) NOT NULL,
  `value` varchar(100) NOT NULL,
  `user` int NOT NULL,
  PRIMARY KEY (`id_user_role`),
  KEY `fk_yi_users_role_yi_users_idx` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `yi_users_role` (`id_user_role`, `application`, `key`, `value`, `user`) VALUES
(69,	'aeroflot_users',	'user',	'true',	13),
(70,	'aeroflot_users',	'worker',	'false',	13),
(71,	'aeroflot_users',	'administrator',	'false',	13),
(72,	'aeroflot_users',	'user',	'true',	16),
(73,	'aeroflot_users',	'worker',	'true',	16),
(74,	'aeroflot_users',	'administrator',	'false',	16),
(75,	'aeroflot_users',	'user',	'false',	15),
(76,	'aeroflot_users',	'worker',	'false',	15),
(77,	'aeroflot_users',	'administrator',	'true',	15),
(93,	'aeroflot_users',	'worker',	'true',	22),
(94,	'aeroflot_users',	'administrator',	'false',	22),
(95,	'aeroflot_users',	'user',	'false',	22),
(111,	'aeroflot_users',	'worker',	'true',	28),
(112,	'aeroflot_users',	'administrator',	'false',	28),
(113,	'aeroflot_users',	'user',	'false',	28),
(114,	'aeroflot_users',	'worker',	'false',	29),
(115,	'aeroflot_users',	'administrator',	'false',	29),
(116,	'aeroflot_users',	'user',	'true',	29),
(132,	'aeroflot_users',	'worker',	'true',	36),
(133,	'aeroflot_users',	'administrator',	'true',	36),
(134,	'aeroflot_users',	'user',	'true',	36),
(135,	'aeroflot_users',	'worker',	'true',	37),
(136,	'aeroflot_users',	'administrator',	'true',	37),
(137,	'aeroflot_users',	'user',	'true',	37),
(138,	'aeroflot_users',	'user',	'true',	38),
(139,	'aeroflot_users',	'worker',	'true',	38),
(140,	'aeroflot_users',	'administrator',	'true',	38),
(177,	'aeroflot_users',	'user',	'false',	52),
(178,	'aeroflot_users',	'worker',	'true',	52),
(179,	'aeroflot_users',	'administrator',	'true',	52),
(180,	'aeroflot_users',	'user',	'false',	53),
(181,	'aeroflot_users',	'worker',	'false',	53),
(182,	'aeroflot_users',	'administrator',	'false',	53),
(183,	'aeroflot_users',	'worker',	'true',	54),
(184,	'aeroflot_users',	'administrator',	'false',	54),
(185,	'aeroflot_users',	'user',	'true',	54);

-- 2021-06-10 19:06:26
