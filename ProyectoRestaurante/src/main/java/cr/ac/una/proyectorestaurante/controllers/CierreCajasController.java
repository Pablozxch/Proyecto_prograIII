/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.utils.*;
import java.io.*;
import java.net.URL;
import java.text.*;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CierreCajasController extends Controller implements Initializable
{

    @FXML
    private JFXTextField txtMontoInicial;
    @FXML
    private JFXTextField txtMontoTarjeta;
    @FXML
    private JFXTextField txtMontoEfectivo;
    @FXML
    private JFXTextField txtMontoFinal;
    @FXML
    private JFXButton btnCierreCaja;
    @FXML
    private JFXButton btnReporte;
    EmpleadoDto emp = new EmpleadoDto();
    CierrecajasDto cierreCajas = new CierrecajasDto();
    CierreCajaService cajaService = new CierreCajaService();
    CierrecajasDto cierre = new CierrecajasDto();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO

    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnCierreCaja)
        {
            Long monto = Long.valueOf(txtMontoInicial.getText());
            emp = null;
            emp = (EmpleadoDto) AppContext.getInstance().get("EmpleadoActual");
            cierre.setEmpleadoDto(emp);
            cierre.setMontoInicial(monto);
            cierre.setMontoEfectivo(0L);
            cierre.setMontoFinal(0L);
            cierre.setMontoTarjeta(0L);
            cierre.setEstado("I");

            Respuesta res2 = cajaService.guardarCierrecajas(cierre);
            if(res2.getEstado())
            {
                Respuesta res3 = cajaService.lasto();
                cierre = (CierrecajasDto) res3.getResultado("CierreCaja");
                AppContext.getInstance().set("CierreCajasActual" , cierre);
                new Mensaje().show(Alert.AlertType.INFORMATION , "Datos guardados." , "El cierre de caja se ha iniciado exitosamente.");
                FlowController.getInstance().goView("VistaGeneral");
            }
            else
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Datos" , "No se pudo iniciar el cierre de caja.");
            }
        }
        if(event.getSource() == btnReporte)
        {
            RestauranteDto res = (RestauranteDto) AppContext.getInstance().get("Restaurante");
            cierre = (CierrecajasDto) AppContext.getInstance().get("CierreCajasActual");
            Long montoEfectivo = Long.valueOf(txtMontoEfectivo.getText());
            Long montoFinal = Long.valueOf(txtMontoFinal.getText());
            Long montoTarjeta = Long.valueOf(txtMontoTarjeta.getText());
            cierre.setMontoEfectivo(montoEfectivo);
            cierre.setMontoFinal(montoFinal);
            cierre.setMontoTarjeta(montoTarjeta);
            if(cierre.getMontoInicial() + montoEfectivo + montoTarjeta != montoFinal)
            {
                cierre.setEstado("I");
            }
            else
            {
                cierre.setEstado("C");
            }
            //cierre.setMontoFinal(cierre.getMontoInicial() + montoEfectivo + montoTarjeta);
            cajaService.guardarCierrecajas(cierre);

            /*
            
                Proceder a generar el reporte correspondiente
             */
            Date date = new Date();
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = formatter1.format(date);
            System.out.println("Fecha " + fecha + "ID CIERRE " + cierre.getId() + "Rees id " + res.getId());

            Respuesta respuesta = cajaService.reporteCierreCajero(fecha , cierre.getId() , res.getId());
            if(respuesta.getEstado())
            {
                byte[] decoder = (byte[]) respuesta.getResultado("CierreCaja");
                cargarArchivo(decoder);
            }
            AppContext.getInstance().delete("CierreCajasActual");
            AppContext.getInstance().delete("EmpleadoActual");
            AppContext.getInstance().delete("RolActual");
            clearALl();
            FlowController.getInstance().goView("VistaGeneral");
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
                new Mensaje().show(Alert.AlertType.CONFIRMATION , "Guardado" , "Cierre de caja guardado con exito.");
            }
            catch(Exception e)
            {
            }
        }
    }

    void clearALl()
    {
        txtMontoInicial.setDisable(false);
        txtMontoFinal.setDisable(false);
        txtMontoEfectivo.setDisable(false);
        txtMontoTarjeta.setDisable(false);
        btnCierreCaja.setDisable(true);
        btnReporte.setDisable(false);
        txtMontoInicial.clear();
        txtMontoFinal.clear();
        txtMontoEfectivo.clear();
        txtMontoTarjeta.clear();
    }

    void createCierre()
    {
        txtMontoFinal.setDisable(true);
        txtMontoEfectivo.setDisable(true);
        txtMontoTarjeta.setDisable(true);
        btnReporte.setDisable(true);
    }

    void closeCierreCajas()
    {
        txtMontoInicial.setDisable(true);
        txtMontoFinal.setDisable(false);
        txtMontoEfectivo.setDisable(false);
        txtMontoTarjeta.setDisable(false);
        btnCierreCaja.setDisable(true);
        btnReporte.setDisable(false);
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
