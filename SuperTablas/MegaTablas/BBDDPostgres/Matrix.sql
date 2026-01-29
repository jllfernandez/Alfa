Crea usuario:
create user matrix_owner with encrypted password 'matrix01';
commit;

create user matrix_app with encrypted password 'matrix01';
commit;

CREATE ROLE matrix_owner WITH 
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	LOGIN
	NOREPLICATION
	NOBYPASSRLS
	CONNECTION LIMIT -1;
commit;



CREATE ROLE matrix_app WITH 
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
create database matrix;
commit;

Privilegios
grant all privileges on database matrix to matrix_owner;
commit;

GoTo BBDD Desaeada

Create Squema
CREATE SCHEMA matrix AUTHORIZATION matrix_owner;
commit;


-- Permissions
GRANT ALL ON SCHEMA matrix TO matrix_owner;
GRANT USAGE ON SCHEMA matrix TO matrix_app;
commit;

-- Permissions
--GRANT CREATE, USAGE ON SCHEMA matrix TO matrix_owner;
--commit;


-- Permissions
--GRANT USAGE ON SCHEMA matrix TO matrix_app;
--commit;


CREATE TABLE matrix.rol (
	id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	code varchar(15) NULL,
	descr varchar(35) NULL,
	CONSTRAINT "ROL_pkey" PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE matrix.rol OWNER TO matrix_owner;
GRANT ALL ON TABLE matrix.rol TO matrix_app;

