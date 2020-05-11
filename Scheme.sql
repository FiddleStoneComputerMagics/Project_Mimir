CREATE TABLE `Faculty` (
  `dean` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `departaments` varchar(255) NOT NULL,
  `rooms` varchar(255) NOT NULL
);

CREATE TABLE `Departament` (
  `headOfDepartament` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `specialization` varchar(255) NOT NULL,
  `faculty` varchar(255) NOT NULL,
  `lablratories` varchar(255) NOT NULL,
  `rooms` varchar(255) NOT NULL
);

CREATE TABLE `Laboratory` (
  `responsablePerson` varchar(255) NOT NULL,
  `departament` varchar(255) NOT NULL,
  `rooms` varchar(255) NOT NULL
);

CREATE TABLE `Room` (
  `height` float NOT NULL,
  `lenght` float NOT NULL,
  `width` float NOT NULL,
  `number` int NOT NULL,
  `purpose` varchar(255) NOT NULL,
  `floor` int NOT NULL,
  `type` varchar(255) NOT NULL,
  `faculty` varchar(255) NOT NULL,
  `departament` varchar(255) NOT NULL,
  `laboratory` varchar(255) NOT NULL,
  `buildings` varchar(255) NOT NULL
);

CREATE TABLE `Building` (
  `name` varchar(255) NOT NULL,
  `rooms` varchar(255) NOT NULL
);

ALTER TABLE `Faculty` ADD FOREIGN KEY (`departaments`) REFERENCES `Departament` (`faculty`);

ALTER TABLE `Faculty` ADD FOREIGN KEY (`rooms`) REFERENCES `Room` (`faculty`);

ALTER TABLE `Departament` ADD FOREIGN KEY (`lablratories`) REFERENCES `Laboratory` (`departament`);

ALTER TABLE `Departament` ADD FOREIGN KEY (`rooms`) REFERENCES `Room` (`departament`);

ALTER TABLE `Laboratory` ADD FOREIGN KEY (`rooms`) REFERENCES `Room` (`laboratory`);

ALTER TABLE `Room` ADD FOREIGN KEY (`buildings`) REFERENCES `Building` (`rooms`);
