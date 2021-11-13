/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.*;
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
import javafx.geometry.*;
import javax.imageio.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CrearRestauranteController extends Controller implements Initializable
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
    private JFXComboBox<String> cmbImpVenta;
    @FXML
    private JFXComboBox<String> cmbImpServicio;
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
        txtDreccion.setTextFormatter(Formato.getInstance().maxLengthFormat(50));
        txtDetalle.setTextFormatter(Formato.getInstance().letrasFormat(50));
    }

    public void load()
    {
        restauranteDto = (RestauranteDto) AppContext.getInstance().get("Restaurante");
        if(restauranteDto != null)
        {
            unbinRestaurante();
            bindRestaurante();
        }
        AppContext.getInstance().delete("Restaurante");
    }

    public void bindRestaurante()
    {
        txtNombre.setText(restauranteDto.getNombre());
        txtDetalle.setText(restauranteDto.getDetalle());
        Image img2 = new Image(new ByteArrayInputStream(restauranteDto.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imvImagen.setImage(img2);
        txtCorreo.setText(restauranteDto.getCorreo());
        txtDreccion.setText(restauranteDto.getDireccion());
        cmbImpServicio.valueProperty().set(restauranteDto.getImpServ().toString());
        cmbImpVenta.valueProperty().set(restauranteDto.getImpVen().toString());
    }

    public void unbinRestaurante()
    {
        txtNombre.clear();
        txtDetalle.clear();
        imvImagen.setImage(null);
        txtCorreo.clear();
        txtDreccion.clear();
        cmbImpServicio.valueProperty().set(null);
        cmbImpVenta.valueProperty().set(null);
    }

    public RestauranteDto retornarRest()
    {
        Respuesta res = restauranteService.getResid(restauranteDto.getId());
        RestauranteDto rr = new RestauranteDto();
        if(res.getEstado())
        {
            rr = (RestauranteDto) res.getResultado("Restaurante");
        }
        return rr;
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
            String nombre;
            String detalle;
            String correo;
            String direccion;
            if(!txtNombre.getText().isBlank())
            {
                nombre = txtNombre.getText();
                if(!txtDetalle.getText().isBlank())
                {
                    if(!txtDetalle.getText().isBlank())
                    {
                        detalle = txtDetalle.getText();
                        if(!txtDreccion.getText().isBlank())
                        {
                            if(!txtDreccion.getText().isBlank())
                            {
                                direccion = txtDreccion.getText();
                                if(!txtCorreo.getText().isBlank())
                                {
                                    if(txtCorreo.getText().contains("@"))
                                    {
                                        correo = txtCorreo.getText();
                                        if(imvImagen.getImage() != null || x != null)
                                        {
                                            if(cmbImpServicio.getValue() != null && cmbImpVenta.getValue() != null)
                                            {
                                                try
                                                {
                                                    restauranteDto.setNombre(nombre);
                                                    restauranteDto.setDetalle(detalle);
                                                    restauranteDto.setCorreo(correo);
                                                    restauranteDto.setDireccion(direccion);
                                                    restauranteDto.setImpServ(Long.valueOf(cmbImpServicio.getValue()));
                                                    restauranteDto.setImpVen(Long.valueOf(cmbImpVenta.getValue()));
                                                    if(x != null)
                                                    {
                                                        BufferedImage bufferimage;
                                                        bufferimage = ImageIO.read(x);
                                                        ByteArrayOutputStream output = new ByteArrayOutputStream();
                                                        ImageIO.write(bufferimage , "jpg" , output);
                                                        byte[] data = output.toByteArray();
                                                        restauranteDto.setFoto(data);
                                                    }
                                                    else
                                                    {
                                                        restauranteDto.setFoto(restauranteDto.getFoto());
                                                    }
                                                    Respuesta res = restauranteService.guardarRestaurante(restauranteDto);
                                                    if(res.getEstado())
                                                    {
                                                        new Mensaje().show(Alert.AlertType.INFORMATION , "Gurdar Restaurante" , "Guardado Correctamente");
                                                    }
                                                    else
                                                    {
                                                        new Mensaje().show(Alert.AlertType.ERROR , "Gurdar Restaurante" , "Error al guardar");
                                                    }

                                                }
                                                catch(IOException ex)
                                                {
                                                    Logger.getLogger(CrearRestauranteController.class.getName()).log(Level.SEVERE , null , ex);
                                                }
                                            }
                                            else
                                            {
                                                new Mensaje().show(Alert.AlertType.WARNING , "Error en los Datos ingresador" , "Problemas con los impuestos");
                                            }
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
        for(int i = 0; i <= 100; i++)
        {
            cmbImpServicio.getItems().add(String.valueOf(i));
            cmbImpVenta.getItems().add(String.valueOf(i));
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
