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
public class MesaService
{

    public Respuesta getMesas()
    {
        try
        {
            Request request = new Request("MesaController/mesa");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }

            List<MesaDto> mesas = (List<MesaDto>) request.readEntity(new GenericType<List<MesaDto>>()
            {
            });
            SalonDto id = (SalonDto) AppContext.getInstance().get("Salon");//colocar 
            List<MesaDto> mesas2 = mesas.stream().filter(t -> Objects.equals(t.getSalonDto().getId() , id.getId())).collect(Collectors.toList());
            return new Respuesta(true , "" , "" , "Mesas" , mesas2);
        }
        catch(Exception ex)
        {
            Logger.getLogger(MesaService.class.getName()).log(Level.SEVERE , "Error obteniendo mesas." , ex);
            return new Respuesta(false , "Error obteniendo mesas." , "geMesas " + ex.getMessage());
        }
    }

    public Respuesta eliminarMesa(Long id)
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("id" , id);
            Request request = new Request("MesaController/mesa" , "/{id}" , parametros);
            request.delete();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");
            }

            return new Respuesta(true , "" , "");
        }
        catch(Exception ex)
        {
            Logger.getLogger(MesaService.class.getName()).log(Level.SEVERE , "Error eliminando el mesa." , ex);
            return new Respuesta(false , "Error eliminando el mesa." , "eliminarMesa " + ex.getMessage());
        }
    }

    public Respuesta guardarMesa(MesaDto pd)
    {
        try
        {
            Request request = new Request("MesaController/mesa");
            request.post(pd);
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            MesaDto mesa = (MesaDto) request.readEntity(MesaDto.class);//
            return new Respuesta(true , "" , "" , "Mesa" , mesa);
        }
        catch(Exception ex)
        {
            Logger.getLogger(MesaService.class.getName()).log(Level.SEVERE , "Error guardando el mesa." , ex);
            return new Respuesta(false , "Error guardando el mesa." , "guardarMesa " + ex.getMessage());
        }
    }
}
