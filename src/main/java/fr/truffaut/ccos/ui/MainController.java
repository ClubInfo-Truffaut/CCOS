package fr.truffaut.ccos.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public Button chinois;
    @FXML
    public ScrollPane gamescrollpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Platform.runLater(() -> new GameContainer(gamescrollpane));
        Pane pane = new Pane();
        pane.setLayoutX(10000);
        pane.setLayoutY(10000);
        gamescrollpane.setContent(pane);
    }

    @FXML
    public void chinoir(ActionEvent actionEvent) {
        System.out.println("mange ta mère");
    }

    @FXML
    public void keypressedgamecontainer(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            switch (keyEvent.getCode()) {
                case RIGHT:

                    break;
                case LEFT:
                    break;
            }
        }
    }
}
