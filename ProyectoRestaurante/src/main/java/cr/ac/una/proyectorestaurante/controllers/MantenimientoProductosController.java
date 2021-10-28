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
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author Christophers
 */
public class MantenimientoProductosController extends Controller implements Initializable
{

    @FXML
    private JFXButton btnAgregarProducto;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private TextField txtBuscar;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private Label lblNombrePro;
    @FXML
    private ImageView imgPro;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private JFXButton btnEliminar;

    /**
     * Initializes the controller class.
     */
    private MyListenerItem myListener;
    ProductoService productoService = new ProductoService();
    ProductoDto productoDto = new ProductoDto();
    private static List<ProductoDto> productos = new ArrayList<>();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    public void setSalSelect(ProductoDto sal)
    {
        lblNombrePro.setText(sal.getNombre());
        Image img2 = new Image(new ByteArrayInputStream(sal.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgPro.setImage(img2);
        AppContext.getInstance().set("Producto" , sal);
    }

//    public void load()
//    {
//        Respuesta respuesta = productoService.getSalones();//da error debido a que no esta implementado
//        productos = (List<ProductoDto>) respuesta.getResultado("Salones");
//        if(productos.size() > 0)
//        {
//            setSalSelect(productos.get(0));
//            myListener = new MyListenerItem()
//            {
//                @Override
//                public void onClickListener(Object sal)
//                {
//                    setSalSelect((ProductoDto) sal);
//                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//            };
//        }
//        int column = 0;
//        int row = 1;
//        String name1;
//        String name2;
//        try
//        {
//            for(int i = 0; i < productos.size(); i++)
//            {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/cr/ac/una/proyectorestaurante/views/Item.fxml"));
//                AnchorPane anchorPane = fxmlLoader.load();
//                name1 = productos.get(i).getNombre();
//                if(i + 1 < productos.size())
//                {
//                    name2 = productos.get(i + 1).getNombre();
//                    if(name1 == null ? name2 == null : name1.equals(name2))
//                    {
//                        break;
//                    }
//                }
//
//                ItemController itemcontroller = fxmlLoader.getController();
//                itemcontroller.setData(productos.get(i) , myListener);
//                if(column == 3)
//                {
//                    column = 0;
//                    row++;
//                }
//                grid.add(anchorPane , column++ , row); //(child,column,row)
//                //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//
//                GridPane.setMargin(anchorPane , new Insets(10));
//            }
//        }
//        catch(IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnAgregarProducto)
        {

        }
        if(event.getSource() == btnEditar)
        {
            
        }
        if(event.getSource() == btnEliminar)
        {

        }
        if(event.getSource() == btnContinuar)
        {

        }
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
