CREATE INDEX idx_usuarios_login ON Atlas.USUARIOS (login);
commit;



CREATE INDEX idx_adm_perfiles_x_roles_id_perfil ON bssos.adm_perfiles_x_roles(id_perfil) TABLESPACE ts_bssos_idx01;
CREATE INDEX idx_adm_perfiles_x_roles_id_rol ON bssos.adm_perfiles_x_roles(id_rol) TABLESPACE ts_bssos_idx01;

CREATE INDEX idx_adm_sub_ambitos_x_id_ambito ON bssos.adm_sub_ambitos(id_ambito) TABLESPACE ts_bssos_idx01;
CREATE INDEX idx_adm_sub_ambitos_x_id_tipo_sub_ambito ON bssos.adm_sub_ambitos(id_tipo_sub_ambito) TABLESPACE ts_bssos_idx01;

CREATE INDEX idx_adm_unidades_org_x_sub_ambito_id_unidad_org ON bssos.repo_unidades_org(id_unidad_org) TABLESPACE ts_bssos_idx01;
CREATE INDEX idx_adm_unidades_org_x_sub_ambito_id_sub_ambito ON bssos.adm_sub_ambitos(id_sub_ambito) TABLESPACE ts_bssos_idx01;

CREATE INDEX idx_adm_usuarios_x_perfiles_id_usuario ON bssos.adm_usuarios_x_perfiles(id_usuario) TABLESPACE ts_bssos_idx01;
CREATE INDEX idx_adm_usuarios_x_perfiles_id_perfil ON bssos.adm_usuarios_x_perfiles(id_perfil) TABLESPACE ts_bssos_idx01;

CREATE INDEX idx_usuarios_x_sub_ambitos_id_usuario ON bssos.adm_usuarios_x_sub_ambitos(id_usuario) TABLESPACE ts_bssos_idx01;
CREATE INDEX idx_usuarios_x_sub_ambitos_id_sub_ambito ON bssos.adm_usuarios_x_sub_ambitos(id_sub_ambito) TABLESPACE ts_bssos_idx01;

CREATE INDEX idx_adm_usuarios_x_repo_profesionales ON  bssos.adm_usuarios (ref_profesional) TABLESPACE ts_bssos_idx01;

CREATE INDEX idx_repo_unidades_org_x_id_tipo_unidad_org ON bssos.repo_unidades_org(id_tipo_unidad_org) TABLESPACE ts_bssos_idx01;

