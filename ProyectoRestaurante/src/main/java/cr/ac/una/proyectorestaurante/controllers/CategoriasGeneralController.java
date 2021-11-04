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
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnGuardar;
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
    CategoriaDto cat = new CategoriaDto();

     @Override

    public void initialize(URL url , ResourceBundle rb)
    {
    }

    public HBox create(String name , boolean x)//workea
    {
        System.out.println("El nombre a crear es " + name + x);
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

    public void load()
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

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnBuscar)
        {
            list.clear();
            listProductos.getItems().clear();
            categorias.forEach(t ->
            {
                if(t.getNombre().toUpperCase().equals(cmbCategorias.getValue().toUpperCase()))
                {
                    t.getProductos().forEach(p ->
                    {
                        System.out.println(t + p.getNombre());
                        list.add(p.getNombre());
                    });
                }

            });

            productos.forEach(t ->
            {
                //se ecnarga de evaluar la lista de productos a la lista de productos de la caregoria como tal
                list.forEach(y ->
                {

                    if(y.toUpperCase().equals(t.getNombre().toUpperCase()))
                    {
                        System.out.println("El valor esta en ambas es  " + y);
                        x = true;
                    }
                });
                listProductos.getItems().add(create(t.getNombre() , x));
                x = false;
            });

        }
        if(event.getSource() == btnCrearCategoria)
        {
            FlowController.getInstance().goViewInWindowModal("CrearCategoría" , getStage() , false);
            load();
        }
        if(event.getSource() == btnEliminar)
        {
            load();
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
                    cat = t;
                    //una lista que creo antes de inclusive buscar entre categorias
                }
            });
            cat.getProductos().forEach(t ->
            {
                System.out.println("El producto es " + t.getNombre());
            });
            Respuesta res = categoriaService.guardarCategoria(cat);
            if(res.getEstado())
            {
                new Mensaje().show(Alert.AlertType.CONFIRMATION , "Guardado Con exito" , "Se guardo con exito toda la informacion");
                load();
            }

        }
    }

    @Override
    public void initialize()
    {
        load();
        productos = (List<ProductoDto>) ((Respuesta) productoServicec.getProductos()).getResultado("Productos");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
