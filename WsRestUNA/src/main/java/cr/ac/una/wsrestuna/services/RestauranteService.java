/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.services;

import cr.ac.una.wsrestuna.models.*;
import cr.ac.una.wsrestuna.utils.*;
import java.util.*;
import java.util.logging.*;
import javax.ejb.*;
import javax.persistence.*;

/**
 *
 * @author jp015
 */
@LocalBean
@Stateless
public class RestauranteService
{

    private static final Logger LOG = Logger.getLogger(RestauranteService.class.getName());

    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;

    public Respuesta getRestaurante(Long resId)// Un unico producto por id
    {
        try
        {
            Query qryRestaurante = em.createNamedQuery("Restaurante.findByResId" , Restaurante.class);
            qryRestaurante.setParameter("resId" , resId);

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Restaurante" , new RestauranteDto((Restaurante) qryRestaurante.getSingleResult()));

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existe un empleado con el código ingresado." , "getRestaurante NoResultException");
        }
        catch(NonUniqueResultException ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el empleado." , "getRestaurante NonUniqueResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el empleado." , "getRestaurante " + ex.getMessage());
        }
    }

    public Respuesta getRestaurantes(String proNombre , String proCosto)
    {
        try
        {
            Query qryRestaurantes = em.createNamedQuery("Restaurante.findAll" , Restaurante.class);
            List<Restaurante> restaurantes = (List<Restaurante>) qryRestaurantes.getResultList();
            List<RestauranteDto> restaurantesDto = new ArrayList<>();
            restaurantes.forEach(restaurante ->
            {
                restaurantesDto.add(new RestauranteDto(restaurante));
            });

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Restaurantes" , restaurantesDto);

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existen restaurantes con los criterios ingresados." , "getRestaurantes NoResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el empleado." , "getRestaurantes " + ex.getMessage());
        }
    }

}
