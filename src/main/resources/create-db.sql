-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema itexperts
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema itexperts
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `itexperts` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `itexperts` ;

-- -----------------------------------------------------
-- Table `itexperts`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `itexperts`.`account` ;

CREATE TABLE IF NOT EXISTS `itexperts`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `account_code` VARCHAR(255) NULL DEFAULT NULL,
  `agency_code` VARCHAR(255) NULL DEFAULT NULL,
  `name_owner` VARCHAR(255) NULL DEFAULT NULL,
  `register_id` VARCHAR(255) NULL DEFAULT NULL,
  `digit_verification` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `itexperts`.`type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `itexperts`.`type` ;

CREATE TABLE IF NOT EXISTS `itexperts`.`type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_card_name_index` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `itexperts`.`card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `itexperts`.`card` ;

CREATE TABLE IF NOT EXISTS `itexperts`.`card` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `digit_code` VARCHAR(255) NULL DEFAULT NULL,
  `flag` VARCHAR(255) NULL DEFAULT NULL,
  `limit_balance` DOUBLE NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `number` VARCHAR(255) NULL DEFAULT NULL,
  `account_id` INT NULL DEFAULT NULL,
  `card_type_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_card_account` (`account_id` ASC) VISIBLE,
  INDEX `fk_card_type` (`card_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_card_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `itexperts`.`account` (`id`),
  CONSTRAINT `fk_card_type`
    FOREIGN KEY (`card_type_id`)
    REFERENCES `itexperts`.`type` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;