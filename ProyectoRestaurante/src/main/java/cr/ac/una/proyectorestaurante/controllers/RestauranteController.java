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
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;

/**
 * FXML Controller class
 *
 * @author jp015
 */
public class RestauranteController extends Controller implements Initializable
{

    @FXML
    private Label lblNombreRes;
    @FXML
    private ImageView imgRest;
    private RestauranteDto restauranteDto;
    private MyListenerRes myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    public void setData(RestauranteDto restauranteDto , MyListenerRes myListener)
    {
        this.restauranteDto = restauranteDto;
        this.myListener = myListener;
        lblNombreRes.setText(restauranteDto.getNombre());
        Image img2 = new Image(new ByteArrayInputStream(restauranteDto.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgRest.setImage(img2);
    }

    @FXML
    private void click(MouseEvent event)
    {
        myListener.onClickListener(restauranteDto);
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
