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
import java.net.URL;
import java.time.format.*;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class ReportesGeneralController extends Controller implements Initializable
{

    @FXML
    private JFXDatePicker dtpFechaInicial;
    @FXML
    private JFXDatePicker dtpFechaFinal;
    @FXML
    private JFXTextField txtIdEmpleado;
    @FXML
    private JFXToggleButton tglCierrreCajas;
    @FXML
    private JFXToggleButton tgllstadoyProductos;
    @FXML
    private JFXDatePicker dtpFechaCierreCaja;
    @FXML
    private JFXButton btnAceptar;

    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
              .ofPattern("dd/MM/yyyy");

    CierreCajaService cierreCajaService = new CierreCajaService();
    FacturaService facturaService = new FacturaService();
    ProductoService productoService = new ProductoService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {

    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnAceptar)
        {
            if(tgllstadoyProductos.isSelected() && tglCierrreCajas.isSelected())
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Error" , "Favor solo tener una seleccion posible");
            }
            else
            {
                String fechaI = null, fechaF = null, fechaC = null;
                if(dtpFechaInicial.getValue() != null)
                {
                    fechaI = DATE_FORMATTER.format(dtpFechaInicial.getValue());
                }
                else if(dtpFechaFinal.getValue() != null)
                {
                    fechaF = DATE_FORMATTER.format(dtpFechaFinal.getValue());
                }
                else if(dtpFechaCierreCaja.getValue() != null)
                {
                    fechaC = DATE_FORMATTER.format(dtpFechaCierreCaja.getValue());
                }

                if(tgllstadoyProductos.isSelected())
                {

                    System.out.println("listado de facturas");
                }
                else if(!tgllstadoyProductos.isSelected())
                {
                    System.out.println("PRODUCTOS MAS VENDIDOS");
                    RestauranteDto resta = (RestauranteDto) AppContext.getInstance().get("Restaurante");
                    Respuesta res = productoService.productosMVendidos(fechaI , fechaF , resta.getId());
                    if(res.getEstado())
                    {
                        System.out.println("gg");
//                        byte[] decoder = (byte[]) res.getResultado("Productos");
//                        cargarArchivo(decoder);
                    }
                    else
                    {
                        System.out.println("ni picha");
                    }
                }
                if(tglCierrreCajas.isSelected())
                {
                    System.out.println("cierre cajas");
                }
            }
            /**
             *
             * SE PROCEDE A OBTENER EL CHUNCHE Y GUARDAR LA VARA
             *
             */
        }
    }

    void cargarArchivo(byte[] decoder)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF" , "*.pdf"));
        fileChooser.setInitialFileName("*.pdf");
        File selectedFile = fileChooser.showSaveDialog(new Stage());

        if(selectedFile != null)
        {
            File file2 = new File(selectedFile.toPath().toString());
            try(FileOutputStream fos = new FileOutputStream(file2);)
            {
                fos.write(decoder);
                new Mensaje().show(Alert.AlertType.CONFIRMATION , "Guardado" , "Con exito");
            }
            catch(Exception e)
            {
            }
        }
    }

}
