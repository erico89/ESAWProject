-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lab2
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `tweets`
--

DROP TABLE IF EXISTS `tweets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tweets` (
  `tweet_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `audio` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) NOT NULL,
  `likes` int NOT NULL DEFAULT '0',
  `retweets` int NOT NULL DEFAULT '0',
  `date` date NOT NULL,
  `parent_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`tweet_id`),
  KEY `tweets_ibfk_1` (`parent_id`),
  KEY `tweets_ibfk_2` (`user_id`),
  CONSTRAINT `tweets_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `tweets` (`tweet_id`),
  CONSTRAINT `tweets_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tweets`
--

LOCK TABLES `tweets` WRITE;
/*!40000 ALTER TABLE `tweets` DISABLE KEYS */;
INSERT INTO `tweets` VALUES (1,'Motomami mola mogollón?',NULL,'Candy.mp3','RosiQueen',122,3,'0000-00-00',NULL,3),
(2,'Go to sleep, tomorrow you have school? @ahmed_14','meme.png',NULL,'Svennos',549,2034,'0000-00-00',NULL,2),
(3,'127.38.118.119',NULL,NULL,'ahmed_14',1439,4060,'0000-00-00',2,1),
(4,'Cant get it outta my head?',NULL,'XO Tour Llif3.mp3','f0ck_your_racist_borders',3410,14,'0000-00-00',NULL,4),
(5,'hello','','','wenjie',1,1,'2022-06-18',NULL,5),
(6,'asdf','','','wenjie',1,1,'2022-06-18',NULL,5),
(7,'aaaaaaaaaaaaaaaadf asdf asdf asdf asdf asdf asdf asdf asdf wef asdfc asdfa erff asffc aerv asdf asdf asdfc er asdvc e',NULL,'','wenjie',1,1,'2022-06-18',NULL,5),
(8,'',NULL,'','wenjie',1,0,'2022-06-18',NULL,5),
(9,'',NULL,'','wenjie',0,0,'2022-06-18',NULL,5),
(10,'',NULL,'','wenjie',0,0,'2022-06-18',NULL,5),
(11,'',NULL,'','wenjie',0,0,'2022-06-18',NULL,5),
(12,'',NULL,'','wenjie',0,0,'2022-06-18',NULL,5),
(13,'',NULL,'','wenjie',0,0,'2022-06-18',NULL,5);
/*!40000 ALTER TABLE `tweets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-25 19:26:42
