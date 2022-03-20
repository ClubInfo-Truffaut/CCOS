package fr.truffaut.ccos.ui;

import fr.truffaut.ccos.ui.manager.GameContainer;
import fr.truffaut.ccos.utils.InterpolatorCustom;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public ScrollPane gamescrollpane;

    private Transition right;
    private Transition left;

    private Timeline fullright;
    private Timeline fullleft;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.fullright = new Timeline(
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(gamescrollpane.hvalueProperty(), 1, InterpolatorCustom.EASE_05)
                ));
        this.fullleft = new Timeline(
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(gamescrollpane.hvalueProperty(), 0, InterpolatorCustom.EASE_05)
                ));
        this.right = new Transition() {
            {
                setCycleDuration(Duration.INDEFINITE);
                setInterpolator(InterpolatorCustom.EASE_05);
            }

            @Override
            protected void interpolate(double v) {
                if (gamescrollpane.getHvalue() == 1) {
                    fullleft.play();
                }
                gamescrollpane.setHvalue(gamescrollpane.getHvalue() + 0.01);
            }
        };
        this.left = new Transition() {
            {
                setCycleDuration(Duration.INDEFINITE);
                setInterpolator(InterpolatorCustom.EASE_05);
            }

            @Override
            protected void interpolate(double v) {
                if(gamescrollpane.getHvalue() == 0) {
                    fullright.play();
                }
                gamescrollpane.setHvalue(gamescrollpane.getHvalue() - 0.01);
            }
        };
        Platform.runLater(() -> new GameContainer(gamescrollpane));
    }

    @FXML
    public void handleKeyPress(KeyEvent keyEvent) {
        switch(keyEvent.getCode()) {
            case RIGHT -> right.play();
            case LEFT -> left.play();
        }
    }

    @FXML
    public void handleKeyReleased(KeyEvent keyEvent) {
        this.right.stop();
        this.left.stop();
    }
}
