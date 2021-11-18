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
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

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
    private JFXButton btnGuardar;

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
    TextField n;
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
    MesaDto mesaDelete = new MesaDto();
    SalonDto salon = new SalonDto();
    @FXML
    private Pane paneEditar;
    @FXML
    private ImageView imgBB;

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
    }

    @Override
    public void initialize()
    {
        btnEliminar.setDisable(true);
        salon = (SalonDto) AppContext.getInstance().get("Salon");//colocar 
        load();
        rolDto = (RolDto) AppContext.getInstance().get("RolActual");
    }

    Circle changeCOlor(String color , Circle c)
    {
        if("D".equals(color))
        {
            c.setStroke(javafx.scene.paint.Color.GREEN);
        }
        else
        {
            c.setStroke(javafx.scene.paint.Color.RED);
        }
        return c;
    }

    void load()//con este metodo se carga la lista de iamgeviews para poder empezar a colocarlas en el grid
    {
        new Mensaje().showModal(Alert.AlertType.INFORMATION , "¡IMPORTANTE!" , getStage() , "ANTES DE MOVER LA MESA o ELIMINAR LA MESA, DARLE CLICK PARA LINKEAR EL TEXTO MANERA, SINO SE PRESENTAN PROBLEMAS");
        paneEditar.getChildren().clear();
        File x = new File("/cr/ac/una/proyectorestaurante/resources/fondomesas.jpeg");
        imgBB.setImage(new Image(x.getPath()));
        paneEditar.getChildren().add(imgBB);
        salon = (SalonDto) AppContext.getInstance().get("Salon");//colocar 
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
            c.setFill(new ImagePattern(img2));
            c=changeCOlor(t.getEstado() , c);
            TextField text = new TextField();
            text.setStyle("-fx-background-color: transparent ");
            text.setText(t.getNombre());
            text.setLayoutX(t.getPosX() - 32);
            text.setLayoutY(t.getPosY() + 30);
            text.setMaxWidth(80);
            text.setTextFormatter(Formato.getInstance().maxLengthFormat(10));
            text.setEditable(true);

            iMloads.add(new cr.ac.una.proyectorestaurante.controllers.IMload(c , t.getPosX() , t.getPosY() , t.getNombre() , t , text));
        });
        iMloads.forEach(j ->
        {

            paneEditar.getChildren().add(j.getCircle());
            paneEditar.getChildren().add(j.getTextField());
            j.getCircle().setOnMouseClicked((m) ->
            {
                n = j.getTextField();
                mesaDelete = j.getMesaDto();
                if(j.getCircle().getStroke()==javafx.scene.paint.Color.YELLOW)
                {
                    j.setCircle(changeCOlor(j.getMesaDto().getEstado() , j.getCircle()));
                }
                else
                {
                     j.getCircle().setStroke(javafx.scene.paint.Color.YELLOW);
                }
               
            });
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
        }
    };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent t)
        {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            ((Circle) (t.getSource())).setTranslateX(newTranslateX);
            ((Circle) (t.getSource())).setTranslateY(newTranslateY);
            long finalx = (long) (((Circle) (t.getSource())).getCenterX() + ((Circle) (t.getSource())).getTranslateX());
            long finaly = (long) (((Circle) (t.getSource())).getCenterY() + ((Circle) (t.getSource())).getTranslateY());
            TextField textField = n;
            textField.setLayoutX(finalx - 32);
            textField.setLayoutY(finaly + 30);
            iMloads.forEach(i ->
            {
                if(i.getPosx() == ((Circle) (t.getSource())).getCenterX() && i.getPosy() == ((Circle) (t.getSource())).getCenterY())
                {
                    i.getMesaDto().setPosX(finalx);
                    i.getMesaDto().setPosY(finaly);
                }
            });
        }
    };

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnEliminar)
        {
//            mesaDelete.getId();
//            mesaService.eliminarMesa(mesaDelete.getId());
//            load();
        }
        if(event.getSource() == btnAnadir)
        {
            MesaDto msa = new MesaDto();
            msa.setSalonDto(salon);
            msa.setPosX(200L);
            msa.setPosY(150L);
            msa.setNombre("Mesa");
            msa.setEstado("D");
            mesaDtos.add(msa);
            mesaService.guardarMesa(msa);
            load();

        }
        if(event.getSource() == btnGuardar)
        {
            iMloads.forEach(t ->
            {
                t.getMesaDto().setNombre(t.getTextField().getText());
                mesaService.guardarMesa(t.getMesaDto());
            });
            load();
        }
    }
}
