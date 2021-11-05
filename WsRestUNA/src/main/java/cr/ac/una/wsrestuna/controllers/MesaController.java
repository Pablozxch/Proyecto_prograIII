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
@Path("/MesaController")
public class MesaController
{
    
    @EJB
    MesaService mesaService;
    @GET
    @Path("/mesa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMesas()
    {
        try
        {
            Respuesta res = mesaService.getMesas();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }

            return Response.ok(new GenericEntity<List<MesaDto>>((List<MesaDto>) res.getResultado("Mesas"))
            {
            }).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(MesaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el mesa ").build();//TODO
        }
    }

    @POST
    @Path("/mesa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarMesa(MesaDto mesa)
    {
        try
        {
            Respuesta res = mesaService.guardarMesa(mesa);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((MesaDto) res.getResultado("Mesa")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(MesaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el mesa ").build();//TODO
        }
    }

    @DELETE
    @Path("/mesa/{id}")
    public Response eliminarMesa(@PathParam(("id")) Long id)
    {
        try
        {
            Respuesta res = mesaService.eliminarMesa(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(MesaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el mesa ").build();//TODO
        }
    }
}
