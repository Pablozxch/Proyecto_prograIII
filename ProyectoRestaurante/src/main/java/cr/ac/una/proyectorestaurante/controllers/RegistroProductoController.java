/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javax.imageio.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class RegistroProductoController extends Controller implements Initializable
{

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextArea txtDetalle;
    @FXML
    private JFXTextField txtCosto;
    @FXML
    private ImageView imvImagen;
    @FXML
    private Button btnCargarImagen;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private JFXComboBox<String> cmbFavorito;
    @FXML
    private JFXButton btnVolver;

    /**
     * Initializes the controller class.
     */
    File x;
    ProductoDto productoDto = new ProductoDto();
    ProductoService productoService = new ProductoService();
    @FXML
    private JFXTextField txtNombreCorto;

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        cmbFavorito.getItems().addAll("Sí" , "NO");
        txtCosto.setTextFormatter(Formato.getInstance().integerFormat());
        txtCantidad.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(50));
        txtNombreCorto.setTextFormatter(Formato.getInstance().letrasFormat(10));
    }

    public void load()
    {
        productoDto = (ProductoDto) AppContext.getInstance().get("Producto");
        if(productoDto != null)
        {
            unbindProducto();
            bindProducto();
        }
        AppContext.getInstance().delete("Producto");
    }

    public void bindProducto()
    {
        txtNombre.setText(productoDto.getNombre());
        txtDetalle.setText(productoDto.getDetalle());
        Image img2 = new Image(new ByteArrayInputStream(productoDto.getFoto()));//crea un objeto imagen, transforma el byte[] a un buffered imagen
        imvImagen.setImage(img2);
        txtCosto.setText(productoDto.getCosto().toString());
        txtCantidad.setText(productoDto.getCantidad().toString());
        if("S".equals(productoDto.getAccesoRapido()))
        {
            cmbFavorito.setValue("SI");

        }
        else
        {
            cmbFavorito.setValue("NO");
        }
    }

    public void unbindProducto()
    {
        txtNombre.clear();
        txtDetalle.clear();
        imvImagen.setImage(null);
        txtCosto.clear();
        txtCantidad.clear();
        cmbFavorito.valueProperty().set(null);
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
        if(event.getSource() == btnVolver)
        {
            getStage().close();
        }
        if(event.getSource() == btnContinuar)
        {
            String nombre;
            String nombrecorto;
            String detalle;
            String costo;
            String cantidad;
            if(!txtNombre.getText().isBlank())
            {
                nombre = txtNombre.getText();
                if(!txtNombreCorto.getText().isBlank())
                {
                    nombrecorto = txtNombreCorto.getText();
                    if(!txtDetalle.getText().isBlank())
                    {
                        detalle = txtDetalle.getText();
                        if(!txtCosto.getText().isBlank())
                        {
                            costo = txtCosto.getText();
                            if(!txtCantidad.getText().isBlank())
                            {
                                cantidad = txtCantidad.getText();
                                if(cmbFavorito.getValue() != null)
                                {
                                    try
                                    {
                                        productoDto.setNombre(nombre);
                                        productoDto.setNombrecorto(nombrecorto);
                                        productoDto.setDetalle(detalle);
                                        productoDto.setCosto(Long.valueOf(costo));
                                        productoDto.setCantidad(Long.valueOf(cantidad));
                                        productoDto.setCantidadV(0L);
                                        if("SI".equals(cmbFavorito.getValue()))
                                        {
                                            productoDto.setAccesoRapido("S");
                                        }
                                        else
                                        {
                                            productoDto.setAccesoRapido("N");
                                        }
                                        if(x != null)
                                        {
                                            BufferedImage bufferimage;
                                            bufferimage = ImageIO.read(x);
                                            ByteArrayOutputStream output = new ByteArrayOutputStream();
                                            ImageIO.write(bufferimage , "jpg" , output);
                                            byte[] data = output.toByteArray();
                                            productoDto.setFoto(data);
                                        }
                                        else
                                        {
                                            productoDto.setFoto(productoDto.getFoto());
                                        }
                                        productoDto.setRestauranteDto((RestauranteDto) AppContext.getInstance().get("Restaurante"));
                                        System.out.println("El valor a guardar es " + productoDto.toString());
                                        Respuesta res = productoService.guardarProducto(productoDto);
                                        if(res.getEstado())
                                        {
                                            new Mensaje().show(Alert.AlertType.INFORMATION , "Gurdar Restaurante" , "Guardado Correctamente");
                                        }
                                        else
                                        {
                                            new Mensaje().show(Alert.AlertType.ERROR , "Gurdar Restaurante" , "Error al guardar");
                                        }

                                    }
                                    catch(IOException ex)
                                    {
                                        Logger.getLogger(RegistroRestauranteController.class.getName()).log(Level.SEVERE , null , ex);
                                    }
                                }
                            }
                        }
                    }

                }

            }
            else
            {

            }
        }
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
