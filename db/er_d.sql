CREATE TABLE [faculty] (
  [id] int PRIMARY KEY NOT NULL,
  [name] nvarchar(255) NOT NULL,
  [decan] nvarchar(255) NOT NULL
);

CREATE TABLE [departament] (
  [id] int PRIMARY KEY NOT NULL,
  [name_department] nvarchar(255) NOT NULL,
  [list_workers] int NOT NULL
);

CREATE TABLE [building] (
  [id] int PRIMARY KEY NOT NULL,
  [name_korpus] nvarchar(255) NOT NULL,
  [adress] nvarchar(255) NOT NULL,
  [watchman] nvarchar(255) NOT NULL
);

CREATE TABLE [room] (
  [id] int PRIMARY KEY NOT NULL,
  [nomber_room] int NOT NULL,
  [floor] int NOT NULL,
  [type_room] nvarchar(255) NOT NULL
);

CREATE TABLE [inf_room] (
  [id] int PRIMARY KEY NOT NULL,
  [length] float NOT NULL,
  [width] float NOT NULL,
  [height] float NOT NULL,
  [volume] float NOT NULL,
  [floor] float NOT NULL
);

CREATE TABLE [list_workers] (
  [id] int PRIMARY KEY NOT NULL,
  [first_name] nvarchar(255) NOT NULL,
  [second_name] nvarchar(255) NOT NULL,
  [middle_name] nvarchar(255) NOT NULL,
  [post] nvarchar(255) NOT NULL
);

CREATE TABLE [audit] (
  [id] int PRIMARY KEY NOT NULL,
  [name_audit] nvarchar(255) NOT NULL,
  [data_audit] datatime NOT NULL,
  [mark_audit] nvarchar(255) NOT NULL,
  [recommendations] nvarchar(255)
);

ALTER TABLE [departament] ADD FOREIGN KEY ([id]) REFERENCES [faculty] ([name]);

ALTER TABLE [building] ADD FOREIGN KEY ([id]) REFERENCES [departament] ([name_department]);

ALTER TABLE [room] ADD FOREIGN KEY ([id]) REFERENCES [building] ([name_building]);

ALTER TABLE [inf_room] ADD FOREIGN KEY ([id]) REFERENCES [room] ([number]);

ALTER TABLE [list_workers] ADD FOREIGN KEY ([id]) REFERENCES [departament] ([list_workers]);

ALTER TABLE [audit] ADD FOREIGN KEY ([id]) REFERENCES [departament] ([name_department]);

CREATE UNIQUE INDEX [faculty_index_0] ON [faculty] ("dean", "name");

CREATE UNIQUE INDEX [departament_index_1] ON [departament] ("name_department");

CREATE UNIQUE INDEX [building_index_2] ON [building] ("watchman", "adress");

CREATE UNIQUE INDEX [room_index_3] ON [room] ("number");