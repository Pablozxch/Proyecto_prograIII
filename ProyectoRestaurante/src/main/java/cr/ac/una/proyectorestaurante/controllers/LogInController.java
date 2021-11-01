/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.proyectorestaurante.utils.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class LogInController extends Controller implements Initializable {


    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXTextField txtContrasena;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private JFXButton btnCrear;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void click(ActionEvent event) {
        if (event.getSource() == btnCrear){
            FlowController.getInstance().goViewInWindowModal("CrearEmpleado", (Stage) btnCrear.getScene().getWindow(), false);
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
