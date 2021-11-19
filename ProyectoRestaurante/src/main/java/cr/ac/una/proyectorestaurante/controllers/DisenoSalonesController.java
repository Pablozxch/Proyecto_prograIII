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
import java.text.*;
import java.time.format.*;
import java.util.*;
import java.util.List;
import java.util.logging.*;
import java.util.stream.*;
import javafx.event.*;
import javafx.fxml.*;
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
    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
              .ofPattern("dd/MM/yyyy");
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
    SalonDto salon = new SalonDto();
    @FXML
    private ImageView imgB;

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
    }

    void load()//con este metodo se carga la lista de iamgeviews para poder empezar a colocarlas en el grid
    {
        salon = (SalonDto) AppContext.getInstance().get("Salon");//colocar 
        mesaDtos.clear();
        ordenes.clear();
        iMloads.clear();
        Respuesta resorden = orden.getOrdenes();
        ordenes = (List<OrdenDto>) resorden.getResultado("Ordenes");
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
            if("D".equals(t.getEstado()))
            {
                c.setStroke(javafx.scene.paint.Color.GREEN);
            }
            else
            {
                c.setStroke(javafx.scene.paint.Color.RED);
            }

            TextField text = new TextField();
            text.setStyle("-fx-background-color: transparent ");
            text.setText(t.getNombre());
            text.setLayoutX(t.getPosX() - 32);
            text.setLayoutY(t.getPosY() + 30);
            text.setMaxWidth(80);
            text.setEditable(false);
            iMloads.add(new IMload(c , t.getPosX() , t.getPosY() , t.getNombre() , t , text));
        });
        iMloads.forEach(j ->
        {

            pane.getChildren().add(j.getCircle());
            pane.getChildren().add(j.getTextField());
            j.getCircle().addEventHandler(MouseEvent.MOUSE_CLICKED , c ->
            {
                if(c.getButton() == MouseButton.PRIMARY)
                {
                    mesaclick = j.getMesaDto();
                    if("O".equals(mesaclick.getEstado()))
                    {
                        list = (List<OrdenDto>) ordenes.stream().filter(o -> Objects.equals(o.getMesaDto().getId() , mesaclick.getId()) && "P".equals(o.getEstado())).collect(Collectors.toList());
                        AppContext.getInstance().set("Orden" , list.get(0));
                        FlowController.getInstance().goViewInWindowModal("CrearPedido" , getStage() , Boolean.FALSE);
                        mesaclick = null;
                    }
                    else
                    {
                        try
                        {
                            EmpleadoDto emp = (EmpleadoDto) AppContext.getInstance().get("EmpleadoActual");
                            OrdenDto odd = new OrdenDto();
                            odd.setEmpleadoDto(emp);
                            Date date = new Date();
                            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date date2 = formatter1.parse(formatter1.format(date));
                            odd.setFecha(date2);
                            odd.setMesaDto(mesaclick);
                            odd.setEstado("P");
                            orden.guardarOrden(odd);
                            Respuesta res2 = orden.lasto();
                            if(res2.getEstado())
                            {
                                odd = (OrdenDto) res2.getResultado("Orden");
                            }
                            mesaclick.setEstado("O");
                            AppContext.getInstance().set("Orden" , odd);
                            FlowController.getInstance().goViewInWindowModal("CrearPedido" , getStage() , Boolean.FALSE);
                        }
                        catch(ParseException ex)
                        {
                            Logger.getLogger(DisenoSalonesController.class.getName()).log(Level.SEVERE , null , ex);
                        }
                    }
                    mesaService.guardarMesa(mesaclick);
                    load();
                }
            });

        });

    }



    @Override
    public void initialize()
    {
        pane.getChildren().clear();
        File x = new File("/cr/ac/una/proyectorestaurante/resources/fondomesas.jpeg");
        imgB.setImage(new Image(x.getPath()));
        pane.getChildren().add(imgB);
        load();
        rolDto = (RolDto) AppContext.getInstance().get("RolActual");

    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnEditar)
        {
            FlowController.getInstance().goViewInWindowModal("EditarSalones" , (Stage) btnEditar.getScene().getWindow() , Boolean.FALSE);
            initialize();
//            if("Administrativos".equals(rolDto.getNombre()))
//            {
//                
//            }
//            else
//            {
//                new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado");
//            }
        }
    }
}
