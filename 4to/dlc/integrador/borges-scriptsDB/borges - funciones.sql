-- =============================================================================
--
--    U N I V E R S I D A D   T E C N O L Ó G I C A   N A C I O N A L
--    F A C U L T A D   R E G I O N A L   C Ó R D O B A
--
--    D I S E Ñ O   D E   L E N G U A J E S   D E   C O N S U L T A   ( D L C )
--    P R Á C T I C O   T U T O R :   B O R G E S
--
--    E S T R U C T U R A   B B D D
--    A U T O R :    C A B R A L - 40842
--
--    CHAR CODE UTF-8 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
--
-- =============================================================================
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
