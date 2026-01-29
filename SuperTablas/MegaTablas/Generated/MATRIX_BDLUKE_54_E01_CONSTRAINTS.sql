

ALTER TABLE matrix.capabilityandrole ADD CONSTRAINT fk_capabilityandrole_x_capabilityes_id FOREIGN KEY (id_capability) REFERENCES matrix.capabilityes(id);
ALTER TABLE matrix.capabilityandrole ADD CONSTRAINT fk_capabilityandrole_x_roles_id FOREIGN KEY (id_rol) REFERENCES matrix.roles(id);

ALTER TABLE matrix.usuarios ADD CONSTRAINT fk_usuarios_x_roles_id FOREIGN KEY (id_rol) REFERENCES matrix.roles(id);

COMMIT;
