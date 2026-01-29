ALTER TABLE Atlas.CAPABILITYANDROLE  ADD CONSTRAINT fk_capabilityandrole_x_capability FOREIGN KEY (id_capability) REFERENCES Atlas.CAPABILITYES(id);
ALTER TABLE Atlas.CAPABILITYANDROLE  ADD CONSTRAINT fk_capabilityandrole_x_rols FOREIGN KEY (id_role) REFERENCES Atlas.ROLES(id);

commit;

ALTER TABLE Atlas.USUARIOS  ADD CONSTRAINT fk_usuarios_x_roles FOREIGN KEY (id_rol) REFERENCES Atlas.ROLES(id);

commit;



ALTER TABLE bssos.adm_perfiles_x_roles ADD CONSTRAINT fk_adm_perfiles_x_roles_id_perfil FOREIGN KEY (id_perfil) REFERENCES bssos.adm_perfiles(id_perfil);
ALTER TABLE bssos.adm_perfiles_x_roles ADD CONSTRAINT fk_adm_perfiles_x_roles_id_rol FOREIGN KEY (id_rol) REFERENCES bssos.adm_roles(id_rol);

ALTER TABLE bssos.adm_sub_ambitos ADD CONSTRAINT fk_id_ambito FOREIGN KEY (id_ambito) REFERENCES bssos.adm_ambitos(id_ambito);
ALTER TABLE bssos.adm_sub_ambitos ADD CONSTRAINT fk_id_tipo_sub_ambito FOREIGN KEY (id_tipo_sub_ambito) REFERENCES bssos.adm_tipo_sub_ambitos(id_tipo_sub_ambito);

ALTER TABLE bssos.adm_unidades_org_x_sub_ambitos ADD CONSTRAINT fk_bssos_adm_unidad_org_x_sub_ambito_id_sub_ambito FOREIGN KEY (id_sub_ambito) REFERENCES bssos.adm_sub_ambitos(id_sub_ambito);
ALTER TABLE bssos.adm_unidades_org_x_sub_ambitos ADD CONSTRAINT fk_bssos_adm_unidad_org_x_unidad_org_id_unidad_org FOREIGN KEY (id_unidad_org) REFERENCES bssos.repo_unidades_org(id_unidad_org);

ALTER TABLE bssos.adm_usuarios_x_perfiles ADD CONSTRAINT fk_adm_usuarios_x_perfiles_id_usuario FOREIGN KEY (id_usuario) REFERENCES bssos.adm_usuarios(id_usuario);
ALTER TABLE bssos.adm_usuarios_x_perfiles ADD CONSTRAINT fk_adm_usuarios_x_perfiles_id_perfil FOREIGN KEY (id_perfil) REFERENCES bssos.adm_perfiles(id_perfil);

ALTER TABLE bssos.adm_usuarios_x_sub_ambitos ADD CONSTRAINT fk_adm_usuarios_x_sub_ambitos_id_usuario FOREIGN KEY (id_usuario) REFERENCES bssos.adm_usuarios(id_usuario);
ALTER TABLE bssos.adm_usuarios_x_sub_ambitos ADD CONSTRAINT fk_adm_usuarios_x_sub_ambitos_id_sub_ambito FOREIGN KEY (id_sub_ambito) REFERENCES bssos.adm_sub_ambitos(id_sub_ambito);

ALTER TABLE bssos.adm_unidades_org_x_sub_ambitos ADD CONSTRAINT fk_adm_unidad_org_x_sub_ambito_id_unidad_org FOREIGN KEY (id_unidad_org) REFERENCES bssos.repo_unidades_org(id_unidad_org);

ALTER TABLE bssos.repo_profesionales ADD CONSTRAINT fk_repo_profesionales_x_repo_unidad_org FOREIGN KEY (id_unidad_org) REFERENCES bssos.repo_unidades_org(id_unidad_org);

ALTER TABLE bssos.repo_unidades_org ADD CONSTRAINT fk_repo_unidad_org_x_tipo_repo_unidad_org FOREIGN KEY (id_tipo_unidad_org) REFERENCES bssos.repo_tipo_unidades_org(id_tipo_unidad_org);
ALTER TABLE bssos.repo_unidades_org ADD CONSTRAINT fk_repo_unidad_org_x_repo_unidad_org FOREIGN KEY (id_padre) REFERENCES bssos.repo_unidades_org(id_unidad_org);



