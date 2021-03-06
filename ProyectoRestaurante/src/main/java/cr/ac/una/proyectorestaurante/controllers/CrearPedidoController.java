/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.io.*;
import java.net.URL;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CrearPedidoController extends Controller implements Initializable
{

    @FXML
    private JFXButton btnMenu;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblCantidadTotal;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton btnSumar;
    @FXML
    private JFXButton btnRestar;
    @FXML
    private Label lblPrecio;
    @FXML
    private JFXButton btnAnadir;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private TableView tblpedido;
    @FXML
    private JFXButton btnMenuRapido;
    @FXML
    private JFXButton btnFacturar;

    /**
     * Initializes the controller class.
     */
    DetallexordenService detallexordenService = new DetallexordenService();
    ProductoService proservice = new ProductoService();
    ProductoDto proo = new ProductoDto();
    List<DetallexordenDto> detallexordenDtos = new ArrayList<>();
    OrdenService ordenService = new OrdenService();
    OrdenDto ordenDto = new OrdenDto();
    ProductoDto pro = new ProductoDto();
    List<DetallexordenDto> productos = new ArrayList<>();
    @FXML
    private ImageView imgProducto;
    int cantidad = 1;
    int cantidadtodal = 0;
    boolean finded = false;
    DecimalFormat currency = new DecimalFormat("??? 0.00");

    RolDto rolDto = new RolDto();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO

    }

    void load()
    {
        tblpedido.getColumns().clear();
        TableColumn<DetallexordenDto , String> nombreCorto = new TableColumn<>("Nombre Corto");
        nombreCorto.setPrefWidth(tblpedido.getPrefWidth() / 4);
        nombreCorto.setCellValueFactory(cd -> cd.getValue().getProductoDto().nombrecorto);
        nombreCorto.setResizable(false);

        TableColumn<DetallexordenDto , String> nombre = new TableColumn<>("Nombre");
        nombre.setPrefWidth(tblpedido.getPrefWidth() / 4);
        nombre.setCellValueFactory(cd -> cd.getValue().getProductoDto().nombre);
        nombre.setResizable(false);
//
        TableColumn<DetallexordenDto , String> cantidad = new TableColumn<>("Cantidad");
        cantidad.setPrefWidth(tblpedido.getPrefWidth() / 4);
        cantidad.setCellValueFactory(cd -> cd.getValue().cantidad);
        cantidad.setResizable(false);

        TableColumn<DetallexordenDto , String> precio = new TableColumn<>("Precio");
        precio.setPrefWidth(tblpedido.getPrefWidth() / 4);
        precio.setCellValueFactory(cd ->
        {

            double total = cd.getValue().getPrecio();
            String formattedCost = currency.format(total);
            return new SimpleStringProperty(formattedCost);
        });
        precio.setResizable(false);

        tblpedido.getColumns().add(nombreCorto);
        tblpedido.getColumns().add(nombre);
        tblpedido.getColumns().add(cantidad);
        tblpedido.getColumns().add(precio);
        tblpedido.refresh();

    }

    void loadItems()
    {
        ordenDto = (OrdenDto) AppContext.getInstance().get("Orden");
        Respuesta res = detallexordenService.getDetalles();

        List<DetallexordenDto> dett = (List<DetallexordenDto>) res.getResultado("Detalles");
        List<DetallexordenDto> detf = dett.stream().filter(t -> Objects.equals(t.getOrdenId().getId() , ordenDto.getId())).collect(Collectors.toList());
        if(detf == null)
        {
            List<DetallexordenDto> deta = new ArrayList<>();
            productos = (List<DetallexordenDto>) deta;
            ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
            tblpedido.setItems(ords);
        }
        else
        {
            productos = (List<DetallexordenDto>) detf;
            ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
            tblpedido.setItems(ords);
        }
        tblpedido.refresh();

    }

    @FXML
    private void click(ActionEvent event)
    {

        if(event.getSource() == btnEliminar)
        {
            finded = false;
            if(pro != null)
            {
                DetallexordenDto dettodelte = (DetallexordenDto) tblpedido.getSelectionModel().getSelectedItem();
                productos.remove(dettodelte);
                ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
                detallexordenDtos = productos;
                tblpedido.setItems(ords);
                tblpedido.refresh();
                detallexordenService.eliminarDetalle(dettodelte.getId());
            }
        }
        if(event.getSource() == btnMenu)
        {
            MenuController menu = (MenuController) FlowController.getInstance().getController("Menu");
            FlowController.getInstance().goViewInWindowModal("Menu" , (Stage) btnMenu.getScene().getWindow() , Boolean.FALSE);
            pro = menu.retornarproducto();
            System.out.println(pro.toString());
            llenar(pro);
        }
        if(event.getSource() == btnMenuRapido)
        {
            MenuRapidoController menuR = (MenuRapidoController) FlowController.getInstance().getController("MenuRapido");
            FlowController.getInstance().goViewInWindowModal("MenuRapido" , (Stage) btnMenu.getScene().getWindow() , Boolean.FALSE);
            pro = menuR.retornarproducto();
            if(pro != null)
            {
                llenar(pro);
            }
        }
        if(event.getSource() == btnSumar)
        {
            if(cantidad + 1 <= cantidadtodal)
            {
                txtCantidad.setText(String.valueOf(cantidad += 1));
                lblPrecio.setText("Precio: ???" + cantidad * pro.getCosto());
                int cantidares = pro.getCantidad().intValue() - 1;
                pro.setCantidad(Long.valueOf(cantidares));
                cantidadtodal = cantidares;
                lblCantidadTotal.setText("Cantidad en Sistema: " + (pro.getCantidad()));
            }
        }
        if(event.getSource() == btnRestar)
        {
            if(cantidad - 1 > 0)
            {
                txtCantidad.setText(String.valueOf(cantidad -= 1));
                lblPrecio.setText("Precio: ???" + cantidad * pro.getCosto());
                int cantidares = pro.getCantidad().intValue() + 1;
                cantidadtodal = cantidares;
                pro.setCantidad(Long.valueOf(cantidares));
                lblCantidadTotal.setText("Cantidad en Sistema: " + (pro.getCantidad()));
            }
        }
        if(event.getSource() == btnAnadir)
        {

            finded = false;
            if(pro != null)
            {
                productos.forEach(t ->
                {
                    if(Objects.equals(t.getProductoDto().getId() , pro.getId()))
                    {
                        finded = true;
                        t.setCantidad(t.getCantidad() + Long.valueOf(txtCantidad.getText()));
                        t.setPrecio(t.getCantidad() * pro.getCosto());
                        ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
                        detallexordenDtos = productos;
                        tblpedido.setItems(ords);
                        tblpedido.refresh();
                        proo = t.getProductoDto();
                    }
                });
                if(finded == false)
                {
                    DetallexordenDto det = new DetallexordenDto();
                    det.setProductoDto(pro);
                    det.setCantidad(Long.valueOf(txtCantidad.getText()));
                    det.setPrecio(det.getCantidad() * pro.getCosto());
                    det.setOrdenId(ordenDto);
                    productos.add(det);
                    ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
                    detallexordenDtos = productos;
                    tblpedido.setItems(ords);
                    tblpedido.refresh();
                    proo = pro;
                }

                detallexordenDtos.forEach(t ->
                {
                    if(t.getOrdenId() == null)
                    {
                        t.setOrdenId(ordenDto);
                    }
                    System.out.println(t.toString());
                    Respuesta res2 = detallexordenService.guardarDetalle(t);
                    if(res2.getEstado())
                    {
                        System.out.println("done");
                    }
                    else
                    {
                        System.out.println("rip");
                    }
                });

            }
            initialize();
        }
        if(event.getSource() == btnFacturar)
        {
            if("Cajeros".equals(rolDto.getNombre()) || "Administrativos".equals(rolDto.getNombre()))
            {
                AppContext.getInstance().set("Orden" , ordenDto);
                FlowController.getInstance().goViewInWindowModal("Factura" , getStage() , Boolean.FALSE);
            }
            else
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Usted no tiene los permisos necesarios para acceder a este apartado.");
            }
        }

    }

    void llenar(ProductoDto pro)
    {
        lblNombre.setText(pro.getNombre());
        lblCantidadTotal.setText("Cantidad en Sistema: " + pro.getCantidad().toString());
        txtCantidad.setText(String.valueOf(cantidad));
        Image img2 = new Image(new ByteArrayInputStream(pro.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgProducto.setImage(img2);
        lblPrecio.setText("Precio: ???" + pro.getCosto());
        cantidadtodal = pro.getCantidad().intValue();
        System.out.println(pro.toString());

    }

    void llenarTb(DetallexordenDto det)
    {
        lblNombre.setText(pro.getNombre());
        lblCantidadTotal.setText("Cantidad en Sistema: " + pro.getCantidad().toString());
        cantidad = det.getCantidad().intValue();
        txtCantidad.setText(String.valueOf(det.getCantidad()));
        Image img2 = new Image(new ByteArrayInputStream(pro.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgProducto.setImage(img2);
        lblPrecio.setText("Precio: ???" + pro.getCosto() * det.getCantidad());
        cantidadtodal = pro.getCantidad().intValue();

    }

    void clear()
    {
        lblNombre.setText("Nombre Producto");
        cantidad = 0;
        lblCantidadTotal.setText("Cantidad en Sistema: " + 00000);
        txtCantidad.setText("0");
        imgProducto.setImage(null);
        lblPrecio.setText("Precio: ???" + 00000);
        pro = null;
    }

    @Override
    public void initialize()
    {
        rolDto = (RolDto) AppContext.getInstance().get("RolActual");
        load();
        loadItems();
        clear();
        
    }

    @FXML
    private void ObtenerResultado(MouseEvent event)
    {
        if(event.isPrimaryButtonDown() && event.getClickCount() == 2)
        {
            DetallexordenDto det = (DetallexordenDto) tblpedido.getSelectionModel().getSelectedItem();
            this.pro = det.getProductoDto();
            llenarTb(det);

        }
    }
}
