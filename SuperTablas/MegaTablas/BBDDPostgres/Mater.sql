Crea usuario:
create user mater_owner with encrypted password 'mater01';
commit;

create user mater_app with encrypted password 'mater01';
commit;

ALTER ROLE mater_owner WITH 
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	LOGIN
	NOREPLICATION
	NOBYPASSRLS
	CONNECTION LIMIT -1;
commit;



ALTER ROLE mater_app WITH 
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	LOGIN
	NOREPLICATION
	NOBYPASSRLS
	CONNECTION LIMIT -1;
commit;


APUNTES
Crear BBDD
create database mater;
commit;

Privilegios
grant all privileges on database mater to mater_owner;
commit;

GoTo BBDD Desaeada

Create Squema
CREATE SCHEMA mater AUTHORIZATION mater_owner;
commit;


-- Permissions
GRANT ALL ON SCHEMA mater TO mater_owner;
GRANT USAGE ON SCHEMA mater TO mater_app;
commit;

CREATE TABLE mater.rol (
	id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	code varchar(15) NULL,
	descr varchar(35) NULL,
	CONSTRAINT "ROL_pkey" PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE mater.rol OWNER TO mater_owner;
GRANT ALL ON TABLE mater.rol TO mater_app;

