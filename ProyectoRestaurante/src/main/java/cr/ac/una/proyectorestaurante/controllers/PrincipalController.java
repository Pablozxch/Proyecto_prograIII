/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class PrincipalController extends Controller implements Initializable
{

    @FXML
    private JFXButton btnSalones;
    @FXML
    private JFXButton btnProductos;
    @FXML
    private JFXButton btnOrdenes;
    @FXML
    private JFXButton btnFacturar;
    @FXML
    private JFXButton btnEmpleados;
    @FXML
    private JFXButton btnCerrarSesion;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private Label lblTitulo;
    @FXML
    private JFXButton btnCategorias;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        lblTitulo.setText(" Sistema del Restaurante " + ((RestauranteDto) AppContext.getInstance().get("Restaurante")).getNombre() + " ");//cambiar para ver que se ve mejor xD
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void click(ActionEvent event)
    {

        if(event.getSource() == btnSalones)
        {

            if(((EmpleadoDto) AppContext.getInstance().get("EmpleadoActual")) == null)
            {
                FlowController.getInstance().goViewInWindowModal("LogIn" , (Stage) btnCerrarSesion.getScene().getWindow() , Boolean.FALSE);//ver que se hace con esto
            }
            else
            {
                FlowController.getInstance().goView("SalonesGeneral");
            }
        }
        if(event.getSource() == btnProductos)
        {
            if(((EmpleadoDto) AppContext.getInstance().get("EmpleadoActual")) == null)
            {
                FlowController.getInstance().goViewInWindowModal("LogIn" , (Stage) btnCerrarSesion.getScene().getWindow() , Boolean.FALSE);//ver que se hace con esto
            }
            else
            {
                FlowController.getInstance().goView("ProductosGeneral");
            }

        }
        if(event.getSource() == btnCategorias)
        {
            if(((EmpleadoDto) AppContext.getInstance().get("EmpleadoActual")) == null)
            {
                FlowController.getInstance().goViewInWindowModal("LogIn" , (Stage) btnCerrarSesion.getScene().getWindow() , Boolean.FALSE);//ver que se hace con esto
            }
            else
            {
                FlowController.getInstance().goView("CategoriasGeneral");
            }

        }
        if(event.getSource() == btnOrdenes)
        {
            if(((EmpleadoDto) AppContext.getInstance().get("EmpleadoActual")) == null)
            {
                FlowController.getInstance().goViewInWindowModal("LogIn" , (Stage) btnCerrarSesion.getScene().getWindow() , Boolean.FALSE);//ver que se hace con esto
            }
            else
            {
                FlowController.getInstance().goView("OrdenesGeneral");
            }

        }
        if(event.getSource() == btnEmpleados)
        {
            if(((EmpleadoDto) AppContext.getInstance().get("EmpleadoActual")) == null)
            {
                FlowController.getInstance().goViewInWindowModal("LogIn" , (Stage) btnCerrarSesion.getScene().getWindow() , Boolean.FALSE);//ver que se hace con esto
            }
            else
            {
                FlowController.getInstance().goView("EmpleadosGeneral");
            }

        }
        if(event.getSource() == btnSalir)
        {
            if(new Mensaje().showConfirmation("Saliendo del Sistema" , getStage() , "¿Esta seguro que desea salir del sistema?"))
            {
                getStage().close();
            }
        }
        if(event.getSource() == btnCerrarSesion)
        {
            AppContext.getInstance().delete("EmpleadoActual");//este usuario es el encargado de ingresar a todo lo que el pueda
            FlowController.getInstance().goViewInWindowModal("LogIn" , (Stage) btnCerrarSesion.getScene().getWindow() , Boolean.FALSE);//ver que se hace con esto
            //preguntar que si lo de crear empleados en esta vista

        }

    }

}
