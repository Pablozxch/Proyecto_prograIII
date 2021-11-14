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

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CrearPedidoController extends Controller implements Initializable {


    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnMenu;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblCantidadTotal;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton btnSumar;
    @FXML
    private JFXButton btnRestar;
    @FXML
    private Label lblPrecio;
    @FXML
    private JFXButton btnAnadir;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnMenuRapido;
    @FXML
    private TableView tblpedido;
    @FXML
    private ImageView imgProducto;
    @FXML
    private JFXButton btnFacturar;
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

    @FXML
    private void ObtenerResultado(MouseEvent event) {
    }

}
