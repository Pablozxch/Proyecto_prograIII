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
@Path("/RestauranteController")
public class RestauranteController
{

    @EJB
    RestauranteService restauranteService;

    @GET
    @Path("/ping")
    public Response ping()
    {
        return Response
                  .ok("ping Restaurante")
                  .build();
    }

    @GET
    @Path("/restaurante/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRestaurante(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = restauranteService.getRestaurante(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((RestauranteDto) res.getResultado("Restaurante")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(RestauranteController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el restaurante ").build();//TODO
        }
    }

    @GET
    @Path("/restaurante")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRestaurantes()
    {
        try
        {
            Respuesta res = restauranteService.getRestaurantes();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }

            return Response.ok(new GenericEntity<List<RestauranteDto>>((List<RestauranteDto>) res.getResultado("Restaurantes"))
            {
            }).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(RestauranteController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el procuto ").build();//TODO
        }
    }

    @POST
    @Path("/restaurante")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarRestaurante(RestauranteDto restauranteDto)
    {
        try
        {
            Respuesta res = restauranteService.guardarRestaurante(restauranteDto);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((RestauranteDto) res.getResultado("Restaurante")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(RestauranteController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el restaurante ").build();//TODO
        }
    }

    @DELETE
    @Path("/restaurante/{id}")
    public Response eliminarRestaurante(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = restauranteService.eliminarRestaurante(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(RestauranteController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el restaurante ").build();//TODO
        }
    }
}
