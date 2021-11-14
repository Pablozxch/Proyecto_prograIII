/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.services;

import cr.ac.una.wsrestuna.models.*;
import cr.ac.una.wsrestuna.utils.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.persistence.*;
import javax.ejb.*;
import javax.naming.*;
import javax.sql.*;
import net.sf.jasperreports.engine.*;

/**
 *
 * @author jp015
 */
@LocalBean
@Stateless
public class ProductoService
{

    private static final Logger LOG = Logger.getLogger(ProductoService.class.getName());

    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;

    public Respuesta getProducto(Long proId)// Un unico producto por id
    {
        try
        {
            Query qryProducto = em.createNamedQuery("Producto.findByProId" , Producto.class);
            qryProducto.setParameter("proId" , proId);

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Producto" , new ProductoDto((Producto) qryProducto.getSingleResult()));

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existe un empleado con el código ingresado." , "getProducto NoResultException");
        }
        catch(NonUniqueResultException ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el empleado." , "getProducto NonUniqueResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el empleado." , "getProducto " + ex.getMessage());
        }
    }

    public Respuesta getProductos()
    {
        try
        {
            Query qryProductos = em.createNamedQuery("Producto.findAll" , Producto.class);
            List<Producto> productos = (List<Producto>) qryProductos.getResultList();
            List<ProductoDto> productosDto = new ArrayList<>();
            productos.forEach(producto ->
            {
                productosDto.add(new ProductoDto(producto));
            });

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Productos" , productosDto);

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existen productos con los criterios ingresados." , "getProductos NoResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el empleado." , "getProductos " + ex.getMessage());
        }
    }

    public Respuesta guardarProducto(ProductoDto empleadoDto)
    {
        try
        {
            Producto empleado;
            if(empleadoDto.getId() != null && empleadoDto.getId() > 0)
            {
                empleado = em.find(Producto.class , empleadoDto.getId());
                if(empleado == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el empleado a modificar." , "guardarProducto NoResultException");
                }
                empleado.actualizarProducto(empleadoDto);
                empleado = em.merge(empleado);
            }
            else
            {
                empleado = new Producto(empleadoDto);
                System.out.println("El valor antes de colocar el restaurante es " + empleado.toString());
                empleado.setResId(new Restaurante(empleadoDto.getRestauranteDto()));
                System.out.println("El valor despues de colocar el restaurante es " + empleado.toString());
                em.persist(empleado);
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Producto" , new ProductoDto(empleado));
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al guardar el empleado." , "guardarProducto " + ex.getMessage());
        }
    }

    public Respuesta eliminarProducto(Long id)
    {
        try
        {
            Producto empleado;
            if(id != null && id > 0)
            {
                empleado = em.find(Producto.class , id);
                if(empleado == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el empleado a eliminar." , "eliminarProducto NoResultException");
                }
                em.remove(empleado);
            }
            else
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "Debe cargar el empleado a eliminar." , "eliminarProducto NoResultException");
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "");
        }
        catch(Exception ex)
        {
            if(ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class)
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "No se puede eliminar el empleado porque tiene relaciones con otros registros." , "eliminarProducto " + ex.getMessage());
            }
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el empleado." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el empleado." , "eliminarProducto " + ex.getMessage());
        }
    }

    public Respuesta reporteProductosVendidos(Long IDrestaurante , String Inicial , String Final)
    {

        try
        {
            InputStream x = FacturaService.class.getClassLoader().getResourceAsStream("/cr/ac/una/wsrestuna/reportes/ProductosVendidos.jrxml");
            JasperReport jasper = JasperCompileManager.compileReport(x);
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("jdbc/RestUNA");
            Connection connection = dataSource.getConnection();

            HashMap<String , Object> map = new HashMap<>();
            map.put("IDrestaurante" , IDrestaurante);
            map.put("Inicial" , Inicial);
            map.put("Final" , Final);
            JasperPrint print = JasperFillManager.fillReport(jasper , map , connection);
            byte[] s = JasperExportManager.exportReportToPdf(print);
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Producto" , s);

        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el registro." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "No se puede generar correctamente el reporte." , "eliminarFactura " + ex.getMessage());
        }
    }
}
