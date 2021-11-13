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
public class FacturaController extends Controller implements Initializable {

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
    EmpleadoDto emp = new EmpleadoDto();
    CierrecajasDto cierre = new CierrecajasDto();
    RolDto rol = new RolDto();
    CierreCajaService cajaService = new CierreCajaService();

    OrdenDto ordenDto = new OrdenDto();

    List<DetallexordenDto> productos = new ArrayList<>();
    DetallexordenService detallexordenService = new DetallexordenService();
    List<DetallexordenDto> detallexordenDtos = new ArrayList<>();

    double impA = 0;
    long subtotal = 0;
    DecimalFormat currency = new DecimalFormat("₡");

    @FXML
    private JFXButton btnBuscarCodDescuento;
    @FXML
    private JFXTextField txtMontoAPagar;
    @FXML
    private JFXTextField txtPagaCon;
    @FXML
    private JFXButton btnEnviarCorreo;
    FacturaService facturaService = new FacturaService();
    long descuento = 0;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void click(ActionEvent event) throws MessagingException {
        if (event.getSource() == btnEnviarCorreo) {
            RestauranteDto r = (RestauranteDto) AppContext.getInstance().get("Restaurante");
            Respuesta res2 = facturaService.lasto();
            FacturaDto ff = (FacturaDto) res2.getResultado("Factura");
            if (res2.getEstado()) {
                String nombre = txtNombre.getText();
                String correo = txtCorreo.getText();
                Respuesta email = facturaService.sendByEmail(r.getId(), nombre, correo, ff.getId());
                if (email.getEstado()) {
                    new Mensaje().show(Alert.AlertType.INFORMATION, "Envio de Correo", "Enviado con Exito");
                } else {
                    System.out.println("Problemas");
                }
            }
            btnEnviarCorreo.setDisable(true);
        }

        CierrecajasDto cierreCajas = (CierrecajasDto) AppContext.getInstance().get("CierreCajasActual");
        if (event.getSource() == btnPagar) {

            if (!"Salonero".equals(emp.getRolDto().getNombre()) && cierreCajas == null) {
                cierre.setEmpleadoDto(emp);
                cierre.setMontoInicial(0L);
                cierre.setMontoEfectivo(0L);
                cierre.setMontoFinal(0L);
                cierre.setMontoTarjeta(0L);
                cierre.setEstado("C");

                Respuesta res2 = cajaService.guardarCierrecajas(cierre);
                if (res2.getEstado()) {
                    Respuesta res3 = cajaService.lasto();
                    cierre = (CierrecajasDto) res3.getResultado("CierreCaja");
                    System.out.println("El cierre es " + cierre);
                    AppContext.getInstance().set("CierreCajasActual", cierre);
                } else {
                    new Mensaje().show(Alert.AlertType.ERROR, "Datos", "No guarda");
                }
            }

            MesaService mesaService = new MesaService();
            CierrecajasDto cierre = (CierrecajasDto) AppContext.getInstance().get("CierreCajasActual");
            MesaDto mesaDto = ordenDto.getMesaDto();
            mesaDto.setEstado("D");
            mesaService.guardarMesa(mesaDto);//se actualiza el estado de la mesa para no tomar ordenes ya canceladas
            FacturaDto fac = new FacturaDto();
            fac.setCierrecajasDto(cierre);
            fac.setEfetivoTarjeta("T");//CAMBIAR EL VALOR DEPENDIENDO DE LO OTRO
            double total = Double.valueOf(txtSubtotal.getText()) * (impA);
            long ttotal = Long.valueOf(String.valueOf((long) total + subtotal));
            long finalmont = Long.valueOf(String.valueOf(((long) total + subtotal) - descuento));
            fac.setSubtotal(subtotal);
            fac.setTotal(ttotal);
            fac.setMontoFinal(finalmont);
            fac.setDescuento(descuento);
            ordenDto.setEstado("C");
            fac.setOrdenDto(ordenDto);
            System.out.println("fac " + fac.toString());
            ordenService.guardarOrden(ordenDto);
            facturaService.guardarFactura(fac);
            btnPagar.setDisable(true);
        }
    }

    void loadRes() {
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

    void load() {
        tblOrdenes.getColumns().clear();
        TableColumn<DetallexordenDto, String> nombreCorto = new TableColumn<>("Nombre Corto");
        nombreCorto.setPrefWidth(tblOrdenes.getPrefWidth() / 4);
        nombreCorto.setCellValueFactory(cd -> cd.getValue().getProductoDto().nombrecorto);
        nombreCorto.setResizable(false);

//
        TableColumn<DetallexordenDto, String> cantidad = new TableColumn<>("Cantidad");
        cantidad.setPrefWidth(tblOrdenes.getPrefWidth() / 4);
        cantidad.setCellValueFactory(cd -> cd.getValue().cantidad);
        cantidad.setResizable(false);
//
        TableColumn<DetallexordenDto, String> precioUnidad = new TableColumn<>("Precio Unidad");
        precioUnidad.setPrefWidth(tblOrdenes.getPrefWidth() / 4);
        precioUnidad.setCellValueFactory(cd
                -> {
            String formattedCost = currency.format(cd.getValue().getProductoDto().getCosto());
            return new SimpleStringProperty(formattedCost);
        });
        precioUnidad.setResizable(false);

        TableColumn<DetallexordenDto, String> total = new TableColumn<>("Total");
        total.setPrefWidth(tblOrdenes.getPrefWidth() / 4);
        total.setCellValueFactory(cellData
                -> {
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

    void loadItems() {
        Respuesta res = detallexordenService.getDetalles();

        List<DetallexordenDto> dett = (List<DetallexordenDto>) res.getResultado("Detalles");
        List<DetallexordenDto> detf = dett.stream().filter(t -> t.getOrdenId().getId() == ordenDto.getId()).collect(Collectors.toList());
        if (detf == null) {
            List<DetallexordenDto> deta = new ArrayList<>();
            productos = (List<DetallexordenDto>) deta;
            ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
            tblOrdenes.setItems(ords);
        } else {
            productos = (List<DetallexordenDto>) detf;
            ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
            tblOrdenes.setItems(ords);
        }
        loadtotales();
    }

    void loadtotales() {
        Respuesta res = detallexordenService.getDetalles();

        List<DetallexordenDto> dett = (List<DetallexordenDto>) res.getResultado("Detalles");
        List<DetallexordenDto> detf = dett.stream().filter(t -> t.getOrdenId().getId() == ordenDto.getId()).collect(Collectors.toList());

        detf.forEach(t
                -> {
            subtotal += t.getPrecio();
        });
        System.out.println("subtotal " + subtotal);
        txtDescuento.setText(String.valueOf(descuento));
        txtSubtotal.setText(String.valueOf(subtotal));
        long total = (long) ((long) Long.valueOf(txtSubtotal.getText()) * (impA));
        System.out.println(total);
        txtTotal.setText(String.valueOf(total + subtotal));
    }

    @Override
    public void initialize() {
        btnEnviarCorreo.setDisable(false);
        btnPagar.setDisable(false);
        loadRes();
        load();
        loadItems();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
