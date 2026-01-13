Instalar Postgres con Docker https://www.datacamp.com/es/tutorial/postgresql-docker

Descarga
docker pull postgres

Una vez que tengas la imagen PostgreSQL, puedes crear e iniciar un contenedor con un solo comando:

Te recomiendo que utilices opciones adicionales:

docker run --name postgres-db -e POSTGRES_PASSWORD=password -e POSTGRES_USER=user -e POSTGRES_DB=postgres_db -p 5432:5432 -v postgres-data:/var/lib/postgresql/data -d postgres

docker volume create postgres-data

Para conectarte a tu base de datos PostgreSQL desde aplicaciones que se ejecuten en tu máquina anfitriona, necesitas exponer el puerto PostgreSQL (5432) a tu sistema local.

Lo más sencillo es asignar el puerto 5432 del contenedor al mismo puerto de tu host:

--docker run --name postgres-db -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres

docker exec -it postgres-db psql -U user -d postgres

Para arrancar una base de datos Postgres con docker el proceso no puede ser más sencillo. 
Basta que cojamos la plantilla del fichero de configuración de Docker Compose que dejamos a continuación e invoques al comando que realizará todo el proceso.

version: '2'
services:
    postgres:
        image: 'postgres:latest'
        restart: always
        volumes:
        - './postgres_data:/var/lib/postgresql/data'
        environment:
        - POSTGRES_PASSWORD=secure
        ports:
        - '5432:5432'
Guarda el fichero en local con el nombre docker-compose.yml en un directorio denominado <homeusuario>/docker/postgres si estás en Unix, o \docker\postgres si estás en Windows. La configuración **guarda los datos en el subdirectorio ./postgresdata** de forma permanentemente, puesto que si no, en cada reinicio del contenedor se perderían las bases de datos alojadas. 
Luego, posiciónate con un terminal de comandos en el mismo directorio que el fichero y ejecuta:

docker-compose up -d

Crea usuario:
create user System with encrypted password 'Temporal01';

create user matrix with encrypted password 'matrix15';
commit;

APUNTES
Crear BBDD
create database Atlas;
commit;
Privilegios
grant all privileges on database Atlas to matrix;
commit;

GoTo BBDD Desaeada

Create Squema
CREATE SCHEMA Matrix;
commit;

CREATE SCHEMA Matrix AUTHORIZATION user;
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
CREATE SCHEMA Atlas AUTHORIZATION pg_database_owner;


-- Permissions

GRANT ALL ON SCHEMA Atlas TO pg_database_owner;
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


