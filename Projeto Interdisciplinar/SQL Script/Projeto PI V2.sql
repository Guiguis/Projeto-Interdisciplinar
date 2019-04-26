SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `projeto` DEFAULT CHARACTER SET utf8 ;
USE `projeto` ;

-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`professor` (
  `professor_id` INT NOT NULL,
  `administrador` INT NULL DEFAULT 0,
  `matricula` VARCHAR(45) NULL,
  PRIMARY KEY (`professor_id`),
  INDEX `fk_Professor_Usuario_idx` (`professor_id` ASC) ,
  UNIQUE INDEX `matricula_UNIQUE` (`matricula` ASC) ,
  CONSTRAINT `fk_Professor_Usuario`
    FOREIGN KEY (`professor_id`)
    REFERENCES `projeto`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`aluno` (
  `aluno_id` INT NOT NULL,
  `ra` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`aluno_id`),
  UNIQUE INDEX `ra_UNIQUE` (`ra` ASC) ,
  CONSTRAINT `fk_Aluno_Usuario1`
    FOREIGN KEY (`aluno_id`)
    REFERENCES `projeto`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`tema` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dt_cadastro` DATETIME NULL,
  `titulo` VARCHAR(100) NOT NULL,
  `introducao` VARCHAR(250) NULL,
  `requisitos` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`turma` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `semestre_letivo` INT NULL,
  `ano_letivo` INT NULL,
  `sigla` VARCHAR(45) NULL,
  `tema_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_turma_tema1_idx` (`tema_id` ASC) ,
  UNIQUE INDEX `idx_uk_sigla_ano_semestre` (`semestre_letivo` ASC, `ano_letivo` ASC, `sigla` ASC) ,
  CONSTRAINT `fk_turma_tema1`
    FOREIGN KEY (`tema_id`)
    REFERENCES `projeto`.`tema` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`grupo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `orientador_id` INT NOT NULL,
  `numero` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_grupo_Professor1_idx` (`orientador_id` ASC) ,
  CONSTRAINT `fk_grupo_Professor1`
    FOREIGN KEY (`orientador_id`)
    REFERENCES `projeto`.`professor` (`professor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`turma_aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`turma_aluno` (
  `Aluno_id` INT NOT NULL,
  `turma_id` INT NOT NULL,
  `grupo_id` INT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  INDEX `fk_Aluno_has_turma_turma1_idx` (`turma_id` ASC) ,
  INDEX `fk_Aluno_has_turma_Aluno1_idx` (`Aluno_id` ASC) ,
  INDEX `fk_Aluno_has_turma_grupo1_idx` (`grupo_id` ASC) ,
  INDEX `idx_aluno_turma` (`Aluno_id` ASC, `turma_id` ASC) ,
  CONSTRAINT `fk_Aluno_has_turma_Aluno1`
    FOREIGN KEY (`Aluno_id`)
    REFERENCES `projeto`.`aluno` (`aluno_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aluno_has_turma_turma1`
    FOREIGN KEY (`turma_id`)
    REFERENCES `projeto`.`turma` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aluno_has_turma_grupo1`
    FOREIGN KEY (`grupo_id`)
    REFERENCES `projeto`.`grupo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`banca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`banca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `grupo_id` INT NOT NULL,
  `data` DATETIME NULL,
  `sala` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_banca_grupo1_idx` (`grupo_id` ASC) ,
  CONSTRAINT `fk_banca_grupo1`
    FOREIGN KEY (`grupo_id`)
    REFERENCES `projeto`.`grupo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`professores_banca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`professores_banca` (
  `banca_id` INT NOT NULL,
  `Professor_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `avaliacao` DECIMAL(4,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_banca_has_Professor_Professor1_idx` (`Professor_id` ASC) ,
  INDEX `fk_banca_has_Professor_banca1_idx` (`banca_id` ASC) ,
  INDEX `idx_banca_prof` (`banca_id` ASC, `Professor_id` ASC) ,
  CONSTRAINT `fk_banca_has_Professor_banca1`
    FOREIGN KEY (`banca_id`)
    REFERENCES `projeto`.`banca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_banca_has_Professor_Professor1`
    FOREIGN KEY (`Professor_id`)
    REFERENCES `projeto`.`professor` (`professor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`atividade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tema_id` INT NOT NULL,
  `numero` INT NULL,
  `descricao` VARCHAR(250) NULL,
  `formato_entrega` VARCHAR(45) NULL,
  `dt_inicio` DATETIME NULL,
  `dt_fim` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_atividade_tema1_idx` (`tema_id` ASC) ,
  CONSTRAINT `fk_atividade_tema1`
    FOREIGN KEY (`tema_id`)
    REFERENCES `projeto`.`tema` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`entrega`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`entrega` (
  `grupo_id` INT NOT NULL,
  `atividade_id` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `dt_cadastro` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_grupo_has_entregas_entregas1_idx` (`atividade_id` ASC) ,
  INDEX `fk_grupo_has_entregas_grupo1_idx` (`grupo_id` ASC) ,
  INDEX `idx_grupo_ativ` (`grupo_id` ASC, `atividade_id` ASC) ,
  CONSTRAINT `fk_grupo_has_entregas_grupo1`
    FOREIGN KEY (`grupo_id`)
    REFERENCES `projeto`.`grupo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_has_entregas_entregas1`
    FOREIGN KEY (`atividade_id`)
    REFERENCES `projeto`.`atividade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`avaliacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projeto`.`avaliacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `entrega_id` INT NOT NULL,
  `turma_aluno_id` INT NOT NULL,
  `nota` DECIMAL(4,2) NULL,
  `dt_avaliacao` DATETIME NULL,
  `comentarios` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Avaliacao_entregas1_idx` (`entrega_id` ASC) ,
  INDEX `fk_Avaliacao_Aluno_has_turma1_idx` (`turma_aluno_id` ASC) ,
  CONSTRAINT `fk_Avaliacao_entregas1`
    FOREIGN KEY (`entrega_id`)
    REFERENCES `projeto`.`entrega` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Avaliacao_Aluno_has_turma1`
    FOREIGN KEY (`turma_aluno_id`)
    REFERENCES `projeto`.`turma_aluno` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- ------------------------------ USUARIO ----------------------------------
INSERT INTO `usuario` VALUES 

(1,'Pedro Quessada','pedro.quessada@usjt.br','aluno123'),
(2,'Jamal Miranda','jamal.miranda@gmail.com.br','aluno123'),
(3,'Arthur Agostinho','arthur.agostinho@outlook.com','aluno123'),
(4,'Karen Beth','karen.beth@hotmail.com','aluno123'),
(5,'Gustavo Xinzao','gustavoxinzao@hotmail.com','aluno123'),
(6,'Guilherme Bodelon','guigui.bo@usjt.com','aluno123'),
(7,'Jose Fonseca ','fonseca.alonso@gmail.com.br','prof123'),
(8,'Zezinho da Silva','silva.zeze@hotmail.com','prof123'),
(9,'Maria Aparecida','maria.aparecida@outlook.com','prof123'),
(10,'Yara Faria','yara.faria@yahoo.com.br','aluno123'),
(11,'Jaqueline Pereira','jaque.pereira@gmail.com','aluno123'),
(12,'Bruno Nascimento','bruno.nascimento@gmail.com','aluno123'),
(13,'Paloma Pereira','paloma.pereira@hotmail.com','aluno123'),
(14,'Napoleao','napoleao@usjt.com','prof123'),
(15,'Ayrton Sena','seninha@gmail.com','admin123'),
(16,'Pedro','prof.pedro@saoUlife.com','prof123');

-- -------------------------------- PROFESSOR ---------------------------------------
INSERT INTO `professor` VALUES

(7,1,'2018Ulife'),
(8,0,'2005usjt3374'),
(9,0,'2000anima8234'),
(14,1,'2010anima8364'),
(15,1,'2012usjt7356'),
(16,0,'973894092');
 
 -- ----------------------------- ALUNO --------------------------------------------
 INSERT INTO `aluno` VALUES 

(6,'806395712'),
(4,'80802347'),
(5,'808137324'),
(2,'81813245'),
(1,'818149040'),
(3,'818246244'),
(11,'844638549'),
(10,'846139536'),
(12,'857638068'),
(13,'986046147');

 -- ----------------------------- TEMA -----------------------------------------------
 INSERT INTO `tema` VALUES

(1,'2018-02-15 00:00:00','Java','Introducao a Java Orientado a Objetos','Logica de Programacao'),
(2,'2018-08-22 00:00:00','HTML','Introducao a HTML e CSS','Sem conhecimentos necessarios'),
(3,'2019-03-02 00:00:00','Java Web','Elaboracao de projeto utilizando Java e HTML','Java e HTML');
 
 -- ----------------------------- TURMA ----------------------------------------------
 INSERT INTO `turma` VALUES 

(1,1,2019,'SIN-MCA3',3),
(2,2,2018,'SIN-MCA2',2),
(3,1,2018,'SIN-MCA1',1);

 -- ---------------------------- GRUPO ---------------------------------------------
INSERT INTO `grupo` VALUES 

(1,8,1,'Grupo 1'),
(2,7,2,'Grupo 2'),
(3,14,3,'Grupo 3'),
(4,15,4,'Grupo 4'),
(5,8,5,'Grupo 5'),
(6,9,6,'Grupo 6');

 -- --------------------------- TURMA_ALUNO ---------------------------------------

INSERT INTO `turma_aluno` VALUES 

(1,3,1,1),
(2,3,1,2),
(3,3,1,3),
(4,3,1,4),
(6,3,1,5),
(5,3,6,6),
(10,3,6,7),
(11,3,6,8),
(12,3,6,9),
(13,3,6,10),
(1,1,2,11),
(2,1,2,12),
(3,1,2,13),
(4,1,2,14),
(6,1,2,15),
(5,2,3,16),
(10,2,3,17),
(11,2,3,18),
(12,2,3,19),
(13,2,3,20),
(5,1,4,21),
(10,1,4,22),
(11,1,4,23),
(12,1,4,24),
(13,1,4,25);

-- ----------------------------- BANCA ----------------------------------------
INSERT INTO `banca` VALUES

 (1,1,'2019-05-20 00:00:00','102A'),
 (6,6,'2019-06-01 00:00:00','103E');
 
-- ----------------------------- PROFESSOR_BANCA ------------------------------
INSERT INTO `professores_banca` VALUES 

(1,7,1,80.00),
(1,8,6,75.00),
(1,9,11,90.00),
(6,7,16,95.00),
(6,14,25,90.00),
(6,15,26,93.00);

-- ----------------------------- ATIVIDADE -----------------------------------
INSERT INTO `atividade` VALUES 

(1,1,1,'Sistema de cadastro','Presencial','2018-03-01 00:00:00','2018-04-01 00:00:00'),
(2,1,2,'Sistema de login','Presencial ','2018-04-02 00:00:00','2018-05-10 00:00:00'),
(3,2,1,'Deve ser eleborado um site','E-mail	','2018-09-08 00:00:00','2018-10-01 00:00:00'),
(4,2,2,'Criar um site responsivo utilizado Bootstrap','GitHub','2018-10-05 00:00:00','2018-11-25 00:00:00'),
(5,3,1,'Criar o banco do site','Presencial','2019-03-10 00:00:00','2019-03-16 00:00:00'),
(6,3,2,'Sistema de cadastro de professores','GitHub','2019-03-17 00:00:00','2019-03-23 00:00:00'),
(7,3,3,'Entrega Banco de Dados','E-mail	','2019-03-24 00:00:00','2019-03-30 00:00:00'),
(8,3,4,'Sistema de verificacao de plagio','GitHub','2019-03-31 00:00:00','2019-04-06 00:00:00'),
(9,3,5,'Refatoracao do codigo','Presencial','2019-04-07 00:00:00','2019-04-13 00:00:00'),
(10,3,6,'Projeto finalizado','GitHub','2019-04-14 00:00:00','2019-04-20 00:00:00');

-- ----------------------------- ENTREGA ----------------------------------------
INSERT INTO `entrega` VALUES 

(1,5,1,'2019-03-15 00:00:00'),
(1,6,2,'2019-03-22 00:00:00'),
(1,7,3,'2019-03-30 00:00:00'),
(1,8,4,'2019-04-06 00:00:00'),
(1,9,5,'2019-04-10 00:00:00'),
(1,10,6,'2019-04-14 00:00:00'),
(6,5,7,'2019-03-14 00:00:00'),
(6,6,8,'2019-03-21 00:00:00'),
(6,7,9,'2019-03-31 00:00:00'),
(1,1,10,'2018-03-28 00:00:00'),
(1,2,11,'2018-05-09 00:00:00'),
(1,3,12,'2018-09-08 00:00:00'),
(6,3,13,'2018-10-01 00:00:00'),
(6,4,14,'2018-11-20 00:00:00');

-- ------------------------------ AVALIAÇÃO ------------------------------------
INSERT INTO `avaliacao` VALUES 

(1,6,1,90.00,'2019-04-14 00:00:00','boa apresentação'),
(2,6,2,90.00,'2019-04-14 00:00:00','boa apresentação '),
(3,6,3,90.00,'2019-04-14 00:00:00','boa apresentação '),
(4,6,4,90.00,'2019-04-14 00:00:00','boa apresentação '),
(5,6,5,90.00,'2019-04-14 00:00:00','boa apresentação '),
(6,9,6,80.00,'2019-03-31 00:00:00','faltou informações'),
(7,9,7,80.00,'2019-03-31 00:00:00','faltou informações'),
(8,9,8,80.00,'2019-03-31 00:00:00','faltou informações'),
(9,9,9,80.00,'2019-03-31 00:00:00','faltou informações'),
(10,9,10,80.00,'2019-03-31 00:00:00','faltou informações'),
(11,11,1,75.00,'2018-05-09 00:00:00','faltou uma entrega'),
(12,11,2,80.00,'2018-05-09 00:00:00','Boa apresentacao'),
(13,11,3,85.00,'2018-05-09 00:00:00','Boa apresentacao'),
(14,11,4,90.00,'2018-05-09 00:00:00','Boa apresentacao'),
(15,11,5,70.00,'2018-05-09 00:00:00','faltou na apresentacao'),
(16,14,6,90.00,'2018-11-20 00:00:00','Boa apresentaçao'),
(17,14,7,80.00,'2018-11-20 00:00:00','faltou na apresentacao'),
(18,14,8,70.00,'2018-11-20 00:00:00','faltou na apresentacao'),
(19,14,9,85.00,'2018-11-20 00:00:00','Nao sabia parte do conteudo'),
(20,14,10,95.00,'2018-11-20 00:00:00','Boa apresentacao'),
(22,2,2,80.00,'2019-04-23 00:00:00','bom'),
(23,2,3,80.00,'2019-04-23 00:00:00','bom'),
(25,2,2,99.00,'2019-04-23 00:00:00','excelente'),
(26,2,3,57.00,'2019-04-23 00:00:00','pessimo'),
(106,1,1,12.00,'2019-04-26 00:00:00','sd'),
(107,1,2,34.00,'2019-04-26 00:00:00','sd'),
(108,1,3,54.00,'2019-04-26 00:00:00','sd'),
(109,1,4,65.00,'2019-04-26 00:00:00','sd'),
(110,1,5,78.00,'2019-04-26 00:00:00','vd');