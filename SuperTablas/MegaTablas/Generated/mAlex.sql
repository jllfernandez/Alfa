DROP TABLE "mAlex"."usuarios";
DROP TABLE "mAlex"."capabilityandrole";
DROP TABLE "mAlex"."roles";
DROP TABLE "mAlex"."capabilityes";
COMMIT;

CREATE TABLE "mAlex"."capabilityes"
(
id integer PRIMARY KEY,
code varchar(15) NOT NULL,
descr varchar(35) NOT NULL
)
;
COMMIT;

COMMIT;

COMMIT;

CREATE TABLE "mAlex"."roles"
(
id integer PRIMARY KEY,
code varchar(15) NOT NULL,
descr varchar(35) NOT NULL
)
;
COMMIT;

COMMIT;

COMMIT;

CREATE TABLE "mAlex"."capabilityandrole"
(
id_capability int4 NOT NULL,
id_rol int4  NOT NULL
)
;
COMMIT;

COMMIT;

COMMIT;

CREATE TABLE "mAlex"."usuarios"
(
id integer PRIMARY KEY,
id_rol int4 NOT NULL,
login varchar(15) NOT NULL,
pass varchar(15) NOT NULL,
fecha timestamp NOT NULL
)
;
COMMIT;

COMMIT;

COMMIT;
