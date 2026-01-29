CREATE TABLE Atlas.CAPABILITYES (
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	code varchar(15) NULL,
	descr varchar(35) NULL,
	CONSTRAINT "CAPABILITYES_pkey" PRIMARY KEY (id)
);

ALTER TABLE Atlas.CAPABILITYES OWNER TO matrix;
GRANT ALL ON TABLE Atlas.CAPABILITYES TO matrix;

commit;

CREATE TABLE Atlas.ROLES (
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	code varchar(15) NULL,
	descr varchar(35) NULL,
	CONSTRAINT "ROLES_pkey" PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE Atlas.ROLES OWNER TO matrix;
GRANT ALL ON TABLE Atlas.ROLES TO matrix;

commit;

CREATE TABLE Atlas.CAPABILITYANDROLE (
	id_capability int4 NOT NULL,
	id_role int4 NOT NULL
);

-- Permissions

ALTER TABLE Atlas.CAPABILITYANDROLE OWNER TO matrix;
GRANT ALL ON TABLE Atlas.CAPABILITYANDROLE TO matrix;

commit;

CREATE TABLE Atlas.USUARIOS (
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	id_rol int4 NOT NULL,
	login varchar(15) NULL,
	pass varchar(15) NULL
);

ALTER TABLE Atlas.USUARIOS OWNER TO matrix;
GRANT ALL ON TABLE Atlas.ROLES TO matrix;

COMMIT;	




CREATE TABLE IF NOT EXISTS Master.adm_perfiles (
	id_perfil INTEGER GENERATED ALWAYS AS identity NOT NULL,  PRIMARY KEY (id_perfil),

	cd_perfil varchar(30) NOT NULL,
	tx_perfil varchar(100) NOT NULL,
	tx_descripcion varchar(500) NULL,
	fec_alta timestamp NULL,
	fec_modif timestamp NULL,
	fec_baja timestamp NULL,
	audit_usuario varchar(200) NULL

)TABLESPACE ts_bssos_dat01; 


CREATE TABLE IF NOT EXISTS Master.adm_usuarios_x_perfiles (
	id_usuarios_x_perfiles INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL, PRIMARY KEY (id_usuarios_x_perfiles),
	
	fec_alta timestamp NULL,
	fec_modif timestamp NULL,
	fec_baja timestamp NULL,
	audit_usuario varchar(200) NULL,	
	id_usuario int4 NULL,
	id_perfil int4 NULL
	
)TABLESPACE ts_bssos_dat01; 


CREATE TABLE IF NOT EXISTS Master.adm_perfiles_x_roles (
	id_perfiles_x_roles INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL, PRIMARY KEY (id_perfiles_x_roles),
	
	flg_por_defecto boolean, 
	fec_alta timestamp NULL,
	fec_modif timestamp NULL,
	fec_baja timestamp NULL,
	audit_usuario varchar(200) NULL,	
	id_perfil int4 NULL,
	id_rol int4 NULL
	
)TABLESPACE ts_bssos_dat01; 


CREATE TABLE IF NOT EXISTS Master.adm_ambitos (
	id_ambito INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL, PRIMARY KEY (id_ambito),

	cd_ambito varchar(30) NOT NULL,
	tx_ambito varchar(100) NOT NULL,
	tx_descripcion varchar(500) NULL,
	fec_alta timestamp NULL,
	fec_modif timestamp NULL,
	fec_baja timestamp NULL,
	audit_usuario varchar(200) NULL

)TABLESPACE ts_bssos_dat01; 


CREATE TABLE IF NOT EXISTS Master.adm_tipo_sub_ambitos (
	id_tipo_sub_ambito INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL, PRIMARY KEY (id_tipo_sub_ambito),

	cd_tipo_sub_ambito varchar(30) NOT NULL,
	tx_tipo_sub_ambito varchar(200) NOT NULL,
	fec_alta timestamp NULL,
	fec_modif timestamp NULL,
	fec_baja timestamp NULL,
	audit_usuario varchar(200) NULL

)TABLESPACE ts_bssos_dat01; 



CREATE TABLE IF NOT EXISTS Master.adm_sub_ambitos (
	id_sub_ambito INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL, PRIMARY KEY (id_sub_ambito),

	cd_sub_ambito varchar(30) NOT NULL,
	tx_sub_ambito varchar(100) NOT NULL,
	tx_descripcion varchar(500) NULL,
	fec_alta timestamp NULL,
	fec_modif timestamp NULL,
	fec_baja timestamp NULL,
	audit_usuario varchar(200) NULL,	
	id_ambito int4 NULL,
	id_tipo_sub_ambito int4 NULL
	
)TABLESPACE ts_bssos_dat01; 


CREATE TABLE Master.adm_usuarios_x_sub_ambitos (
	id_usuario_x_sub_ambito INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL, PRIMARY KEY (id_usuario_x_sub_ambito),
	fec_alta timestamp NULL,
	fec_modif timestamp NULL,
	fec_baja timestamp NULL,
	audit_usuario varchar(200) NULL,
	id_usuario int4 NOT NULL,
	id_sub_ambito int4 NOT NULL
)TABLESPACE ts_bssos_dat01; 

CREATE TABLE IF NOT EXISTS Master.repo_tipo_unidades_org (
	id_tipo_unidad_org INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL, PRIMARY KEY (id_tipo_unidad_org),

	cd_tipo_unidad_org varchar(30) NOT NULL,
	tx_tipo_unidad_org varchar(200) NOT NULL,
	fec_alta timestamp NULL,
	fec_modif timestamp NULL,
	fec_baja timestamp NULL,
	audit_usuario varchar(200) NULL

)TABLESPACE ts_bssos_dat01; 

CREATE TABLE IF NOT EXISTS Master.repo_unidades_org (
	id_unidad_org INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL, PRIMARY KEY (id_unidad_org),

	ref_unidad_org varchar(30) NOT NULL,
	tx_unidad_org varchar(200) NOT NULL,
	fec_alta timestamp NULL,
	fec_modif timestamp NULL,
	fec_baja timestamp NULL,
	audit_usuario varchar(200) NULL,	
	id_tipo_unidad_org int4 NULL,
	id_padre int4 NULL

)TABLESPACE ts_bssos_dat01; 

CREATE TABLE IF NOT EXISTS Master.adm_unidades_org_x_sub_ambitos (
	id_adm_unidad_org_x_sub_ambito INTEGER GENERATED ALWAYS AS IDENTITY NOT NULL, PRIMARY KEY (id_adm_unidad_org_x_sub_ambito),
	
	fec_alta timestamp NULL,
	fec_modif timestamp NULL,
	fec_baja timestamp NULL,
	audit_usuario varchar(200) NULL,	
	id_unidad_org int4 NULL,
	id_sub_ambito int4 NULL
	
)TABLESPACE ts_bssos_dat01; 





ALTER TABLE Master.repo_profesionales add id_unidad_org INTEGER NULL;

ALTER TABLE Master.adm_roles add tx_descripcion varchar(500) NULL;
ALTER TABLE Master.adm_roles ALTER COLUMN cd_rol TYPE varchar(30) USING cd_rol::varchar(30);
ALTER TABLE Master.adm_roles ALTER COLUMN tx_rol TYPE varchar(100) USING tx_rol::varchar(100);

ALTER TABLE Master.adm_grupos_funcionales add tx_descripcion varchar(500) NULL;
ALTER TABLE Master.adm_grupos_funcionales ALTER COLUMN cd_grupo_funcional TYPE varchar(30) USING cd_grupo_funcional::varchar(30);
ALTER TABLE Master.adm_grupos_funcionales ALTER COLUMN tx_grupo_funcional TYPE varchar(100) USING tx_grupo_funcional::varchar(100);

CREATE MATERIALIZED VIEW Master.v_repo_unidades_org_distritos AS
	select distinct
	   CAST(1000000000 + floor(random() * 9000000000) AS bigint) as id_view,
	   distrito.id_unidad_org as id_distrito, distrito.ref_unidad_org as ref_distrito, distrito.tx_unidad_org as distrito,
       coordinacion.id_unidad_org as id_coordinacion, coordinacion.ref_unidad_org as ref_coordinacion, coordinacion.tx_unidad_org as coordinacion,
       servicio.id_unidad_org as id_servicio, servicio.ref_unidad_org as ref_servicio, servicio.tx_unidad_org as servicio,
       departamento.id_unidad_org as id_departamento, departamento.ref_unidad_org as ref_departamento, departamento.tx_unidad_org as departamento
	from repo_unidades_org distrito
	left join repo_unidades_org coordinacion on coordinacion.id_padre = distrito.id_unidad_org
	left join repo_unidades_org servicio on servicio.id_padre = coordinacion.id_unidad_org
	left join repo_unidades_org departamento on departamento.id_padre = servicio.id_unidad_org
	join repo_tipo_unidades_org tdistrito on tdistrito.id_tipo_unidad_org = distrito.id_tipo_unidad_org and tdistrito.cd_tipo_unidad_org = 'DIST'
	join repo_tipo_unidades_org tcoordinacion on tcoordinacion.id_tipo_unidad_org = coordinacion.id_tipo_unidad_org and tcoordinacion.cd_tipo_unidad_org = 'COOR'
	join repo_tipo_unidades_org tservicio on tservicio.id_tipo_unidad_org  = servicio.id_tipo_unidad_org and tservicio.cd_tipo_unidad_org = 'SERV'
	join repo_tipo_unidades_org tdepartamento on tdepartamento.id_tipo_unidad_org = departamento.id_tipo_unidad_org and tdepartamento.cd_tipo_unidad_org = 'DPTO';
	
		
CREATE MATERIALIZED VIEW Master.v_repo_unidades_org_areas AS
	select distinct
	   CAST(1000000000 + floor(random() * 9000000000) AS bigint) as id_view,
	   direccion.id_unidad_org as id_direccion, direccion.ref_unidad_org as ref_direccion, direccion.tx_unidad_org as direccion,
       subdireccion.id_unidad_org as id_subdireccion, subdireccion.ref_unidad_org as ref_subdireccion, subdireccion.tx_unidad_org as subdireccion,
       departamento.id_unidad_org as id_departamento, departamento.ref_unidad_org as ref_departamento, departamento.tx_unidad_org as departamento	
	from repo_unidad_org direccion
	left join repo_unidad_org subdireccion on subdireccion.id_padre = direccion.id_unidad_org
	left join repo_unidad_org departamento on departamento.id_padre = subdireccion.id_unidad_org
	join repo_tipo_unidad_org tdireccion on tdireccion.id_tipo_unidad_org = direccion.id_tipo_unidad_org and tdireccion.cd_tipo_unidad_org = 'DIR'
	join repo_tipo_unidad_org tsubdireccion on tsubdireccion.id_tipo_unidad_org  = subdireccion.id_tipo_unidad_org and tsubdireccion.cd_tipo_unidad_org = 'SDIR'
	join repo_tipo_unidad_org tdepartamento on tdepartamento.id_tipo_unidad_org = departamento.id_tipo_unidad_org and tdepartamento.cd_tipo_unidad_org = 'DPTO';
	


CREATE VIEW Master.v_adm_usuarios_permisos AS
SELECT DISTINCT 
	CAST(1000000000 + floor(random() * 9000000000) AS bigint) as id_view,
	usuarios.id_usuario,
    usuarios.cd_usuario AS cd,
    usuarios.tx_nombre AS nombre,
    usuarios.tx_apellido1 AS apellido1,
    usuarios.tx_apellido2 AS apellido2,
    usuarios.tx_nif,
    roles.id_rol,
    roles.cd_rol,
    grupos.id_grupo_funcional,
    grupos.cd_grupo_funcional,
    permisos.id_permiso,
    permisos.cd_permiso
   FROM adm_usuarios usuarios
     JOIN adm_usuarios_x_roles usuarios_roles ON usuarios_roles.id_usuario = usuarios.id_usuario
     JOIN adm_roles roles ON usuarios_roles.id_rol = roles.id_rol
     JOIN adm_roles_x_grupos_funcionales roles_grupos ON roles_grupos.id_rol = roles.id_rol
     JOIN adm_grupos_funcionales grupos ON grupos.id_grupo_funcional = roles_grupos.id_grupo_funcional
     JOIN adm_grupos_funcionales_x_permisos grupos_permisos ON grupos_permisos.id_grupo_funcional = grupos.id_grupo_funcional
     JOIN adm_permisos permisos ON permisos.id_permiso = grupos_permisos.id_permiso
  WHERE usuarios.fec_baja IS NULL AND usuarios_roles.fec_baja IS NULL AND roles.fec_baja IS NULL AND roles_grupos.fec_baja IS NULL AND grupos.fec_baja IS NULL AND grupos_permisos.fec_baja IS NULL AND permisos.fec_baja IS NULL;


ALTER TABLE Master.adm_catalogos ALTER COLUMN fec_alta TYPE timestamp USING fec_alta::timestamp;
ALTER TABLE Master.adm_catalogos ALTER COLUMN fec_modif TYPE timestamp USING fec_modif::timestamp;
ALTER TABLE Master.adm_catalogos ALTER COLUMN fec_baja TYPE timestamp USING fec_baja::timestamp;

ALTER TABLE Master.adm_grupos_funcionales ALTER COLUMN fec_alta TYPE timestamp USING fec_alta::timestamp;
ALTER TABLE Master.adm_grupos_funcionales ALTER COLUMN fec_modif TYPE timestamp USING fec_modif::timestamp;
ALTER TABLE Master.adm_grupos_funcionales ALTER COLUMN fec_baja TYPE timestamp USING fec_baja::timestamp;

ALTER TABLE Master.adm_grupos_funcionales_x_permisos ALTER COLUMN fec_alta TYPE timestamp USING fec_alta::timestamp;
ALTER TABLE Master.adm_grupos_funcionales_x_permisos ALTER COLUMN fec_modif TYPE timestamp USING fec_modif::timestamp;
ALTER TABLE Master.adm_grupos_funcionales_x_permisos ALTER COLUMN fec_baja TYPE timestamp USING fec_baja::timestamp;

ALTER TABLE Master.adm_permisos ALTER COLUMN fec_alta TYPE timestamp USING fec_alta::timestamp;
ALTER TABLE Master.adm_permisos ALTER COLUMN fec_modif TYPE timestamp USING fec_modif::timestamp;
ALTER TABLE Master.adm_permisos ALTER COLUMN fec_baja TYPE timestamp USING fec_baja::timestamp;

ALTER TABLE Master.adm_propiedades ALTER COLUMN fec_alta TYPE timestamp USING fec_alta::timestamp;
ALTER TABLE Master.adm_propiedades ALTER COLUMN fec_modif TYPE timestamp USING fec_modif::timestamp;
ALTER TABLE Master.adm_propiedades ALTER COLUMN fec_baja TYPE timestamp USING fec_baja::timestamp;

ALTER TABLE Master.adm_roles ALTER COLUMN fec_alta TYPE timestamp USING fec_alta::timestamp;
ALTER TABLE Master.adm_roles ALTER COLUMN fec_modif TYPE timestamp USING fec_modif::timestamp;
ALTER TABLE Master.adm_roles ALTER COLUMN fec_baja TYPE timestamp USING fec_baja::timestamp;

ALTER TABLE Master.adm_roles_x_grupos_funcionales ALTER COLUMN fec_alta TYPE timestamp USING fec_alta::timestamp;
ALTER TABLE Master.adm_roles_x_grupos_funcionales ALTER COLUMN fec_modif TYPE timestamp USING fec_modif::timestamp;
ALTER TABLE Master.adm_roles_x_grupos_funcionales ALTER COLUMN fec_baja TYPE timestamp USING fec_baja::timestamp;

ALTER TABLE Master.adm_roles_x_permisos ALTER COLUMN fec_alta TYPE timestamp USING fec_alta::timestamp;
ALTER TABLE Master.adm_roles_x_permisos ALTER COLUMN fec_modif TYPE timestamp USING fec_modif::timestamp;
ALTER TABLE Master.adm_roles_x_permisos ALTER COLUMN fec_baja TYPE timestamp USING fec_baja::timestamp;

ALTER TABLE Master.adm_tipo_catalogos ALTER COLUMN fec_alta TYPE timestamp USING fec_alta::timestamp;
ALTER TABLE Master.adm_tipo_catalogos ALTER COLUMN fec_baja TYPE timestamp USING fec_baja::timestamp;
ALTER TABLE Master.adm_tipo_catalogos ALTER COLUMN fec_modif TYPE timestamp USING fec_modif::timestamp;

ALTER TABLE Master.adm_usuarios ALTER COLUMN fec_alta TYPE timestamp USING fec_alta::timestamp;
ALTER TABLE Master.adm_usuarios ALTER COLUMN fec_modif TYPE timestamp USING fec_modif::timestamp;
ALTER TABLE Master.adm_usuarios ALTER COLUMN fec_baja TYPE timestamp USING fec_baja::timestamp;

ALTER TABLE Master.adm_usuarios_x_roles ALTER COLUMN fec_alta TYPE timestamp USING fec_alta::timestamp;
ALTER TABLE Master.adm_usuarios_x_roles ALTER COLUMN fec_modif TYPE timestamp USING fec_modif::timestamp;
ALTER TABLE Master.adm_usuarios_x_roles ALTER COLUMN fec_baja TYPE timestamp USING fec_baja::timestamp;

ALTER TABLE Master.adm_valores ALTER COLUMN fec_alta TYPE timestamp USING fec_alta::timestamp;
ALTER TABLE Master.adm_valores ALTER COLUMN fec_modif TYPE timestamp USING fec_modif::timestamp;
ALTER TABLE Master.adm_valores ALTER COLUMN fec_baja TYPE timestamp USING fec_baja::timestamp;

