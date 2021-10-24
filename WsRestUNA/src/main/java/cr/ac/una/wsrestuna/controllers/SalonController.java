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
@Path("/SalonController")
public class SalonController
{

    @EJB
    SalonService salonService;

    @GET
    @Path("/ping")
    public Response ping()
    {
        return Response
                  .ok("ping Salon")
                  .build();
    }

    @GET
    @Path("/salon/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalon(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = salonService.getSalon(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((SalonDto) res.getResultado("Salon")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(SalonController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el salon ").build();//TODO
        }
    }

    @GET
    @Path("/salon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalones()
    {
        try
        {
            Respuesta res = salonService.getSalones();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }

            return Response.ok(new GenericEntity<List<SalonDto>>((List<SalonDto>) res.getResultado("Salones"))
            {
            }).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(SalonController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el salon ").build();//TODO
        }
    }
}
