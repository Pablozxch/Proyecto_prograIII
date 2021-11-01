/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.awt.image.*;
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
import javax.imageio.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CrearSalonController extends Controller implements Initializable
{

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXComboBox<String> cmbBarraoMesa;
    @FXML
    private JFXButton btnCargar;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private JFXButton btnAceptar;
    File x;
    SalonDto salonDto = new SalonDto();
    SalonService salonService = new SalonService();
    @FXML
    private ImageView imgSalon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        cmbBarraoMesa.getItems().setAll("Barra" , "Mesa");
    }

    void ubindSalon()
    {
        txtNombre.clear();
        imgSalon.setImage(null);
        cmbBarraoMesa.valueProperty().set(null);
    }

    void bindSalon()
    {
        txtNombre.setText(salonDto.getNombre());
        Image img2 = new Image(new ByteArrayInputStream(salonDto.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgSalon.setImage(img2);
        if("B".equals(salonDto.getBarraMesa()))
        {
            cmbBarraoMesa.setValue("Barra");
        }
        else
        {
            cmbBarraoMesa.setValue("Mesa");
        }
    }

    void load()
    {
        salonDto = (SalonDto) AppContext.getInstance().get("Salon");
        if(salonDto != null)
        {
            ubindSalon();
            bindSalon();
        }
        AppContext.getInstance().delete("Salon");
    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnCargar)
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
                imgSalon.setImage(imagen1);
            }
        }
        if(event.getSource() == btnAceptar)
        {
            String nombre;
            String barra;
            if(!txtNombre.getText().isBlank())
            {
                nombre = txtNombre.getText();
                if(cmbBarraoMesa.getValue() != null)
                {
                    if("Barra".equals(cmbBarraoMesa.getValue()))
                    {
                        barra = "B";
                    }
                    else
                    {
                        barra = "S";
                    }
                    try
                    {
                        salonDto.setNombre(nombre);
                        salonDto.setBarraMesa(barra);
                        if(x != null)
                        {
                            BufferedImage bufferimage;
                            bufferimage = ImageIO.read(x);
                            ByteArrayOutputStream output = new ByteArrayOutputStream();
                            ImageIO.write(bufferimage , "jpg" , output);
                            byte[] data = output.toByteArray();
                            salonDto.setFoto(data);
                        }
                        else
                        {
                            salonDto.setFoto(salonDto.getFoto());
                        }
                        salonDto.setRestauranteDto((RestauranteDto) AppContext.getInstance().get("Restaurante"));
                        Respuesta res = salonService.guardarSalon(salonDto);
                        if(res.getEstado())
                        {
                            new Mensaje().show(Alert.AlertType.INFORMATION , "Gurdar Salon" , "Guardado Correctamente");
                        }
                        else
                        {
                            new Mensaje().show(Alert.AlertType.ERROR , "Gurdar Salon" , "Error al guardar");
                        }

                    }
                    catch(IOException ex)
                    {
                        Logger.getLogger(CrearRestauranteController.class.getName()).log(Level.SEVERE , null , ex);
                    }
                }
                else
                {
                    new Mensaje().show(Alert.AlertType.ERROR , "Error en Datos" , "El dato de Barra o Mesa está en blancoF");
                }
            }
            else
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Error en Datos" , "El nombre está en blanco");
            }
        }

    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
