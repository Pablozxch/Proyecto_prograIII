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
import java.net.URL;
import java.text.*;
import java.util.*;
import java.util.stream.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;

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
    private JFXButton btnEnviar;
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
    private JFXButton btnGuardar;

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

    @Override

    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void click(ActionEvent event)
    {
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

}
