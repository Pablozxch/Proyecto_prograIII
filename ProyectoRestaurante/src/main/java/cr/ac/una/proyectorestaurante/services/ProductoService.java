/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.services;

import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.utils.*;
import cr.ac.una.proyectorestaurante.utils.Request;
import jakarta.ws.rs.core.*;
import java.util.*;
import java.util.logging.*;
import java.util.stream.*;

/**
 *
 * @author jp015
 */
public class ProductoService
{

    public Respuesta getProductos()
    {
        try
        {
            Request request = new Request("ProductoController/producto");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }

            List<ProductoDto> productos = (List<ProductoDto>) request.readEntity(new GenericType<List<ProductoDto>>()
            {
            });
            RestauranteDto id = (RestauranteDto) AppContext.getInstance().get("Restaurante");
            List<ProductoDto> productos2 = productos.stream().filter(t -> t.getRestauranteDto().getId() == id.getId()).collect(Collectors.toList());
            return new Respuesta(true , "" , "" , "Productos" , productos2);
        }
        catch(Exception ex)
        {
            Logger.getLogger(ProductoService.class.getName()).log(Level.SEVERE , "Error obteniendo productos." , ex);
            return new Respuesta(false , "Error obteniendo productos." , "getProductos " + ex.getMessage());
        }
    }

    public Respuesta eliminarProducto(Long id)
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("id" , id);
            Request request = new Request("ProductoController/producto" , "/{id}" , parametros);
            request.delete();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");
            }

            return new Respuesta(true , "" , "");
        }
        catch(Exception ex)
        {
            Logger.getLogger(ProductoService.class.getName()).log(Level.SEVERE , "Error eliminando el producto." , ex);
            return new Respuesta(false , "Error eliminando el producto." , "eliminarProducto " + ex.getMessage());
        }
    }

    public Respuesta guardarProducto(ProductoDto pd)
    {
        try
        {
            Request request = new Request("ProductoController/producto");
            request.post(pd);
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            ProductoDto producto = (ProductoDto) request.readEntity(ProductoDto.class);//
            return new Respuesta(true , "" , "" , "Producto" , producto);
        }
        catch(Exception ex)
        {
            Logger.getLogger(ProductoService.class.getName()).log(Level.SEVERE , "Error guardando el producto." , ex);
            return new Respuesta(false , "Error guardando el producto." , "guardarProducto " + ex.getMessage());
        }
    }

    public Respuesta productosMVendidos(String Inicio , String Final , Long idRes)
    {
        try
        {
            System.out.println("wenas");
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("inicio" , Inicio);
            parametros.put("finall" , Final);
            parametros.put("idRes" , idRes);
            Request request = new Request("ProductoController/producto" , "/{inicio}/{finall}/{idRes}" , parametros);
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            Byte[] bytes = (Byte[]) request.readEntity(Byte.class);
            return new Respuesta(true , "" , "" , "Productos" , bytes);
        }
        catch(Exception ex)
        {
            System.out.println("picha");
            return new Respuesta(false , "Error obteniendo el usuario." , "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta ping()
    {
        try
        {
            Request request = new Request("ProductoController/ping");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            RestauranteDto restauranteDto = (RestauranteDto) request.readEntity(RestauranteDto.class);
            return new Respuesta(true , "" , "" , "Producto" , restauranteDto);
        }
        catch(Exception ex)
        {
            //Logger.getLogger(SalonService.class.getName()).log(Level.SEVERE , "Error obteniendo el restaurante [" + id + "]" , ex);
            return new Respuesta(false , "Error obteniendo el restaurante." , "getUsuario " + ex.getMessage());
        }
    }

}
