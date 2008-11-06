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
--    L E N G U A J E S   M A N E J A D O S   P O R   E L   M O T O R
-- =============================================================================
--CREATE PROCEDURAL LANGUAGE "plpgsql";

-- =============================================================================
--    U S U A R I O S   Y   G R U P O S   ( D E L   M O T O R )
-- =============================================================================
--CREATE ROLE "grAppClients";
--CREATE ROLE "webApp"
--  LOGIN
--  IN ROLE "grAppClients"
--  INHERIT
--  PASSWORD 'webApp';

-- =============================================================================
--    T A B L A S ,   Í N D I C E S   Y   S E Q U E N C I A S
-- =============================================================================
-- =============================================================================
-- NACIONALIDADES
-- =============================================================================
DROP SEQUENCE IF EXISTS sq_nacionalidad CASCADE;
CREATE SEQUENCE sq_nacionalidad;
GRANT ALL ON sq_nacionalidad TO "grAppClients";
DROP TABLE IF EXISTS nacionalidad CASCADE;
CREATE TABLE nacionalidad (
  idnacionalidad        SMALLINT                        NOT NULL,
  cgonacionalidad       VARCHAR(8)                      NOT NULL,
  nacionalidad          VARCHAR(24)                     NOT NULL,
  PRIMARY KEY (idnacionalidad),
  UNIQUE (cgonacionalidad),
  UNIQUE (nacionalidad),
  CHECK (cgonacionalidad <> '' AND nacionalidad <> '')
);
GRANT ALL ON nacionalidad TO "grAppClients";

-- =============================================================================
-- PERSONAS
-- =============================================================================
DROP SEQUENCE IF EXISTS sq_persona CASCADE;
CREATE SEQUENCE sq_persona;
GRANT ALL ON sq_persona TO "grAppClients";
DROP TABLE IF EXISTS persona CASCADE;
CREATE TABLE persona (
  idpersona             INTEGER                         NOT NULL,
  tipodoc               VARCHAR(8)    DEFAULT 'DNI'     NOT NULL,
  nrodoc                VARCHAR(16)                     NOT NULL,
  apellido              VARCHAR(40)                     NOT NULL,
  nombre                VARCHAR(40)                     NOT NULL,
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
GRANT ALL ON persona TO "grAppClients";
CREATE INDEX persona_documento ON
  persona(tipodoc, nrodoc);
CREATE INDEX persona_apenom ON
  persona(apellido, nombre);

DROP TABLE IF EXISTS personadireccion CASCADE;
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
GRANT ALL ON personadireccion TO "grAppClients";

DROP TABLE IF EXISTS personatelefono CASCADE;
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
GRANT ALL ON personatelefono TO "grAppClients";

DROP TABLE IF EXISTS personaemail CASCADE;
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
GRANT ALL ON personaemail TO "grAppClients";

-- =============================================================================
-- PERSONAL
-- =============================================================================
DROP SEQUENCE IF EXISTS sq_personal CASCADE;
CREATE SEQUENCE sq_personal;
GRANT ALL ON sq_personal TO "grAppClients";
DROP TABLE IF EXISTS personal CASCADE;
CREATE TABLE personal (
  idpersonal            INTEGER                         NOT NULL,
  legajo                VARCHAR(24)                     NOT NULL,
  idpersona             INTEGER                         NOT NULL,
  fechaalta             TIMESTAMP                       NOT NULL,
  fechabaja             TIMESTAMP,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idpersonal),
  UNIQUE (legajo),
  FOREIGN KEY (idpersona)
    REFERENCES persona(idpersona),
  CHECK (legajo <> '')
);
GRANT ALL ON personal TO "grAppClients";

-- =============================================================================
-- USUARIOS Y PRIVILEGIOS
-- =============================================================================
DROP SEQUENCE IF EXISTS sq_usuario CASCADE;
CREATE SEQUENCE sq_usuario;
GRANT ALL ON sq_usuario TO "grAppClients";
DROP TABLE IF EXISTS usuario CASCADE;
CREATE TABLE usuario (
  idusuario             INTEGER                         NOT NULL,
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
GRANT ALL ON usuario TO "grAppClients";

DROP SEQUENCE IF EXISTS sq_rolsistema CASCADE;
CREATE SEQUENCE sq_rolsistema;
GRANT ALL ON sq_rolsistema TO "grAppClients";
DROP TABLE IF EXISTS rolsistema CASCADE;
CREATE TABLE rolsistema (
  idrolsistema          INTEGER                         NOT NULL,
  cgorolsistema         VARCHAR(16)                     NOT NULL,
  descripcion           VARCHAR(64)                     NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idrolsistema),
  UNIQUE (cgorolsistema),
  CHECK (cgorolsistema <> '' AND descripcion <> '')
);
GRANT ALL ON rolsistema TO "grAppClients";

DROP TABLE IF EXISTS personarolsistema CASCADE;
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
GRANT ALL ON personarolsistema TO "grAppClients";

DROP SEQUENCE IF EXISTS sq_privilegio CASCADE;
CREATE SEQUENCE sq_privilegio;
GRANT ALL ON sq_privilegio TO "grAppClients";
DROP TABLE IF EXISTS privilegio CASCADE;
CREATE TABLE privilegio (
  idprivilegio          INTEGER                         NOT NULL,
  cgoprivilegio         VARCHAR(32)                     NOT NULL,
  descripcion           VARCHAR(64)                     NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idprivilegio),
  UNIQUE (cgoprivilegio),
  CHECK (cgoprivilegio <> '' AND descripcion <> '')
);
GRANT ALL ON privilegio TO "grAppClients";
CREATE INDEX privilegio_descripcion ON
  privilegio(descripcion);

DROP TABLE IF EXISTS rolsistemaprivilegio CASCADE;
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
GRANT ALL ON rolsistemaprivilegio TO "grAppClients";

DROP TABLE IF EXISTS personaprivilegio CASCADE;
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
GRANT ALL ON personaprivilegio TO "grAppClients";

-- =============================================================================
-- SUCURSALES
-- =============================================================================
DROP SEQUENCE IF EXISTS sq_sucursal CASCADE;
CREATE SEQUENCE sq_sucursal;
GRANT ALL ON sq_sucursal TO "grAppClients";
DROP TABLE IF EXISTS sucursal CASCADE;
CREATE TABLE sucursal (
  idsucursal            INTEGER                         NOT NULL,
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
GRANT ALL ON sucursal TO "grAppClients";

-- =============================================================================
-- CLIENTES
-- =============================================================================
DROP SEQUENCE IF EXISTS sq_cliente CASCADE;
CREATE SEQUENCE sq_cliente;
GRANT ALL ON sq_cliente TO "grAppClients";
DROP TABLE IF EXISTS cliente CASCADE;
CREATE TABLE cliente (
  idcliente             INTEGER                         NOT NULL,
  cgocliente            VARCHAR(8)                      NOT NULL,
  nomcliente            VARCHAR(64)                     NOT NULL,
  tipodoc               VARCHAR(8)    DEFAULT 'CUIT'    NOT NULL,
  nrodoc                VARCHAR(16)                     NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idcliente),
  UNIQUE (cgocliente),
  UNIQUE (tipodoc, nrodoc),
  CHECK (cgocliente <> '' AND nomcliente <> '')
);
GRANT ALL ON cliente TO "grAppClients";

DROP TABLE IF EXISTS clientedireccion CASCADE;
CREATE TABLE clientedireccion (
  idcliente             INTEGER                         NOT NULL,
  orden                 INTEGER                         NOT NULL,
  direccioncp           VARCHAR(12)                     NOT NULL,
  direccionlocalidad    VARCHAR(40)                     NOT NULL,
  direccioncalle        VARCHAR(40)                     NOT NULL,
  direccionnro          VARCHAR(8)                      NOT NULL,
  direccionpuerta       VARCHAR(8),
  observaciones         VARCHAR(64),
  PRIMARY KEY (idcliente, orden),
  FOREIGN KEY (idcliente)
    REFERENCES cliente(idcliente),
  CHECK (orden >= 0),
  CHECK (direccioncp <> '' AND direccionlocalidad <> ''),
  CHECK (direccioncalle <> '' AND direccionnro <> '')
);
GRANT ALL ON clientedireccion TO "grAppClients";

DROP TABLE IF EXISTS clientetelefono CASCADE;
CREATE TABLE clientetelefono (
  idcliente             INTEGER                         NOT NULL,
  orden                 INTEGER                         NOT NULL,
  telefono              VARCHAR(24)                     NOT NULL,
  observaciones         VARCHAR(64),
  PRIMARY KEY (idcliente, orden),
  FOREIGN KEY (idcliente)
    REFERENCES cliente(idcliente),
  CHECK (orden >= 0),
  CHECK (telefono <> '')
);
GRANT ALL ON clientetelefono TO "grAppClients";

DROP TABLE IF EXISTS clienteemail CASCADE;
CREATE TABLE clienteemail (
  idcliente             INTEGER                         NOT NULL,
  orden                 INTEGER                         NOT NULL,
  email                 VARCHAR(40),
  observaciones         VARCHAR(64),
  PRIMARY KEY (idcliente, orden),
  FOREIGN KEY (idcliente)
    REFERENCES cliente(idcliente),
  CHECK (orden >= 0),
  CHECK (email <> '')
);
GRANT ALL ON clienteemail TO "grAppClients";

DROP TABLE IF EXISTS clientecontacto CASCADE;
CREATE TABLE clientecontacto (
  idcliente             INTEGER                         NOT NULL,
  idpersona             INTEGER                         NOT NULL,
  orden                 INTEGER                         NOT NULL,
  observaciones         VARCHAR(64),
  PRIMARY KEY (idcliente, idpersona),
  UNIQUE (idcliente, orden),
  FOREIGN KEY (idcliente)
    REFERENCES cliente(idcliente),
  FOREIGN KEY (idpersona)
    REFERENCES persona(idpersona),
  CHECK (orden >= 0)
);
GRANT ALL ON clientecontacto TO "grAppClients";

-- =============================================================================
-- CUENTAS
-- =============================================================================
DROP SEQUENCE IF EXISTS sq_tipocuenta CASCADE;
CREATE SEQUENCE sq_tipocuenta;
GRANT ALL ON sq_tipocuenta TO "grAppClients";
DROP TABLE IF EXISTS tipocuenta CASCADE;
CREATE TABLE tipocuenta (
  idtipocta             INTEGER                         NOT NULL,
  cgotipocta            VARCHAR(8)                      NOT NULL,
  descripcion           VARCHAR(64)                     NOT NULL,
  PRIMARY KEY (idtipocta),
  UNIQUE (cgotipocta),
  CHECK (descripcion <> '')
);
GRANT ALL ON tipocuenta TO "grAppClients";

DROP SEQUENCE IF EXISTS sq_cuenta CASCADE;
CREATE SEQUENCE sq_cuenta;
GRANT ALL ON sq_cuenta TO "grAppClients";
DROP TABLE IF EXISTS cuenta CASCADE;
CREATE TABLE cuenta (
  idcta                 INTEGER                         NOT NULL,
  cgocta                VARCHAR(8)                      NOT NULL,
  cbu                   CHAR(22)                        NOT NULL,
  idtipocta             INTEGER                         NOT NULL,
  idsucursal            INTEGER                         NOT NULL,
  idcliente             INTEGER                         NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idcta),
  UNIQUE (cgocta),
  UNIQUE (cbu),
  FOREIGN KEY (idtipocta)
    REFERENCES tipocuenta(idtipocta),
  FOREIGN KEY (idsucursal)
    REFERENCES sucursal(idsucursal),
  FOREIGN KEY (idcliente)
    REFERENCES cliente(idcliente),
  CHECK (cgocta <> '' AND cbu <> '')
);
GRANT ALL ON cuenta TO "grAppClients";

DROP TABLE IF EXISTS cuentacliente CASCADE;
CREATE TABLE cuentacliente (
  idcta                 INTEGER                         NOT NULL,
  idcliente             INTEGER                         NOT NULL,
  tipo                  CHAR(2)       DEFAULT 'CT'      NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idcta, idcliente),
  FOREIGN KEY (idcta)
    REFERENCES cuenta(idcta),
  FOREIGN KEY (idcliente)
    REFERENCES cliente(idcliente),
  CHECK (tipo IN ('CT', 'AU'))
);
GRANT ALL ON cuentacliente TO "grAppClients";

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
GRANT ALL ON v_personal TO "grAppClients";

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
GRANT ALL ON v_usuario TO "grAppClients";

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
GRANT ALL ON v_usuarioprivilegio TO "grAppClients";

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
GRANT ALL ON v_personadirtel TO "grAppClients";

-- =============================================================================
COMMIT;
-- =============================================================================

