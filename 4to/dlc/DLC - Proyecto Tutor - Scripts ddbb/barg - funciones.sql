-- =============================================================================
--
--    U N I V E R S I D A D   T E C N O L Ó G I C A   N A C I O N A L
--    F A C U L T A D   R E G I O N A L   C Ó R D O B A
--
--    D I S E Ñ O   D E   L E N G U A J E S   D E   C O N S U L T A   ( D L C )
--    P R Á C T I C O   T U T O R :   H O M E B A N K I N G
--
--    F U N C I O N E S / P R O C E D I M I E N T O S   B B D D
--    A U T O R :    S c a r a f i a
--
-- =============================================================================

-- =============================================================================
-- NACIONALIDADES
-- =============================================================================
CREATE OR REPLACE FUNCTION fn_getidnacionalidad (
) RETURNS SMALLINT AS $$

  DECLARE
      var_idnacionalidad           INTEGER         := NULL;

  BEGIN
      var_idnacionalidad:= NEXTVAL('sq_nacionalidad');
      RETURN var_idnacionalidad;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_savenacionalidad (
  pin_idnacionalidad               SMALLINT,
  pin_cgonacionalidad              VARCHAR,
  pin_nacionalidad                 VARCHAR
) RETURNS SMALLINT AS $$

  DECLARE
      var_idnacionalidad           SMALLINT        := pin_idnacionalidad;
      var_cgonacionalidad          VARCHAR         := TRIM(pin_cgonacionalidad);
      var_nacionalidad             VARCHAR         := TRIM(pin_nacionalidad);

      var_step                     INTEGER         := 0;

      var_count                    INTEGER         := 0;

  BEGIN
      var_step:= 1; -- cuento nacionalidades
      SELECT COUNT(*)
        INTO var_count
        FROM nacionalidad n
        WHERE n.idnacionalidad = var_idnacionalidad;

      var_step:= 2; -- veo si existe
      IF (var_count > 0) THEN
        var_step:= 3; -- sí existe ==> update
        UPDATE nacionalidad n SET
          cgonacionalidad = var_cgonacionalidad,
          nacionalidad = var_nacionalidad
        WHERE n.idnacionalidad = var_idnacionalidad;
      ELSE
        var_step:= 3; -- no existe ==> insert
        IF (var_idnacionalidad IS NULL) THEN
          var_idnacionalidad := fn_getidnacionalidad();
        END IF;
        INSERT INTO nacionalidad(idnacionalidad, cgonacionalidad, nacionalidad)
          VALUES (var_idnacionalidad, var_cgonacionalidad, var_nacionalidad);
      END IF;

      RETURN var_idnacionalidad;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION pr_deletenacionalidad (
  pin_idnacionalidad               SMALLINT
) RETURNS VOID AS $$

  DECLARE
      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino la nacionalidad
      DELETE FROM nacionalidad n
        WHERE n.idnacionalidad = pin_idnacionalidad;

      RETURN;
  END;
$$ LANGUAGE plpgsql;

-- =============================================================================
-- PERSONAS
-- =============================================================================
CREATE OR REPLACE FUNCTION fn_getidpersona (
) RETURNS INTEGER AS $$

  DECLARE
      var_idpersona                INTEGER         := NULL;

  BEGIN
      var_idpersona:= NEXTVAL('sq_persona');
      RETURN var_idpersona;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_savepersona (
  pin_idpersona                    INTEGER,
  pin_tipodoc                      VARCHAR,
  pin_nrodoc                       VARCHAR,
  pin_apellido                     VARCHAR,
  pin_nombre                       VARCHAR,
  pin_sexo                         CHAR,
  pin_fecnac                       VARCHAR,
  pin_idnacionalidad               SMALLINT,
  pin_observaciones                VARCHAR
) RETURNS INTEGER AS $$

  DECLARE
      var_idpersona                INTEGER         := pin_idpersona;
      var_tipodoc                  VARCHAR         := TRIM(pin_tipodoc);
      var_nrodoc                   VARCHAR         := TRIM(pin_nrodoc);
      var_apellido                 VARCHAR         := TRIM(pin_apellido);
      var_nombre                   VARCHAR         := TRIM(pin_nombre);
      var_fecnac                   DATE            := NULL;
      var_observaciones            VARCHAR         := TRIM(pin_observaciones);

      var_step                     INTEGER         := 0;

      var_count                    INTEGER         := 0;

  BEGIN
      var_step:= 1; -- convierto fecha
      var_fecnac := TO_DATE(pin_fecnac, 'YYYYMMDD');

      var_step:= 2; -- cuento personas
      SELECT COUNT(*)
        INTO var_count
        FROM persona p
        WHERE p.idpersona = var_idpersona;

      var_step:= 3; -- veo si existe
      IF (var_count > 0) THEN
        var_step:= 4; -- sí existe ==> update
        UPDATE persona p SET
          tipodoc = var_tipodoc,
          nrodoc = var_nrodoc,
          apellido = var_apellido,
          nombre = var_nombre,
          sexo = pin_sexo,
          fecnac = var_fecnac,
          idnacionalidad = pin_idnacionalidad,
          observaciones = var_observaciones
        WHERE p.idpersona = var_idpersona;
      ELSE
        var_step:= 5; -- no existe ==> insert
        IF (var_idpersona IS NULL) THEN
          var_idpersona := fn_getidpersona();
        END IF;
        INSERT INTO persona(idpersona, tipodoc, nrodoc, apellido, nombre, sexo, fecnac, idnacionalidad, observaciones)
          VALUES (var_idpersona, var_tipodoc, var_nrodoc, var_apellido, var_nombre, pin_sexo, var_fecnac, pin_idnacionalidad, var_observaciones);
      END IF;

      RETURN var_idpersona;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION pr_deletepersona (
  pin_idpersona                    INTEGER
) RETURNS VOID AS $$

  DECLARE
      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino el alumno
      DELETE FROM persona p
        WHERE p.idpersona = pin_idpersona;

      RETURN;
  END;
$$ LANGUAGE plpgsql;

-- =============================================================================
-- DOMICILIOS PERSONALES
-- =============================================================================
CREATE OR REPLACE FUNCTION fn_savedireccionpersona (
  pin_idpersona                    INTEGER,
  pin_orden                        INTEGER,
  pin_tipo                         CHAR,
  pin_direccioncp                  VARCHAR,
  pin_direccionlocalidad           VARCHAR,
  pin_direccioncalle               VARCHAR,
  pin_direccionnro                 VARCHAR,
  pin_direccionpuerta              VARCHAR,
  pin_observaciones                VARCHAR
) RETURNS INTEGER AS $$

  DECLARE
      var_direccioncp              VARCHAR         := TRIM(pin_direccioncp);
      var_direccionlocalidad       VARCHAR         := TRIM(pin_direccionlocalidad);
      var_direccioncalle           VARCHAR         := TRIM(pin_direccioncalle);
      var_direccionnro             VARCHAR         := TRIM(pin_direccionnro);
      var_direccionpuerta          VARCHAR         := TRIM(pin_direccionpuerta);
      var_observaciones            VARCHAR         := TRIM(pin_observaciones);

      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino la dirección en la posisión "orden"
      DELETE FROM personadireccion pd
        WHERE pd.idpersona = pin_idpersona
        AND pd.orden = pin_orden;

      var_step:= 2; -- agrego la dirección en la posición "orden"
      INSERT INTO personadireccion(idpersona, orden, tipo, direccioncp, direccionlocalidad, direccioncalle, direccionnro, direccionpuerta, observaciones)
        VALUES (pin_idpersona, pin_orden, pin_tipo, var_direccioncp, var_direccionlocalidad, var_direccioncalle, var_direccionnro, var_direccionpuerta, var_observaciones);

      RETURN pin_idpersona;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION pr_deletedireccionpersona (
  pin_idpersona                    INTEGER,
  pin_orden                        INTEGER
) RETURNS VOID AS $$

  DECLARE
      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino la dirección en la posición "orden"
      DELETE FROM personadireccion pd
        WHERE pd.idpersona = pin_idpersona
        AND pd.orden = pin_orden;

      RETURN;
  END;
$$ LANGUAGE plpgsql;

-- =============================================================================
-- TELÉFONOS PERSONALES
-- =============================================================================
CREATE OR REPLACE FUNCTION fn_savetelefonopersona (
  pin_idpersona                    INTEGER,
  pin_orden                        INTEGER,
  pin_tipo                         CHAR,
  pin_telefono                     VARCHAR,
  pin_observaciones                VARCHAR
) RETURNS INTEGER AS $$

  DECLARE
      var_telefono                 VARCHAR         := TRIM(pin_telefono);
      var_observaciones            VARCHAR         := TRIM(pin_observaciones);

      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino el teléfono en la posisión "orden"
      DELETE FROM personatelefono pt
        WHERE pt.idpersona = pin_idpersona
        AND pt.orden = pin_orden;

      var_step:= 2; -- agrego el teléfono en la posición "orden"
      INSERT INTO personatelefono(idpersona, orden, tipo, telefono, observaciones)
        VALUES (pin_idpersona, pin_orden, pin_tipo, var_telefono, var_observaciones);

      RETURN pin_idpersona;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION pr_deletetelefonopersona (
  pin_idpersona                    INTEGER,
  pin_orden                        INTEGER
) RETURNS VOID AS $$

  DECLARE
      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino el teléfono en la posición "orden"
      DELETE FROM personatelefono pt
        WHERE pt.idpersona = pin_idpersona
        AND pt.orden = pin_orden;

      RETURN;
  END;
$$ LANGUAGE plpgsql;

-- =============================================================================
-- DIRECCIONES EMAIL PERSONALES
-- =============================================================================
CREATE OR REPLACE FUNCTION fn_saveemailpersona (
  pin_idpersona                    INTEGER,
  pin_orden                        INTEGER,
  pin_tipo                         CHAR,
  pin_email                        VARCHAR,
  pin_observaciones                VARCHAR
) RETURNS INTEGER AS $$

  DECLARE
      var_email                    VARCHAR         := TRIM(pin_email);
      var_observaciones            VARCHAR         := TRIM(pin_observaciones);

      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino el email en la posisión "orden"
      DELETE FROM personaemail pe
        WHERE pt.idpersona = pin_idpersona
        AND pe.orden = pin_orden;

      var_step:= 2; -- agrego el email en la posición "orden"
      INSERT INTO personaemail(idpersona, orden, tipo, email, observaciones)
        VALUES (pin_idpersona, pin_orden, pin_tipo, var_email, var_observaciones);

      RETURN pin_idpersona;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION pr_deleteemailpersona (
  pin_idpersona                    INTEGER,
  pin_orden                        INTEGER
) RETURNS VOID AS $$

  DECLARE
      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino el email en la posición "orden"
      DELETE FROM personaemail pe
        WHERE pe.idpersona = pin_idpersona
        AND pe.orden = pin_orden;

      RETURN;
  END;
$$ LANGUAGE plpgsql;

-- =============================================================================
-- PERSONAL
-- =============================================================================
CREATE OR REPLACE FUNCTION fn_getidpersonal (
) RETURNS INTEGER AS $$

  DECLARE
      var_idpersonal               INTEGER         := NULL;

  BEGIN
      var_idpersonal:= NEXTVAL('sq_personal');
      RETURN var_idpersonal;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_savepersonal (
  pin_idpersonal                   INTEGER,
  pin_legajo                       VARCHAR,
  pin_idpersona                    INTEGER,
  pin_fechaalta                    VARCHAR,
  pin_fechabaja                    VARCHAR,
  pin_observaciones                VARCHAR
) RETURNS INTEGER AS $$

  DECLARE
      var_idpersonal               INTEGER         := pin_idpersonal;
      var_legajo                   VARCHAR         := TRIM(pin_legajo);
      var_fechaalta                DATE            := NULL;
      var_fechabaja                DATE            := NULL;
      var_observaciones            VARCHAR         := TRIM(pin_observaciones);

      var_step                     INTEGER         := 0;

      var_count                    INTEGER         := 0;

  BEGIN
      var_step:= 1; -- convierto fechas
      var_fechaalta := TO_TIMESTAMP(pin_fechaalta, 'YYYYMMDDHHMISS');
      var_fechabaja := TO_TIMESTAMP(pin_fechabaja, 'YYYYMMDDHHMISS');

      var_step:= 2; -- cuento personal
      SELECT COUNT(*)
        INTO var_count
        FROM personal p
        WHERE p.idpersonal = var_idpersonal;

      var_step:= 3; -- veo si existe
      IF (var_count > 0) THEN
        var_step:= 4; -- sí existe ==> update
        UPDATE personal p SET
          legajo = var_legajo,
          idpersona = pin_idpersona,
          fechaalta = var_fechaalta,
          fechabaja = var_fechabaja,
          observaciones = var_observaciones
        WHERE p.idpersonal = var_idpersonal;
      ELSE
        var_step:= 5; -- no existe ==> insert
        IF (var_idpersonal IS NULL) THEN
          var_idpersonal := fn_getidpersonal();
        END IF;
        INSERT INTO personal(idpersonal, legajo, idpersona, fechaalta, fechabaja, observaciones)
          VALUES (var_idpersonal, var_legajo, pin_idpersona, var_fechaalta, var_fechabaja, var_observaciones);
      END IF;

      RETURN var_idpersonal;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION pr_deletepersonal (
  pin_idpersonal                   INTEGER
) RETURNS VOID AS $$

  DECLARE
      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino personal
      DELETE FROM personal p
        WHERE p.idpersonal = pin_idpersonal;

      RETURN;
  END;
$$ LANGUAGE plpgsql;

-- =============================================================================
-- USUARIOS
-- =============================================================================
CREATE OR REPLACE FUNCTION fn_getidusuario (
) RETURNS INTEGER AS $$

  DECLARE
      var_idusuario                INTEGER         := NULL;

  BEGIN
      var_idusuario:= NEXTVAL('sq_usuario');
      RETURN var_idusuario;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_saveusuario (
  pin_idusuario                    INTEGER,
  pin_usr                          VARCHAR,
  pin_passwd                       VARCHAR,
  pin_idpersona                    INTEGER,
  pin_observaciones                VARCHAR
) RETURNS INTEGER AS $$

  DECLARE
      var_idusuario                INTEGER         := pin_idusuario;
      var_usr                      VARCHAR         := TRIM(pin_usr);
      var_passwd                   VARCHAR         := TRIM(pin_passwd);
      var_observaciones            VARCHAR         := TRIM(pin_observaciones);
      
      var_step                     INTEGER         := 0;
      
      var_count                    INTEGER         := 0;

  BEGIN
      var_step:= 1; -- cuento usuarios
      SELECT COUNT(*)
        INTO var_count
        FROM usuario u
        WHERE u.idusuario = var_idusuario;

      var_step:= 2; -- veo si existe
      IF (var_count > 0) THEN
        var_step:= 3; -- sí existe ==> update
        UPDATE usuario u SET
          usr = var_usr,
          passwd = var_passwd,
          idpersona = pin_idpersona,
          observaciones = var_observaciones
        WHERE u.idusuario = var_idusuario;
      ELSE
        var_step:= 4; -- no existe ==> insert
        IF (var_idusuario IS NULL) THEN
          var_idusuario := fn_getidusuario();
        END IF;
        INSERT INTO usuario(idusuario, usr, passwd, idpersona, observaciones)
          VALUES (var_idusuario, var_usr, var_passwd, pin_idpersona, var_observaciones);
      END IF;

      RETURN var_idusuario;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION pr_deleteusuario (
  pin_idusuario                    INTEGER
) RETURNS VOID AS $$

  DECLARE
      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino el usuario
      DELETE FROM usuario u
        WHERE u.idusuario = pin_idusuario;

      RETURN;
  END;
$$ LANGUAGE plpgsql;

-- =============================================================================
-- ROLES DE SISTEMA
-- =============================================================================
CREATE OR REPLACE FUNCTION fn_getidrolsistema (
) RETURNS INTEGER AS $$

  DECLARE
      var_idrol                    INTEGER         := NULL;

  BEGIN
      var_idrol:= NEXTVAL('sq_rolsistema');
      RETURN var_idrol;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_saverolsistema (
  pin_idrolsistema                 INTEGER,
  pin_cgorolsistema                VARCHAR,
  pin_descripcion                  VARCHAR,
  pin_observaciones                VARCHAR
) RETURNS INTEGER AS $$

  DECLARE
      var_idrolsistema             INTEGER         := pin_idrolsistema;
      var_cgorolsistema            VARCHAR         := TRIM(pin_cgorolsistema);
      var_descripcion              VARCHAR         := TRIM(pin_descripcion);
      var_observaciones            VARCHAR         := TRIM(pin_observaciones);
      
      var_step                     INTEGER         := 0;
      
      var_count                    INTEGER         := 0;

  BEGIN
      var_step:= 1; -- cuento roles de sistema
      SELECT COUNT(*)
        INTO var_count
        FROM rolsistema rs
        WHERE rs.idrolsistema = var_idrolsistema;

      var_step:= 2; -- veo si existe
      IF (var_count > 0) THEN
        var_step:= 3; -- sí existe ==> update
        UPDATE rolsistema rs SET
          cgorolsistema = var_cgorolsistema,
          descripcion = var_descripcion,
          observaciones = var_observaciones
        WHERE rs.idrol = var_idrolsistema;
      ELSE
        var_step:= 4; -- no existe ==> insert
        IF (var_idrolsistema IS NULL) THEN
          var_idrolsistema = fn_getidrolsistema();
        END IF;
        INSERT INTO rolsistema(idrolsistema, cgorolsistema, descripcion, observaciones)
          VALUES (var_idrolsistema, var_cgorolsistema, var_descripcion, var_observaciones);
      END IF;

      RETURN var_idrolsistema;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION pr_deleterolsistema (
  pin_idrolsistema                 INTEGER
) RETURNS VOID AS $$

  DECLARE
      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino el rol de sistema
      DELETE FROM rol rs
        WHERE rs.idrolsistema = pin_idrolsistema;

      RETURN;
  END;
$$ LANGUAGE plpgsql;

-- =============================================================================
-- ROLES DE SISTEMA x PERSONA
-- =============================================================================
CREATE OR REPLACE FUNCTION fn_saverolsistemapersona (
  pin_idpersona                    INTEGER,
  pin_idrolsistema                 INTEGER,
  pin_suspendido                   CHAR,
  pin_observaciones                VARCHAR
) RETURNS INTEGER AS $$

  DECLARE
      var_observaciones            VARCHAR         := TRIM(pin_observaciones);

      var_step                     INTEGER         := 0;

      var_count                    INTEGER         := 0;

  BEGIN
      var_step:= 1; -- cuento roles de sistema
      SELECT COUNT(*)
        INTO var_count
        FROM personarolsistema prs
        WHERE prs.idpersona = pin_idpersona
        AND prs.idrolsistema = pin_idrolsistema;

      var_step:= 2; -- veo si existe
      IF (var_count > 0) THEN
        var_step:= 3; -- sí existe ==> update
        UPDATE personarolsistema prs SET
          suspendido = pin_suspendido,
          observaciones = var_observaciones
        WHERE prs.idpersona = pin_idpersona
        AND prs.idrolsistema = pin_idrolsistema;
      ELSE
        var_step:= 4; -- no existe ==> insert
        INSERT INTO personarolsistema(idpersona, idrolsistema, suspendido, observaciones)
          VALUES (pin_idpersona, pin_idrolsistema, pin_suspendido, var_observaciones);
      END IF;

      RETURN pin_idrolsistema;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION pr_deleterolsistemapersona (
  pin_idpersona                    INTEGER,
  pin_idrolsistema                 INTEGER
) RETURNS VOID AS $$

  DECLARE
      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino el rol de sistema
      DELETE FROM personarolsistema prs
        WHERE prs.idpersona = pin_idpersona
        AND prs.idrolsistema = pin_idrolsistema;

      RETURN;
  END;
$$ LANGUAGE plpgsql;

-- =============================================================================
-- PRIVILEGIOS
-- =============================================================================
CREATE OR REPLACE FUNCTION fn_getidprivilegio (
) RETURNS INTEGER AS $$

  DECLARE
      var_idprivilegio             INTEGER         := NULL;

  BEGIN
      var_idprivilegio:= NEXTVAL('sq_privilegio');
      RETURN var_idprivilegio;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_saveprivilegio (
  pin_idprivilegio                 INTEGER,
  pin_cgoprivilegio                VARCHAR,
  pin_descripcion                  VARCHAR,
  pin_observaciones                VARCHAR
) RETURNS INTEGER AS $$

  DECLARE
      var_idprivilegio             INTEGER         := pin_idprivilegio;
      var_cgoprivilegio            VARCHAR         := TRIM(pin_cgoprivilegio);
      var_descripcion              VARCHAR         := TRIM(pin_descripcion);
      var_observaciones            VARCHAR         := TRIM(pin_observaciones);
      
      var_step                     INTEGER         := 0;
      
      var_count                    INTEGER         := 0;

  BEGIN
      var_step:= 1; -- cuento privilegios
      SELECT COUNT(*)
        INTO var_count
        FROM privilegio p
        WHERE p.idprivilegio = var_idprivilegio;

      var_step:= 2; -- veo si existe
      IF (var_count > 0) THEN
        var_step:= 3; -- sí existe ==> update
        UPDATE privilegio p SET
          cgoprivilegio = var_cgoprivilegio,
          descripcion = var_descripcion,
          observaciones = var_observaciones
        WHERE p.idprivilegio = var_idprivilegio;
      ELSE
        var_step:= 4; -- no existe ==> insert
        IF (var_idprivilegio IS NULL) THEN
          var_idprivilegio = fn_getidprivilegio();
        END IF;
        INSERT INTO privilegio(idprivilegio, cgoprivilegio, descripcion, observaciones)
          VALUES (var_idprivilegio, var_cgoprivilegio, var_descripcion, var_observaciones);
      END IF;

      RETURN var_idprivilegio;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION pr_deleteprivilegio (
  pin_idprivilegio                 INTEGER
) RETURNS VOID AS $$

  DECLARE
      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino el privilegio
      DELETE FROM privilegio p
        WHERE p.idprivilegio = pin_idprivilegio;

      RETURN;
  END;
$$ LANGUAGE plpgsql;

-- =============================================================================
-- PRIVILEGIOS x ROLES Y PERSONAS
-- =============================================================================
CREATE OR REPLACE FUNCTION pr_deleteprivilegiorolsistema (
  pin_idrolsistema                 INTEGER,
  pin_idprivilegio                 INTEGER
) RETURNS VOID AS $$

  DECLARE
      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino el privilegio
      DELETE FROM rolsistemaprivilegio rsp
        WHERE rsp.idrolsistema = pin_idrolsistema
        AND rsp.idprivilegio = pin_idprivilegio;

      RETURN;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_saveprivilegiorolsistema (
  pin_idrolsistema                 INTEGER,
  pin_idprivilegio                 INTEGER,
  pin_permit                       CHAR,
  pin_observaciones                VARCHAR
) RETURNS INTEGER AS $$

  DECLARE
      var_observaciones            VARCHAR         := TRIM(pin_observaciones);
      
      var_step                     INTEGER         := 0;
      
      var_count                    INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino privilegio
      PERFORM pr_deleteprivilegiorolsistema(pin_idrolsistema, pin_idprivilegio);

      var_step:= 2; -- agrego privilegio
      INSERT INTO rolsistemaprivilegio(idrolsistema, idprivilegio, permit, observaciones)
        VALUES (pin_idrolsistema, pin_idprivilegio, pin_permit, var_observaciones);

      RETURN pin_idprivilegio;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION pr_deleteprivilegiopersona (
  pin_idpersona                    INTEGER,
  pin_idprivilegio                 INTEGER
) RETURNS VOID AS $$

  DECLARE
      var_step                     INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino el privilegio
      DELETE FROM personaprivilegio pp
        WHERE pp.idpersona = pin_idpersona
        AND pp.idprivilegio = pin_idprivilegio;

      RETURN;
  END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_saveprivilegiopersona (
  pin_idpersona                    INTEGER,
  pin_idprivilegio                 INTEGER,
  pin_permit                       CHAR,
  pin_observaciones                VARCHAR
) RETURNS INTEGER AS $$

  DECLARE
      var_observaciones            VARCHAR         := TRIM(pin_observaciones);

      var_step                     INTEGER         := 0;

      var_count                    INTEGER         := 0;

  BEGIN
      var_step:= 1; -- elimino privilegio
      PERFORM pr_deleteprivilegiopersona(pin_idpersona, pin_idprivilegio);

      var_step:= 2; -- agrego privilegio
      INSERT INTO personaprivilegio(idpersona, idprivilegio, permit, observaciones)
        VALUES (pin_idpersona, pin_idprivilegio, pin_permit, var_observaciones);

      RETURN pin_idprivilegio;
  END;
$$ LANGUAGE plpgsql;

-- =============================================================================
COMMIT;
-- =============================================================================

