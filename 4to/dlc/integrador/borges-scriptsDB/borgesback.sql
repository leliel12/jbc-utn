-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-Debian_1ubuntu3.3-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema borgesdb
--

CREATE DATABASE IF NOT EXISTS borgesdb;
USE borgesdb;

--
-- Definition of table `borgesdb`.`detalle_ticket`
--

DROP TABLE IF EXISTS `borgesdb`.`detalle_ticket`;
CREATE TABLE  `borgesdb`.`detalle_ticket` (
  `id_detalle` int(11) NOT NULL auto_increment,
  `id_ticket` int(11) NOT NULL,
  `cantidad` smallint(6) NOT NULL,
  `isbn` varchar(30) default NULL,
  `id_servicio` smallint(6) default NULL,
  `activo` smallint(6) NOT NULL,
  PRIMARY KEY  (`id_detalle`,`id_ticket`),
  KEY `isbn` (`isbn`),
  KEY `id_servicio` (`id_servicio`),
  KEY `id_ticket` (`id_ticket`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borgesdb`.`detalle_ticket`
--

/*!40000 ALTER TABLE `detalle_ticket` DISABLE KEYS */;
LOCK TABLES `detalle_ticket` WRITE;
INSERT INTO `borgesdb`.`detalle_ticket` VALUES  (1,1,1,'84-663-0991-8',NULL,1),
 (2,1,1,NULL,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `detalle_ticket` ENABLE KEYS */;


--
-- Definition of table `borgesdb`.`forma_pago`
--

DROP TABLE IF EXISTS `borgesdb`.`forma_pago`;
CREATE TABLE  `borgesdb`.`forma_pago` (
  `id_forma_pago` smallint(6) NOT NULL auto_increment,
  `forma_pago` varchar(100) NOT NULL,
  `descuento` float(5,5) NOT NULL,
  `activo` smallint(6) NOT NULL,
  PRIMARY KEY  (`id_forma_pago`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borgesdb`.`forma_pago`
--

/*!40000 ALTER TABLE `forma_pago` DISABLE KEYS */;
LOCK TABLES `forma_pago` WRITE;
INSERT INTO `borgesdb`.`forma_pago` VALUES  (1,'Efectivo',0.00000,1),
 (2,'Debito',0.50000,1),
 (3,'Tarjeta',0.20000,1),
 (4,'Cheque',0.10000,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `forma_pago` ENABLE KEYS */;


--
-- Definition of table `borgesdb`.`libro`
--

DROP TABLE IF EXISTS `borgesdb`.`libro`;
CREATE TABLE  `borgesdb`.`libro` (
  `isbn` varchar(30) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `autor` varchar(100) NOT NULL,
  `precio` float(5,2) NOT NULL,
  `editorial` varchar(100) NOT NULL,
  `activo` smallint(6) NOT NULL,
  PRIMARY KEY  (`isbn`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borgesdb`.`libro`
--

/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
LOCK TABLES `libro` WRITE;
INSERT INTO `borgesdb`.`libro` VALUES  ('B-0000-01','Don Quijote de la Mancha','Miguel de Cervantes Saavedra',29.50,'Alfaguara',1),
 ('84-663-0991-8','Las Aventuras del Capitan Alatriste','Arturo y Carlota Perez-Reverte',22.00,'Punto de Lectura',1),
 ('84-395-8721-X','Ficciones','Jorge Luis Borges',18.00,'Biblioteca La Nacion',1),
 ('950-9080-00-4','Artmis Fowl','Eoin Colfer',20.00,'Montena',1),
 ('84-450-7022-3','El Fin de La Infancia','Arthur C. Clarke',32.50,'Minotauro',1),
 ('B-666','Necronomicon','A-Hazif',66.60,'Burinjell',1),
 ('987-545-194-0','Los Dias del Fuego','Liliana Bodoc',39.90,'norma',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;


--
-- Definition of table `borgesdb`.`localidad`
--

DROP TABLE IF EXISTS `borgesdb`.`localidad`;
CREATE TABLE  `borgesdb`.`localidad` (
  `id_localidad` smallint(6) NOT NULL auto_increment,
  `localidad` varchar(24) NOT NULL,
  `id_region` smallint(6) NOT NULL,
  `activo` smallint(6) NOT NULL,
  PRIMARY KEY  (`id_localidad`),
  KEY `id_region` (`id_region`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borgesdb`.`localidad`
--

/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
LOCK TABLES `localidad` WRITE;
INSERT INTO `borgesdb`.`localidad` VALUES  (1,'Posadas',4,1),
 (2,'Cordoba',3,1),
 (3,'Rio Cuarto',3,1),
 (4,'Capital Federal',5,1),
 (5,'Copiapo',2,1),
 (6,'Valparaiso',1,1),
 (7,'NN',6,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;


--
-- Definition of table `borgesdb`.`pagina`
--

DROP TABLE IF EXISTS `borgesdb`.`pagina`;
CREATE TABLE  `borgesdb`.`pagina` (
  `id_pagina` smallint(6) NOT NULL auto_increment,
  `pagina` varchar(100) NOT NULL,
  `activo` smallint(6) NOT NULL,
  PRIMARY KEY  (`id_pagina`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borgesdb`.`pagina`
--

/*!40000 ALTER TABLE `pagina` DISABLE KEYS */;
LOCK TABLES `pagina` WRITE;
INSERT INTO `borgesdb`.`pagina` VALUES  (1,'Home',1),
 (2,'Administracion',1),
 (3,'Comprar',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `pagina` ENABLE KEYS */;


--
-- Definition of table `borgesdb`.`pais`
--

DROP TABLE IF EXISTS `borgesdb`.`pais`;
CREATE TABLE  `borgesdb`.`pais` (
  `id_pais` smallint(6) NOT NULL auto_increment,
  `pais` varchar(24) NOT NULL,
  `activo` smallint(6) NOT NULL,
  PRIMARY KEY  (`id_pais`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borgesdb`.`pais`
--

/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
LOCK TABLES `pais` WRITE;
INSERT INTO `borgesdb`.`pais` VALUES  (1,'Argentina',1),
 (2,'Chile',1),
 (3,'NN',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;


--
-- Definition of table `borgesdb`.`region`
--

DROP TABLE IF EXISTS `borgesdb`.`region`;
CREATE TABLE  `borgesdb`.`region` (
  `id_region` smallint(6) NOT NULL auto_increment,
  `region` varchar(24) NOT NULL,
  `id_pais` smallint(6) NOT NULL,
  `activo` smallint(6) NOT NULL,
  PRIMARY KEY  (`id_region`),
  KEY `id_pais` (`id_pais`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borgesdb`.`region`
--

/*!40000 ALTER TABLE `region` DISABLE KEYS */;
LOCK TABLES `region` WRITE;
INSERT INTO `borgesdb`.`region` VALUES  (1,'Valparaiso',2,1),
 (2,'Atacama',2,1),
 (3,'Cordoba',1,1),
 (4,'Misiones',1,1),
 (5,'Buenos Aires',1,1),
 (6,'NN',3,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `region` ENABLE KEYS */;


--
-- Definition of table `borgesdb`.`roles`
--

DROP TABLE IF EXISTS `borgesdb`.`roles`;
CREATE TABLE  `borgesdb`.`roles` (
  `id_roles` smallint(6) NOT NULL auto_increment,
  `roles` varchar(30) NOT NULL,
  `activo` smallint(6) NOT NULL,
  PRIMARY KEY  (`id_roles`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borgesdb`.`roles`
--

/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
LOCK TABLES `roles` WRITE;
INSERT INTO `borgesdb`.`roles` VALUES  (1,'Administrador',1),
 (2,'Usuario',1),
 (3,'Anonimo',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


--
-- Definition of table `borgesdb`.`roles_x_pagina`
--

DROP TABLE IF EXISTS `borgesdb`.`roles_x_pagina`;
CREATE TABLE  `borgesdb`.`roles_x_pagina` (
  `id_roles` smallint(6) NOT NULL,
  `id_pagina` smallint(6) NOT NULL,
  `activo` smallint(6) NOT NULL,
  PRIMARY KEY  (`id_roles`,`id_pagina`),
  KEY `id_pagina` (`id_pagina`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borgesdb`.`roles_x_pagina`
--

/*!40000 ALTER TABLE `roles_x_pagina` DISABLE KEYS */;
LOCK TABLES `roles_x_pagina` WRITE;
INSERT INTO `borgesdb`.`roles_x_pagina` VALUES  (1,1,1),
 (1,2,1),
 (2,1,1),
 (2,3,1),
 (1,3,1),
 (3,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `roles_x_pagina` ENABLE KEYS */;


--
-- Definition of table `borgesdb`.`servicio`
--

DROP TABLE IF EXISTS `borgesdb`.`servicio`;
CREATE TABLE  `borgesdb`.`servicio` (
  `id_servicio` smallint(6) NOT NULL auto_increment,
  `servicio` varchar(100) NOT NULL,
  `precio` float(5,2) NOT NULL,
  `activo` smallint(6) NOT NULL,
  PRIMARY KEY  (`id_servicio`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borgesdb`.`servicio`
--

/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
LOCK TABLES `servicio` WRITE;
INSERT INTO `borgesdb`.`servicio` VALUES  (1,'Encuadernado',50.00,1),
 (2,'Restauracion',100.00,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;


--
-- Definition of table `borgesdb`.`ticket`
--

DROP TABLE IF EXISTS `borgesdb`.`ticket`;
CREATE TABLE  `borgesdb`.`ticket` (
  `id_ticket` int(11) NOT NULL auto_increment,
  `fecha` date NOT NULL,
  `login` varchar(100) NOT NULL,
  `total_precio` float(5,2) NOT NULL,
  `id_forma_pago` smallint(6) NOT NULL,
  `activo` smallint(6) NOT NULL,
  PRIMARY KEY  (`id_ticket`),
  KEY `login` (`login`),
  KEY `id_forma_pago` (`id_forma_pago`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borgesdb`.`ticket`
--

/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
LOCK TABLES `ticket` WRITE;
INSERT INTO `borgesdb`.`ticket` VALUES  (1,'2008-04-09','Kaiser',72.00,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;


--
-- Definition of table `borgesdb`.`usuario`
--

DROP TABLE IF EXISTS `borgesdb`.`usuario`;
CREATE TABLE  `borgesdb`.`usuario` (
  `login` varchar(100) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `mail` varchar(20) NOT NULL,
  `direccion` varchar(20) default NULL,
  `telefono` varchar(30) default NULL,
  `id_localidad` smallint(6) default NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `id_roles` smallint(6) NOT NULL,
  `activo` smallint(6) NOT NULL,
  PRIMARY KEY  (`login`),
  KEY `id_roles` (`id_roles`),
  KEY `id_localidad` (`id_localidad`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borgesdb`.`usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
LOCK TABLES `usuario` WRITE;
INSERT INTO `borgesdb`.`usuario` VALUES  ('admin','admin','admin@borgesdb.com','Ayacucho 437 - 1D','156-45698',2,'Jorge','Borges',1,1),
 ('Kaiser','25012501','tarado@hotmail.com','Dean Funes 900','156-45868',3,'Elmer','Luso',2,1),
 ('Anonimo','0','0','0','0',7,'0','0',3,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


--
-- Definition of procedure `borgesdb`.`pr_deleteDetalle_ticket`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_deleteDetalle_ticket`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_deleteDetalle_ticket`(
  IN  pin_id_detalle          INTEGER,
      pin_id_ticket           INTEGER)
BEGIN 
        update detalle_ticket set detalle_ticket.activo=0
	where pin_id_ticket = detalle_ticket.id_ticket
	and pin_id_detalle = detalle_ticket.id_detalle;
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_deleteformaPago`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_deleteformaPago`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_deleteformaPago`(
 IN  pin_id_forma_pago              SMALLINT )
BEGIN 
        update forma_pago set forma_pago.activo=0
	where pin_id_forma_pago=forma_pago.id_forma_pago;
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_deleteLibro`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_deleteLibro`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_deleteLibro`(
 IN  pin_isbn              VARCHAR(34) )
BEGIN 
        update libro set libro.activo=0
	where pin_isbn=libro.isbn;
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_deleteLocalidad`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_deleteLocalidad`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_deleteLocalidad`(
IN  pin_id_localidad              SMALLINT)
BEGIN 
        update localidad set localidad.activo=0
	where pin_id_localidad=localidad.id_localidad;
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_deletepagina`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_deletepagina`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_deletepagina`(
IN  pin_id_pagina              SMALLINT)
BEGIN 
        update pagina set pagina.activo=0
	where pin_id_pagina=pagina.id_pagina;
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_deletepais`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_deletepais`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_deletepais`(
  IN  pin_id_pais              SMALLINT )
BEGIN 
        update pais set pais.activo=0
	where pin_id_pais=pais.id_pais;
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_deleteregion`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_deleteregion`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_deleteregion`(
IN  pin_id_region              SMALLINT)
BEGIN 
        update region set region.activo=0
	where pin_id_region=region.id_region;
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_deleteroles`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_deleteroles`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_deleteroles`(
IN  pin_id_roles              SMALLINT)
BEGIN 
        update roles set roles.activo=0
	where pin_id_roles=roles.id_roles;
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_deleteroles_x_pagina`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_deleteroles_x_pagina`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_deleteroles_x_pagina`(
IN  pin_id_roles              SMALLINT,
    pin_id_pagina             SMALLINT)
BEGIN 
        update roles_x_pagina set roles_x_pagina.activo=0
	where pin_id_roles=roles_x_pagina.id_roles
	and pin_id_pagina=roles_x_pagina.id_pagina;
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_deleteservicio`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_deleteservicio`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_deleteservicio`(
IN  pin_id_servicio              SMALLINT)
BEGIN 
        update servicio set servicio.activo=0
	where pin_id_servicio=servicio.id_servicio;
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_deleteticket`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_deleteticket`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_deleteticket`(
IN  pin_id_ticket              INTEGER)
BEGIN 
        update ticket set ticket.activo=0
	where pin_id_ticket=ticket.id_ticket;
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_deleteusuario`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_deleteusuario`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_deleteusuario`(
IN  pin_login              VARCHAR(100))
BEGIN 
        update usuario set usuario.activo=0
	where pin_login=usuario.login;
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_insertDetalle_ticket`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_insertDetalle_ticket`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_insertDetalle_ticket`(
  IN  pin_id_ticket            INTEGER,
         pin_cantidad            SMALLINT,
        pin_isbn                     VARCHAR(30),
        pin_id_servicio         SMALLINT)
BEGIN 
        INSERT INTO detalle_ticket(id_ticket,cantidad,isbn,id_servicio,activo)
        VALUES (pin_id_ticket, pin_cantidad,pin_isbn,pin_id_servicio,1);
 END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_insertforma_pago`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_insertforma_pago`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_insertforma_pago`(
  IN  pin_forma_pago             VARCHAR(100),
        pin_descuento                FLOAT(5,5))
BEGIN 
        INSERT INTO forma_pago(forma_pago, descuento, activo)
        VALUES (pin_forma_pago, pin_descuento , 1);
 END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_insertLibro`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_insertLibro`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_insertLibro`(
IN  pin_isbn              VARCHAR(30),
	pin_titulo         VARCHAR(100),
	pin_autor          VARCHAR(100),
	pin_precio          FLOAT(5,2),
	pin_editorial       VARCHAR(100))
BEGIN 
        INSERT INTO libro(isbn,titulo,autor,precio,editorial,activo)
        VALUES (pin_isbn, pin_titulo,pin_autor,pin_precio,pin_editorial,1);
END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_insertlocalidad`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_insertlocalidad`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_insertlocalidad`(
  IN  pin_localidad         VARCHAR(24),
        pin_id_region        SMALLINT)
BEGIN 
        INSERT INTO localidad(localidad, id_region, activo)
        VALUES (pin_localidad, pin_id_region , 1);
 END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_insertpagina`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_insertpagina`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_insertpagina`(
  IN  pin_pagina            VARCHAR(100))
BEGIN 
        INSERT INTO pagina(pagina, activo)
        VALUES (pin_pagina, 1);
 END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_insertpais`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_insertpais`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_insertpais`(
  IN  pin_pais              VARCHAR(24) )
BEGIN 
        INSERT INTO pais(pais, activo)
        VALUES (pin_pais, 1);
 END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_insertregion`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_insertregion`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_insertregion`(
  IN  pin_region              VARCHAR(24),
         pin_id_pais            SMALLINT)
BEGIN 
        INSERT INTO region(region,id_pais, activo)
        VALUES (pin_region,pin_id_pais, 1);
 END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_insertRoles`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_insertRoles`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_insertRoles`(
  IN  pin_rol        VARCHAR(24))
BEGIN 
        INSERT INTO roles(roles, activo)
        VALUES (pin_rol , 1);
 END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_insertroles_x_pagina`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_insertroles_x_pagina`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_insertroles_x_pagina`(
  IN  pin_id_roles            SMALLINT,
        pin_id_pagina            SMALLINT)
BEGIN 
        INSERT INTO roles_x_pagina(id_roles, id_pagina,activo)
        VALUES (pin_id_roles,pin_id_pagina, 1);
 END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_insertservicio`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_insertservicio`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_insertservicio`(
  IN  pin_servicio              VARCHAR(100),
         pin_precio                  FLOAT(5,2))
BEGIN 
        INSERT INTO servicio(servicio,precio, activo)
        VALUES (pin_servicio,pin_precio, 1);
 END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_insertTicket`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_insertTicket`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_insertTicket`(
  IN    pin_login               VARCHAR(100),
          pin_total_precio FLOAT(5,2),
         pin_id_forma_pago        SMALLINT)
BEGIN 
        INSERT INTO ticket(fecha,login,total_precio,id_forma_pago,activo)
        VALUES (CurDate(),pin_login,pin_Total_Precio,pin_id_forma_pago, 1);
 END $$

DELIMITER ;

--
-- Definition of procedure `borgesdb`.`pr_insertusuario`
--

DROP PROCEDURE IF EXISTS `borgesdb`.`pr_insertusuario`;

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE  `borgesdb`.`pr_insertusuario`(
  IN  pin_login              VARCHAR(100),
        pin_pass            VARCHAR(20),
        pin_mail             VARCHAR(20),
        pin_direccion           VARCHAR(20),
        pin_telefono                 VARCHAR(30),
       pin_id_localidad         SMALLINT,
	pin_nombre                VARCHAR(30),
      pin_apellido                  VARCHAR(30),
 	pin_id_roles                SMALLINT)
BEGIN 
        INSERT INTO usuario(login,pass,mail,direccion,telefono,id_localidad,nombre, apellido,id_roles,activo)
        VALUES (pin_login, pin_pass,pin_mail,pin_direccion,pin_telefono, pin_id_localidad, pin_nombre,pin_apellido, pin_id_roles , 1);
 END $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
