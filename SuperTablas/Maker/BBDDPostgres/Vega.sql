Crea usuario:
create user vega_owner with encrypted password 'vega01';
commit;

create user vega_app with encrypted password 'vega01';
commit;

CREATE ROLE vega_owner WITH 
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	LOGIN
	NOREPLICATION
	NOBYPASSRLS
	CONNECTION LIMIT -1;
commit;



CREATE ROLE vega_app WITH 
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
create database vega;
commit;

Privilegios
grant all privileges on database vega to vega_owner;
commit;

GoTo BBDD Desaeada

Create Squema
CREATE SCHEMA vega AUTHORIZATION vega_owner;
commit;


-- Permissions
GRANT ALL ON SCHEMA vega TO vega_owner;
GRANT USAGE ON SCHEMA vega TO vega_app;
commit;

-- Permissions
GRANT CREATE, USAGE ON SCHEMA vega TO vega_owner;
commit;


-- Permissions
GRANT USAGE ON SCHEMA vega TO vega_app;
commit;

