CREATE DATABASE  IF NOT EXISTS `images_upscaler` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `images_upscaler`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: images_upscaler
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` mediumtext,
  `width` int NOT NULL,
  `height` int NOT NULL,
  `extension` varchar(10) DEFAULT NULL,
  `size` bigint NOT NULL,
  `userId` bigint NOT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `userId_idx` (`userId`),
  CONSTRAINT `userImage` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (7,'mavr_52.jpg',80,80,'jpg',9531,2,'2023-11-21 10:29:27','2023-11-21 10:29:27'),(11,'bluesky3.png',288,288,'png',72193,2,'2023-11-21 23:39:43','2023-11-21 23:39:43'),(12,'wolf_gray.jpg',236,236,'jpg',53077,2,'2023-11-21 23:41:17','2023-11-21 23:41:17'),(14,'asian-girl.jpg',300,300,'jpg',19929,2,'2023-11-23 01:14:48','2023-11-23 01:14:48'),(15,'(1) asian-girl.jpg',300,300,'jpg',19929,2,'2023-11-23 01:15:16','2023-11-23 01:15:16'),(16,'(2) asian-girl.jpg',300,300,'jpg',19929,2,'2023-11-23 10:55:27','2023-11-23 10:55:27'),(17,'de187d426dc939336da4222637142a01.jpg',300,300,'jpg',22752,2,'2023-11-23 18:09:54','2023-11-23 18:09:54'),(18,'db3eb85bb995a46472fc5fbc2927cdf7.jpg',300,300,'jpg',16527,2,'2023-11-23 18:10:18','2023-11-23 18:10:18'),(19,'(1) de187d426dc939336da4222637142a01.jpg',300,300,'jpg',22752,2,'2023-11-23 18:11:05','2023-11-23 18:11:05'),(20,'f6056efb228a0af9e5aec2267df85fbf.jpg',225,400,'jpg',19951,3,'2023-11-23 23:16:13','2023-11-23 23:16:13'),(21,'609c7229a9ad358059506d2f27ac291f.jpg',225,400,'jpg',28024,3,'2023-11-23 23:16:25','2023-11-23 23:16:25'),(22,'373e629b32c4fc2fe81207fe4cf7e64c.jpg',225,400,'jpg',18212,3,'2023-11-23 23:16:38','2023-11-23 23:16:38'),(23,'18b12377b632d7f465e7ff0b982e35ae.jpg',225,400,'jpg',17814,3,'2023-11-23 23:16:49','2023-11-23 23:16:49'),(24,'(1) 373e629b32c4fc2fe81207fe4cf7e64c.jpg',225,400,'jpg',18212,3,'2023-11-23 23:19:00','2023-11-23 23:19:00'),(25,'(1) f6056efb228a0af9e5aec2267df85fbf.jpg',225,400,'jpg',19951,3,'2023-11-23 23:19:13','2023-11-23 23:19:13'),(26,'images.jpg',112,200,'jpg',2617,3,'2023-11-24 00:09:33','2023-11-24 00:09:33'),(27,'beautiful-young-asian-woman-portrait-cute-girl-wallpaper-background-generative-ai-free-photo.jpg',112,200,'jpg',4352,3,'2023-11-24 00:09:58','2023-11-24 00:09:58'),(28,'beautiful-asian-teenager-with-short-hair-and-black-jacket-generative-ai-free-photo.jpg',112,200,'jpg',5278,3,'2023-11-24 00:10:11','2023-11-24 00:10:11'),(29,'ai-generative-womans-hand-use-chopsticks-hold-korean-pork-grilled-pork-slice-roasted-bbq-or-yakinikuhappy-smile-hungry-asian-girl-have-meal-lunch-or-dinner-at-restaurantfunny-and-enjoy-eating-li-.jpg',112,200,'jpg',4436,3,'2023-11-24 00:11:07','2023-11-24 00:11:07'),(30,'(1) beautiful-young-asian-woman-portrait-cute-girl-wallpaper-background-generative-ai-free-photo.jpg',112,200,'jpg',4352,3,'2023-11-24 00:11:36','2023-11-24 00:11:36'),(31,'beautiful-asian-young-woman-outdoors-at-night-generative-ai-free-photo.jpg',112,200,'jpg',4745,3,'2023-11-24 00:11:54','2023-11-24 00:11:54'),(32,'beautiful-asian-girl-with-red-hair-generative-ai-free-photo.jpg',112,200,'jpg',4491,3,'2023-11-24 00:19:36','2023-11-24 00:19:36'),(33,'(1) beautiful-asian-girl-with-red-hair-generative-ai-free-photo.jpg',112,200,'jpg',4491,3,'2023-11-24 00:21:27','2023-11-24 00:21:27'),(34,'(2) beautiful-asian-girl-with-red-hair-generative-ai-free-photo.jpg',112,200,'jpg',4491,3,'2023-11-24 00:22:47','2023-11-24 00:22:47'),(35,'(3) beautiful-asian-girl-with-red-hair-generative-ai-free-photo.jpg',112,200,'jpg',4491,3,'2023-11-24 00:24:35','2023-11-24 00:24:35'),(36,'(4) beautiful-asian-girl-with-red-hair-generative-ai-free-photo.jpg',112,200,'jpg',4491,3,'2023-11-24 00:25:41','2023-11-24 00:25:41'),(37,'oY8fnXB7E0WICBAFMtJ4WI3FyIA4XzsJSAhwBA~tplv-sehyhbs0wv-resize_400_400.jpeg',225,400,'jpeg',14087,3,'2023-11-24 00:28:32','2023-11-24 00:28:32'),(38,'cuadro_11.jpg',112,200,'jpg',9403,3,'2023-11-24 00:33:51','2023-11-24 00:33:51'),(39,'8mf40dnsa.jpg',112,200,'jpg',5105,3,'2023-11-24 00:35:26','2023-11-24 00:35:26'),(40,'(1) 8mf40dnsa.jpg',112,200,'jpg',5105,3,'2023-11-24 00:36:54','2023-11-24 00:36:54'),(41,'148fh23bew.jpg',300,168,'jpg',6287,3,'2023-11-24 00:40:50','2023-11-24 00:40:50'),(42,'6218_masonry_400.jpg',400,225,'jpg',34860,3,'2023-11-24 00:40:59','2023-11-24 00:40:59'),(43,'em-la-con-di-cua-ai-400x225.jpg',400,225,'jpg',11509,3,'2023-11-24 00:50:52','2023-11-24 00:50:52'),(44,'ao-dai-thu-dam-2-400x225.jpg',400,225,'jpg',8153,3,'2023-11-24 00:52:17','2023-11-24 00:52:17'),(45,'(1) ao-dai-thu-dam-2-400x225.jpg',400,225,'jpg',8153,3,'2023-11-24 00:54:56','2023-11-24 00:54:56'),(46,'pha-trinh-400x225.jpg',400,225,'jpg',7785,3,'2023-11-24 00:56:49','2023-11-24 00:56:49'),(47,'w_CkExbYKTX3AlW4jtJ5F9hpf26VnvQiIqr-1-400x225.jpg',400,225,'jpg',13089,3,'2023-11-24 00:59:36','2023-11-24 00:59:36'),(48,'xvideos-quangninh-vietnamese-400x225.jpg',400,225,'jpg',9109,3,'2023-11-24 01:02:51','2023-11-24 01:02:51'),(49,'10.jpg',400,225,'jpg',13950,3,'2023-11-24 01:06:26','2023-11-24 01:06:26'),(50,'77407_masonry_400.jpg',400,225,'jpg',23349,3,'2023-11-24 01:08:24','2023-11-24 01:08:24'),(51,'318656_masonry_400.jpg',400,225,'jpg',17952,3,'2023-11-24 01:08:32','2023-11-24 01:08:32'),(52,'9941_masonry_400.jpg',400,225,'jpg',31766,3,'2023-11-24 01:10:00','2023-11-24 01:10:00'),(53,'45n4idsff.jpg',300,168,'jpg',4790,3,'2023-11-24 01:11:13','2023-11-24 01:11:13'),(54,'20.jpg',400,225,'jpg',11820,3,'2023-11-24 01:16:43','2023-11-24 01:16:43'),(55,'58y9jislgf.jpg',300,168,'jpg',6665,3,'2023-11-24 01:16:52','2023-11-24 01:16:52'),(56,'5.jpg',400,225,'jpg',10333,3,'2023-11-24 01:17:01','2023-11-24 01:17:01'),(57,'(1) 20.jpg',400,225,'jpg',11820,3,'2023-11-24 01:20:10','2023-11-24 01:20:10'),(58,'(1) pha-trinh-400x225.jpg',400,225,'jpg',7785,3,'2023-11-24 01:20:32','2023-11-24 01:20:32'),(59,'(2) ao-dai-thu-dam-2-400x225.jpg',400,225,'jpg',8153,3,'2023-11-24 01:21:59','2023-11-24 01:21:59'),(60,'g8gig4ygweqgf.jpg',300,168,'jpg',8939,3,'2023-11-28 08:26:05','2023-11-28 08:26:05'),(61,'4vyuqriyrfbuef.jpg',300,168,'jpg',7985,3,'2023-11-28 08:26:14','2023-11-28 08:26:14'),(62,'f092d72b9d103986d6a511dd0927fc92.jpg',400,225,'jpg',42142,3,'2023-11-28 08:26:27','2023-11-28 08:26:27'),(63,'(1) f092d72b9d103986d6a511dd0927fc92.jpg',400,225,'jpg',42142,3,'2023-11-28 08:43:25','2023-11-28 08:43:25'),(64,'2872hrfidnvjkvnz.jpg',200,112,'jpg',6723,3,'2023-11-28 08:45:20','2023-11-28 08:45:20'),(65,'(1) 2872hrfidnvjkvnz.jpg',200,112,'jpg',6723,3,'2023-11-28 08:45:25','2023-11-28 08:45:25'),(66,'9438erjfndkf0.jpg',200,112,'jpg',3640,3,'2023-11-28 08:45:33','2023-11-28 08:45:33'),(67,'(1) 9438erjfndkf0.jpg',200,112,'jpg',3640,3,'2023-11-28 08:45:38','2023-11-28 08:45:38'),(68,'(2) 9438erjfndkf0.jpg',200,112,'jpg',3640,3,'2023-11-28 08:45:53','2023-11-28 08:45:53'),(69,'19374yuahnfan.jpg',200,112,'jpg',5471,3,'2023-11-28 08:48:39','2023-11-28 08:48:39'),(70,'(1) 19374yuahnfan.jpg',200,112,'jpg',5471,3,'2023-11-28 08:48:44','2023-11-28 08:48:44'),(71,'(1) 4vyuqriyrfbuef.jpg',300,168,'jpg',7985,3,'2023-12-05 09:21:01','2023-12-05 09:21:01'),(72,'(3) 9438erjfndkf0.jpg',200,112,'jpg',3640,3,'2023-12-09 13:38:19','2023-12-09 13:38:19'),(73,'(1) beautiful-asian-teenager-with-short-hair-and-black-jacket-generative-ai-free-photo.jpg',112,200,'jpg',5278,3,'2023-12-09 13:38:31','2023-12-09 13:38:31'),(74,'Sicily 2.jpg',300,168,'jpg',10552,3,'2023-12-12 09:41:28','2023-12-12 09:41:28'),(75,'Sicily 1.jpg',300,168,'jpg',10432,3,'2023-12-12 09:43:14','2023-12-12 09:43:14'),(76,'Santorini 1.jpg',400,225,'jpg',30908,3,'2023-12-12 09:43:24','2023-12-12 09:43:24'),(77,'Santorini 2.jpeg',400,225,'jpeg',28948,3,'2023-12-12 09:43:34','2023-12-12 09:43:34'),(78,'Italian Coastal.png',400,225,'png',192291,3,'2023-12-12 09:46:41','2023-12-12 09:46:41'),(79,'Italian Coastal 2.jpg',300,168,'jpg',10061,3,'2023-12-12 09:46:53','2023-12-12 09:46:53');
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `taskname` varchar(256) NOT NULL,
  `imageId` bigint NOT NULL,
  `userId` bigint NOT NULL,
  `scaleRatio` int NOT NULL,
  `status` varchar(45) NOT NULL,
  `result` text,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  `startedAt` datetime DEFAULT NULL,
  `endedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `userId_idx` (`userId`),
  KEY `imageId_idx` (`imageId`),
  CONSTRAINT `imageTask` FOREIGN KEY (`imageId`) REFERENCES `images` (`id`),
  CONSTRAINT `userTask` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (4,'upscale_image_ESRNet',7,2,4,'succeeded','Image upscaled successfully.','2023-11-21 10:29:28','2023-11-23 17:46:02','2023-11-23 17:45:40','2023-11-23 17:46:02'),(8,'upscale_image_ESRGAN',11,2,4,'succeeded','Image upscaled successfully.','2023-11-21 23:39:44','2023-11-23 17:38:31','2023-11-23 17:36:17','2023-11-23 17:38:31'),(9,'upscale_image_ESRGAN',12,2,4,'succeeded','Image upscaled successfully.','2023-11-21 23:41:18','2023-11-23 09:55:32','2023-11-23 09:52:42','2023-11-23 09:55:32'),(11,'upscale_image_ESRGAN',14,2,4,'succeeded','Image upscaled successfully.','2023-11-23 01:14:48','2023-11-23 09:56:12','2023-11-23 09:52:50','2023-11-23 09:56:12'),(12,'upscale_image_ESRNet_face_enhance',15,2,4,'succeeded','Image upscaled successfully.','2023-11-23 01:15:16','2023-11-23 18:09:09','2023-11-23 18:06:29','2023-11-23 18:09:09'),(13,'upscale_image_ESRGAN_face_enhance',16,2,2,'succeeded','Image upscaled successfully.','2023-11-23 10:55:27','2023-11-23 17:59:25','2023-11-23 17:57:56','2023-11-23 17:59:25'),(14,'upscale_image_ESRGAN_face_enhance',17,2,4,'succeeded','Image upscaled successfully.','2023-11-23 18:09:54','2023-11-23 18:12:27','2023-11-23 18:09:56','2023-11-23 18:12:27'),(15,'upscale_image_ESRGAN_face_enhance',18,2,4,'succeeded','Image upscaled successfully.','2023-11-23 18:10:18','2023-11-23 18:13:02','2023-11-23 18:10:23','2023-11-23 18:13:02'),(16,'upscale_image_ESRGAN',19,2,4,'succeeded','Image upscaled successfully.','2023-11-23 18:11:06','2023-11-23 18:14:42','2023-11-23 18:12:28','2023-11-23 18:14:42'),(17,'upscale_image_ESRGAN_face_enhance',20,3,4,'succeeded','Image upscaled successfully.','2023-11-23 23:16:13','2023-11-23 23:21:18','2023-11-23 23:17:03','2023-11-23 23:21:18'),(18,'upscale_image_ESRGAN_face_enhance',21,3,4,'succeeded','Image upscaled successfully.','2023-11-23 23:16:25','2023-11-23 23:21:18','2023-11-23 23:17:11','2023-11-23 23:21:18'),(19,'upscale_image_ESRGAN_face_enhance',22,3,4,'succeeded','Image upscaled successfully.','2023-11-23 23:16:38','2023-11-23 23:55:56','2023-11-23 23:51:38','2023-11-23 23:55:56'),(20,'upscale_image_ESRGAN_face_enhance',23,3,4,'succeeded','Image upscaled successfully.','2023-11-23 23:16:49','2023-11-23 23:55:56','2023-11-23 23:51:42','2023-11-23 23:55:56'),(21,'upscale_image_ESRGAN',24,3,4,'succeeded','Image upscaled successfully.','2023-11-23 23:19:00','2023-11-23 23:59:06','2023-11-23 23:56:02','2023-11-23 23:59:06'),(22,'upscale_image_ESRGAN',25,3,4,'succeeded','Image upscaled successfully.','2023-11-23 23:19:13','2023-11-23 23:59:07','2023-11-23 23:56:07','2023-11-23 23:59:07'),(23,'upscale_image_ESRGAN_face_enhance',26,3,4,'succeeded','Image upscaled successfully.','2023-11-24 00:09:33','2023-11-24 00:11:26','2023-11-24 00:09:36','2023-11-24 00:11:26'),(24,'upscale_image_ESRGAN_face_enhance',27,3,4,'succeeded','Image upscaled successfully.','2023-11-24 00:09:59','2023-11-24 00:11:26','2023-11-24 00:10:03','2023-11-24 00:11:26'),(25,'upscale_image_ESRGAN_face_enhance',28,3,4,'succeeded','Image upscaled successfully.','2023-11-24 00:10:11','2023-11-24 00:16:59','2023-11-24 00:15:15','2023-11-24 00:16:59'),(26,'upscale_image_ESRGAN_face_enhance',29,3,4,'succeeded','Image upscaled successfully.','2023-11-24 00:11:07','2023-11-24 00:15:10','2023-11-24 00:13:48','2023-11-24 00:15:10'),(27,'upscale_image_ESRGAN_face_enhance',30,3,4,'succeeded','Image upscaled successfully.','2023-11-24 00:11:37','2023-11-24 00:15:10','2023-11-24 00:13:49','2023-11-24 00:15:10'),(28,'upscale_image_ESRGAN_face_enhance',31,3,4,'succeeded','Image upscaled successfully.','2023-11-24 00:11:54','2023-11-24 00:16:59','2023-11-24 00:15:20','2023-11-24 00:16:58'),(29,'upscale_image_ESRGAN_face_enhance',32,3,4,'succeeded','Image upscaled successfully.','2023-11-24 00:19:36','2023-11-24 00:20:35','2023-11-24 00:19:40','2023-11-24 00:20:35'),(30,'upscale_image_ESRGAN',33,3,4,'succeeded','Image upscaled successfully.','2023-11-24 00:21:27','2023-11-24 00:21:54','2023-11-24 00:21:30','2023-11-24 00:21:54'),(31,'upscale_image_ESRNet_face_enhance',34,3,4,'succeeded','Image upscaled successfully.','2023-11-24 00:22:48','2023-11-24 00:23:35','2023-11-24 00:22:50','2023-11-24 00:23:34'),(32,'upscale_image_ESRGAN_anime_face_enhance',35,3,4,'succeeded','Image upscaled successfully.','2023-11-24 00:24:35','2023-11-24 00:25:09','2023-11-24 00:24:35','2023-11-24 00:25:09'),(33,'upscale_image_ESRGAN_anime',36,3,4,'succeeded','Image upscaled successfully.','2023-11-24 00:25:41','2023-11-24 00:25:58','2023-11-24 00:25:45','2023-11-24 00:25:58'),(34,'upscale_image_ESRGAN_face_enhance',37,3,4,'succeeded','Image upscaled successfully.','2023-11-24 00:28:33','2023-11-24 00:30:09','2023-11-24 00:28:34','2023-11-24 00:30:09'),(57,'upscale_image_ESRGAN_face_enhance',60,3,4,'succeeded','Image upscaled successfully.','2023-11-28 08:26:05','2023-11-28 08:37:12','2023-11-28 08:35:43','2023-11-28 08:37:12'),(58,'upscale_image_ESRGAN_face_enhance',61,3,4,'succeeded','Image upscaled successfully.','2023-11-28 08:26:14','2023-11-28 08:37:14','2023-11-28 08:35:47','2023-11-28 08:37:14'),(59,'upscale_image_ESRGAN_face_enhance',62,3,4,'succeeded','Image upscaled successfully.','2023-11-28 08:26:28','2023-11-28 08:39:10','2023-11-28 08:37:17','2023-11-28 08:39:10'),(61,'upscale_image_ESRGAN_face_enhance',64,3,4,'succeeded','Image upscaled successfully.','2023-11-28 08:45:20','2023-11-28 08:47:16','2023-11-28 08:46:31','2023-11-28 08:47:16'),(62,'upscale_image_ESRGAN_anime_face_enhance',65,3,4,'succeeded','Image upscaled successfully.','2023-11-28 08:45:25','2023-11-28 08:47:19','2023-11-28 08:46:35','2023-11-28 08:47:19'),(63,'upscale_image_ESRGAN_anime_face_enhance',66,3,4,'succeeded','Image upscaled successfully.','2023-11-28 08:45:33','2023-11-28 08:47:19','2023-11-28 08:46:36','2023-11-28 08:47:19'),(64,'upscale_image_ESRGAN_face_enhance',67,3,4,'succeeded','Image upscaled successfully.','2023-11-28 08:45:38','2023-11-28 08:48:21','2023-11-28 08:47:22','2023-11-28 08:48:21'),(65,'upscale_image_ESRGAN_anime_face_enhance',68,3,4,'succeeded','Image upscaled successfully.','2023-11-28 08:45:53','2023-11-28 08:48:14','2023-11-28 08:47:25','2023-11-28 08:48:14'),(66,'upscale_image_ESRGAN_anime_face_enhance',69,3,4,'succeeded','Image upscaled successfully.','2023-11-28 08:48:39','2023-11-28 08:49:05','2023-11-28 08:48:41','2023-11-28 08:49:05'),(67,'upscale_image_ESRGAN_face_enhance',70,3,4,'succeeded','Image upscaled successfully.','2023-11-28 08:48:45','2023-11-28 08:49:28','2023-11-28 08:48:49','2023-11-28 08:49:28'),(68,'upscale_image_ESRGAN_face_enhance',71,3,4,'succeeded','Image upscaled successfully.','2023-12-05 09:21:02','2023-12-05 09:24:12','2023-12-05 09:21:27','2023-12-05 09:24:12'),(69,'upscale_image_ESRGAN_face_enhance',72,3,4,'succeeded','Image upscaled successfully.','2023-12-09 13:38:19','2023-12-09 13:40:46','2023-12-09 13:39:03','2023-12-09 13:40:46'),(70,'upscale_image_ESRGAN_face_enhance',73,3,4,'succeeded','Image upscaled successfully.','2023-12-09 13:38:31','2023-12-09 13:41:08','2023-12-09 13:39:06','2023-12-09 13:41:08'),(71,'upscale_image_ESRGAN_face_enhance',74,3,4,'succeeded','Image upscaled successfully.','2023-12-12 09:41:29','2023-12-12 09:53:29','2023-12-12 09:48:22','2023-12-12 09:53:29'),(72,'upscale_image_ESRGAN_face_enhance',75,3,4,'succeeded','Image upscaled successfully.','2023-12-12 09:43:14','2023-12-12 09:53:32','2023-12-12 09:48:26','2023-12-12 09:53:32'),(73,'upscale_image_ESRGAN_face_enhance',76,3,4,'succeeded','Image upscaled successfully.','2023-12-12 09:43:25','2023-12-12 09:53:48','2023-12-12 09:48:27','2023-12-12 09:53:48'),(74,'upscale_image_ESRGAN_face_enhance',77,3,4,'succeeded','Image upscaled successfully.','2023-12-12 09:43:34','2023-12-12 10:04:33','2023-12-12 09:53:35','2023-12-12 10:04:33'),(75,'upscale_image_ESRGAN_face_enhance',78,3,4,'succeeded','Image upscaled successfully.','2023-12-12 09:46:41','2023-12-12 10:05:07','2023-12-12 09:53:42','2023-12-12 10:05:07'),(76,'upscale_image_ESRGAN_face_enhance',79,3,4,'succeeded','Image upscaled successfully.','2023-12-12 09:46:53','2023-12-12 10:03:08','2023-12-12 09:53:54','2023-12-12 10:03:07');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `maxImagesUpscaled` int unsigned DEFAULT NULL,
  `isActive` tinyint NOT NULL DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `isAdmin` tinyint NOT NULL DEFAULT '0',
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'hoaan123','hoaan123',15,1,'2023-11-16 16:01:51','2023-12-16 13:00:24',0),(3,'hoaan2003','hoaan2003',30,1,'2023-11-23 18:19:00','2023-11-23 18:19:00',1),(5,'hoaan172003','8151114qtc',15,0,'2023-12-16 11:42:47','2023-12-16 11:42:47',0),(6,'thzlinhh123','thuylinhcutis1',20,1,'2023-12-16 12:56:12','2023-12-16 12:57:59',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-16 13:07:23
