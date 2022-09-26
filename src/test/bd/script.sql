CREATE DATABASE  IF NOT EXISTS `ecomerce` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ecomerce`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: ecomerce
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombres` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `dni` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'jhordan','briceño','jhordan795@hotmail.com','12345','545645464','95112331'),(3,'Wendy','Joins','joinswndy5@hotmail.com','12345','45445554','95112331');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrito`
--

DROP TABLE IF EXISTS `carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrito` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(8,2) DEFAULT NULL,
  `precio` decimal(8,2) DEFAULT NULL,
  `producto_id` int NOT NULL,
  `clientes_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_carrito_producto1_idx` (`producto_id`),
  KEY `fk_carrito_clientes1_idx` (`clientes_id`),
  CONSTRAINT `fk_carrito_clientes1` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `fk_carrito_producto1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrito`
--

LOCK TABLES `carrito` WRITE;
/*!40000 ALTER TABLE `carrito` DISABLE KEYS */;
/*!40000 ALTER TABLE `carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(155) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Zapatos','zapato militares'),(2,'Casacas','casacas térmicas'),(3,'Ropa','ropa clásica'),(4,'Poleras','Polera militares'),(5,'Gorras','Gorras '),(6,'Pantalones',NULL),(7,'Botas',NULL),(8,'Buzos',NULL),(11,'Damas','categoria damas'),(12,'Hombres',NULL);
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombres` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `pais` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `perfil` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `fechaNac` datetime DEFAULT NULL,
  `genero` varchar(45) DEFAULT NULL,
  `dni` int DEFAULT NULL,
  `fecha_nac` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Jerferson','farfan vargas ','farfan457@hotmail.com','Perú','654887','goto.jpg','95555141','2022-01-02 00:00:00','Masculino',77554122,NULL),(5,'Carlos','Zambrano Ruiz ','Zambrano25@hotmail.com','Perú','654887','goto.jpg','95555141',NULL,'Masculino',65545544,NULL),(6,'Hugo','Fuentes Lopez ','fuientes21@hotmail.com','Perú','654887','goto.jpg','95555141',NULL,'Femenino',471215547,NULL),(7,'Luz Wendy','Suarez Lopez ','hernandes21@hotmail.com','Perú','654887','goto.jpg','95555141',NULL,'Femenino',471215547,NULL),(13,'Jhordan Eugenio','Briceño Caipo','jhordan795@hotmail.com',NULL,NULL,NULL,'+51995052331',NULL,'Masculino',555444554,'2022-09-27 00:00:00.000000'),(16,'José Paul','Fuentes Polo','pauljose795@hotmail.com',NULL,NULL,NULL,'+51995052331',NULL,'Masculino',77349533,'2022-09-13 00:00:00.000000'),(18,'Juan Pablo','Guido Diaz','pablojuan777@hotmail.com',NULL,NULL,NULL,'+51995052331',NULL,'Masculino',444445454,'2022-09-26 00:00:00.000000'),(19,'Cris Poars','Wong','polarsPaul795@hotmail.com',NULL,NULL,NULL,'+51995052331',NULL,'Masculino',455454,'2022-09-15 00:00:00.000000'),(23,'Mary','Contreras Puyol','puyolmary897@gmail.com',NULL,NULL,NULL,'911111244',NULL,'Masculino',77852415,'2022-09-15 00:00:00.000000');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config` (
  `id` int NOT NULL AUTO_INCREMENT,
  `razon social` varchar(45) DEFAULT NULL,
  `logo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupon`
--

DROP TABLE IF EXISTS `cupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) DEFAULT NULL,
  `valor` decimal(8,2) DEFAULT NULL,
  `limite` decimal(8,2) DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `producto_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupon`
--

LOCK TABLES `cupon` WRITE;
/*!40000 ALTER TABLE `cupon` DISABLE KEYS */;
/*!40000 ALTER TABLE `cupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `destinatario` varchar(45) DEFAULT NULL,
  `dni` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `pais` varchar(45) DEFAULT NULL,
  `region` varchar(45) DEFAULT NULL,
  `provincia` varchar(45) DEFAULT NULL,
  `ciudad` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dventa`
--

DROP TABLE IF EXISTS `dventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dventa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subtotal` decimal(8,2) DEFAULT NULL,
  `cantidad` decimal(8,2) DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `producto_id` int NOT NULL,
  `venta_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dventa_producto1_idx` (`producto_id`),
  KEY `fk_dventa_venta1_idx` (`venta_id`),
  CONSTRAINT `fk_dventa_producto1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  CONSTRAINT `fk_dventa_venta1` FOREIGN KEY (`venta_id`) REFERENCES `venta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dventa`
--

LOCK TABLES `dventa` WRITE;
/*!40000 ALTER TABLE `dventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `dventa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `producto_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_images_producto1_idx` (`producto_id`),
  CONSTRAINT `fk_images_producto1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(8,2) DEFAULT NULL,
  `producto_id` int NOT NULL,
  `createAt` datetime DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_inventario_producto1_idx` (`producto_id`),
  CONSTRAINT `fk_inventario_producto1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (1,43.00,12,NULL,'2022-09-18 12:25:28.758000');
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
INSERT INTO `marca` VALUES (1,'nike','zapato'),(2,'Cat','Cat');
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca_categorias`
--

DROP TABLE IF EXISTS `marca_categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca_categorias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `marca_id` int NOT NULL,
  `categorias_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_marca_categorias_marca1_idx` (`marca_id`),
  KEY `fk_marca_categorias_categorias1_idx` (`categorias_id`),
  CONSTRAINT `fk_marca_categorias_categorias1` FOREIGN KEY (`categorias_id`) REFERENCES `categorias` (`id`),
  CONSTRAINT `fk_marca_categorias_marca1` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca_categorias`
--

LOCK TABLES `marca_categorias` WRITE;
/*!40000 ALTER TABLE `marca_categorias` DISABLE KEYS */;
/*!40000 ALTER TABLE `marca_categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensajes`
--

DROP TABLE IF EXISTS `mensajes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensajes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mensaje` varchar(200) DEFAULT NULL,
  `asunto` varchar(45) DEFAULT NULL,
  `clientes_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mensajes_clientes1_idx` (`clientes_id`),
  CONSTRAINT `fk_mensajes_clientes1` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensajes`
--

LOCK TABLES `mensajes` WRITE;
/*!40000 ALTER TABLE `mensajes` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensajes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `slug` varchar(45) DEFAULT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `precio` decimal(8,2) DEFAULT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  `contenido` varchar(155) DEFAULT NULL,
  `stock` decimal(8,2) DEFAULT NULL,
  `nventas` decimal(8,2) DEFAULT NULL,
  `npuntos` decimal(8,2) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `imagen` varchar(45) DEFAULT NULL,
  `categorias_id` int NOT NULL,
  `marca_id` int NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_producto_categorias1_idx` (`categorias_id`),
  KEY `fk_producto_marca1_idx` (`marca_id`),
  CONSTRAINT `fk_producto_categorias1` FOREIGN KEY (`categorias_id`) REFERENCES `categorias` (`id`),
  CONSTRAINT `fk_producto_marca1` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'fggggf','Zapato Cat',456.00,'zapato','zapato',4.00,3.00,5.00,'zapato','2022-01-01 00:00:00','botaverde.png',1,1,NULL),(4,'wsddssdsd','Chaleco falk',456.00,'casaca de cuero colo verde ejercito','casaca de cuero colo verde ejercito',4.00,3.00,5.00,'llegado',NULL,'gorramaron.jpg',1,1,'2022-09-13 00:00:00.000000'),(5,'rerererr','Chaleco Draw',150.00,'Powerful, extensible, and feature-packed frontend toolkit. Build and customize with Sass, utilize prebuilt grid system and components','Powerful, extensible, and feature-packed frontend toolkit. Build and customize with Sass, utilize prebuilt grid system and components',40.00,0.00,5.00,NULL,NULL,'buzoverde.png',2,2,'2022-09-16 00:00:00.000000'),(6,'ggfgfghhg','Zapatillas',459.00,'Powerful, extensible, and feature-packed frontend toolkit. Build and customize with Sass, utilize prebuilt grid system and components','Powerful, extensible, and feature-packed frontend toolkit. Build and customize with Sass, utilize prebuilt grid system and components',80.00,0.00,5.00,NULL,NULL,'R.jpg',1,1,'2022-09-16 00:00:00.000000'),(8,'cvbbvcvbb','Canguro trure',150.00,'Kit de herramientas frontend potente, extensible y repleta de funciones. Cree y personalice con Sass, utilice el sistema de cuadrícula ','Kit de herramientas frontend potente, extensible y repleta de funciones. Cree y personalice con Sass, utilice el sistema de cuadrícula ',43.00,0.00,5.00,NULL,NULL,'OIP(1).jpg',2,1,'2022-09-18 00:00:00.000000'),(9,NULL,'Botas Ng',250.00,'Kit de herramientas frontend potente, extensible y repleta de funciones. Cree y personalice con Sass, utilice el sistema de cuadrícula ','Kit de herramientas frontend potente, extensible y repleta de funciones. Cree y personalice con Sass, utilice el sistema de cuadrícula ',43.00,0.00,5.00,NULL,NULL,'OIP.jpg',2,1,'2022-09-18 00:00:00.000000'),(10,NULL,'Gorra Quueen',125.00,'Kit de herramientas frontend potente, extensible y repleta de funciones. Cree y personalice con Sass, utilice el sistema de cuadrícula ','Kit de herramientas frontend potente, extensible y repleta de funciones. Cree y personalice con Sass, utilice el sistema de cuadrícula ',43.00,0.00,5.00,NULL,NULL,'OIP(2).jpg',2,1,'2022-09-18 00:00:00.000000'),(11,NULL,'Tv Smat',0.00,'Kit de herramientas frontend potente, extensible y repleta de funciones. Cree y personalice con Sass, utilice el sistema de cuadrícula ','Kit de herramientas frontend potente, extensible y repleta de funciones. Cree y personalice con Sass, utilice el sistema de cuadrícula ',43.00,0.00,5.00,NULL,NULL,'pantalon.jpg',2,1,'2022-09-18 00:00:00.000000'),(12,NULL,'Tv Smat',0.00,'Kit de herramientas frontend potente, extensible y repleta de funciones. Cree y personalice con Sass, utilice el sistema de cuadrícula ','Kit de herramientas frontend potente, extensible y repleta de funciones. Cree y personalice con Sass, utilice el sistema de cuadrícula ',43.00,0.00,5.00,NULL,NULL,NULL,2,1,'2022-09-18 00:00:00.000000');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promocion`
--

DROP TABLE IF EXISTS `promocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promocion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) DEFAULT NULL,
  `banner` varchar(45) DEFAULT NULL,
  `descuento` decimal(8,2) DEFAULT NULL,
  `fechainicio` datetime DEFAULT NULL,
  `fechafin` datetime DEFAULT NULL,
  `producto_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_promocion_producto1_idx` (`producto_id`),
  CONSTRAINT `fk_promocion_producto1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promocion`
--

LOCK TABLES `promocion` WRITE;
/*!40000 ALTER TABLE `promocion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estrellas` int DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `producto_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_review_producto1_idx` (`producto_id`),
  CONSTRAINT `fk_review_producto1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nventa` int DEFAULT NULL,
  `subtotal` decimal(8,2) DEFAULT NULL,
  `total_pagar` decimal(8,2) DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `tracking` varchar(45) DEFAULT NULL,
  `envio_precio` decimal(8,2) DEFAULT NULL,
  `transaccion` varchar(45) DEFAULT NULL,
  `metodo_pago` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `tipo_descuento` varchar(45) DEFAULT NULL,
  `nota` varchar(200) DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `clientes_id` int NOT NULL,
  `direccion_id` int NOT NULL,
  `cupon_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_venta_clientes1_idx` (`clientes_id`),
  KEY `fk_venta_direccion1_idx` (`direccion_id`),
  KEY `fk_venta_cupon1_idx` (`cupon_id`),
  CONSTRAINT `fk_venta_clientes1` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `fk_venta_cupon1` FOREIGN KEY (`cupon_id`) REFERENCES `cupon` (`id`),
  CONSTRAINT `fk_venta_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-26 18:28:03
