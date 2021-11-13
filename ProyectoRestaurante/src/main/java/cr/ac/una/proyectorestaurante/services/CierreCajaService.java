/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.services;

import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.utils.*;
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
}