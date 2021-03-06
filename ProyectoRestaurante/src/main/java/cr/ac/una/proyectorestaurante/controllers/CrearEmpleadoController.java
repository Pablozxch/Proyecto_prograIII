/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.*;
import java.util.stream.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javax.imageio.*;
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CrearEmpleadoController extends Controller implements Initializable
{

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXComboBox<String> cmbRoles;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXTextField txtContrasena;
    @FXML
    private JFXButton btnRegresar;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private JFXTextField txtApellido;
    @FXML
    private ImageView imvEmpleado;
    @FXML
    private JFXButton btnCargarImagen;
    /**
     * Initializes the controller class.
     */
    File x;
    EmpleadoDto empleadoDto = new EmpleadoDto();
    EmpleadoService empleadoService = new EmpleadoService();
    RolService rolService = new RolService();
    List<RolDto> roles = new ArrayList<>();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        Respuesta res = rolService.getRoles();
        roles = (List<RolDto>) res.getResultado("Roles");
        roles.forEach(t ->
        {
            cmbRoles.getItems().add(t.getNombre());
        });
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(26));
        txtApellido.setTextFormatter(Formato.getInstance().letrasFormat(26));
        txtUsuario.setTextFormatter(Formato.getInstance().maxLengthFormat(26));
        txtContrasena.setTextFormatter(Formato.getInstance().maxLengthFormat(26));

    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnRegresar)
        {
            getStage().close();
        }
        if(event.getSource() == btnCargarImagen)
        {
            FileChooser file = new FileChooser();
            file.getExtensionFilters().addAll(
                      new FileChooser.ExtensionFilter("img" , "*.jpg") ,
                      new FileChooser.ExtensionFilter("img" , "*.png") ,
                      new FileChooser.ExtensionFilter("img2" , "*.gif")
            );

            File file2 = file.showOpenDialog(null);
            if(file2 != null)
            {
                Image imagen1 = new Image(file2.toURI().toString());
                x = file2;
                imvEmpleado.setImage(imagen1);
            }
        }
        if(event.getSource() == btnContinuar)
        {
            String nombre;
            String apellido;
            String password;
            String user;
            String rol;
            if(!txtNombre.getText().isBlank())
            {
                nombre = txtNombre.getText();
                if(!txtApellido.getText().isBlank())
                {
                    apellido = txtApellido.getText();
                    if(cmbRoles.getValue() != null)
                    {
                        rol = cmbRoles.getValue();
                        if(imvEmpleado.getImage() != null || x != null)
                        {
                            if(!txtUsuario.getText().isBlank())
                            {
                                user = txtUsuario.getText();
                                if(!txtContrasena.getText().isBlank())
                                {
                                    password = txtContrasena.getText();
                                    try
                                    {
                                        empleadoDto.setNombre(nombre);
                                        empleadoDto.setApellido(apellido);
                                        empleadoDto.setUsuario(user);
                                        empleadoDto.setContra(password);
                                        roles.forEach(t ->
                                        {
                                            if(t.getNombre() == null ? cmbRoles.getValue() == null : t.getNombre().equals(cmbRoles.getValue()))
                                            {
                                                empleadoDto.setRolDto(t);//ver como guardar este dato
                                            }
                                        });

                                        if(x != null)
                                        {
                                            BufferedImage bufferimage;
                                            bufferimage = ImageIO.read(x);
                                            ByteArrayOutputStream output = new ByteArrayOutputStream();
                                            String fe = FilenameUtils.getExtension(x.getAbsolutePath());
                                            ImageIO.write(bufferimage , fe , output);
                                            byte[] data = output.toByteArray();
                                            empleadoDto.setFoto(data);
                                        }
                                        else
                                        {
                                            empleadoDto.setFoto(empleadoDto.getFoto());
                                        }
                                        RestauranteDto resta=(RestauranteDto)AppContext.getInstance().get("Restaurante");
                                        empleadoDto.setRestauranteDto(resta);
                                        Respuesta res = empleadoService.guardarEmpleado(empleadoDto);
                                        if(res.getEstado())
                                        {
                                            new Mensaje().show(Alert.AlertType.INFORMATION , "Guardar Empleado" , "El empleado ha sido guardado correctamente.");
                                            getStage().close();
                                        }
                                        else
                                        {
                                            new Mensaje().show(Alert.AlertType.ERROR , "Guardar Empleado" , "Error al guardar el empleado.");
                                        }

                                    }
                                    catch(IOException ex)
                                    {
                                        Logger.getLogger(CrearRestauranteController.class.getName()).log(Level.SEVERE , null , ex);
                                    }
                                }
                                else
                                {
                                    new Mensaje().show(Alert.AlertType.ERROR , "Error en algunos datos" , "El campo de la contrase??a se encuentra vac??o.");
                                }
                            }
                        }
                        else
                        {
                            new Mensaje().show(Alert.AlertType.ERROR , "Error en algunos datos" , "Debe ingresar una imagen para poder guardar el empleado.");
                        }
                    }
                    else
                    {
                        new Mensaje().show(Alert.AlertType.ERROR , "Error en algunos datos" , "Debe seleccionar un rol para poder guardar el empleado.");
                    }
                }
                else
                {
                    new Mensaje().show(Alert.AlertType.ERROR , "Error en algunos datos" , "Debe digitar un apellido para poder guardar el empleado.");
                }
            }
            else
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Error en algunos datos" , "Debe digitar un nombre para poder guardar el empleado.");
            }
        }
    }

    @Override
    public void initialize()
    {
    }

    void unbindEmpleado()
    {
        txtNombre.clear();
        txtApellido.clear();
        imvEmpleado.setImage(null);//falta el espacio de la imagen ya lo coloco
        txtUsuario.clear();
        txtContrasena.clear();
        cmbRoles.valueProperty().set(null);
    }

    private void bindEmpleado()
    {
        txtNombre.setText(empleadoDto.getNombre());
        txtApellido.setText(empleadoDto.getApellido());
        Image img2 = new Image(new ByteArrayInputStream(empleadoDto.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imvEmpleado.setImage(img2);
        txtContrasena.setText(empleadoDto.getContra());
        txtUsuario.setText(empleadoDto.getUsuario());
        cmbRoles.valueProperty().set(empleadoDto.getRolDto().getNombre());
    }

    void load()
    {
        empleadoDto = (EmpleadoDto) AppContext.getInstance().get("Empleado");
        if(empleadoDto != null)
        {
            unbindEmpleado();
            bindEmpleado();
        }
        AppContext.getInstance().delete("Empleado");
    }

}
