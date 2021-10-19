/*
Created: 10/10/2021
Modified: 18/10/2021
Model: Oracle 18c
Database: Oracle 18c
*/


-- Create sequences section -------------------------------------------------

CREATE SEQUENCE tbl_categorias_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_restaurantes_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_salones_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_proxorden_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_codigosdesc_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_mesas_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_empleados_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_roles_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_ordenes_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

-- Create tables section -------------------------------------------------

-- Table tbl_productos

CREATE TABLE tbl_productos(
  pro_id Number NOT NULL,
  res_id Number NOT NULL,
  pro_nombre Varchar2(50 ) NOT NULL,
  pro_detalle Varchar2(50 ) NOT NULL,
  pro_foto Blob NOT NULL,
  pro_costo Number NOT NULL,
  pro_cantidad Number NOT NULL,
  pro_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table tbl_productos

ALTER TABLE tbl_productos ADD CONSTRAINT PK_tbl_productos PRIMARY KEY (pro_id)
;

-- Table tbl_empleados

CREATE TABLE tbl_empleados(
  emp_id Number NOT NULL,
  emp_nombre Varchar2(50 ) NOT NULL,
  emp_usuario Varchar2(50 ) NOT NULL,
  emp_contra Varchar2(50 ) NOT NULL,
  emp_foto Blob NOT NULL,
  emp_apelllido Varchar2(50 ) NOT NULL,
  emp__version Number DEFAULT 1 NOT NULL,
  rol_id Number NOT NULL,
  res_id Number NOT NULL
)
;

-- Add keys for table tbl_empleados

ALTER TABLE tbl_empleados ADD CONSTRAINT PK_tbl_empleados PRIMARY KEY (emp_id)
;

-- Table tbl_restaurantes

CREATE TABLE tbl_restaurantes(
  res_id Number NOT NULL,
  res_nombre Varchar2(50 ) NOT NULL,
  res_detalle Varchar2(50 ) NOT NULL,
  res_direccion Varchar2(50 ) NOT NULL,
  res_correo Varchar2(50 ) NOT NULL,
  res_foto Blob NOT NULL,
  res_version Number DEFAULT 1 NOT NULL,
  res_ven Number NOT NULL,
  res_serv Number NOT NULL
)
;

-- Add keys for table tbl_restaurantes

ALTER TABLE tbl_restaurantes ADD CONSTRAINT PK_tbl_restaurantes PRIMARY KEY (res_id)
;

-- Table tbl_categorias

CREATE TABLE tbl_categorias(
  cat_id Number NOT NULL,
  cat_nombre Varchar2(50 ) NOT NULL,
  cat_descripcion Varchar2(50 ) NOT NULL,
  cat_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table tbl_categorias

ALTER TABLE tbl_categorias ADD CONSTRAINT PK_tbl_categorias PRIMARY KEY (cat_id)
;

-- Table tbl_categoriasxProductos

CREATE TABLE tbl_categoriasxProductos(
  prod_id Number NOT NULL,
  cat_id Number NOT NULL
)
;

-- Table tbl_res_rol

CREATE TABLE tbl_res_rol(
  rol_id Number NOT NULL,
  rol_nombre Varchar2(30 ) NOT NULL,
  rol_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table tbl_res_rol

ALTER TABLE tbl_res_rol ADD CONSTRAINT PK_tbl_res_rol PRIMARY KEY (rol_id)
;

-- Table tbl_salones

CREATE TABLE tbl_salones(
  sal_id Number NOT NULL,
  sal_nombre Varchar2(50 ) NOT NULL,
  sal_imagen Blob NOT NULL,
  sal_barraOmesa Varchar2(1 ) NOT NULL,
  res_id Number NOT NULL,
  sal_version Number DEFAULT 1 NOT NULL,
  CONSTRAINT tbl_salones_ck01 CHECK (sal_barraOmesa IN ('B','M'))
)
;

-- Add keys for table tbl_salones

ALTER TABLE tbl_salones ADD CONSTRAINT PK_tbl_salones PRIMARY KEY (sal_id)
;

-- Table tbl_mesas

CREATE TABLE tbl_mesas(
  mesa_id Number NOT NULL,
  mesa_nombre Varchar2(50 ) NOT NULL,
  mesa_estado Varchar2(1 ) NOT NULL,
  mesa_posX Number NOT NULL,
  mesa_posY Number NOT NULL,
  sal_id Number NOT NULL,
  mesa_version Number DEFAULT 1 NOT NULL,
  CONSTRAINT tbl_mesas_ck01 CHECK (mesa_estado IN ('D','O'))
)
;

-- Add keys for table tbl_mesas

ALTER TABLE tbl_mesas ADD CONSTRAINT PK_tbl_mesas PRIMARY KEY (mesa_id)
;

-- Table tbl_ordenes

CREATE TABLE tbl_ordenes(
  ord_id Number NOT NULL,
  emp_id Number NOT NULL,
  mesa_id Number NOT NULL,
  fac_total Number NOT NULL,
  fac_fecha Date NOT NULL,
  fac_version Number DEFAULT 1 NOT NULL,
  fac_desc Number NOT NULL
)
;

-- Add keys for table tbl_ordenes

ALTER TABLE tbl_ordenes ADD CONSTRAINT PK_tbl_ordenes PRIMARY KEY (ord_id)
;

-- Table tbl_productosxOrden

CREATE TABLE tbl_productosxOrden(
  pxo_id Number NOT NULL,
  ord_id Number NOT NULL,
  pro_id Number NOT NULL,
  pxo_cantidad Number NOT NULL,
  pxo_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table tbl_productosxOrden

ALTER TABLE tbl_productosxOrden ADD CONSTRAINT PK_tbl_productosxOrden PRIMARY KEY (pxo_id)
;

-- Table tbl_codigosdesc

CREATE TABLE tbl_codigosdesc(
  cod_id Number NOT NULL,
  cod_nombre Varchar2(30 ) NOT NULL,
  cod_cant Number NOT NULL,
  cod_version Number DEFAULT 1 NOT NULL,
  res_id Number
)
;

-- Create indexes for table tbl_codigosdesc

CREATE INDEX IX_Relationship39 ON tbl_codigosdesc (res_id)
;

-- Add keys for table tbl_codigosdesc

ALTER TABLE tbl_codigosdesc ADD CONSTRAINT PK_tbl_codigosdesc PRIMARY KEY (cod_id)
;


-- Trigger for sequence tbl_productos_seq01 for column pro_id in table tbl_productos ---------
CREATE OR REPLACE TRIGGER tbl_productos_tgr01 BEFORE INSERT
ON tbl_productos FOR EACH ROW
BEGIN
  IF :new.pro_id IS NULL OR :new.pro_id <=1 THEN
  :new.pro_id := tbl_productos_tgr01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_productos_tgr02 AFTER UPDATE OF pro_id
ON tbl_productos FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column pro_id in table tbl_productos as it uses sequence.');
END;
/

-- Trigger for sequence tbl_empleados_seq01 for column emp_id in table tbl_empleados ---------
CREATE OR REPLACE TRIGGER tbl_empleados_tgr01 BEFORE INSERT
ON tbl_empleados FOR EACH ROW
BEGIN
  IF :new.emp_id IS NULL OR :new.emp_id <=1 THEN
  :new.emp_id := tbl_empleados_tgr01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_empleados_tgr02 AFTER UPDATE OF emp_id
ON tbl_empleados FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column emp_id in table tbl_empleados as it uses sequence.');
END;
/

-- Trigger for sequence tbl_restaurantes_seq01 for column res_id in table tbl_restaurantes ---------
CREATE OR REPLACE TRIGGER tbl_restaurantes_tgr01 BEFORE INSERT
ON tbl_restaurantes FOR EACH ROW
BEGIN
  IF :new.res_id IS NULL OR :new.res_id <=1 THEN
  :new.res_id := tbl_restaurantes_tgr01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_restaurantes_tgr02 AFTER UPDATE OF res_id
ON tbl_restaurantes FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column res_id in table tbl_restaurantes as it uses sequence.');
END;
/

-- Trigger for sequence tbl_categorias_seq01 for column cat_id in table tbl_categorias ---------
CREATE OR REPLACE TRIGGER tbl_categorias_tgr01 BEFORE INSERT
ON tbl_categorias FOR EACH ROW
BEGIN
  IF :new.cat_id IS NULL OR :new.cat_id<=1 THEN
  :new.cat_id := tbl_categorias_tgr01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_categorias_tgr02 AFTER UPDATE OF cat_id
ON tbl_categorias FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column cat_id in table tbl_categorias as it uses sequence.');
END;
/

-- Trigger for sequence tbl_roles_seq01 for column rol_id in table tbl_res_rol ---------
CREATE OR REPLACE TRIGGER tbl_roles_tgr01 BEFORE INSERT
ON tbl_res_rol FOR EACH ROW
BEGIN
  IF :new.rol_id IS NULL OR :new.rol_id <=1 THEN
  :new.rol_id := tbl_roles_tgr01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_roles_tgr02 AFTER UPDATE OF rol_id
ON tbl_res_rol FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column rol_id in table tbl_res_rol as it uses sequence.');
END;
/

-- Trigger for sequence tbl_salones_seq01 for column sal_id in table tbl_salones ---------
CREATE OR REPLACE TRIGGER tbl_salones_tgr01 BEFORE INSERT
ON tbl_salones FOR EACH ROW
BEGIN
  IF :new.sal_id IS NULL OR :new.sal_id<=1 THEN
  :new.sal_id := tbl_salones_tgr01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_salones_tgr02 AFTER UPDATE OF sal_id
ON tbl_salones FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column sal_id in table tbl_salones as it uses sequence.');
END;
/

-- Trigger for sequence tbl_mesas_seq01 for column mesa_id in table tbl_mesas ---------
CREATE OR REPLACE TRIGGER tbl_mesas_tgr01 BEFORE INSERT
ON tbl_mesas FOR EACH ROW
BEGIN
  IF :new.mesa_id IS NULL OR :new.mesa_id <=1 THEN
  :new.mesa_id := tbl_mesas_tgr01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_mesas_tgr02 AFTER UPDATE OF mesa_id
ON tbl_mesas FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column mesa_id in table tbl_mesas as it uses sequence.');
END;
/

-- Trigger for sequence tbl_ordenes_seq01 for column ord_id in table tbl_ordenes ---------
CREATE OR REPLACE TRIGGER tbl_ordenes_tgr01 BEFORE INSERT
ON tbl_ordenes FOR EACH ROW
BEGIN
  IF :new.ord_id IS NULL OR :new.ord_id <=1 THEN
  :new.ord_id := tbl_ordenes_tgr01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_ordenes_tgr02 AFTER UPDATE OF ord_id
ON tbl_ordenes FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column ord_id in table tbl_ordenes as it uses sequence.');
END;
/

-- Trigger for sequence tbl_proxorden_seq01 for column pxo_id in table tbl_productosxOrden ---------
CREATE OR REPLACE TRIGGER tbl_proxorden_tgr01 BEFORE INSERT
ON tbl_productosxOrden FOR EACH ROW
BEGIN
  IF :new.pxo_id IS NULL OR :new.pxo_id <=1 THEN
  :new.pxo_id := tbl_proxorden_tgr01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_proxorden_tgr02 AFTER UPDATE OF pxo_id
ON tbl_productosxOrden FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column pxo_id in table tbl_productosxOrden as it uses sequence.');
END;
/
-- Trigger for sequence tbl_codigosdesc_seq01 for column cod_id in table tbl_codigosdesc ---------
CREATE OR REPLACE TRIGGER tbl_codigosdesc_tgr01 BEFORE INSERT
ON tbl_codigosdesc FOR EACH ROW
BEGIN 
  IF :new.cod_id IS NULL OR :new.cod_id <=1 THEN
  :new.cod_id := tbl_codigosdesc_seq01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_codigosdesc_tgr02 AFTER UPDATE OF cod_id
ON tbl_codigosdesc FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column cod_id in table tbl_codigosdesc as it uses sequence.');
END;
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE tbl_categoriasxProductos ADD CONSTRAINT rel_cat_pro FOREIGN KEY (cat_id) REFERENCES tbl_categorias (cat_id)
;



ALTER TABLE tbl_categoriasxProductos ADD CONSTRAINT rel_pro_cat FOREIGN KEY (prod_id) REFERENCES tbl_productos (pro_id)
;



ALTER TABLE tbl_ordenes ADD CONSTRAINT rel_mes_ord FOREIGN KEY (mesa_id) REFERENCES tbl_mesas (mesa_id)
;



ALTER TABLE tbl_ordenes ADD CONSTRAINT rel_emp_ord FOREIGN KEY (emp_id) REFERENCES tbl_empleados (emp_id)
;



ALTER TABLE tbl_empleados ADD CONSTRAINT rel_res_emp FOREIGN KEY (res_id) REFERENCES tbl_restaurantes (res_id)
;



ALTER TABLE tbl_empleados ADD CONSTRAINT rel_rol_emp FOREIGN KEY (rol_id) REFERENCES tbl_res_rol (rol_id)
;



ALTER TABLE tbl_salones ADD CONSTRAINT rel_res_sal FOREIGN KEY (res_id) REFERENCES tbl_restaurantes (res_id)
;



ALTER TABLE tbl_mesas ADD CONSTRAINT rel_sal_mes FOREIGN KEY (sal_id) REFERENCES tbl_salones (sal_id)
;



ALTER TABLE tbl_productos ADD CONSTRAINT Relationship33 FOREIGN KEY (res_id) REFERENCES tbl_restaurantes (res_id)
;



ALTER TABLE tbl_productosxOrden ADD CONSTRAINT rel_ord_pro FOREIGN KEY (ord_id) REFERENCES tbl_ordenes (ord_id)
;



ALTER TABLE tbl_productosxOrden ADD CONSTRAINT rel_pro_ord FOREIGN KEY (pro_id) REFERENCES tbl_productos (pro_id)
;



ALTER TABLE tbl_codigosdesc ADD CONSTRAINT rel_res_cod FOREIGN KEY (res_id) REFERENCES tbl_restaurantes (res_id)
;





