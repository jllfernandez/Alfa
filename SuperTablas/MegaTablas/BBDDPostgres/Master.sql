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


APUNTES
Crear BBDD
create database Master;
commit;
Privilegios
grant all privileges on database Master to adminbd;
commit;

-- GoTo BBDD Desaeada

Crea usuario:
CREATE USER master_owner WITH ENCRYPTED PASSWORD 'master_owner';
CREATE USER master_app WITH ENCRYPTED PASSWORD 'master_app';
CREATE USER master_read WITH ENCRYPTED PASSWORD 'master_read';
commit;

grant all privileges on database postgres to master_owner;
commit;

-- Create Squema
CREATE SCHEMA Master AUTHORIZATION pg_database_owner;
commit;

-- Permissions
GRANT ALL ON SCHEMA Master TO master_owner;
GRANT CREATE, USAGE ON SCHEMA master TO master_app;
GRANT pg_read_all_data TO master_read;

commit;

GRANT ALL ON SCHEMA master TO master_owner;
commit;
ALTER DEFAULT PRIVILEGES IN SCHEMA master GRANT ALL ON TABLES TO master_app;
commit;
ALTER DEFAULT PRIVILEGES IN SCHEMA master GRANT SELECT ON TABLES TO master_read;
commit;

-- Cambiar propietario de tablas
ALTER TABLE master.capabilityes OWNER TO master_owner;
ALTER TABLE master.roles OWNER TO master_owner;
ALTER TABLE master.capabilityandrole OWNER TO master_owner;
ALTER TABLE master.usuarios OWNER TO master_owner;
commit;

Aquí la forma correcta para realizar la creacion de un usuario sobre la BD de postgres y que este cuente con los permisos necesarios para realizar todas las actividades.

CREATE USER master_owner WITH ENCRYPTED PASSWORD 'master_owner';
GRANT CONNECT ON DATABASE Master TO  master;
GRANT USAGE, CREATE ON SCHEMA master TO master_owner;
GRANT SELECT, INSERT, UPDATE, DELETE ON myschema.mytable TO myuser;

--------------------------------------------------
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA master TO master_owner;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA master TO master_owner;
GRANT ALL ON SCHEMA master TO master_owner;





