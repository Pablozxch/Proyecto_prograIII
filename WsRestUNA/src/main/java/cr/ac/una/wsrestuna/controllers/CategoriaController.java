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
@Path("/CategoriaController")
public class CategoriaController
{

    @EJB
    CategoriaService categoriaService;

    @GET
    @Path("/ping")
    public Response ping()
    {
        return Response
                  .ok("ping Categoria")
                  .build();
    }

    @GET
    @Path("/categoria/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoria(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = categoriaService.getCategoria(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CategoriaDto) res.getResultado("Categoria")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la categoria ").build();//TODO
        }
    }

    @GET
    @Path("/categoria")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategorias()
    {
        try
        {
            Respuesta res = categoriaService.getCategorias();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }

            return Response.ok(new GenericEntity<List<CategoriaDto>>((List<CategoriaDto>) res.getResultado("Categorias"))
            {
            }).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la categoria ").build();//TODO
        }
    }

    @POST
    @Path("/categoria")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarCategoria(CategoriaDto categoriaDto)
    {
        try
        {
            Respuesta res = categoriaService.guardarCategoria(categoriaDto);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CategoriaDto) res.getResultado("Categoria")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la categoria ").build();//TODO
        }
    }

    @DELETE
    @Path("/categoria/{id}")
    public Response eliminarCategoria(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = categoriaService.eliminarCategoria(id);
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
