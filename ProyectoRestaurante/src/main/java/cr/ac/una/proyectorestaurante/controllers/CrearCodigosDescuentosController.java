/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import cr.ac.una.proyectorestaurante.utils.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CrearCodigosDescuentosController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXTextField txtUrl;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void click(ActionEvent event) {
        if (event.getSource() == btnAceptar) {
            if (txtNombre.getText().isEmpty() || txtNombre.getText().isBlank()) {
                new Mensaje().show(Alert.AlertType.ERROR, "Incompleto", "Se debe ingresar un nombre para el codigo de descuento.");
            } else if (txtCantidad.getText().isEmpty() || txtCantidad.getText().isBlank()) {
                new Mensaje().show(Alert.AlertType.ERROR, "Incompleto", "Se debe ingresar una cantidad de veces a usar del codigo de descuento.");
            } else if (txtPorcentaje.getText().isEmpty() || txtPorcentaje.getText().isBlank()) {
                new Mensaje().show(Alert.AlertType.ERROR, "Incompleto", "Se debe ingresar el porcentaje del codigo de descuento.");
            }
        } else if(event.getSource() == btnVolver){
            
        }

    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
