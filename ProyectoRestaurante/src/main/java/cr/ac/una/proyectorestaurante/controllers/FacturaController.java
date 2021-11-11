/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.io.*;
import java.net.URL;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import java.util.stream.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class FacturaController extends Controller implements Initializable
{

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private TableView tblOrdenes;
    @FXML
    private JFXTextField txtCodigoDescuento;
    @FXML
    private JFXTextField txtSubtotal;
    @FXML
    private JFXTextField txtDescuento;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXTextField txtNombreRest;
    @FXML
    private JFXTextField txtCorreoRest;
    @FXML
    private JFXTextField txtDireccionRest;
    @FXML
    private JFXCheckBox chkEfectivo;
    @FXML
    private JFXCheckBox chkTarjeta;
    @FXML
    private JFXTextField txtImpuestos;
    @FXML
    private JFXButton btnPagar;

    /**
     * Initializes the controller class.
     */
    OrdenService ordenService = new OrdenService();
    RestauranteDto restauranteDto = new RestauranteDto();
    SalonDto salonDto = new SalonDto();
    List<OrdenDto> ordenes = new ArrayList<OrdenDto>();

    OrdenDto ordenDto = new OrdenDto();

    List<DetallexordenDto> productos = new ArrayList<>();
    DetallexordenService detallexordenService = new DetallexordenService();
    List<DetallexordenDto> detallexordenDtos = new ArrayList<>();

    double impA = 0;
    double subtotal = 0;
    DecimalFormat currency = new DecimalFormat("₡ 0.00");
    @FXML
    private JFXButton btnBuscarCodDescuento;
    @FXML
    private JFXTextField txtMontoAPagar;
    @FXML
    private JFXTextField txtPagaCon;
    @FXML
    private JFXButton btnEnviarCorreo;

    @Override

    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void click(ActionEvent event) throws MessagingException
    {
        RestauranteDto r = (RestauranteDto) AppContext.getInstance().get("Restaurante");
        if(event.getSource() == btnEnviarCorreo)
        {
      
            sendEmaill();
            
        }
    }

    void loadRes()
    {
        restauranteDto = (RestauranteDto) AppContext.getInstance().get("Restaurante");
        ordenDto = (OrdenDto) AppContext.getInstance().get("Orden");
        salonDto = ordenDto.getMesaDto().getSalonDto();
        System.out.println(restauranteDto.getImpVen());
        impA = restauranteDto.getImpVen() / 100.0;
        System.out.println(impA);
        txtNombreRest.setText(restauranteDto.getNombre());
        txtCorreoRest.setText(restauranteDto.getCorreo());
        txtDireccionRest.setText(restauranteDto.getDireccion());
    }

    void load()
    {
        tblOrdenes.getColumns().clear();
        TableColumn<DetallexordenDto , String> nombreCorto = new TableColumn<>("Nombre Corto");
        nombreCorto.setPrefWidth(tblOrdenes.getPrefWidth() / 4);
        nombreCorto.setCellValueFactory(cd -> cd.getValue().getProductoDto().nombrecorto);
        nombreCorto.setResizable(false);

//
        TableColumn<DetallexordenDto , String> cantidad = new TableColumn<>("Cantidad");
        cantidad.setPrefWidth(tblOrdenes.getPrefWidth() / 4);
        cantidad.setCellValueFactory(cd -> cd.getValue().cantidad);
        cantidad.setResizable(false);
//
        TableColumn<DetallexordenDto , String> precioUnidad = new TableColumn<>("Precio Unidad");
        precioUnidad.setPrefWidth(tblOrdenes.getPrefWidth() / 4);
        precioUnidad.setCellValueFactory(cd ->
        {
            String formattedCost = currency.format(cd.getValue().getProductoDto().getCosto());
            return new SimpleStringProperty(formattedCost);
        });
        precioUnidad.setResizable(false);

        TableColumn<DetallexordenDto , String> total = new TableColumn<>("Total");
        total.setPrefWidth(tblOrdenes.getPrefWidth() / 4);
        total.setCellValueFactory(cellData ->
        {
            double totalF = (double) (cellData.getValue().getPrecio() * impA) + cellData.getValue().getPrecio();
            String formattedCost = currency.format(totalF);
            return new SimpleStringProperty(formattedCost);
        });
        total.setResizable(false);

        tblOrdenes.getColumns().add(nombreCorto);
        tblOrdenes.getColumns().add(cantidad);
        tblOrdenes.getColumns().add(precioUnidad);
        tblOrdenes.getColumns().add(total);

        tblOrdenes.refresh();
    }

    void loadItems()
    {
        Respuesta res = detallexordenService.getDetalles();

        List<DetallexordenDto> dett = (List<DetallexordenDto>) res.getResultado("Detalles");
        List<DetallexordenDto> detf = dett.stream().filter(t -> t.getOrdenId().getId() == ordenDto.getId()).collect(Collectors.toList());
        if(detf == null)
        {
            List<DetallexordenDto> deta = new ArrayList<>();
            productos = (List<DetallexordenDto>) deta;
            ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
            tblOrdenes.setItems(ords);
        }
        else
        {
            productos = (List<DetallexordenDto>) detf;
            ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
            tblOrdenes.setItems(ords);
        }
        loadtotales();
    }

    void loadtotales()
    {
        Respuesta res = detallexordenService.getDetalles();

        List<DetallexordenDto> dett = (List<DetallexordenDto>) res.getResultado("Detalles");
        List<DetallexordenDto> detf = dett.stream().filter(t -> t.getOrdenId().getId() == ordenDto.getId()).collect(Collectors.toList());

        detf.forEach(t ->
        {
            subtotal += t.getPrecio();
        });
        txtSubtotal.setText(String.valueOf(subtotal));
        double total = Double.valueOf(txtSubtotal.getText()) * (impA);
        txtTotal.setText(String.valueOf(total + subtotal));
    }

    @Override
    public void initialize()
    {
        loadRes();
        load();
        loadItems();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void sendEmaill() throws MessagingException
    {
        try
        {
            Properties prop = new Properties();
            prop.put("mail.smtp.auth" , true);
            prop.put("mail.smtp.starttls.enable" , "true");
            prop.put("mail.smtp.host" , "smtp.mailtrap.io");
            prop.put("mail.smtp.port" , "25");
            prop.put("mail.smtp.ssl.trust" , "smtp.mailtrap.io");
            Session session = Session.getInstance(prop , new Authenticator()
            {
                @Override
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication("proyectorestaurante3@gmail.com" , "McDonald");
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pigua8100@gmail.com"));
            message.setRecipients(
                      Message.RecipientType.TO , InternetAddress.parse("to@gmail.com"));
            message.setSubject("Mail Subject");

            String msg = "This is my first email using JavaMailer";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg , "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        }
        catch(AddressException ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
        }
    }
}
