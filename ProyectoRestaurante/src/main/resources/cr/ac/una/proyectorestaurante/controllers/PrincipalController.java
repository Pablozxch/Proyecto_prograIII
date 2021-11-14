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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class PrincipalController extends Controller implements Initializable {

    @FXML
    private JFXButton btnSalones;
    @FXML
    private JFXButton btnProductos;
    @FXML
    private JFXButton btnOrdenes;
    @FXML
    private JFXButton btnFacturar;
    @FXML
    private JFXButton btnEmpleados;
    @FXML
    private JFXButton btnCerrarSesion;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private Label lblTitulo;
    @FXML
    private JFXButton btnCategorias;
    @FXML
    private Button btnRestaurante;
    @FXML
    private JFXButton btnReportes;
    @FXML
    private JFXButton btnCierreCajas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void click(ActionEvent event) {
    }

}
