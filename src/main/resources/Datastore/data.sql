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
  KEY `district` (`district`),
  CONSTRAINT `FK_birth_cert_request` FOREIGN KEY (`birth_applicant_id`) REFERENCES `child_birth_detail` (`birth_id`),
  CONSTRAINT `birth_cert_request_ibfk_1` FOREIGN KEY (`district`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `birth_cert_request` */

insert  into `birth_cert_request`(`birth_cert_request_id`,`system_user_id`,`district`,`birth_applicant_id`,`district_approved`,`regional_approved`,`cert_printed`,`updated`,`deleted`) values ('1061861f-a71d-41f8-8acd-2bde336dc90c','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-4-2013','YES','YES','YES','NO','NO'),('1c663856-4360-4316-8a1a-7854617a8b61','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-4-2013','YES','YES','YES','NO','NO'),('1d2136f8-6706-4a98-89c7-d7d90af02cd5','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-10-2018','YES','YES','YES','NO','NO'),('21c153d8-03df-4ae7-8f99-674ac551c6d2','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-1-2013','YES','YES','YES','NO','NO'),('662ba826-f61d-4c0f-99ac-c6255731402a','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-9-2013','NO','NO','NO','NO','NO'),('6cbebbc3-93a5-43f5-8ea5-16b61e64ca97','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-7-2013','YES','NO','NO','NO','NO'),('6eb1af6d-f112-496a-a0e6-272b3afacdb5','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-1-2013','YES','YES','YES','NO','NO'),('7d0be6d9-9463-4afd-896d-a92bbd2fc9f2','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-8-2013','NO','NO','NO','NO','NO'),('b7a4fc7d-f4aa-4929-8756-63bfa303a37b','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-6-2013','YES','YES','YES','NO','NO'),('bbb1582a-41e7-427d-9274-837b51bff1ca','0df4d41b-2d87-4cbb-9180-f6f04bfa0a22','055928','055928-1-2013','YES','YES','YES','NO','NO'),('c352233f-0f62-48aa-a895-5c0369c7cef9','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-4-2013','NO','NO','NO','NO','NO'),('c3b45e5f-acea-40b7-bd26-d6e7e357e547','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-1-2013','YES','NO','NO','NO','NO'),('cc9decf1-5238-4406-b8b2-c7470aedd8bd','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-1-2013','YES','NO','NO','NO','NO'),('f4452e09-09e7-4538-86e6-fd8b210ef580','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310','053310-5-2013','YES','NO','NO','NO','NO');

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

/*Data for the table `child_birth_detail` */

insert  into `child_birth_detail`(`birth_id`,`surname`,`othername`,`full_name`,`gender`,`date_of_birth`,`place_of_birth`,`town_delivered`,`guardian`,`informant_birth`,`date_of_registration`,`time_of_registration`,`system_user`,`center_registered`,`district_approved`,`regional_approved`,`updated`,`deleted`) values ('053310-1-2013','Abdul-Hanan','Rayan S.','ABDUL-HANAN Rayan S.','Female','2013-04-09','Hospital','Tamale','45a2b301-4040-41ad-8f96-fcdb19bf5d99','29ba3bb0-c4bb-4603-9fab-b4ecb04f90fd','2013-04-09','06:33:25','b3bb4001-0f06-4730-9199-abf2225e9bc6','Afiya Agyei Center','YES','YES','YES','NO'),('053310-10-2018','Siba','Muhammad Bashar','SIBA Muhammad Bashar','Male','2018-01-23','Hospital','Tamale','e4362558-6380-4261-8947-491fe2847af4','52cc7dd4-ec04-4459-8ea4-5d3db23ab85b','2018-03-07','14:42:30','b3bb4001-0f06-4730-9199-abf2225e9bc6','Afiya Agyei Center','YES','YES','YES','NO'),('053310-11-2018','Iddrisu ','Sibdow Abdul-Jalil','IDDRISU  Sibdow Abdul-Jalil','Male','2018-01-23','Hospital','Tamale','a4cc00fd-df51-4f1c-bdfb-d874b6d7a964','043e3ff0-0eec-458f-a73e-188d63845d5a','2018-03-15','22:58:25','b3bb4001-0f06-4730-9199-abf2225e9bc6','Afiya Agyei Center','YES','YES','YES','NO'),('053310-3-2013','Sudais','Abubakar','SUDAIS Abubakar','Male','2013-04-17','Maternity Home','Kumasi','b531e692-fdda-441b-a6f1-052e697c73df','2ad68d84-ce2f-4931-8479-5319614ffae0','2013-04-17','11:30:04','b3bb4001-0f06-4730-9199-abf2225e9bc6','Afiya Agyei Center','YES','NO','YES','NO'),('053310-4-2013','Farouk','Humu Salma','FAROUK Humu Salma','Female','2013-04-17','House','Kumasi','c1eb6939-4f46-4e3c-b193-08732de180f6','adc8c56c-1ca8-451d-8649-a9678cb5ea42','2013-04-18','14:47:37','b3bb4001-0f06-4730-9199-abf2225e9bc6','Afiya Agyei Center','YES','YES','YES','NO'),('053310-5-2013','Zakaria','Jamie','ZAKARIA Jamie','Female','2012-06-20','Clinic','Tamale','189b70b2-cc7b-40e3-b7ab-fcad05dae703','5d03c12f-3393-468b-a9d3-a446e2d4c0ca','2013-04-18','14:47:37','b3bb4001-0f06-4730-9199-abf2225e9bc6','Afiya Agyei Center','YES','YES','YES','NO'),('053310-6-2013','Fadil','Rahimatu','FADIL Rahimatu','Female','2013-04-18','Hospital','Kumasi','a93ef670-c824-44c4-ab29-e11a7db705da','f8675a7a-5863-4de0-b3bc-b5d9ce0975c3','2013-04-20','22:31:15','b3bb4001-0f06-4730-9199-abf2225e9bc6','Afiya Agyei Center','YES','NO','NO','NO'),('053310-7-2013','Abdul-Mumin','Sibdow','ABDUL-MUMIN Sibdow','Male','2009-10-09','Hospital','Tamale','a515c20a-e1af-4dbc-b9b0-284ab063c2be','ce06cd99-6a97-48d6-ac49-a012ea6d4636','2013-04-20','22:31:15','b3bb4001-0f06-4730-9199-abf2225e9bc6','Afiya Agyei Center','YES','NO','NO','NO'),('053310-8-2013','Mumuni','Abdul-Rahim','MUMUNI Abdul-Rahim','Male','2003-12-18','Hospital','Accra','f9a86a5f-f77e-4a7e-ae52-727bbd679a28','a4fafa86-4a87-41f2-8924-459e7e9b5e01','2013-04-22','18:08:03','b3bb4001-0f06-4730-9199-abf2225e9bc6','Afiya Agyei Center','YES','YES','NO','NO'),('053310-9-2013','Alhassan','Kemi','ALHASSAN Kemi','Female','2013-04-03','Hospital','Accra','6a984897-9817-4a31-a96b-050225f79d99','95500463-605a-4ac0-8b6a-c489876f9c7d','2013-04-27','16:25:11','b3bb4001-0f06-4730-9199-abf2225e9bc6','Afiya Agyei Center','NO','NO','NO','NO'),('055928-1-2013','Yamusah','Abubakar','YAMUSAH Abubakar','Male','1988-02-10','House','Tamale','f93e82e8-506d-4da4-bee5-02d71baea733','a5e67826-7364-47db-ab0e-c48e57ff0395','2013-04-23','02:14:32','0df4d41b-2d87-4cbb-9180-f6f04bfa0a22','Bomso ','NO','NO','NO','NO');

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

/*Data for the table `child_guardian` */

insert  into `child_guardian`(`guardian_id`,`mum_surname`,`mum_othername`,`mum_fullname`,`mum_age`,`mum_nationality`,`mum_contact`,`mum_id_type`,`mum_id_num`,`mum_residence`,`dad_surname`,`dad_othername`,`dad_fullname`,`dad_age`,`dad_nationality`,`dad_id_type`,`dad_id_num`,`updated`,`deleted`) values ('189b70b2-cc7b-40e3-b7ab-fcad05dae703','Abubakari','Salamatu','ABUBAKARI Salamatu',29,'Ghanaian','0236954103','Voters\' Id','HJU768I','H No. Ext. 67,Ahongyo, Kumasi','Mohammed','Zakaria','MOHAMMED Zakaria',36,'Ghanaian','National Id','GH675TL','NO','NO'),('45a2b301-4040-41ad-8f96-fcdb19bf5d99','Unknown','Not Yet','UNKNOWN Not Yet',22,'Ghanaian','0243761521','NHIS Card','9067788','B188,Tishigu,Tamale','Iddrisu','Sibdow Abdul-Jalil','IDDRISU Sibdow Abdul-Jalil',25,'Ghanaian','Voters\' Id','H2C1288','NO','NO'),('6a984897-9817-4a31-a96b-050225f79d99','Lambo','Kemi','LAMBO Kemi',23,'Ghanaian','025879652','National Id','GH765AC','Off the Accra-Kasoa Road','Labaran Adamu','Alhassan','LABARAN ADAMU Alhassan',25,'Ghanaian','Voters\' Id','HUYT78','NO','NO'),('a4cc00fd-df51-4f1c-bdfb-d874b6d7a964','Mohammed','Sa\'adatu','MOHAMMED Sa\'adatu',26,'Ghanaian','0233567898','NHIS Card','G675456','Tamale','Ahmed','Siba Wunnam','AHMED Siba Wunnam',29,'Ghanaian','Voters\' Id','H654678','NO','NO'),('a515c20a-e1af-4dbc-b9b0-284ab063c2be','Mahama','Sadatu','MAHAMA Sadatu',26,'Ghanaian','0254781203','Voters\' Id','HJP708I','Behind the MTN Office,Tishigu,Tamale.','Pamboba','Abdul-Mumin','PAMBOBA Abdul-Mumin',28,'Ghanaian','Voters\' Id','H7G1288','NO','NO'),('a9289b40-1764-446b-b170-c0b435b73c5f','Unknown Yet','Not Yet','UNKNOWN YET Not Yet',23,'Ghanaian','0249946220','National Id','GH54632','Gym 1000, Russian Bangalows,Tamale ','Ahmed','Sibawaihi Shani','AHMED Sibawaihi Shani',27,'Ghanaian','Voters\' Id','HR456FC','NO','NO'),('a93ef670-c824-44c4-ab29-e11a7db705da','Mohammed','Rahamatulai','MOHAMMED Rahamatulai',25,'Ghanaian','0245874120','NHIS Card','9067765','Box 1432,Kumasi.','Abdulai','Fadil','ABDULAI Fadil',29,'Ghanaian','Voters\' Id','H2C1254','NO','NO'),('b531e692-fdda-441b-a6f1-052e697c73df','Iddrisu','Zaharatu','IDDRISU Zaharatu',25,'Ghanaian','0263584120','Voters\' Id','HYU789U','H No. B678,Ayegya Zongo,Kumasi','Zuure George ','Sudais A-Rahman','ZUURE GEORGE  Sudais A-Rahman',28,'Ghanaian','NHIS Card','B5T6780','NO','NO'),('c1eb6939-4f46-4e3c-b193-08732de180f6','Mohammed','Aisha','MOHAMMED Aisha',28,'Ghanaian','0254786521','Voters\' Id','HY987BK','H No. B123,Kumasi','Muniru','Munkaila','MUNIRU Munkaila',32,'Ghanaian','Voters\' Id','F54RE32','NO','NO'),('e4362558-6380-4261-8947-491fe2847af4','Mohammed','Sa\'adatu','MOHAMMED Sa\'adatu',26,'Ghanaian','0233567898','Voters\' Id','G675456','No. 232, Kukuo','Ahmed','Siba Wunnam','AHMED Siba Wunnam',29,'Ghanaian','Voters\' Id','H654678','NO','NO'),('f93e82e8-506d-4da4-bee5-02d71baea733','Yakubu','Memunatu','YAKUBU Memunatu',28,'Ghanaian','0265262799','National Id','GH320TL','H No.B188,\r\nTishigu,Tamale','Ibrahim','Yamusah','IBRAHIM Yamusah',32,'Ghanaian','National Id','GH654TL','NO','NO'),('f9a86a5f-f77e-4a7e-ae52-727bbd679a28','Alhassan','Aisha','ALHASSAN Aisha',52,'Ghanaian','0245875652','NHIS Card','THGA455','Accra New Town,Kasoa.','Abdul-Mumuni','Ibrahim','ABDUL-MUMUNI Ibrahim',60,'Ghanaian','Voters\' Id','HYT5646','NO','NO');

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
  KEY `district` (`district`),
  CONSTRAINT `FK_death_cert_request` FOREIGN KEY (`deceased_details`) REFERENCES `deceased_detail` (`deceased_id`),
  CONSTRAINT `death_cert_request_ibfk_1` FOREIGN KEY (`district`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `death_cert_request` */

insert  into `death_cert_request`(`death_cert_request_id`,`system_user`,`deceased_details`,`district`,`district_approved`,`regional_approved`,`cert_printed`,`updated`,`deleted`) values ('13c5cfc8-1612-4753-a3b4-6d22194e3035','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310-1-2013','053310','NO','NO','NO','NO','NO'),('3b5f7412-83e3-4c5e-b453-fc55c1318b2e','0df4d41b-2d87-4cbb-9180-f6f04bfa0a22','055928-1-2013','055928','YES','NO','NO','NO','NO'),('3f75c7d5-848e-465e-bc86-4f3418445609','0df4d41b-2d87-4cbb-9180-f6f04bfa0a22','055928-1-2013','055928','NO','NO','NO','NO','NO'),('c96acc49-d8aa-4e18-85c6-dec560f51381','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310-1-2013','053310','YES','YES','YES','NO','NO'),('d241aacb-5fcd-4977-a453-7bad823760a5','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310-2-2013','053310','YES','NO','NO','NO','NO'),('d6602ba2-2374-4dd2-a675-8a3faa8e3b22','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310-1-2013','053310','YES','YES','YES','NO','NO'),('d938f647-8450-4867-bf8f-69d1c7a91763','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310-1-2013','053310','YES','NO','NO','NO','NO'),('fced437f-9b18-4c0b-9d1b-5031bacb58a2','b3bb4001-0f06-4730-9199-abf2225e9bc6','053310-2-2013','053310','NO','NO','NO','NO','NO');

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

/*Data for the table `deceased_detail` */

insert  into `deceased_detail`(`deceased_id`,`surname`,`othername`,`deceased_fullname`,`gender`,`date_of_birth`,`deceased_residence`,`nationality`,`id_type`,`id_num`,`date_of_death`,`age_of_death`,`place_of_burial`,`address_of_burial_place`,`place_of_death`,`address_of_death_place`,`cause_of_death`,`system_user`,`registration_type`,`date_of_registration`,`parent_name`,`parent_relation`,`parent_id_type`,`parent_id_num`,`parent_nationality`,`center_registered`,`district_approved`,`regional_approved`,`informant_death`,`updated`,`deleted`) values ('053310-1-2013','Atambiri','Atampoga','ATAMBIRI Atampoga','Female',NULL,'Manhyia','Ghanaian','Voters\' Id','H3TY90G','2013-04-03',27,NULL,NULL,'Hospital','Okomfo Anokye Hospital,Bantama','Acute Malaria','b3bb4001-0f06-4730-9199-abf2225e9bc6',NULL,'2013-04-09',NULL,NULL,NULL,NULL,NULL,'Afiya Agyei Center','YES','YES','5aa013dc-59e8-444d-bd60-bb03e380a301','NO','NO'),('053310-11-2018','Ibrahim','Abubakar','IBRAHIM Abubakar','Male',NULL,'House No. B188, Tishigu, Tamale','Ghanaian','National Id','GH42534','2017-12-05',42,'Zudjung Cemetry','Tamale - Kumasi Road','Hospital','Old Hospital, Tishigu, Tamale','Unknown','b3bb4001-0f06-4730-9199-abf2225e9bc6',NULL,'2018-03-07',NULL,NULL,NULL,NULL,NULL,'Afiya Agyei Center','YES','YES','cf883c62-0b64-4528-a9a0-f436ad1b19c3','NO','NO'),('053310-12-2018','Try','Test','TRY Test','Female',NULL,'Hhdhhhs','Cho','NHIS Card','Hqbgs','1254-08-12',20,NULL,NULL,'Clinic','Jgggh','Mkjg','b3bb4001-0f06-4730-9199-abf2225e9bc6',NULL,'2018-03-16',NULL,NULL,NULL,NULL,NULL,'Afiya Agyei Center','NO','NO','29de1eb7-9395-4a0d-ac10-92295f0699c0','NO','NO'),('053310-2-2013','Pamboba','Abubakar','PAMBOBA Abubakar','Male',NULL,'Walawale','Ghanaian','Voters\' Id','WD456TR','2010-10-20',28,'Vittin Estates','Off the Kalariga Road,Tamale','Hospital','Tamale Teaching Hospital,Tamale','Malaria','b3bb4001-0f06-4730-9199-abf2225e9bc6',NULL,'2013-04-18',NULL,NULL,NULL,NULL,NULL,'Afiya Agyei Center','NO','NO','ecb9f98b-40fd-472d-80b3-bacc479f375a','NO','NO'),('055928-1-2013','Alhassan','Ibrahim','ALHASSAN Ibrahim','Male',NULL,'Tishigu,Tamale','Ghanaian','Voters\' Id','GH76547','1990-04-09',78,'Zuugyung Cemetry','Off the Tamale-Kumasi Road.Close to Village Water.','House','H No. B188,\r\nTishigu,Tamale','Natural Death','0df4d41b-2d87-4cbb-9180-f6f04bfa0a22',NULL,'2013-04-23',NULL,NULL,NULL,NULL,NULL,'Bomso ','YES','YES','2c4b6737-15af-4af8-b79a-c9fb7bf0e1db','NO','NO');

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

/*Data for the table `district` */

insert  into `district`(`district_id`,`district_name`,`region`,`primary_contact`,`seconday_contact`,`physical_location`,`updated`,`deleted`) values ('0324098','Test12345','032','3063212547','1234567890','Kpamberu Estates, Tamale','NO','NO'),('0328099','Sibdow','032','3063212547','1234567890','ABCDEFGHIJ','NO','NO'),('050001','Regional Office','032','0302584022','0274580235','Behind the Tech Hospital','NO','NO'),('053310','Amansaman','032','0278564520','0245213021','Opposite the District Court House','NO','NO'),('055928','Knust','032','0325454552','0245102365','Inside the Tech Hospital,Knust','NO','NO');

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

/*Data for the table `district_center` */

insert  into `district_center`(`center_id`,`center_name`,`center_type`,`center_location`,`region_under`,`district_under`,`primary_contact`,`secondary_contact`,`updated`,`deleted`) values ('1209','Afiya Agyei Center','Health Center','Opposite the Main Bus Station','032','053310','0278541056',NULL,'NO','NO'),('1681','Bomso ','Mortuary','Behind the Ghanamy SHS.Tech Junction','032','053310','0325684521',NULL,'NO','NO'),('3922','Ayigya Center','Others','Close to the Paragon Hostel.','032','053310','0326548745',NULL,'NO','NO'),('4258','Ibrahim M','Health Center','S4S 0A2, UoR','032','055928','3067876352',NULL,'NO','NO'),('4899','Ayeduase Center','Health Center','Opposite the Ayeduase Central Mosque,KNUST ','032','053310','0325410876',NULL,'NO','NO'),('5250','Aprukusu Center','Others','Close to the main district Court','032','053310','0245621358',NULL,'NO','NO'),('8908','Doodo','Health Center','Kpamberu Estates, Tamale',NULL,'050001','3067876352',NULL,'NO','NO');

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

/*Data for the table `gen_id` */

insert  into `gen_id`(`gen_table_id`,`district_code`,`dist_last_birth_num`,`dist_last_death_num`,`updated`,`deleted`) values ('0a6ad254-159c-4d4a-a585-a966b4a1ea40','0324098',0,0,'NO','NO'),('71d1ff61-d989-4703-8d4d-f7cc2c417ed7','0321039',0,0,'NO','NO'),('812ccb72-5f85-427b-875c-cae02b7e2d76','0325086',0,0,'NO','NO'),('819d7961-c05a-44a9-853d-81d7fdc2b8bc','0324757',0,0,'NO','NO'),('84d3777a-bb3d-40d4-bfa7-514f80f913bd','0328099',0,0,'NO','NO'),('acd5448a-5d8e-4d46-81cf-da8d33hsgf5e','053310',11,12,'NO','NO'),('adfsr43e-6ytg-09jk-675t-jkhbg6ggfy8l','055928',1,1,'NO','NO'),('c99565a1-4c24-403e-a70c-ea6064bdb08d','0321967',0,0,'NO','NO'),('jhgg7876-kkjg-765t-kl87-jghgtfcx6677','050001',0,0,'NO','NO');

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

/*Data for the table `informant_birth` */

insert  into `informant_birth`(`informant_birth_id`,`informant_name`,`relationship`,`id_type`,`id_num`,`residence`,`updated`,`deleted`) values ('043e3ff0-0eec-458f-a73e-188d63845d5a','AHMED Siba Wunnam','Father',NULL,NULL,NULL,'NO','NO'),('29ba3bb0-c4bb-4603-9fab-b4ecb04f90fd',NULL,'Father',NULL,NULL,NULL,'NO','NO'),('2ad68d84-ce2f-4931-8479-5319614ffae0',NULL,'Father',NULL,NULL,NULL,'NO','NO'),('52cc7dd4-ec04-4459-8ea4-5d3db23ab85b','AHMED Siba Wunnam','Father',NULL,NULL,NULL,'NO','NO'),('5d03c12f-3393-468b-a9d3-a446e2d4c0ca',NULL,'Self',NULL,NULL,NULL,'NO','NO'),('5dfa0ead-68d2-4815-b284-4587231e2aa7','Iddrisu Sibdow Abdul-Jalil','Other','Voters\' Id','HUYT590','Tishigu,Tamale','NO','NO'),('95500463-605a-4ac0-8b6a-c489876f9c7d','Labaran Adamu Liman','Other','Voters\' Id','YTRF768','Accra','NO','NO'),('a4fafa86-4a87-41f2-8924-459e7e9b5e01','ABDUL-MUMUNI Ibrahim','Father',NULL,NULL,NULL,'NO','NO'),('a5e67826-7364-47db-ab0e-c48e57ff0395','YAKUBU Memunatu','Mother',NULL,NULL,NULL,'NO','NO'),('adc8c56c-1ca8-451d-8649-a9678cb5ea42',NULL,'Mother',NULL,NULL,NULL,'NO','NO'),('ce06cd99-6a97-48d6-ac49-a012ea6d4636','PETER  Jamie','Self',NULL,NULL,NULL,'NO','NO'),('f8675a7a-5863-4de0-b3bc-b5d9ce0975c3','MOHAMMED Rahamatulai','Mother',NULL,NULL,NULL,'NO','NO');

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

/*Data for the table `informant_death` */

insert  into `informant_death`(`informant_death_id`,`informant_name`,`informant_address`,`informant_contact`,`id_type`,`id_num`,`relationship`,`updated`,`deleted`) values ('29de1eb7-9395-4a0d-ac10-92295f0699c0','Hhvvg','Hhshdveh','085755886','National Id','Bggqy','Vvhuh','NO','NO'),('2c4b6737-15af-4af8-b79a-c9fb7bf0e1db','Ibrahim Iddrisu','Tishigu,Tamale','0244748913','National Id','GH587TL','Son','NO','NO'),('5aa013dc-59e8-444d-bd60-bb03e380a301','Atambire Abi','Ayigya,Kumasi','0248563012','National Id','567GH69','Inlaw','NO','NO'),('cf883c62-0b64-4528-a9a0-f436ad1b19c3','Ibrahim','House No. B188, Tishigu, Tamale','0265452103','NHIS Card','H263463','Abass','NO','NO'),('ecb9f98b-40fd-472d-80b3-bacc479f375a','Pamboba Abdul-Mumin','Tamale','0203247851','National Id','GH765TL','Brother','NO','NO');

/*Table structure for table `region` */

DROP TABLE IF EXISTS `region`;

CREATE TABLE `region` (
  `region_id` varchar(70) NOT NULL,
  `region_name` varchar(70) NOT NULL,
  `updated` varchar(70) DEFAULT 'NO',
  `deleted` varchar(70) DEFAULT 'NO',
  PRIMARY KEY (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `region` */

insert  into `region`(`region_id`,`region_name`,`updated`,`deleted`) values ('000','Allah','NO','NO'),('030','Greater Accra','NO','NO'),('031','Western','NO','NO'),('032','Ashanti','NO','NO'),('033','Central','NO','NO'),('034','Eastern','NO','NO'),('035','Brong Ahafo','NO','NO'),('036','Volta','NO','NO'),('037','Northern','NO','NO'),('038','Upper East','NO','NO'),('039','Upper West','NO','NO');

/*Table structure for table `security_question` */

DROP TABLE IF EXISTS `security_question`;

CREATE TABLE `security_question` (
  `question_id` varchar(70) NOT NULL,
  `question` text NOT NULL,
  `updated` varchar(70) DEFAULT 'NO',
  `deleted` varchar(70) DEFAULT 'NO',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `security_question` */

insert  into `security_question`(`question_id`,`question`,`updated`,`deleted`) values ('01','In which city were you born?','NO','NO'),('02','What is the name of your favourite cousin?','NO','NO'),('03','Who is your childhood hero?','NO','NO'),('04','What is the name of your primary school?','NO','NO'),('05','Where did you first work?','NO','NO'),('06','What is the name of your favourite teacher from primary school','NO','NO');

/*Table structure for table `system_user` */

DROP TABLE IF EXISTS `system_user`;

CREATE TABLE `system_user` (
  `system_user_id` varchar(70) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `username` varchar(70) DEFAULT NULL,
  `password` varchar(70) DEFAULT NULL,
  `account_status` varchar(50) DEFAULT NULL,
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
  CONSTRAINT `system_user_ibfk_2` FOREIGN KEY (`district`) REFERENCES `district` (`district_id`),
  CONSTRAINT `system_user_ibfk_3` FOREIGN KEY (`user_role`) REFERENCES `user_role` (`role_id`),
  CONSTRAINT `system_user_ibfk_4` FOREIGN KEY (`district_center`) REFERENCES `district_center` (`center_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `system_user` */

insert  into `system_user`(`system_user_id`,`last_name`,`middle_name`,`first_name`,`username`,`password`,`account_status`,`gender`,`phone_number`,`place_of_residence`,`physical_location`,`district`,`district_center`,`user_role`,`email`,`next_of_kin`,`next_of_kin_gender`,`next_of_kin_contact`,`relationship`,`updated`,`deleted`) values ('06c16473-e690-4500-a2f9-c8b6efa26fae','Ibrahim','Iddrisu Alhassan','IBRAHIM Iddrisu Alhassan','iaibrahim','edf8bb84bdc6f0adfc2a17c74bf5923fd53f7561f0a8e0b1fee8578230398996','Active','Male','0244748913','Kpamberu Estates, Tamale','Kpamberu Estates, Tamale','053310',NULL,'03','iddrisuib@gmail.com','Iddrisu Sibdow Abdul_Jalil','Male','3065811592','Others','NO','NO'),('0df4d41b-2d87-4cbb-9180-f6f04bfa0a22','Iddrisu','Amal Wunzooya','IDDRISU Amal Wunzooya','awiddrisu','2c0a6aec5c8d5bf3b79602d449f18a26497efdb6a973b566b7fae8a8b7e5c103','Active','Female','0243698541','Knust,Kumasi, A/R','Senior Lecturers Quaters,Knust','053310','3922','03','amalwunzooya@gmail.com','Iddrisu Wunam Ibrahim','Male','0244748913','Sister','NO','NO'),('b3bb4001-0f06-4730-9199-abf2225e9bc6','Peter','Jamie Wunam','PETER Jamie Wunam','jpeter','2654358fea5bb0ac5e3b9b15064ec6f3bed051becc8d3da109ac51ae73989a6e','Active','Female','0268118488','Tishigu,Tamale','Behind the MTN Office,Tishigu,Tamale.','053310','1209','03','Jpbarker2008@gmail.com','Osman Rahama','Female','026811488','Others','NO','NO'),('c81f9331-d8c1-479f-884b-b6006c19f081','Ahmed','Sibawaihi Shani','AHMED Siba Shani','ssahmed','f4237ace363afd6a24c719e6969fbc95472f342786bf00f36b776a14d0cccabe','Active','Male','0249946220','Tamale','Gym 1000,Russian Bangalows','053310',NULL,'02','maway52@yahoo.com','Ahmed Abdul-Majeed','Male','0249946220','Brother','NO','NO'),('first#siaj#name143','Iddrisu','Abdul-Jalil','Sibdow','sibdow','897479897c736c41b31bf587644ca8d8fa4bc7a994953f906cb64ae6da3092c7','Active','Male','3065811592','Tishigu,Tamale','Behind the MTN Office,Tamale','050001',NULL,'01','sibdowsaij@gmail.com','Iddrisu Wunpini Abdul-Rahman','Male','0243425804','Brother','NO','NO');

/*Table structure for table `user_account` */

DROP TABLE IF EXISTS `user_account`;

CREATE TABLE `user_account` (
  `user_account_id` varchar(70) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `password_salt` varchar(50) DEFAULT NULL,
  `account_status` varchar(10) DEFAULT NULL,
  `updated` varchar(10) DEFAULT NULL,
  `deleted` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_account` */

insert  into `user_account`(`user_account_id`,`username`,`password`,`password_salt`,`account_status`,`updated`,`deleted`) values ('0df4d41b-2d87-4cbb-9180-f6f04bfa0a22','awiddrisu','2c0a6aec5c8d5bf3b79602d449f18a26497efdb6a973b566b7fae8a8b7e5c103',NULL,'Active','NO','NO'),('b3bb4001-0f06-4730-9199-abf2225e9bc6','jpeter ','2654358fea5bb0ac5e3b9b15064ec6f3bed051becc8d3da109ac51ae73989a6e',NULL,'Active','NO','NO'),('c81f9331-d8c1-479f-884b-b6006c19f081','ssahmed','f4237ace363afd6a24c719e6969fbc95472f342786bf00f36b776a14d0cccabe',NULL,'Active','NO','NO'),('first#siaj#name143','sibdow','897479897c736c41b31bf587644ca8d8fa4bc7a994953f906cb64ae6da3092c7',NULL,'Active','NO','NO');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `role_id` varchar(70) NOT NULL,
  `role_name` varchar(70) NOT NULL,
  `updated` varchar(70) DEFAULT 'NO',
  `deleted` varchar(70) DEFAULT 'NO',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_role` */

insert  into `user_role`(`role_id`,`role_name`,`updated`,`deleted`) values ('01','Regional Administrator','NO','NO'),('02','District Administrator','NO','NO'),('03','Registrar','NO','NO');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
