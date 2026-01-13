DROP TABLE master.usuarios;
DROP TABLE master.capabilityandrole;
DROP TABLE master.roles;
DROP TABLE master.capabilityes;
COMMIT;

CREATE TABLE IF NOT EXISTS master.capabilityes
(
     id integer GENERATED ALWAYS AS identity NOT NULL,  PRIMARY KEY (id),
     code varchar(15) NOT NULL,
     descr varchar(35) NOT NULL

);
COMMIT;

CREATE TABLE IF NOT EXISTS master.roles
(
     id integer GENERATED ALWAYS AS identity NOT NULL,  PRIMARY KEY (id),
     code varchar(15) NOT NULL,
     descr varchar(35) NOT NULL

);
COMMIT;

CREATE TABLE IF NOT EXISTS master.capabilityandrole
(
     id_capability int4 NOT NULL,
     id_rol int4  NOT NULL

);
COMMIT;

CREATE TABLE IF NOT EXISTS master.usuarios
(
     id integer GENERATED ALWAYS AS identity NOT NULL,  PRIMARY KEY (id),
     id_rol int4 NOT NULL,
     login varchar(15) NOT NULL,
     pass varchar(15) NOT NULL,
     fecha timestamp DEFAULT now() NOT NULL

);
COMMIT;
