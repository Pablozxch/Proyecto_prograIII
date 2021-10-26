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
public class SalonService
{

    private static final Logger LOG = Logger.getLogger(SalonService.class.getName());

    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;

    public Respuesta getSalon(Long salId)// Un unico producto por id
    {
        try
        {
            Query qrySalon = em.createNamedQuery("Salon.findBySalId" , Salon.class);
            qrySalon.setParameter("salId" , salId);
            SalonDto salDto = new SalonDto((Salon) qrySalon.getSingleResult());
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Salon" , salDto);

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existe un salon con el código ingresado." , "getSalon NoResultException");
        }
        catch(NonUniqueResultException ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el salon." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el salon." , "getSalon NonUniqueResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el salon." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el salon." , "getSalon " + ex.getMessage());
        }
    }

    public Respuesta getSalones()
    {
        try
        {

            Query qrySalones = em.createNamedQuery("Salon.findAll" , Salon.class);
            List<Salon> salones = (List<Salon>) qrySalones.getResultList();
            List<SalonDto> salonesDto = new ArrayList<>();
            salones.forEach(salon ->
            {
                salonesDto.add(new SalonDto(salon));
            });

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Salones" , salonesDto);

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existen salones con los criterios ingresados." , "getSalones NoResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el salon." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el salon." , "getSalones " + ex.getMessage());
        }
    }

}
