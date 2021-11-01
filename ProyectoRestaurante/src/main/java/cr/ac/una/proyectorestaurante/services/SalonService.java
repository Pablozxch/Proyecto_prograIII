/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ws.rs.core.GenericType;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.utils.*;
import cr.ac.una.proyectorestaurante.utils.Request;
import jakarta.ws.rs.core.*;
import java.util.stream.*;

/**
 *
 * @author jp015
 */
public class SalonService
{

    public Respuesta getSalid(Long id)
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("id" , id);
            Request request = new Request("SalonController/salon" , "/{id}" , parametros);
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            SalonDto salon = (SalonDto) request.readEntity(SalonDto.class);
            return new Respuesta(true , "" , "" , "Salon" , salon);
        }
        catch(Exception ex)
        {
            Logger.getLogger(SalonService.class.getName()).log(Level.SEVERE , "Error obteniendo el usuario [" + id + "]" , ex);
            return new Respuesta(false , "Error obteniendo el usuario." , "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta getSalones()
    {
        try
        {
            Request request = new Request("SalonController/salon");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }

            List<SalonDto> salones = (List<SalonDto>) request.readEntity(new GenericType<List<SalonDto>>()
            {
            });
            RestauranteDto id = (RestauranteDto) AppContext.getInstance().get("Restaurante");
            List<SalonDto> salones2 = salones.stream().filter(t -> t.getRestauranteDto().getId() == id.getId()).collect(Collectors.toList());
            return new Respuesta(true , "" , "" , "Salones" , salones2);
        }
        catch(Exception ex)
        {
            Logger.getLogger(SalonService.class.getName()).log(Level.SEVERE , "Error obteniendo salones." , ex);
            return new Respuesta(false , "Error obteniendo salones." , "getSalons " + ex.getMessage());
        }
    }

    public Respuesta eliminarSalon(Long id)
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("id" , id);
            Request request = new Request("SalonController/salon" , "/{id}" , parametros);
            request.delete();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");
            }

            return new Respuesta(true , "" , "");
        }
        catch(Exception ex)
        {
            Logger.getLogger(RestauranteService.class.getName()).log(Level.SEVERE , "Error eliminando el salon." , ex);
            return new Respuesta(false , "Error eliminando el salon." , "eliminarSalon " + ex.getMessage());
        }
    }

}
