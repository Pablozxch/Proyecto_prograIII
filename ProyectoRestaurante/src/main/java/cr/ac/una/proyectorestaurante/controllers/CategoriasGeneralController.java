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
import javafx.scene.control.*;
import javafx.scene.paint.*;

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
    private JFXListView<String> listProductos;

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

    @Override

    public void initialize(URL url , ResourceBundle rb)
    {
        /*
            Encargado de cargar todos los productos del restaurante como tal
         */
        ObservableList<String> list = FXCollections.observableArrayList();//con esto se llena la cosa

        productos.forEach(t ->
        {
            list.add(t.getNombre());
        });
        /*
              Encargado de cargar todos los productos del restaurante como tal
         */
        
        if (cmbCategorias.getSelectionModel().getSelectedItem() == null){
            listProductos.setDisable(true);
        }
        Respuesta res = categoriaService.getCategorias();
        productos = (List<ProductoDto>) ((Respuesta) productoServicec.getProductos()).getResultado("Productos");
        if(res.getEstado())
        {
            categorias = (List<CategoriaDto>) res.getResultado("Categorias");
        }
        categorias.forEach(t ->
        {
            cmbCategorias.getItems().add(t.getNombre());
        });

//        categorias.forEach(t ->
//        {
//            t.getProductos().forEach(p ->
//            {
//                list.add(p.getNombre());
//                System.out.println("El nombre es " + p.getNombre());
//            });
//        });
        listProductos.setItems(list);

    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnCrearCategoria)
        {

        }
        if(event.getSource() == btnEliminar)
        {

        }
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
