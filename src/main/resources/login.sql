-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.13 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for login
DROP DATABASE IF EXISTS `login`;
CREATE DATABASE IF NOT EXISTS `login` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `login`;

-- Dumping structure for table login.file
DROP TABLE IF EXISTS `file`;
CREATE TABLE IF NOT EXISTS `file` (
  `id` bigint(20) NOT NULL,
  `filename` varchar(45) DEFAULT NULL,
  `content` longblob,
  `creation_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table login.file: ~0 rows (approximately)
DELETE FROM `file`;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` (`id`, `filename`, `content`, `creation_date`, `update_date`) VALUES
	(23, 'test1.txt', _binary 0x4351436C367261464B7059446C642B6A49794453532B6476356D745A5074396F425A435A7874423938785756385063437579534B786952657238486C575A3857575467704254376748345055495A4179494A2B75366663476B414E664F757A6857586268416255614E7073344C6D5574597143776D6B38583761356F71667769633054375A2B59567765745152343948514C6962715371346977677870424B786B5933635930644D794D493758666D434D49576A4B5477704931705067444F30734E4233612B38326A6D7078424F5879626D7457452B4E566578372F376B6358433076544B3233536A47417A454876534A754E67434761662F33783662304670364367546E4B4F3942564D787A53736476746664645A524B67685371713872796954575A456659374A5167636379334762727A66322B58452F6E44783761534F47662F2B6470687169715A4139764E30642B574C61673D3D, '2019-03-04 14:36:28', '2019-03-04 14:36:29');
/*!40000 ALTER TABLE `file` ENABLE KEYS */;

-- Dumping structure for table login.file_history
DROP TABLE IF EXISTS `file_history`;
CREATE TABLE IF NOT EXISTS `file_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uploadedby` varchar(50) DEFAULT NULL,
  `fileId` bigint(20) DEFAULT NULL,
  `uploadtype` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fileid` (`fileId`),
  CONSTRAINT `FK_fileid` FOREIGN KEY (`fileId`) REFERENCES `file` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table login.file_history: ~4 rows (approximately)
DELETE FROM `file_history`;
/*!40000 ALTER TABLE `file_history` DISABLE KEYS */;
INSERT INTO `file_history` (`id`, `uploadedby`, `fileId`, `uploadtype`) VALUES
	(24, 'test@test.com', 23, 'Initial'),
	(26, 'raj.kumar@gmail.com', 23, 'Secondary');
/*!40000 ALTER TABLE `file_history` ENABLE KEYS */;

-- Dumping structure for table login.hibernate_sequence
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table login.hibernate_sequence: 1 rows
DELETE FROM `hibernate_sequence`;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(27);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table login.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table login.role: 1 rows
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`role_id`, `role`) VALUES
	(1, 'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table login.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table login.user: 1 rows
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `active`, `email`, `last_name`, `name`, `password`) VALUES
	(1, 1, 'test@test.com', 'Kumar', 'Sumit', '$2a$10$mvqVn0HZJobSlGeJXozYR.ittZt4ymKfj8XKx.S2rmxmiFrS6rI2u'),
	(25, 1, 'raj.kumar@gmail.com', 'Kumar', 'Raj', '$2a$10$3hEmET9fr5DL6jsiB5ia/.LLFPa/7yZHS/RyfKppV0fPaapb8saXK');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table login.user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table login.user_role: 1 rows
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(1, 1),
	(25, 1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
