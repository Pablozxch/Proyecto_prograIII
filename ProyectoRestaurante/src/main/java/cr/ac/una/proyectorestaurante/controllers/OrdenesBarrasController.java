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
import java.text.*;
import java.util.*;
import java.util.logging.*;
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
    List<OrdenDto> ordenes = new ArrayList<>();
    RolDto rolDto = new RolDto();
    SalonDto salonDto = new SalonDto();
    List<MesaDto> nMesaDtos = new ArrayList<>();
    MesaService mesaService = new MesaService();
    OrdenService ordenService = new OrdenService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        // TODO
        llenar();
    }

    void llenar()
    {
        tblpedidos.getColumns().clear();
        TableColumn<OrdenDto , String> NombreSalon = new TableColumn<>("Nombre Salon");
        NombreSalon.setPrefWidth(tblpedidos.getPrefWidth() / 2);
        NombreSalon.setCellValueFactory(cd -> cd.getValue().getMesaDto().getSalonDto().nombre);
        NombreSalon.setResizable(false);

//
        TableColumn<OrdenDto , String> nombreempelado = new TableColumn<>("Empleado");
        nombreempelado.setPrefWidth(tblpedidos.getPrefWidth() / 2);
        nombreempelado.setCellValueFactory(cd -> cd.getValue().getEmpleadoDto().nombre);
        nombreempelado.setResizable(false);

        tblpedidos.getColumns().add(NombreSalon);
        tblpedidos.getColumns().add(nombreempelado);
        tblpedidos.refresh();

    }

    void ObtencionDatos()
    {

        Respuesta res = ordenService.getOrdenes();
        if(res.getEstado())
        {
            List<OrdenDto> ordeness = (List<OrdenDto>) res.getResultado("Ordenes");
            ordenes = ordeness.stream().filter(t -> "P".equals(t.getEstado()) && Objects.equals(t.getMesaDto().getSalonDto().getId() , salonDto.getId())).collect(Collectors.toList());
        }
        ObservableList<OrdenDto> ords = FXCollections.observableList(ordenes);
        tblpedidos.setItems(ords);
        tblpedidos.refresh();
        Respuesta res2 = mesaService.getMesas();
        if(res2.getEstado())
        {
            nMesaDtos=(List<MesaDto>) res2.getResultado("Mesas");
        }
        if(nMesaDtos == null)
        {

            System.out.println("NO HAY MESA");
            MesaDto mesaN = new MesaDto();
            mesaN.setNombre("Bar");
            mesaN.setPosX(Long.MIN_VALUE);
            mesaN.setPosY(Long.MIN_VALUE);
            mesaN.setSalonDto(salonDto);
            mesaN.setEstado("D");
            Respuesta respu = mesaService.guardarMesa(mesaN);
            if(respu.getEstado())
            {
                System.out.println("Se guardo");
                obtenermesas();
            }
        }

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
            try
            {
                EmpleadoDto empleadoDto = (EmpleadoDto) AppContext.getInstance().get("EmpleadoActual");
                OrdenDto orden = new OrdenDto();
                orden.setEmpleadoDto(empleadoDto);
                Date date = new Date();
                SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                Date date2 = formatter1.parse(formatter1.format(date));
                orden.setEmpleadoDto(empleadoDto);
                orden.setFecha(date2);
                orden.setMesaDto(nMesaDtos.get(0));
                orden.setEstado("P");
                ordenService.guardarOrden(orden);
                Respuesta res2 = ordenService.lasto();
                orden = null;
                if(res2.getEstado())
                {
                    orden = (OrdenDto) res2.getResultado("Orden");
                }
                AppContext.getInstance().set("Orden" , orden);
                FlowController.getInstance().goViewInWindowModal("CrearPedido" , getStage() , Boolean.FALSE);
            }
            catch(ParseException ex)
            {
                Logger.getLogger(OrdenesBarrasController.class.getName()).log(Level.SEVERE , null , ex);
            }
        }
        initialize();

    }

    void obtenermesas()
    {
        System.out.println("Obteniendo mesas");
        nMesaDtos.clear();
        Respuesta res = mesaService.getMesas();
        if(res.getEstado())
        {
            System.out.println("mesa encontrada");
            nMesaDtos = (List<MesaDto>) res.getResultado("Mesas");
        }
    }

    @Override
    public void initialize()
    {
        obtenermesas();
        rolDto = (RolDto) AppContext.getInstance().get("RolActual");
        salonDto = (SalonDto) AppContext.getInstance().get("Salon");
        ObtencionDatos();

    }

}
