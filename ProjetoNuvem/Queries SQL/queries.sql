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
  `UltimoDia` varchar(1024) DEFAULT NULL,
  `MediaMeses` varchar(1024) DEFAULT NULL,
  `MediaDiasDaSemana` varchar(1024) DEFAULT NULL,  
  `SupermercadoId` INT NULL,
  PRIMARY KEY (`Id`),
  FOREIGN KEY (`SupermercadoId`) REFERENCES Supermercado(`Id`));
  
INSERT INTO Supermercado VALUES (1, 'Labsoft', 'Laboratorio de Software', 22, 'Poli', 'Poli-Eletrica', -23.55, -45.00);
INSERT INTO Supermercado VALUES (2, 'Sala1', 'Sala de aulas', 50, 'Poli', 'Poli-Eletrica', -23.50, -45.55);
INSERT INTO Historico VALUES (1, '0, 0, 0, 0, 0, 0, 1, 2, 10, 12, 15, 16, 0, 1, 13, 15, 17, 20, 5, 4, 3, 0, 0, 0', '10, 12, 10, 12, 13, 14, 13, 14, 16, 5, 20, 20', '2,3,2,3,2,3,7', 1);
INSERT INTO Historico VALUES (2, '10, 0, 0, 0, 0, 0, 1, 2, 10, 12, 15, 16, 0, 1, 13, 15, 17, 20, 5, 4, 3, 0, 0, 0', '10, 12, 10, 12, 13, 14, 13, 14, 16, 5, 20, 40', '2,3,2,3,2,3,7', 2);


