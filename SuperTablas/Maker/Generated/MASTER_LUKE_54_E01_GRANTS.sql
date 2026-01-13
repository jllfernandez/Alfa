GRANT DELETE, INSERT, SELECT, UPDATE ON master.capabilityes TO master_app;
GRANT SELECT ON master.capabilityes TO master_read;

GRANT DELETE, INSERT, SELECT, UPDATE ON master.roles TO master_app;
GRANT SELECT ON master.roles TO master_read;

GRANT DELETE, INSERT, SELECT, UPDATE ON master.capabilityandrole TO master_app;
GRANT SELECT ON master.capabilityandrole TO master_read;

GRANT DELETE, INSERT, SELECT, UPDATE ON master.usuarios TO master_app;
GRANT SELECT ON master.usuarios TO master_read;

COMMIT;
