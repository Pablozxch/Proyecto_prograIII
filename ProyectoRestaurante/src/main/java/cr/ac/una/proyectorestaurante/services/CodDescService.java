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
public class CodDescService
{

    public Respuesta buscarUrl(String url)
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("url" , url);
            Request request = new Request("CodigodescController/codigo" , "/{url}" , parametros);
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            CodigodescDto cod = (CodigodescDto) request.readEntity(CodigodescDto.class);
            return new Respuesta(true , "" , "" , "coddesc" , cod);
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE , "Error obteniendo el usuario [" + url + "]" , ex);
            return new Respuesta(false , "Error obteniendo el usuario." , "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta getCodigos()
    {
        try
        {
            Request request = new Request("CodigodescController/codigo");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }

            List<CodigodescDto> Ordenes = (List<CodigodescDto>) request.readEntity(new GenericType<List<CodigodescDto>>()
            {
            });

            RestauranteDto id = (RestauranteDto) AppContext.getInstance().get("Restaurante");
            List<CodigodescDto> Ordenes2 = Ordenes.stream().filter(t -> Objects.equals(t.getRestaurante().getId() , id.getId())).collect(Collectors.toList());
            return new Respuesta(true , "" , "" , "coddesc" , Ordenes2);
        }
        catch(Exception ex)
        {
            Logger.getLogger(OrdenService.class.getName()).log(Level.SEVERE , "Error obteniendo Ordenes." , ex);
            return new Respuesta(false , "Error obteniendo Ordenes." , "getOrdens " + ex.getMessage());
        }
    }

    public Respuesta eliminarEmpleado(Long id)
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("id" , id);
            Request request = new Request("CodigodescController/codigo" , "/{id}" , parametros);
            request.delete();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");
            }

            return new Respuesta(true , "" , "");
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE , "Error eliminando el codigo desc." , ex);
            return new Respuesta(false , "Error eliminando el empleado." , "eliminarEmpleado " + ex.getMessage());
        }
    }

    public Respuesta guardarEmpleado(EmpleadoDto pd)
    {
        try
        {
            Request request = new Request("CodigodescController/codigo");
            request.post(pd);
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            CodigodescDto cod = (CodigodescDto) request.readEntity(CodigodescDto.class);
            return new Respuesta(true , "" , "" , "coddesc" , cod);
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE , "Error guardando el empleado." , ex);
            return new Respuesta(false , "Error guardando el empleado." , "guardarEmpleado " + ex.getMessage());
        }
    }

}
