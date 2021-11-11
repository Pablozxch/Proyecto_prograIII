/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import javafx.scene.Node;

/**
 *
 * @author Christopher
 */
public class DragAndDrop {

    private double mouseAnchorX;
    private double mouseAnchorY;

    public void makeDraggable(Node node) {

        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
            System.out.println(mouseAnchorY);
            System.out.println(mouseAnchorX);
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
        });
    }

}
