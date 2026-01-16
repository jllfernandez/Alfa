--Instalar Postgres con Docker: https://www.datacamp.com/es/tutorial/postgresql-docker

--Descarga
docker pull postgres

--Una vez que tengas la imagen PostgreSQL, puedes crear e iniciar un contenedor con un solo comando:

--Te recomiendo que utilices opciones adicionales:
-- user: adminbd
--contraseña: pass
--Base de Datos: Atlas

docker run -name postgres-db -e POSTGRES_PASSWORD=pass -e POSTGRES_USER=adminbd -e POSTGRES_DB=postgres_db -p 5432:5432 -v postgres-data:/var/lib/postgresql/data -d postgres

docker volume create postgres-data

--Para conectarte a tu base de datos PostgreSQL desde aplicaciones que se ejecuten en tu máquina anfitriona, 
--necesitas exponer el puerto PostgreSQL (5432) a tu sistema local.

--Lo más sencillo es asignar el puerto 5432 del contenedor al mismo puerto de tu host:

docker exec -it postgres-db psql -U adminbd -d postgres


--Crea usuario:
create user adminbd with encrypted password 'pass';
commit;


APUNTES
Crear BBDD
create database Atlas;
commit;
Privilegios
grant all privileges on database Atlas to adminbd;
commit;