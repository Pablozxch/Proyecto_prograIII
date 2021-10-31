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
public class RolService
{

    public Respuesta getRoles()
    {
        try
        {
            Request request = new Request("RolController/rol");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }

            List<RolDto> roles = (List<RolDto>) request.readEntity(new GenericType<List<RolDto>>()
            {
            });
            return new Respuesta(true , "" , "" , "Roles" , roles);
        }
        catch(Exception ex)
        {
            Logger.getLogger(RolService.class.getName()).log(Level.SEVERE , "Error obteniendo roles." , ex);
            return new Respuesta(false , "Error obteniendo roles." , "getRols " + ex.getMessage());
        }
    }
}
