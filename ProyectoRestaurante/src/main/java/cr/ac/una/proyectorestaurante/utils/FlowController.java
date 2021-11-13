/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.utils;

import animatefx.animation.*;
import cr.ac.una.proyectorestaurante.App;
import cr.ac.una.proyectorestaurante.controllers.Controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.*;

public class FlowController
{

    private static FlowController INSTANCE = null;
    private static Stage mainStage;
    private static ResourceBundle idioma;
    private static HashMap<String , FXMLLoader> loaders = new HashMap<>();
    private static Controller controller;

    private FlowController()
    {
    }

    private static void createInstance()
    {
        if(INSTANCE == null)
        {
            synchronized(FlowController.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new FlowController();
                }
            }
        }
    }

    public static FlowController getInstance()
    {
        if(INSTANCE == null)
        {
            createInstance();
        }
        return INSTANCE;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    public void InitializeFlow(Stage stage , ResourceBundle idioma)
    {
        getInstance();
        this.mainStage = stage;
        this.idioma = idioma;
    }

    private FXMLLoader getLoader(String name)
    {
        FXMLLoader loader = loaders.get(name);
        if(loader == null)
        {
            synchronized(FlowController.class)
            {
                if(loader == null)
                {
                    try
                    {
                        loader = new FXMLLoader(App.class.getResource("views/" + name + ".fxml") , this.idioma);
                        loader.load();
                        loaders.put(name , loader);
                    }
                    catch(Exception ex)
                    {
                        loader = null;
                        java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE , "Creando loader [" + name + "]." , ex);
                    }
                }
            }
        }
        return loader;
    }

    public void goMain()
    {
        try
        {
            this.mainStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("/cr/ac/una/proyectorestaurante/views/Main.fxml") , this.idioma)));
            mainStage.setMinHeight(1000);
            mainStage.setMinWidth(1445);
            this.mainStage.show();
        }
        catch(IOException ex)
        {
            java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE , "Error inicializando la vista base." , ex);
        }
    }

    public void goView(String viewName)
    {
        goView(viewName , "Center" , null);
    }

    public void goView(String viewName , String accion)
    {
        goView(viewName , "Center" , accion);
    }

    public void goView(String viewName , String location , String accion)
    {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setAccion(accion);
        controller.initialize();
        Stage stage = controller.getStage();
        if(stage == null)
        {
            stage = this.mainStage;
            controller.setStage(stage);
        }
        switch(location)
        {
            case "Center":
                ((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).getChildren().clear();
                ((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).getChildren().add(loader.getRoot());
                int num = (int) (Math.random() * 9 + 1);
                switch(num)
                {
                    case 1:
                        new BounceInUp((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).setCycleCount(1).play();
                        break;
                    case 2:
                        new BounceInDown((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).setCycleCount(1).play();
                        break;
                    case 3:
                        new BounceInLeft((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).setCycleCount(1).play();
                        break;
                    case 4:
                        new BounceInRight((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).setCycleCount(1).play();
                        break;
                    case 5:
                        new SlideInUp((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).setCycleCount(1).play();
                        break;
                    case 6:
                        new SlideInDown((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).setCycleCount(1).play();
                        break;
                    case 7:
                        new SlideInLeft((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).setCycleCount(1).play();
                        break;
                    case 8:
                        new SlideInRight((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).setCycleCount(1).play();
                        break;

                }

                break;
            case "Top":
                break;
            case "Bottom":
                break;
            case "Right":
                break;
            case "Left":
                break;
            default:
                break;
        }
    }

    public void goViewInStage(String viewName , Stage stage)
    {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setStage(stage);
        stage.getScene().setRoot(loader.getRoot());
    }

    public void goViewInWindow(String viewName)
    {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/cr/ac/una/proyectorestaurante/resources/icon.png")));
        stage.setTitle("Restaurante");
        stage.setOnHidden((WindowEvent event) ->
        {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void goLogInWindowModal(Boolean resizable)//Aqui se le puede mandar tambien el nombre de la vista para abrir en modal
    {
        goViewInWindowModal("LogIng2" , this.controller.getStage() , resizable);
    }

    public void goViewInWindowModal(String viewName , Stage parentStage , Boolean resizable)
    {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/cr/ac/una/proyectorestaurante/resources/icon.png")));
        stage.setTitle("Restaurante");
        stage.setResizable(resizable);
        stage.setOnHidden((WindowEvent event) ->
        {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentStage);
        stage.centerOnScreen();
        stage.showAndWait();

    }

    public Controller getController(String viewName)
    {
        return getLoader(viewName).getController();
    }

    public static void setIdioma(ResourceBundle idioma)
    {
        FlowController.idioma = idioma;
    }

    public void initialize()
    {
        this.loaders.clear();
    }

    public void salir()
    {
        this.mainStage.close();
    }

}
