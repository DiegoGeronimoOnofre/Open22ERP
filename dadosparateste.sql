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
INSERT INTO `estoque` VALUES (1,101),(2,195),(3,317);
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
INSERT INTO `formaspagamento` VALUES (1,'A vista',30);
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
INSERT INTO `mov` VALUES (1,1,1,1,1435441209921),(2,2,1,1,1435441298590),(3,3,1,1,1435441336262),(4,1,1,1,1435441454662),(5,2,1,1,1435441592254),(6,1,1,2,1435441693902),(7,3,1,2,1435441809510),(8,2,1,2,1435442094296),(9,1,1,2,1435442288955);
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
INSERT INTO `mov_prod` VALUES (1,1,50.00,1,0.00),(1,2,100.00,2,0.00),(1,3,200.00,3,0.00),(2,1,50.00,1,0.00),(2,2,100.00,2,0.00),(3,1,50.00,1,0.00),(4,1,50.00,10,0.00),(4,2,100.00,20,0.00),(4,3,200.00,30,0.00),(5,1,50.00,100,0.00),(5,2,100.00,200,0.00),(5,3,200.00,300,0.00),(6,1,55.00,10,0.00),(6,2,110.00,15,0.00),(6,3,220.00,15,0.00),(7,1,55.00,1,0.00),(7,2,110.00,5,0.00),(8,2,110.00,8,0.00),(9,1,55.00,1,0.00),(9,2,110.00,1,0.00),(9,3,220.00,1,0.00);
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
INSERT INTO `pessoas` VALUES (1,'Pessoa1','xxx.842.117-70','contato@pessoa1.com','Cidade1, Estado1','(xx)1111-xxxx'),(2,'Pessoa2','xxx.346.564-88','contato@pessoa2.com','Cidade1, Estado1','(xx)2222-xxxx'),(3,'Pessoa3','xxx.047.394-02','contato@pessoa3.com','Cidade1, Estado1','(xx)3333-xxxx');
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
INSERT INTO `produtos` VALUES (1,'7898357410015','Descrição Produto1',50.00,55.00),(2,'7898357410016','Descrição Produto2',100.00,110.00),(3,'7898357410017','Descrição Produto3',200.00,220.00);
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

-- Dump completed on 2015-06-27 20:18:44
