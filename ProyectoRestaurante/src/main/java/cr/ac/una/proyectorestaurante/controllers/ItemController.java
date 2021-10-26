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
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;

/**
 * FXML Controller class
 *
 * @author jp015
 */
public class ItemController extends Controller implements Initializable
{

    private RestauranteDto restauranteDto;
    private SalonDto salonDto;
    private ProductoDto productoDto;
    private MyListenerItem myListener;
    @FXML
    private Label lblNombreItem;
    @FXML
    private ImageView imgItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    public void setData(Object object , MyListenerItem myListener)
    {
        if(object.getClass() == RestauranteDto.class)
        {
            System.out.println("Restaurantes");
            this.restauranteDto = ((RestauranteDto) object);
            lblNombreItem.setText(((RestauranteDto) object).getNombre());
            Image img2 = new Image(new ByteArrayInputStream(((RestauranteDto) object).getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
            imgItem.setImage(img2);
        }
        if(object.getClass() == SalonDto.class)
        {
            System.out.println("Salones");
            this.salonDto = ((SalonDto) object);
            lblNombreItem.setText(((SalonDto) object).getNombre());
            Image img2 = new Image(new ByteArrayInputStream(((SalonDto) object).getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
            imgItem.setImage(img2);
        }
        this.myListener = myListener;
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void click(MouseEvent event)
    {
        myListener.onClickListener(restauranteDto);
    }

}
