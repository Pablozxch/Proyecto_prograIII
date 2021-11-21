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
    CategoriaDto catDelete = new CategoriaDto();

    @Override

    public void initialize(URL url , ResourceBundle rb)
    {
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

    public void load()
    {
        list.clear();
        listProductos.getItems().clear();
        productos = (List<ProductoDto>) ((Respuesta) productoServicec.getProductos()).getResultado("Productos");
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
            if(cmbCategorias.getValue() != null)
            {
                list.clear();
                listProductos.getItems().clear();
                categorias.forEach(t ->
                {
                    if(t.getNombre().toUpperCase().equals(cmbCategorias.getValue().toUpperCase()))
                    {
                        t.getProductos().forEach(p ->
                        {
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
                            x = true;
                        }
                    });
                    listProductos.getItems().add(create(t.getNombre() , x));
                    x = false;
                });
            }
            else
            {
                new Mensaje().show(Alert.AlertType.INFORMATION , "Categoria" , "No tiene ninguna categoria seleccionada");
            }

        }
        if(event.getSource() == btnCrearCategoria)
        {
            FlowController.getInstance().goViewInWindowModal("CrearCategoría" , getStage() , false);
            load();
        }
        if(event.getSource() == btnEliminar)
        {
            if(cmbCategorias.getValue() != null)
            {
                if(new Mensaje().showConfirmation("Eliminacion de Categorias" , getStage() , "¿Está seguro que desea eliminar la categoria " + cmbCategorias.getValue() + "?"))
                {
                    categorias.forEach(t ->
                    {
                        if(t.getNombre().toUpperCase().equals(cmbCategorias.getValue().toUpperCase()))
                        {
                            catDelete = t;
                        }

                    });
                    Respuesta res = categoriaService.eliminarCategoria(catDelete.getId());
                    if(res.getEstado())
                    {
                        new Mensaje().show(Alert.AlertType.INFORMATION , "Categoria Eliminada" , "La categoria " + catDelete.getNombre() + " ha sido eliminada correctamente.");
                    }
                }
            }
            else
            {
                new Mensaje().show(Alert.AlertType.INFORMATION , "Categoria" , "No hay ninguna categoria seleccionada");
            }
            list.clear();
            listProductos.getItems().clear();
            catDelete = null;
            load();
        }
        if(event.getSource() == btnGuardar)
        {
            if(cmbCategorias.getValue() != null)
            {
                List<ProductoDto> productosSave = new ArrayList<>();
                listProductos.getItems().forEach(t ->
                {
                    if(((CheckBox) t.getChildren().get(1)).isSelected())
                    {
                        productos.forEach(p ->
                        {
                            if(((Label) t.getChildren().get(0)).getText().equals(p.getNombre()))
                            {
                                productosSave.add(p);
                            }
                        });
                    }
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
                Respuesta res = categoriaService.guardarCategoria(cat);
                if(res.getEstado())
                {
                    new Mensaje().show(Alert.AlertType.INFORMATION , "Guardado Con exito" , "Se guardo con exito toda la información.");
                    load();
                }
            }
            else
            {
                new Mensaje().show(Alert.AlertType.INFORMATION , "Categoria" , "No tiene ninguna categoria seleccionada.");
            }
        }
    }

    @Override
    public void initialize()
    {
        load();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
