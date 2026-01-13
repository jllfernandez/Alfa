GRANT DELETE, INSERT, SELECT, UPDATE ON matrix.capabilityes TO matrix_app;
GRANT SELECT ON matrix.capabilityes TO matrix_read;

GRANT DELETE, INSERT, SELECT, UPDATE ON matrix.roles TO matrix_app;
GRANT SELECT ON matrix.roles TO matrix_read;

GRANT DELETE, INSERT, SELECT, UPDATE ON matrix.capabilityandrole TO matrix_app;
GRANT SELECT ON matrix.capabilityandrole TO matrix_read;

GRANT DELETE, INSERT, SELECT, UPDATE ON matrix.usuarios TO matrix_app;
GRANT SELECT ON matrix.usuarios TO matrix_read;

COMMIT;
