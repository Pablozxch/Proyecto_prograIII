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
public class OrdenService
{

    private static final Logger LOG = Logger.getLogger(EmpleadoService.class.getName());

    //TODO
    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;

    public Respuesta getOrdenes()
    {
        try
        {
            Query qryOrdens = em.createNamedQuery("Orden.findAll" , Orden.class);
            List<Orden> ordens = (List<Orden>) qryOrdens.getResultList();
            List<OrdenDto> ordensDto = new ArrayList<>();
            ordens.forEach(orden ->
            {
                ordensDto.add(new OrdenDto(orden));
            });

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Ordenes" , ordensDto);

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existen ordens con los criterios ingresados." , "getOrdens NoResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el orden." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el orden." , "getOrdens " + ex.getMessage());
        }
    }

    public Respuesta guardarOrden(OrdenDto ordenDto)
    {
        try
        {
            Orden orden;
            if(ordenDto.getId() != null && ordenDto.getId() > 0)
            {
                orden = em.find(Orden.class , ordenDto.getId());
                if(orden == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el orden a modificar." , "guardarOrden NoResultException");
                }
                orden.actualizarOrden(ordenDto);
                orden = em.merge(orden);
            }
            else
            {
                orden = new Orden(ordenDto);
                em.persist(orden);
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Orden" , new OrdenDto(orden));
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el orden." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al guardar el orden." , "guardarOrden " + ex.getMessage());
        }
    }

    public Respuesta eliminarOrden(Long id)
    {
        try
        {
            Orden orden;
            if(id != null && id > 0)
            {
                orden = em.find(Orden.class , id);
                if(orden == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el orden a eliminar." , "eliminarOrden NoResultException");
                }
                em.remove(orden);
            }
            else
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "Debe cargar el orden a eliminar." , "eliminarOrden NoResultException");
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "");
        }
        catch(Exception ex)
        {
            if(ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class)
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "No se puede eliminar el orden porque tiene relaciones con otros registros." , "eliminarOrden " + ex.getMessage());
            }
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el orden." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el orden." , "eliminarOrden " + ex.getMessage());
        }
    }
}
