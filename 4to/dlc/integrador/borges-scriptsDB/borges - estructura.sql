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

-- =============================================================================
--    U S U A R I O S
-- =============================================================================-- CREATE USER 'borgesAdmin' IDENTIFIED BY 'borgesAdmin';

-- =============================================================================
--    D A T A B A S E
-- =============================================================================
DROP DATABASE IF EXISTS borgesdb;
CREATE DATABASE borgesdb;
USE borgesdb;

-- =============================================================================
--    T A B L A S ,   Í N D I C E S   Y   S E Q U E N C I A S
-- =============================================================================
-- =============================================================================
-- PAIS
-- =============================================================================
CREATE TABLE pais (
	id_pais        SMALLINT                        NOT NULL AUTO_INCREMENT,
	pais          VARCHAR(24)                      NOT NULL,
	activo		SMALLINT			NOT NULL,
	PRIMARY KEY (id_pais),
	CHECK (pais <> '')
);
GRANT ALL ON pais TO 'borgesAdmin'@'%';
-- =============================================================================
-- REGION
-- =============================================================================
CREATE TABLE region (
	id_region        SMALLINT                        NOT NULL AUTO_INCREMENT,
	region          VARCHAR(24)                      NOT NULL,
	id_pais         SMALLINT                         NOT NULL,
	activo		SMALLINT			NOT NULL,
	PRIMARY KEY (id_region),
	FOREIGN KEY (id_pais) REFERENCES pais(id_pais),
	CHECK (region <> '')
);
GRANT ALL ON region TO 'borgesAdmin'@'%';
-- =============================================================================
-- LOCALIDAD
-- =============================================================================
CREATE TABLE localidad (
	id_localidad        SMALLINT                        NOT NULL AUTO_INCREMENT,
	localidad          VARCHAR(24)                      NOT NULL,
	id_region          SMALLINT                         NOT NULL,
	activo		SMALLINT			NOT NULL,
	PRIMARY KEY (id_localidad),
	FOREIGN KEY (id_region) REFERENCES region(id_region),
	CHECK (localidad <> '')
);
GRANT ALL ON localidad TO 'borgesAdmin'@'%';
-- =============================================================================
-- PAGINAS
-- =============================================================================
CREATE TABLE pagina(
	id_pagina 	SMALLINT	NOT NULL AUTO_INCREMENT,
	pagina		VARCHAR(100)	NOT NULL,
	activo		SMALLINT			NOT NULL,
	PRIMARY KEY (id_pagina),
	CHECK (pagina <> '')
);
GRANT ALL ON pagina TO 'borgesAdmin'@'%';
-- =============================================================================
-- ROLES
-- =============================================================================
CREATE TABLE roles(
	id_roles 	SMALLINT	NOT NULL AUTO_INCREMENT,
	roles		VARCHAR(30)	NOT NULL,
	activo		SMALLINT			NOT NULL,
	PRIMARY KEY (id_roles),
	CHECK (roles <> '')
);
GRANT ALL ON roles TO 'borgesAdmin'@'%';
-- =============================================================================
-- ROLES X PAGINA
-- =============================================================================
CREATE TABLE roles_x_pagina(
	id_roles 	SMALLINT	NOT NULL,
	id_pagina 	SMALLINT	NOT NULL,
	activo		SMALLINT			NOT NULL,
	PRIMARY KEY (id_roles, id_pagina),
	FOREIGN KEY (id_roles) REFERENCES roles(id_roles),
	FOREIGN KEY (id_pagina) REFERENCES pagina(id_pagina)
);
GRANT ALL ON roles_x_pagina TO 'borgesAdmin'@'%';
-- =============================================================================
-- USUARIO
-- =============================================================================
CREATE TABLE usuario(
	login 	        VARCHAR(100)	NOT NULL,
	pass            VARCHAR(20)	NOT NULL, 
	mail    	VARCHAR(20)	NOT NULL,
	direccion       VARCHAR(20),
	telefono 	VARCHAR(30),
	id_localidad    SMALLINT,
	nombre 		VARCHAR(30)      NOT NULL,
	apellido 	VARCHAR(30)	 NOT NULL,
	id_roles          SMALLINT         NOT NULL,
	activo		SMALLINT			NOT NULL,
	PRIMARY KEY (login),
	FOREIGN KEY (id_roles) REFERENCES roles(id_roles),
	FOREIGN KEY (id_localidad) REFERENCES localidad(id_localidad)
);
GRANT ALL ON usuario TO 'borgesAdmin'@'%';
-- =============================================================================
-- LIBRO
-- =============================================================================
CREATE TABLE libro(
	isbn	 	VARCHAR(30)	NOT NULL, 
	titulo          VARCHAR(100)	NOT NULL,
	autor 		VARCHAR(100)	NOT NULL,
	precio          FLOAT(5,2)      NOT NULL,
	editorial       VARCHAR(100)	NOT NULL,
	activo		SMALLINT			NOT NULL,
	PRIMARY KEY (isbn));
GRANT ALL ON libro TO 'borgesAdmin'@'%';
-- =============================================================================
-- SERVICIO
-- =============================================================================
CREATE TABLE servicio(
	id_servicio 	SMALLINT	NOT NULL AUTO_INCREMENT,
	servicio          VARCHAR(100)	NOT NULL,
	precio          FLOAT(5,2)      NOT NULL,
	activo		SMALLINT			NOT NULL,
	PRIMARY KEY (id_servicio));
GRANT ALL ON servicio TO 'borgesAdmin'@'%';
-- =============================================================================
-- FORMA DE PAGO
-- =============================================================================
CREATE TABLE forma_pago(
	id_forma_pago 	SMALLINT	NOT NULL AUTO_INCREMENT,
	forma_pago          VARCHAR(100)	NOT NULL,
	descuento          FLOAT(5,5)      NOT NULL,
	activo		SMALLINT			NOT NULL,
	PRIMARY KEY (id_forma_pago));
GRANT ALL ON forma_pago TO 'borgesAdmin'@'%';
-- =============================================================================
-- TICKET
-- =============================================================================
CREATE TABLE ticket(	
	id_ticket 	INTEGER		NOT NULL AUTO_INCREMENT,
	fecha           DATE 		NOT NULL,
	login 		VARCHAR(100)	NOT NULL,
	total_precio    FLOAT(5,2)      NOT NULL,
	id_forma_pago 	SMALLINT	NOT NULL,
	activo		SMALLINT			NOT NULL,
	PRIMARY KEY (id_ticket),
	FOREIGN KEY (login) REFERENCES usuario(login),
	FOREIGN KEY (id_forma_pago) REFERENCES forma_pago(id_forma_pago));
GRANT ALL ON ticket TO 'borgesAdmin'@'%';
-- =============================================================================
-- DETALLE_TICKET
-- =============================================================================
CREATE TABLE detalle_ticket(
	id_detalle	INTEGER NOT NULL AUTO_INCREMENT,
	id_ticket 	INTEGER	NOT NULL,
	cantidad	SMALLINT NOT NULL,
	isbn		VARCHAR(30),
	id_servicio 	SMALLINT,
	activo		SMALLINT			NOT NULL,
	PRIMARY KEY (id_detalle, id_ticket),
	FOREIGN KEY (isbn) REFERENCES libro(isbn), 
	FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio), 
	FOREIGN KEY (id_ticket) REFERENCES ticket(id_ticket));
GRANT ALL ON detalle_ticket TO 'borgesAdmin'@'%';

