/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Iddrisu Sibdow SIAJ
 * Created: 12-Mar-2018
 */


/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.1.26-MariaDB : Database - birdsapp_db
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`birdsapp_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `birdsapp_db`;

/*Table structure for table `birth_cert_request` */

DROP TABLE IF EXISTS `birth_cert_request`;

CREATE TABLE `birth_cert_request` (
  `birth_cert_request_id` varchar(70) NOT NULL,
  `system_user_id` varchar(70) NOT NULL,
  `district` varchar(50) NOT NULL,
  `birth_applicant_id` varchar(70) NOT NULL,
  `district_approved` varchar(10) DEFAULT NULL,
  `regional_approved` varchar(10) DEFAULT NULL,
  `cert_printed` varchar(10) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`birth_cert_request_id`),
  KEY `FK_birth_cert_request` (`birth_applicant_id`),
  KEY `FK_birth_cert_request1` (`system_user_id`),
  CONSTRAINT `FK_birth_cert_request` FOREIGN KEY (`birth_applicant_id`) REFERENCES `child_birth_detail` (`birth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `child_birth_detail` */

DROP TABLE IF EXISTS `child_birth_detail`;

CREATE TABLE `child_birth_detail` (
  `birth_id` varchar(70) NOT NULL,
  `surname` varchar(70) DEFAULT NULL,
  `othername` varchar(70) DEFAULT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `place_of_birth` varchar(50) DEFAULT NULL,
  `town_delivered` varchar(50) DEFAULT NULL,
  `guardian` varchar(70) DEFAULT NULL,
  `informant_birth` varchar(70) DEFAULT NULL,
  `date_of_registration` date DEFAULT NULL,
  `time_of_registration` time DEFAULT NULL,
  `system_user` varchar(70) DEFAULT NULL,
  `center_registered` varchar(70) DEFAULT NULL,
  `district_approved` varchar(10) DEFAULT NULL,
  `regional_approved` varchar(10) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`birth_id`),
  KEY `FK_child_birth_detail` (`guardian`),
  KEY `FK_child_birth_detail1` (`informant_birth`),
  KEY `FK_child_birth_detail2` (`system_user`),
  CONSTRAINT `FK_child_birth_detail` FOREIGN KEY (`guardian`) REFERENCES `child_guardian` (`guardian_id`),
  CONSTRAINT `FK_child_birth_detail1` FOREIGN KEY (`informant_birth`) REFERENCES `informant_birth` (`informant_birth_id`),
  CONSTRAINT `FK_child_birth_detail2` FOREIGN KEY (`system_user`) REFERENCES `system_user` (`system_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `child_guardian` */

DROP TABLE IF EXISTS `child_guardian`;

CREATE TABLE `child_guardian` (
  `guardian_id` varchar(70) NOT NULL,
  `mum_surname` varchar(50) DEFAULT NULL,
  `mum_othername` varchar(50) DEFAULT NULL,
  `mum_fullname` varchar(100) DEFAULT NULL,
  `mum_age` int(70) DEFAULT NULL,
  `mum_nationality` varchar(50) DEFAULT NULL,
  `mum_contact` varchar(70) DEFAULT NULL,
  `mum_id_type` varchar(50) DEFAULT NULL,
  `mum_id_num` varchar(70) DEFAULT NULL,
  `mum_residence` tinytext,
  `dad_surname` varchar(50) DEFAULT NULL,
  `dad_othername` varchar(50) DEFAULT NULL,
  `dad_fullname` varchar(100) DEFAULT NULL,
  `dad_age` int(70) DEFAULT NULL,
  `dad_nationality` varchar(50) DEFAULT NULL,
  `dad_id_type` varchar(50) DEFAULT NULL,
  `dad_id_num` varchar(70) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`guardian_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `death_cert_request` */

DROP TABLE IF EXISTS `death_cert_request`;

CREATE TABLE `death_cert_request` (
  `death_cert_request_id` varchar(70) NOT NULL,
  `system_user` varchar(70) DEFAULT NULL,
  `deceased_details` varchar(70) DEFAULT NULL,
  `district` varchar(70) DEFAULT NULL,
  `district_approved` varchar(10) DEFAULT NULL,
  `regional_approved` varchar(10) DEFAULT NULL,
  `cert_printed` varchar(10) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`death_cert_request_id`),
  KEY `FK_death_cert_request` (`deceased_details`),
  CONSTRAINT `FK_death_cert_request` FOREIGN KEY (`deceased_details`) REFERENCES `deceased_detail` (`deceased_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `deceased_detail` */

DROP TABLE IF EXISTS `deceased_detail`;

CREATE TABLE `deceased_detail` (
  `deceased_id` varchar(70) NOT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `othername` varchar(50) DEFAULT NULL,
  `deceased_fullname` varchar(100) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `deceased_residence` text,
  `nationality` varchar(50) DEFAULT NULL,
  `id_type` varchar(50) DEFAULT NULL,
  `id_num` varchar(70) DEFAULT NULL,
  `date_of_death` date DEFAULT NULL,
  `age_of_death` int(10) DEFAULT NULL,
  `place_of_burial` varchar(50) DEFAULT NULL,
  `address_of_burial_place` varchar(100) DEFAULT NULL,
  `place_of_death` varchar(100) DEFAULT NULL,
  `address_of_death_place` varchar(100) DEFAULT NULL,
  `cause_of_death` varchar(100) DEFAULT NULL,
  `system_user` varchar(70) DEFAULT NULL,
  `registration_type` varchar(70) DEFAULT NULL,
  `date_of_registration` date DEFAULT NULL,
  `parent_name` varchar(100) DEFAULT NULL,
  `parent_relation` varchar(50) DEFAULT NULL,
  `parent_id_type` varchar(50) DEFAULT NULL,
  `parent_id_num` varchar(70) DEFAULT NULL,
  `parent_nationality` varchar(50) DEFAULT NULL,
  `center_registered` varchar(70) DEFAULT NULL,
  `district_approved` varchar(10) DEFAULT NULL,
  `regional_approved` varchar(10) DEFAULT NULL,
  `informant_death` varchar(70) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`deceased_id`),
  KEY `FK_deceased_detail` (`system_user`),
  KEY `FK_deceased_detail1` (`informant_death`),
  CONSTRAINT `FK_deceased_detail` FOREIGN KEY (`system_user`) REFERENCES `system_user` (`system_user_id`),
  CONSTRAINT `FK_deceased_detail1` FOREIGN KEY (`informant_death`) REFERENCES `informant_death` (`informant_death_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `district` */

DROP TABLE IF EXISTS `district`;

CREATE TABLE `district` (
  `district_id` varchar(70) NOT NULL,
  `district_name` varchar(50) DEFAULT NULL,
  `region` varchar(50) DEFAULT NULL,
  `primary_contact` varchar(50) DEFAULT NULL,
  `seconday_contact` varchar(50) DEFAULT NULL,
  `physical_location` text,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`district_id`),
  KEY `region` (`region`),
  CONSTRAINT `district_ibfk_1` FOREIGN KEY (`region`) REFERENCES `region` (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `district_center` */

DROP TABLE IF EXISTS `district_center`;

CREATE TABLE `district_center` (
  `center_id` varchar(70) NOT NULL,
  `center_name` varchar(50) DEFAULT NULL,
  `center_type` varchar(50) DEFAULT NULL,
  `center_location` varchar(50) DEFAULT NULL,
  `region_under` varchar(50) DEFAULT NULL,
  `district_under` varchar(70) DEFAULT NULL,
  `primary_contact` varchar(50) DEFAULT NULL,
  `secondary_contact` varchar(50) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`center_id`),
  KEY `FK_district_center` (`district_under`),
  CONSTRAINT `district_center_ibfk_1` FOREIGN KEY (`district_under`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `gen_id` */

DROP TABLE IF EXISTS `gen_id`;

CREATE TABLE `gen_id` (
  `gen_table_id` varchar(70) NOT NULL,
  `district_code` varchar(70) DEFAULT NULL,
  `dist_last_birth_num` int(40) DEFAULT NULL,
  `dist_last_death_num` int(40) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`gen_table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `informant_birth` */

DROP TABLE IF EXISTS `informant_birth`;

CREATE TABLE `informant_birth` (
  `informant_birth_id` varchar(70) NOT NULL,
  `informant_name` varchar(100) DEFAULT NULL,
  `relationship` varchar(50) DEFAULT NULL,
  `id_type` varchar(50) DEFAULT NULL,
  `id_num` varchar(70) DEFAULT NULL,
  `residence` varchar(100) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`informant_birth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `informant_death` */

DROP TABLE IF EXISTS `informant_death`;

CREATE TABLE `informant_death` (
  `informant_death_id` varchar(70) NOT NULL,
  `informant_name` varchar(100) DEFAULT NULL,
  `informant_address` varchar(100) DEFAULT NULL,
  `informant_contact` varchar(50) DEFAULT NULL,
  `id_type` varchar(50) DEFAULT NULL,
  `id_num` varchar(70) DEFAULT NULL,
  `relationship` varchar(70) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`informant_death_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `region` */

DROP TABLE IF EXISTS `region`;

CREATE TABLE `region` (
  `region_id` varchar(70) NOT NULL,
  `region_name` varchar(70) NOT NULL,
  `updated` varchar(70) DEFAULT 'NO',
  `deleted` varchar(70) DEFAULT 'NO',
  PRIMARY KEY (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `security_question` */

DROP TABLE IF EXISTS `security_question`;

CREATE TABLE `security_question` (
  `question_id` varchar(70) NOT NULL,
  `question` text NOT NULL,
  `updated` varchar(70) DEFAULT 'NO',
  `deleted` varchar(70) DEFAULT 'NO',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `system_user` */

DROP TABLE IF EXISTS `system_user`;

CREATE TABLE `system_user` (
  `system_user_id` varchar(70) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `place_of_residence` varchar(70) DEFAULT NULL,
  `physical_location` text,
  `district` varchar(70) DEFAULT NULL,
  `district_center` varchar(70) DEFAULT NULL,
  `user_role` varchar(50) DEFAULT NULL,
  `email` varchar(70) DEFAULT NULL,
  `next_of_kin` varchar(70) DEFAULT NULL,
  `next_of_kin_gender` varchar(10) DEFAULT NULL,
  `next_of_kin_contact` varchar(50) DEFAULT NULL,
  `relationship` varchar(50) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`system_user_id`),
  KEY `FK_system_user1` (`district`),
  KEY `user_role` (`user_role`),
  KEY `district_center` (`district_center`),
  CONSTRAINT `system_user_ibfk_1` FOREIGN KEY (`system_user_id`) REFERENCES `user_account` (`user_account_id`),
  CONSTRAINT `system_user_ibfk_2` FOREIGN KEY (`district`) REFERENCES `district` (`district_id`),
  CONSTRAINT `system_user_ibfk_3` FOREIGN KEY (`user_role`) REFERENCES `user_role` (`role_id`),
  CONSTRAINT `system_user_ibfk_4` FOREIGN KEY (`district_center`) REFERENCES `district_center` (`center_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `user_account` */

DROP TABLE IF EXISTS `user_account`;

CREATE TABLE `user_account` (
  `user_account_id` varchar(70) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `password_salt` varchar(50) DEFAULT NULL,
  `account_status` varchar(10) DEFAULT NULL,
  `security_question` varchar(70) DEFAULT NULL,
  `security_answer` varchar(70) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_account_id`),
  KEY `security_question` (`security_question`),
  CONSTRAINT `user_account_ibfk_1` FOREIGN KEY (`security_question`) REFERENCES `security_question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `role_id` varchar(70) NOT NULL,
  `role_name` varchar(70) NOT NULL,
  `updated` varchar(70) DEFAULT 'NO',
  `deleted` varchar(70) DEFAULT 'NO',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
