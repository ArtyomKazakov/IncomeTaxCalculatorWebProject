-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema calculator
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `calculator` ;

-- -----------------------------------------------------
-- Schema calculator
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `calculator` DEFAULT CHARACTER SET utf8 ;
USE `calculator` ;

-- -----------------------------------------------------
-- Table `calculator`.`languages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `calculator`.`languages` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `calculator`.`periods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `calculator`.`periods` (
  `value` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `language_id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`value`, `language_id`),
  INDEX `fk_periods_languages_idx` (`language_id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  CONSTRAINT `fk_periods_languages`
    FOREIGN KEY (`language_id`)
    REFERENCES `calculator`.`languages` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `calculator`.`calculations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `calculator`.`calculations` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `period_value` INT UNSIGNED NOT NULL COMMENT 'Период, за который производится расчет',
  `revenue_from_sale` DECIMAL(13,2) UNSIGNED NOT NULL COMMENT 'Сумма выручки от реализации товаров (работ, услуг), имущественных прав за выбранный период (без налога на добавленную стоимость), руб.',
  `nonoperating_income` DECIMAL(13,2) UNSIGNED NOT NULL COMMENT 'Сумма внереализационных доходов за выбранный период (без налога на добавленную стоимость), руб.',
  `has_main_job` TINYINT(1) NOT NULL COMMENT 'Наличие места основной работы',
  `has_right_to_benefits` TINYINT(1) NULL COMMENT 'Наличие права на льготы (инвалид I или II группы, инвалид с детства, участник боевых действий на территории других государств и др.)',
  `is_widow_or_single_parent_or_trustee` TINYINT(1) NULL COMMENT 'Являетесь ли Вы вдовой (вдовцом), одиноким родителем, приемным родителем, опекуном или попечителем',
  `number_of_children_under_eighteen` TINYINT UNSIGNED NULL COMMENT 'Количество детей до 18 лет',
  `number_of_disabled_children_under_eighteen` TINYINT UNSIGNED NULL COMMENT 'Количество детей-инвалидов до 18 лет\n',
  `number_of_dependents` TINYINT UNSIGNED NULL COMMENT 'Количество иждивенцев ',
  `sum_of_expenses_on_insurance_premiums` DECIMAL(13,2) UNSIGNED NULL COMMENT 'Сумма расходов за выбранный период по страховым взносам по договорам добровольного страхования жизни и дополнительной пенсии, заключенным на срок не менее трех лет, а также по договорам добровольного страхования медицинских расходов, руб.',
  `sum_of_expenses_on_first_paid_education_for_relative` DECIMAL(13,2) UNSIGNED NULL COMMENT 'Сумма расходов за выбранный период на получение первого платного образования своего либо близких родственников, руб.',
  `sum_of_expenses_on_constr_or_acquisition_of_housing_for_req` DECIMAL(13,2) UNSIGNED NULL COMMENT 'Сумма расходов за выбранный период на строительство либо приобретение жилья для нуждающихся в улучшении жилищных условий, руб.',
  `sum_of_expenses_on_business_activity` DECIMAL(13,2) UNSIGNED NOT NULL COMMENT 'Сумма расходов за выбранный период, связанных с осуществлением предпринимательской деятельности, руб. ',
  `income_tax` DECIMAL(13,2) UNSIGNED NOT NULL COMMENT 'Сумма подоходного налога к уплате, руб. ',
  PRIMARY KEY (`id`),
  INDEX `fk_calculations_periods1_idx` (`period_value` ASC),
  CONSTRAINT `fk_calculations_periods1`
    FOREIGN KEY (`period_value`)
    REFERENCES `calculator`.`periods` (`value`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `calculator`.`languages`
-- -----------------------------------------------------
START TRANSACTION;
USE `calculator`;
INSERT INTO `calculator`.`languages` (`id`, `name`) VALUES (1, 'RU');
INSERT INTO `calculator`.`languages` (`id`, `name`) VALUES (2, 'EN');

COMMIT;


-- -----------------------------------------------------
-- Data for table `calculator`.`periods`
-- -----------------------------------------------------
START TRANSACTION;
USE `calculator`;
INSERT INTO `calculator`.`periods` (`value`, `language_id`, `name`) VALUES (3, 1, 'квартал');
INSERT INTO `calculator`.`periods` (`value`, `language_id`, `name`) VALUES (3, 2, 'quarter');
INSERT INTO `calculator`.`periods` (`value`, `language_id`, `name`) VALUES (6, 1, 'полугодие');
INSERT INTO `calculator`.`periods` (`value`, `language_id`, `name`) VALUES (6, 2, 'half-year');
INSERT INTO `calculator`.`periods` (`value`, `language_id`, `name`) VALUES (9, 1, 'девять месяцев');
INSERT INTO `calculator`.`periods` (`value`, `language_id`, `name`) VALUES (9, 2, 'nine months');
INSERT INTO `calculator`.`periods` (`value`, `language_id`, `name`) VALUES (12, 1, 'год');
INSERT INTO `calculator`.`periods` (`value`, `language_id`, `name`) VALUES (12, 2, 'year');

COMMIT;

