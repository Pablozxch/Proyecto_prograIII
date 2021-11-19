/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import cr.ac.una.proyectorestaurante.models.RestauranteDto;
import cr.ac.una.proyectorestaurante.services.RestauranteService;
import cr.ac.una.proyectorestaurante.utils.*;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class VistaGeneralController extends Controller implements Initializable {

    @FXML
    private ImageView imvRestaurante;
    @FXML
    private Label lblNombreRestaurante;

    /**
     * Initializes the controller class.
     */
    
    RestauranteDto restauranteDto = new RestauranteDto();
    RestauranteService restauranteService = new RestauranteService();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        restauranteDto = (RestauranteDto) AppContext.getInstance().get("Restaurante");
        
        Image img2 = new Image(new ByteArrayInputStream(restauranteDto.getFoto()));
        imvRestaurante.setImage(img2);
        lblNombreRestaurante.setText(restauranteDto.getNombre());
    }

    @Override
    public void initialize() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
