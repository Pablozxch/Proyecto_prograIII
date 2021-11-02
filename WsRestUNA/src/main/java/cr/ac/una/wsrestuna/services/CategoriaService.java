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
public class CategoriaService
{
    
    private static final Logger LOG = Logger.getLogger(EmpleadoService.class.getName());

    //TODO
    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;
    
    public Respuesta getCategoriabyNamae(String CatNombre)// Un unico producto por id
    {
        try
        {
            Query qryCategoria = em.createNamedQuery("   Categoria.findByCatNombre" , Categoria.class);
            qryCategoria.setParameter("CatNombre" , CatNombre);
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Categoria" , new CategoriaDto((Categoria) qryCategoria.getSingleResult()));
        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existe un categoria con el nombre ingresado." , "getCategoria NoResultException");
        }
        catch(NonUniqueResultException ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el categoria." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el categoria." , "getCategoria NonUniqueResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el categoria." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el categoria." , "getCategoria " + ex.getMessage());
        }
        
    }
    
    public Respuesta getCategoria(Long catId)// Un unico producto por id
    {
        try
        {
            Query qryCategoria = em.createNamedQuery("Categoria.findByCatId" , Categoria.class);
            qryCategoria.setParameter("catId" , catId);
            
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Categoria" , new CategoriaDto((Categoria) qryCategoria.getSingleResult()));
            
        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existe un categoria con el código ingresado." , "getCategoria NoResultException");
        }
        catch(NonUniqueResultException ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el categoria." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el categoria." , "getCategoria NonUniqueResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el categoria." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el categoria." , "getCategoria " + ex.getMessage());
        }
    }
    
    public Respuesta getCategorias()
    {
        try
        {
            Query qryCategorias = em.createNamedQuery("Categoria.findAll" , Categoria.class);
            List<Categoria> categorias = (List<Categoria>) qryCategorias.getResultList();
            List<CategoriaDto> categoriasDto = new ArrayList<>();
            categorias.forEach(categoria ->
            {
                categoriasDto.add(new CategoriaDto(categoria));
            });
            
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Categorias" , categoriasDto);
            
        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existen categorias con los criterios ingresados." , "getCategorias NoResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el categoria." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el categoria." , "getCategorias " + ex.getMessage());
        }
    }
    
    public Respuesta guardarCategoria(CategoriaDto categoriaDto)
    {
        try
        {
            Categoria categoria;
            if(categoriaDto.getId() != null && categoriaDto.getId() > 0)
            {
                categoria = em.find(Categoria.class , categoriaDto.getId());
                if(categoria == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el categoria a modificar." , "guardarCategoria NoResultException");
                }
                categoria.actualizarCategoria(categoriaDto);
                categoria = em.merge(categoria);
            }
            else
            {
                categoria = new Categoria(categoriaDto);
                em.persist(categoria);
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Categoria" , new CategoriaDto(categoria));
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el categoria." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al guardar el categoria." , "guardarCategoria " + ex.getMessage());
        }
    }
    
    public Respuesta eliminarCategoria(Long id)
    {
        try
        {
            Categoria categoria;
            if(id != null && id > 0)
            {
                categoria = em.find(Categoria.class , id);
                if(categoria == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el categoria a eliminar." , "eliminarCategoria NoResultException");
                }
                em.remove(categoria);
            }
            else
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "Debe cargar el categoria a eliminar." , "eliminarCategoria NoResultException");
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "");
        }
        catch(Exception ex)
        {
            if(ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class)
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "No se puede eliminar el categoria porque tiene relaciones con otros registros." , "eliminarCategoria " + ex.getMessage());
            }
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el categoria." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el categoria." , "eliminarCategoria " + ex.getMessage());
        }
    }
}
