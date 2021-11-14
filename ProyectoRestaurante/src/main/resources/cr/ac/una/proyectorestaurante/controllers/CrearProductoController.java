/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CrearProductoController extends Controller implements Initializable {


    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextArea txtDetalle;
    @FXML
    private JFXTextField txtCosto;
    @FXML
    private ImageView imvImagen;
    @FXML
    private Button btnCargarImagen;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private JFXComboBox<String> cmbFavorito;
    @FXML
    private JFXButton btnVolver;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
