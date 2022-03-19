package fr.truffaut.ccos.ui.manager;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class GameContainer {
    List<Node> listPane = new ArrayList<>();
    private ScrollPane scrollPane;

    public GameContainer(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
        this.init();
    }

    private void paneCreator() {
        VBox tempPane = new VBox();
        tempPane.setLayoutX(100);
        tempPane.setLayoutY(100);
        tempPane.setId("firstgame");
        tempPane.setVisible(true);

        listPane.add(tempPane);
    }

    public void init() {
        paneCreator();
        Pane pane = new Pane();
        pane.setLayoutX(1000);
        pane.setLayoutY(1000);
        scrollPane.setContent(pane);
        System.out.println(listPane);
        System.out.println(scrollPane);
    }
}
