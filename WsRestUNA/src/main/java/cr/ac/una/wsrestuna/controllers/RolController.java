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
@Path("/RolController")
public class RolController
{

    @EJB
    RolService rolService;

    @Path("/ping")
    public Response ping()
    {
        return Response
                  .ok("ping rol")
                  .build();
    }

    @GET
    @Path("/rol")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoles()
    {
        try
        {
            Respuesta res = rolService.getRoles();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }

            return Response.ok(new GenericEntity<List<RolDto>>((List<RolDto>) res.getResultado("Roles"))
            {
            }).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(RolController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener los roles ").build();//TODO
        }
    }
}
