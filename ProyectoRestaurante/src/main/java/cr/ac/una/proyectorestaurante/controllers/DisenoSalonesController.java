/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectorestaurante.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Christopher
 */
public class DisenoSalonesController extends Controller implements Initializable {

    @FXML
    private JFXButton btnAgregar;
    @FXML
    private GridPane gripMesa;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXCheckBox chkEditar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {
    }
    
    private void dragAndDrop(ImageView image, GridPane board) {

        image.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Dragboard db = image.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                ClipboardContent content = new ClipboardContent();
                Image checker = image.getImage();
                content.putImage(checker);
                db.setContent(content);

                System.out.println("Drag detectado");
                event.consume();

            }
        });

        image.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

                    System.out.println("Drag over detectado");
                }

                event.consume();
            }
        });

        image.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.ANY);

                System.out.println("Drop detected");
                event.consume();
            }
        });
    }

    private void dragAndDrop2(ImageView image, GridPane board) {
        List<Node> squares = board.getChildrenUnmodifiable();
        for (int i = 0; i < 2; i++) {
            Node target = squares.get(i);

            image.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    Dragboard db = image.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(image.getImage());
                    db.setContent(content);
                    System.out.println("Drag detected");
                    event.consume();
                }
            });

            image.setOnDragOver(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    if (event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    int col = GridPane.getColumnIndex(target);
                    int row = GridPane.getRowIndex(target);
                    System.out.println("Drag Over Detected over column " + col + " and row " + row);
                    event.consume();
                }
            });

            image.setOnDragDropped(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasImage()) {
                        ImageView piece = new ImageView(db.getImage());
                        dragAndDrop(piece, board);
                        board.add(piece, GridPane.getColumnIndex(target), GridPane.getRowIndex(target));
                        success = true;
                    }
                    event.setDropCompleted(success);
                    System.out.println("Drop detected");
                    event.consume();
                }
            });

            image.setOnDragDone(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    if (event.getTransferMode() == TransferMode.MOVE) {
                        image.setImage(null);
                    }
                    System.out.println("Drag Complete!");
                    event.consume();
                }
            });
        }
    }

    @FXML
    private void click(ActionEvent event) {
    }

}
