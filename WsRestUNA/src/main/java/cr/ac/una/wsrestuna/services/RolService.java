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
public class RolService
{

    private static final Logger LOG = Logger.getLogger(EmpleadoService.class.getName());

    //TODO
    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;

    public Respuesta getRoles()
    {
        try
        {
            Query qryRoles = em.createNamedQuery("Rol.findAll" , Rol.class);
            List<Rol> categorias = (List<Rol>) qryRoles.getResultList();
            List<RolDto> categoriasDto = new ArrayList<>();
            categorias.forEach(rol ->
            {
                categoriasDto.add(new RolDto(rol));
            });

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Roles" , categoriasDto);

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existen roles." , "getRoles NoResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el rol." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el rol." , "getRoles " + ex.getMessage());
        }
    }
}
