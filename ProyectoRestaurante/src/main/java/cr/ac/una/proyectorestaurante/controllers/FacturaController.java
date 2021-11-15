/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.*;
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
    private JFXTextField txtImpuestos;
    @FXML
    private JFXButton btnPagar;

    /**
     * Initializes the controller class.
     */
    OrdenService ordenService = new OrdenService();
    RestauranteDto restauranteDto = new RestauranteDto();
    SalonDto salonDto = new SalonDto();
    EmpleadoDto emp = new EmpleadoDto();
    CierrecajasDto cierre = new CierrecajasDto();
    RolDto rol = new RolDto();
    CierreCajaService cajaService = new CierreCajaService();

    OrdenDto ordenDto = new OrdenDto();

    List<DetallexordenDto> productos = new ArrayList<>();
    DetallexordenService detallexordenService = new DetallexordenService();
    List<DetallexordenDto> detallexordenDtos = new ArrayList<>();

    CodigodescDto codigodescDto = new CodigodescDto();
    double impA = 0;
    long subtotal = 0;
    DecimalFormat currency = new DecimalFormat("₡");
    CierrecajasDto cierreCajas = new CierrecajasDto();
    @FXML
    private JFXButton btnBuscarCodDescuento;
    @FXML
    private JFXTextField txtPagaCon;
    @FXML
    private JFXButton btnEnviarCorreo;
    FacturaService facturaService = new FacturaService();
    long descuento = 0;
    boolean cancelado = false;
    @FXML
    private JFXToggleButton chkTarjetaEfectivo;

    @Override

    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void click(ActionEvent event) throws MessagingException
    {

        if(event.getSource() == btnEnviarCorreo)
        {
            if(cancelado == true)
            {
                RestauranteDto r = (RestauranteDto) AppContext.getInstance().get("Restaurante");
                Respuesta res2 = facturaService.lasto();
                FacturaDto ff = (FacturaDto) res2.getResultado("Factura");
                if(res2.getEstado())
                {
                    String nombre = txtNombre.getText();
                    String correo = txtCorreo.getText();
                    Respuesta email = facturaService.sendByEmail(r.getId() , nombre , correo , ff.getId());
                    if(email.getEstado())
                    {
                        new Mensaje().show(Alert.AlertType.INFORMATION , "Envio de Correo." , "El correo ha sido enviado con exito");
                    }
                }
                btnEnviarCorreo.setDisable(true);
            }
            else
            {
                new Mensaje().show(Alert.AlertType.INFORMATION , "Pago" , "El pago no ha sido realizado.");
            }

        }
        if(event.getSource() == btnPagar)
        {
            cancelado = true;

            RolDto rol = (RolDto) AppContext.getInstance().get("RolActual");
            if(!"Salonero".equals(rol.getNombre()) && cierreCajas == null)
            {
                CierreCajasController cr = (CierreCajasController) FlowController.getInstance().getController("CierreCajas");
                cr.createCierre();
                FlowController.getInstance().goViewInWindowModal("CierreCajas" , getStage() , Boolean.FALSE);

            }
            btnPagar.setDisable(true);
            validarResultado();
            new Mensaje().show(Alert.AlertType.ERROR , "Factura." , "Factura pagada exitosamente.");
        }
        if(event.getSource() == btnBuscarCodDescuento)
        {
            CodDescService codService = new CodDescService();
            btnBuscarCodDescuento.setDisable(true);
            Respuesta res = codService.buscarUrl(txtCodigoDescuento.getText());
            if(res.getEstado())
            {
                codigodescDto = (CodigodescDto) res.getResultado("coddesc");
                descuento = codigodescDto.getDesc();
                txtDescuento.setText(codigodescDto.getDesc().toString());
                validarResultado();

            }
            else
            {
                new Mensaje().show(Alert.AlertType.INFORMATION , "Codigo Descuento" , "El codigo de descuento no ha sido encontrado");
            }

        }
    }

    void validarResultado()
    {

        MesaService mesaService = new MesaService();
        CierrecajasDto cierre = (CierrecajasDto) AppContext.getInstance().get("CierreCajasActual");
        MesaDto mesaDto = ordenDto.getMesaDto();
        mesaDto.setEstado("D");
        mesaService.guardarMesa(mesaDto);//se actualiza el estado de la mesa para no tomar ordenes ya canceladas
        FacturaDto fac = new FacturaDto();
        fac.setCierrecajasDto(cierre);
        if(chkTarjetaEfectivo.isSelected())
        {
            fac.setEfetivoTarjeta("T");//CAMBIAR EL VALOR DEPENDIENDO DE LO OTRO
        }
        else
        {
            fac.setEfetivoTarjeta("E");//CAMBIAR EL VALOR DEPENDIENDO DE LO OTRO
        }

        double total = Double.valueOf(txtSubtotal.getText()) * (impA);
        long ttotal = Long.valueOf(String.valueOf((long) total + subtotal));
        long finalmont = Long.valueOf(String.valueOf(((long) total + subtotal) - codigodescDto.getDesc()));
        txtImpuestos.setText(String.valueOf(total));
        fac.setSubtotal(subtotal);
        fac.setTotal(ttotal);
        fac.setMontoFinal(finalmont);
        fac.setDescuento(descuento);
        ordenDto.setEstado("C");
        ordenService.guardarOrden(ordenDto);
        fac.setOrdenDto(ordenDto);
        if(codigodescDto != null)
        {
            fac.setCodigodescDto(codigodescDto);
            fac.setDescuento(codigodescDto.getDesc());
            txtTotal.setText(String.valueOf(finalmont));
        }
         facturaService.guardarFactura(fac);
    }

    void loadRes()
    {
        restauranteDto = (RestauranteDto) AppContext.getInstance().get("Restaurante");
        ordenDto = (OrdenDto) AppContext.getInstance().get("Orden");
        salonDto = ordenDto.getMesaDto().getSalonDto();
        impA = restauranteDto.getImpVen() / 100.0;
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
        precioUnidad.setCellValueFactory(cd
                  ->
        {
            String formattedCost = currency.format(cd.getValue().getProductoDto().getCosto());
            return new SimpleStringProperty(formattedCost);
        });
        precioUnidad.setResizable(false);

        TableColumn<DetallexordenDto , String> total = new TableColumn<>("Total");
        total.setPrefWidth(tblOrdenes.getPrefWidth() / 4);
        total.setCellValueFactory(cellData->
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
        List<DetallexordenDto> detf = dett.stream().filter(t -> Objects.equals(t.getOrdenId().getId() , ordenDto.getId())).collect(Collectors.toList());
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
        List<DetallexordenDto> detf = dett.stream().filter(t -> Objects.equals(t.getOrdenId().getId() , ordenDto.getId())).collect(Collectors.toList());

        detf.forEach(t ->
        {
            subtotal += t.getPrecio();
        });
        txtDescuento.setText(String.valueOf(descuento));
        txtSubtotal.setText(String.valueOf(subtotal));
        long total = (long) ((long) Long.valueOf(txtSubtotal.getText()) * (impA));
        txtTotal.setText(String.valueOf(total + subtotal));
        txtImpuestos.setText(String.valueOf(total));
    }

    @Override
    public void initialize()
    {

        cierreCajas = (CierrecajasDto) AppContext.getInstance().get("CierreCajasActual");
        btnEnviarCorreo.setDisable(false);
        btnPagar.setDisable(false);
        btnBuscarCodDescuento.setDisable(false);
        loadRes();
        load();
        loadItems();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
