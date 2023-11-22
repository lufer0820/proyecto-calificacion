CREATE DATABASE  IF NOT EXISTS `proyecto` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `proyecto`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: proyecto
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiante` (
  `idestudiante` int unsigned NOT NULL AUTO_INCREMENT,
  `identificacion` varchar(12) NOT NULL,
  `nombre_acudiente` text,
  `telefono_acudiente` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idestudiante`),
  KEY `Estudiante_FKIndex1` (`identificacion`),
  CONSTRAINT `estudiante_ibfk_1` FOREIGN KEY (`identificacion`) REFERENCES `persona` (`identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES (1,'3','Martín Lawrence','+86 (559) 144-8683'),(2,'4','Martín Prescott','+27 (922) 824-0260'),(3,'5','Steven Seagal','+358 (425) 695-4068'),(4,'101010101','Steven Seagal','1239012'),(5,'321321','Ninguno','Ninguno');
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materia`
--

DROP TABLE IF EXISTS `materia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materia` (
  `id_materia` int unsigned NOT NULL AUTO_INCREMENT,
  `idprofesor` int unsigned NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `dia` varchar(10) NOT NULL,
  `horainicio` varchar(10) NOT NULL,
  `horafin` varchar(10) NOT NULL,
  PRIMARY KEY (`id_materia`),
  KEY `Materia_FKIndex1` (`idprofesor`),
  CONSTRAINT `materia_ibfk_1` FOREIGN KEY (`idprofesor`) REFERENCES `profesor` (`idprofesor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materia`
--

LOCK TABLES `materia` WRITE;
/*!40000 ALTER TABLE `materia` DISABLE KEYS */;
INSERT INTO `materia` VALUES (1,1,'Ciencia','Lunes','07:10','08:10'),(4,1,'Matemáticas','Lunes','10:00','12:00'),(5,8,'Informática','Miércoles','10:00','11:00');
/*!40000 ALTER TABLE `materia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota`
--

DROP TABLE IF EXISTS `nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota` (
  `idnota` int NOT NULL AUTO_INCREMENT,
  `id_estudiante` int unsigned NOT NULL,
  `idtarea` int unsigned NOT NULL,
  `nota` double NOT NULL,
  `comentario` text,
  PRIMARY KEY (`idnota`),
  KEY `Nota_FKIndex1` (`idtarea`),
  KEY `Nota_FKIndex2` (`id_estudiante`),
  CONSTRAINT `nota_ibfk_1` FOREIGN KEY (`idtarea`) REFERENCES `tarea` (`idtarea`),
  CONSTRAINT `nota_ibfk_2` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`idestudiante`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota`
--

LOCK TABLES `nota` WRITE;
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
INSERT INTO `nota` VALUES (1,2,1,1,''),(2,3,1,0,NULL),(3,2,2,5,NULL),(4,3,2,0,NULL),(5,2,3,0,NULL),(6,3,3,2,''),(12,5,7,5,'');
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `nota_BEFORE_INSERT` BEFORE INSERT ON `nota` FOR EACH ROW BEGIN
	IF new.nota > 5 THEN
		SET NEW.nota = 5;
	ELSEIF new.nota < 0 THEN
		SET NEW.nota = 0;
	ELSEIF new.nota = NULL THEN
		SET NEW.nota = 0;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `identificacion` varchar(12) NOT NULL,
  `tipo_identificacion` varchar(6) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(200) NOT NULL,
  `direccion` text NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `rol` enum('Profesor','Estudiante') NOT NULL,
  PRIMARY KEY (`identificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES ('1','C.C.','Carlos','Bodoque','Apt 1246','+82 (475) 108-2172','jsells0@dot.gov','Profesor'),('101010101','C.C.','Carlos','Santana Martínez','Calle 1#1-1','3000000000','csantanamartinez@gmail.com','Estudiante'),('120349120','C.C.','Camilo','Murcia','Calle 50#14-91','310 2557269','cmurcia@gmail.com','Profesor'),('123456789','C.C.','Agustín','Hernández','calle 1#1-1','12309812390','papellido1@correo.com','Profesor'),('2','C.C.','Brooke','Embery','Room 1593','+82 (652) 666-4495','bembery1@cpanel.net','Profesor'),('3','CON','Jami','Storah','1st Floor','+86 (559) 144-8683','jstorah0@hugedomains.com','Estudiante'),('321321','C.C.','Juan','Vargas','321321','321321','321321@correo.com','Estudiante'),('4','CON','Marten','Kilgour','Suite 13','+27 (922) 824-0260','mkilgour1@bravesites.com','Estudiante'),('5','C.C.','Davy','Broomer','Apt 1282','+358 (425) 695-4068','dbroomer2@trellian.com','Estudiante');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `persona_AFTER_INSERT` AFTER INSERT ON `persona` FOR EACH ROW BEGIN
	IF new.rol = 'Profesor' THEN
		SET @usuario = CONCAT(lower(LEFT(new.nombre, 1)), REPLACE(lower(new.apellidos), ' ', ''), RIGHT(new.identificacion, 2));
		INSERT INTO profesor(identificacion, usuario, contrasenia) VALUES (new.identificacion, @usuario, @usuario);
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor` (
  `idprofesor` int unsigned NOT NULL AUTO_INCREMENT,
  `identificacion` varchar(12) NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `contrasenia` varchar(20) NOT NULL,
  PRIMARY KEY (`idprofesor`),
  KEY `profesor_FKIndex1` (`identificacion`),
  CONSTRAINT `profesor_ibfk_1` FOREIGN KEY (`identificacion`) REFERENCES `persona` (`identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (1,'1','JStorah1','Bodoque2024'),(2,'2','BEmbery2','BEmbery2'),(6,'120349120','cmurcia','cmurcia'),(7,'123456789','papellido189','papellido189'),(8,'123456789','papellido','Agustin');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registra`
--

DROP TABLE IF EXISTS `registra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registra` (
  `idregistro` int NOT NULL AUTO_INCREMENT,
  `id_materia` int unsigned NOT NULL,
  `id_estudiante` int unsigned NOT NULL,
  PRIMARY KEY (`idregistro`),
  KEY `registra_FKIndex1` (`id_estudiante`),
  KEY `registra_FKIndex2` (`id_materia`),
  CONSTRAINT `registra_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiante` (`idestudiante`),
  CONSTRAINT `registra_ibfk_2` FOREIGN KEY (`id_materia`) REFERENCES `materia` (`id_materia`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registra`
--

LOCK TABLES `registra` WRITE;
/*!40000 ALTER TABLE `registra` DISABLE KEYS */;
INSERT INTO `registra` VALUES (1,1,3),(2,1,2),(4,4,3),(6,5,5);
/*!40000 ALTER TABLE `registra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarea`
--

DROP TABLE IF EXISTS `tarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarea` (
  `idtarea` int unsigned NOT NULL AUTO_INCREMENT,
  `id_materia` int unsigned NOT NULL,
  `descripcion` text NOT NULL,
  `nombre` text NOT NULL,
  PRIMARY KEY (`idtarea`),
  KEY `tarea_FKIndex1` (`id_materia`),
  CONSTRAINT `tarea_ibfk_1` FOREIGN KEY (`id_materia`) REFERENCES `materia` (`id_materia`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarea`
--

LOCK TABLES `tarea` WRITE;
/*!40000 ALTER TABLE `tarea` DISABLE KEYS */;
INSERT INTO `tarea` VALUES (1,1,'Hacer una maqueta de la luna',''),(2,1,'nuevos detalles del taller','nuevo taller'),(3,4,'Ingrese una imagen de una maqueta del edificio más grande que ha visto en fotos','Maqueta edificio'),(7,5,'Detalles tarea de prueba','Tarea de Prueba');
/*!40000 ALTER TABLE `tarea` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tarea_AFTER_INSERT` AFTER INSERT ON `tarea` FOR EACH ROW BEGIN
    CALL agregar_notas_nueva_tarea(new.id_materia, new.idtarea);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'proyecto'
--

--
-- Dumping routines for database 'proyecto'
--
/*!50003 DROP PROCEDURE IF EXISTS `agregar_notas_nueva_tarea` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregar_notas_nueva_tarea`(IN id_materia INT, IN id_tarea INT)
BEGIN
	DECLARE var_final INTEGER DEFAULT 0;
    DECLARE estudiante INTEGER;
	DECLARE estudiantes CURSOR FOR SELECT id_estudiante FROM registra WHERE registra.id_materia = id_materia;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET var_final = 1;
    
    OPEN estudiantes;
    bucle: LOOP
		FETCH estudiantes INTO estudiante;
        
        IF var_final = 1 THEN
			LEAVE bucle;
        END IF;
        
        INSERT INTO nota (id_estudiante, idtarea, nota) VALUES (estudiante, id_tarea, 0);
    END LOOP bucle;    
	CLOSE estudiantes;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-21 18:33:08
