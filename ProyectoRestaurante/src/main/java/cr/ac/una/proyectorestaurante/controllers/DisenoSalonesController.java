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
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

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
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private GridPane gripMesa;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXCheckBox chkEditar;
    @FXML
    private ImageView imvCajero;
    @FXML
    private Pane pane;
    double oldX;
    double oldY;

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

    MesaDto mesaclick = new MesaDto();
    @FXML
    private AnchorPane rt;

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

    void loadgrid()//falta ver como se verifica la parte de las mesas//verificar si esto anda bien tomorrow
    {
        gripMesa.getChildren().clear();
        iMloads.forEach(t
                  ->
        {
            gripMesa.getChildren().add(t.getIm());
            GridPane.setConstraints(t.getIm() , (int) t.getPosx() , (int) t.getPosy());
            GridPane.setHalignment(t.getIm() , HPos.CENTER);
            GridPane.setValignment(t.getIm() , VPos.BOTTOM);

        });

    }

    @Override
    public void initialize()
    {
        load();
        loadgrid();

        // loadEvent();
        gripMesa.addEventHandler(MouseEvent.MOUSE_CLICKED , (t)
                  ->
        {
            for(int z = 0; z < 10; z++)
            {
                for(int v = 0; v < 10; v++)
                {
                    if(gripMesa.getCellBounds(z , v).contains(t.getX() , t.getY()))
                    {
                        k = z;
                        l = v;
                        mesaDtos.forEach(m
                                  ->
                        {
                            if(m.getPosX() == k && m.getPosY() == l)
                            {
                                System.out.println("El valor del click fue " + m.toString());
                                mesaclick = m;
                                Respuesta res = orden.getOrdenes();
                                if(res.getEstado())
                                {
                                    ordenes = (List<OrdenDto>) res.getResultado("Ordenes");
                                }
                            }
                        });

                    }
                }
            }
            if("O".equals(mesaclick.getEstado()))
            {
                System.out.println("La mesa tiene orden existente");
                list = (List<OrdenDto>) ordenes.stream().filter(o -> Objects.equals(o.getMesaDto().getId() , mesaclick.getId())).collect(Collectors.toList());
                System.out.println("xD " + list.get(0).toString());;
                ordenes.clear();
                AppContext.getInstance().set("Orden" , list.get(0));
                FlowController.getInstance().goViewInWindowModal("CrearPedido" , getStage() , Boolean.FALSE);
                list.clear();
            }
            else//se procede a craer una orden para la mesa 
            {
                System.out.println("La mesa no tiene orden");
                EmpleadoDto emp = (EmpleadoDto) AppContext.getInstance().get("EmpleadoActual");
                OrdenDto odd = new OrdenDto();
                odd.setEmpleadoDto(emp);
                odd.setFecha(new Date());
                odd.setMesaDto(mesaclick);
                odd.setEstado("P");
                orden.guardarOrden(odd);
                mesaclick.setEstado("O");
                ordenes.clear();
                AppContext.getInstance().set("Orden" , new OrdenDto());
                FlowController.getInstance().goViewInWindowModal("CrearPedido" , getStage() , Boolean.FALSE);
                list.clear();
                mesaDtos.forEach(g
                          ->
                {
                    if(Objects.equals(g.getId() , mesaclick.getId()))
                    {
                        g = mesaclick;
                    }
                });
            }
            mesaService.guardarMesa(mesaclick);
        });
    }

    @FXML
    private void click(ActionEvent event)
    {

    }

    private void dragOver(DragEvent event)
    {

        Dragboard db = event.getDragboard();
        event.acceptTransferModes(TransferMode.ANY);
        System.out.println("Dragging over");
        event.consume();
    }

    private void dragDropped(DragEvent event)
    {

        Dragboard board = event.getDragboard();
        event.acceptTransferModes(TransferMode.ANY);

        double dragX = event.getSceneX();
        double dragY = event.getSceneY();

//        double newPosX = dragX + oldX;
//        double newPosY = dragY + oldY;
//
//        pa.setLayoutX(newPosX);
//        pane.setLayoutY(newPosY);
//
//        de.setDropCompleted(true);
//        System.out.println("Drag Dropped");
//        de.consume();
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
