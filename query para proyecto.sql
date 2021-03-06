/*select c.* from Tbl_Factura f 
join RestUna.Tbl_Cierrecajas c on f.ccaj_id=c.ccaj_id
join RestUna.Tbl_Orden o on f.ord_id =o.ord_id 
where o.ord_fecha='7/11/2021' and c.emp_id=10*/


/*select o.*,f.*,d.* from Tbl_Factura f 
join RestUna.Tbl_Orden o on f.ord_id =o.ord_id 
join RestUna.Tbl_Detallexorden d on o.ord_id=d.ord_id*/



/*Select p.* from Tbl_Factura f 
join RestUna.Tbl_Orden o on f.ord_id =o.ord_id 
join RestUna.Tbl_Detallexorden d on o.ord_id=d.ord_id
join  RestUna.Tbl_Producto p on d.pro_id=p.pro_id
where f.fac_id=2 

Select f.* from Tbl_Factura f where f.fac_id=2*/

/*query para el primero*/

SELECT "TBL_FACTURA"."FAC_ID",
    "TBL_FACTURA"."FAC_DESC",
    "TBL_FACTURA"."FAC_EFECTIVOTARJETA",
    "TBL_FACTURA"."FAC_SUBTOTAL",
    "TBL_FACTURA"."FAC_TOTAL",
    "TBL_FACTURA"."FAC_MONTOCDESC",
    "TBL_DETALLEXORDEN"."DXO_CANTIDAD",
    "TBL_DETALLEXORDEN"."DXO_PRECIOC",
    "TBL_PRODUCTO"."PRO_NOMBRECORTO"
FROM "TBL_FACTURA"
    LEFT JOIN "TBL_ORDEN" ON 
     "TBL_FACTURA"."ORD_ID" = "TBL_ORDEN"."ORD_ID" 
     LEFT JOIN  "TBL_DETALLEXORDEN" ON
    "TBL_ORDEN"."ORD_ID"= "TBL_DETALLEXORDEN"."ORD_ID"
    LEFT JOIN "TBL_PRODUCTO" ON 
     "TBL_DETALLEXORDEN"."PRO_ID" = "TBL_PRODUCTO"."PRO_ID" 
     
WHERE "TBL_ORDEN"."EMP_ID" = 1 AND "TBL_ORDEN"."ORD_FECHA" BETWEEN '1/11/2021' AND '30/11/2021';
    
/*SELECT "TBL_DETALLEXORDEN"."DXO_CANTIDAD"
       FROM "TBL_DETALLEXORDEN" LEFT JOIN "TBL_ORDEN" 
       ON "TBL_DETALLEXORDEN"."ORD_ID" = "TBL_ORDEN"."ORD_ID"
       WHERE "TBL_ORDEN"."ORD_ID" = */
       
       
SELECT "TBL_FACTURA"."FAC_ID",
  "TBL_FACTURA"."FAC_DESC",
  "TBL_FACTURA"."FAC_EFECTIVOTARJETA",
  "TBL_FACTURA"."FAC_SUBTOTAL",
  "TBL_FACTURA"."FAC_TOTAL",
  "TBL_FACTURA"."FAC_MONTOCDESC",
  "TBL_DETALLEXORDEN"."DXO_CANTIDAD",
  "TBL_DETALLEXORDEN"."DXO_PRECIOC",
  "TBL_PRODUCTO"."PRO_NOMBRECORTO"
FROM "TBL_FACTURA"
  LEFT JOIN "TBL_ORDEN" ON 
   "TBL_FACTURA"."ORD_ID" = "TBL_ORDEN"."ORD_ID" 
  LEFT JOIN "TBL_DETALLEXORDEN" ON 
   "TBL_ORDEN"."ORD_ID" = "TBL_DETALLEXORDEN"."ORD_ID" 
  LEFT JOIN "TBL_PRODUCTO" ON 
   "TBL_DETALLEXORDEN"."PRO_ID" = "TBL_PRODUCTO"."PRO_ID" 
   AND "TBL_DETALLEXORDEN"."PRO_ID" = "TBL_PRODUCTO"."PRO_ID" ,
  "TBL_PRODUCTO"
WHERE 
    ( 
   "TBL_ORDEN"."ORD_FECHA" BETWEEN '1/11/2021' AND '1/12/2021')
