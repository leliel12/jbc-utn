-- =============================================================================
--
--    U N I V E R S I D A D   T E C N O L Ó G I C A   N A C I O N A L
--    F A C U L T A D   R E G I O N A L   C Ó R D O B A
--
--    D I S E Ñ O   D E   L E N G U A J E S   D E   C O N S U L T A   ( D L C )
--    P R Á C T I C O   T U T O R :   H O M E B A N K I N G
--
--    E S T R U C T U R A   B B D D
--    A U T O R :    S c a r a f i a
--
-- =============================================================================

-- =============================================================================
--    U S U A R I O S
-- =============================================================================
CREATE USER 'webApp' IDENTIFIED BY 'webApp';

-- =============================================================================
--    D A T A B A S E
-- =============================================================================
DROP DATABASE IF EXISTS bargdb;
CREATE DATABASE bargdb;
USE bargdb;

-- =============================================================================
--    T A B L A S ,   Í N D I C E S   Y   S E Q U E N C I A S
-- =============================================================================
-- =============================================================================
-- NACIONALIDADES
-- =============================================================================
CREATE TABLE nacionalidad (
  idnacionalidad        SMALLINT                        NOT NULL AUTO_INCREMENT,
  cgonacionalidad       VARCHAR(8)                      NOT NULL,
  nacionalidad          VARCHAR(24)                     NOT NULL,
  PRIMARY KEY (idnacionalidad),
  UNIQUE (cgonacionalidad),
  UNIQUE (nacionalidad),
  CHECK (cgonacionalidad <> '' AND nacionalidad <> '')
);
GRANT ALL ON nacionalidad TO 'webApp'@'%';

-- =============================================================================
-- PERSONAS
-- =============================================================================
CREATE TABLE persona (
  idpersona             INTEGER                         NOT NULL AUTO_INCREMENT,
  tipodoc               VARCHAR(8)    DEFAULT 'DNI'     NOT NULL,
  nrodoc                VARCHAR(16)                     NOT NULL,
  apellido              VARCHAR(32)                     NOT NULL,
  nombre                VARCHAR(32)                     NOT NULL,
  sexo                  CHAR(1)                         NOT NULL,
  fecnac                DATE                            NOT NULL,
  idnacionalidad        SMALLINT                        NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idpersona),
  FOREIGN KEY (idnacionalidad)
    REFERENCES nacionalidad(idnacionalidad),
  CHECK (tipodoc <> '' AND nrodoc <> '' AND apellido <> '' AND nombre <> ''),
  CHECK (sexo IN ('M', 'F', 'N'))
);
GRANT ALL ON persona TO 'webApp'@'%';
CREATE INDEX persona_documento ON
  persona(tipodoc, nrodoc);
CREATE INDEX persona_apenom ON
  persona(apellido, nombre);

CREATE TABLE personadireccion (
  idpersona             INTEGER                         NOT NULL,
  orden                 INTEGER                         NOT NULL,
  tipo                  CHAR(1)       DEFAULT 'P'       NOT NULL,
  direccioncp           VARCHAR(12)                     NOT NULL,
  direccionlocalidad    VARCHAR(40)                     NOT NULL,
  direccioncalle        VARCHAR(40)                     NOT NULL,
  direccionnro          VARCHAR(8)                      NOT NULL,
  direccionpuerta       VARCHAR(8),
  observaciones         VARCHAR(64),
  PRIMARY KEY (idpersona, orden),
  FOREIGN KEY (idpersona)
    REFERENCES persona(idpersona),
  CHECK (orden >= 0),
  CHECK (tipo IN ('P', 'L')),                           -- Particular, laboral
  CHECK (direccioncp <> '' AND direccionlocalidad <> ''),
  CHECK (direccioncalle <> '' AND direccionnro <> '')
);
GRANT ALL ON personadireccion TO 'webApp'@'%';

CREATE TABLE personatelefono (
  idpersona             INTEGER                         NOT NULL,
  orden                 INTEGER                         NOT NULL,
  tipo                  CHAR(1)       DEFAULT 'P'       NOT NULL,
  telefono              VARCHAR(24)                     NOT NULL,
  observaciones         VARCHAR(64),
  PRIMARY KEY (idpersona, orden),
  FOREIGN KEY (idpersona)
    REFERENCES persona(idpersona),
  CHECK (orden >= 0),
  CHECK (tipo IN ('C', 'T', 'M', 'O')),                 -- Casa, trabajo, celular (móvil), otros
  CHECK (telefono <> '')
);
GRANT ALL ON personatelefono TO 'webApp'@'%';

CREATE TABLE personaemail (
  idpersona             INTEGER                         NOT NULL,
  orden                 INTEGER                         NOT NULL,
  tipo                  CHAR(1)       DEFAULT 'P'       NOT NULL,
  email                 VARCHAR(40),
  observaciones         VARCHAR(64),
  PRIMARY KEY (idpersona, orden),
  FOREIGN KEY (idpersona)
    REFERENCES persona(idpersona),
  CHECK (orden >= 0),
  CHECK (tipo IN ('P', 'L', 'A', 'O')),                 -- Personal, laboral, académico, otros
  CHECK (email <> '')
);
GRANT ALL ON personaemail TO 'webApp'@'%';

-- =============================================================================
-- PERSONAL
-- =============================================================================
CREATE TABLE personal (
  idpersonal            INTEGER                         NOT NULL AUTO_INCREMENT,
  legajo                VARCHAR(24)                     NOT NULL,
  idpersona             INTEGER                         NOT NULL,
  fechaalta             DATETIME                        NOT NULL,
  fechabaja             DATETIME,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idpersonal),
  UNIQUE (legajo),
  FOREIGN KEY (idpersona)
    REFERENCES persona(idpersona),
  CHECK (legajo <> '')
);
GRANT ALL ON personal TO 'webApp'@'%';

-- =============================================================================
-- USUARIOS Y PRIVILEGIOS
-- =============================================================================
CREATE TABLE usuario (
  idusuario             INTEGER                         NOT NULL AUTO_INCREMENT,
  usr                   VARCHAR(32)                     NOT NULL,
  passwd                VARCHAR(32)                     NOT NULL,
  idpersona             INTEGER                         NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idusuario),
  UNIQUE (usr),
  FOREIGN KEY (idpersona)
    REFERENCES persona(idpersona),
  CHECK (usr <> '' AND passwd <> '')
);
GRANT ALL ON usuario TO 'webApp'@'%';

CREATE TABLE rolsistema (
  idrolsistema          INTEGER                         NOT NULL AUTO_INCREMENT,
  cgorolsistema         VARCHAR(16)                     NOT NULL,
  descripcion           VARCHAR(64)                     NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idrolsistema),
  UNIQUE (cgorolsistema),
  CHECK (cgorolsistema <> '' AND descripcion <> '')
);
GRANT ALL ON rolsistema TO 'webApp'@'%';

CREATE TABLE personarolsistema (
  idpersona             INTEGER                         NOT NULL,
  idrolsistema          INTEGER                         NOT NULL,
  suspendido            CHAR(1)       DEFAULT 'N'       NOT NULL,
  observaciones         VARCHAR(64),
  PRIMARY KEY (idpersona, idrolsistema),
  FOREIGN KEY (idpersona)
    REFERENCES persona(idpersona),
  FOREIGN KEY (idrolsistema)
    REFERENCES rolsistema(idrolsistema),
  CHECK (suspendido IN ('S', 'N'))
);
GRANT ALL ON personarolsistema TO 'webApp'@'%';

CREATE TABLE privilegio (
  idprivilegio          INTEGER                         NOT NULL AUTO_INCREMENT,
  cgoprivilegio         VARCHAR(32)                     NOT NULL,
  descripcion           VARCHAR(64)                     NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idprivilegio),
  UNIQUE (cgoprivilegio),
  CHECK (cgoprivilegio <> '' AND descripcion <> '')
);
GRANT ALL ON privilegio TO 'webApp'@'%';
CREATE INDEX privilegio_descripcion ON
  privilegio(descripcion);

CREATE TABLE rolsistemaprivilegio (
  idrolsistema          INTEGER                         NOT NULL,
  idprivilegio          INTEGER                         NOT NULL,
  permit                CHAR(1)       DEFAULT 'S'       NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idrolsistema, idprivilegio),
  FOREIGN KEY (idrolsistema)
    REFERENCES rolsistema(idrolsistema),
  FOREIGN KEY (idprivilegio)
    REFERENCES privilegio(idprivilegio),
  CHECK (permit IN ('S', 'N'))
);
GRANT ALL ON rolsistemaprivilegio TO 'webApp'@'%';

CREATE TABLE personaprivilegio (
  idpersona             INTEGER                         NOT NULL,
  idprivilegio          INTEGER                         NOT NULL,
  permit                CHAR(1)       DEFAULT 'S'       NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idpersona, idprivilegio),
  FOREIGN KEY (idpersona)
    REFERENCES persona(idpersona),
  FOREIGN KEY (idprivilegio)
    REFERENCES privilegio(idprivilegio),
  CHECK (permit IN ('S', 'N'))
);
GRANT ALL ON personaprivilegio TO 'webApp'@'%';

-- =============================================================================
-- SUCURSALES
-- =============================================================================
CREATE TABLE sucursal (
  idsucursal            INTEGER                         NOT NULL AUTO_INCREMENT,
  cgosucursal           VARCHAR(8)                      NOT NULL,
  nombresucursal        VARCHAR(40)                     NOT NULL,
  dirsucursal           VARCHAR(64)                     NOT NULL,
  telsucursal           VARCHAR(32)                     NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idsucursal),
  UNIQUE (cgosucursal),
  CHECK (
    cgosucursal <> '' AND nombresucursal <> ''
    AND dirsucursal <> '' AND telsucursal <> ''
  )
);
GRANT ALL ON sucursal TO 'webApp'@'%';

-- =============================================================================
-- CUENTAS
-- =============================================================================
CREATE TABLE tipocuenta (
  idtipocta             INTEGER                         NOT NULL AUTO_INCREMENT,
  cgotipocta            VARCHAR(8)                      NOT NULL,
  descripcion           VARCHAR(64)                     NOT NULL,
  PRIMARY KEY (idtipocta),
  UNIQUE (cgotipocta),
  CHECK (descripcion <> '')
);
GRANT ALL ON tipocuenta TO 'webApp'@'%';

CREATE TABLE cuenta (
  idcta                 INTEGER                         NOT NULL AUTO_INCREMENT,
  cgocta                VARCHAR(8)                      NOT NULL,
  cbu                   CHAR(22)                        NOT NULL,
  idtipocta             INTEGER                         NOT NULL,
  idsucursal            INTEGER                         NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idcta),
  UNIQUE (cgocta),
  UNIQUE (cbu),
  CHECK (cgocta <> '' AND cbu <> '')
);
GRANT ALL ON cuenta TO 'webApp'@'%';

-- =============================================================================
--    V I S T A S
-- =============================================================================
-- =============================================================================
-- PERSONAL
-- =============================================================================
CREATE OR REPLACE VIEW v_personal AS
  SELECT pl.idpersonal, pl.legajo, p.*
  FROM personal pl
  JOIN persona p ON pl.idpersona = p.idpersona
;
GRANT ALL ON v_personal TO 'webApp'@'%';

-- =============================================================================
-- USUARIOS
-- =============================================================================
CREATE OR REPLACE VIEW v_usuario AS
  SELECT u.idusuario, u.usr, u.passwd, u.observaciones AS observacionesusuario,
         p.idpersona, p.tipodoc, p.nrodoc, p.apellido, p.nombre,
         p.observaciones AS observacionespersona
  FROM usuario u
  JOIN persona p ON u.idpersona = p.idpersona
;
GRANT ALL ON v_usuario TO 'webApp'@'%';

CREATE OR REPLACE VIEW v_usuarioprivilegio AS
  SELECT DISTINCT u.*, 'ROLSISTEMA::'||rs.cgorolsistema AS heredadode,
                  p.idprivilegio, p.cgoprivilegio, p.descripcion,
                  rsp.permit, p.observaciones AS observacionesprivilegio
  FROM v_usuario u
  JOIN personarolsistema prs ON u.idpersona = prs.idpersona
  JOIN rolsistema rs ON prs.idrolsistema = rs.idrolsistema
  JOIN rolsistemaprivilegio rsp ON rs.idrolsistema = rsp.idrolsistema
  JOIN privilegio p ON rsp.idprivilegio = p.idprivilegio
UNION
  SELECT DISTINCT u.*, 'PROPIO' AS heredadode,
                  p.idprivilegio, p.cgoprivilegio, p.descripcion,
                  pp.permit, p.observaciones AS observacionesprivilegio
  FROM v_usuario u
  JOIN personaprivilegio pp ON u.idpersona = pp.idpersona
  JOIN privilegio p ON pp.idprivilegio = p.idprivilegio
;
GRANT ALL ON v_usuarioprivilegio TO 'webApp'@'%';

CREATE OR REPLACE VIEW v_personadirtel AS
  SELECT p.idpersona, p.tipodoc, p.nrodoc, p.apellido, p.nombre,
         p.sexo, p.fecnac, pd.tipo AS tipodir, pd.direccioncp,
         pd.direccionlocalidad, pd.direccioncalle, pd.direccionnro,
         pd.direccionpuerta, pt.tipo AS tipotel, pt.telefono, p.observaciones
  FROM persona p
  LEFT JOIN personadireccion pd ON p.idpersona = pd.idpersona
  LEFT JOIN personatelefono pt ON p.idpersona = pt.idpersona
  WHERE pd.orden IN (0, NULL)
  AND pt.orden IN (0, NULL)
;
GRANT ALL ON v_personadirtel TO 'webApp'@'%';

-- =============================================================================

