CREATE TABLE `Supermercado` (
  `Id` int(11) NOT NULL,
  `Nome` varchar(45) DEFAULT NULL,
  `Descricao` varchar(45) DEFAULT NULL,
  `LotacaoMaxima` int(11) DEFAULT NULL,
  `Rede` varchar(45) DEFAULT NULL,
  `Endereco` varchar(45) DEFAULT NULL,
  `Latitude` double DEFAULT NULL,
  `Longitude` double DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `schemalotation2`.`HistoricoMensalPorAno` (
  `Id` INT NOT NULL,
  `Mes` INT NULL,
  `Ano` INT NULL,
  `SupermercadoId` INT NULL,
  `TimeStamp` TIMESTAMP(10) NULL,
  PRIMARY KEY (`Id`),
  FOREIGN KEY (`SupermercadoId`) REFERENCES Supermercado(`Id`));
  
CREATE TABLE `schemalotation2`.`HistoricoHorarioPorDia` (
  `Id` INT NOT NULL,
  `DiaDaSemana` INT NULL,
  `Hora` INT NULL,
  `SupermercadoId` INT NULL,
  `TimeStamp` TIMESTAMP(10) NULL,
  PRIMARY KEY (`Id`));
  
CREATE TABLE `schemalotation2`.`HistoricoDiarioPorMesPorAno` (
  `Id` INT NOT NULL,
  `Dia` INT NULL,
  `Mes` INT NULL,
  `Ano` INT NULL,
  `SupermercadoId` INT NULL,
  `TimeStamp` TIMESTAMP(10) NULL,
  PRIMARY KEY (`Id`));
  
  CREATE TABLE `schemalotation2`.`Historico` (
  `Id` INT NOT NULL,
  `Dia` INT NULL,
  `Mes` INT NULL,
  `Ano` INT NULL,
  `SupermercadoId` INT NULL,
  `TimeStamp` TIMESTAMP(10) NULL,
  PRIMARY KEY (`Id`));
  
INSERT INTO Supermercado VALUES (1, 'Labsoft', 'Laboratorio de Software', 50, 'Poli', 'Poli-Eletrica', -23.55, -45.00);
INSERT INTO Supermercado VALUES (2, 'Sala1', 'Sala de aulas', 20, 'Poli', 'Poli-Eletrica', -23.50, -45.55);


