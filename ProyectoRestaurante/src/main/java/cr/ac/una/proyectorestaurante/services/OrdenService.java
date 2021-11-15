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
public class OrdenService
{

    public Respuesta getOrdenes()
    {
        try
        {
            System.out.println("Estoy entrado");
            Request request = new Request("OrdenController/orden");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }

            List<OrdenDto> Ordenes = (List<OrdenDto>) request.readEntity(new GenericType<List<OrdenDto>>()
            {
            });

            RestauranteDto id = (RestauranteDto) AppContext.getInstance().get("Restaurante");
            List<OrdenDto> Ordenes2 = Ordenes.stream().filter(t -> Objects.equals(t.getEmpleadoDto().getRestauranteDto().getId() , id.getId())).collect(Collectors.toList());
            Ordenes2.forEach(t ->
            {
                System.out.println("El valor es " + t.getMesaDto().toString());
            });
            return new Respuesta(true , "" , "" , "Ordenes" , Ordenes2);
        }
        catch(Exception ex)
        {
            Logger.getLogger(OrdenService.class.getName()).log(Level.SEVERE , "Error obteniendo Ordenes." , ex);
            return new Respuesta(false , "Error obteniendo Ordenes." , "getOrdens " + ex.getMessage());
        }
    }

    public Respuesta eliminarOrden(Long id)
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("id" , id);
            Request request = new Request("OrdenController/orden" , "/{id}" , parametros);
            request.delete();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");
            }

            return new Respuesta(true , "" , "");
        }
        catch(Exception ex)
        {
            Logger.getLogger(OrdenService.class.getName()).log(Level.SEVERE , "Error eliminando el Orden." , ex);
            return new Respuesta(false , "Error eliminando el Orden." , "eliminarOrden " + ex.getMessage());
        }
    }

    public Respuesta guardarOrden(OrdenDto pd)
    {
        try
        {
            Request request = new Request("OrdenController/orden");
            request.post(pd);
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            OrdenDto Orden = (OrdenDto) request.readEntity(OrdenDto.class);//
            return new Respuesta(true , "" , "" , "Orden" , Orden);
        }
        catch(Exception ex)
        {
            Logger.getLogger(OrdenService.class.getName()).log(Level.SEVERE , "Error guardando el Orden." , ex);
            return new Respuesta(false , "Error guardando el Orden." , "guardarOrdenOrden " + ex.getMessage());
        }
    }

    public Respuesta lasto()
    {
        try
        {
            Request request = new Request("OrdenController/ordenlasto");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            OrdenDto orden = (OrdenDto) request.readEntity(OrdenDto.class);

            return new Respuesta(true , "" , "" , "Orden" , orden);
        }
        catch(Exception ex)
        {
            Logger.getLogger(OrdenService.class.getName()).log(Level.SEVERE , "Error obteniendo facturas." , ex);
            return new Respuesta(false , "Error obteniendo facturas." , "getFacturas " + ex.getMessage());
        }
    }

}
