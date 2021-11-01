/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class ProductosGeneralController extends Controller implements Initializable {


    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnEliminar;
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
    private Label lblPrecio;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
        
    }

    @FXML
    private void click(ActionEvent event) {
    }
    
}
