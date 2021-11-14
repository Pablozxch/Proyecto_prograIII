/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.io.*;
import java.net.URL;
import java.time.format.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private JFXToggleButton tgllstadoyProductos;
    @FXML
    private JFXDatePicker dtpFechaCierreCaja;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnGenerar;

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

            if(tgllstadoyProductos.isSelected())
            {

                String fechaI = DATE_FORMATTER.format(dtpFechaInicial.getValue());
                String fechaF = DATE_FORMATTER.format(dtpFechaFinal.getValue());
//                    Respuesta res = productoService.productosMVendidos("5/5/2021" , "5/12/2021" , resta.getId());
                System.out.println("Fehca i" + fechaI);
                System.out.println("Fehca F" + fechaF);
                System.out.println("listado de facturas");
                RestauranteDto resta = (RestauranteDto) AppContext.getInstance().get("Restaurante");
                Respuesta res = facturaService.reportelistadofacturas(fechaI , fechaF , resta.getId());
                if(res.getEstado())
                {
                    System.out.println("gg");
                    byte[] decoder = (byte[]) res.getResultado("Factura");
                    cargarArchivo(decoder);
                }
                else
                {
                    System.out.println("ni picha");
                }

            }
            else if(!tgllstadoyProductos.isSelected())
            {
                String fechaI = DATE_FORMATTER.format(dtpFechaInicial.getValue());
                String fechaF = DATE_FORMATTER.format(dtpFechaFinal.getValue());
                System.out.println("PRODUCTOS MAS VENDIDOS");
                RestauranteDto resta = (RestauranteDto) AppContext.getInstance().get("Restaurante");
//                    Respuesta res = productoService.productosMVendidos("5/5/2021" , "5/12/2021" , resta.getId());
                System.out.println("Fehca i" + fechaI);
                System.out.println("Fehca F" + fechaF);
                Respuesta res = productoService.productosMVendidos(fechaI , fechaF , resta.getId());
                if(res.getEstado())
                {
                    System.out.println("gg");
                    byte[] decoder = (byte[]) res.getResultado("Productos");
                    cargarArchivo(decoder);
                }
                else
                {
                    System.out.println("ni picha al cuadrado");
                }
            }

            /**
             *
             * SE PROCEDE A OBTENER EL CHUNCHE Y GUARDAR LA VARA
             *
             */
        }
        if(event.getSource() == btnGenerar)
        {
            System.out.println("cierre cajas");
            RestauranteDto resta = (RestauranteDto) AppContext.getInstance().get("Restaurante");
            CierrecajasDto ciere = (CierrecajasDto) AppContext.getInstance().get("CierreCajasActual");
            String fechaC = DATE_FORMATTER.format(dtpFechaCierreCaja.getValue());
            Long idemp = Long.valueOf(txtIdEmpleado.getText());
            Respuesta res = cierreCajaService.reporteCierreEspecifico(fechaC , idemp , resta.getId());
            if(res.getEstado())
            {
                System.out.println("gg");
                byte[] decoder = (byte[]) res.getResultado("CierreCaja");
                cargarArchivo(decoder);
            }
            else
            {
                System.out.println("ni picha al cuadrado");
            }
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
