-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema students
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema students
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `students` DEFAULT CHARACTER SET utf8 ;
USE `students` ;

-- -----------------------------------------------------
-- Table `students`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `students`.`students` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `gpa` DOUBLE NOT NULL,
  `facultad` VARCHAR(45) NOT NULL,
  `creditoscompletados` INT NOT NULL,
  `ranking` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

INSERT INTO `students`.`students` (`id`, `nombre`, `gpa`, `facultad`, `creditoscompletados`, `ranking`) VALUES 
(1, 'Juan Pérez', 3.8, 'Ingeniería', 120, 15),
(2, 'María López', 3.6, 'Medicina', 110, 30),
(3, 'Carlos Sánchez', 3.9, 'Ciencias Sociales', 130, 5),
(4, 'Ana Martínez', 3.5, 'Artes', 100, 50),
(5, 'Luis Fernández', 4.0, 'Ciencias Sociales', 140, 1),
(6, 'Gabriela Gómez', 3.7, 'Artes', 115, 25),
(7, 'Miguel Torres', 3.4, 'Artes', 90, 60),
(8, 'Sandra Ramírez', 3.2, 'Ingenieria', 80, 75),
(9, 'Ricardo Ruiz', 3.3, 'Medicina', 85, 70),
(10, 'Patricia Jiménez', 3.9, 'Artes', 135, 10);



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
