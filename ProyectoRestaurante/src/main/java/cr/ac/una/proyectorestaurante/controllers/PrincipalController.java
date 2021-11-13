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
    @FXML
    private JFXButton btnReportes;
    @FXML
    private JFXButton btnCierreCajas;

    /**
     * Initializes the controller class.
     */
    RolDto rolDto = new RolDto();
    @FXML
    private Button btnRestaurante;

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        lblTitulo.setText(" Sistema del Restaurante " + ((RestauranteDto) AppContext.getInstance().get("Restaurante")).getNombre() + " ");//cambiar para ver que se ve mejor xD
    }

    @Override
    public void initialize()
    {

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
                rolDto = (RolDto) AppContext.getInstance().get("RolActual");
                if("Administrativos".equals(rolDto.getNombre()))
                {
                    FlowController.getInstance().goView("ProductosGeneral");
                }
                else
                {
                    new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado");
                }
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
                rolDto = (RolDto) AppContext.getInstance().get("RolActual");
                if("Administrativos".equals(rolDto.getNombre()))
                {
                    FlowController.getInstance().goView("CategoriasGeneral");
                }
                else
                {
                    new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado");
                }

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
        if(event.getSource() == btnRestaurante)
        {
            CrearRestauranteController registroRestauranteController = (CrearRestauranteController) FlowController.getInstance().getController("CrearRestaurante");
            registroRestauranteController.load();
            FlowController.getInstance().goView("CrearRestaurante");
            RestauranteDto rDto = registroRestauranteController.retornarRest();
            AppContext.getInstance().delete("Restaurante");
            AppContext.getInstance().set("Restaurante" , rDto);
        }
        if(event.getSource() == btnEmpleados)
        {
            if(((EmpleadoDto) AppContext.getInstance().get("EmpleadoActual")) == null)
            {
                FlowController.getInstance().goViewInWindowModal("LogIn" , (Stage) btnCerrarSesion.getScene().getWindow() , Boolean.FALSE);//ver que se hace con esto
            }
            else
            {
                rolDto = (RolDto) AppContext.getInstance().get("RolActual");
                if("Administrativos".equals(rolDto.getNombre()))
                {
                    FlowController.getInstance().goView("EmpleadosGeneral");
                }
                else
                {
                    new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado");
                }
            }

        }
        if(event.getSource() == btnReportes)
        {
            FlowController.getInstance().goView("ReportesGeneral");
        }
        if(event.getSource() == btnCierreCajas)
        {
            FlowController.getInstance().goView("CierreCajas");
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
