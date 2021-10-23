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

import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class MainController extends Controller implements Initializable
{

    @FXML
    private AnchorPane root;
    @FXML
    private TextField TextF_DeporteBuscar;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private Label Lbl_NombreD;
    @FXML
    private ImageView img_Foto;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnAgregarRestaurante;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource()==btnAgregarRestaurante)
        {
            
        }
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
