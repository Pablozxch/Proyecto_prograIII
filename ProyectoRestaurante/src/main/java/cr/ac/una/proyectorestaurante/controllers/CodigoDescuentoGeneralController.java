/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.proyectorestaurante.services.*;
import cr.ac.una.proyectorestaurante.models.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import cr.ac.una.proyectorestaurante.utils.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CodigoDescuentoGeneralController extends Controller implements Initializable {

    @FXML
    private TableView tblpedidos;
    @FXML
    private JFXButton btnAnadir;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnFacturar;

    /**
     * Initializes the controller class.
     */
    
    CodDescService codigoService = new CodDescService();
    List<CodigodescDto> codigos = new ArrayList<CodigodescDto>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenar();
    }    

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void click(ActionEvent event) {
        if(event.getSource() == btnAnadir){
            FlowController.getInstance().goViewInWindowModal("CrearCodigosDescuentos", (Stage) btnAnadir.getScene().getWindow() , Boolean.FALSE);
        }
    }
    
    void llenar(){
        tblpedidos.getColumns().clear();
        
        TableColumn<CodigodescDto , String> idDesc = new TableColumn<>("Nombre Salon");
        idDesc.setPrefWidth(tblpedidos.getPrefWidth() /4);
        idDesc.setCellValueFactory(cd -> cd.getValue().nombre);
        idDesc.setResizable(false);
        
        TableColumn<CodigodescDto , String> nombreDesc = new TableColumn<>("Nombre Salon");
        nombreDesc.setPrefWidth(tblpedidos.getPrefWidth() / 4);
        nombreDesc.setCellValueFactory(cd -> cd.getValue().nombre);
        nombreDesc.setResizable(false);

        TableColumn<CodigodescDto , String> descuento = new TableColumn<>("Nombre Mesa");
        descuento.setPrefWidth(tblpedidos.getPrefWidth() / 4);
        descuento.setCellValueFactory(cd -> cd.getValue().desc);
        descuento.setResizable(false);
//
        TableColumn<CodigodescDto , String> cantDesc = new TableColumn<>("Empleado");
        cantDesc.setPrefWidth(tblpedidos.getPrefWidth() / 4);
        cantDesc.setCellValueFactory(cd -> cd.getValue().cantidadusar);
        cantDesc.setResizable(false);

        tblpedidos.getColumns().add(idDesc);
        tblpedidos.getColumns().add(nombreDesc);
        tblpedidos.getColumns().add(descuento);
        tblpedidos.getColumns().add(cantDesc);
        tblpedidos.refresh();
    }
    
}
