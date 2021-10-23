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
import javax.persistence.*;
import javax.ejb.*;

/**
 *
 * @author jp015
 */
@LocalBean
@Stateless
public class EmpleadoServices
{

    private static final Logger LOG = Logger.getLogger(EmpleadoServices.class.getName());

    //TODO
    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;

    public Respuesta validarEmpleado(String empUsuario , String empContra)
    {
        try
        {
            Query qryEmpleado = em.createNamedQuery("Empleado.findByUsuClave" , Empleados.class);
            qryEmpleado.setParameter("empUsuario" , empUsuario);
            qryEmpleado.setParameter("empContra" , empContra);

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Empleado" , new EmpleadoDto((Empleados) qryEmpleado.getSingleResult()));

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existe un empleado con el código ingresado." , "getEmpleado NoResultException");
        }
        catch(NonUniqueResultException ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el empleado." , "getEmpleado NonUniqueResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el empleado." , "getEmpleado " + ex.getMessage());
        }
    }

    public Respuesta getEmpleados(String cedula , String nombre , String pApellido)
    {
        try
        {
            Query qryEmpleado = em.createNamedQuery("Empleado.findByCedulaNombrePapellido" , Empleados.class);
            qryEmpleado.setParameter("empCedula" , cedula);
            qryEmpleado.setParameter("empNombre" , nombre);
            qryEmpleado.setParameter("empPapellido" , pApellido);
            List<Empleados> empleados = (List<Empleados>) qryEmpleado.getResultList();
            List<EmpleadoDto> empleadosDto = new ArrayList<>();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Empleados" , empleadosDto);

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existen empleados con los criterios ingresados." , "getEmpleados NoResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el empleado." , "getEmpleados " + ex.getMessage());
        }
    }

    public Respuesta getEmpleado(Long empId)
    {
        try
        {
            Query qryEmpleado = em.createNamedQuery("Empleados.findByEmpId" , Empleados.class);
            qryEmpleado.setParameter("empId" , empId);

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Empleado" , new EmpleadoDto((Empleados) qryEmpleado.getSingleResult()));

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existe un empleado con el código ingresado." , "getEmpleado NoResultException");
        }
        catch(NonUniqueResultException ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el empleado." , "getEmpleado NonUniqueResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el empleado." , "getEmpleado " + ex.getMessage());
        }
    }

    public Respuesta guardarEmpleado(EmpleadoDto empleadoDto)
    {
        try
        {
            Empleados empleado;
            if(empleadoDto.getId() != null && empleadoDto.getId() > 0)
            {
                empleado = em.find(Empleados.class , empleadoDto.getId());
                if(empleado == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el empleado a modificar." , "guardarEmpleado NoResultException");
                }
                empleado.actualizarEmpleados(empleadoDto);
                empleado = em.merge(empleado);
            }
            else
            {
                empleado = new Empleados(empleadoDto);
                em.persist(empleado);
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Empleado" , new EmpleadoDto(empleado));
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al guardar el empleado." , "guardarEmpleado " + ex.getMessage());
        }
    }

    public Respuesta eliminarEmpleado(Long id)
    {
        try
        {
            Empleados empleado;
            if(id != null && id > 0)
            {
                empleado = em.find(Empleados.class , id);
                if(empleado == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el empleado a eliminar." , "eliminarEmpleado NoResultException");
                }
                em.remove(empleado);
            }
            else
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "Debe cargar el empleado a eliminar." , "eliminarEmpleado NoResultException");
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "");
        }
        catch(Exception ex)
        {
            if(ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class)
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "No se puede eliminar el empleado porque tiene relaciones con otros registros." , "eliminarEmpleado " + ex.getMessage());
            }
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el empleado." , "eliminarEmpleado " + ex.getMessage());
        }
    }

}
