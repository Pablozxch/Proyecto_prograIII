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
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class MainController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnAgregarRestaurante;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label lblNombreRes;
    @FXML
    private ImageView imgRes;
    @FXML
    private TextField txtBuscar;
    /**
     * Initializes the controller class.
     */

    private MyListenerItem myListenerRess;
    RestauranteService restauranteService = new RestauranteService();
    RestauranteDto restauranteDto = new RestauranteDto();
    private static List<RestauranteDto> restaurantes = new ArrayList<>();
    @FXML
    private BorderPane root2;
    @FXML
    private JFXButton btnSpanish;
    @FXML
    private JFXButton btnEnglish;
    @FXML
    private Label lblRestaurante;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadItems();
    }

    public void loadItems() {
        Respuesta respuesta = restauranteService.getRestaurantes();
        restaurantes = (List<RestauranteDto>) respuesta.getResultado("Restaurantes");

        if (restaurantes.size() > 0) {
            setResSelect(restaurantes.get(0));
            myListenerRess = new MyListenerItem() {
                @Override
                public void onClickListener(Object res) {
                    setResSelect((RestauranteDto) res);
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
        }
        int column = 0;
        int row = 1;
        String name1;
        String name2;
        try {
            for (int i = 0; i < restaurantes.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/cr/ac/una/proyectorestaurante/views/Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                name1 = restaurantes.get(i).getNombre();
                if (i + 1 < restaurantes.size()) {
                    name2 = restaurantes.get(i + 1).getNombre();
                    if (name1 == null ? name2 == null : name1.equals(name2)) {
                        break;
                    }
                }

                ItemController itemrest = fxmlLoader.getController();
                itemrest.setData(restaurantes.get(i), myListenerRess);
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setResSelect(RestauranteDto res) {
        System.out.println("Nasd");
        lblNombreRes.setText(res.getNombre());
        Image img2 = new Image(new ByteArrayInputStream(res.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imgRes.setImage(img2);
        AppContext.getInstance().set("Restaurante", res);
    }

    @FXML
    private void click(ActionEvent event) {
        if (event.getSource() == btnAgregarRestaurante) {
            FlowController.getInstance().goViewInWindowModal("RegistroRestaurante", (Stage) btnAgregarRestaurante.getScene().getWindow(), false);
        }
        if (event.getSource() == btnBuscar) {
            
        }
        if (event.getSource() == btnContinuar) {

            FlowController.getInstance().goViewInStage("Principal", (Stage) btnContinuar.getScene().getWindow());
            Stage s = (Stage) btnContinuar.getScene().getWindow();
            s.close();
        }
        if (event.getSource() == btnEditar) {
            FlowController.getInstance().goViewInWindowModal("RegistroRestaurante", (Stage) btnAgregarRestaurante.getScene().getWindow(), false);
        }
        if (event.getSource() == btnEliminar) {
            RestauranteDto res = (RestauranteDto) AppContext.getInstance().get("Restaurante");
            restauranteService.eliminarRestaurante(res.getId());

        }
        if (event.getSource() == btnSpanish) {

            System.out.println("Espa");
            Locale locale = new Locale("es_MX");
            ResourceBundle bundle = ResourceBundle.getBundle("/cr/ac/una/proyectorestaurante/resources/Espanol", locale);
            FlowController.setIdioma(bundle);
            
            btnAgregarRestaurante.setText(bundle.getString("AgregarRestaurante"));
            btnBuscar.setText(bundle.getString("buscar"));
            btnContinuar.setText(bundle.getString("Continuar"));
            btnEditar.setText(bundle.getString("Editar"));
            btnEliminar.setText(bundle.getString("Eliminar"));
            lblNombreRes.setText(bundle.getString("Restaurante"));
        }
        if (event.getSource() == btnEnglish) {
            System.out.println("English");
            Locale locale = new Locale("en_UK");
            ResourceBundle bundle = ResourceBundle.getBundle("/cr/ac/una/proyectorestaurante/resources/Ingles", locale);
            FlowController.setIdioma(bundle);
            
            btnAgregarRestaurante.setText(bundle.getString("AgregarRestaurante"));
            btnBuscar.setText(bundle.getString("buscar"));
            btnContinuar.setText(bundle.getString("Continuar"));
            btnEditar.setText(bundle.getString("Editar"));
            btnEliminar.setText(bundle.getString("Eliminar"));
            lblNombreRes.setText(bundle.getString("Restaurante"));
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
