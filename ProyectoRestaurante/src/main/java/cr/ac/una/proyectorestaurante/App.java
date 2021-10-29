package cr.ac.una.proyectorestaurante;

import cr.ac.una.proyectorestaurante.utils.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.*;

/**
 * JavaFX App
 */
public class App extends Application
{

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException
    {
        FlowController.getInstance().InitializeFlow(stage , null);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/cr/ac/una/proyectorestaurante/resources/icon.png")));
        stage.setTitle("Restaurante");
        FlowController.getInstance().goView("Categorias");
        //FlowController.getInstance().goMain();
    }

    static void setRoot(String fxml) throws IOException
    {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
