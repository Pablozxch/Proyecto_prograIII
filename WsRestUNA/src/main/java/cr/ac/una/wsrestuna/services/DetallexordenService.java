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
public class DetallexordenService
{

    private static final Logger LOG = Logger.getLogger(EmpleadoService.class.getName());

    //TODO
    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;

    public Respuesta getDetallexordenes()
    {
        try
        {
            Query qryDetallexordens = em.createNamedQuery("Detallexorden.findAll" , Detallexorden.class);
            List<Detallexorden> ordens = (List<Detallexorden>) qryDetallexordens.getResultList();
            List<DetallexordenDto> ordensDto = new ArrayList<>();
            ordens.forEach(orden ->
            {
                ordensDto.add(new DetallexordenDto(orden));
            });

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Detallexordenes" , ordensDto);

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existen ordens con los criterios ingresados." , "getDetallexordens NoResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el orden." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el orden." , "getDetallexordens " + ex.getMessage());
        }
    }

    public Respuesta guardarDetallexorden(DetallexordenDto ordenDto)
    {
        try
        {
            Detallexorden detallexorden;
            if(ordenDto.getId() != null && ordenDto.getId() > 0)
            {
                detallexorden = em.find(Detallexorden.class , ordenDto.getId());
                if(detallexorden == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el orden a modificar." , "guardarDetallexorden NoResultException");
                }
                detallexorden.actualizarDetallexorden(ordenDto);
                detallexorden = em.merge(detallexorden);
            }
            else
            {
                detallexorden = new Detallexorden(ordenDto);
                em.persist(detallexorden);
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Detallexorden" , new DetallexordenDto(detallexorden));
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el orden." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al guardar el orden." , "guardarDetallexorden " + ex.getMessage());
        }
    }

    public Respuesta eliminarDetallexorden(Long id)
    {
        try
        {
            Detallexorden detallexorden;
            if(id != null && id > 0)
            {
                detallexorden = em.find(Detallexorden.class , id);
                if(detallexorden == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el orden a eliminar." , "eliminarDetallexorden NoResultException");
                }
                em.remove(detallexorden);
            }
            else
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "Debe cargar el orden a eliminar." , "eliminarDetallexorden NoResultException");
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "");
        }
        catch(Exception ex)
        {
            if(ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class)
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "No se puede eliminar el orden porque tiene relaciones con otros registros." , "eliminarDetallexorden " + ex.getMessage());
            }
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el orden." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el detallexOrden." , "eliminarDetallexorden " + ex.getMessage());
        }
    }
}
