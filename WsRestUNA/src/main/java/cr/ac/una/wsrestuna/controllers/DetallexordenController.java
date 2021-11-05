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
@Path("/DetallexordenController")
public class DetallexordenController
{

    @EJB
    DetallexordenService detallexordenService;

    @GET
    @Path("/ping")
    public Response ping()
    {
        return Response
                  .ok("ping DetallexordenController")
                  .build();
    }

    @GET
    @Path("/detallexorden")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdenees()
    {
        try
        {
            Respuesta res = detallexordenService.getDetallexordenes();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }

            return Response.ok(new GenericEntity<List<DetallexordenDto>>((List<DetallexordenDto>) res.getResultado("Detallexordenes"))
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
    @Path("/detallexorden")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarCategoria(DetallexordenDto detallexordenDto)
    {
        try
        {
            Respuesta res = detallexordenService.guardarDetallexorden(detallexordenDto);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((DetallexordenDto) res.getResultado("Detallexorden")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la categoria ").build();//TODO
        }
    }

    @DELETE
    @Path("/detallexorden/{id}")
    public Response eliminarCategoria(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = detallexordenService.eliminarDetallexorden(id);
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
