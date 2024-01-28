-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: tfm_mysql
-- ------------------------------------------------------
-- Server version	11.2.2-MariaDB-1:11.2.2+maria~ubu2204

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
-- Table structure for table `attribute`
--

DROP TABLE IF EXISTS `attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribute` (
  `id_attribute` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `id_category` int(11) NOT NULL,
  PRIMARY KEY (`id_attribute`),
  KEY `FKqnam9996gjf3u3833lox3epn8` (`id_category`),
  CONSTRAINT `FKqnam9996gjf3u3833lox3epn8` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute`
--

LOCK TABLES `attribute` WRITE;
/*!40000 ALTER TABLE `attribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attributevalue`
--

DROP TABLE IF EXISTS `attributevalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attributevalue` (
  `id_attributevalue` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) DEFAULT NULL,
  `id_attribute` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  PRIMARY KEY (`id_attributevalue`),
  KEY `FKiev4toj9h72e4dc1hrusv9fd` (`id_attribute`),
  KEY `FKf5x370ofrendggw0n5g3ygi8u` (`id_product`),
  CONSTRAINT `FKf5x370ofrendggw0n5g3ygi8u` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  CONSTRAINT `FKiev4toj9h72e4dc1hrusv9fd` FOREIGN KEY (`id_attribute`) REFERENCES `attribute` (`id_attribute`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attributevalue`
--

LOCK TABLES `attributevalue` WRITE;
/*!40000 ALTER TABLE `attributevalue` DISABLE KEYS */;
/*!40000 ALTER TABLE `attributevalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id_category` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comparison`
--

DROP TABLE IF EXISTS `comparison`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comparison` (
  `id_comparison` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `valoration` int(11) NOT NULL,
  PRIMARY KEY (`id_comparison`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comparison`
--

LOCK TABLES `comparison` WRITE;
/*!40000 ALTER TABLE `comparison` DISABLE KEYS */;
/*!40000 ALTER TABLE `comparison` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id_category` int(11) NOT NULL,
  PRIMARY KEY (`id_product`),
  KEY `FK5cxv31vuhc7v32omftlxa8k3c` (`id_category`),
  CONSTRAINT `FK5cxv31vuhc7v32omftlxa8k3c` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tfm_mysql'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-27 12:46:49

INSERT INTO tfm_mysql.`user` (email,name,password,roles) VALUES
	 ('ericduro@gmail.com','Eric Duro','$2a$10$Yd1f3C9PCbSyFZ3oloxs6uar51L8BGdVxhyOHNWqbkDNXvgZBCx5C','ADMIN');

INSERT INTO tfm_mysql.category (name) VALUES
	 ('Teclados'),
	 ('Televisores'),
	 ('Monitores');

INSERT INTO tfm_mysql.product (description,image,name,id_category) VALUES
	 ('Akko 3084B RGB Teclado mecánico para juegos, multimodos (BT5.0/2.4Ghz/Tipo C) Teclado compacto con 5 pines intercambiables en caliente, teclas PBT, macros programables (B&g interruptor lineal). Precio: 109,99€','https://m.media-amazon.com/images/I/61cGJprVuOL._AC_SL1290_.jpg','Akko 3084B RGB',1),
	 ('Logitech G915 LIGHTSPEED TKL Teclado Gaming Mecánico Inalámbrico con teclas GL-Táctil de bajo perfil, LIGHTSYNC RGB, Diseño ultra delgado, 40 Horas duración de Batería, Disposición QWERTY ES - Negro. Precio: 177,00€','https://m.media-amazon.com/images/I/61pgWVrhHGL._AC_SL1500_.jpg','Logitech G915 LIGHTSPEED TKL',1),
	 ('MSI G281UV Monitor Gaming 4K UHD de 27,9 Pulgadas - Panel IPS 3840 x 2160, 60 Hz / 4 ms GtG, FreeSync, 94% DCI-P3 Color (10 bits), DisplayHDR 400, Menos luz Azul Premium - DP 1.2a, HDMI 2.0b. Precio: 199,00€','https://m.media-amazon.com/images/I/815c+bUSTJL._AC_SL1500_.jpg','MSI G281UV',3),
	 ('Samsung LS27C312EAUXEN - Monitor 247 FullHD (1920x1080, 16:9, Freesync, IPS, 75Hz, 5ms, Modo Eye Saver), Negro. Precio: 125,99€','https://m.media-amazon.com/images/I/71dQbAi9UzL._AC_SL1500_.jpg','Samsung LS27C312EAUXEN',3),
	 ('Cecotec Televisor LED 32" Smart TV LED a3 Series ALH30032s. Resolución LED HD, Android 11, Diseño sin Marco, MEMC, Dolby Atmos, HDR10, 2 Altavoces de 10W, Modelo 2023 [Clase de eficiencia energética F]. Precio: 159,00€','https://m.media-amazon.com/images/I/71xSLxoMgFL._AC_SL1000_.jpg','Cecotec Televisor LED 32"',2),
	 ('TOSHIBA 32WV2E63DG Smart TV de 32" con Resolución HD HDR10, Compatible con asistentes de Voz Alexa y Google, TV Satélite, Bluetooth, Dolby Audio [Clase de eficiencia energética E]. Precio: 169,99€','https://m.media-amazon.com/images/I/71MZUnQs8-L._AC_SL1500_.jpg','TOSHIBA 32WV2E63DG Smart TV de 32"',2),
	 ('Razer BlackWidow V3 Mini HyperSpeed (Yellow Switch) - Teclado para juegos 65% compacto con interruptores mecánicos (lineales y de clic, iluminación RGB Chroma) Teclado Español - Negro. Precio: 189,99€','https://m.media-amazon.com/images/I/71nIltoyIkS._AC_SL1500_.jpg','Razer BlackWidow V3 Mini',1),
	 ('Tesla - Smart TV de 32" (81cm), Televisión Resolución UHD, Android TV 11, Hey Google Official Assistant, WiFi & Bluetooth, 2 Altavoces de 10W, Chromecast Integrado, 1.366x768 (32E635BHS) - 2023 [Clase de eficiencia energética E]. Precio: 189,99€','https://m.media-amazon.com/images/I/61P56qwjTHL._AC_SL1500_.jpg','Tesla - Smart TV de 32"',2),
	 ('Acer EK241YHBIF - Monitor 24 " Full HD 100 Hz (60 cm, 1920x1080, 16:9, 250 Nits, Tiempo de Respuesta 1 ms VRB, AMD FreeSync, Zero Frame 1xVGA, 1xHDMI 1.4) Monitor PC Color Negro. Precio: 99,90€ ','https://m.media-amazon.com/images/I/71PGMANcaIL._AC_SL1500_.jpg','Acer EK241YHBIF',3),
	 ('EPOMAKER 65% Compact TH66 Pro Teclado Mecánico Inalámbrico/Bluetooth/USB-C, con Iluminación RGB, Hot Swap, Teclas Multimedia, para PC/Mac/PS4/PS5/Xbox (TH66 Monet White, Epomaker Budgerigar Switch). Precio: 99,99€','https://m.media-amazon.com/images/I/61SY+dIECTL._AC_SL1500_.jpg','EPOMAKER 65% Compact TH66 Pro',1);
INSERT INTO tfm_mysql.product (description,image,name,id_category) VALUES
	 ('Dell SE2422HX 24" Full HD (1920x1080) Monitor, 75Hz, VA, 5ms, AMD FreeSync, HDMI, VGA, Negro. Precio 105,00€','https://m.media-amazon.com/images/I/81fLxRHnI+L._AC_SL1500_.jpg','Dell SE2422HX 24"',3);


