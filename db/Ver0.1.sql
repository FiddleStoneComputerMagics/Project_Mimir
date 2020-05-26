CREATE TABLE "Faculty" (
  "dean" varchar NOT NULL,
  "name" varchar NOT NULL,
  "departaments" varchar NOT NULL,
  "rooms" varchar NOT NULL,
  FOREIGN KEY ("departaments") REFERENCES "Departament"("faculty"),
  FOREIGN KEY ("rooms") REFERENCES "Room" ("faculty")
);

CREATE TABLE "Departament" (
  "headOfDepartament" varchar NOT NULL,
  "name" varchar NOT NULL,
  "specialization" varchar NOT NULL,
  "faculty" varchar NOT NULL,
  "lablratories" varchar NOT NULL,
  "rooms" varchar NOT NULL,
  FOREIGN KEY ("lablratories") REFERENCES "Laboratory" ("departament"),
  FOREIGN KEY ("rooms") REFERENCES "Room" ("departament")
);

CREATE TABLE "Laboratory" (
  "responsablePerson" varchar NOT NULL,
  "departament" varchar NOT NULL,
  "rooms" varchar NOT NULL,
  FOREIGN KEY ("rooms") REFERENCES "Room" ("laboratory")
);

CREATE TABLE "Room" (
  "height" float NOT NULL,
  "lenght" float NOT NULL,
  "width" float NOT NULL,
  "number" int NOT NULL,
  "purpose" varchar NOT NULL,
  "floor" int NOT NULL,
  "type" varchar NOT NULL,
  "faculty" varchar NOT NULL,
  "departament" varchar NOT NULL,
  "laboratory" varchar NOT NULL,
  "buildings" varchar NOT NULL,
  FOREIGN KEY ("buildings") REFERENCES "Building" ("rooms")
);

CREATE TABLE "Building" (
  "name" varchar NOT NULL,
  "rooms" varchar NOT NULL
);

insert into "Faculty"("dean","name", "departaments", "rooms") values ("Джотаро Куджо", "Физический Факультет", "Кафедра Нанотехнологий", "228");
insert into "Faculty"("dean","name", "departaments", "rooms") values ("Джотаро Куджо", "Физический Факультет", "Кафедра Ядерной Физики", "227");
insert into "Faculty"("dean","name", "departaments", "rooms") values ("Джотаро Куджо", "Физический Факультет", "Кафедра Нанотехнологий", "226");
insert into "Faculty"("dean","name", "departaments", "rooms") values ("Джотаро Куджо", "Физический Факультет", "Кафедра Общей Физики", "225");
insert into "Faculty"("dean","name", "departaments", "rooms") values ("Джотаро Куджо", "Физический Факультет", "Кафедра Технической Физики", "224");
insert into "Faculty"("dean","name", "departaments", "rooms") values ("Джотаро Куджо", "Физический Факультет", "Кафедра Радиофизики", "223");
insert into "Faculty"("dean","name", "departaments", "rooms") values ("Джотаро Куджо", "Физический Факультет", "Кафедра Инфокоммуникационных технологий", "222");
insert into "Faculty"("dean","name", "departaments", "rooms") values ("Джонатан Джостар", "Химический Факультет", "Кафедра Органический Химии", "32");
insert into "Faculty"("dean","name", "departaments", "rooms") values ("Джозеф Джостар", "Механико-математический Факультет", "Кафедра Многомерного Интегрирования", "124");
insert into "Faculty"("dean","name", "departaments", "rooms") values ("Джоске Хигашиката", "Биологический Факультет", "Кафедра Зоологии", "235");
insert into "Faculty"("dean","name", "departaments", "rooms") values ("Кира Йошикаге", "Экономический Факультет", "Кафедра Экономического Планирования", "118");
insert into "Faculty"("dean","name", "departaments", "rooms") values ("Дио Брандо", "Геологический Факультет", "Кафедра Минералогии", "190");
insert into "Faculty"("dean","name", "departaments", "rooms") values ("Джорно Джованна", "Исторический Факультет", "Кафедра Военной Истории", "179");
insert into "Departament"("headOfDepartament", "name", "specialization", "faculty", "lablratories", "rooms") values ("Люк Скайуокер", "Кафедра Нанотехнологий", "Нанотехнологии и микросистемная техника", "Физический Факультет", "20", "400")





