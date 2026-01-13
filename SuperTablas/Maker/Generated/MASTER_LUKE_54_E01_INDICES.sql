CREATE INDEX idx_capabilityes_code ON master.capabilityes(code);
CREATE INDEX idx_roles_code ON master.roles(code);

CREATE INDEX idx_usuarios_login ON master.usuarios(login);
COMMIT;
