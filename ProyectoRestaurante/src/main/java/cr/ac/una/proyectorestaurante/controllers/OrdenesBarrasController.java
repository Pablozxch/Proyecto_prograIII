/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.*;
import cr.ac.una.proyectorestaurante.models.*;
import cr.ac.una.proyectorestaurante.services.*;
import java.net.URL;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author jp015
 */
public class OrdenesBarrasController extends Controller implements Initializable
{

    @FXML
    private TableView tblpedidos;
    @FXML
    private JFXButton btnAnadir;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnFacturar;
    OrdenService ordenService = new OrdenService();
    List<OrdenDto> ordenes = new ArrayList<OrdenDto>();
    RolDto rolDto = new RolDto();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
    }

    void llenar()
    {
        tblpedidos.getColumns().clear();
        TableColumn<OrdenDto , String> NombreSalon = new TableColumn<>("Nombre Salon");
        NombreSalon.setPrefWidth(tblpedidos.getPrefWidth() / 3);
        NombreSalon.setCellValueFactory(cd -> cd.getValue().getMesaDto().getSalonDto().nombre);
        NombreSalon.setResizable(false);

        TableColumn<OrdenDto , String> nombreMesa = new TableColumn<>("Nombre Mesa");
        nombreMesa.setPrefWidth(tblpedidos.getPrefWidth() / 3);
        nombreMesa.setCellValueFactory(cd -> cd.getValue().getMesaDto().nombre);
        nombreMesa.setResizable(false);
//
        TableColumn<OrdenDto , String> nombreempelado = new TableColumn<>("Empleado");
        nombreempelado.setPrefWidth(tblpedidos.getPrefWidth() / 3);
        nombreempelado.setCellValueFactory(cd -> cd.getValue().getEmpleadoDto().nombre);
        nombreempelado.setResizable(false);

        tblpedidos.getColumns().add(NombreSalon);
        tblpedidos.getColumns().add(nombreMesa);
        tblpedidos.getColumns().add(nombreempelado);
        tblpedidos.refresh();

    }

    @FXML
    private void click(ActionEvent event)
    {
    }

    @Override
    public void initialize()
    {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
