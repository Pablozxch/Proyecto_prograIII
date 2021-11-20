/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.*;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.io.*;
import java.util.logging.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.image.*;
import javax.imageio.*;
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CrearRestauranteController extends Controller implements Initializable
{

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextArea txtDetalle;
    @FXML
    private ImageView imvImagen;
    @FXML
    private JFXButton btnCargarImagen;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXTextArea txtDreccion;
    @FXML
    private JFXComboBox<String> cmbImpVenta;
    @FXML
    private JFXComboBox<String> cmbImpServicio;
    @FXML
    private Label lblImpVenta;
    @FXML
    private Label lblImpServicio;
    /**
     * Initializes the controller class.
     */
    File x;
    RestauranteDto restauranteDto = new RestauranteDto();
    RestauranteService restauranteService = new RestauranteService();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        txtDreccion.setTextFormatter(Formato.getInstance().maxLengthFormat(50));
        txtDetalle.setTextFormatter(Formato.getInstance().letrasFormat(50));
    }

    public void load()
    {
        restauranteDto = (RestauranteDto) AppContext.getInstance().get("Restaurante");
        if(restauranteDto != null)
        {
            unbinRestaurante();
            bindRestaurante();
        }
        AppContext.getInstance().delete("Restaurante");
    }

    public void bindRestaurante()
    {
        txtNombre.setText(restauranteDto.getNombre());
        txtDetalle.setText(restauranteDto.getDetalle());
        Image img2 = new Image(new ByteArrayInputStream(restauranteDto.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imvImagen.setImage(img2);
        txtCorreo.setText(restauranteDto.getCorreo());
        txtDreccion.setText(restauranteDto.getDireccion());
        cmbImpServicio.valueProperty().set(restauranteDto.getImpServ().toString());
        cmbImpVenta.valueProperty().set(restauranteDto.getImpVen().toString());
        
    }

    public void unbinRestaurante()
    {
        txtNombre.clear();
        txtDetalle.clear();
        imvImagen.setImage(null);
        txtCorreo.clear();
        txtDreccion.clear();
        cmbImpServicio.valueProperty().set(null);
        cmbImpVenta.valueProperty().set(null);
    }

    public RestauranteDto retornarRest()
    {
        Respuesta res = restauranteService.getResid(restauranteDto.getId());
        RestauranteDto rr = new RestauranteDto();
        if(res.getEstado())
        {
            rr = (RestauranteDto) res.getResultado("Restaurante");
        }
        return rr;
    }

    void loadIdioma(ResourceBundle bundle){
        btnCargarImagen.setText(bundle.getString("CargarImagen"));
        btnCerrar.setText(bundle.getString("Cancelar"));
        btnContinuar.setText(bundle.getString("Continuar"));
        txtNombre.setPromptText(bundle.getString("Nombre"));
        txtDreccion.setPromptText(bundle.getString("Direccion"));
        txtDetalle.setPromptText(bundle.getString("Detalle"));
        txtCorreo.setPromptText(bundle.getString("Correo"));
        lblImpServicio.setText(bundle.getString("ImpuestoVenta"));
        lblImpVenta.setText(bundle.getString("ImpuestoServicio"));
        
    }
    @FXML
    private void click(ActionEvent event)
    {
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
                imvImagen.setImage(imagen1);
            }
        }
        if(event.getSource() == btnCerrar)
        {
            getStage().close();

        }
        if(event.getSource() == btnContinuar)
        {
            String nombre;
            String detalle;
            String correo;
            String direccion;
            if(!txtNombre.getText().isBlank())
            {
                nombre = txtNombre.getText();
                if(!txtDetalle.getText().isBlank())
                {
                    if(!txtDetalle.getText().isBlank())
                    {
                        detalle = txtDetalle.getText();
                        if(!txtDreccion.getText().isBlank())
                        {
                            if(!txtDreccion.getText().isBlank())
                            {
                                direccion = txtDreccion.getText();
                                if(!txtCorreo.getText().isBlank())
                                {
                                    if(txtCorreo.getText().contains("@"))
                                    {
                                        correo = txtCorreo.getText();
                                        if(imvImagen.getImage() != null || x != null)
                                        {
                                            if(cmbImpServicio.getValue() != null && cmbImpVenta.getValue() != null)
                                            {
                                                try
                                                {
                                                    restauranteDto.setNombre(nombre);
                                                    restauranteDto.setDetalle(detalle);
                                                    restauranteDto.setCorreo(correo);
                                                    restauranteDto.setDireccion(direccion);
                                                    restauranteDto.setImpServ(Long.valueOf(cmbImpServicio.getValue()));
                                                    restauranteDto.setImpVen(Long.valueOf(cmbImpVenta.getValue()));
                                                    if(x != null)
                                                    {
                                                        BufferedImage bufferimage;
                                                        bufferimage = ImageIO.read(x);
                                                        ByteArrayOutputStream output = new ByteArrayOutputStream();
                                                        String fe = FilenameUtils.getExtension(x.getAbsolutePath());
                                                        ImageIO.write(bufferimage , fe , output);
                                                        byte[] data = output.toByteArray();
                                                        restauranteDto.setFoto(data);
                                                    }
                                                    else
                                                    {
                                                        restauranteDto.setFoto(restauranteDto.getFoto());
                                                    }
                                                    Respuesta res = restauranteService.guardarRestaurante(restauranteDto);
                                                    
                                                    
                                                    if(res.getEstado())
                                                    {
                                                        new Mensaje().show(Alert.AlertType.INFORMATION , "Guardar Restaurante" , "El restaurante se ha guardado correctamente.");
                                                        //getStage().close();
                                                    }
                                                    else
                                                    {
                                                        new Mensaje().show(Alert.AlertType.ERROR , "Guardar Restaurante" , "Error al guardar el restaurante.");
                                                    }

                                                }
                                                catch(IOException ex)
                                                {
                                                    Logger.getLogger(CrearRestauranteController.class.getName()).log(Level.SEVERE , null , ex);
                                                }
                                            }
                                            else
                                            {
                                                new Mensaje().show(Alert.AlertType.WARNING , "Error en los datos ingresados" , "Debe seleccionar los tipos de impuestos.");
                                            }
                                        }
                                        else
                                        {
                                            new Mensaje().show(Alert.AlertType.WARNING , "Error en los datos ingresados" , "Debe digitar una imagen para el restaurante.");
                                        }
                                    }
                                    else
                                    {
                                        new Mensaje().show(Alert.AlertType.WARNING , "Error en los datos ingresados" , "Debe digitar un correo electronico para el restaurante.");
                                    }

                                }
                                else
                                {
                                    new Mensaje().show(Alert.AlertType.WARNING , "Error en los datos ingresados" ,"El formato del correo electronico es incorrecto.");
                                }
                            }
                            else
                            {
                                new Mensaje().show(Alert.AlertType.WARNING , "Error en los datos ingresados" , "La direccion del restaurante excede la cantidad maxima de caracteres.");
                            }
                        }
                        else
                        {
                            new Mensaje().show(Alert.AlertType.WARNING , "Error en los datos ingresados" , "Debe digitar la direccion del restaurante.");
                        }
                    }
                    else
                    {
                        new Mensaje().show(Alert.AlertType.WARNING , "Error en los datos ingresados" , "Debe digitar un detalle para el restaurante.");
                    }
                }
                else
                {
                    new Mensaje().show(Alert.AlertType.WARNING , "Error en los datos ingresados" , "Debe digitar un detalle para el restaurante.");
                }

            }
            else
            {
                new Mensaje().show(Alert.AlertType.WARNING , "Error en los datos ingresados" , "Debe digitar un nombre para el restaurante.");
            }
        }
    }

    @Override
    public void initialize()
    {
        for(int i = 0; i <= 100; i++)
        {
            cmbImpServicio.getItems().add(String.valueOf(i));
            cmbImpVenta.getItems().add(String.valueOf(i));
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
