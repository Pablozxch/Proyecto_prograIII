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

/**
 *
 * @author jp015
 */
public class RestauranteService
{

    public Respuesta getRestaurantes()
    {
        try
        {
            Request request = new Request("RestauranteController/restaurante");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }

            List<RestauranteDto> restaurantes = (List<RestauranteDto>) request.readEntity(new GenericType<List<RestauranteDto>>()
            {
            });
            return new Respuesta(true , "" , "" , "Restaurantes" , restaurantes);
        }
        catch(Exception ex)
        {
            Logger.getLogger(RestauranteService.class.getName()).log(Level.SEVERE , "Error obteniendo restaurantes." , ex);
            return new Respuesta(false , "Error obteniendo restaurantes." , "getRestaurantes " + ex.getMessage());
        }
    }
}
