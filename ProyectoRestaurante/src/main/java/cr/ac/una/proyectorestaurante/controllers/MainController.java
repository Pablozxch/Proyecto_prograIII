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
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class MainController extends Controller implements Initializable
{

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnAgregarRestaurante;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label lblNombreRes;
    @FXML
    private ImageView imgRes;
    @FXML
    private TextField txtBuscar;
    /**
     * Initializes the controller class.
     */

    private MyListenerItem myListenerRess;
    RestauranteService restauranteService = new RestauranteService();
    RestauranteDto restauranteDto = new RestauranteDto();
    private List<RestauranteDto> restaurantes = new ArrayList<>();
    @FXML
    private BorderPane root2;

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        loadItems("aux");
    }

    public void loadItems(String name)
    {
        if(!"aux".equals(name))
        {
            restaurantes.clear();
            Respuesta respuesta = restauranteService.getRestaurantes();
            List<RestauranteDto> aux = (List<RestauranteDto>) respuesta.getResultado("Restaurantes");
            restaurantes = aux.stream().filter(t -> t.getNombre().toUpperCase().contains(name.toUpperCase())).collect(Collectors.toList());
        }
        else
        {
            restaurantes.clear();
            Respuesta respuesta = restauranteService.getRestaurantes();
            restaurantes = (List<RestauranteDto>) respuesta.getResultado("Restaurantes");
        }
        if(restaurantes != null)
        {
            if(restaurantes.size() > 0)
            {
                setResSelect(restaurantes.get(0));
                myListenerRess = new MyListenerItem()
                {
                    @Override
                    public void onClickListener(Object item)
                    {
                        setResSelect((RestauranteDto) item);
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                };
            }
            int column = 0;
            int row = 1;
            String name1;
            String name2;
            try
            {
                for(int i = 0; i < restaurantes.size(); i++)
                {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/cr/ac/una/proyectorestaurante/views/Item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    name1 = restaurantes.get(i).getNombre();
                    if(i + 1 < restaurantes.size())
                    {
                        name2 = restaurantes.get(i + 1).getNombre();
                        if(name1 == null ? name2 == null : name1.equals(name2))
                        {
                            break;
                        }
                    }

                    ItemController itemrest = fxmlLoader.getController();
                    itemrest.setData(restaurantes.get(i) , myListenerRess);
                    if(column == 6)
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
    }

    public void setResSelect(RestauranteDto res)
    {
        lblNombreRes.setText(res.getNombre());
        Image img2 = new Image(new ByteArrayInputStream(res.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgRes.setImage(img2);
        AppContext.getInstance().set("Restaurante" , res);
    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnAgregarRestaurante)
        {
            CrearRestauranteController registroRestauranteController = (CrearRestauranteController) FlowController.getInstance().getController("CrearRestaurante");
            FlowController.getInstance().goViewInWindowModal("CrearRestaurante" , (Stage) btnAgregarRestaurante.getScene().getWindow() , false);
            registroRestauranteController.unbinRestaurante();
            update();
        }
        if(event.getSource() == btnBuscar)
        {
            update();
        }
        if(event.getSource() == btnContinuar)
        {
            RestauranteDto resta = (RestauranteDto) AppContext.getInstance().get("Restaurante");
            System.out.println(resta.toString());
            FlowController.getInstance().goViewInStage("Principal" , (Stage) btnContinuar.getScene().getWindow());
        }
        if(event.getSource() == btnEditar)
        {
            CrearRestauranteController registroRestauranteController = (CrearRestauranteController) FlowController.getInstance().getController("CrearRestaurante");
            registroRestauranteController.load();
            FlowController.getInstance().goViewInWindowModal("CrearRestaurante" , (Stage) btnContinuar.getScene().getWindow() , false);
            registroRestauranteController.unbinRestaurante();
            update();

        }
        if(event.getSource() == btnEliminar)
        {
            if(new Mensaje().showConfirmation("Eliminar el restaurante" , getStage() , "¿Esta seguro que desea eliminar el restaurante?"))
            {
                RestauranteDto res = (RestauranteDto) AppContext.getInstance().get("Restaurante");
                Respuesta respuesta = restauranteService.eliminarRestaurante(res.getId());
                if(respuesta.getEstado())
                {
                    new Mensaje().show(Alert.AlertType.INFORMATION , "Eliminar el restaurante" , "Eliminado Correctamente");
                    update();
                }
            }

        }
    }

    public void update()
    {
        grid.getChildren().clear();
        if(!txtBuscar.getText().isBlank() || !txtBuscar.getText().isEmpty())
        {
            loadItems(txtBuscar.getText());
        }
        else
        {
            loadItems("aux");
        }
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
