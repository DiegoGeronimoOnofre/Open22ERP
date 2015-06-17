-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: db
-- Source Schemata: dados
-- Created: Sun May 17 12:51:44 2015
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema db
-- ----------------------------------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db` ;


-- ----------------------------------------------------------------------------
-- Table db.clientes
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`pessoas` (
  `codigo` INT(11) NOT NULL,
  `nome` VARCHAR(50) NULL DEFAULT NULL,
  `cpf` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `localizacao` VARCHAR(100) NULL DEFAULT NULL,
  `telefone` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table db.formaspagamento
-- ----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `db`.`formaspagamento` (
  `codigo` INT(11) NOT NULL PRIMARY KEY,
  `descricao` VARCHAR(50) NOT NULL,	
  `limite_value` INT(11) NOT NULL	
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table db.mov
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`mov` (
  `codigo` INT(11) NOT NULL,
  `cod_cli` INT(11) NULL DEFAULT NULL,
  `cod_pay_method` INT(11) NOT NULL,
  `mov_type` INT(11) NOT NULL,
  `mov_time` BIGINT(8) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fkcli` (`cod_cli` ASC),
  CONSTRAINT `fkcli`
    FOREIGN KEY (`cod_cli`)
    REFERENCES `db`.`pessoas` (`codigo`),

  INDEX `fkpay_method` (`cod_pay_method` ASC),
  CONSTRAINT `fkpay_method`
    FOREIGN KEY (`cod_pay_method`)
    REFERENCES `db`.`formaspagamento` (`codigo`))

ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table db.mov_prod
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`mov_prod` (
  `cod_mov` INT(11) NOT NULL DEFAULT '0',
  `cod_prod` INT(11) NOT NULL DEFAULT '0',
  `preco` DOUBLE(6,2) NULL DEFAULT NULL,
  `qt` INT(11) NULL DEFAULT NULL,
  `total` DOUBLE(6,2) NULL DEFAULT NULL,
  PRIMARY KEY (`cod_mov`, `cod_prod`),
  INDEX `fk_cod_prod` (`cod_prod` ASC),
  CONSTRAINT `fk_cod_mov`
    FOREIGN KEY (`cod_mov`)
    REFERENCES `db`.`mov` (`codigo`),
  CONSTRAINT `fk_cod_prod`
    FOREIGN KEY (`cod_prod`)
    REFERENCES `db`.`produtos` (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table db.produtos
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `db`.`produtos` (
  `codigo` INT(11) NOT NULL,
  `cod_barras` VARCHAR(30) NULL DEFAULT NULL,
  `descricao` VARCHAR(50) NULL DEFAULT NULL,
  `preco` DOUBLE(6,2) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table db.estoque
-- ----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `db`.`estoque` (
  `cod_prod` INT(11) NOT NULL,
  `qt`       INT(11) NOT NULL,
  PRIMARY KEY (`cod_prod`),

  CONSTRAINT `fk_est_cod_prod`
    FOREIGN KEY (`cod_prod`)
    REFERENCES `db`.`produtos` (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SET FOREIGN_KEY_CHECKS = 1;

