/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableView;
/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class FacturaController extends Controller implements Initializable {


    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private TableView<?> tblOrdenes;
    @FXML
    private JFXTextField txtCodigoDescuento;
    @FXML
    private JFXTextField txtSubtotal;
    @FXML
    private JFXTextField txtDescuento;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXTextField txtNombreRest;
    @FXML
    private JFXTextField txtCorreoRest;
    @FXML
    private JFXTextField txtDireccionRest;
    @FXML
    private JFXTextField txtImpuestos;
    @FXML
    private JFXButton btnBuscarCodDescuento;
    @FXML
    private JFXButton btnPagar;
    @FXML
    private JFXToggleButton chkTarjetaEfectivo;
    @FXML
    private JFXTextField txtMontoAPagar;
    @FXML
    private JFXTextField txtPagaCon;
    @FXML
    private JFXButton btnEnviarCorreo;
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
