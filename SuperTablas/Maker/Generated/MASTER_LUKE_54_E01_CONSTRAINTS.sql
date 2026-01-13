

ALTER TABLE master.capabilityandrole ADD CONSTRAINT fk_capabilityandrole_x_capabilityes_id FOREIGN KEY (id_capability) REFERENCES master.capabilityes(id);
ALTER TABLE master.capabilityandrole ADD CONSTRAINT fk_capabilityandrole_x_roles_id FOREIGN KEY (id_rol) REFERENCES master.roles(id);

ALTER TABLE master.usuarios ADD CONSTRAINT fk_usuarios_x_roles_id FOREIGN KEY (id_rol) REFERENCES master.roles(id);

COMMIT;
