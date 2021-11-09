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
import java.util.stream.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtNombreCorto;
    @FXML
    private JFXButton btnBuscar;
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
    DecimalFormat currency = new DecimalFormat("₡ 0.00");

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        load();
    }

    void load()
    {
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
        List<DetallexordenDto> detf = dett.stream().filter(t -> t.getOrdenId().getId() == ordenDto.getId()).collect(Collectors.toList());
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

        /*
            @RECORDAR ESTAR CAMBIANDO Y ACTUALIZANDO  LOS VALORES DE VENDIDOS Y PRODUCTOS RESTANTES
         */
        if(event.getSource() == btnBuscar)
        {
            //implementar la parte de buscar ya sea por nombre o nombre corto
            //y guardar estos datos en una variable de tipo producto para poder agregarla a la lista de productos 
        }
        if(event.getSource() == btnEliminar)
        {
            finded = false;
            if(pro != null)
            {
                System.out.println("Delete");
                productos.forEach(t ->
                {
                    System.out.println(t.getProductoDto().getNombre());
                });
                DetallexordenDto dettodelte = (DetallexordenDto) tblpedido.getSelectionModel().getSelectedItem();
                productos.remove(dettodelte);
                productos.forEach(t ->
                {
                    System.out.println(t.getProductoDto().getNombre());
                });
                ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
                detallexordenDtos = productos;
                tblpedido.setItems(ords);
                tblpedido.refresh();
                /*
                @   ACTUALIZAR LOS DATOS RESPECTIVOS DEL PRODUCTO, YA QUE LA PERSONA NO QUIERE ESTE PRODUCTO
                @   EJEMPLOS 
                @   LA CANTIDAD DE ESTOS EN EL SISTEMA 
                @   LA CANTIDAD VENDIDA
                @   GUARDAR EL PRODUCTO CON LOS DATOS CORREGIDOS
                
                 */
                Respuesta res2 = detallexordenService.eliminarDetalle(dettodelte.getId());
                if(res2.getEstado())
                {
                    System.out.println("done");
                }
                else
                {
                    System.out.println("rip");
                }

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
                lblPrecio.setText("Precio: ₡" + cantidad * pro.getCosto());
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
                lblPrecio.setText("Precio: ₡" + cantidad * pro.getCosto());
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
        }

    }

    void llenar(ProductoDto pro)
    {
        lblNombre.setText(pro.getNombre());
        lblCantidadTotal.setText("Cantidad en Sistema: " + pro.getCantidad().toString());
        txtCantidad.setText(String.valueOf(cantidad));
        Image img2 = new Image(new ByteArrayInputStream(pro.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgProducto.setImage(img2);
        lblPrecio.setText("Precio: ₡" + pro.getCosto());
        cantidadtodal = pro.getCantidad().intValue();

    }

    void llenarTb(DetallexordenDto det)
    {
        lblNombre.setText(pro.getNombre());
        lblCantidadTotal.setText("Cantidad en Sistema: " + pro.getCantidad().toString());
        cantidad = det.getCantidad().intValue();
        txtCantidad.setText(String.valueOf(det.getCantidad()));
        Image img2 = new Image(new ByteArrayInputStream(pro.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgProducto.setImage(img2);
        lblPrecio.setText("Precio: ₡" + pro.getCosto() * det.getCantidad());
        cantidadtodal = pro.getCantidad().intValue();

    }

    void clear()
    {
        lblNombre.setText("Nombre Producto");
        cantidad = 0;
        lblCantidadTotal.setText("Cantidad en Sistema: " + 00000);
        txtCantidad.setText("0");
        imgProducto.setImage(null);
        lblPrecio.setText("Precio: ₡" + 00000);
        pro = null;
    }

    @Override
    public void initialize()
    {

        loadItems();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
