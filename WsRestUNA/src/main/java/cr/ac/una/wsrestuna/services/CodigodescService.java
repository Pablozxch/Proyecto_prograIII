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
public class CodigodescService
{

    private static final Logger LOG = Logger.getLogger(EmpleadoService.class.getName());

    //TODO
    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;

    public Respuesta getCodNombre(String codUrl)
    {
        try
        {
            Query qryCodigodesc = em.createNamedQuery("Codigodesc.findByCodUrl" , Codigodesc.class);
            qryCodigodesc.setParameter("codUrl" , codUrl);

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Codigodesc" , new CodigodescDto((Codigodesc) qryCodigodesc.getSingleResult()));

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existe un coidgodesc con el código ingresado." , "getCodigodesc NoResultException");
        }
        catch(NonUniqueResultException ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el coidgodesc." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el coidgodesc." , "getCodigodesc NonUniqueResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el coidgodesc." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el coidgodesc." , "getCodigodesc " + ex.getMessage());
        }
    }

    public Respuesta getCodigosdesc()
    {
        try
        {
            System.out.println("sdasd");
            Query qryCodigosdesc = em.createNamedQuery("Codigodesc.findAll" , Codigodesc.class);
            List<Codigodesc> codigosdec = (List<Codigodesc>) qryCodigosdesc.getResultList();
            List<CodigodescDto> codigosdecDto = new ArrayList<>();
            codigosdec.forEach(coidgodesc ->
            {
                codigosdecDto.add(new CodigodescDto(coidgodesc));
                System.out.println("t "+coidgodesc.toString());
            });

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Codigo" , codigosdecDto);

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existen codigosdec con los criterios ingresados." , "getCodigosdesc NoResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el coidgodesc." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el coidgodesc." , "getCodigosdesc " + ex.getMessage());
        }
    }

    public Respuesta guardarCodigodesc(CodigodescDto codigodescDto)
    {
        try
        {
            Codigodesc codigodesc;
            if(codigodescDto.getId() != null && codigodescDto.getId() > 0)
            {
                codigodesc = em.find(Codigodesc.class , codigodescDto.getId());
                if(codigodesc == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el codigodesc a modificar." , "guardarCodigodesc NoResultException");
                }
                codigodesc.actualizarCodigodesc(codigodescDto);
                codigodesc = em.merge(codigodesc);
            }
            else
            {
                codigodesc = new Codigodesc(codigodescDto);
                em.persist(codigodesc);
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Codigodesc" , new CodigodescDto(codigodesc));
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el codigodesc." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al guardar el codigodesc." , "guardarCodigodesc " + ex.getMessage());
        }
    }

    public Respuesta eliminarCodigodesc(Long id)
    {
        try
        {
            Codigodesc codigodesc;
            if(id != null && id > 0)
            {
                codigodesc = em.find(Codigodesc.class , id);
                if(codigodesc == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el codigodesc a eliminar." , "eliminarCodigodesc NoResultException");
                }
                em.remove(codigodesc);
            }
            else
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "Debe cargar el codigodesc a eliminar." , "eliminarCodigodesc NoResultException");
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "");
        }
        catch(Exception ex)
        {
            if(ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class)
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "No se puede eliminar el codigodesc porque tiene relaciones con otros registros." , "eliminarCodigodesc " + ex.getMessage());
            }
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el codigodesc." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el codigodesc." , "eliminarCodigodesc " + ex.getMessage());
        }
    }
}
