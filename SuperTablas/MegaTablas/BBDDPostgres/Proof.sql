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
create database proof;
commit;
Privilegios
grant all privileges on database proof to adminbd;
commit;

-- GoTo BBDD Desaeada

Crea usuario:
CREATE USER proof_owner WITH ENCRYPTED PASSWORD 'owner';
CREATE USER proof_app WITH ENCRYPTED PASSWORD 'app';
CREATE USER proof_read WITH ENCRYPTED PASSWORD 'read';
commit;

grant all privileges on database postgres to proof_owner;
commit;

-- Create Squema
CREATE SCHEMA proof AUTHORIZATION pg_database_owner;
commit;

-- Permissions
GRANT ALL ON SCHEMA proof TO proof_owner;
GRANT CREATE, USAGE ON SCHEMA proof TO proof_app;
GRANT pg_read_all_data TO proof_read;

commit;

GRANT ALL ON SCHEMA proof TO proof_owner;
commit;
ALTER DEFAULT PRIVILEGES IN SCHEMA proof GRANT ALL ON TABLES TO proof_app;
commit;
ALTER DEFAULT PRIVILEGES IN SCHEMA proof GRANT SELECT ON TABLES TO proof_read;
commit;

-- Cambiar propietario de tablas
ALTER TABLE proof.capabilityes OWNER TO proof_owner;
ALTER TABLE proof.roles OWNER TO proof_owner;
ALTER TABLE proof.capabilityandrole OWNER TO proof_owner;
ALTER TABLE proof.usuarios OWNER TO proof_owner;
commit;

Aquí la forma correcta para realizar la creacion de un usuario sobre la BD de postgres y que este cuente con los permisos necesarios para realizar todas las actividades.

CREATE USER proof_owner WITH ENCRYPTED PASSWORD 'proof_owner';
GRANT CONNECT ON DATABASE proof TO  proof;
GRANT USAGE, CREATE ON SCHEMA proof TO proof_owner;
GRANT SELECT, INSERT, UPDATE, DELETE ON myschema.mytable TO myuser;

--------------------------------------------------
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA proof TO proof_owner;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA proof TO proof_owner;
GRANT ALL ON SCHEMA proof TO proof_owner;





