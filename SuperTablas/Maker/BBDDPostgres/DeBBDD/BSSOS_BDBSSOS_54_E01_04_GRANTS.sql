ALTER TABLE Atlas.CAPABILITYES OWNER TO matrix;
GRANT ALL ON TABLE Atlas.CAPABILITYES TO matrix;

commit;

ALTER TABLE Atlas.ROLES OWNER TO matrix;
GRANT ALL ON TABLE Atlas.ROLES TO matrix;

commit;

ALTER TABLE Atlas.CAPABILITYANDROLE OWNER TO matrix;
GRANT ALL ON TABLE Atlas.CAPABILITYANDROLE TO matrix;

commit;

ALTER TABLE Atlas.USUARIOS OWNER TO matrix;
GRANT ALL ON TABLE Atlas.USUARIOS TO matrix;

COMMIT;	



GRANT DELETE, INSERT, SELECT, UPDATE ON adm_perfiles TO bssos_app; 
GRANT SELECT ON adm_perfiles TO bssos_read; 

GRANT DELETE, INSERT, SELECT, UPDATE ON adm_usuarios_x_perfiles TO bssos_app; 
GRANT SELECT ON adm_usuarios_x_perfiles TO bssos_read; 

GRANT DELETE, INSERT, SELECT, UPDATE ON bssos.adm_perfiles_x_roles TO bssos_app; 
GRANT SELECT ON bssos.adm_perfiles_x_roles TO bssos_read; 

GRANT DELETE, INSERT, SELECT, UPDATE ON bssos.adm_ambitos TO bssos_app; 
GRANT SELECT ON bssos.adm_ambitos TO bssos_read; 

GRANT DELETE, INSERT, SELECT, UPDATE ON bssos.adm_tipo_sub_ambitos TO bssos_app; 
GRANT SELECT ON bssos.adm_tipo_sub_ambitos TO bssos_read; 

GRANT DELETE, INSERT, SELECT, UPDATE ON bssos.adm_sub_ambitos TO bssos_app; 
GRANT SELECT ON bssos.adm_sub_ambitos TO bssos_read; 

GRANT DELETE, INSERT, SELECT, UPDATE ON bssos.adm_usuarios_x_sub_ambitos TO bssos_app; 
GRANT SELECT ON bssos.adm_usuarios_x_sub_ambitos TO bssos_read; 

GRANT DELETE, INSERT, SELECT, UPDATE ON bssos.repo_unidades_org TO bssos_app; 
GRANT SELECT ON bssos.repo_unidades_org TO bssos_read; 

GRANT DELETE, INSERT, SELECT, UPDATE ON bssos.adm_unidades_org_x_sub_ambitos TO bssos_app; 
GRANT SELECT ON bssos.adm_unidades_org_x_sub_ambitos TO bssos_read; 

GRANT DELETE, INSERT, SELECT, UPDATE ON bssos.repo_tipo_unidades_org TO bssos_app; 
GRANT SELECT ON bssos.repo_tipo_unidades_org TO bssos_read; 

GRANT SELECT ON bssos.v_repo_unidades_org_distritos  TO bssos_read;  
GRANT SELECT ON bssos.v_repo_unidades_org_distritos TO bssos_app; 

GRANT SELECT ON bssos.v_repo_unidades_org_areas  TO bssos_read;  
GRANT SELECT ON bssos.v_repo_unidades_org_areas TO bssos_app; 
		   
GRANT SELECT ON bssos.v_adm_usuarios_permisos  TO bssos_read;  
GRANT SELECT ON bssos.v_adm_usuarios_permisos TO bssos_app; 

		   

