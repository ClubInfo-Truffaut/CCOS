package fr.truffaut.ccos.ui.manager;

import fr.truffaut.ccos.utils.InterpolatorCustom;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class GameContainer {
    List<Node> listPane = new ArrayList<>();
    private ScrollPane scrollPane;
    private Timeline zoomIn;
    private Timeline zoomOut;

    public GameContainer(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
        this.init();
    }

    private void paneCreator() {
        Button tempPane = new Button();
        Rectangle rectangle = new Rectangle(250, 384);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        tempPane.setMinHeight(384);
        tempPane.setMinWidth(250);
        tempPane.setShape(rectangle);
        tempPane.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.3), 10, 0.5, 10, 10);");
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/img/test.jpg").toExternalForm(), 250, 384, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        tempPane.setBackground(background);
        tempPane.setVisible(true);
        tempPane.setOnMouseEntered(mouseEvent -> {
            this.zoomIn = new Timeline(
                    new KeyFrame(Duration.seconds(0.3),
                            new KeyValue(tempPane.scaleXProperty(), 1.2, InterpolatorCustom.EASE_BOTH),
                            new KeyValue(tempPane.scaleYProperty(), 1.2, InterpolatorCustom.EASE_BOTH)
                    ));
            zoomIn.play();
        });
        tempPane.setOnMouseExited(mouseEvent -> {
            this.zoomOut = new Timeline(
                    new KeyFrame(Duration.seconds(0.3),
                            new KeyValue(tempPane.scaleXProperty(), 1, InterpolatorCustom.EASE_BOTH),
                            new KeyValue(tempPane.scaleYProperty(), 1, InterpolatorCustom.EASE_BOTH)
                    ));
            zoomOut.play();
        });
        this.listPane.add(tempPane);
    }

    public void init() {
        paneCreator();
        paneCreator();
        paneCreator();
        paneCreator();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(50));
        hbox.setSpacing(50);
        hbox.setMinWidth(scrollPane.getMinWidth());
        hbox.setMinHeight(scrollPane.getMinHeight());
        hbox.setVisible(true);
        hbox.getChildren().addAll(listPane);
        scrollPane.setContent(hbox);
    }

}
