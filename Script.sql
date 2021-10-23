/*
Created: 10/10/2021
Modified: 22/10/2021
Model: Oracle 18c
Database: Oracle 18c
*/


-- Drop relationships section -------------------------------------------------

ALTER TABLE tbl_orden DROP CONSTRAINT rel_cod_orden
;
ALTER TABLE tbl_codigodesc DROP CONSTRAINT rel_res_cod
;
ALTER TABLE tbl_productosxOrden DROP CONSTRAINT rel_pro_ord
;
ALTER TABLE tbl_productosxOrden DROP CONSTRAINT rel_ord_pro
;
ALTER TABLE tbl_producto DROP CONSTRAINT rel_res_pro
;
ALTER TABLE tbl_mesa DROP CONSTRAINT rel_sal_mes
;
ALTER TABLE tbl_salon DROP CONSTRAINT rel_res_sal
;
ALTER TABLE tbl_empleado DROP CONSTRAINT rel_rol_emp
;
ALTER TABLE tbl_empleado DROP CONSTRAINT rel_res_emp
;
ALTER TABLE tbl_orden DROP CONSTRAINT rel_emp_ord
;
ALTER TABLE tbl_orden DROP CONSTRAINT rel_mes_ord
;
ALTER TABLE tbl_categoriaxProducto DROP CONSTRAINT rel_pro_cat
;
ALTER TABLE tbl_categoriaxProducto DROP CONSTRAINT rel_cat_pro
;




-- Drop keys for tables section -------------------------------------------------

ALTER TABLE tbl_codigodesc DROP CONSTRAINT PK_tbl_codigodesc
;
ALTER TABLE tbl_productosxOrden DROP CONSTRAINT PK_tbl_productosxOrden
;
ALTER TABLE tbl_orden DROP CONSTRAINT PK_tbl_orden
;
ALTER TABLE tbl_mesa DROP CONSTRAINT PK_tbl_mesa
;
ALTER TABLE tbl_salon DROP CONSTRAINT PK_tbl_salon
;
ALTER TABLE tbl_res_rol DROP CONSTRAINT PK_tbl_res_rol
;
ALTER TABLE tbl_categoria DROP CONSTRAINT PK_tbl_categoria
;
ALTER TABLE tbl_restaurante DROP CONSTRAINT PK_tbl_restaurante
;
ALTER TABLE tbl_empleado DROP CONSTRAINT PK_tbl_empleado
;
ALTER TABLE tbl_producto DROP CONSTRAINT PK_tbl_producto
;


-- Drop indexes section -------------------------------------------------

DROP INDEX IX_Relationship39
;
DROP INDEX IX_Relationship2
;


-- Drop tables section ---------------------------------------------------

DROP TABLE tbl_codigodesc
;
DROP TABLE tbl_productosxOrden
;
DROP TABLE tbl_orden
;
DROP TABLE tbl_mesa
;
DROP TABLE tbl_salon
;
DROP TABLE tbl_res_rol
;
DROP TABLE tbl_categoriaxProducto
;
DROP TABLE tbl_categoria
;
DROP TABLE tbl_restaurante
;
DROP TABLE tbl_empleado
;
DROP TABLE tbl_producto
;

-- Drop sequences section --------------------------------------------------- 

DROP SEQUENCE tbl_orden_seq01
;
DROP SEQUENCE tbl_rol_seq01
;
DROP SEQUENCE tbl_empleado_seq01
;
DROP SEQUENCE tbl_mesa_seq01
;
DROP SEQUENCE tbl_codigodesc_seq01
;
DROP SEQUENCE tbl_proxorden_seq01
;
DROP SEQUENCE tbl_salon_seq01
;
DROP SEQUENCE tbl_restaurante_seq01
;
DROP SEQUENCE tbl_categoria_seq01
;

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE tbl_categoria_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_restaurante_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_salon_seq01
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

CREATE SEQUENCE tbl_codigodesc_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_mesa_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_empleado_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_rol_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

CREATE SEQUENCE tbl_orden_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCACHE
;

-- Create tables section -------------------------------------------------

-- Table tbl_producto

CREATE TABLE tbl_producto(
  pro_id Number NOT NULL,
  pro_nombre Varchar2(50 ) NOT NULL,
  pro_nombrecorto Varchar2(10 ) NOT NULL,
  pro_detalle Varchar2(50 ) NOT NULL,
  pro_foto Blob NOT NULL,
  pro_costo Number NOT NULL,
  pro_cantidad Number NOT NULL,
  pro_acceso_rapido Varchar2(1 ) DEFAULT 'N' NOT NULL
        CONSTRAINT tbl_producto_ck01 CHECK (pro_acceso_rapido IN ('N','S')),
  pro_cantidadv Number NOT NULL,
  res_id Number NOT NULL,
  pro_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table tbl_producto

ALTER TABLE tbl_producto ADD CONSTRAINT PK_tbl_producto PRIMARY KEY (pro_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN tbl_producto.pro_id IS 'Id del producto'
;
COMMENT ON COLUMN tbl_producto.pro_nombre IS 'Nombre del producto'
;
COMMENT ON COLUMN tbl_producto.pro_nombrecorto IS 'Nombre corto del producto'
;
COMMENT ON COLUMN tbl_producto.pro_detalle IS 'Detalle  del producto'
;
COMMENT ON COLUMN tbl_producto.pro_foto IS 'Foto  del producto'
;
COMMENT ON COLUMN tbl_producto.pro_costo IS 'Costo del producto'
;
COMMENT ON COLUMN tbl_producto.pro_cantidad IS 'Cantidad del producto'
;
COMMENT ON COLUMN tbl_producto.pro_acceso_rapido IS 'Acceso Rapido  del producto'
;
COMMENT ON COLUMN tbl_producto.pro_cantidadv IS 'Cantidad Vendida'
;
COMMENT ON COLUMN tbl_producto.res_id IS 'ID del restaurante'
;
COMMENT ON COLUMN tbl_producto.pro_version IS 'Version del producto'
;

-- Table tbl_empleado

CREATE TABLE tbl_empleado(
  emp_id Number NOT NULL,
  emp_nombre Varchar2(50 ) NOT NULL,
  emp_usuario Varchar2(50 ) NOT NULL,
  emp_contra Varchar2(50 ) NOT NULL,
  emp_foto Blob NOT NULL,
  emp_apelllido Varchar2(50 ) NOT NULL,
  rol_id Number NOT NULL,
  res_id Number NOT NULL,
  emp__version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table tbl_empleado

ALTER TABLE tbl_empleado ADD CONSTRAINT PK_tbl_empleado PRIMARY KEY (emp_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN tbl_empleado.emp_id IS 'ID del empleado'
;
COMMENT ON COLUMN tbl_empleado.emp_nombre IS 'Nombre del empleado'
;
COMMENT ON COLUMN tbl_empleado.emp_usuario IS 'Usuario del empleado'
;
COMMENT ON COLUMN tbl_empleado.emp_contra IS 'Cotrase�a del empleado'
;
COMMENT ON COLUMN tbl_empleado.emp_foto IS 'Foto del empleado'
;
COMMENT ON COLUMN tbl_empleado.emp_apelllido IS 'Apellido del empleado'
;
COMMENT ON COLUMN tbl_empleado.rol_id IS 'ID del rol'
;
COMMENT ON COLUMN tbl_empleado.res_id IS 'ID del restaurante'
;
COMMENT ON COLUMN tbl_empleado.emp__version IS 'Version del empleado'
;

-- Table tbl_restaurante

CREATE TABLE tbl_restaurante(
  res_id Number NOT NULL,
  res_nombre Varchar2(50 ) NOT NULL,
  res_detalle Varchar2(50 ) NOT NULL,
  res_direccion Varchar2(50 ) NOT NULL,
  res_correo Varchar2(50 ) NOT NULL,
  res_foto Blob NOT NULL,
  res_impv Number NOT NULL,
  res_serv Number NOT NULL,
  res_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table tbl_restaurante

ALTER TABLE tbl_restaurante ADD CONSTRAINT PK_tbl_restaurante PRIMARY KEY (res_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN tbl_restaurante.res_id IS 'ID del restaurante'
;
COMMENT ON COLUMN tbl_restaurante.res_nombre IS 'Nombre del restaurante'
;
COMMENT ON COLUMN tbl_restaurante.res_detalle IS 'Detalle del restaurante'
;
COMMENT ON COLUMN tbl_restaurante.res_direccion IS 'Direccion del restaurante'
;
COMMENT ON COLUMN tbl_restaurante.res_correo IS 'Correo  del restaurante'
;
COMMENT ON COLUMN tbl_restaurante.res_foto IS 'Foto del restaurante'
;
COMMENT ON COLUMN tbl_restaurante.res_impv IS 'Impuesto de venta maximo'
;
COMMENT ON COLUMN tbl_restaurante.res_serv IS 'Impuesto de servicio maximo'
;
COMMENT ON COLUMN tbl_restaurante.res_version IS 'Version del resturante'
;

-- Table tbl_categoria

CREATE TABLE tbl_categoria(
  cat_id Number NOT NULL,
  cat_nombre Varchar2(50 ) NOT NULL,
  cat_detalle Varchar2(50 ) NOT NULL,
  cat_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table tbl_categoria

ALTER TABLE tbl_categoria ADD CONSTRAINT PK_tbl_categoria PRIMARY KEY (cat_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN tbl_categoria.cat_id IS 'ID de la categoria'
;
COMMENT ON COLUMN tbl_categoria.cat_nombre IS 'Nombre de la categoria'
;
COMMENT ON COLUMN tbl_categoria.cat_detalle IS 'Detalle de la categoria'
;
COMMENT ON COLUMN tbl_categoria.cat_version IS 'Version de la categoria'
;

-- Table tbl_categoriaxProducto

CREATE TABLE tbl_categoriaxProducto(
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

-- Table and Columns comments section

COMMENT ON COLUMN tbl_res_rol.rol_id IS 'ID del rol'
;
COMMENT ON COLUMN tbl_res_rol.rol_nombre IS 'Nombre del rol'
;
COMMENT ON COLUMN tbl_res_rol.rol_version IS 'Version del rol'
;

-- Table tbl_salon

CREATE TABLE tbl_salon(
  sal_id Number NOT NULL,
  sal_nombre Varchar2(50 ) NOT NULL,
  sal_imagen Blob NOT NULL,
  sal_barraOmesa Varchar2(1 ) NOT NULL,
  res_id Number NOT NULL,
  sal_version Number DEFAULT 1 NOT NULL,
  CONSTRAINT tbl_salon_ck01 CHECK (sal_barraOmesa IN ('B','M'))
)
;

-- Add keys for table tbl_salon

ALTER TABLE tbl_salon ADD CONSTRAINT PK_tbl_salon PRIMARY KEY (sal_id)
;

-- Table tbl_mesa

CREATE TABLE tbl_mesa(
  mesa_id Number NOT NULL,
  mesa_nombre Varchar2(50 ) NOT NULL,
  mesa_estado Varchar2(1 ) NOT NULL,
  mesa_posX Number NOT NULL,
  mesa_posY Number NOT NULL,
  sal_id Number NOT NULL,
  mesa_version Number DEFAULT 1 NOT NULL,
  CONSTRAINT tbl_mesa_ck01 CHECK (mesa_estado IN ('D','O'))
)
;

-- Add keys for table tbl_mesa

ALTER TABLE tbl_mesa ADD CONSTRAINT PK_tbl_mesa PRIMARY KEY (mesa_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN tbl_mesa.mesa_id IS 'ID de la mesa'
;
COMMENT ON COLUMN tbl_mesa.mesa_nombre IS 'Nombre de la mesa'
;
COMMENT ON COLUMN tbl_mesa.mesa_estado IS 'Estado de la mesa'
;
COMMENT ON COLUMN tbl_mesa.mesa_posX IS 'Pos X de la mesa'
;
COMMENT ON COLUMN tbl_mesa.mesa_posY IS 'Pos Y de la mesa'
;
COMMENT ON COLUMN tbl_mesa.sal_id IS 'ID del salon'
;
COMMENT ON COLUMN tbl_mesa.mesa_version IS 'Version de la mesa'
;

-- Table tbl_orden

CREATE TABLE tbl_orden(
  ord_id Number NOT NULL,
  fac_total Number NOT NULL,
  fac_fecha Date NOT NULL,
  fac_desc Number NOT NULL,
  emp_id Number NOT NULL,
  mesa_id Number NOT NULL,
  cod_id Number,
  fac_version Number DEFAULT 1 NOT NULL
)
;

-- Create indexes for table tbl_orden

CREATE INDEX IX_Relationship2 ON tbl_orden (cod_id)
;

-- Add keys for table tbl_orden

ALTER TABLE tbl_orden ADD CONSTRAINT PK_tbl_orden PRIMARY KEY (ord_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN tbl_orden.ord_id IS 'ID del la orden'
;
COMMENT ON COLUMN tbl_orden.fac_total IS 'Total FInal del la orden'
;
COMMENT ON COLUMN tbl_orden.fac_fecha IS 'Fecha del la orden'
;
COMMENT ON COLUMN tbl_orden.fac_desc IS 'Descuento propio de la factura'
;
COMMENT ON COLUMN tbl_orden.emp_id IS 'ID del empleado'
;
COMMENT ON COLUMN tbl_orden.mesa_id IS 'ID de la mesa'
;
COMMENT ON COLUMN tbl_orden.cod_id IS 'Cod descuento'
;
COMMENT ON COLUMN tbl_orden.fac_version IS 'Version  del la orden'
;

-- Table tbl_productosxOrden

CREATE TABLE tbl_productosxOrden(
  pxo_id Number NOT NULL,
  pxo_cantidad Number NOT NULL,
  ord_id Number NOT NULL,
  pro_id Number NOT NULL,
  pxo_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table tbl_productosxOrden

ALTER TABLE tbl_productosxOrden ADD CONSTRAINT PK_tbl_productosxOrden PRIMARY KEY (pxo_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN tbl_productosxOrden.pxo_id IS 'ID del producto por orden'
;
COMMENT ON COLUMN tbl_productosxOrden.pxo_cantidad IS 'Cantidad del producto'
;
COMMENT ON COLUMN tbl_productosxOrden.ord_id IS 'ID de la orden'
;
COMMENT ON COLUMN tbl_productosxOrden.pro_id IS 'Producto id'
;
COMMENT ON COLUMN tbl_productosxOrden.pxo_version IS 'Version de la Orden'
;

-- Table tbl_codigodesc

CREATE TABLE tbl_codigodesc(
  cod_id Number NOT NULL,
  cod_nombre Varchar2(30 ) NOT NULL,
  cod_desc Number NOT NULL,
  cod_url Varchar2(50 ) NOT NULL,
  cod_cant Number NOT NULL,
  res_id Number,
  cod_version Number DEFAULT 1 NOT NULL
)
;

-- Create indexes for table tbl_codigodesc

CREATE INDEX IX_Relationship39 ON tbl_codigodesc (res_id)
;

-- Add keys for table tbl_codigodesc

ALTER TABLE tbl_codigodesc ADD CONSTRAINT PK_tbl_codigodesc PRIMARY KEY (cod_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN tbl_codigodesc.cod_id IS 'ID del codigo de descuento'
;
COMMENT ON COLUMN tbl_codigodesc.cod_nombre IS 'Nombre del codigo de descuento'
;
COMMENT ON COLUMN tbl_codigodesc.cod_desc IS 'Descuento del codigo de descuento'
;
COMMENT ON COLUMN tbl_codigodesc.cod_url IS 'codigo  del codigo de descuento'
;
COMMENT ON COLUMN tbl_codigodesc.cod_cant IS 'Cantidad del codigo de descuento'
;
COMMENT ON COLUMN tbl_codigodesc.res_id IS 'ID del restaurante'
;
COMMENT ON COLUMN tbl_codigodesc.cod_version IS 'Version del codigo de descuento'
;

-- Trigger for sequence tbl_producto_seq01 for column pro_id in table tbl_producto ---------
CREATE OR REPLACE TRIGGER tbl_producto_tgr01 BEFORE INSERT
ON tbl_producto FOR EACH ROW
BEGIN
  IF :new.pro_id IS NULL OR :new.pro_id <=1 THEN
  :new.pro_id := tbl_producto_seq01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_producto_tgr02 AFTER UPDATE OF pro_id
ON tbl_producto FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column pro_id in table tbl_producto as it uses sequence.');
END;
/

-- Trigger for sequence tbl_empleado_seq01 for column emp_id in table tbl_empleado ---------
CREATE OR REPLACE TRIGGER tbl_empleado_tgr01 BEFORE INSERT
ON tbl_empleado FOR EACH ROW
BEGIN
  IF :new.emp_id IS NULL OR :new.emp_id <=1 THEN
  :new.emp_id := tbl_empleado_seq01.nextval;
  END IF;
END;
;
/
CREATE OR REPLACE TRIGGER tbl_empleado_tgr02 AFTER UPDATE OF emp_id
ON tbl_empleado FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column emp_id in table tbl_empleados as it uses sequence.');
END;
/

-- Trigger for sequence tbl_restaurante_seq01 for column res_id in table tbl_restaurante ---------
CREATE OR REPLACE TRIGGER tbl_restaurante_tgr01 BEFORE INSERT
ON tbl_restaurante FOR EACH ROW
BEGIN
  IF :new.res_id IS NULL OR :new.res_id <=1 THEN
  :new.res_id := tbl_restaurante_seq01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_restaurante_tgr02 AFTER UPDATE OF res_id
ON tbl_restaurante FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column res_id in table tbl_restaurante as it uses sequence.');
END;
/

-- Trigger for sequence tbl_categoria_seq01 for column cat_id in table tbl_categoria ---------
CREATE OR REPLACE TRIGGER tbl_categoria_tgr01 BEFORE INSERT
ON tbl_categoria FOR EACH ROW
BEGIN
  IF :new.cat_id IS NULL OR :new.cat_id <=1 THEN 
  :new.cat_id := tbl_categoria_seq01.nextval;
  END IF;
END;
;
/
CREATE OR REPLACE TRIGGER tbl_categoria_tgr02 AFTER UPDATE OF cat_id
ON tbl_categoria FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column cat_id in table tbl_categoria as it uses sequence.');
END;
/

-- Trigger for sequence tbl_rol_seq01 for column rol_id in table tbl_res_rol ---------
CREATE OR REPLACE TRIGGER tbl_rol_tgr01 BEFORE INSERT
ON tbl_res_rol FOR EACH ROW
BEGIN
  IF :new.rol_id IS NULL OR :new.rol_id <=1 THEN
  :new.rol_id := tbl_rol_seq01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_rol_tgr02 AFTER UPDATE OF rol_id
ON tbl_res_rol FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column rol_id in table tbl_res_rol as it uses sequence.');
END;
/

-- Trigger for sequence tbl_salon_seq01 for column sal_id in table tbl_salon ---------
CREATE OR REPLACE TRIGGER tbl_salon_tgr01 BEFORE INSERT
ON tbl_salon FOR EACH ROW
BEGIN
  IF :new.sal_id IS NULL OR :new.sal_id <=1 THEN
  :new.sal_id := tbl_salon_seq01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_salon_tgr02 AFTER UPDATE OF sal_id
ON tbl_salon FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column sal_id in table tbl_salon as it uses sequence.');
END;
/

-- Trigger for sequence tbl_mesa_seq01 for column mesa_id in table tbl_mesa ---------
CREATE OR REPLACE TRIGGER tbl_mesa_tgr01 BEFORE INSERT
ON tbl_mesa FOR EACH ROW
BEGIN
  IF :new.mesa_id IS NULL OR :new.mesa_id <=1 THEN
  :new.mesa_id := tbl_mesa_seq01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_mesa_tgr02 AFTER UPDATE OF mesa_id
ON tbl_mesa FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column mesa_id in table tbl_mesa as it uses sequence.');
END;
/

-- Trigger for sequence tbl_orden_seq01 for column ord_id in table tbl_orden ---------
CREATE OR REPLACE TRIGGER tbl_orden_tgr01 BEFORE INSERT
ON tbl_orden FOR EACH ROW
BEGIN
  IF :new.ord_id IS NULL OR :new.ord_id <=1 THEN
  :new.ord_id := tbl_orden_seq01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_orden_tgr02 AFTER UPDATE OF ord_id
ON tbl_orden FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column ord_id in table tbl_orden as it uses sequence.');
END;
/

-- Trigger for sequence tbl_proxorden_seq01 for column pxo_id in table tbl_productosxOrden ---------
CREATE OR REPLACE TRIGGER tbl_proxorden_tgr01 BEFORE INSERT
ON tbl_productosxOrden FOR EACH ROW
BEGIN
  IF :new.pxo_id IS NULL OR :new.pxo_id <=1 THEN
  :new.pxo_id := tbl_proxorden_seq01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_proxorden_tgr02 AFTER UPDATE OF pxo_id
ON tbl_productosxOrden FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column pxo_id in table tbl_productosxOrden as it uses sequence.');
END;
/

-- Trigger for sequence tbl_codigodesc_seq01 for column cod_id in table tbl_codigodesc ---------
CREATE OR REPLACE TRIGGER tbl_codigodesc_tgr01 BEFORE INSERT
ON tbl_codigodesc FOR EACH ROW
BEGIN
  IF :new.cod_id IS NULL OR :new.cod_id <=1 THEN
  :new.cod_id := tbl_codigodesc_seq01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tbl_codigodesc_tgr01 AFTER UPDATE OF cod_id
ON tbl_codigodesc FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column cod_id in table tbl_codigodesc as it uses sequence.');
END;
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE tbl_categoriaxProducto ADD CONSTRAINT rel_cat_pro FOREIGN KEY (cat_id) REFERENCES tbl_categoria (cat_id)
;



ALTER TABLE tbl_categoriaxProducto ADD CONSTRAINT rel_pro_cat FOREIGN KEY (prod_id) REFERENCES tbl_producto (pro_id)
;



ALTER TABLE tbl_orden ADD CONSTRAINT rel_mes_ord FOREIGN KEY (mesa_id) REFERENCES tbl_mesa (mesa_id)
;



ALTER TABLE tbl_orden ADD CONSTRAINT rel_emp_ord FOREIGN KEY (emp_id) REFERENCES tbl_empleado (emp_id)
;



ALTER TABLE tbl_empleado ADD CONSTRAINT rel_res_emp FOREIGN KEY (res_id) REFERENCES tbl_restaurante (res_id)
;



ALTER TABLE tbl_empleado ADD CONSTRAINT rel_rol_emp FOREIGN KEY (rol_id) REFERENCES tbl_res_rol (rol_id)
;



ALTER TABLE tbl_salon ADD CONSTRAINT rel_res_sal FOREIGN KEY (res_id) REFERENCES tbl_restaurante (res_id)
;



ALTER TABLE tbl_mesa ADD CONSTRAINT rel_sal_mes FOREIGN KEY (sal_id) REFERENCES tbl_salon (sal_id)
;



ALTER TABLE tbl_producto ADD CONSTRAINT rel_res_pro FOREIGN KEY (res_id) REFERENCES tbl_restaurante (res_id)
;



ALTER TABLE tbl_productosxOrden ADD CONSTRAINT rel_ord_pro FOREIGN KEY (ord_id) REFERENCES tbl_orden (ord_id)
;



ALTER TABLE tbl_productosxOrden ADD CONSTRAINT rel_pro_ord FOREIGN KEY (pro_id) REFERENCES tbl_producto (pro_id)
;



ALTER TABLE tbl_codigodesc ADD CONSTRAINT rel_res_cod FOREIGN KEY (res_id) REFERENCES tbl_restaurante (res_id)
;



ALTER TABLE tbl_orden ADD CONSTRAINT rel_cod_orden FOREIGN KEY (cod_id) REFERENCES tbl_codigodesc (cod_id)
;





