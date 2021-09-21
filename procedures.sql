#CREAR BASE DE DATOS

CREATE DATABASE IF NOT EXISTS tutorial_procedure CHARACTER SET utf8 COLLATE utf8_general_ci;

# LISTA DE COCHES

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `lista_procedure`()
    READS SQL DATA
SELECT * FROM coche$$
DELIMITER ;

# COCHE A PARTIR DEL ID

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `id_procedure`(IN `idIn` INT(11))
    READS SQL DATA
SELECT * FROM coche WHERE id = idIn$$
DELIMITER ;

# GUARDAR UN NUEVO COCHE

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `save_procedure`(IN `marcaIn` VARCHAR(255), IN `modeloIn` VARCHAR(255), IN `anyoIn` INT(11), IN `kmIn` INT(11))
    MODIFIES SQL DATA
INSERT INTO coche (marca, modelo, anyo, km) VALUES(marcaIn, modeloIn, anyoIn, kmIn)$$
DELIMITER ;

# ELIMINAR UN COCHE

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_procedure`(IN `idIn` INT(11))
    MODIFIES SQL DATA
DELETE FROM coche WHERE id = idIn$$
DELIMITER ;

# OBTENER LA MEDIA DE LOS KILÓMETROS DE TODOS LOS COCHES

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `media_procedure`()
    READS SQL DATA
SELECT AVG(km) FROM coche$$
DELIMITER ;

# Modified Object
# Car lisy by brand and model
DROP PROCEDURE IF EXISTS marca_procedure;
CREATE DEFINER=`root`@`localhost` PROCEDURE `marca_procedure`(
    IN `typeIn` int, IN `brandIn` VARCHAR(255), IN `modelIn` VARCHAR(100))
    READS SQL DATA
if typeIn = 1 then
    SELECT * FROM coche WHERE marca = brandIn;
elseIF typeIn = 2 then
    SELECT * FROM coche WHERE marca = brandIn and modelo = modelIn;
end if

# Added Objects
# Get Reports
DROP PROCEDURE IF EXISTS sp_reports;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reports`(
    IN `typeIn` int, IN `brandIn` VARCHAR(100), IN `modelIn` VARCHAR(100))
    READS SQL DATA
if typeIn = 1 then
    SELECT marca, count(1) total FROM coche group by marca;
elseIF typeIn = 2 then
    SELECT marca, count(1) total FROM coche where marca=brandIn group by marca;
elseIF typeIn = 3 then
    SELECT modelo, count(1) total FROM coche where marca=brandIn group by modelo;
elseIF typeIn = 4 then
    SELECT modelo, count(1) total FROM coche where marca=brandIn and modelo=modelIn group by modelo;
end if