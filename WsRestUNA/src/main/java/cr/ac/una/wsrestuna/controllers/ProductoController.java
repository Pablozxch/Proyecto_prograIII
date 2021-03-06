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
@Path("/ProductoController")
public class ProductoController
{

    @EJB
    ProductoService productoService;

    @GET
    @Path("/ping")
    public Response ping()
    {
        return Response
                  .ok("ping Producto")
                  .build();
    }

    @GET
    @Path("/producto/{fechai}/{fechaf}/{idres}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response productosVendidos(@PathParam("fechai") String fechaI , @PathParam("fechaf") String fechaF , @PathParam("idres") Long idRes)
    {
        try
        {
            Respuesta res = productoService.reporteProductosVendidos(idRes , fechaI , fechaF);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            byte[] bytes = (byte[]) res.getResultado("Producto");
            return Response.ok(bytes).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el factura ").build();//TODO
        }
    }

    @GET
    @Path("/producto/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducto(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = productoService.getProducto(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ProductoDto) res.getResultado("Producto")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto ").build();//TODO
        }
    }

    @GET
    @Path("/producto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductos()
    {
        try
        {
            Respuesta res = productoService.getProductos();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }

            return Response.ok(new GenericEntity<List<ProductoDto>>((List<ProductoDto>) res.getResultado("Productos"))
            {
            }).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto ").build();//TODO
        }
    }

    @POST
    @Path("/producto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarProducto(ProductoDto productoDto)
    {
        System.out.println("El producto a guardar es "+productoDto.toString());
        try
        {
            Respuesta res = productoService.guardarProducto(productoDto);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            System.out.println("El prodcuto es"+((ProductoDto) res.getResultado("Producto")).toString());
            return Response.ok((ProductoDto) res.getResultado("Producto")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto ").build();//TODO
        }
    }

    @DELETE
    @Path("/producto/{id}")
    public Response eliminarProducto(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = productoService.eliminarProducto(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto ").build();//TODO
        }
    }

}
