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

/**
 *
 * @author jp015
 */
public class FacturaService
{

    public Respuesta sendByEmail(Long idRes , String nombrePersona , String correoPersona , Long idFac)
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("idRes" , idRes);
            parametros.put("nombrePersona" , nombrePersona);
            parametros.put("correoPersona" , correoPersona);
            parametros.put("idFac" , idFac);
            Request request = new Request("FacturaController/factura" , "/{idRes}/{nombrePersona}/{correoPersona}/{idFac}" , parametros);
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }

            return new Respuesta(true , "" , "");
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaService.class.getName()).log(Level.SEVERE , "Error eliminando el factura." , ex);
            return new Respuesta(false , "Error enviando correo." , "eliminarFactura " + ex.getMessage());
        }
    }

    public Respuesta lasto()
    {
        try
        {
            Request request = new Request("FacturaController/facturalast");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            FacturaDto facturaDto = (FacturaDto) request.readEntity(FacturaDto.class);

            return new Respuesta(true , "" , "" , "Factura" , facturaDto);
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaService.class.getName()).log(Level.SEVERE , "Error obteniendo facturas." , ex);
            return new Respuesta(false , "Error obteniendo facturas." , "getFacturas " + ex.getMessage());
        }
    }

    public Respuesta getFacturas()
    {
        try
        {
            Request request = new Request("FacturaController/factura");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }

            List<FacturaDto> facturas = (List<FacturaDto>) request.readEntity(new GenericType<List<FacturaDto>>()
            {
            });
            return new Respuesta(true , "" , "" , "Facturas" , facturas);
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaService.class.getName()).log(Level.SEVERE , "Error obteniendo facturas." , ex);
            return new Respuesta(false , "Error obteniendo facturas." , "getFacturas " + ex.getMessage());
        }
    }

    public Respuesta guardarFactura(FacturaDto pd)
    {
        try
        {
            Request request = new Request("FacturaController/factura");
            request.post(pd);
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            FacturaDto factura = (FacturaDto) request.readEntity(FacturaDto.class);//
            return new Respuesta(true , "" , "" , "Factura" , factura);
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaService.class.getName()).log(Level.SEVERE , "Error guardando el factura." , ex);
            return new Respuesta(false , "Error guardando el factura." , "guardarFactura " + ex.getMessage());
        }
    }
}
