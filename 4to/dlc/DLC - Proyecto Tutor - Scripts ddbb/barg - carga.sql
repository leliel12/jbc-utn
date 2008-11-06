-- =============================================================================
--
--    U N I V E R S I D A D   T E C N O L Ó G I C A   N A C I O N A L
--    F A C U L T A D   R E G I O N A L   C Ó R D O B A
--
--    D I S E Ñ O   D E   L E N G U A J E S   D E   C O N S U L T A   ( D L C )
--    P R Á C T I C O   T U T O R :   H O M E B A N K I N G
--
--    C A R G A   B B D D
--    A U T O R :    S c a r a f i a
--
-- =============================================================================

-- =============================================================================
-- NACIONALIDADES
-- =============================================================================
SELECT fn_getidnacionalidad();
SELECT fn_savenacionalidad(1::SMALLINT, 'AR', 'Argentina');

-- =============================================================================
-- ROLES DE SISTEMA
-- =============================================================================
SELECT fn_getidrolsistema();
SELECT fn_saverolsistema(1, 'ADM_SISTEMA', 'Administrador de sistema', NULL);
SELECT fn_getidrolsistema();
SELECT fn_saverolsistema(2, 'RESPSUCURSAL', 'Responsable Sucursal', NULL);

-- =============================================================================
-- PRIVILEGIOS
-- =============================================================================
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(1, 'LOGIN', 'Ingresar al sistema', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(2, 'ADMIN', 'Administrar sistema', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(3, 'LST_SUCURSALES', 'Listar sucursales', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(4, 'RD_SUCURSALES', 'Ver datos de sucursales', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(5, 'WRT_SUCURSALES', 'Modificar datos de sucursales', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(6, 'LST_PERSONAL', 'Listar personal', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(7, 'RD_PERSONAL', 'Ver datos de personal', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(8, 'WRT_PERSONAL', 'Modificar datos de personal', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(9, 'LST_CLIENTES', 'Listar clientes', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(10, 'RD_CLIENTES', 'Ver datos de clientes', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(11, 'WRT_CLIENTES', 'Modificar datos de clientes', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(12, 'LST_PERSONAS', 'Listar clientes', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(13, 'RD_PERSONAS', 'Ver datos de clientes', NULL);
SELECT fn_getidprivilegio();
SELECT fn_saveprivilegio(14, 'WRT_PERSONAS', 'Modificar datos de clientes', NULL);

-- =============================================================================
-- PRIVILEGIOS x ROLES DE SISTEMA
-- =============================================================================
-- Administrador
SELECT fn_saveprivilegiorolsistema(1, 1, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 2, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 3, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 4, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 5, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 6, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 7, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 8, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 9, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 10, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 11, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 12, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 13, 'S', NULL);
SELECT fn_saveprivilegiorolsistema(1, 14, 'S', NULL);

-- =============================================================================
-- PERSONAL
-- =============================================================================
SELECT fn_getidpersona();
SELECT fn_savepersona(1, 'DNI', '99999999', 'Scarafia Altamira', 'Diego', 'M', '19800101', 1::SMALLINT, NULL);
SELECT fn_savedireccionpersona(1, 0, 'P', '5000', 'Córdoba', 'DomicilioCalle', '9999', NULL, NULL);
SELECT fn_savetelefonopersona(1, 0, 'M', '+54 9 351 9 999 999', NULL);
SELECT fn_getidpersonal();
SELECT fn_savepersonal(1, '99999', 1, '20070101000000', NULL, NULL);
SELECT fn_saverolsistemapersona(1, 1, 'N', NULL);
SELECT fn_getidusuario();
SELECT fn_saveusuario(1, 'scarafia', '999', 1, NULL);

SELECT fn_getidpersona();
SELECT fn_savepersona(2, 'DNI', 'DNIGerEnte', 'Ente', 'Ger', 'M', '19000101', 1::SMALLINT, NULL);
SELECT fn_savedireccionpersona(2, 0, 'P', '5000', 'Córdoba', 'CalleGerEnte', '1111', NULL, NULL);
SELECT fn_savetelefonopersona(2, 0, 'M', 'TelGerEnte', NULL);
SELECT fn_getidpersonal();
SELECT fn_savepersonal(2, 'legGerEnte', 2, '20070101000000', NULL, NULL);
SELECT fn_saverolsistemapersona(2, 2, 'N', NULL);
SELECT fn_getidusuario();
SELECT fn_saveusuario(2, 'gerente', '999', 2, NULL);

-- =============================================================================
-- CLIENTES
-- =============================================================================
SELECT fn_getidpersona();
SELECT fn_savepersona(3, 'DNI', '33333333', 'Pérez', 'Juan', 'M', '19830303', 1::SMALLINT, NULL);
SELECT fn_savedireccionpersona(3, 0, 'P', 'CP03', 'loc03', 'calle03', 'nro03', NULL, NULL);
SELECT fn_savetelefonopersona(3, 0, 'M', 'tel03', NULL);

SELECT fn_getidpersona();
SELECT fn_savepersona(4, 'DNI', '44444444', 'González', 'Pedro', 'M', '19840404', 1::SMALLINT, NULL);
SELECT fn_savedireccionpersona(4, 0, 'P', 'CP04', 'loc04', 'calle04', 'nro04', NULL, NULL);
SELECT fn_savetelefonopersona(4, 0, 'M', 'tel04', NULL);

SELECT fn_getidpersona();
SELECT fn_savepersona(5, 'DNI', '55555555', 'Ramírez', 'Paula', 'F', '19850505', 1::SMALLINT, NULL);
SELECT fn_savedireccionpersona(5, 0, 'P', 'CP05', 'loc05', 'calle05', 'nro05', NULL, NULL);
SELECT fn_savetelefonopersona(5, 0, 'M', 'tel05', NULL);

SELECT fn_getidpersona();
SELECT fn_savepersona(6, 'DNI', '66666666', 'Rodríguez', 'Carlos', 'M', '19860606', 1::SMALLINT, NULL);
SELECT fn_savedireccionpersona(6, 0, 'P', 'CP06', 'loc06', 'calle06', 'nro06', NULL, NULL);
SELECT fn_savetelefonopersona(6, 0, 'M', 'tel06', NULL);

SELECT fn_getidpersona();
SELECT fn_savepersona(7, 'DNI', '77777777', 'García', 'Julieta', 'F', '19870707', 1::SMALLINT, NULL);
SELECT fn_savedireccionpersona(7, 0, 'P', 'CP07', 'loc07', 'calle07', 'nro07', NULL, NULL);
SELECT fn_savetelefonopersona(7, 0, 'M', 'tel07', NULL);

SELECT fn_getidpersona();
SELECT fn_savepersona(8, 'DNI', '88888888', 'Peralta', 'Ana María', 'F', '19880808', 1::SMALLINT, NULL);
SELECT fn_savedireccionpersona(8, 0, 'P', 'CP08', 'loc08', 'calle08', 'nro08', NULL, NULL);
SELECT fn_savetelefonopersona(8, 0, 'M', 'tel08', NULL);

-- =============================================================================
-- PERSONAS
-- =============================================================================

-- =============================================================================
COMMIT;
-- =============================================================================

