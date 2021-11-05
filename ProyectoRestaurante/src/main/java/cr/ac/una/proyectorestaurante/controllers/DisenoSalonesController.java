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
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private GridPane gripMesa;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXCheckBox chkEditar;
    public int f = 20;
    public Thread taskThread;
    /**
     * Initializes the controller class.
     */
    MesaService mesaService = new MesaService();
    SalonDto salon = (SalonDto) AppContext.getInstance().get("Salon");//colocar 
    List<IMload> iMloads = new ArrayList<IMload>();
    List<MesaDto> mesaDtos = new ArrayList<>();
    @FXML
    private AnchorPane rt;

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {

        load();
        loadgrid();
        loadEvent();
    }

    void load()//con este metodo se carga la lista de iamgeviews para poder empezar a colocarlas en el grid
    {

        Respuesta res = mesaService.getMesas();
        if(res.getEstado())
        {
            mesaDtos = (List<MesaDto>) res.getResultado("Mesas");
        }
        mesaDtos.forEach(t ->
        {
            Image img2 = new Image(new ByteArrayInputStream(salon.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
            iMloads.add(new IMload(new ImageView(img2) , t.getPosX() , t.getPosY()));
        });
    }

    void loadgrid()//falta ver como se verifica la parte de las mesas//verificar si esto anda bien tomorrow
    {
        iMloads.forEach(t ->
        {
            gripMesa.getChildren().addAll(t.getIm());
            GridPane.setConstraints(t.getIm() , (int) t.getPosx() , (int) t.getPosy());
            GridPane.setHalignment(t.getIm() , HPos.CENTER);
            GridPane.setValignment(t.getIm() , VPos.BOTTOM);

        });
    }
    Node n = null;

    void loadEvent()
    {

        iMloads.forEach(t ->
        {
            t.getIm().setOnDragDetected(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    Dragboard db = t.getIm().startDragAndDrop(TransferMode.COPY_OR_MOVE);
                    n = t.getIm();
                    ClipboardContent content = new ClipboardContent();
                    Image checker = t.getIm().getImage();
                    content.putImage(checker);
                    db.setContent(content);

                    System.out.println("Drag detectado");
                    event.consume();
                }
            });
            t.getIm().setOnDragOver(new EventHandler<DragEvent>()
            {
                @Override
                public void handle(DragEvent ev)
                {
                    if(ev.getDragboard().hasImage())|   
                    {
                        ev.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    System.out.println(ev.getSceneX());
                    ev.consume();
                }

            });

            t.getIm().setOnDragDone(new EventHandler<DragEvent>()
            {
                @Override
                public void handle(DragEvent d2)
                {

                    System.out.println(d2.getSceneX());
                    System.out.println("Wenas");
                    for(int k = 0; k < 10; k++)
                    {
                        for(int z = 0; z < 10; z++)
                        {

                            if(gripMesa.getCellBounds(k , z).contains(d2.getSceneX() , d2.getSceneY()))//buscar el click dentro del anchor pane de dentro dD))
                            {
//                                xx = GridPane.getRowIndex(n3);
//                                yy = GridPane.getColumnIndex(n3);
                                GridPane.setConstraints(n , k , z);
                            }
                        }
                    }

//                    d2.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//                    Dragboard db = d2.getDragboard();
//                    System.out.println("Drop detected");
//                    d2.consume();
                }
            });

        });
    }

    @Override
    public void initialize()
    {
    }

    void prueba()
    {
        taskThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for(int i = 0; i < f; i++)
                {
                    f += 50;
                    try
                    {
                        Thread.sleep(1);
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
                            rt.addEventHandler(MouseEvent.ANY , (j) ->
                            {
                                mx = (int) j.getSceneX();
                                my = (int) j.getSceneY();
                                System.out.println(mx + my);
                            });
                        }

                    });

                }

            }
        });

        taskThread.start();
    }

    @FXML
    private void click(ActionEvent event
    )
    {

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
