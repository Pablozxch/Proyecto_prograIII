/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class ReportesGeneralController extends Controller implements Initializable
{

    @FXML
    private JFXDatePicker dtpFechaInicial;
    @FXML
    private JFXDatePicker dtpFechaFinal;
    @FXML
    private JFXTextField txtIdEmpleado;
    @FXML
    private JFXToggleButton tglCierrreCajas;
    @FXML
    private JFXToggleButton tgllstadoyProductos;
    @FXML
    private JFXDatePicker dtpFechaCierreCaja;
    @FXML
    private JFXButton btnAceptar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void click(ActionEvent event)
    {
    }

}
