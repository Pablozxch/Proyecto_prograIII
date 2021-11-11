/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controllers;

import cr.ac.una.wsrestuna.models.*;
import cr.ac.una.wsrestuna.services.*;
import cr.ac.una.wsrestuna.utils.*;
import java.io.*;
import java.util.*;
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

    void send(String correo , String pass , String correotosend , File factura) throws MessagingException
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
            messageBodyPart.setContent("<h1>Factura digital de la empresa" + pass + "<h1>" , "text/html");
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
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE , null , ex);
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
        Respuesta res2 = facturaService.reporteFactura(idFac , correoPersona , nombrePersona);
        File file = new File("Factura.pdf");
        byte[] decoder = (byte[]) res2.getResultado("Factura");
        try(FileOutputStream fos = new FileOutputStream(file);)
        {
            fos.write(decoder);
            send(resta.getCorreo() , resta.getNombre() , correoPersona , file);
        }
        catch(Exception e)
        {
        }

        return Response.ok().build();
    }
    
    
    
    
    
    
}
