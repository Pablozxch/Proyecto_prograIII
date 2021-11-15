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
import java.util.stream.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class CodigoDescuentoGeneralController extends Controller implements Initializable
{

    @FXML
    private TableView tblpedidos;
    @FXML
    private JFXButton btnAnadir;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnEliminar;

    /**
     * Initializes the controller class.
     */
    CodDescService codigoService = new CodDescService();
    List<CodigodescDto> codigos = new ArrayList<CodigodescDto>();
    List<CodigodescDto> codigodescDtos = new ArrayList<>();

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        llenar();
    }

    @Override
    public void initialize()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void ObtencionDatos()
    {
        Respuesta res = codigoService.getCodigos();
        if(res.getEstado())
        {
            List<CodigodescDto> codd = (List<CodigodescDto>) res.getResultado("coddesc");
            codigodescDtos = codd;
        }
        ObservableList<CodigodescDto> codds = FXCollections.observableList(codigodescDtos);
        tblpedidos.setItems(codds);
        tblpedidos.refresh();

    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnEditar)
        {
            if(tblpedidos.getSelectionModel().getSelectedItem() != null)
            {
                AppContext.getInstance().set("Codigo" , (OrdenDto) tblpedidos.getSelectionModel().getSelectedItem());
                FlowController.getInstance().goViewInWindowModal("CrearCodigosDescuentos" , (Stage) btnAnadir.getScene().getWindow() , Boolean.FALSE);
            }
        }
        if(event.getSource() == btnEliminar)//cambiar por eliminar
        {
            if(tblpedidos.getSelectionModel().getSelectedItem() != null)
            {
                AppContext.getInstance().set("Codigo" , (OrdenDto) tblpedidos.getSelectionModel().getSelectedItem());
                FlowController.getInstance().goViewInWindowModal("CrearCodigosDescuentos" , (Stage) btnAnadir.getScene().getWindow() , Boolean.FALSE);
            }

        }
    }

    void llenar()
    {
        tblpedidos.getColumns().clear();

        TableColumn<CodigodescDto , String> idDesc = new TableColumn<>("Nombre");
        idDesc.setPrefWidth(tblpedidos.getPrefWidth() / 3);
        idDesc.setCellValueFactory(cd -> cd.getValue().nombre);
        idDesc.setResizable(false);

        TableColumn<CodigodescDto , String> nombreDesc = new TableColumn<>("URL");
        nombreDesc.setPrefWidth(tblpedidos.getPrefWidth() / 3);
        nombreDesc.setCellValueFactory(cd -> cd.getValue().url);
        nombreDesc.setResizable(false);

        TableColumn<CodigodescDto , String> descuento = new TableColumn<>("Cantidad Descuento");
        descuento.setPrefWidth(tblpedidos.getPrefWidth() / 3);
        descuento.setCellValueFactory(cd -> cd.getValue().desc);
        descuento.setResizable(false);

        tblpedidos.getColumns().add(idDesc);
        tblpedidos.getColumns().add(nombreDesc);
        tblpedidos.getColumns().add(descuento);
        tblpedidos.refresh();
    }

}
