Crea usuario:
create user alfa_owner with encrypted password 'alfa';
commit;
create user alfa_app with encrypted password 'alfa';
commit;
create user alfa_read with encrypted password 'alfa';
commit;

-- Privilegios
grant all privileges on database postgres to alfa_owner;
commit;

-- Create Squema
CREATE SCHEMA Alfa AUTHORIZATION alfa_owner;
commit;

-- Permissions
GRANT ALL ON SCHEMA Alfa TO alfa_owner;
GRANT USAGE ON SCHEMA Alfa TO public;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA Alfa TO alfa_app;
GRANT SELECT ON ALL TABLES IN SCHEMA Alfa TO alfa_read;
commit;

-- Permissions
ALTER DEFAULT PRIVILEGES IN SCHEMA alfa GRANT SELECT ON TABLES TO alfa_read;
ALTER DEFAULT PRIVILEGES IN SCHEMA alfa GRANT SELECT, DELETE, UPDATE, INSERT ON TABLES TO alfa_app;
COMMIT;
