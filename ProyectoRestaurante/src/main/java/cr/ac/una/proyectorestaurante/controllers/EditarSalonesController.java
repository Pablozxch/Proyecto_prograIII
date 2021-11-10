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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
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
    @FXML
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
    @FXML
    private AnchorPane rt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO

    }

    @Override
    public void initialize()
    {
        gripMesa.addEventHandler(MouseEvent.ANY , h ->
        {
            updatePosMouse(h);

        });
        load();
        loadgrid();
        rolDto = (RolDto) AppContext.getInstance().get("RolActual");
        loadEvent();
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

    void loadEvent()
    {

        iMloads.forEach(t ->
        {
            t.getIm().addEventHandler(MouseEvent.DRAG_DETECTED , new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {

                    Dragboard db = t.getIm().startDragAndDrop(TransferMode.COPY_OR_MOVE);
                    ClipboardContent content = new ClipboardContent();
                    Image checker = t.getIm().getImage();
                    content.putImage(checker);
                    content.putString(String.valueOf(t.getPosx()) + "H" + String.valueOf(t.getPosy()));
                    db.setContent(content);
                    n = t.getIm();
                    posx = t.getPosx();
                    posy = t.getPosy();
                    System.out.println("Drag detectado");

                    event.consume();

                }
            });

            t.getIm().addEventHandler(DragEvent.DRAG_OVER , new EventHandler<DragEvent>()
            {
                @Override
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
            t.getIm().setOnDragDone(new EventHandler<DragEvent>()
            {
                public void handle(DragEvent event)
                {
                    event.acceptTransferModes(TransferMode.ANY);
                    for(int i = 0; i < 10; i++)
                    {
                        for(int j = 0; j < 10; j++)
                        {
                            if(gripMesa.getCellBounds(i , j).contains(posMouseX , posMouseY))//buscar el click dentro del anchor pane de dentro dD))
                            {
                                GridPane.setConstraints(n , i , j);
                            }
                        }
                    }
                    System.out.println("Drop detected");
                    event.consume();
                }
            });

        });

    }

    void updatePosMouse(MouseEvent event)
    {
        taskThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {

                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable()
                {
                    @Override

                    public void run()
                    {
                        posMouseX = event.getSceneX();
                        posMouseY = event.getScreenY();
                    }

                });

            }
        });

        taskThread.start();
    }

    @FXML
    private void click(ActionEvent event
    )
    {
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
