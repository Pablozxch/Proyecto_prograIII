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
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class SalonesGeneralController extends Controller implements Initializable
{

    @FXML
    private JFXButton btnAgregarSalon;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private TextField txtBuscar;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private Label lblNombreSalon;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private ImageView imgSal;
    /**
     * Initializes the controller class.
     */

    private MyListenerItem myListener;
    SalonService salonService = new SalonService();
    SalonDto salonDto = new SalonDto();
    private static List<SalonDto> salones = new ArrayList<>();
    RolDto rolDto = new RolDto();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        loadItems("aux");
    }

    public void setSalSelect(SalonDto sal)
    {
        lblNombreSalon.setText(sal.getNombre());
        Image img2 = new Image(new ByteArrayInputStream(sal.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgSal.setImage(img2);
        AppContext.getInstance().set("Salon" , sal);
    }

    public void loadItems(String name)
    {
        if(!"aux".equals(name))
        {
            if(salones != null)
            {
                salones.clear();
            }
            Respuesta respuesta = salonService.getSalones();
            List<SalonDto> aux = (List<SalonDto>) respuesta.getResultado("Salones");
            salones = aux.stream().filter(t -> t.getNombre().toUpperCase().contains(name.toUpperCase())).collect(Collectors.toList());
        }
        else
        {
            if(salones != null)
            {
                salones.clear();
            }
            Respuesta respuesta = salonService.getSalones();
            salones = (List<SalonDto>) respuesta.getResultado("Salones");
        }
        if(salones != null)
        {
            if(salones.size() > 0)
            {
                setSalSelect(salones.get(0));
                myListener = new MyListenerItem()
                {

                    @Override
                    public void onClickListener(Object item)
                    {
                        setSalSelect((SalonDto) item);
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
                    fxmlLoader.setLocation(getClass().getResource("/cr/ac/una/proyectorestaurante/views/Item.fxml"));
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

                    ItemController itemrest = fxmlLoader.getController();
                    itemrest.setData(salones.get(i) , myListener);
                    if(column == 4)
                    {
                        column = 0;
                        row += 2;
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

    private void update()
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

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnAgregarSalon)
        {
            if("Administrativos".equals(rolDto.getNombre()))
            {
                CrearSalonController crearSalonController = (CrearSalonController) FlowController.getInstance().getController("CrearSalon");
                FlowController.getInstance().goViewInWindowModal("CrearSalon" , (Stage) btnContinuar.getScene().getWindow() , Boolean.FALSE);
                crearSalonController.ubindSalon();
                update();
            }
            else
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado.");
            }

        }
        if(event.getSource() == btnEditar)
        {
            if("Administrativos".equals(rolDto.getNombre()))
            {
                CrearSalonController crearSalonController = (CrearSalonController) FlowController.getInstance().getController("CrearSalon");
                crearSalonController.load();
                FlowController.getInstance().goViewInWindowModal("CrearSalon" , (Stage) btnContinuar.getScene().getWindow() , Boolean.FALSE);
                crearSalonController.ubindSalon();
                update();
            }
            else
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado");
            }
        }
        if(event.getSource() == btnBuscar)
        {
            update();
        }
        if(event.getSource() == btnContinuar)
        {
            SalonDto sbm = (SalonDto) AppContext.getInstance().get("Salon");
            if("M".equals(sbm.getBarraMesa()))
            {
                System.out.println("ES mesa");
                FlowController.getInstance().goView("disenoSalones");//verificar si el salon que estoy accediendo es una barra o una mesa para mostrar cosas diferentes 
            }
            else
            {
                FlowController.getInstance().goView("OrdenesBarras");

            }

        }
        if(event.getSource() == btnEliminar)
        {
            if("Administrativos".equals(rolDto.getNombre()))
            {
                if(new Mensaje().showConfirmation("Eliminar el Salon" , getStage() , "¿Esta seguro que desea eliminar el Salon?"))
                {
                    SalonDto pro = (SalonDto) AppContext.getInstance().get("Salon");
                    Respuesta respuesta = salonService.eliminarSalon(pro.getId());
                    if(respuesta.getEstado())
                    {
                        new Mensaje().show(Alert.AlertType.INFORMATION , "Eliminar el Salon" , "Eliminado Correctamente.");
                        update();
                    }
                }
            }
            else
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado.");
            }
        }
    }

    @Override
    public void initialize()
    {
        loadItems("aux");
        rolDto = (RolDto) AppContext.getInstance().get("RolActual");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
