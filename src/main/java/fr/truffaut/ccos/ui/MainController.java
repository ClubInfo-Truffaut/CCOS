package fr.truffaut.ccos.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public Button chinois;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void chinoir(ActionEvent actionEvent) {
        System.out.println("mange ta mère");
    }
}
