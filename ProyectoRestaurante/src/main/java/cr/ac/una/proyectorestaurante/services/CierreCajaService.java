/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.services;

import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author jp015
 */
public class CierreCajaService
{

    public Respuesta guardarCierrecajas(CierrecajasDto pd)
    {
        try
        {
            Request request = new Request("CierreCajaController/cierrecaja");
            request.post(pd);
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            CierrecajasDto categoria = (CierrecajasDto) request.readEntity(CierrecajasDto.class);//
            return new Respuesta(true , "" , "" , "CierreCaja" , categoria);
        }
        catch(Exception ex)
        {
            Logger.getLogger(CierreCajaService.class.getName()).log(Level.SEVERE , "Error guardando el categoria." , ex);
            return new Respuesta(false , "Error guardando el categoria." , "guardarCierrecajas " + ex.getMessage());
        }
    }

    public Respuesta lasto()
    {
        try
        {
            Request request = new Request("CierreCajaController/cierrecaja");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            CierrecajasDto cierrecajasDto = (CierrecajasDto) request.readEntity(CierrecajasDto.class);

            return new Respuesta(true , "" , "" , "CierreCaja" , cierrecajasDto);
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaService.class.getName()).log(Level.SEVERE , "Error obteniendo facturas." , ex);
            return new Respuesta(false , "Error obteniendo facturas." , "getFacturas " + ex.getMessage());
        }
    }
    public Respuesta reporteCierreCajero(String fecha , Long idCierre , Long idRes)//el que se genera al terminar el cierre caja
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("fecha" , fecha);
            parametros.put("idcierre" , idCierre);
            parametros.put("idres" , idRes);
            Request request = new Request("CierreCajaController/cierrecajero" , "/{fecha}/{idcierre}/{idres}" , parametros);
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");
            }
            byte[] bytes = (byte[]) request.readEntity(byte[].class);
            return new Respuesta(true , "" , "" , "CierreCaja" , bytes);
        }
        catch(Exception ex)
        {
            return new Respuesta(false , "Error obteniendo el usuario." , "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta reporteCierreEspecifico(String fecha , Long idEmp , Long idres)//fecha especifica
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("fecha" , fecha);
            parametros.put("idEmp" , idEmp);
            parametros.put("idres" , idres);
            Request request = new Request("CierreCajaController/cierrecaja" , "/{fecha}/{idEmp}/{idres}" , parametros);
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            byte[] bytes = (byte[]) request.readEntity(byte[].class);
            return new Respuesta(true , "" , "" , "CierreCaja" , bytes);
        }
        catch(Exception ex)
        {
            return new Respuesta(false , "Error obteniendo el usuario." , "getUsuario " + ex.getMessage());
        }
    }

}
