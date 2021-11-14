/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.services;

import cr.ac.una.wsrestuna.models.*;
import cr.ac.una.wsrestuna.utils.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.persistence.*;
import net.sf.jasperreports.engine.*;

/**
 *
 * @author jp015
 */
@LocalBean
@Stateless
public class FacturaService
{

    private static final Logger LOG = Logger.getLogger(FacturaService.class.getName());

    //TODO
    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;

    public Respuesta getFactura(Long facId)// Un unico producto por id
    {
        try
        {
            Query qryFactura = em.createNamedQuery("Factura.findByFacId" , Factura.class);
            qryFactura.setParameter("facId" , facId);
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Factura" , new FacturaDto((Factura) qryFactura.getSingleResult()));

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existe un factura con el código ingresado." , "getFactura NoResultException");
        }
        catch(NonUniqueResultException ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el factura." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el factura." , "getFactura NonUniqueResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el factura." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el factura." , "getFactura " + ex.getMessage());
        }
    }

    public Respuesta getFacturas()
    {
        try
        {
            Query qryFacturas = em.createNamedQuery("Factura.findAll" , Factura.class);
            List<Factura> facturas = (List<Factura>) qryFacturas.getResultList();
            List<FacturaDto> facturasDto = new ArrayList<>();
            facturas.forEach(factura ->
            {
                facturasDto.add(new FacturaDto(factura));
            });

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Facturas" , facturasDto);

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existen facturas con los criterios ingresados." , "getFacturas NoResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el factura." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el factura." , "getFacturas " + ex.getMessage());
        }
    }

    public Long last()
    {

        Query qryFacturas = em.createNamedQuery("Factura.findlast" , Factura.class);
        long idLast = (long) qryFacturas.getSingleResult();
        return idLast;
    }

    public Respuesta guardarFactura(FacturaDto facturaDto)
    {
        try
        {
            Factura factura;
            if(facturaDto.getId() != null && facturaDto.getId() > 0)
            {
                factura = em.find(Factura.class , facturaDto.getId());
                if(factura == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el factura a modificar." , "guardarFactura NoResultException");
                }
                factura.actualizarFactura(facturaDto);
                factura = em.merge(factura);
            }
            else
            {
                factura = new Factura(facturaDto);
                em.persist(factura);
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Factura" , new FacturaDto(factura));
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el factura." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al guardar el factura." , "guardarFactura " + ex.getMessage());
        }
    }

    public Respuesta eliminarFactura(Long id)
    {
        try
        {
            Factura factura;
            if(id != null && id > 0)
            {
                factura = em.find(Factura.class , id);
                if(factura == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el factura a eliminar." , "eliminarFactura NoResultException");
                }
                em.remove(factura);
            }
            else
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "Debe cargar el factura a eliminar." , "eliminarFactura NoResultException");
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "");
        }
        catch(Exception ex)
        {
            if(ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class)
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "No se puede eliminar el factura porque tiene relaciones con otros registros." , "eliminarFactura " + ex.getMessage());
            }
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el factura." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el factura." , "eliminarFactura " + ex.getMessage());
        }
    }

    public Respuesta reporteFacturaCorreo(Long idFac , String correo , String nombre)
    {

        try
        {
            InputStream x = FacturaService.class.getClassLoader().getResourceAsStream("/cr/ac/una/wsrestuna/reportes/ParaCorreo.jrxml");
            JasperReport jasper = JasperCompileManager.compileReport(x);
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("jdbc/RestUNA");
            Connection connection = dataSource.getConnection();

            HashMap<String , Object> map = new HashMap<>();
            map.put("ID_FACTURA" , idFac);
            map.put("CORREO" , correo);
            map.put("NOMBRE" , nombre);
            JasperPrint print = JasperFillManager.fillReport(jasper , map , connection);
            String a = JasperExportManager.exportReportToXml(print);
            System.out.println(a);
            byte[] s = JasperExportManager.exportReportToPdf(print);
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Factura" , s);

        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el registro." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el factura." , "eliminarFactura " + ex.getMessage());
        }
    }

    public Respuesta reporteListadoFacturas(Long IDrestaurante , String ii , String ff)
    {

        try
        {
            InputStream x = FacturaService.class.getClassLoader().getResourceAsStream("/cr/ac/una/wsrestuna/reportes/ListadoFactura.jrxml");
            JasperReport jasper = JasperCompileManager.compileReport(x);
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("jdbc/RestUNA");
            Connection connection = dataSource.getConnection();

            HashMap<String , Object> map = new HashMap<>();
            map.put("IDrestaurante" , IDrestaurante);
            map.put("Inicio" , ii);
            map.put("Final" , ff);
            JasperPrint print = JasperFillManager.fillReport(jasper , map , connection);
            String a = JasperExportManager.exportReportToXml(print);
            //System.out.println(JasperExportManager.exportReportToHtmlFile(a));
            byte[] s = JasperExportManager.exportReportToPdf(print);
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Factura" , s);

        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el registro." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el factura." , "eliminarFactura " + ex.getMessage());
        }
    }

}
