/*select c.* from Tbl_Factura f 
join RestUna.Tbl_Cierrecajas c on f.ccaj_id=c.ccaj_id
join RestUna.Tbl_Orden o on f.ord_id =o.ord_id 
where o.ord_fecha='7/11/2021' and c.emp_id=10*/


/*select o.*,f.*,d.* from Tbl_Factura f 
join RestUna.Tbl_Orden o on f.ord_id =o.ord_id 
join RestUna.Tbl_Detallexorden d on o.ord_id=d.ord_id*/




Select p.* from Tbl_Factura f 
join RestUna.Tbl_Orden o on f.ord_id =o.ord_id 
join RestUna.Tbl_Detallexorden d on o.ord_id=d.ord_id
join  RestUna.Tbl_Producto p on d.pro_id=p.pro_id
where f.fac_id=2 

Select f.* from Tbl_Factura f where f.fac_id=2
