CREATE INDEX idx_capabilityes_code ON matrix.capabilityes(code);
CREATE INDEX idx_roles_code ON matrix.roles(code);

CREATE INDEX idx_usuarios_login ON matrix.usuarios(login);
COMMIT;
