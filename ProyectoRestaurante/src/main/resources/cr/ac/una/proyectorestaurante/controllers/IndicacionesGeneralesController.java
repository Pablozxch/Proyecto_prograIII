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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class IndicacionesGeneralesController extends Controller implements Initializable {


    @FXML
    private JFXTextField txtImpVentas;
    @FXML
    private JFXTextField txtImpServicio;
    @FXML
    private JFXTextField txtDescuentoMax;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private JFXButton btnAceptar;
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
    
}
