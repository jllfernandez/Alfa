Instalar Postgres con Docker https://www.datacamp.com/es/tutorial/postgresql-docker

Descarga
docker pull postgres

Una vez que tengas la imagen PostgreSQL, puedes crear e iniciar un contenedor con un solo comando:

Te recomiendo que utilices opciones adicionales:

docker run --name postgres-db -e POSTGRES_PASSWORD=password -e POSTGRES_USER=user -e POSTGRES_DB=postgres_db -p 5432:5432 -v postgres-data:/var/lib/postgresql/data -d postgres

docker volume create postgres-data

Para conectarte a tu base de datos PostgreSQL desde aplicaciones que se ejecuten en tu máquina anfitriona, necesitas exponer el puerto PostgreSQL (5432) a tu sistema local.

Lo más sencillo es asignar el puerto 5432 del contenedor al mismo puerto de tu host:

docker exec -it postgres-db psql -U user -d postgres


Crea usuario:
create user adminbd with encrypted password 'adminbd';
commit;

create user matrix_app with encrypted password 'matrix15';
commit;
create user matrix_read with encrypted password 'matrix15';
commit;

APUNTES
Crear BBDD
create database Atlas;
commit;
Privilegios
grant all privileges on database Atlas to adminbd;
commit;

GoTo BBDD Desaeada

Create Squema
CREATE SCHEMA Matrix;
commit;


-- Permissions
GRANT ALL ON SCHEMA Atlas TO pg_database_owner;
GRANT USAGE ON SCHEMA Atlas TO public;
commit;





Crear BBDD
create database bssos;
commit;

create database Matrix;
commit;

Privilegios
grant all privileges on database postgres to matrix;
commit;

grant all privileges on database bssos to System;
commit;

grant all privileges on database Matrix to matrix;
commit;

Create Squema
CREATE SCHEMA Matrix AUTHORIZATION pg_database_owner;


-- Permissions

--GRANT ALL ON SCHEMA Atlas TO pg_database_owner;
GRANT ALL ON SCHEMA Matrix TO matrix_app;
--GRANT DELETE, INSERT, SELECT, UPDATE ON SCHEMA Matrix TO matrix_app;
GRANT pg_read_all_data to matrix_read;

commit;

--GRANT DELETE, INSERT, SELECT, UPDATE on database TO matrix_app;
GRANT SELECT ON SCHEMA Matrix TO matrix_read;

GRANT USAGE ON SCHEMA Atlas TO public;
Privilegios
grant all privileges on database Atlas to matrix;
commit;





CREATE SCHEMA Mamen AUTHORIZATION pg_database_owner;
commit;
-- Permissions
GRANT ALL ON SCHEMA Mamen TO pg_database_owner;
GRANT USAGE ON SCHEMA Mamen TO public;
commit;

CREATE SCHEMA matrix AUTHORIZATION pg_database_owner;
-- Permissions
GRANT ALL ON SCHEMA matrix TO pg_database_owner;
GRANT USAGE ON SCHEMA matrix TO public;

-- DROP SCHEMA public;

CREATE SCHEMA Matrix AUTHORIZATION postgres;

Privilegios
grant all privileges on database bssos to System;
commit;

docker run -e POSTGRES_PASSWORD=secure_pass_here


Aquí les dejo la forma correcta para realizar la creacion de un usuario sobre la BD de postgres y que este cuente con los permisos necesarios para realizar todas las actividades.

CREATE USER myuser WITH ENCRYPTED PASSWORD 'xxxxxxxxxx';
GRANT CONNECT ON DATABASE mydb TO  myuser;
GRANT USAGE, CREATE ON SCHEMA myschema TO myuser;
GRANT SELECT, INSERT, UPDATE, DELETE ON myschema.mytable TO myuser;

--------------------------------------------------
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA myschema TO myuser;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA myschema TO myuser;
GRANT ALL ON SCHEMA matrix TO matrix_owner;

