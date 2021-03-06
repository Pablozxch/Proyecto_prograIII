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
    private EmpleadoDto empleadoDto;
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
            this.restauranteDto = ((RestauranteDto) object);
            lblNombreItem.setText(((RestauranteDto) object).getNombre());
            Image img2 = new Image(new ByteArrayInputStream(((RestauranteDto) object).getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
            imgItem.setImage(img2);
        }
        if(object.getClass() == SalonDto.class)
        {
            this.salonDto = ((SalonDto) object);
            lblNombreItem.setText(((SalonDto) object).getNombre());
            Image img2 = new Image(new ByteArrayInputStream(((SalonDto) object).getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
            imgItem.setImage(img2);
        }
        if(object.getClass() == ProductoDto.class)
        {
            this.productoDto = ((ProductoDto) object);
            lblNombreItem.setText(((ProductoDto) object).getNombre());
            Image img2 = new Image(new ByteArrayInputStream(((ProductoDto) object).getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
            imgItem.setImage(img2);
        }
        if(object.getClass() == EmpleadoDto.class)
        {
            this.empleadoDto = ((EmpleadoDto) object);
            lblNombreItem.setText(((EmpleadoDto) object).getNombre());
            Image img2 = new Image(new ByteArrayInputStream(((EmpleadoDto) object).getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
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
        try
        {
            myListener.onClickListener(productoDto);
        }
        catch(Exception e)
        {

        }
        try
        {
            myListener.onClickListener(restauranteDto);
        }
        catch(Exception e)
        {

        }
        try
        {
            myListener.onClickListener(salonDto);
        }
        catch(Exception e)
        {

        }
        try
        {
            myListener.onClickListener(empleadoDto);
        }
        catch(Exception e)
        {

        }

    }

}
