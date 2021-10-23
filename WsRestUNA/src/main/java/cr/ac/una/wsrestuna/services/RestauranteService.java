/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.services;

import cr.ac.una.wsrestuna.models.*;
import cr.ac.una.wsrestuna.utils.*;
import java.sql.*;
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
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existe un restaurante con el código ingresado." , "getRestaurante NoResultException");
        }
        catch(NonUniqueResultException ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el restaurante." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el restaurante." , "getRestaurante NonUniqueResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el restaurante." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el restaurante." , "getRestaurante " + ex.getMessage());
        }
    }

    public Respuesta getRestaurantes()
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
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el restaurante." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el restaurante." , "getRestaurantes " + ex.getMessage());
        }
    }

    public Respuesta guardarRestaurante(RestauranteDto restauranteDto)
    {
        try
        {
            Restaurante restaurante;
            if(restauranteDto.getId() != null && restauranteDto.getId() > 0)
            {
                restaurante = em.find(Restaurante.class , restauranteDto.getId());
                if(restaurante == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el restaurante a modificar." , "guardarRestaurante NoResultException");
                }
                restaurante.actualizarRestaurante(restauranteDto);
                restaurante = em.merge(restaurante);
            }
            else
            {
                restaurante = new Restaurante(restauranteDto);
                em.persist(restaurante);
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Restaurante" , new RestauranteDto(restaurante));
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el restaurante." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al guardar el restaurante." , "guardarRestaurante " + ex.getMessage());
        }
    }

    public Respuesta eliminarRestaurante(Long id)
    {
        try
        {
            Restaurante restaurante;
            if(id != null && id > 0)
            {
                restaurante = em.find(Restaurante.class , id);
                if(restaurante == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el restaurante a eliminar." , "eliminarRestaurante NoResultException");
                }
                em.remove(restaurante);
            }
            else
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "Debe cargar el restaurante a eliminar." , "eliminarRestaurante NoResultException");
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "");
        }
        catch(Exception ex)
        {
            if(ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class)
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "No se puede eliminar el restaurante porque tiene relaciones con otros registros." , "eliminarRestaurante " + ex.getMessage());
            }
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el restaurante." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el restaurante." , "eliminarRestaurante " + ex.getMessage());
        }
    }
}
