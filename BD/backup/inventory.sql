-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: inventory
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Farmaceutico');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` decimal(19,2) DEFAULT NULL,
  `last_date` datetime DEFAULT NULL,
  `last_user` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp7gj4l80fx8v0uap3b2crjwp5` (`product_id`)
) ENGINE=MyISAM AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,1.00,'2018-10-27 16:01:26','inicial',30.00,0,1),(2,1.00,'2018-10-23 20:13:17','inicial',100.00,0,2),(3,1.00,'2018-10-23 20:27:14','inicial',80.00,0,3),(4,1.00,'2018-10-24 18:53:59','inicial',80.00,0,4),(5,1.00,'2018-10-24 19:12:50','inicial',85.00,0,5),(6,1.00,'2018-10-24 19:14:43','inicial',110.00,0,6),(7,1.00,'2018-10-24 19:18:13','inicial',90.00,0,7),(8,1.00,'2018-10-24 19:20:03','inicial',30.00,0,8),(9,1.00,'2018-10-24 19:21:39','inicial',80.00,0,9),(10,1.00,'2018-10-24 19:22:57','inicial',110.00,0,10),(11,1.00,'2018-10-24 19:25:21','inicial',150.00,0,11),(12,1.00,'2018-10-24 19:29:56','inicial',20.00,0,12),(13,1.00,'2018-10-24 19:33:08','inicial',70.00,0,13),(14,1.00,'2018-10-24 19:36:43','inicial',20.00,0,14),(15,1.00,'2018-10-24 19:38:57','inicial',100.00,0,15),(16,1.00,'2018-10-24 19:44:00','inicial',15.00,0,16),(17,1.00,'2018-10-24 19:45:03','inicial',20.00,0,17),(18,1.00,'2018-10-24 19:49:56','inicial',40.00,0,18),(19,1.00,'2018-10-26 18:20:13','inicial',60.00,0,19),(20,1.00,'2018-10-26 18:25:11','inicial',30.00,0,20),(21,1.00,'2018-10-26 18:27:52','inicial',50.00,0,21),(22,1.00,'2018-10-26 18:29:35','inicial',45.00,0,22),(23,1.00,'2018-10-26 18:33:28','inicial',40.00,0,23),(24,1.00,'2018-10-26 18:37:10','inicial',30.00,0,24),(25,1.00,'2018-10-26 18:35:49','inicial',20.00,0,25),(26,1.00,'2018-10-26 18:41:27','inicial',40.00,0,26),(27,1.00,'2018-10-26 18:43:36','inicial',35.00,0,27),(28,1.00,'2018-10-26 18:45:36','inicial',35.00,0,28),(29,1.00,'2018-10-26 18:48:36','inicial',90.00,0,29),(30,1.00,'2018-10-26 18:50:08','inicial',60.00,0,30),(31,1.00,'2018-10-26 18:51:46','inicial',65.00,0,31),(32,1.00,'2018-10-26 18:52:51','inicial',40.00,0,32),(33,1.00,'2018-10-26 19:02:35','inicial',27.00,0,33),(34,1.00,'2018-10-26 19:02:02','inicial',40.00,0,34),(35,1.00,'2018-10-26 19:04:17','inicial',22.00,0,35),(36,1.00,'2018-10-26 19:06:06','inicial',25.00,0,36),(37,1.00,'2018-10-26 19:07:21','inicial',35.00,0,37),(38,1.00,'2018-10-26 19:13:15','inicial',30.00,0,38),(39,1.00,'2018-10-26 19:15:23','inicial',40.00,0,39),(40,1.00,'2018-10-26 19:20:44','inicial',45.00,0,40),(41,1.00,'2018-10-26 19:22:44','inicial',40.00,0,41),(42,1.00,'2018-10-27 12:56:49','inicial',65.00,0,42),(43,1.00,'2018-10-27 12:59:37','inicial',65.00,0,43),(44,1.00,'2018-10-27 13:00:58','inicial',70.00,0,44),(45,1.00,'2018-10-27 13:02:00','inicial',70.00,0,45),(46,1.00,'2018-10-27 13:03:59','inicial',75.00,0,46),(47,1.00,'2018-10-27 13:05:30','inicial',90.00,0,47),(48,1.00,'2018-10-27 13:14:02','inicial',35.00,0,48),(49,1.00,'2018-10-27 13:18:06','inicial',30.00,0,49),(50,1.00,'2018-10-27 13:20:03','inicial',40.00,0,50),(51,1.00,'2018-10-27 13:26:56','inicial',45.00,0,51),(52,1.00,'2018-10-27 13:26:34','inicial',40.00,0,52),(53,1.00,'2018-10-27 13:28:15','inicial',25.00,0,53),(54,1.00,'2018-10-27 13:29:49','inicial',25.00,0,54),(55,1.00,'2018-10-27 13:32:50','inicial',115.00,0,55),(56,1.00,'2018-10-27 13:33:33','inicial',115.00,0,56),(57,1.00,'2018-10-27 13:36:04','inicial',65.00,0,57),(58,1.00,'2018-10-27 13:37:49','inicial',70.00,0,58),(59,1.00,'2018-10-27 13:38:33','inicial',70.00,0,59),(60,1.00,'2018-10-27 13:40:47','inicial',80.00,0,60),(61,1.00,'2018-10-27 13:41:39','inicial',125.00,0,61),(62,1.00,'2018-10-27 13:42:27','inicial',85.00,0,62),(63,1.00,'2018-10-27 13:44:28','inicial',60.00,0,63),(64,1.00,'2018-10-27 14:08:03','inicial',50.00,0,64),(65,1.00,'2018-10-27 14:10:04','inicial',85.00,0,65),(66,1.00,'2018-10-27 14:11:01','inicial',80.00,0,66),(67,1.00,'2018-10-27 14:16:56','inicial',20.00,0,67),(68,1.00,'2018-10-27 14:19:18','inicial',20.00,0,68),(69,1.00,'2018-10-27 14:24:51','inicial',25.00,0,69),(70,1.00,'2018-10-27 14:27:36','inicial',40.00,0,70),(71,1.00,'2018-10-27 14:32:52','inicial',60.00,0,71),(72,1.00,'2018-10-27 14:36:05','inicial',50.00,0,72),(73,1.00,'2018-10-27 14:37:40','inicial',50.00,0,73),(74,1.00,'2018-10-27 14:42:02','inicial',50.00,0,74),(75,1.00,'2018-10-27 14:44:43','inicial',80.00,0,75),(76,1.00,'2018-10-27 14:46:22','inicial',60.00,0,76),(77,1.00,'2018-10-27 14:48:36','inicial',80.00,0,77),(78,1.00,'2018-10-27 14:49:53','inicial',40.00,0,78),(79,1.00,'2018-10-27 14:50:38','inicial',40.00,0,79),(80,1.00,'2018-10-27 14:52:34','inicial',40.00,0,80),(81,1.00,'2018-10-27 14:54:33','inicial',200.00,0,81),(82,1.00,'2018-10-27 14:59:51','inicial',70.00,0,82),(83,1.00,'2018-10-27 15:01:17','inicial',50.00,0,83),(84,1.00,'2018-10-27 15:02:54','inicial',45.00,0,84),(85,1.00,'2018-10-27 15:04:24','inicial',100.00,0,85),(86,1.00,'2018-10-27 15:08:05','inicial',60.00,0,86),(87,1.00,'2018-10-27 15:15:16','inicial',50.00,0,87),(88,1.00,'2018-10-27 15:19:11','inicial',25.00,0,88),(89,1.00,'2018-10-27 15:21:45','inicial',50.00,0,89),(90,1.00,'2018-10-27 16:00:11','inicial',65.00,0,90),(91,1.00,'2018-10-27 16:01:30','inicial',50.00,0,91);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `barcode` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8vqjj32x97p0xtnbe504aku1c` (`department_id`)
) ENGINE=MyISAM AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'7502211780830','Nimesulida','Flamozin jbe.',1),(2,'714908101003','calcio magnesio','caltron',1),(3,'7503006268069','Acido glutamico','Actigluv',1),(4,'7503004908875','Acarbosa 50 mg.','Acarbosa tab.',1),(5,'7502227874417','Aceclofenaco 100 mg.','Raamcedo tab.',1),(6,'7502009746345','Acemetacina 90 mg.','Urdinat cap.',1),(7,'7502001162457','Aceclofenaco 1.5g/100g','Mycinadol crema',1),(8,'780083141585','Aciclovir 0.05g/1g','Soviclor crema',1),(9,'785118753528','Aciclovir 200mg/5mL','Lesaclor jbe.',1),(10,'7501109765270','Aciclovir 200mg','Viroxil comp.',1),(11,'785120753530','Aciclovir 400 mg.','Maclov tab.',1),(12,'7501825300601','Acido Acetilsalicilico 100 mg.','Midolen tab.',1),(13,'7501349014190','Acido Alendronico 10 mg.','Acido Alendronico tab.',1),(14,'7501573900399','Acido Ascorbico 100 mg.','C-Messel tab.',1),(15,'7501825300038','Acido Nalidixico/ Fenazopiridina 500/50 mg.','Azogen tab.',1),(16,'7501537101879','Albendazol 2g/10mL','Bruzol susp.',1),(17,'7501075715927','Albendazol 200 mg.','Vermisen tab.',1),(18,'7501537102104','Alopurinol 300 mg','Puribel 300 tab.',1),(19,'7501825301356','Al, Mg, Metoclopramida, Dimeticona','Espraden tab.',1),(20,'7503003738879','Amantadina, Clorfenamina, Paracetamol 50mg/3mg/300mg','Rosel-T tab.',1),(21,'780083148584','Amantadina, Clorfenamina, Paracetamol 2.5g, 0.1g, 15.0g/100 mL','Ampigrin PFC gts.',1),(22,'780083148577','Amantadina, Clorfenamina, Paracetamol 0.50g, 0.02g, 3.00g/ 100 mL','Ampigrin PFC jbe.',1),(23,'7503000422566','Ambroxol 7.5 mg/ 1 mL','Viaxol gts.',1),(24,'7501825300359','Ambroxol 15 mg/ 5mL','Ebromin jbe.',1),(25,'7501573900337','Ambroxol 30 mg.','Cloxan comp.',1),(26,'7501537102210','Ambroxol, Clenbuterol 750 mg/ 0.5 mg','Oxolvan C gts.',1),(27,'7501537162214','Ambroxol, Clenbuterol 150 mg/ 0.1 mg','Oxolvan C jbe.',1),(28,'7502009740268','Ambroxol, Dextrometorfano 225 mg/ 225 mg/ 100 mL','Cobadex jbe. adulto',1),(29,'7502003381672','Ambroxol, Levodropropizina 300 mg/ 600 mg','Dizolvin Flux jbe.',1),(30,'7502003381146','Ambroxol, Oxoledina 115 mg/ 100 mg','Connexus jbe. infantil',1),(31,'7502003381153','Ambroxol, Oxoledina 225 mg/ 200 mg','Connexus jbe. adulto',1),(32,'7502009740282','Ambroxol, Salbutamol 40 mg/ 150 mg/ 100 mL','Fluxol jbe.',1),(33,'7501349020603','Amikacina 100 mg/ 2 mL','Amikacina amp. infantil con 1 amp.',1),(34,'7501349021488','Amikacina 500 mg/ 2mL','Amikacina amp. adulto con 2 amp.',1),(35,'7501125105135','Amikacina 500 mg/ 2 mL','Amk amp. adulto con 1 amp.',1),(36,'7501349028524','Amlodipino 5 mg.','Amlodipino tab.',1),(37,'7502208892294','Amoxicilina 250 mg/ 5 mL','Dimopen susp.',1),(38,'7503001007281','Amoxicilina 250 mg','Vandix cap.',1),(39,'7502208892232','Amoxicilina 500 mg','Dimopen cap.',1),(40,'7502009740206','Amoxicilina, Acido Clavulanico 125 mg/ 31.25 mg/ 5 mL','Clamoxin susp.125 mg',1),(41,'7502009740497','Amoxicilina, Acido Clavulanico 200 mg/ 28.5 mg/ 5 mL','Clamoxin 12 H 200',1),(42,'7502009740213','Amoxicilina, Acido Clavulanico, 250 mg/ 65.5 mg/ 5 mL','Clamoxin 250',1),(43,'7502009740503','Amoxicilina, Acido Clavulanico, 400 mg/ 57 mg/ 5 mL','Clamoxin 12 H 400',1),(44,'7502009740992','Amoxicilina, Acido Clavulanico, 500 mg/ 125 mg','Clamoxin 500',1),(45,'7501277080748','Amoxicilina, Acido Clavulanico, 500 mg/ 125 mg','Amoxicilina/ Acido Clavulanico',1),(46,'7502009745140','Amoxicilina, Acido Clavulanico, 600 mg/ 42.9 mg/ 5 mL','Clamoxin S 600',1),(47,'7502009740763','Amoxicilina, Acido Clavulanico, 875 mg/ 125 mg','Clamoxin 12 H 875',1),(48,'7502208892683','Amoxicilina, Bromhexin,  250 mg/ 8 mg/ 5 mL','Mucoxina susp.',1),(49,'7503001007137','Ampicilina 250 mg/ 5 mL','Mexapin 250',1),(50,'7503001007144','Ampicilina 500 mg/ 5mL','Mexapin 500 susp.',1),(51,'7501349021594','Ampicilina 500 mg','Ampicilina 500 tab.',1),(52,'7503000422672','Ampicilina 1 g','Pentiver 1 g tab.',1),(53,'7501349021051','Ampicilina 500 mg','Ampicilina 500 amp.',1),(54,'7501349021037','Ampicilina 1g','Ampicilina 1g amp.',1),(55,'780083140939','Ampicilina 250 mg','Ampigrin amp. infantil',1),(56,'780083140922','Ampicilina 500 mg','Ampigrin amp. adulto',1),(57,'7501349024885','Atorvastatina 10 mg','Atorvastatina 10 tab.',1),(58,'7501349024526','Atorvastatina 20 mg','Atorvastatina 20 tab.',1),(59,'785120754810','Atorvastatina 20 mg','Atorlip 20 tab.',1),(60,'7501349028647','Atorvastatina 40 mg','Atorvastatina 40 tab.',1),(61,'7502211783527','Azitromicina 4.00 g','Sicalan susp.',1),(62,'7502227871348','Azitromicina 500 mg','Craztronin tab.',1),(63,'7503002771167','Ban~o coloide pH 5.75','Suaderm granulado',1),(64,'785118752705','Bencidamida 45 mg/ 30 mL','Artroben sol. spray',1),(65,'785118752927','Bencidamida 540 mg/ 360 mL','Artroben sol. enjuague',1),(66,'785118752521','Bencidamida 5 %','Artroben crema',1),(67,'7502208892249','Bencilpenicilina Procainica, Bencilpenicilina Cristalina, 300,000 U/ 100,000 U','Lugaxil amp. 400,000 U',1),(68,'7501349011175','Bencilpenicilina Procainica/ Bencilpenicilina Cristalina 600,000 U/ 200,000 U','Penipot amp. 800,000 U',1),(69,'7501349015081','Bencilpenicilina, Bencilpenicilina procaina, Benzatina Bencilpenicilina 600,000 / 300,000 U/ 300,000 U','Bencelin Combinado amp. 1,200,000 U',1),(70,'7501123011506','Bencilpenicilina Clemizol, Metamizol, Vitamina C  200,000 U/ 250 mg/ 125 mg','Alivin Plus amp. infantil',1),(71,'7501123011605','Bencilpenicilina Clemizol, Metamizol, Vitamina C  300,000 U/ 500 mg/ 250 mg','Alivin Plus amp. adulto 300,000 U',1),(72,'7501123012008','Bencilpenicilina Sodica, Penicilina Clemizol 300,000 U/ 100,000 U','Anapenil amp. 400,000 U',1),(73,'7502226290102','Benzonatato 100 mg','Velpro cap.',1),(74,'7502009740916','Betametasona 8 mg/ 2 mL','Erispan amp.',1),(75,'7501752513204','Betametasona 0.1 g','Dermoval crema',1),(76,'7502001163805','Betametasona, Clotrimazol, Gentamicina  50 mg/ 1 g/ 0.1 g','Barmicil Compuesto crema',1),(77,'1306881052206','Betametasona, Ketorolaco 0.50 mg/ 10.00 mg','Scarpett K tab.',1),(78,'7501075718546','Bezafibrato 200 mg','Nibezvag tab.',1),(79,'7502211781059','Bromhexina 80 mg','Bromicof sol. infantil',1),(80,'7502211781189','Bromhexina 160 mg','Bromicof sol. adulto',1),(81,'7503002772799','Bromuro de Ipratopio 25 mg/ 100 mL','Suantrolin sol. nebulizacion',1),(82,'785118753771','Bromuro de Pinaverio 100 mg','Distental tab.',1),(83,'7501349024595','Bultihiocina 20 mg/ 1 mL','Hioscina amp.',1),(84,'7501537102920','Bultihiocina 10 mg','Brupacil tab.',1),(85,'7501277091034','Bultihiocina, Ibuprofeno 20 mg/ 400 mg','Espadiva tab.',1),(86,'7501573900115','Bultihiocina, Metamizol Sodico 10 mg/ 250 mg','Biomesna Compuesta grageas',1),(87,'780083140076','Bultihiocina, Metamizol 20 mg/ 2.5 g','Pasmodil amp.',1),(88,'7502216804678','Captopril 25 mg','Captopril tab.',1),(89,'7502216804821','Captopril 25 mg','Captopril tab. con 100 tab.',1),(90,'7503008344495','Lactobacillus','Lactopram cap. infantil',1),(91,'656599040608','hidrocloratiazida 25 mg','Acortiz tab. 25',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-19 22:06:44
