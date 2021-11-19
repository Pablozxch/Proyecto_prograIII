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
import java.util.*;
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
    @FXML
    private JFXButton btnDescuento;
    /**
     * Initializes the controller class.
     */
    RolDto rolDto = new RolDto();
    @FXML
    private Button btnRestaurante;
    @FXML
    private JFXButton btnEspanol;
    @FXML
    private JFXButton btnEnglish;

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
        if(event.getSource() == btnDescuento)
        {
            if(((EmpleadoDto) AppContext.getInstance().get("EmpleadoActual")) == null)
            {
                FlowController.getInstance().goViewInWindowModal("LogIn" , (Stage) btnCerrarSesion.getScene().getWindow() , Boolean.FALSE);//ver que se hace con esto
            }
            else
            {
                rolDto = (RolDto) AppContext.getInstance().get("RolActual");
                if("Administrativos".equals(rolDto.getNombre())){
                    FlowController.getInstance().goView("CodigoDescuentoGeneral");
                }
                
            }
        }
        if(event.getSource() == btnRestaurante)
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
                    CrearRestauranteController registroRestauranteController = (CrearRestauranteController) FlowController.getInstance().getController("CrearRestaurante");
                    registroRestauranteController.load();
                    FlowController.getInstance().goView("CrearRestaurante");
                    RestauranteDto rDto = registroRestauranteController.retornarRest();
                    AppContext.getInstance().delete("Restaurante");
                    AppContext.getInstance().set("Restaurante" , rDto);
                }
                else
                {
                    new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado");
                }

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

        if(event.getSource() == btnDescuento)
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
                    FlowController.getInstance().goView("CodigoDescuentoGeneral");
                }
                else
                {
                    new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado");
                }
            }

        }
        if(event.getSource() == btnReportes)
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
                    FlowController.getInstance().goView("ReportesGeneral");
                }
                else
                {
                    new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado");
                }
            }
        }
        if(event.getSource() == btnCierreCajas)
        {
            if(((EmpleadoDto) AppContext.getInstance().get("EmpleadoActual")) == null)
            {
                FlowController.getInstance().goViewInWindowModal("LogIn" , (Stage) btnCerrarSesion.getScene().getWindow() , Boolean.FALSE);//ver que se hace con esto
            }
            else
            {
                rolDto = (RolDto) AppContext.getInstance().get("RolActual");
                if(!"Saloneros".equals(rolDto.getNombre()))
                {
                    CierreCajasController cr = (CierreCajasController) FlowController.getInstance().getController("CierreCajas");
                    if(((CierrecajasDto) AppContext.getInstance().get("CierreCajasActual")) == null)
                    {
                        FlowController.getInstance().goView("CierreCajas");
                        cr.createCierre();
                    }
                    else
                    {
                        FlowController.getInstance().goView("CierreCajas");
                        cr.closeCierreCajas();
                    }
                }

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

    @FXML
    private void idiomaClick(ActionEvent event)
    {
        if(event.getSource() == btnEspanol)
        {

            Locale locale = new Locale("es_MX");
            ResourceBundle bundle = ResourceBundle.getBundle("/cr/ac/una/proyectorestaurante/resources/Espanol" , locale);
            btnCategorias.setText(bundle.getString("Categorias"));

        }
        if(event.getSource() == btnEnglish)
        {
            Locale locale = new Locale("en_UK");
            ResourceBundle bundle = ResourceBundle.getBundle("/cr/ac/una/proyectorestaurante/resources/Ingles" , locale);
            btnCategorias.setText(bundle.getString("Categorias"));
        }
    }
}
