/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
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
public class CrearEmpleadoController extends Controller implements Initializable
{

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXComboBox<String> cmbRoles;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtContrasena;
    @FXML
    private JFXButton btnRegresar;
    @FXML
    private JFXButton btnContinuar;

    /**
     * Initializes the controller class.
     */
    File x;
    EmpleadoDto empleadoDto = new EmpleadoDto();
    EmpleadoService empleadoService = new EmpleadoService();
    RolService rolService = new RolService();
    @FXML
    private JFXTextField txtApellido;
    @FXML
    private ImageView imvEmpleado;
    @FXML
    private JFXButton btnCargarImagen;

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(50));
        txtApellido.setTextFormatter(Formato.getInstance().letrasFormat(50));
        txtUsuario.setTextFormatter(Formato.getInstance().maxLengthFormat(26));
        txtContrasena.setTextFormatter(Formato.getInstance().maxLengthFormat(26));

    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnRegresar)
        {
            getStage().close();
        }
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
                imvEmpleado.setImage(imagen1);
            }
        }
        if(event.getSource() == btnContinuar)
        {
            String nombre;
            String apellido;
            String password;
            String rol;
            if(!txtNombre.getText().isBlank())
            {
                nombre = txtNombre.getText();
                if(!txtApellido.getText().isBlank())
                {
                    apellido = txtApellido.getText();
                    if(!cmbRoles.getValue().isBlank())
                    {
                        rol = cmbRoles.getValue();
                    }
                    else
                    {
                        new Mensaje().show(Alert.AlertType.ERROR , "Falta de Datos" , "El campo de roles está en blanco");
                    }
                }
                else
                {
                    new Mensaje().show(Alert.AlertType.ERROR , "Falta de Datos" , "El campo de apellido está en blanco");
                }
            }
            else
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Falta de Datos" , "El campo de nombre está en blanco");
            }
        }
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void unbindProducto()
    {
//        txtNombre.clear();
//        txtApellido.clear();
//        imvImagen.setImage(null);//falta el espacio de la imagen ya lo coloco
//        txtUsuario.clear();
//        txtContrasena.clear();
//        cmbFavorito.valueProperty().set(null);
    }

    void load()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
