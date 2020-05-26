CREATE TABLE "Users" (
  "id" numeric NOT NULL UNIQUE,
  "First" varchar NOT NULL,
  "Birth" integer NOT NULL,
  "Adress" varchar NOT NULL,
  "Login" varchar NOT NULL,
  "Password" varchar NOT NULL,
  "Position" varchar NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY ("Position") REFERENCES "Faculty"("dean"),
   FOREIGN KEY ("Position") REFERENCES "Departament"("headOfDepartament"),
   FOREIGN KEY ("Position") REFERENCES "Laboratory"("responsablePerson"),
   FOREIGN KEY ("First") REFERENCES "Faculty"("dean"),
   FOREIGN KEY ("First") REFERENCES "Departament"("headOfDepartament"),
   FOREIGN KEY ("First") REFERENCES "Laboratory"("responsablePerson"),
);

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


