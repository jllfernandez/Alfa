Crea usuario:
create user vera_owner with encrypted password 'vera01';
commit;

create user vera_app with encrypted password 'vera01';
commit;

APUNTES
Crear BBDD
create database Vera;
commit;

Privilegios
grant all privileges on database Vera to postgres;
commit;
grant all privileges on database Vera to vera_owner;
commit;

Create Squema
CREATE SCHEMA Vera AUTHORIZATION vera_owner;
commit;

-- Permissions
GRANT ALL ON SCHEMA Vera TO vera_owner;
GRANT USAGE ON SCHEMA Vera TO vera_app;
commit;

