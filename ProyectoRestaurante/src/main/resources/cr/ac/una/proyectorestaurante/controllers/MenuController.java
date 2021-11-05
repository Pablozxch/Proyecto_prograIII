/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class MenuController extends Controller implements Initializable {


    @FXML
    private JFXComboBox<String> cmbCategorias;
    @FXML
    private JFXButton btnBuscarCategoria;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private VBox lblCantidadTotal;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void click(ActionEvent event) {
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
