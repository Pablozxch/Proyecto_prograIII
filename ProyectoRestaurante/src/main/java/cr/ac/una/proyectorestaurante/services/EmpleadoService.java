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
public class EmpleadoService
{

    public Respuesta validarEmpleado(String usuario , String clave)
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("usuario" , usuario);
            parametros.put("clave" , clave);
            Request request = new Request("EmpleadoController/empleado" , "/{usuario}/{clave}" , parametros);
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            EmpleadoDto empleado = (EmpleadoDto) request.readEntity(EmpleadoDto.class);
            return new Respuesta(true , "" , "" , "Empleado" , empleado);
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE , "Error obteniendo el usuario [" + usuario + "]" , ex);
            return new Respuesta(false , "Error obteniendo el usuario." , "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta getEmpleados()
    {
        try
        {
            Request request = new Request("EmpleadoController/empleado");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }

            List<EmpleadoDto> empleados = (List<EmpleadoDto>) request.readEntity(new GenericType<List<EmpleadoDto>>()
            {
            });
            RestauranteDto id = (RestauranteDto) AppContext.getInstance().get("Restaurante");
            List<EmpleadoDto> empleados2 = empleados.stream().filter(t -> Objects.equals(t.getRestauranteDto().getId() , id.getId())).collect(Collectors.toList());
            return new Respuesta(true , "" , "" , "Empleados" , empleados2);
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE , "Error obteniendo empleados." , ex);
            return new Respuesta(false , "Error obteniendo empleados." , "getEmpleados " + ex.getMessage());
        }
    }

    public Respuesta eliminarEmpleado(Long id)
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("id" , id);
            Request request = new Request("EmpleadoController/empleado" , "/{id}" , parametros);
            request.delete();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");
            }

            return new Respuesta(true , "" , "");
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE , "Error eliminando el empleado." , ex);
            return new Respuesta(false , "Error eliminando el empleado." , "eliminarEmpleado " + ex.getMessage());
        }
    }

    public Respuesta guardarEmpleado(EmpleadoDto pd)
    {
        try
        {
            Request request = new Request("EmpleadoController/empleado");
            request.post(pd);
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            EmpleadoDto empleado = (EmpleadoDto) request.readEntity(EmpleadoDto.class);//
            return new Respuesta(true , "" , "" , "Empleado" , empleado);
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE , "Error guardando el empleado." , ex);
            return new Respuesta(false , "Error guardando el empleado." , "guardarEmpleado " + ex.getMessage());
        }
    }

    public Respuesta renovarToken()
    {
        try
        {
            Request request = new Request("EmpleadoController/renovar");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            String token = (String) request.readEntity(String.class);
            return new Respuesta(true , "" , "" , "Token" , token);
        }
        catch(Exception ex)
        {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE , "Error renovando el token " , ex);
            return new Respuesta(false , "Error renovando el token." , "renovarToken " + ex.getMessage());
        }
    }
}
