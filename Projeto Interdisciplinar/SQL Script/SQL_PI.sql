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
  `avaliacao` DECIMAL(5,2) NULL,
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
  `link` VARCHAR(255) NULL,
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
INSERT INTO `usuario`  (nome, email, senha) VALUES
('Maya', 'maya.debora@hotmail.com', 'aluno123'),
('Rosângela Maitê', 'rosangelamaite@hotmail.com', 'aluno123'),
('Guilherme Ducati','guilhermeducati@hotmail.com', 'aluno123'),
('Matheus Amaral', 'matheusamaral@hotmail.com', 'alunos123'),
('Lucas Gomes', 'lucasgomes@hotmail.com', 'alunos123'),
('Guilherme Teixeira', 'guilhermeteixeira@hotmail.com', 'aluno123'),
('Heitor Machado', 'heitormachado@hotmail.com', 'aluno123'),
('Guilherme Amaral', 'guilhermeamaral@hotmail.com', 'aluno123'),
('Gabriel Gomes', 'gabrielgomes@hotmail.com', 'aluno123'),
('Carlos Amaral', 'carlosamaral@hotmail.com', 'aluno123'),
('Otávio Gomes', 'otaviogomes@hotmail.com ', 'aluno123'),
('Camila Ribeiro', 'camila33@hotmail.com', 'aluno123'),
('MelissaDias', 'melissadias@hotmail.com', 'aluno123'),
('Antônio Rodrigues', 'antonio_rodrigues3@hotmail.com', 'aluno123'),
('Daniel Fernandes', 'danielfernandes@hotmail.com', 'aluno123'),
('Julia Rocha', 'julia.rocha2@hotmail.com', 'aluno123'),
('Matheus Rodrigues', 'matheus.rodrigues@hotmail.com', 'aluno123'),
('Lucas Cavalcanti', 'lucas_cavalcanti@hotmail.com', 'aluno123'),
('Carla Pereira', 'carlapereira@hotmail.com', 'aluno123'),
('Ana Correia', 'anacorreia@hotmail.com', 'aluno123'),
('Kevin Márcio', 'kevinmarcio@hotmail.com', 'aluno123'),
('Rafael Oliveira', 'rafaeloliveira@hotmail.com', 'aluno123'),
('Tiago Victor', 'tiagovictor@hotmail.com', 'aluno123'),
('Melissa Cardoso', 'melissacardoso@hotmail.com', 'aluno123'),
('Carolina Souza', 'carolinasouza@hotmail.com', 'aluno123'),
('Danilo Rocha', 'danilorocha@hotmail.com', 'aluno123'),
('Vitoria Oliveira', 'vitoriaoliveira@hotmail.com', 'aluno123'),
('Luiza Souza', 'luizasouza@hotmail.com', 'aluno123'),
('Aline Souza', 'alinesouza@hotmail.com', 'aluno123'),
('Davi Barros', 'davibarros@hotmail.com', 'aluno123'),
('Martim Santos', 'martinsantos@hotmail.com', 'aluno123'),
('Sarah Carvalho', 'sarahcarvalho@hotmail.com', 'aluno123'),
('Marcos Ferreira', 'marcosferreira@hotmail.com', 'aluno123'),
('Felipe Rocha', 'feliperocha@hotmail.com', 'aluno123'),
('Vinicius Correia', 'ViniciusCorreia@hotmail.com', 'aluno123'),
('Livia Castro', 'liviacastro@hotmail.com', 'aluno123'),
('João Cardoso', 'joaocardoso@hotmail.com', 'aluno123'),
('Rafael Barros', 'rafaelbarros@hotmail.com', 'aluno123'),
('Rafael Alves', 'rafaelalves@hotmail.com', 'aluno123'),
('Beatrice Rocha', 'beatricerocha@hotmail.com', 'aluno123'),
('Kauê Souza', 'kauesouza@hotmail.com', 'aluno123'),
('André Ribeiro', 'andreribeiro@hotmail.com', 'aluno123'),
('Rebeca Castro', 'rebecacastro@hotmail.com', 'aluno123'),
('Kauê Castro', 'kauecastro@hotmail.com', 'aluno123'),
('Clara Cardoso', 'claracardoso@hotmail.com', 'aluno123'),
('Diogo Fernandes', 'diogofernandes@hotmail.com', 'aluno123'),
('Mariana Oliveira', 'marianaoliveira@hotmail.com', 'aluno123'),
('Giovanna Ferreira', 'giovannaferreira@hotmail.com', 'aluno123'),
('Emily Silva', 'emilysilva@hotmail.com', 'aluno123'),
('Clara Cunha', 'claracunha@hotmail.com', 'aluno123'),
('Livia Rocha', 'liviarocha@hotmail.com', 'aluno123'),
('Victor Rocha', 'victorrocha@hotmail.com', 'aluno123'),
('Leonor Ferreira', 'leonorferreira@hotmail.com', 'aluno123'),
('Thiago Martins', 'thiagomartins@hotmail.com', 'aluno123'),
('Gabriel Souza Rocha', 'gabrielsouzarocha@hotmail.com', 'aluno123'),
('Antonio Fernandes', 'antoniofernandes@hotmail.com', 'aluno123'),
('Beatrice Costa', 'beatricecosta@gmail.com', 'aluno123'),
('Martim Martins', 'martimmartins@hotmail.com', 'aluno123'),
('Bianca Correira', 'biancacorreia@gmail.com', 'aluno123'),
('Pedro Quessada', 'pedro.quessada@usjt.br', 'aluno123'),
('Jamal Miranda', 'jamalmiranda@gmail.com', 'aluno123'),
('Arthur Augustinho', 'arthuraugustinho@gmail.com', 'aluno123'),
('Karen Beth Yara', 'karenbethyara@gmail.com', 'aluno123'),
('Gustavo Xinzhao', 'gustavoxinzhao@gmail.com', 'aluno123'),
('Guilherme Bodelon', 'guilhermebodelon@gmail.com', 'aluno123'),
('Yara Faria', 'yarafaria@hotmail.com', 'aluno123'),
('Jaqueline Pereira', 'jaquelinepereira@hotmail.com', 'aluno123'),
('Bruno Nascimento', 'brunonascimento@gmail.com', 'aluno123'),
('Paloma Pereira', 'palomapereira@gmail.com', 'aluno123'),
('Agata Silva', 'agatasilva@hotmail.com', 'aluno123'),
('Aurora Bruna', 'aurorabruna@gmail.com', 'aluno123'),
('Felipe Gustavo', 'felipegustavo@gmail.com', 'aluno123'),
('Katarina Camila', 'katarinacamila@gmai.com', 'aluno123'),
('Xeyen Varys', 'xeyenvarys@gmail.com', 'aluno123'),
('Rani Nisa', 'raninisa@gmail.com', 'aluno123'),
('Jenegar Arloc', 'jenegararloc@gmail.com', 'aluno123'),
('Pe Ethelste', 'pe@gmail.com', 'aluno123'),
('Sigano Risfer', 'sigano@gmail.com', 'aluno123'),
('Fernan Georald', 'fernangeorald@gmail.com', 'aluno123'),
('Ricon Kenar', 'reconkenar@gmail.com', 'aluno123'),
('Jose Fonseca', 'josefonseca@hotmail.com', 'adm123'),
('Zezinho da Silva', 'zezinhosilva@gmail.com', 'prof123'),
('Maria Aparecida', 'maria@hotmail.com', 'prof123'),
('Napoleao', 'napoleao@gmail.com', 'adm123'),
('Ayrton Sena', 'ayrtonsena@gmail.com', 'adm123'),
('Pedro', 'pedroquessad@gmail.com', 'prof123'),
('Camila', 'camila@hotmail.com', 'prof123'),
('Katarina', 'katarina@gmail.com', 'prof123'),
('Gabriela', 'gabriela@gmail.com', 'prof123'),
('Bruno', 'bruno@gmail.com', 'prof123'),
('Floriano', 'floriano@gmail.com', 'adm123'),
('Gabriel', 'gabriel@hotmail.com', 'adm123'),
('Cleo', 'pires@outlook.com', 'prof123'),
('Joana', 'joana@gmail.com', 'prof123'),
('Yara', 'karen_beth25@hotmail.com', 'prof123'),
('Beth', 'beth@gmail.com', 'prof123'),
('Mika Nisa', 'karen_mikanisa@hotmail.com', 'prof123');

 -- ----------------------------- ALUNO --------------------------------------------
 INSERT INTO `aluno`(aluno_id,ra) VALUES 

(1,'808046403'),
(2,'808032314'),
(3,'808069233'),
(4,'808064333'),
(5,'808047057'),
(6,'808068135'),
(7,'808060551'),
(8,'808058776'),
(9,'808013604'),
(10,'808070981'),
(11,'808012887'),
(12,'808028322'),
(13,'808060343'),
(14,'808092006'),
(15,'808014278'),
(16,'808010255'),
(17,'808071804'),
(18,'808091654'),
(19,'808032232'),
(20,'808064953'),
(21,'808013314'),
(22,'808079485'),
(23,'808021158'),
(24,'808044738'),
(25,'808032220'),
(26,'808013762'),
(27,'808053125'),
(28,'808068560'),
(29,'808018632'),
(30,'808066292'),
(31,'808019996'),
(32,'818119996'),
(33,'818119997'),
(34,'818119998'),
(35,'818119999'),
(36,'818120000'),
(37,'818120001'),
(38,'818120002'),
(39,'818120003'),
(40,'818120004'),
(41,'818120005'),
(42,'818120006'),
(43,'818120007'),
(44,'818120008'),
(45,'818120009'),
(46,'818120010'),
(47,'818120011'),
(48,'818120012'),
(49,'818120013'),
(50,'818120014'),
(51,'818120015'),
(52,'818120016'),
(53,'818120017'),
(54,'818120018'),
(55,'818120019'),
(56,'818120020'),
(57,'818120021'),
(58,'818120022'),
(59,'818120023'),
(60,'818120024'),
(61,'818120025'),
(62,'818120026'),
(63,'818120027'),
(64,'818120028'),
(65,'818120029'),
(66,'818120030'),
(67,'818120031'),
(68,'818120032'),
(69,'818120033'),
(70,'818120034'),
(71,'818120035'),
(72,'818120036'),
(73,'818120037'),
(74,'818120038'),
(75,'818120039'),
(76,'818120040'),
(77,'818120041'),
(78,'818120042'),
(79,'818120043'),
(80,'818120044');

-- -------------------------------- PROFESSOR ---------------------------------------
INSERT INTO `professor` (professor_id,administrador,matricula) VALUES

(81,1,'2018Ulife'),
(82,0,'2005usjt3374'),
(83,0,'2000anima8234'),
(84,1,'2010anima8364'),
(85,1,'2012usjt7356'),
(86,0,'973894092'),
(87,0,'12usjt12'),
(88,0,'13usjt13'),
(89,0,'14usjt14'),
(90,0,'15usjt15'),
(91,1,'admin12'),
(92,1,'admin13'),
(93,0,'16usjt16'),
(94,0,'17usjt17'),
(95,0,'18usjt18'),
(96,0,'19usjt19'),
(97,0,'20usjt20');

 
 -- ----------------------------- TEMA -----------------------------------------------
 INSERT INTO `tema` (dt_cadastro,titulo,introducao,requisitos)VALUES

('2018-03-12 01:01:01','Java','Introducao a Java Orientado a Objetos','Logica de Programacao'),
('2018-08-06 00:00:00','HTML','Introducao a HTML e CSS','Sem conhecimentos necessarios'),
('2019-03-04 00:00:00','Java Web','Elaboracao de projeto utilizando Java e HTML','Java e HTML'),
('2019-08-05 00:00:00','UXL','Introducao da experiencia do usuario','HTML,MySQL e CSS'),
('2018-03-19 00:00:00','Avaliacao Nutricional','Importancia do Diagnostico Nutricional','sem conhecimentos necessarios'),
('2018-08-13 00:00:00','Tecnologia de Alimentos','Tecnicas de Introducao','Quimica farmacia'),
('2019-03-11 00:00:00','Nutição Clinica','Diversas enferminades','Bioquimica, farmacia e quimica'),
('2018-03-05 00:00:00','Contabilidade Basica','Fundamento basico contabeis','Matematica'),
('2018-08-20 00:00:00','Matematica Financeira','Principios de calculo financeiro','Juros simples'),
('2019-03-25 00:00:00','Controladoria','Sistema Tributario Brasileiro','Ajustes de calculo, elisao');
 
 -- ----------------------------- TURMA ----------------------------------------------
 INSERT INTO `turma` (semestre_letivo, ano_letivo, sigla, tema_id) VALUES 

(1,2018,'NUT1CN-MCC',5),
(2,2018,'NUT2CN-MCC',6),
(1,2019,'NUT3CN-MCC',7),
(1,2018,'CC01CN-MCB',8),
(2,2018,'CCO2CN-MCB',9),
(1,2019,'CCO3CN-MCB',10),
(1,2018,'SIN1AN-MCA',1),
(2,2018,'SIN2AN-MCA',2),
(1,2019,'SIN3BN-MCA',3);

 -- ---------------------------- GRUPO ---------------------------------------------
INSERT INTO `grupo` (orientador_id, numero, nome) VALUES 

(82,1,'Grupo 1'),
(82,2,'Grupo 2'),
(82,3,'Grupo 3'),
(82,4,'Grupo 4'),
(82,5,'Grupo 5'),
(82,6,'Grupo 6'),
(82,7,'Grupo 7'),
(83,8,'Grupo 1'),
(83,9,'Grupo 2'),
(83,10,'Grupo 3'),
(83,11,'Grupo 4'),
(83,12,'Grupo 5'),
(83,13,'Grupo 6'),
(83,14,'Grupo 7'),
(81,15,'Grupo 1'),
(81,16,'Grupo 2'),
(81,17,'Grupo 3'),
(81,18,'Grupo 4'),
(81,19,'Grupo 5'),
(81,20,'Grupo 6'),
(81,21,'Grupo 7'),
(84,22,'Grupo 1'),
(84,23,'Grupo 2'),
(84,24,'Grupo 3'),
(84,25,'Grupo 4'),
(84,26,'Grupo 5'),
(84,27,'Grupo 6'),
(85,28,'Grupo 1'),
(85,29,'Grupo 2'),
(85,30,'Grupo 3'),
(85,31,'Grupo 4'),
(85,32,'Grupo 5'),
(85,33,'Grupo 6'),
(86,34,'Grupo 1'),
(86,35,'Grupo 2'),
(86,36,'Grupo 3'),
(86,37,'Grupo 4'),
(86,38,'Grupo 5'),
(86,39,'Grupo 6'),
(97,40,'Grupo 1'),
(97,41,'Grupo 2'),
(97,42,'Grupo 3'),
(97,43,'Grupo 4'),
(96,44,'Grupo 1'),
(96,45,'Grupo 2'),
(96,46,'Grupo 3'),
(96,47,'Grupo 4'),
(97,48,'Grupo 1'),
(97,49,'Grupo 2'),
(97,50,'Grupo 3'),
(97,51,'Grupo 4');
 -- --------------------------- TURMA_ALUNO ---------------------------------------

INSERT INTO `turma_aluno` (Aluno_id, turma_id,grupo_id) VALUES 

(1,1,1),
(2,1,1),
(3,1,1),
(4,1,1),
(5,1,2),
(6,1,2),
(7,1,2),
(8,1,2),
(9,1,2),
(10,1,3),
(11,1,3),
(12,1,3),
(13,1,3),
(14,1,3),
(15,1,4),
(16,1,4),
(17,1,4),
(18,1,4),
(21,1,4),
(19,1,5),
(20,1,5),
(22,1,5),
(23,1,5),
(24,1,6),
(25,1,6),
(26,1,6),
(27,1,6),
(28,1,7),
(29,1,7),
(30,1,7),
(31,1,7),
(1,2,8),
(2,2,8),
(3,2,8),
(4,2,8),
(5,2,9),
(10,2,9),
(15,2,9),
(19,2,9),
(24,2,9),
(6,2,10),
(11,2,10),
(16,2,10),
(20,2,10),
(25,2,10),
(7,2,11),
(12,2,11),
(17,2,11),
(22,2,11),
(21,2,11),
(8,2,12),
(13,2,12),
(18,2,12),
(23,2,12),
(9,2,13),
(14,2,13),
(26,2,13),
(27,2,13),
(28,2,14),
(29,2,14),
(30,2,14),
(31,2,14),
(1,3,15),
(8,3,15),
(13,3,15),
(4,3,15),
(2,3,16),
(3,3,16),
(23,3,16),
(18,3,16),
(5,3,17),
(6,3,17),
(10,3,17),
(11,3,17),
(15,3,17),
(16,3,18),
(19,3,18),
(24,3,18),
(20,3,18),
(25,3,18),
(7,3,19),
(12,3,19),
(17,3,19),
(9,3,19),
(14,3,19),
(22,3,20),
(21,3,20),
(26,3,20),
(27,3,20),
(28,3,21),
(29,3,21),
(30,3,21),
(31,3,21),
(32,4,22),
(33,4,22),
(34,4,22),
(35,4,22),
(36,4,23),
(37,4,23),
(38,4,23),
(39,4,23),
(40,4,23),
(41,4,24),
(42,4,24),
(43,4,24),
(44,4,24),
(45,4,24),
(46,4,25),
(47,4,25),
(48,4,25),
(49,4,25),
(50,4,25),
(51,4,26),
(52,4,26),
(53,4,26),
(54,4,26),
(55,4,26),
(56,4,27),
(57,4,27),
(58,4,27),
(59,4,27),
(32,5,28),
(33,5,28),
(34,5,28),
(35,5,28),
(36,5,29),
(37,5,29),
(38,5,29),
(39,5,29),
(40,5,29),
(41,5,30),
(42,5,30),
(43,5,30),
(44,5,30),
(45,5,30),
(46,5,31),
(47,5,31),
(48,5,31),
(49,5,31),
(50,5,31),
(51,5,32),
(52,5,32),
(53,5,32),
(54,5,32),
(55,5,32),
(56,5,33),
(57,5,33),
(58,5,33),
(59,5,33),
(32,6,34),
(36,6,34),
(41,6,34),
(46,6,34),
(33,6,35),
(37,6,35),
(42,6,35),
(47,6,35),
(51,6,35),
(34,6,36),
(38,6,36),
(43,6,36),
(39,6,36),
(44,6,36),
(35,6,37),
(40,6,37),
(45,6,37),
(57,6,37),
(52,6,37),
(58,6,38),
(53,6,38),
(54,6,38),
(55,6,38),
(47,6,38),
(48,6,39),
(49,6,39),
(50,6,39),
(56,6,39),
(59,6,39),
(60,7,40),
(61,7,40),
(62,7,40),
(63,7,40),
(64,7,40),
(65,7,41),
(66,7,41),
(67,7,41),
(68,7,41),
(69,7,41),
(70,7,42),
(71,7,42),
(72,7,42),
(73,7,42),
(74,7,42),
(75,7,43),
(76,7,43),
(77,7,43),
(78,7,43),
(79,7,43),
(80,7,43),
(60,8,44),
(65,8,44),
(70,8,44),
(75,8,44),
(61,8,44),
(62,8,45),
(66,8,45),
(71,8,45),
(76,8,45),
(63,8,45),
(64,8,46),
(67,8,46),
(72,8,46),
(77,8,46),
(68,8,46),
(69,8,47),
(73,8,47),
(74,8,47),
(78,8,47),
(79,8,47),
(80,8,47),
(60,9,48),
(62,9,48),
(64,9,48),
(65,9,48),
(66,9,48),
(67,9,49),
(70,9,49),
(71,9,49),
(72,9,49),
(75,9,49),
(76,9,50),
(77,9,50),
(61,9,50),
(63,9,50),
(68,9,50),
(69,9,51),
(73,9,51),
(74,9,51),
(78,9,51),
(79,9,51),
(80,9,51);

-- ----------------------------- BANCA ----------------------------------------
INSERT INTO `banca` (grupo_id, data, sala) VALUES

 (1,'2018-06-11 00:00:00','102A'),
 (2,'2018-06-11 00:00:00','102A'),
 (3,'2018-06-11 00:00:00','102A'),
 (4,'2018-06-11 00:00:00','102A'),
 (5,'2018-06-11 00:00:00','102A'), 
 (6,'2018-06-11 00:00:00','102A'),
 (7,'2018-06-11 00:00:00','102A'),
 (8,'2018-11-28 00:00:00','103E'),
 (9,'2018-11-28 00:00:00','103E'),
 (10,'2018-11-28 00:00:00','103E'),
 (11,'2018-11-28 00:00:00','103E'),
 (12,'2018-11-28 00:00:00','103E'),
 (13,'2018-11-28 00:00:00','103E'),
 (14,'2018-11-28 00:00:00','103E'),
 (15,'2019-06-24 00:00:00','102A'),
 (16,'2019-06-24 00:00:00','102A'),
 (17,'2019-06-24 00:00:00','102A'),
 (18,'2019-06-24 00:00:00','102A'),
 (19,'2019-06-24 00:00:00','102A'),
 (20,'2019-06-24 00:00:00','102A'),
 (21,'2019-06-24 00:00:00','102A'),
 (22,'2018-06-12 00:00:00','102A'),
 (23,'2018-06-12 00:00:00','102A'),
 (24,'2018-06-12 00:00:00','102A'),
 (25,'2018-06-12 00:00:00','102A'),
 (26,'2018-06-12 00:00:00','102A'),
 (27,'2018-06-12 00:00:00','102A'),
 (28,'2018-11-29 00:00:00','103E'),
 (29,'2018-11-29 00:00:00','103E'),
 (30,'2018-11-29 00:00:00','103E'),
 (31,'2018-11-29 00:00:00','103E'),
 (32,'2018-11-29 00:00:00','103E'),
 (33,'2018-11-29 00:00:00','103E'),
 (34,'2019-06-25 00:00:00','REITORIA'),
 (35,'2019-06-25 00:00:00','REITORIA'),
 (36,'2019-06-25 00:00:00','REITORIA'),
 (37,'2019-06-25 00:00:00','REITORIA'),
 (38,'2019-06-25 00:00:00','REITORIA'),
 (39,'2019-06-25 00:00:00','REITORIA'),
 (40,'2018-06-13 00:00:00','106B'),
 (41,'2018-06-13 00:00:00','106B'),
 (42,'2018-06-13 00:00:00','106B'),
 (43,'2018-06-13 00:00:00','106B'),
 (44,'2018-11-30 00:00:00','REITORIA'),
 (45,'2018-11-30 00:00:00','REITORIA'),
 (46,'2018-11-30 00:00:00','REITORIA'),
 (47,'2018-11-30 00:00:00','REITORIA');
 
-- ----------------------------- PROFESSOR_BANCA ------------------------------
INSERT INTO `professores_banca` (banca_id, professor_id, avaliacao)VALUES 

(1,87,50.00),
(1,88,30.00),
(1,97,40.00),
(2,87,90.00),
(2,88,70.00),
(2,97,80.00),
(3,87,20.00),
(3,88,30.00),
(3,97,10.00),
(4,87,80.00),
(4,88,70.00),
(4,97,60.00),
(5,87,82.00),
(5,88,90.00),
(5,97,70.00),
(6,87,90.00),
(6,88,90.00),
(6,97,90.00),
(7,87,60.00),
(7,88,50.00),
(7,97,40.00),
(8,89,70.00),
(8,90,70.00),
(8,86,70.00),
(9,89,60.00),
(9,90,60.00),
(9,86,60.00),
(10,89,69.00),
(10,90,58.00),
(10,86,70.00),
(11,89,45.00),
(11,90,61.00),
(11,86,55.00),
(12,89,55.00),
(12,90,55.00),
(12,86,55.00),
(13,89,40.00),
(13,90,40.00),
(13,86,40.00),
(14,89,30.00),
(14,90,20.00),
(14,86,10.00),
(15,91,33.00),
(15,92,45.00),
(15,93,33.00),
(16,91,25.00),
(16,92,30.00),
(16,93,33.00),
(17,91,51.00),
(17,92,52.00),
(17,93,53.00),
(18,91,40.00),
(18,92,50.00),
(18,93,40.00),
(19,91,60.00),
(19,92,70.00),
(19,93,70.00),
(20,91,80.00),
(20,92,80.00),
(20,93,80.00),
(21,91,80.00),
(21,92,70.00),
(21,93,70.00),
(22,94,40.00),
(22,95,40.00),
(22,96,40.00),
(23,94,40.00),
(23,95,40.00),
(23,96,40.00),
(24,94,50.00),
(24,95,50.00),
(24,96,50.00),
(25,94,70.00),
(25,95,70.00),
(25,96,70.00),
(26,94,60.00),
(26,95,62.00),
(26,96,63.00),
(27,94,51.00),
(27,95,58.00),
(27,96,53.00),
(28,84,44.00),
(28,86,55.00),
(28,97,66.00),
(29,84,33.00),
(29,86,44.00),
(29,97,55.00),
(30,84,90.00),
(30,86,90.00),
(30,97,90.00),
(31,84,80.00),
(31,86,73.00),
(31,97,81.00),
(32,84,66.00),
(32,86,65.00),
(32,97,64.00),
(33,84,70.00),
(33,86,75.00),
(33,97,71.00),
(34,84,80.00),
(34,85,81.00),
(34,97,82.00),
(35,84,75.00),
(35,85,75.00),
(35,97,75.00),
(36,84,80.00),
(36,85,82.00),
(36,97,81.00),
(37,84,90.00),
(37,85,90.00),
(37,97,100.00),
(38,84,99.00),
(38,85,100.00),
(38,97,34.00),
(39,84,35.00),
(39,85,55.00),
(39,97,34.00),
(40,86,36.00),
(40,89,40.00),
(40,90,50.00),
(41,86,52.00),
(41,89,53.00),
(41,90,70.00),
(42,86,70.00),
(42,89,70.00),
(42,90,80.00),
(43,86,70.00),
(43,89,70.00),
(43,90,70.00),
(44,84,80.00),
(44,95,80.00),
(44,97,80.00),
(45,84,95.00),
(45,95,90.00),
(45,97,91.00),
(46,84,63.00),
(46,95,70.00),
(46,97,68.00),
(47,84,50.00),
(47,95,50.00),
(47,97,50.00);
-- ----------------------------- ATIVIDADE -----------------------------------
INSERT INTO `atividade` (tema_id,numero,descricao,formato_entrega,dt_inicio,dt_fim) VALUES 

(1,1,'Conceitos','GitHub','2018-03-13 00:00:00','2018-03-23 00:00:00'),
(1,2,'Elaboração','GitHub','2018-03-26 00:00:00','2018-04-06 00:00:00'),
(1,3,'Aplicação de exemplos','GitHub','2018-04-09 00:00:00','2018-04-27 00:00:00'),
(1,4,'Finalização','GitHub','2018-05-01 00:00:00','2018-05-11 00:00:00'),
(1,5,'Pré-Banca','GitHub','2018-05-15 00:00:00','2018-05-01 00:00:00'),
(2,1,'Introdução','GitHub','2018-08-13 00:00:00','2018-08-24 00:00:00'),
(2,2,'Elaboração do Layout','GitHub','2018-08-27 00:00:00','2018-09-07 00:00:00'),
(2,3,'Codificar em HTML e CSS','GitHub','2018-09-10 00:00:00','2018-09-21 00:00:00'),
(2,4,'Aplicar JavaScript','GitHub','2018-09-24 00:00:00','2018-10-08 00:00:00'),
(2,5,'Finalizar','GitHub','2018-10-08 00:00:00','2018-10-22 00:00:00'),
(2,6,'Pré-Banca','GitHub','2018-10-22 00:00:00','2018-11-12 00:00:00'),
(3,1,'Introdução','GitHub','2019-03-05 00:00:00','2019-03-08 00:00:00'),
(3,2,'explicação Java','GitHub','2019-03-12 00:00:00','2019-03-22 00:00:00'),
(3,3,'Elaboração do modal','GitHub','2019-03-26 00:00:00','2019-03-29 00:00:00'),
(3,4,'DAO','GitHub','2019-04-02 00:00:00','2019-04-12 00:00:00'),
(3,5,'Aplicação do Controller','Github','2019-04-16 00:00:00','2019-04-26 00:00:00'),
(3,6,'Aplicação do Filter','GitHub','2019-04-30 00:00:00','2019-05-10 00:00:00'),
(3,7,'Finalizar','GitHub','2019-05-14 00:00:00','2019-05-24 00:00:00'),
(3,8,'Pré-Banca','GitHub','2019-05-28 00:00:00','2019-06-14 00:00:00'),
(4,1,'Conceitos','GitHub','2019-03-11 00:00:00','2019-03-29 00:00:00'),
(4,2,'Aplicar UXL','GitHub','2019-04-01 00:00:00','2019-04-12 00:00:00'),
(4,3,'Finalizar','GitHub','2019-04-15 00:00:00','2019-04-26 00:00:00'),
(4,4,'Pré-Banca','GitHub','2019-04-29 00:00:00','2019-05-23 00:00:00'),
(5,1,'Conceitos','PDF','2018-03-19 00:00:00','2018-03-30 00:00:00'),
(5,2,'Elaboração das Pesquisas','PDF','2018-04-03 00:00:00','2018-04-20 00:00:00'),
(5,3,'Planejamento do Documento','PDF','2018-04-24 00:00:00','2018-05-11 00:00:00'),
(5,4,'Aplicação','PDF','2018-05-15 00:00:00','2018-05-25 00:00:00'),
(5,5,'Pré-Banca','PDF','2018-05-29 00:00:00','2018-06-08 00:00:00'),
(6,1,'Efeitos na Sociedade','arquivo de texto','2018-08-13 00:00:00','2018-09-07 00:00:00'),
(6,2,'Aplicar na Documentação','arquivo de texto','2018-09-10 00:00:00','2018-11-02 00:00:00'),
(7,1,'Conceitos/Pesquisa','arquivo de texto','2019-03-11 00:00:00','2019-04-05 00:00:00'),
(7,2,'Aplicação no Documento','arquivo de texto','2019-04-08 00:00:00','2019-05-10 00:00:00'),
(7,3,'Pré-Banca','arquivo de texto','2019-05-13 00:00:00','2019-05-31 00:00:00'),
(8,1,'Calculo Simples','arquivo de texto','2018-03-12 00:00:00','2018-03-30 00:00:00'),
(8,2,'Elaboração do Documento','arquivo de texto','2018-04-02 00:00:00','2018-05-11 00:00:00'),
(8,3,'Pré-Banca','arquivo de texto','2018-05-14 00:00:00','2018-05-31 00:00:00'),
(9,1,'Conceitos Basicos','arquivo de texto','2018-08-20 00:00:00','2018-09-14 00:00:00'),
(9,2,'Finalizar','arquivo de texto','2018-09-18 00:00:00','2018-10-12 00:00:00'),
(9,3,'Pré-Banca','arquivo de texto','2018-10-16 00:00:00','2018-11-09 00:00:00'),
(10,1,'Diferença no extrangeiro','arquivo de texto','2019-03-25 00:00:00','2019-05-03 00:00:00'),
(10,2,'Finalizar','arquivo de texto','2019-05-06 00:00:00','2019-05-24 00:00:00'),
(10,3,'Pré-Banca','arquivo de texto','2019-05-27 00:00:00','2019-06-07 00:00:00');

-- ----------------------------- ENTREGA ----------------------------------------
INSERT INTO `entrega` (grupo_id, atividade_id,link,dt_cadastro)VALUES 

(1,24,'PDF','2018-03-23 00:00:00'),
(2,24,'PDF','2018-03-23 00:00:00'),
(3,24,'PDF','2018-03-23 00:00:00'),
(4,24,'PDF','2018-03-23 00:00:00'),
(5,24,'PDF','2018-03-23 00:00:00'),
(6,24,'PDF','2018-03-23 00:00:00'),
(7,24,'PDF','2018-03-23 00:00:00'),
(1,25,'PDF','2018-04-18 00:00:00'),
(2,25,'PDF','2018-04-18 00:00:00'),
(3,25,'PDF','2018-04-18 00:00:00'),
(4,25,'PDF','2018-04-18 00:00:00'),
(5,25,'PDF','2018-04-18 00:00:00'),
(6,25,'PDF','2018-04-18 00:00:00'),
(7,25,'PDF','2018-04-18 00:00:00'),
(1,26,'PDF','2018-04-28 00:00:00'),
(2,26,'PDF','2018-04-28 00:00:00'),
(3,26,'PDF','2018-04-28 00:00:00'),
(4,26,'PDF','2018-04-28 00:00:00'),
(5,26,'PDF','2018-04-28 00:00:00'),
(6,26,'PDF','2018-04-28 00:00:00'),
(7,26,'PDF','2018-04-28 00:00:00'),
(1,27,'PDF','2018-05-20 00:00:00'),
(2,27,'PDF','2018-05-20 00:00:00'),
(3,27,'PDF','2018-05-20 00:00:00'),
(4,27,'PDF','2018-05-20 00:00:00'),
(5,27,'PDF','2018-05-20 00:00:00'),
(6,27,'PDF','2018-05-20 00:00:00'),
(7,27,'PDF','2018-05-20 00:00:00'),
(1,28,'PDF','2018-06-05 00:00:00'),
(2,28,'PDF','2018-06-05 00:00:00'),
(3,28,'PDF','2018-06-05 00:00:00'),
(4,28,'PDF','2018-06-05 00:00:00'),
(5,28,'PDF','2018-06-05 00:00:00'),
(6,28,'PDF','2018-06-05 00:00:00'),
(7,28,'PDF','2018-06-05 00:00:00'),
(8,29,'PDF','2018-08-27 00:00:00'),
(9,29,'PDF','2018-08-27 00:00:00'),
(10,29,'PDF','2018-08-27 00:00:00'),
(11,29,'PDF','2018-08-27 00:00:00'),
(12,29,'PDF','2018-08-27 00:00:00'),
(13,29,'PDF','2018-08-27 00:00:00'),
(14,29,'PDF','2018-08-27 00:00:00'),
(8,30,'PDF','2018-09-30 00:00:00'),
(9,30,'PDF','2018-09-30 00:00:00'),
(10,30,'PDF','2018-09-30 00:00:00'),
(11,30,'PDF','2018-09-30 00:00:00'),
(12,30,'PDF','2018-09-30 00:00:00'),
(13,30,'PDF','2018-09-30 00:00:00'),
(14,30,'PDF','2018-09-30 00:00:00'),
(15,31,'PDF','2019-04-01 00:00:00'),
(16,31,'PDF','2019-04-01 00:00:00'),
(17,31,'PDF','2019-04-01 00:00:00'),
(18,31,'PDF','2019-04-01 00:00:00'),
(19,31,'PDF','2019-04-01 00:00:00'),
(20,31,'PDF','2019-04-01 00:00:00'),
(21,31,'PDF','2019-04-01 00:00:00'), 
(15,32,'PDF','2019-05-05 00:00:00'),
(16,32,'PDF','2019-05-05 00:00:00'),
(17,32,'PDF','2019-05-05 00:00:00'),
(18,32,'PDF','2019-05-05 00:00:00'),
(19,32,'PDF','2019-05-05 00:00:00'),
(20,32,'PDF','2019-05-05 00:00:00'),
(21,32,'PDF','2019-05-05 00:00:00'),
(15,33,'PDF','2019-05-28 00:00:00'),
(16,33,'PDF','2019-05-28 00:00:00'),
(17,33,'PDF','2019-05-28 00:00:00'),
(18,33,'PDF','2019-05-28 00:00:00'),
(19,33,'PDF','2019-05-28 00:00:00'),
(20,33,'PDF','2019-05-28 00:00:00'),
(21,33,'PDF','2019-05-28 00:00:00'),
(22,34,'PDF','2018-03-28 00:00:00'),
(23,34,'PDF','2018-03-28 00:00:00'),
(24,34,'PDF','2018-03-28 00:00:00'),
(25,34,'PDF','2018-03-28 00:00:00'),
(26,34,'PDF','2018-03-28 00:00:00'),
(27,34,'PDF','2018-03-28 00:00:00'),
(22,35,'PDF','2018-05-10 00:00:00'),
(23,35,'PDF','2018-05-10 00:00:00'),
(24,35,'PDF','2018-05-10 00:00:00'),
(25,35,'PDF','2018-05-10 00:00:00'),
(26,35,'PDF','2018-05-10 00:00:00'),
(27,35,'PDF','2018-05-10 00:00:00'),
(22,36,'PDF','2018-05-29 00:00:00'),
(23,36,'PDF','2018-05-29 00:00:00'),
(24,36,'PDF','2018-05-29 00:00:00'),
(25,36,'PDF','2018-05-29 00:00:00'),
(26,36,'PDF','2018-05-29 00:00:00'),
(27,36,'PDF','2018-05-29 00:00:00'),
(28,37,'PDF','2018-09-10 00:00:00'),
(29,37,'PDF','2018-09-10 00:00:00'),
(30,37,'PDF','2018-09-10 00:00:00'),
(31,37,'PDF','2018-09-10 00:00:00'),
(32,37,'PDF','2018-09-10 00:00:00'),
(33,37,'PDF','2018-09-10 00:00:00'),
(28,38,'PDF','2018-10-10 00:00:00'),
(29,38,'PDF','2018-10-10 00:00:00'),
(30,38,'PDF','2018-10-10 00:00:00'),
(31,38,'PDF','2018-10-10 00:00:00'),
(32,38,'PDF','2018-10-10 00:00:00'),
(33,38,'PDF','2018-10-10 00:00:00'),
(28,39,'PDF','2018-11-05 00:00:00'),
(29,39,'PDF','2018-11-05 00:00:00'),
(30,39,'PDF','2018-11-05 00:00:00'),
(31,39,'PDF','2018-11-05 00:00:00'),
(32,39,'PDF','2018-11-05 00:00:00'),
(33,39,'PDF','2018-11-05 00:00:00'),
(34,40,'PDF','2019-05-02 00:00:00'),
(35,40,'PDF','2019-05-02 00:00:00'),
(36,40,'PDF','2019-05-02 00:00:00'),
(37,40,'PDF','2019-05-02 00:00:00'),
(38,40,'PDF','2019-05-02 00:00:00'),
(39,40,'PDF','2019-05-02 00:00:00'),
(34,41,'PDF','2019-05-20 00:00:00'),
(35,41,'PDF','2019-05-20 00:00:00'),
(36,41,'PDF','2019-05-20 00:00:00'),
(37,41,'PDF','2019-05-20 00:00:00'),
(38,41,'PDF','2019-05-20 00:00:00'),
(39,41,'PDF','2019-05-20 00:00:00'),
(34,42,'PDF','2019-06-05 00:00:00'),
(35,42,'PDF','2019-06-05 00:00:00'),
(36,42,'PDF','2019-06-05 00:00:00'),
(37,42,'PDF','2019-06-05 00:00:00'),
(38,42,'PDF','2019-06-05 00:00:00'),
(39,42,'PDF','2019-06-05 00:00:00'),
(40,1,'PDF','2018-03-20 00:00:00'),
(41,1,'PDF','2018-03-20 00:00:00'),
(42,1,'PDF','2018-03-20 00:00:00'),
(43,1,'PDF','2018-03-20 00:00:00'),
(40,2,'PDF','2018-04-03 00:00:00'),
(41,2,'PDF','2018-04-03 00:00:00'),
(42,2,'PDF','2018-04-03 00:00:00'),
(43,2,'PDF','2018-04-03 00:00:00'),
(40,3,'https://github.com/KarenBeth/Pais','2018-04-23 00:00:00'),
(41,3,'https://github.com/KarenBeth/Pais','2018-04-23 00:00:00'),
(42,3,'https://github.com/KarenBeth/Pais','2018-04-23 00:00:00'),
(43,3,'https://github.com/KarenBeth/Pais','2018-04-23 00:00:00'),
(40,4,'https://github.com/KarenBeth/Pais','2018-05-09 00:00:00'),
(41,4,'https://github.com/KarenBeth/Pais','2018-05-09 00:00:00'),
(42,4,'https://github.com/KarenBeth/Pais','2018-05-09 00:00:00'),
(43,4,'https://github.com/KarenBeth/Pais','2018-05-09 00:00:00'),
(40,5,'https://github.com/KarenBeth/Pais','2018-04-29 00:00:00'),
(41,5,'https://github.com/KarenBeth/Pais','2018-04-29 00:00:00'),
(42,5,'https://github.com/KarenBeth/Pais','2018-04-29 00:00:00'),
(43,5,'https://github.com/KarenBeth/Pais','2018-04-29 00:00:00'),
(44,6,'https://github.com/KarenBeth/Pais','2018-08-20 00:00:00'),
(45,6,'https://github.com/KarenBeth/Pais','2018-08-20 00:00:00'),
(46,6,'https://github.com/KarenBeth/Pais','2018-08-20 00:00:00'),
(47,6,'https://github.com/KarenBeth/Pais','2018-08-20 00:00:00'),
(44,7,'https://github.com/KarenBeth/Pais','2018-09-05 00:00:00'),
(45,7,'https://github.com/KarenBeth/Pais','2018-09-05 00:00:00'),
(46,7,'https://github.com/KarenBeth/Pais','2018-09-05 00:00:00'),
(47,7,'https://github.com/KarenBeth/Pais','2018-09-05 00:00:00'),
(44,8,'https://github.com/KarenBeth/Pais','2018-09-19 00:00:00'),
(45,8,'https://github.com/KarenBeth/Pais','2018-09-19 00:00:00'),
(46,8,'https://github.com/KarenBeth/Pais','2018-09-19 00:00:00'),
(47,8,'https://github.com/KarenBeth/Pais','2018-09-19 00:00:00'),
(44,9,'https://github.com/KarenBeth/Pais','2018-10-05 00:00:00'),
(45,9,'https://github.com/KarenBeth/Pais','2018-10-05 00:00:00'),
(46,9,'https://github.com/KarenBeth/Pais','2018-10-05 00:00:00'),
(47,9,'https://github.com/KarenBeth/Pais','2018-10-05 00:00:00'),
(44,10,'https://github.com/KarenBeth/Pais','2018-10-20 00:00:00'),
(45,10,'https://github.com/KarenBeth/Pais','2018-10-20 00:00:00'),
(46,10,'https://github.com/KarenBeth/Pais','2018-10-20 00:00:00'),
(47,10,'https://github.com/KarenBeth/Pais','2018-10-20 00:00:00'),
(44,11,'https://github.com/KarenBeth/Pais','2018-11-09 00:00:00'),
(45,11,'https://github.com/KarenBeth/Pais','2018-11-09 00:00:00'),
(46,11,'https://github.com/KarenBeth/Pais','2018-11-09 00:00:00'),
(47,11,'https://github.com/KarenBeth/Pais','2018-11-09 00:00:00'),
(48,12,'https://github.com/KarenBeth/Pais','2019-03-05 00:00:00'),
(49,12,'https://github.com/KarenBeth/Pais','2019-03-05 00:00:00'),
(50,12,'https://github.com/KarenBeth/Pais','2019-03-05 00:00:00'),
(51,12,'https://github.com/KarenBeth/Pais','2019-03-05 00:00:00'),
(48,13,'https://github.com/KarenBeth/Pais','2019-03-20 00:00:00'),
(49,13,'https://github.com/KarenBeth/Pais','2019-03-20 00:00:00'),
(50,13,'https://github.com/KarenBeth/Pais','2019-03-20 00:00:00'),
(51,13,'https://github.com/KarenBeth/Pais','2019-03-20 00:00:00'),
(48,14,'https://github.com/KarenBeth/Pais','2019-03-28 00:00:00'),
(49,14,'https://github.com/KarenBeth/Pais','2019-03-28 00:00:00'),
(50,14,'https://github.com/KarenBeth/Pais','2019-03-28 00:00:00'),
(51,14,'https://github.com/KarenBeth/Pais','2019-03-28 00:00:00'),
(48,15,'https://github.com/KarenBeth/Pais','2019-04-10 00:00:00'),
(49,15,'https://github.com/KarenBeth/Pais','2019-04-10 00:00:00'),
(50,15,'https://github.com/KarenBeth/Pais','2019-04-10 00:00:00'),
(51,15,'https://github.com/KarenBeth/Pais','2019-04-10 00:00:00'),
(48,16,'https://github.com/KarenBeth/Pais','2019-04-24 00:00:00'),
(49,16,'https://github.com/KarenBeth/Pais','2019-04-24 00:00:00'),
(50,16,'https://github.com/KarenBeth/Pais','2019-04-24 00:00:00'),
(51,16,'https://github.com/KarenBeth/Pais','2019-04-24 00:00:00'),
(48,17,'https://github.com/KarenBeth/Pais','2019-05-05 00:00:00'),
(49,17,'https://github.com/KarenBeth/Pais','2019-05-05 00:00:00'),
(50,17,'https://github.com/KarenBeth/Pais','2019-05-05 00:00:00'),
(51,17,'https://github.com/KarenBeth/Pais','2019-05-05 00:00:00'),
(48,18,'https://github.com/KarenBeth/Pais','2019-05-20 00:00:00'),
(49,18,'https://github.com/KarenBeth/Pais','2019-05-20 00:00:00'),
(50,18,'https://github.com/KarenBeth/Pais','2019-05-20 00:00:00'),
(51,18,'https://github.com/KarenBeth/Pais','2019-05-20 00:00:00'),
(48,19,'https://github.com/KarenBeth/Pais','2019-06-10 00:00:00'),
(49,19,'https://github.com/KarenBeth/Pais','2019-06-10 00:00:00'),
(50,19,'https://github.com/KarenBeth/Pais','2019-06-10 00:00:00'),
(51,19,'https://github.com/KarenBeth/Pais','2019-06-10 00:00:00');
-- ------------------------------ AVALIAÇÃO ------------------------------------
INSERT INTO `avaliacao` (entrega_id, turma_aluno_id, nota, dt_avaliacao, comentarios) VALUES 
(1,19,90.00,'2019-03-25 00:00:00','boa apresentação'),
(1,20,90.00,'2019-03-25 00:00:00','boa apresentação'),
(1,22,90.00,'2019-03-25 00:00:00','boa apresentação'),
(1,23,90.00,'2019-03-25 00:00:00','boa apresentação');
