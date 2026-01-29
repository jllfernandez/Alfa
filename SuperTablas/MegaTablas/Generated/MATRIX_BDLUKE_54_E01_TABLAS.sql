DROP TABLE matrix.usuarios;
DROP TABLE matrix.capabilityandrole;
DROP TABLE matrix.roles;
DROP TABLE matrix.capabilityes;
COMMIT;

CREATE TABLE IF NOT EXISTS matrix.capabilityes
(
     id integer GENERATED ALWAYS AS identity NOT NULL,  PRIMARY KEY (id),
     code varchar(15) NOT NULL,
     descr varchar(35) NOT NULL

);
COMMIT;

CREATE TABLE IF NOT EXISTS matrix.roles
(
     id integer GENERATED ALWAYS AS identity NOT NULL,  PRIMARY KEY (id),
     code varchar(15) NOT NULL,
     descr varchar(35) NOT NULL

);
COMMIT;

CREATE TABLE IF NOT EXISTS matrix.capabilityandrole
(
     id_capability int4 NOT NULL,
     id_rol int4  NOT NULL

);
COMMIT;

CREATE TABLE IF NOT EXISTS matrix.usuarios
(
     id integer GENERATED ALWAYS AS identity NOT NULL,  PRIMARY KEY (id),
     id_rol int4 NOT NULL,
     login varchar(15) NOT NULL,
     pass varchar(15) NOT NULL,
     fecha timestamp NOT NULL

);
COMMIT;
