/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
public class MenuController extends Controller implements Initializable
{

    @FXML
    private JFXComboBox<String> cmbCategorias;
    @FXML
    private JFXButton btnBuscarCategoria;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label lblCantidadTotal;
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

    private static List<ProductoDto> productosddelete = new ArrayList<>();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        Respuesta respuesta = productoService.getProductos();
        productos = (List<ProductoDto>) respuesta.getResultado("Productos");
    }

    public void setProSelect(ProductoDto sal)
    {
        lblNombrePro.setText(sal.getNombre());
        Image img2 = new Image(new ByteArrayInputStream(sal.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgPro.setImage(img2);
        lblCantidadTotal.setText("Cantidad : " + sal.getCantidad().toString());
        lblPrecio.setText("Precio Unidad: ₡" + sal.getCosto().toString());
        this.proorden = sal;

    }

    public ProductoDto retornarproducto()
    {
        return proorden;
    }

    public void loadItems(String name)
    {
        if(!"aux".equals(name) && !"cat".equals(name))
        {
            grid.getChildren().clear();
            productosddelete=productos;
            productosddelete = productos.stream().filter(t -> t.getNombre().toUpperCase().contains(name.toUpperCase())).collect(Collectors.toList());
        }
        else if("cat".equals(name))
        {
            //necesito pasar todos los productos de x categoria a la lista de prodcutos principal para poder jugar con eesto
            if(productosddelete != null)
            {
                productosddelete.clear();
            }
            categorias.forEach(t ->
            {
                if(t.getNombre().toUpperCase().equals(cmbCategorias.getValue().toUpperCase()))
                {
                    t.getProductos().forEach(p ->
                    {
                        productosddelete.add(p);
                    });
                }

            });

        }
        else
        {
            if(productosddelete != null)
            {
                productosddelete.clear();
            }
            Respuesta respuesta = productoService.getProductos();
            productos = (List<ProductoDto>) respuesta.getResultado("Productos");
            productosddelete= productos;
        }
        if(productosddelete != null)
        {
            if(productosddelete.size() > 0)
            {
                setProSelect(productosddelete.get(0));
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
                for(int i = 0; i < productosddelete.size(); i++)
                {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/cr/ac/una/proyectorestaurante/views/Item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    name1 = productosddelete.get(i).getNombre();
                    if(i + 1 < productosddelete.size())
                    {
                        name2 = productosddelete.get(i + 1).getNombre();
                        if(name1 == null ? name2 == null : name1.equals(name2))
                        {
                            break;
                        }
                    }

                    ItemController itemrest = fxmlLoader.getController();
                    itemrest.setData(productosddelete.get(i) , myListener);
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
        else if(event.getSource() == btnBuscarCategoria)
        {
            updatebyCategories();
        }
        else if(event.getSource() == btnContinuar)
        {
            getStage().close();
        }
    }

    public void updatebyCategories()
    {
        grid.getChildren().clear();
        loadItems("cat");
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

    public void loadCat()
    {
        Respuesta res = categoriaService.getCategorias();
        if(res.getEstado())
        {
            categorias.clear();
            categorias = (List<CategoriaDto>) res.getResultado("Categorias");
            cmbCategorias.getItems().clear();
            categorias.forEach(t ->
            {
                cmbCategorias.getItems().add(t.getNombre());
            });
        }

    }

    @Override
    public void initialize()
    {
        Respuesta respuesta = productoService.getProductos();
        productos = (List<ProductoDto>) respuesta.getResultado("Productos");
        loadItems("aux");
        loadCat();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
