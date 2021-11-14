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
import javax.mail.*;
import javax.mail.internet.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author jp015
 */
@Secure
@Path("/EmpleadoController")
public class EmpleadoController
{

    @EJB
    EmpleadoService empleadoServices;
    
    @Context
    SecurityContext securityContext;

    @GET
    @Path("/ping")
    public Response ping()
    {
        return Response
                  .ok("ping Producto")
                  .build();
    }

    

    @GET
    @Path("/empleado/{usuario}/{clave}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("usuario") String usuario , @PathParam("clave") String clave)//este coso es el encargado de asignarle el token etc etc etc etc
    {
        try
        {
            Respuesta res = empleadoServices.validarEmpleado(usuario , clave);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            EmpleadoDto empleadoDto = (EmpleadoDto) res.getResultado("Empleado");//TODO
            return Response.ok(empleadoDto).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al validar el usuario ").build();//TODO
        }
    }

    @GET
    @Path("/empleado/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpleado(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = empleadoServices.getEmpleado(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((EmpleadoDto) res.getResultado("Empleado")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la empleado ").build();//TODO
        }
    }

    @GET
    @Path("/empleado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpleados()
    {
        try
        {
            Respuesta res = empleadoServices.getEmpleados();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }

            return Response.ok(new GenericEntity<List<EmpleadoDto>>((List<EmpleadoDto>) res.getResultado("Empleados"))
            {
            }).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el empleado ").build();//TODO
        }
    }

    @POST
    @Path("/empleado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarEmpleado(EmpleadoDto empleado)
    {
        try
        {
            Respuesta res = empleadoServices.guardarEmpleado(empleado);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((EmpleadoDto) res.getResultado("Empleado")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el empleado ").build();//TODO
        }
    }

    @DELETE
    @Path("/empleado/{id}")
    public Response eliminarEmpleado(@PathParam(("id")) Long id)
    {
        try
        {
            Respuesta res = empleadoServices.eliminarEmpleado(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el empleado ").build();//TODO
        }
    }
    @GET
    @Path("/renovar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response renovarToken()
    {
        try
        {
            String usuarioRequest = securityContext.getUserPrincipal().getName();
            if(usuarioRequest != null && !usuarioRequest.isBlank())
            {
                return Response.ok(JwTokenHelper.getInstance().generatePrivateKey(usuarioRequest)).build();
            }
            else
            {
                return Response.status(CodigoRespuesta.ERROR_PERMISOS.getValue()).entity("Error renovando el token").build();
            }
        }
        catch(Exception e)
        {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE , null , e);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error renovando el token").build();//TODO
        }
    }
}
