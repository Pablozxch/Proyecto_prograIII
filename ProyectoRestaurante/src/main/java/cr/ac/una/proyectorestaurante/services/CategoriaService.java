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
public class CategoriaService
{

    public Respuesta getCategorias()
    {
        try
        {
            Request request = new Request("CategoriaController/categoria");
            request.get();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }

            List<CategoriaDto> categorias = (List<CategoriaDto>) request.readEntity(new GenericType<List<CategoriaDto>>()
            {
            });
            RestauranteDto id = (RestauranteDto) AppContext.getInstance().get("Restaurante");
            List<CategoriaDto> list = categorias.stream().filter(t -> Objects.equals(t.getRestauranteDto().getId() , id.getId())).collect(Collectors.toList());
            return new Respuesta(true , "" , "" , "Categorias" , list);
        }
        catch(Exception ex)
        {
            Logger.getLogger(CategoriaService.class.getName()).log(Level.SEVERE , "Error obteniendo categorias." , ex);
            return new Respuesta(false , "Error obteniendo categorias." , "getCategorias " + ex.getMessage());
        }
    }

    public Respuesta eliminarCategoria(Long id)
    {
        try
        {
            Map<String , Object> parametros = new HashMap<>();
            parametros.put("id" , id);
            Request request = new Request("CategoriaController/categoria" , "/{id}" , parametros);
            request.delete();
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");
            }

            return new Respuesta(true , "" , "");
        }
        catch(Exception ex)
        {
            Logger.getLogger(CategoriaService.class.getName()).log(Level.SEVERE , "Error eliminando el categoria." , ex);
            return new Respuesta(false , "Error eliminando el categoria." , "eliminarCategoria " + ex.getMessage());
        }
    }

    public Respuesta guardarCategoria(CategoriaDto pd)
    {
        try
        {
            Request request = new Request("CategoriaController/categoria");
            request.post(pd);
            if(request.isError())
            {
                return new Respuesta(false , request.getError() , "");

            }
            CategoriaDto categoria = (CategoriaDto) request.readEntity(CategoriaDto.class);//
            return new Respuesta(true , "" , "" , "Categoria" , categoria);
        }
        catch(Exception ex)
        {
            Logger.getLogger(CategoriaService.class.getName()).log(Level.SEVERE , "Error guardando el categoria." , ex);
            return new Respuesta(false , "Error guardando el categoria." , "guardarCategoria " + ex.getMessage());
        }
    }
}
