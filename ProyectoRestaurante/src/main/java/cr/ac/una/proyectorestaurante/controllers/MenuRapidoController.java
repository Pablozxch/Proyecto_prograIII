/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class MenuRapidoController extends Controller implements Initializable
{

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
    private Label lblPrecio;
    @FXML
    private Label lblCantidadTotal;
    @FXML
    private JFXButton btnContinuar;

    /**
     * Initializes the controller class.
     */
    private MyListenerItem myListener;
    ProductoService productoService = new ProductoService();
    CategoriaService categoriaService = new CategoriaService();
    List<CategoriaDto> categorias = new ArrayList<>();
    ProductoDto productoDto = new ProductoDto();
    ProductoDto proorden = new ProductoDto();
    private static List<ProductoDto> productos = new ArrayList<>();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    public void setProSelect(ProductoDto sal)
    {
        lblNombrePro.setText(sal.getNombre());
        Image img2 = new Image(new ByteArrayInputStream(sal.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgPro.setImage(img2);
        lblCantidadTotal.setText("Cantidad : " + sal.getCantidad().toString());
        lblPrecio.setText("Precio Unidad: ???" + sal.getCosto().toString());
        this.proorden = sal;

    }

    public ProductoDto retornarproducto()
    {
        return proorden;
    }

    public void loadItems(String name)
    {
        if(!"aux".equals(name))
        {
            if(productos != null)
            {
                productos.clear();
            }
            Respuesta respuesta = productoService.getProductos();
            List<ProductoDto> aux = (List<ProductoDto>) respuesta.getResultado("Productos");
            productos = aux.stream().filter(t -> t.getNombre().toUpperCase().contains(name.toUpperCase()) && "S".equals(t.getAccesoRapido())).collect(Collectors.toList());
        }
        else
        {
            if(productos != null)
            {
                productos.clear();
            }
            Respuesta respuesta = productoService.getProductos();
            List<ProductoDto> aux = (List<ProductoDto>) respuesta.getResultado("Productos");
            productos = aux.stream().filter(t -> "S".equals(t.getAccesoRapido())).collect(Collectors.toList());;
        }
        if(productos != null)
        {
            if(productos.size() > 0)
            {
                setProSelect(productos.get(0));
                myListener = new MyListenerItem()
                {
                    @Override
                    public void onClickListener(Object pro)
                    {
                        setProSelect((ProductoDto) pro);
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                };
            }
            int column = 0;
            int row = 1;
            String name1;
            String name2;
            try
            {
                for(int i = 0; i < productos.size(); i++)
                {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/cr/ac/una/proyectorestaurante/views/Item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    name1 = productos.get(i).getNombre();
                    if(i + 1 < productos.size())
                    {
                        name2 = productos.get(i + 1).getNombre();
                        if(name1 == null ? name2 == null : name1.equals(name2))
                        {
                            break;
                        }
                    }

                    ItemController itemrest = fxmlLoader.getController();
                    itemrest.setData(productos.get(i) , myListener);
                    if(column == 6)
                    {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorPane , column++ , row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane , new Insets(10));
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnBuscar)
        {
            update();
        }
        if(event.getSource() == btnContinuar)
        {
            getStage().close();
        }
    }

    public void update()
    {
        grid.getChildren().clear();
        if(!txtBuscar.getText().isBlank() || !txtBuscar.getText().isEmpty())
        {
            loadItems(txtBuscar.getText());
        }
        else
        {
            loadItems("aux");
        }
    }

    @Override
    public void initialize()
    {
        grid.getChildren().clear();
        if(productos != null)
        {
            productos.clear();
        }
        loadItems("aux");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
