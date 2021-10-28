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
import java.util.logging.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import java.awt.image.*;
import java.time.*;
import java.util.*;
import java.util.logging.*;
import javax.imageio.*;

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
    private JFXTextArea txtDetalle;
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
    @FXML
    private JFXTextField txtImpVenta;
    @FXML
    private JFXTextField txtImpServicio;

    /**
     * Initializes the controller class.
     */
    File x;
    RestauranteDto restauranteDto = new RestauranteDto();
    RestauranteService restauranteService = new RestauranteService();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO

    }

    public void load()
    {
        restauranteDto = (RestauranteDto) AppContext.getInstance().get("Restaurante");
        if(restauranteDto != null)
        {
            unbinRestaurante();
            bindRestaurante();
        }
    }

    public void bindRestaurante()
    {
        txtNombre.setText(restauranteDto.getNombre());
        txtDetalle.setText(restauranteDto.getDetalle());
        Image img2 = new Image(new ByteArrayInputStream(restauranteDto.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imvImagen.setImage(img2);
        txtCorreo.setText(restauranteDto.getCorreo());
        txtDreccion.setText(restauranteDto.getDireccion());
    }

    public void unbinRestaurante()
    {
        txtNombre.clear();
        txtDetalle.clear();
        imvImagen.setImage(null);
        txtCorreo.clear();
        txtDreccion.clear();
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
            Long impV, impS;
            if(!txtNombre.getText().isBlank())
            {
                nombre = txtNombre.getText();
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
                                if(!txtCorreo.getText().isBlank())
                                {
                                    if(txtCorreo.getText().contains("@"))
                                    {
                                        correo = txtCorreo.getText();
                                        if(imvImagen.getImage() != null || x != null)
                                        {
                                            restauranteDto.setNombre(nombre);
                                            restauranteDto.setDetalle(detalle);
                                            restauranteDto.setCorreo(correo);
                                            restauranteDto.setDireccion(direccion);
//                                            restauranteDto.setImpServ(impS);
//                                            restauranteDto.setImpVen(impS);
                                            BufferedImage bufferimage;
                                            bufferimage = ImageIO.read(x);
                                            ByteArrayOutputStream output = new ByteArrayOutputStream();
                                            ImageIO.write(bufferimage , "jpg" , output);
                                            byte[] data = output.toByteArray();
                                            restauranteDto.setFoto(data);
                                            restauranteService.guardarRestaurante(restauranteDto);
                                            System.out.println("Se procede a guardar correctamente");
                                        }
                                        else
                                        {
                                            new Mensaje().show(Alert.AlertType.WARNING , "Error en los Datos ingresador" , "Problemas con la imagen");
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
                new Mensaje().show(Alert.AlertType.WARNING , "Error en los Datos ingresador" , "Nombre No Nigitado");
            }
        }
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
