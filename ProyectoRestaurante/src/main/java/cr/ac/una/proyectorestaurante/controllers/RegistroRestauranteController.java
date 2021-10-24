/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class RegistroRestauranteController extends Controller implements Initializable
{

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtDetalle;
    @FXML
    private ImageView imvImagen;
    @FXML
    private JFXButton btnCargarImagen;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextArea txtDreccion;

    /**
     * Initializes the controller class.
     */
    File x;
    RestauranteDto empleadoClienteDto = new RestauranteDto();
    RestauranteService service = new RestauranteService();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnCargarImagen)
        {
            FileChooser file = new FileChooser();
            file.getExtensionFilters().addAll(
                      new FileChooser.ExtensionFilter("img" , "*.jpg") ,
                      new FileChooser.ExtensionFilter("img" , "*.png") ,
                      new FileChooser.ExtensionFilter("img2" , "*.gif")
            );

            File file2 = file.showOpenDialog(null);
            if(file2 != null)
            {
                Image imagen1 = new Image(file2.toURI().toString());
                x = file2;
                imvImagen.setImage(imagen1);
            }
        }
        if(event.getSource() == btnCerrar)
        {
            getStage().close();

        }
        if(event.getSource() == btnContinuar)
        {
            String nombre, detalle, correo, direccion;
            if(!txtNombre.getText().isBlank())
            {
                nombre = txtNombre.getText();
                if(!txtCorreo.getText().isBlank())
                {
                    if(txtCorreo.getText().contains("@"))
                    {
                        correo = txtCorreo.getText();
                        if(!txtDetalle.getText().isBlank())
                        {
                            if(txtDetalle.getText().length() <= 50)
                            {
                                detalle = txtDetalle.getText();
                                if(!txtDreccion.getText().isBlank())
                                {
                                    if(txtDreccion.getText().length() <= 50)
                                    {
                                        direccion = txtDreccion.getText();

                                    }
                                    else
                                    {
                                        new Mensaje().show(Alert.AlertType.WARNING , "Error en los Datos ingresador" , "Direccion No Valido, excede caracteres");
                                    }
                                }
                                else
                                {
                                    new Mensaje().show(Alert.AlertType.WARNING , "Error en los Datos ingresador" , "Direccion No Ingresdo");
                                }
                            }
                            else
                            {
                                new Mensaje().show(Alert.AlertType.WARNING , "Error en los Datos ingresador" , "Detalle No Valido, excede caracteres");
                            }
                        }
                        else
                        {
                            new Mensaje().show(Alert.AlertType.WARNING , "Error en los Datos ingresador" , "Detalle No Ingresdo");
                        }
                    }
                    else
                    {
                        new Mensaje().show(Alert.AlertType.WARNING , "Error en los Datos ingresador" , "Correo No Valido");
                    }

                }
                else
                {
                    new Mensaje().show(Alert.AlertType.WARNING , "Error en los Datos ingresador" , "Correo No Digitado");
                }

            }
            else
            {
                new Mensaje().show(Alert.AlertType.WARNING , "Error en los Datos ingresador" , "Nombre No Nigitado");
            }
        }
    }

    @Override
    public void initialize()
    {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
