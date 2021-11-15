/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controllers;

import cr.ac.una.wsrestuna.models.*;
import cr.ac.una.wsrestuna.services.*;
import cr.ac.una.wsrestuna.utils.*;
import java.util.*;
import java.util.logging.*;
import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author jp015
 */
@Path("/CierreCajaController")
public class CierreCajaController
{

    @EJB
    CierreCajaService cierreCajaService;

    @POST
    @Path("/cierrecaja")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarCierreCaja(CierrecajasDto cierrecajaDto)
    {
        try
        {
            Respuesta res = cierreCajaService.guardarCierrecajas(cierrecajaDto);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CierrecajasDto) res.getResultado("CierreCaja")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(CierreCajaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la cierre cajas ").build();//TODO
        }
    }

    @GET
    @Path("/cierrecaja")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response lasto()
    {
        long id = cierreCajaService.last();;
        try
        {
            Respuesta res = cierreCajaService.getCierrecajas(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CierrecajasDto) res.getResultado("Cierrecajas")).build();//TODO

        }
        catch(Exception ex)
        {
            Logger.getLogger(CierreCajaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la cierre cajas ").build();//TODO
        }
    }

    @DELETE
    @Path("/cierrecaja/{id}")
    public Response eliminarCierreCaja(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = cierreCajaService.eliminarCierrecajas(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(CierreCajaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la cierre cajas ").build();//TODO
        }
    }

    @GET
    @Path("/cierrecaja/{inicio}/{idcierre}/{idres}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reporteCompletoCajas(@PathParam("inicio") String Inicio , @PathParam("idcierre") Long idCierre , @PathParam("idres") Long idRes)//es el que se genera al finalizar al cerrar caja 
    {
        try
        {
            Respuesta res = cierreCajaService.reporteCompletoCajas(idRes , idCierre , Inicio);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((byte[]) res.getResultado("CierreCaja")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el factura ").build();//TODO
        }
    }

    @GET
    @Path("/cierrecajero/{fecha}/{idcierre}/{idres}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reporteCierreCajero(@PathParam("fecha") String fecha , @PathParam("idcierre") Long idcierre , @PathParam("idres") Long idRes)
    {
        try
        {
            Respuesta res = cierreCajaService.reporteCierreCajero(fecha , idcierre , idRes);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((byte[]) res.getResultado("CierreCaja")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el factura ").build();//TODO
        }
    }
}
