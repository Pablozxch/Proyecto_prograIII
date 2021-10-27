module cr.ac.una.proyectorestaurante
{
    requires javafx.controls;
    requires javafx.fxml;

    requires java.logging;
    requires com.jfoenix;
    requires java.base;
    /*    
    REST
     */
    requires jakarta.ws.rs;
    requires jakarta.xml.bind;
    requires AnimateFX;
//    requires jakarta.json;

    opens cr.ac.una.proyectorestaurante to javafx.fxml , javafx.graphics;
    opens cr.ac.una.proyectorestaurante.controllers to javafx.fxml , javafx.graphics;
    exports cr.ac.una.proyectorestaurante.models;
    exports cr.ac.una.proyectorestaurante.services;

}
