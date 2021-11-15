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
import javax.ejb.*;
import javax.naming.*;
import javax.persistence.*;
import javax.sql.*;
import net.sf.jasperreports.engine.*;

/**
 *
 * @author jp015
 */
@LocalBean
@Stateless
public class CierreCajaService
{

    private static final Logger LOG = Logger.getLogger(CierreCajaService.class.getName());

    //TODO
    @PersistenceContext(unitName = "WsRestUNA")
    private EntityManager em;

    public Respuesta getCierrecajasbyNamae(String CatNombre)// Un unico producto por id
    {
        try
        {
            Query qryCierrecajas = em.createNamedQuery("   Cierrecajas.findByCatNombre" , Cierrecajas.class);
            qryCierrecajas.setParameter("CatNombre" , CatNombre);
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Cierrecajas" , new CierrecajasDto((Cierrecajas) qryCierrecajas.getSingleResult()));
        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existe un cierreCaja con el nombre ingresado." , "getCierrecajas NoResultException");
        }
        catch(NonUniqueResultException ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el cierreCaja." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el cierreCaja." , "getCierrecajas NonUniqueResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el cierreCaja." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el cierreCaja." , "getCierrecajas " + ex.getMessage());
        }

    }

    public Long last()
    {

        Query qryCierrecajas = em.createNamedQuery("Cierrecajas.findlast" , Cierrecajas.class);
        long idLast = (long) qryCierrecajas.getSingleResult();
        return idLast;
    }

    public Respuesta getCierrecajas(Long ccajId)// Un unico producto por id
    {
        try
        {
            Query qryCierrecajas = em.createNamedQuery("Cierrecajas.findByCcajId" , Cierrecajas.class);
            qryCierrecajas.setParameter("ccajId" , ccajId);

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Cierrecajas" , new CierrecajasDto((Cierrecajas) qryCierrecajas.getSingleResult()));

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existe un cierreCaja con el código ingresado." , "getCierrecajas NoResultException");
        }
        catch(NonUniqueResultException ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el cierreCaja." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el cierreCaja." , "getCierrecajas NonUniqueResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el cierreCaja." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el cierreCaja." , "getCierrecajas " + ex.getMessage());
        }
    }

    public Respuesta getCierrecajass()
    {
        try
        {
            Query qryCierrecajass = em.createNamedQuery("Cierrecajas.findAll" , Cierrecajas.class);
            List<Cierrecajas> cierreCajas = (List<Cierrecajas>) qryCierrecajass.getResultList();
            List<CierrecajasDto> cierreCajasDto = new ArrayList<>();
            cierreCajas.forEach(cierreCaja ->
            {
                cierreCajasDto.add(new CierrecajasDto(cierreCaja));
            });

            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "Cierrecajass" , cierreCajasDto);

        }
        catch(NoResultException ex)
        {
            return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No existen cierreCajas con los criterios ingresados." , "getCierrecajass NoResultException");
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al consultar el cierreCaja." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al consultar el cierreCaja." , "getCierrecajass " + ex.getMessage());
        }
    }

    public Respuesta guardarCierrecajas(CierrecajasDto cierreCajaDto)
    {
        try
        {
            Cierrecajas cierreCaja;
            if(cierreCajaDto.getId() != null && cierreCajaDto.getId() > 0)
            {
                cierreCaja = em.find(Cierrecajas.class , cierreCajaDto.getId());
                if(cierreCaja == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el cierreCaja a modificar." , "guardarCierrecajas NoResultException");
                }
                cierreCaja.actualizarCierreCajas(cierreCajaDto);
                cierreCaja = em.merge(cierreCaja);
            }
            else
            {
                cierreCaja = new Cierrecajas(cierreCajaDto);
                em.persist(cierreCaja);
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "CierreCaja" , new CierrecajasDto(cierreCaja));
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el cierreCaja." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al guardar el cierreCaja." , "guardarCierrecajas " + ex.getMessage());
        }
    }

    public Respuesta eliminarCierrecajas(Long id)
    {
        try
        {
            Cierrecajas cierreCaja;
            if(id != null && id > 0)
            {
                cierreCaja = em.find(Cierrecajas.class , id);
                if(cierreCaja == null)
                {
                    return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "No se encrontró el cierreCaja a eliminar." , "eliminarCierrecajas NoResultException");
                }
                em.remove(cierreCaja);
            }
            else
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_NOENCONTRADO , "Debe cargar el cierreCaja a eliminar." , "eliminarCierrecajas NoResultException");
            }
            em.flush();
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "");
        }
        catch(Exception ex)
        {
            if(ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class)
            {
                return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "No se puede eliminar el cierreCaja porque tiene relaciones con otros registros." , "eliminarCierrecajas " + ex.getMessage());
            }
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el cierreCaja." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el cierreCaja." , "eliminarCierrecajas " + ex.getMessage());
        }
    }

    public Respuesta reporteCompletoCajas(Long IDrestaurante , Long idCIerreCajas , String FECHA)
    {

        try
        {
            InputStream x = FacturaService.class.getClassLoader().getResourceAsStream("/cr/ac/una/wsrestuna/reportes/reporteCompletoCajas.jrxml");
            JasperReport jasper = JasperCompileManager.compileReport(x);
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("jdbc/RestUNA");
            Connection connection = dataSource.getConnection();

            HashMap<String , Object> map = new HashMap<>();
            map.put("ID" , idCIerreCajas);//id de cierre de cajas
            map.put("IDrestaurante" , IDrestaurante);
            map.put("FECHA" , FECHA);
            JasperPrint print = JasperFillManager.fillReport(jasper , map , connection);
            System.out.println(JasperExportManager.exportReportToXml(print));
            byte[] s = JasperExportManager.exportReportToPdf(print);
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "CierreCaja" , s);

        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el registro." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el factura." , "eliminarFactura " + ex.getMessage());
        }
    }

    public Respuesta reporteCierreCajero(String Fecha , Long idcierre , Long idRes)
    {

        try
        {
            InputStream x = FacturaService.class.getClassLoader().getResourceAsStream("/cr/ac/una/wsrestuna/reportes/CierreCajero.jrxml");
            JasperReport jasper = JasperCompileManager.compileReport(x);
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("jdbc/RestUNA");
            Connection connection = dataSource.getConnection();

            HashMap<String , Object> map = new HashMap<>();
            map.put("idCierreCaja" , idcierre);
            map.put("Fecha" , Fecha);
            map.put("IDrestaurante" , idRes);
            JasperPrint print = JasperFillManager.fillReport(jasper , map , connection);
            byte[] s = JasperExportManager.exportReportToPdf(print);
            return new Respuesta(true , CodigoRespuesta.CORRECTO , "" , "" , "CierreCaja" , s);

        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE , "Ocurrio un error al guardar el registro." , ex);
            return new Respuesta(false , CodigoRespuesta.ERROR_INTERNO , "Ocurrio un error al eliminar el factura." , "eliminarFactura " + ex.getMessage());
        }
    }

}
