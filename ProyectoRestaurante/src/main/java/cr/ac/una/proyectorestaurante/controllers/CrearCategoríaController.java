/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CrearCategoríaController extends Controller implements Initializable
{

    @FXML
    private JFXTextField txtCategoria;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXTextArea txtDetalle;

    /**
     * Initializes the controller class.
     */
    CategoriaService service = new CategoriaService();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnVolver)
        {
            getStage().close();
        }
        if(event.getSource() == btnAceptar)
        {
            String nombrecat = txtCategoria.getText();
            String detalle = txtDetalle.getText();
            CategoriaDto catDto = new CategoriaDto();
            catDto.setNombre(nombrecat);
            catDto.setDetalle(detalle);
            catDto.setRestauranteDto((RestauranteDto) AppContext.getInstance().get("Restaurante"));
            Respuesta res=service.guardarCategoria(catDto);
            if(res.getEstado())
            {
                new Mensaje().show(Alert.AlertType.INFORMATION , "Guardado de Categorias", "La categoria fue guardad con exito");
            }
            getStage().close();
        }
    }

}
