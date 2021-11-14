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
import java.net.URL;
import java.util.*;
import java.util.stream.*;
import javafx.collections.*;
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
    SalonDto salonDto = new SalonDto();

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

    void ObtencionDatos()
    {
        Respuesta res = ordenService.getOrdenes();
        if(res.getEstado())
        {
            List<OrdenDto> ordeness = (List<OrdenDto>) res.getResultado("Ordenes");
            ordenes = ordeness.stream().filter(t -> "P".equals(t.getEstado()) && t.getMesaDto().getId() == salonDto.getId()).collect(Collectors.toList());
            ordenes.forEach(t ->
            {
                System.out.println(t.getEstado());
            });
        }
        ObservableList<OrdenDto> ords = FXCollections.observableList(ordenes);
        tblpedidos.setItems(ords);
        tblpedidos.refresh();

    }

    @FXML
    private void click(ActionEvent event)
    {
        if(event.getSource() == btnEditar)
        {
            if(tblpedidos.getSelectionModel().getSelectedItem() != null)
            {
                AppContext.getInstance().set("Orden" , (OrdenDto) tblpedidos.getSelectionModel().getSelectedItem());
                FlowController.getInstance().goViewInWindowModal("CrearPedido" , getStage() , Boolean.FALSE);
            }
        }
        if(event.getSource() == btnFacturar)
        {

            if("Cajeros".equals(rolDto.getNombre()) || "Administrativos".equals(rolDto.getNombre()))
            {
                if(tblpedidos.getSelectionModel().getSelectedItem() != null)
                {
                    AppContext.getInstance().set("Orden" , (OrdenDto) tblpedidos.getSelectionModel().getSelectedItem());
                    FlowController.getInstance().goViewInWindowModal("Factura" , getStage() , Boolean.FALSE);

                }
            }
            else
            {
                new Mensaje().show(Alert.AlertType.ERROR , "Permisos" , "Permisos innecesarios para acceder a este apartado");
            }

        }

        if(event.getSource() == btnAnadir)
        {

            
            /*
            
                @SE DEBE DE CREAR LA VARA PARA QUE CREE LAS ORDENES Y LAS ASIGNE AL SALON, YA QUE ESTA VISTA NO CONTIENE
                @COORDENADAS   
            
            */
        }
    }

    @Override
    public void initialize()
    {
        rolDto = (RolDto) AppContext.getInstance().get("RolActual");
        salonDto = (SalonDto) AppContext.getInstance().get("Salon");
        ObtencionDatos();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
