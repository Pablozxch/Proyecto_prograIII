/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import cr.ac.una.proyectorestaurante.*;
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
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    @FXML
    private ImageView imgB;

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
    }

    void load()//con este metodo se carga la lista de iamgeviews para poder empezar a colocarlas en el grid
    {
        SalonDto salon = (SalonDto) AppContext.getInstance().get("Salon");//colocar 
        mesaDtos.clear();
        iMloads.clear();
        Respuesta res = mesaService.getMesas();
        if(res.getEstado())
        {
            mesaDtos = (List<MesaDto>) res.getResultado("Mesas");
        }
        mesaDtos.forEach(t ->
        {
            Image img2 = new Image(new ByteArrayInputStream(salon.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered image
            Circle c = new Circle(30);
            c.setCenterX(t.getPosX());
            c.setCenterY(t.getPosY());
            c.setStrokeWidth(3);
            c.setStrokeMiterLimit(10);
            c.setStrokeType(StrokeType.CENTERED);
            c.setStroke(javafx.scene.paint.Color.BLACK);
            c.setFill(new ImagePattern(img2));
            c.setEffect(new DropShadow(0 , 0 , 0 , javafx.scene.paint.Color.BLACK));
            iMloads.add(new IMload(c , t.getPosX() , t.getPosY() , t.getNombre() , t));
        });
        iMloads.forEach(j ->
        {
            pane.getChildren().add(j.getCircle());
            j.getCircle().setOnMousePressed(circleOnMousePressedEventHandler);
            j.getCircle().setOnMouseDragged(circleOnMouseDraggedEventHandler);
        });

    }
    EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent t)
        {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Circle) (t.getSource())).getTranslateX();
            orgTranslateY = ((Circle) (t.getSource())).getTranslateY();
            /*
            tratar de centrar al click
            
            
             */
            System.out.println("X: " + t.getSceneX());
            System.out.println("Y: " + t.getSceneY());
        }
    };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent t)
        {
            System.out.println("X dragged: " + t.getSceneX());
            System.out.println("Y dragged" + t.getSceneY());
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            if(t.getSceneX() > 345 && t.getSceneX() < 1245 && t.getSceneY() > 245 && t.getSceneY() < 774)
            {
                ((Circle) (t.getSource())).setTranslateX(newTranslateX);
                ((Circle) (t.getSource())).setTranslateY(newTranslateY);
                iMloads.forEach(i ->
                {

                    if(i.getPosx() == ((Circle) (t.getSource())).getCenterX() && i.getPosy() == ((Circle) (t.getSource())).getCenterY())
                    {
                        System.out.println("Old x " + i.getMesaDto().getPosX());
                        System.out.println("Old Y " + i.getMesaDto().getPosY());
//                        i.setPosx((long) newTranslateX);
//                        i.setPosy((long) newTranslateY);
                        i.mesaDto.setPosX((long) newTranslateX);
                        i.mesaDto.setPosY((long) newTranslateY);
                        System.out.println("New x " + newTranslateX);
                        System.out.println("New Y " + newTranslateY);
                    }
                });

            }

        }
    };

    void loadEvents()
    {

    }

    @Override
    public void initialize()
    {
        pane.getChildren().clear();
//        File x=new File("/cr/ac/una/proyectorestaurante/resources/fondomesas.jpeg");
//        imgB.setImage(new Image(x.getAbsolutePath()));
        load();
        rolDto = (RolDto) AppContext.getInstance().get("RolActual");

    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnEditar)
        {
            iMloads.forEach(t ->
            {
                MesaDto mesa = t.getMesaDto();
                System.out.println("MesaDto " + mesa.toString());
                Respuesta res = mesaService.guardarMesa(mesa);
                if(res.getEstado())
                {
                    System.out.println("done");
                }
                else
                {
                    System.out.println("f");
                }
            });

        }
//if("Administrativos".equals(rolDto.getNombre()))
//            {
//                FlowController.getInstance().goViewInWindowModal("EditarSalones" , (Stage) btnEditar.getScene().getWindow() , Boolean.FALSE);
//            }
//            else
//            {
//                new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado");
//            }
    }

    class IMload
    {

        private Circle circle;
        private long posx;
        private long posy;
        private String nombre;
        private MesaDto mesaDto;

        public IMload(Circle circle , long posx , long posy , String nombre , MesaDto mesaDto)
        {
            this.circle = circle;
            this.posx = posx;
            this.posy = posy;
            this.nombre = nombre;
            this.mesaDto = mesaDto;
        }

        public String getNombre()
        {
            return nombre;
        }

        public void setNombre(String nombre)
        {
            this.nombre = nombre;
        }

        public Circle getCircle()
        {
            return circle;
        }

        public void setCircle(Circle circle)
        {
            this.circle = circle;
        }

        public long getPosx()
        {
            return posx;
        }

        public void setPosx(long posx)
        {
            this.posx = posx;
        }

        public long getPosy()
        {
            return posy;
        }

        public void setPosy(long posy)
        {
            this.posy = posy;
        }

        public MesaDto getMesaDto()
        {
            return mesaDto;
        }

        public void setMesaDto(MesaDto mesaDto)
        {
            this.mesaDto = mesaDto;
        }

    }
}
