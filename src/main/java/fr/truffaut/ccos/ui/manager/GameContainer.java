package fr.truffaut.ccos.ui.manager;

import fr.truffaut.ccos.games.GamesModel;
import fr.truffaut.ccos.utils.InterpolatorCustom;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class GameContainer {
    List<Node> listPane = new ArrayList<>();
    private ScrollPane scrollPane;
    private final ArrayList<GamesModel> listGames = new ArrayList<>();

    public GameContainer(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
        this.init();
    }

    private void paneCreator() {
        Font font = Font.loadFont(getClass().getResource("/font.ttf").toExternalForm(), 45);
        Pane tempPane = new Pane();
        Rectangle rectangle = new Rectangle();
        rectangle.setArcHeight(12);
        rectangle.setArcWidth(12);
        rectangle.setHeight(384);
        rectangle.setWidth(250);
        tempPane.setMaxHeight(384);
        tempPane.setMinHeight(384);
        tempPane.setMaxWidth(250);
        tempPane.setMinWidth(250);
        tempPane.setShape(rectangle);
        tempPane.setId("gamebuttons");
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/img/test.jpg").toExternalForm(), 250, 384, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        tempPane.setBackground(background);
        tempPane.setVisible(true);
        tempPane.setOnMouseEntered(mouseEvent -> {
            Timeline zoomIn = new Timeline(
                    new KeyFrame(Duration.seconds(0.15),
                            new KeyValue(tempPane.scaleXProperty(), 1.05, InterpolatorCustom.EASE_BOTH),
                            new KeyValue(tempPane.scaleYProperty(), 1.05, InterpolatorCustom.EASE_BOTH)
                    ));
            zoomIn.play();


        });


        tempPane.setOnMouseExited(mouseEvent -> {


            Timeline zoomOut = new Timeline(
                    new KeyFrame(Duration.seconds(0.15),
                            new KeyValue(tempPane.scaleXProperty(), 1, InterpolatorCustom.EASE_BOTH),
                            new KeyValue(tempPane.scaleYProperty(), 1, InterpolatorCustom.EASE_BOTH)
                    ));
            zoomOut.play();


        });


        this.listPane.add(tempPane);
    }

    public void init() {
        paneCreator();
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(50);
        hbox.setMinWidth(scrollPane.getWidth());
        hbox.setMinHeight(scrollPane.getHeight());
        hbox.setVisible(true);
        hbox.getChildren().addAll(listPane);
        scrollPane.getStyleClass().clear();
        scrollPane.setContent(hbox);
    }

}
