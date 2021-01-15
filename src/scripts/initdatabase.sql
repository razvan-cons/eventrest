CREATE DATABASE  IF NOT EXISTS `dbo` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbo`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: database-1.cn6ntgbwi9xh.eu-west-1.rds.amazonaws.com    Database: dbo
-- ------------------------------------------------------
-- Server version	8.0.20

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `idCategory` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `description` varchar(500) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `parentCategoryId` int DEFAULT NULL,
  PRIMARY KEY (`idCategory`),
  UNIQUE KEY `UNIQUE_CATEGORY` (`name`,`parentCategoryId`),
  KEY `FK_Parent_Category_idx` (`parentCategoryId`),
  CONSTRAINT `FK_Parent_Category` FOREIGN KEY (`parentCategoryId`) REFERENCES `category` (`idCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Deporte',NULL,NULL),(2,'Música',NULL,NULL),(3,'Teatro',NULL,NULL),(4,'Fútbol','El deporte rey, puro espectáculo',1),(5,'Baloncesto','Ba-lon-ces-to!',1),(6,'Liga BBVA','El mejor fútbol, la liga española',4),(7,'Euroliga','Espectáculo en las mejores europeas',5),(8,'Copa del Rey','Vive la emoción de la copa',4);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `idEvent` int NOT NULL AUTO_INCREMENT,
  `title` varchar(250) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `description` varchar(5000) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `kickOffTime` datetime NOT NULL,
  `categoryId` int NOT NULL,
  PRIMARY KEY (`idEvent`),
  UNIQUE KEY `UNIQUE_EVENT` (`title`,`kickOffTime`),
  KEY `FK_CategoryId_idx` (`categoryId`),
  CONSTRAINT `FK_CategoryId` FOREIGN KEY (`categoryId`) REFERENCES `category` (`idCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'Athletic Club - FC Barcelona','Los equipos más laureados de la Copa del Rey, frente a frente','2021-04-21 21:00:00',8),(2,'FC Barcelona - Real Madrid','Vive la emoción del clásico','2021-06-01 20:00:00',6),(3,'Bilbao Basket - Alba Berlín','Ratatata ya está aquí la Euroliga en el Bilbao Arena','2021-05-01 19:30:00',7);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'dbo'
--

--
-- Dumping routines for database 'dbo'
--
/*!50003 DROP PROCEDURE IF EXISTS `get_event_breadcrumb` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `get_event_breadcrumb`(IN eventId INT)
BEGIN
	SELECT categoryId
	INTO @id_category
	FROM dbo.event
	WHERE idEvent = eventId;

	IF(@id_category IS NOT NULL) THEN
    
        WITH RECURSIVE
			children AS (
				SELECT 1 AS categoryLevel, d.* FROM category d WHERE idCategory = @id_category
				UNION ALL
				SELECT c.categoryLevel, d.* FROM category d INNER JOIN children c ON c.idCategory = d.parentCategoryId
			),
			parents AS (
				SELECT 1 AS categoryLevel, d.* FROM category d WHERE idCategory = @id_category
				UNION ALL
				SELECT p.categoryLevel - 1, d.* FROM category d INNER JOIN parents p ON d.idCategory = p.parentCategoryId
			)
		SELECT * FROM parents
		uniON SELECT * FROM children
		ORDER BY categoryLevel;
        
    END IF;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-15  0:56:16
