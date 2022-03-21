package fr.truffaut.ccos.ui;

import animatefx.animation.AnimateFXInterpolator;
import animatefx.animation.FadeIn;
import animatefx.animation.SlideInLeft;
import fr.truffaut.ccos.Main;
import fr.truffaut.ccos.ui.manager.GameContainer;
import fr.truffaut.ccos.utils.Config;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public ScrollPane gamescrollpane;
    @FXML
    public ToolBar toolbarup;
    public GridPane gridpane;

    private Transition right;
    private Transition left;

    private Timeline fullright;
    private Timeline fullleft;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setSpacing(20);
        Platform.runLater(() -> {
            initTimeline();
            hBox.getChildren().add(initClock());
            hBox.getChildren().add(initVersion());
            gridpane.add(initLogo(), 8, 4);
            toolbarup.getItems().add(hBox);
            new GameContainer(gamescrollpane);
            gamescrollpane.setHvalue(-1);
        });
    }

    private void initTimeline() {
        System.out.println((double) 1 / 100);
        this.fullright = new Timeline(
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(gamescrollpane.hvalueProperty(), 1, AnimateFXInterpolator.EASE)
                ));
        this.fullleft = new Timeline(
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(gamescrollpane.hvalueProperty(), 0, AnimateFXInterpolator.EASE)
                ));

        this.right = new Transition() {
            {
                setCycleDuration(Duration.INDEFINITE);
                setInterpolator(AnimateFXInterpolator.EASE);
            }

            @Override
            protected void interpolate(double v) {
                if (gamescrollpane.getHvalue() == 1) {
                    fullleft.play();
                }
                gamescrollpane.setHvalue(gamescrollpane.getHvalue() + (double) 1 / (Main.gamesList.size() * 10));

            }
        };
        this.left = new Transition() {
            {
                setCycleDuration(Duration.INDEFINITE);
                setInterpolator(AnimateFXInterpolator.EASE);
            }

            @Override
            protected void interpolate(double v) {
                if (gamescrollpane.getHvalue() == 0) {
                    fullright.play();
                }
                gamescrollpane.setHvalue(gamescrollpane.getHvalue() - (double) 1 / (Main.gamesList.size() * 10));
            }
        };
    }

    private ImageView initLogo() {
        Image image = new Image(getClass().getResource("/img/logo.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(141);
        imageView.setLayoutY(-100);
        return imageView;
    }

    private Text initVersion() {
        Text text = new Text(Config.getProperty("version").get());
        text.setFont(Font.loadFont(Objects.requireNonNull(getClass().getResource("/fontr.ttf")).toExternalForm(), 20));
        text.setOpacity(0);
        new FadeIn(text).play();
        new SlideInLeft(text).play();
        return text;
    }

    private Text initClock() {
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        final Text clock = new Text(time.format(new Date()));
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                event -> clock.setText(time.format(new Date()))));
        clock.setOpacity(0);
        new FadeIn(clock).setDelay(Duration.seconds(1)).play();
        new SlideInLeft(clock).setDelay(Duration.seconds(1)).play();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        clock.setFont(Font.loadFont(Objects.requireNonNull(getClass().getResource("/fontr.ttf")).toExternalForm(), 20));
        return clock;
    }

    @FXML
    public void handleKeyPress(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case D -> right.play();
            case Q -> left.play();
        }
    }

    @FXML
    public void handleKeyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case D -> right.stop();
            case Q -> left.stop();
        }
    }
}
