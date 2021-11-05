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
@Path("/OrdenController")
public class OrdenController
{

    @EJB
    OrdenService ordenService;

    @GET
    @Path("/ping")
    public Response ping()
    {
        return Response
                  .ok("ping OrdenController")
                  .build();
    }

    @GET
    @Path("/orden")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdenees()
    {
        try
        {
            Respuesta res = ordenService.getOrdenes();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }

            return Response.ok(new GenericEntity<List<OrdenDto>>((List<OrdenDto>) res.getResultado("Ordenes"))
            {
            }).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la orden ").build();//TODO
        }
    }

    @POST
    @Path("/orden")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarCategoria(OrdenDto ordenDto)
    {
        try
        {
            Respuesta res = ordenService.guardarOrden(ordenDto);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((OrdenDto) res.getResultado("Orden")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la categoria ").build();//TODO
        }
    }

    @DELETE
    @Path("/orden/{id}")
    public Response eliminarCategoria(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = ordenService.eliminarOrden(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la categoria ").build();//TODO
        }
    }

}
