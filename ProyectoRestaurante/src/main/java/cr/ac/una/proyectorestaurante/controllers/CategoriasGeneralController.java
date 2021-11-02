/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import io.github.palexdev.materialfx.controls.*;
import java.net.URL;
import java.util.*;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CategoriasGeneralController extends Controller implements Initializable
{

    @FXML
    private JFXComboBox<String> cmbCategorias;
    @FXML
    private JFXButton btnCrearCategoria;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXListView<HBox> listProductos;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    CategoriaDto categoriaDto = new CategoriaDto();
    CategoriaService categoriaService = new CategoriaService();
    ProductoService productoServicec = new ProductoService();
    List<ProductoDto> productos = new ArrayList<>();
    List<CategoriaDto> categorias = new ArrayList<>();
    ObservableList<String> list = FXCollections.observableArrayList();//con esto se llena la cosa
    boolean x = false;
    @FXML
    private JFXButton btnGuardar;

    @Override

    public void initialize(URL url , ResourceBundle rb)
    {
        llenarCategorias();
        categorias.forEach(t ->
        {
            t.getProductos().forEach(p ->
            {
                list.add(p.getNombre());
            });
        });

        //la de productos generales
        productos.forEach(t ->
        {
            //se ecnarga de evaluar la lista de productos a la lista de productos de la caregoria como tal
            list.forEach(y ->
            {
                if(t.getNombre().toUpperCase().equals(y.toUpperCase()))
                {
                    x = true;
                }
            });
            listProductos.getItems().add(create(t.getNombre() , x));
            x = false;
        });
        //esta es la de la categoria

        //   listProductos.getItems().addAll(create("Coca") , create("asd") , create("1234e") , create("asdcs"));
    }

    public HBox create(String name , boolean x)//workea
    {
        CheckBox ch = new CheckBox();
        ch.setSelected(x);
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(0 , 10 , 0 , 10));
        hBox.setPrefSize(200 , 30);
        Label label2 = new Label(name);
        label2.setPrefSize(200 , 30);
        label2.setStyle("-fx-text-fill: #000000");
        hBox.getChildren().addAll(label2 , ch);
        return hBox;
    }

    public void llenarCategorias()
    {
        Respuesta res = categoriaService.getCategorias();
        if(res.getEstado())
        {
            categorias = (List<CategoriaDto>) res.getResultado("Categorias");
        }
        categorias.forEach(t ->
        {
            cmbCategorias.getItems().add(t.getNombre());
        });
        cmbCategorias.setValue(categorias.get(0).getNombre());
        productos = (List<ProductoDto>) ((Respuesta) productoServicec.getProductos()).getResultado("Productos");
    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnCrearCategoria)
        {
            FlowController.getInstance().goViewInWindowModal("CrearCategoría" , getStage() , false);
        }
        if(event.getSource() == btnEliminar)
        {

        }
        if(event.getSource() == btnGuardar)
        {
            List<ProductoDto> productosSave = new ArrayList<>();
            listProductos.getItems().forEach(t ->
            {
                if(((CheckBox) t.getChildren().get(1)).isSelected())
                {
                    System.out.println("El valor seleccionado es " + ((Label) t.getChildren().get(0)).getText());
                    productos.forEach(p ->
                    {
                        if(((Label) t.getChildren().get(0)).getText().equals(p.getNombre()))
                        {
                            productosSave.add(p);
                        }
                    });
                }
            });
            productosSave.forEach(t ->
            {
                System.out.println("Los valores son" + t.toString());
            });
            categorias.forEach(t ->
            {
                if(t.getNombre().equals(cmbCategorias.getValue()))
                {
                    t.getProductos().clear();
                    t.setProductos(productosSave);
                    //una lista que creo antes de inclusive buscar entre categorias
                }
            });
        }
    }

    @Override
    public void initialize()
    {

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

}
