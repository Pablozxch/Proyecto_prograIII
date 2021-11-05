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
public class MesaService
{

    private static final Logger LOG = Logger.getLogger(MesaService.class.getName());

    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;

    public Respuesta getMesas()
    {
        try
        {
            Query qryMesas = em.createNamedQuery("Mesa.findAll" , Mesa.class);
            List<Mesa> mesas = (List<Mesa>) qryMesas.getResultList();
            List<MesaDto> mesasDto = new ArrayList<>();
            mesas.forEach(mesa ->
            {
                mesasDto.add(new MesaDto(mesa));
            });

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Mesas" , mesasDto);

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existen mesas con los criterios ingresados." , "getMesas NoResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el mesa." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el mesa." , "getMesas " + ex.getMessage());
        }
    }

    public Respuesta guardarMesa(MesaDto mesaDto)
    {
        try
        {
            Mesa mesa;
            if(mesaDto.getId() != null && mesaDto.getId() > 0)
            {
                mesa = em.find(Mesa.class , mesaDto.getId());
                if(mesa == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el mesa a modificar." , "guardarMesa NoResultException");
                }
                mesa.actualizarMesa(mesaDto);
                mesa = em.merge(mesa);
            }
            else
            {
                mesa = new Mesa(mesaDto);
                em.persist(mesa);
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Mesa" , new MesaDto(mesa));
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el mesa." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al guardar el mesa." , "guardarMesa " + ex.getMessage());
        }
    }

    public Respuesta eliminarMesa(Long id)
    {
        try
        {
            Mesa mesa;
            if(id != null && id > 0)
            {
                mesa = em.find(Mesa.class , id);
                if(mesa == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el mesa a eliminar." , "eliminarMesa NoResultException");
                }
                em.remove(mesa);
            }
            else
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "Debe cargar el mesa a eliminar." , "eliminarMesa NoResultException");
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "");
        }
        catch(Exception ex)
        {
            if(ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class)
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "No se puede eliminar el mesa porque tiene relaciones con otros registros." , "eliminarMesa " + ex.getMessage());
            }
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el mesa." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el mesa." , "eliminarMesa " + ex.getMessage());
        }
    }
}
