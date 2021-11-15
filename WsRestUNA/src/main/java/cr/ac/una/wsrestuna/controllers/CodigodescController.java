/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controllers;

import cr.ac.una.wsrestuna.models.*;
import cr.ac.una.wsrestuna.services.*;
import cr.ac.una.wsrestuna.utils.*;
import java.util.logging.*;
import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author jp015
 */
@Path("/CodigodescController")
public class CodigodescController
{

    @EJB
    CodigodescService codigodescServices;

    @GET
    @Path("/ping")
    public Response ping()
    {
        return Response
                  .ok("ping Producto")
                  .build();
    }

    @POST
    @Path("/codigo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarCodigo(CodigodescDto codigodescDto)
    {
        try
        {
            Respuesta res = codigodescServices.guardarCodigodesc(codigodescDto);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CodigodescDto) res.getResultado("Codigodesc")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(CodigodescController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la cierre cajas ").build();//TODO
        }
    }

    @GET
    @Path("/codigo/{url}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUrl(@PathParam("url") String url)
    {
        try
        {
            Respuesta res = codigodescServices.getCodNombre(url);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CodigodescDto) res.getResultado("Codigodesc")).build();//TODO

        }
        catch(Exception ex)
        {
            Logger.getLogger(CodigodescController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el codigo descuento ").build();//TODO
        }
    }

    @DELETE
    @Path("/cierrecaja/{id}")
    public Response eliminarCierreCaja(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = codigodescServices.eliminarCodigodesc(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(CodigodescController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la cierre cajas ").build();//TODO
        }
    }
}
