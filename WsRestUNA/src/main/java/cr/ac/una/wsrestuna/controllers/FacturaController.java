/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controllers;


import cr.ac.una.wsrestuna.models.*;
import cr.ac.una.wsrestuna.services.*;
import cr.ac.una.wsrestuna.utils.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.logging.*;
import javax.ejb.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author jp015
 */
@Path("/FacturaController")
public class FacturaController
{

    @EJB
    FacturaService facturaService;

    @EJB
    RestauranteService restauranteService;

    void send(String correo , String pass , String correotosend , File factura , RestauranteDto resta) throws MessagingException , IOException , BadElementException
    {
         try
        {
            // When creating a mail session you need to create a Properties object that contains
            // any properties that the session needs to send or receive mail.
            Properties properties = new Properties();

            // getDefaultInstance() has all the default settings for a mail session.
            // However, you need to specify the smtp host by using properties.
            // This is localhost because it specifies that the SMTP server is running on the same
            // computer as the application,
            properties.put("mail.smtp.host" , "smtp.gmail.com");

            // For gmail mail.smtp.auth is required.
            properties.put("mail.smtp.auth" , true);
            properties.put("mail.smtp.starttls.enable" , true);

            // Set the protocol to SMTP and the port to 25.
            // These are actually the defaults and aren't necessary but it shows you how to
            // set the protocol and port number.
            properties.put("mail.transport.protocol" , "smtp");
            properties.put("mail.smtp.port" , 587);

            // Create a mail session so you can create and send an email message.
            Session session = Session.getInstance(properties , new Authenticator()
            {
                @Override
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(correo , pass);
                }
            });
            Message message = new MimeMessage(session);
            message.setSubject("!!Factura Digital!!");
            Address addressTo = new InternetAddress(correotosend);
            message.setRecipient(Message.RecipientType.TO , addressTo);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<h1>Factura digital de la empresa " + pass + "</h1>"
                      + "<footer> "
                      + "<h3> "
                      + "Derechos reservados @" + pass
                      + "</h3>"
                      + " </footer>" , "text/html");
            MimeBodyPart attachment = new MimeBodyPart();
            try
            {
                attachment.attachFile(factura);
            }
            catch(IOException ex)
            {
                Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
            }

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachment);
            message.setContent(multipart);
            Transport.send(message);

        }
        catch(AddressException ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
        }
    }

    @GET
    @Path("/factura/{idRes}/{nombrePersona}/{correoPersona}/{idFac}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendEmail(@PathParam("idRes") Long idRes , @PathParam("nombrePersona") String nombrePersona , @PathParam("correoPersona") String correoPersona , @PathParam("idFac") Long idFac)
    {

        Respuesta res = restauranteService.getRestaurante(idRes);
        RestauranteDto resta = (RestauranteDto) res.getResultado("Restaurante");
        Respuesta res2 = facturaService.reporteFacturaCorreo(idFac , correoPersona , nombrePersona);
        File file = new File("Factura.pdf");
        byte[] decoder = (byte[]) res2.getResultado("Factura");
        try(FileOutputStream fos = new FileOutputStream(file);)
        {
            fos.write(decoder);
            send(resta.getCorreo() , resta.getNombre() , correoPersona , file , resta);
        }
        catch(Exception e)
        {
        }

        return Response.ok().build();
    }

    /*
    IMPLEMENTAR LOS SERVICES TANTO DEL SERVICES COMO EL CLIENTE Y DARLE LA FUNCION AL ENVIAR CORREO DEL CLIENTE
    
     */
    @GET
    @Path("/factura/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFactura(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = facturaService.getFactura(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((FacturaDto) res.getResultado("Factura")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la factura ").build();//TODO
        }
    }

    @GET
    @Path("/factura")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFacturas()
    {
        try
        {
            Respuesta res = facturaService.getFacturas();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }

            return Response.ok(new GenericEntity<List<FacturaDto>>((List<FacturaDto>) res.getResultado("Facturas"))
            {
            }).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el factura ").build();//TODO
        }
    }

    @POST
    @Path("/factura")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarFactura(FacturaDto factura)
    {
        try
        {

            Respuesta res = facturaService.guardarFactura(factura);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((FacturaDto) res.getResultado("Factura")).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el factura ").build();//TODO
        }
    }

    @DELETE
    @Path("/factura/{id}")
    public Response eliminarFactura(@PathParam(("id")) Long id)
    {
        try
        {
            Respuesta res = facturaService.eliminarFactura(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el factura ").build();//TODO
        }
    }

    @GET
    @Path("/facturalast")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response lasto()
    {
        long id = facturaService.last();;
        try
        {
            Respuesta res = facturaService.getFactura(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((FacturaDto) res.getResultado("Factura")).build();//TODO

        }
        catch(Exception ex)
        {
            Logger.getLogger(CierreCajaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la cierre cajas ").build();//TODO
        }
    }

    @GET
    @Path("/factura/{fechai}/{fechaf}/{idres}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listadoFacturas(@PathParam("fechai") String fechaI , @PathParam("fechaf") String fechaF , @PathParam("idres") Long idRes)
    {
        try
        {
            Respuesta res = facturaService.reporteListadoFacturas(idRes , fechaI , fechaF);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            byte[] bytes = (byte[]) res.getResultado("Factura");
            return Response.ok(bytes).build();//TODO
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el factura ").build();//TODO
        }
    }

}
