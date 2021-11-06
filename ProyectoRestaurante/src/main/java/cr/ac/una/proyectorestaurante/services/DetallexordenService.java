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
public class DetallexordenService
{

    public Respuesta getDetalles()
    {
        try
        {
            Request request = new Request("DetallexordenController/detallexorden");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }

            List<DetallexordenDto> detallexordens = (List<DetallexordenDto>) request.readEntity(new GenericType<List<DetallexordenDto>>()
            {
            });
            return new Respuesta(true , "" , "" , "Detalles" , detallexordens);
        }
        catch(Exception ex)
        {
            Logger.getLogger(DetallexordenService.class.getName()).log(Level.SEVERE , "Error obteniendo detallexordens." , ex);
            return new Respuesta(false , "Error obteniendo detallexordens." , "getDetalles " + ex.getMessage());
        }
    }

    public Respuesta eliminarDetalle(Long id)
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("id" , id);
            Request request = new Request("DetallexordenController/detallexorden" , "/{id}" , parametros);
            request.delete();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");
            }

            return new Respuesta(true , "" , "");
        }
        catch(Exception ex)
        {
            Logger.getLogger(DetallexordenService.class.getName()).log(Level.SEVERE , "Error eliminando el detallexorden." , ex);
            return new Respuesta(false , "Error eliminando el detallexorden." , "eliminarCategoria " + ex.getMessage());
        }
    }

    public Respuesta guardarDetalle(DetallexordenDto dt)
    {
        try
        {
            Request request = new Request("DetallexordenController/detallexorden");
            request.post(dt);
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            DetallexordenDto detallexorden = (DetallexordenDto) request.readEntity(DetallexordenDto.class);//
            return new Respuesta(true , "" , "" , "Detalle" , detallexorden);
        }
        catch(Exception ex)
        {
            Logger.getLogger(DetallexordenService.class.getName()).log(Level.SEVERE , "Error guardando el detallexorden." , ex);
            return new Respuesta(false , "Error guardando el detallexorden." , "guardarCategoria " + ex.getMessage());
        }
    }
}
