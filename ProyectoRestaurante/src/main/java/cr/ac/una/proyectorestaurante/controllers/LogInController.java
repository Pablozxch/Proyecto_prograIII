/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class LogInController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtContrasena;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnContinuar;

    /**
     * Initializes the controller class.
     */
    EmpleadoService empleadoService = new EmpleadoService();
    EmpleadoDto emp = new EmpleadoDto();
    CierrecajasDto cierre = new CierrecajasDto();
    RolDto rol = new RolDto();
    CierreCajaService cajaService = new CierreCajaService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void click(ActionEvent event) {

        if (event.getSource() == btnContinuar) {
            if (txtUsuario.getText() == null || txtUsuario.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de usuario", (Stage) btnContinuar.getScene().getWindow(), "Es necesario digitar un usuario para ingresar al sistema.");
            } else if (txtContrasena.getText() == null || txtContrasena.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de usuario", (Stage) btnContinuar.getScene().getWindow(), "Es necesario digitar la clave para ingresar al sistema.");
            } else {
                String user = txtUsuario.getText();
                String contra = txtContrasena.getText();
                Respuesta res = empleadoService.validarEmpleado(user, contra);
                if (res.getEstado()) {
                    emp = (EmpleadoDto) res.getResultado("Empleado");
                    rol = emp.getRolDto();
                    AppContext.getInstance().set("RolActual", rol);
                    AppContext.getInstance().set("EmpleadoActual", emp);
                    AppContext.getInstance().set("Token", emp.getToken());
                    new Mensaje().show(Alert.AlertType.INFORMATION, "Datos Correctos.", "Empleado " + emp.getNombre() + " encontrado ");
                    getStage().close();
                } else {
                    new Mensaje().show(Alert.AlertType.ERROR, "Datos", "Los datos no existen o están mal digitados");
                }
            }
        }
        if (event.getSource() == btnCancelar) {
            ((Stage) btnCancelar.getScene().getWindow()).close();
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
