/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class SalonesController extends Controller implements Initializable
{

    @FXML
    private JFXButton btnCrear;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private Label lblNombreSal;
    @FXML
    private ImageView imgSal;
    /**
     * Initializes the controller class.
     */

    private MyListenerSal myListenerSal;
    SalonService salonService = new SalonService();
    SalonDto salonDto = new SalonDto();
    private static List<SalonDto> salones = new ArrayList<>();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        load();
    }

    public void setSalSelect(SalonDto sal)
    {
        lblNombreSal.setText(sal.getNombre());
        Image img2 = new Image(new ByteArrayInputStream(sal.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgSal.setImage(img2);
        AppContext.getInstance().set("Salon" , sal);
    }

    public void load()
    {
        Respuesta respuesta = salonService.getSalones();
        salones = (List<SalonDto>) respuesta.getResultado("Salones");
        if(salones.size() > 0)
        {
            setSalSelect(salones.get(0));
            myListenerSal = new MyListenerSal()
            {
                @Override
                public void onClickListener(SalonDto sal)
                {
                    setSalSelect(sal);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
        }
        int column = 0;
        int row = 1;
        String name1;
        String name2;
        try
        {
            for(int i = 0; i < salones.size(); i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/cr/ac/una/proyectorestaurante/views/Salon.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                name1 = salones.get(i).getNombre();
                if(i + 1 < salones.size())
                {
                    name2 = salones.get(i + 1).getNombre();
                    if(name1 == null ? name2 == null : name1.equals(name2))
                    {
                        break;
                    }
                }

                SalonController itemrest = fxmlLoader.getController();
                itemrest.setData(salones.get(i) , myListenerSal);
                if(column == 3)
                {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane , column++ , row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane , new Insets(10));
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnCrear)
        {

        }
        if(event.getSource() == btnEditar)
        {

        }
        if(event.getSource() == btnEliminar)
        {

        }
        if(event.getSource() == btnContinuar)
        {

        }

    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
