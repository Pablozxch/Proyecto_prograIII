/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.stream.*;
import javafx.application.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class DisenoSalonesController extends Controller implements Initializable
{

    int xx = 0;
    int yy = 0;
    int mx, my;
    int k, l;
    private GridPane gripMesa;
    @FXML
    private ImageView imvCajero;
    @FXML
    private Pane pane;
    double oldX;
    double oldY;

    double posx, posy = 0;

    public int f = 20;
    public Thread taskThread;
    /**
     *
     * Initializes the controller class.
     */
    MesaService mesaService = new MesaService();

    List<IMload> iMloads = new ArrayList<>();
    OrdenService orden = new OrdenService();
    List<OrdenDto> ordenes = new ArrayList<>();
    List<MesaDto> mesaDtos = new ArrayList<>();
    List<OrdenDto> list = new ArrayList<>();
    RolDto rolDto = new RolDto();
    MesaDto mesaclick = new MesaDto();
    @FXML
    private AnchorPane rt;
    @FXML
    private JFXButton btnEditar;



    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
    }

    void load()//con este metodo se carga la lista de iamgeviews para poder empezar a colocarlas en el grid
    {
        SalonDto salon = (SalonDto) AppContext.getInstance().get("Salon");//colocar 
        mesaDtos.clear();
        Respuesta res = mesaService.getMesas();
        if(res.getEstado())
        {
            mesaDtos = (List<MesaDto>) res.getResultado("Mesas");
        }
        mesaDtos.forEach(t
                  ->
        {
            Image img2 = new Image(new ByteArrayInputStream(salon.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
            ImageView im = new ImageView(img2);
            im.setFitHeight(50);
            im.setFitWidth(50);
            iMloads.add(new IMload(im , t.getPosX() , t.getPosY()));
        });
      
    }


    @Override
    public void initialize()
    {
        load();
        rolDto = (RolDto) AppContext.getInstance().get("RolActual");

    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnEditar)
        {
            if("Administrativos".equals(rolDto.getNombre()))
            {
                FlowController.getInstance().goViewInWindowModal("EditarSalones" , (Stage) btnEditar.getScene().getWindow() , Boolean.FALSE);
            }
            else
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado");
            }
        }

    }

    class IMload
    {

        private ImageView im;
        private long posx;
        private long posy;

        public IMload(ImageView im , long posx , long posy)
        {
            this.im = im;
            this.posx = posx;
            this.posy = posy;
        }

        public long getPosy()
        {
            return posy;
        }

        public void setPosy(long posy)
        {
            this.posy = posy;
        }

        public ImageView getIm()
        {
            return im;
        }

        public void setIm(ImageView im)
        {
            this.im = im;
        }

        public long getPosx()
        {
            return posx;
        }

        public void setPosx(long posx)
        {
            this.posx = posx;
        }
    }
}
