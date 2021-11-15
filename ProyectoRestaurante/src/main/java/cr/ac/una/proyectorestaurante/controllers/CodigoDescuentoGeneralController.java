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

    void ObtencionDatos()
    {
        Respuesta res = codigoService.getCodigos();
        if(res.getEstado())
        {
            List<CodigodescDto> codd = (List<CodigodescDto>) res.getResultado("Codigo");
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
                AppContext.getInstance().set("Codigo" , (CodigodescDto) tblpedidos.getSelectionModel().getSelectedItem());
                CrearCodigosDescuentosController ccod = (CrearCodigosDescuentosController) FlowController.getInstance().getController("CrearCodigosDescuentos");
                FlowController.getInstance().goViewInWindowModal("CrearCodigosDescuentos" , (Stage) btnAnadir.getScene().getWindow() , Boolean.FALSE);
                ccod.clear();
                AppContext.getInstance().delete("Codigo");
            }
        }
        if(event.getSource() == btnEliminar)
        {
            if(tblpedidos.getSelectionModel().getSelectedItem() != null)
            {
                CodigodescDto codt = (CodigodescDto) tblpedidos.getSelectionModel().getSelectedItem();
                System.out.println("codt "+codt.toString());
                Respuesta res = codigoService.eliminarCodigo(codt.getId());
                if(res.getEstado())
                {
                    new Mensaje().show(Alert.AlertType.INFORMATION , "Eliminado" , "Correctamente Elimnado");
                }
            }

        }
        if(event.getSource() == btnAnadir)
        {
            CrearCodigosDescuentosController ccod = (CrearCodigosDescuentosController) FlowController.getInstance().getController("CrearCodigosDescuentos");
            ccod.clear();
            FlowController.getInstance().goViewInWindowModal("CrearCodigosDescuentos" , (Stage) btnAnadir.getScene().getWindow() , Boolean.FALSE);
        }
        initialize();
    }

    @Override
    public void initialize()
    {
        ObtencionDatos();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
