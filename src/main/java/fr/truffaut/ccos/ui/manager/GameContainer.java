package fr.truffaut.ccos.ui.manager;

import fr.truffaut.ccos.Main;
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
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameContainer {
    List<Node> listPane = new ArrayList<>();
    private ScrollPane scrollPane;

    public GameContainer(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
        this.init();
    }

    private Pane pane(Path imgpath) {
        System.out.println(imgpath);
        Pane pane = new Pane();
        Rectangle rectangle = new Rectangle();
        BackgroundImage backgroundImage = new BackgroundImage(new Image(imgpath.toAbsolutePath().toString(), 250, 384, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);

        rectangle.setArcHeight(12);
        rectangle.setArcWidth(12);
        rectangle.setHeight(384);
        rectangle.setWidth(250);

        pane.setShape(rectangle);
        pane.setMaxHeight(384);
        pane.setMinHeight(384);
        pane.setMaxWidth(250);
        pane.setMinWidth(250);
        pane.setId("gamebuttons");
        pane.setBackground(background);
        pane.setVisible(true);
        pane.setOnMouseEntered(mouseEvent -> {
            Timeline zoomIn = new Timeline(
                    new KeyFrame(Duration.seconds(0.05),
                            new KeyValue(pane.scaleXProperty(), 1.05, InterpolatorCustom.EASE_IN),
                            new KeyValue(pane.scaleYProperty(), 1.05, InterpolatorCustom.EASE_IN)
                    ));
            zoomIn.play();
        });
        pane.setOnMouseExited(mouseEvent -> {
            Timeline zoomOut = new Timeline(
                    new KeyFrame(Duration.seconds(0.05),
                            new KeyValue(pane.scaleXProperty(), 1, InterpolatorCustom.EASE_OUT),
                            new KeyValue(pane.scaleYProperty(), 1, InterpolatorCustom.EASE_OUT)
                    ));
            zoomOut.play();
        });
        return pane;
    }

    private Text text(String name) {
        Text text = new Text();
        Font font = Font.loadFont(Objects.requireNonNull(getClass().getResource("/font.ttf")).toExternalForm(), 30);

        text.setText(name);
        text.setFont(font);
        text.setVisible(true);

        return text;
    }

    private void Creator(String name, Path imgpath, Path rompath) throws IOException {
        Pane pane = pane(imgpath);
        Text text = text(name);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        vBox.getChildren().addAll(pane, text);
        listPane.add(vBox);
    }

    private void createFakeBox() {
        Pane pane = new Pane();
        pane.setMaxHeight(384);
        pane.setMinHeight(384);
        pane.setMaxWidth(384);
        pane.setMinWidth(384);
        listPane.add(pane);
    }

    public void init() {
        createFakeBox();
        Main.gamesList.forEach(game -> {
            try {
                Creator(game.getName(), Path.of(game.getImagePath()), Path.of(game.getRomPath())); //
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        createFakeBox();
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
