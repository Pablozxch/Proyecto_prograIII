/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.*;
import cr.ac.una.proyectorestaurante.models.EmpleadoDto;
import cr.ac.una.proyectorestaurante.models.MesaDto;
import cr.ac.una.proyectorestaurante.models.OrdenDto;
import cr.ac.una.proyectorestaurante.models.RolDto;
import cr.ac.una.proyectorestaurante.models.SalonDto;
import cr.ac.una.proyectorestaurante.services.MesaService;
import cr.ac.una.proyectorestaurante.services.OrdenService;
import cr.ac.una.proyectorestaurante.utils.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class EditarSalonesController extends Controller implements Initializable
{

    @FXML
    private JFXButton btnAnadir;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private Pane pane;
    private GridPane gripMesa;

    MesaService mesaService = new MesaService();

    List<IMload> iMloads = new ArrayList<>();
    OrdenService orden = new OrdenService();
    List<OrdenDto> ordenes = new ArrayList<>();
    List<MesaDto> mesaDtos = new ArrayList<>();
    List<OrdenDto> list = new ArrayList<>();
    RolDto rolDto = new RolDto();
    MesaDto mesaclick = new MesaDto();
    double posx, posy = 0;
    int xx = 0;
    int yy = 0;
    int mx, my;
    int k, l;
    Node n;
    public int f = 20;
    public Thread taskThread;
    double posMouseX;
    double posMouseY;

    /**
     * Initializes the controller class.
     */
    Circle circle_Red, circle_Green, circle_Blue;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        System.out.println("X MAX" + pane.getBoundsInLocal().getMaxX());
        System.out.println("Y MAX" + pane.getBoundsInLocal().getMaxY());
        System.out.println("X MIN " + pane.getBoundsInLocal().getMinX());
        System.out.println("Y MIN" + pane.getBoundsInLocal().getMinY());
    }

    @Override
    public void initialize()
    {
        load();
        rolDto = (RolDto) AppContext.getInstance().get("RolActual");
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
            iMloads.add(new cr.ac.una.proyectorestaurante.controllers.IMload(c , t.getPosX() , t.getPosY() , t.getNombre() , t));
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
                        i.getMesaDto().setPosX((long) newTranslateX);
                        i.getMesaDto().setPosY((long) newTranslateY);
                        System.out.println("New x " + newTranslateX);
                        System.out.println("New Y " + newTranslateY);
                    }
                });

            }

        }
    };

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnEliminar)
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
    }
}
