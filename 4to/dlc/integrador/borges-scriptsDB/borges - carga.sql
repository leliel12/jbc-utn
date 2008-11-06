-- =============================================================================
--
--    U N I V E R S I D A D   T E C N O L Ó G I C A   N A C I O N A L
--    F A C U L T A D   R E G I O N A L   C Ó R D O B A
--
--    D I S E Ñ O   D E   L E N G U A J E S   D E   C O N S U L T A   ( D L C )
--    P R Á C T I C O   T U T O R :   BORGES
--
--    C A R G A   B B D D
--    A U T O R :    CABRAL - 40842
--
-- ============================================================================= 
USE borgesdb;


-- Cargamos las formas de pago -- 
call pr_insertforma_pago('Efectivo', 0);
call pr_insertforma_pago('Debito', 0.5);
call pr_insertforma_pago('Tarjeta', 0.2);
call pr_insertforma_pago('Cheque', 0.1);

-- Libros --
call pr_insertlibro('B-0000-01','Don Quijote de la Mancha', 'Miguel de Cervantes Saavedra', 29.5, 'Alfaguara');
call pr_insertlibro('84-663-0991-8','Las Aventuras del Capitan Alatriste', 'Arturo y Carlota Perez-Reverte', 22.0, 'Punto de Lectura');
call pr_insertlibro('84-395-8721-X','Ficciones', 'Jorge Luis Borges', 18.0, 'Biblioteca La Nacion');
call pr_insertlibro('950-9080-00-4','Artmis Fowl', 'Eoin Colfer', 20.0, 'Montena');
call pr_insertlibro('84-450-7022-3','El Fin de La Infancia', 'Arthur C. Clarke', 32.5, 'Minotauro');
call pr_insertlibro('B-666','Necronomicon', 'A-Hazif', 66.6, 'Burinjell');
call pr_insertLibro('987-545-194-0','Los Dias del Fuego','Liliana Bodoc',39.90,'norma');

-- Paginas --
call pr_insertpagina('Home');
call pr_insertpagina('Administracion');
call pr_insertpagina('Comprar');

-- Pais --
call pr_insertpais('Argentina');
call pr_insertpais('Chile');
call pr_insertpais('NN');

--Region--
call pr_insertregion('Valparaiso',2);
call pr_insertregion('Atacama',2);
call pr_insertregion('Cordoba',1);
call pr_insertregion('Misiones',1);
call pr_insertregion('Buenos Aires',1);
call pr_insertregion('NN',3);

-- Localidad --
call pr_insertLocalidad('Posadas', 4);
call pr_insertLocalidad('Cordoba',3);
call pr_insertLocalidad('Rio Cuarto', 3);
call pr_insertLocalidad('Capital Federal',5);
call pr_insertLocalidad('Copiapo',2);
call pr_insertLocalidad('Valparaiso',1);
call pr_insertLocalidad('NN',6);

-- Roles --
call pr_insertRoles('Administrador');
call pr_insertRoles('Usuario');
call pr_insertRoles('Anonimo');

-- roles x pagina --
call pr_insertroles_x_pagina(1,1);
call pr_insertroles_x_pagina(1,2);
call pr_insertroles_x_pagina(2,1);
call pr_insertroles_x_pagina(2,3);
call pr_insertroles_x_pagina(1,3);
call pr_insertroles_x_pagina(3,1);

-- Servicios --
call pr_insertservicio('Encuadernado',50);
call pr_insertservicio('Restauracion',100);

-- Usuarios --
call pr_insertusuario('admin','admin','admin@borgesdb.com','Ayacucho 437 - 1D', '156-45698', 2,'Jorge', 'Borges', 1);
call pr_insertusuario('Kaiser','25012501','tarado@hotmail.com','Dean Funes 900', '156-45868', 3,'Elmer', 'Luso', 2);
call pr_insertusuario('Anonimo','0','0','0','0',7,'0','0',3);

-- Ticket --
call pr_insertTicket('Kaiser',72,1);

-- Detalle de Ticket --
call pr_insertDetalle_Ticket(1,1,'84-663-0991-8',null);
call pr_insertDetalle_Ticket(1,1,null,1);






















