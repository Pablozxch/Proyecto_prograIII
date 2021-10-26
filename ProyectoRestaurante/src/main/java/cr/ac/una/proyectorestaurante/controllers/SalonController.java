/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import cr.ac.una.proyectorestaurante.models.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class SalonController extends Controller implements Initializable
{

    @FXML
    private Label lblNombreSalon;
    @FXML
    private ImageView imgSalon;

    private SalonDto salonDto;
    private MyListenerSal myListener;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    public void setData(SalonDto salonDto , MyListenerSal myListener)
    {
        this.salonDto = salonDto;
        this.myListener = myListener;
        lblNombreSalon.setText(salonDto.getNombre());
        Image img2 = new Image(new ByteArrayInputStream(salonDto.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgSalon.setImage(img2);
    }

    @FXML
    private void click(MouseEvent event)
    {
        myListener.onClickListener(salonDto);
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
