/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import cr.ac.una.proyectorestaurante.utils.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CrearCodigosDescuentosController extends Controller implements Initializable
{

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXTextField txtUrl;

    CodDescService service = new CodDescService();
    CodigodescDto dd = new CodigodescDto();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        txtCantidad.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
    }

    void clear()
    {
        txtNombre.clear();
        txtCantidad.clear();
        txtUrl.clear();
    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnAceptar)
        {
            System.out.println("aceptar");
            if(txtNombre.getText().isEmpty() || txtNombre.getText().isBlank())
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Incompleto" , "Se debe ingresar un nombre para el codigo de descuento.");
            }
            else if(txtCantidad.getText().isEmpty() || txtCantidad.getText().isBlank())
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Incompleto" , "Se debe ingresar el monto de descuento del codigo.");
            }
            else if(txtUrl.getText().isEmpty() || txtUrl.getText().isBlank())
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Incompleto" , "Se debe ingresar el codigo del descuento.");
            }
            else
            {
                RestauranteDto resta = (RestauranteDto) AppContext.getInstance().get("Restaurante");
                dd.setNombre(txtNombre.getText());
                dd.setCantidadusar(100L);
                dd.setDesc(Long.valueOf(txtCantidad.getText()));
                dd.setUrl(txtUrl.getText());
                dd.setRestaurante(resta);
                Respuesta res = service.guardarCodigo(dd);
                if(res.getEstado())
                {
                    new Mensaje().show(Alert.AlertType.INFORMATION , "Guardado" , "Se guardo el codigo satisfactoriamente.");
                    getStage().close();
                }
            }
        }
        else if(event.getSource() == btnVolver)
        {
            getStage().close();
        }

    }

    @Override
    public void initialize()
    {
        dd = (CodigodescDto) AppContext.getInstance().get("Codigo");
        if(dd != null)
        {

            txtNombre.setText(dd.getNombre());
            txtCantidad.setText(String.valueOf(dd.getDesc()));
            txtUrl.setText(dd.getUrl());

        }
        else
        {
            dd = new CodigodescDto();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
