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
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.image.*;
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

    /**
     * Initializes the controller class.
     */
    DetallexordenService detallexordenService = new DetallexordenService();
    List<DetallexordenDto> detallexordenDtos = new ArrayList<>();
    ProductoDto pro = new ProductoDto();
    List<DetallexordenDto> productos = new ArrayList<>();
    @FXML
    private ImageView imgProducto;
    int cantidad = 1;
    int cantidadtodal = 0;
    boolean finded = false;

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO

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
        precio.setCellValueFactory(cd -> cd.getValue().precio);
        precio.setResizable(false);

        tblpedido.getColumns().add(nombreCorto);
        tblpedido.getColumns().add(nombre);
        tblpedido.getColumns().add(cantidad);
        tblpedido.getColumns().add(precio);
        tblpedido.refresh();
        loadItems();
    }

    void loadItems()
    {
        OrdenDto orden = (OrdenDto) AppContext.getInstance().get("Orden");
        productos = (List<DetallexordenDto>) orden.getDetallexordenDtos();
        ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
        tblpedido.setItems(ords);
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
        if(event.getSource() == btnAnadir)
        {
            //se anade el prodcuto del de buscar a la lsita de productos
        }
        if(event.getSource() == btnEliminar)
        {
            //se elimina un producto de la lista de productos,
        }
        if(event.getSource() == btnMenu)
        {
            MenuController menu = (MenuController) FlowController.getInstance().getController("Menu");
            FlowController.getInstance().goViewInWindowModal("Menu" , (Stage) btnMenu.getScene().getWindow() , Boolean.FALSE);
            pro = menu.retornarproducto();
            System.out.println(pro.toString());
            llenar(pro);
        }
        if(event.getSource() == btnSumar)
        {
            if(cantidad + 1 <= cantidadtodal)
            {
                txtCantidad.setText(String.valueOf(cantidad += 1));
                lblPrecio.setText("Precio: ₡" + cantidad * pro.getCosto());
            }
        }
        if(event.getSource() == btnRestar)
        {

            if(cantidad - 1 > 0)
            {
                txtCantidad.setText(String.valueOf(cantidad -= 1));
                lblPrecio.setText("Precio: ₡" + cantidad * pro.getCosto());
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
                        t.setCantidad(t.getCantidad() + cantidad);
                        System.out.println(t.getPrecio());
                        t.setPrecio(t.getCantidad() * pro.getCosto());
                        System.out.println(t.getPrecio() + "asda" + t.getCantidad() * pro.getCosto());
                        ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
                        tblpedido.setItems(ords);
                        tblpedido.refresh();
                    }
                });
                if(finded == false)
                {
                    DetallexordenDto det = new DetallexordenDto();
                    det.setProductoDto(pro);
                    det.setCantidad(Long.valueOf(cantidad));
                    det.setPrecio(cantidad * pro.getCosto());
                    productos.add(det);
                    ObservableList<DetallexordenDto> ords = FXCollections.observableList(productos);
                    tblpedido.setItems(ords);
                    tblpedido.refresh();
                }
                clear();
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

    void clear()
    {
        lblNombre.setText("Nombre Producto");
        lblCantidadTotal.setText("Cantidad en Sistema: " + 00000);
        txtCantidad.setText("0");
        imgProducto.setImage(null);
        lblPrecio.setText("Precio: ₡" + 00000);
        pro = null;
    }

    @Override
    public void initialize()
    {
        load();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
