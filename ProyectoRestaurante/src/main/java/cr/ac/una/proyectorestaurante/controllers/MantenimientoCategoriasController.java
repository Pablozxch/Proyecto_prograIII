/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableView;
/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class MantenimientoCategoriasController extends Controller implements Initializable {


    @FXML
    private TableView<?> tblCategorias;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXButton btnEliminar;
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
