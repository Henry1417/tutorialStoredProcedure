-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: tutorial_procedure
-- ------------------------------------------------------
-- Server version	8.0.25-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `coche`
--

DROP TABLE IF EXISTS `coche`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coche` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `anyo` int DEFAULT NULL,
  `km` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coche`
--

/*!40000 ALTER TABLE `coche` DISABLE KEYS */;
INSERT INTO `coche` VALUES (1,'Ford','Mustang',2900,2000),(2,'Ford','Focus',2000,120003),(3,'Chevrolet','Equinox',2900,2000),(4,'Chevrolet','Cavalier',2900,2000),(5,'VW','Tiguan',2900,2000),(6,'Chevrolet','Cavalier',2000,20500),(7,'VW','Tiguan',2009,48000),(8,'Ford','Mustang',2021,1900),(9,'Ford','Focus',2020,4500),(10,'Chevrolet','Cavalier',2000,20500),(11,'VW','Tiguan',2009,48000),(12,'Ford','Mustang',2021,1900),(13,'Ford','Focus',2020,4500),(14,'Chevrolet','Cruize',2022,20400),(15,'VW','Polo',2009,48000),(16,'Ford','F150',2005,19000),(17,'Ford','Fiesta',2014,45900),(18,'Chevrolet','Camaro',2022,100),(19,'Chevrolet','Cruize',2022,20400),(20,'VW','Polo',2009,48000),(21,'Ford','F150',2005,19000),(22,'Ford','Fiesta',2014,45900),(23,'Chevrolet','Corvet',2022,5000),(24,'Chevrolet','Corvet',2022,5000);
/*!40000 ALTER TABLE `coche` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-21 14:53:56
