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
    @FXML
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

    void loadEvent()
    {
  
        iMloads.forEach(t ->
        {
            t.getIm().setOnDragDetected(new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent event)
                {
                    Dragboard db = t.getIm().startDragAndDrop(TransferMode.COPY_OR_MOVE);
                    ClipboardContent content = new ClipboardContent();
                    Image checker = t.getIm().getImage();
                    content.putImage(checker);
                    content.putString(String.valueOf(t.getPosx()) + "H" + String.valueOf(t.getPosy()));
                    db.setContent(content);

                    posx = t.getPosx();
                    posy = t.getPosy();
                    System.out.println("Drag detectado");
                    event.consume();

                }
            });

            t.getIm().setOnDragOver(new EventHandler<DragEvent>()
            {
                public void handle(DragEvent event)
                {
                    if(event.getDragboard().hasImage())
                    {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

                        System.out.println("Drag over detectado");
                    }

                    event.consume();
                }
            });
        });
        imvCajero.setOnDragEntered(new EventHandler<DragEvent>()
        {
            public void handle(DragEvent event)
            {

                event.acceptTransferModes(TransferMode.ANY);
                System.out.println("Drop detected");
                String numeros = event.getDragboard().getString();
                char[] s = numeros.toCharArray();
                String num1 = String.valueOf(s[0]);
                String num2 = String.valueOf(s[2]);

                Long nm1 = Long.valueOf(num1);
                Long nm2 = Long.valueOf(num2);
                event.consume();
                if(event.isConsumed())
                {
                    System.out.println("El numero 1 es " + nm1);
                    System.out.println("El numero 2 es " + nm2);
                    ordenes.forEach(y ->
                    {
                        System.out.println("POS X " + y.getMesaDto().getPosX());
                        System.out.println("POS Y" + y.getMesaDto().getPosY());
                    });
                    list = (List<OrdenDto>) ordenes.stream().filter(o -> Objects.equals(o.getMesaDto().getPosX() , nm1) && Objects.equals(o.getMesaDto().getPosY() , nm2)).collect(Collectors.toList());
                    System.out.println("AS " + list.get(0));
                    ordenes.clear();
                    AppContext.getInstance().set("Orden" , list.get(0));
                    FlowController.getInstance().goViewInWindowModal("Factura" , (Stage) btnEditar.getScene().getWindow() , Boolean.FALSE);
                }

            }
        });
    }

    void loadthings()
    {
        Respuesta res = orden.getOrdenes();
        if(res.getEstado())
        {
            ordenes = (List<OrdenDto>) res.getResultado("Ordenes");
        }
        gripMesa.addEventHandler(MouseEvent.MOUSE_CLICKED , t ->
        {
            for(int z = 0; z < 10; z++)
            {
                for(int v = 0; v < 10; v++)
                {
                    if(gripMesa.getCellBounds(z , v).contains(t.getX() , t.getY()))
                    {
                        k = z;
                        l = v;
                        mesaDtos.forEach(m ->
                        {
                            if(m.getPosX() == k && m.getPosY() == l)
                            {
                                mesaclick = null;
                                System.out.println("El valor del click fue " + m.toString());
                                mesaclick = m;
                            }
                        });

                    }
                }
            }
            if("O".equals(mesaclick.getEstado()))
            {
                System.out.println("La mesa tiene orden existente");
                ordenes.forEach(y ->
                {
                    System.out.println("POS X " + y.getMesaDto().getPosX());
                    System.out.println("POS Y" + y.getMesaDto().getPosY());
                });
                list = (List<OrdenDto>) ordenes.stream().filter(o -> Objects.equals(o.getMesaDto().getId() , mesaclick.getId())).collect(Collectors.toList());
                list.forEach(z ->
                {
                    System.out.println("POS X " + z.getMesaDto().getPosX());
                    System.out.println("POS Y" + z.getMesaDto().getPosY());
                });
                AppContext.getInstance().set("Orden" , list.get(0));
                FlowController.getInstance().goViewInWindowModal("CrearPedido" , getStage() , Boolean.FALSE);
                list.clear();
                mesaclick = null;
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
//                AppContext.getInstance().set("Orden" , odd);
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

    @Override
    public void initialize()
    {
        load();
        loadgrid();
        rolDto = (RolDto) AppContext.getInstance().get("RolActual");
        loadEvent();
        loadthings();

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
