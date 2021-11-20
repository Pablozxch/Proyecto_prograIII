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
import org.apache.commons.io.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CrearProductoController extends Controller implements Initializable
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
    @FXML
    private JFXTextField txtNombreCorto;
    /**
     * Initializes the controller class.
     */
    File x;
    ProductoDto productoDto = new ProductoDto();
    ProductoService productoService = new ProductoService();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        cmbFavorito.getItems().addAll("SI" , "NO");
        txtCosto.setTextFormatter(Formato.getInstance().integerFormat());
        txtCantidad.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().maxLengthFormat(50));
        txtNombreCorto.setTextFormatter(Formato.getInstance().maxLengthFormat(10));
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
        txtNombreCorto.setText(productoDto.getNombrecorto());
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
        txtNombreCorto.clear();
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
                                            String fe = FilenameUtils.getExtension(x.getAbsolutePath());
                                            ImageIO.write(bufferimage , fe , output);
                                            byte[] data = output.toByteArray();
                                            productoDto.setFoto(data);
                                        }
                                        else
                                        {
                                            productoDto.setFoto(productoDto.getFoto());
                                        }
                                        productoDto.setRestauranteDto((RestauranteDto) AppContext.getInstance().get("Restaurante"));
                                        Respuesta res = productoService.guardarProducto(productoDto);
                                        if(res.getEstado())
                                        {
                                            new Mensaje().show(Alert.AlertType.INFORMATION , "Guardar Producto" , "El producto se ha guardado correctamente");
                                            getStage().close();
                                        }
                                        else
                                        {
                                            new Mensaje().show(Alert.AlertType.ERROR , "Guardar Producto" , "Ha ocurrido un error al guardar el producto.");
                                        }

                                    }
                                    catch(IOException ex)
                                    {
                                        Logger.getLogger(CrearRestauranteController.class.getName()).log(Level.SEVERE , null , ex);
                                    }
                                }
                                else
                                {
                                    new Mensaje().show(Alert.AlertType.ERROR , "Faltan datos" , "Debe seleccionar si quiere el producto en el menu favorito o no.");
                                }
                            }
                            else
                            {
                                new Mensaje().show(Alert.AlertType.ERROR , "Faltan datos" , "Debe digitar la cantidad de productos con los que cuenta el restaurante.");
                            }
                        }
                        else
                        {
                            new Mensaje().show(Alert.AlertType.ERROR , "Faltan datos" , "Debe digitar el precio del producto.");
                        }
                    }
                    else
                    {
                        new Mensaje().show(Alert.AlertType.ERROR , "Faltan datos" , "Debe digitar un detalle para el producto.");
                    }

                }
                else
                {
                    new Mensaje().show(Alert.AlertType.ERROR , "Faltan datos" , "Debe digitar el nombre corto para el producto.");
                }
            }
            else
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Faltan datos" , "Debe digitar un nombre para el producto.");
            }
        }
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
