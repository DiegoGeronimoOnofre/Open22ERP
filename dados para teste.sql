-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (i686)
--
-- Host: 192.168.1.2    Database: db
-- ------------------------------------------------------
-- Server version	5.5.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estoque` (
  `cod_prod` int(11) NOT NULL,
  `qt` int(11) NOT NULL,
  PRIMARY KEY (`cod_prod`),
  CONSTRAINT `fk_est_cod_prod` FOREIGN KEY (`cod_prod`) REFERENCES `produtos` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (1,0),(2,0),(3,0);
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formaspagamento`
--

DROP TABLE IF EXISTS `formaspagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formaspagamento` (
  `codigo` int(11) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `limite_value` int(11) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formaspagamento`
--

LOCK TABLES `formaspagamento` WRITE;
/*!40000 ALTER TABLE `formaspagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `formaspagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mov`
--

DROP TABLE IF EXISTS `mov`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mov` (
  `codigo` int(11) NOT NULL,
  `cod_cli` int(11) DEFAULT NULL,
  `cod_pay_method` int(11) NOT NULL,
  `mov_type` int(11) NOT NULL,
  `mov_time` bigint(8) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fkcli` (`cod_cli`),
  KEY `fkpay_method` (`cod_pay_method`),
  CONSTRAINT `fkcli` FOREIGN KEY (`cod_cli`) REFERENCES `pessoas` (`codigo`),
  CONSTRAINT `fkpay_method` FOREIGN KEY (`cod_pay_method`) REFERENCES `formaspagamento` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mov`
--

LOCK TABLES `mov` WRITE;
/*!40000 ALTER TABLE `mov` DISABLE KEYS */;
/*!40000 ALTER TABLE `mov` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mov_prod`
--

DROP TABLE IF EXISTS `mov_prod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mov_prod` (
  `cod_mov` int(11) NOT NULL DEFAULT '0',
  `cod_prod` int(11) NOT NULL DEFAULT '0',
  `preco` double(6,2) DEFAULT NULL,
  `qt` int(11) DEFAULT NULL,
  `total` double(6,2) DEFAULT NULL,
  PRIMARY KEY (`cod_mov`,`cod_prod`),
  KEY `fk_cod_prod` (`cod_prod`),
  CONSTRAINT `fk_cod_mov` FOREIGN KEY (`cod_mov`) REFERENCES `mov` (`codigo`),
  CONSTRAINT `fk_cod_prod` FOREIGN KEY (`cod_prod`) REFERENCES `produtos` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mov_prod`
--

LOCK TABLES `mov_prod` WRITE;
/*!40000 ALTER TABLE `mov_prod` DISABLE KEYS */;
/*!40000 ALTER TABLE `mov_prod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoas`
--

DROP TABLE IF EXISTS `pessoas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoas` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `cpf` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `localizacao` varchar(100) DEFAULT NULL,
  `telefone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoas`
--

LOCK TABLES `pessoas` WRITE;
/*!40000 ALTER TABLE `pessoas` DISABLE KEYS */;
INSERT INTO `pessoas` VALUES (1,'Primeira pessoa','cpf111','Email@111.com','loc111','tel111'),(2,'Segunda pessoa','cpf222','Email@222.com','loc222','tel222'),(3,'Terceira pessoa','cpf333','Email@333.com','loc333','tel333');
/*!40000 ALTER TABLE `pessoas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtos` (
  `codigo` int(11) NOT NULL,
  `cod_barras` varchar(30) DEFAULT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  `preco_compra` double(6,2) DEFAULT NULL,
  `preco_venda` double(6,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (1,'CB111','Primeiro produto',50.00,55.00),(2,'CB222','Segundo produto',30.00,33.00),(3,'CB333','Terceiro produto',100.00,110.00);
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-22 17:14:38
