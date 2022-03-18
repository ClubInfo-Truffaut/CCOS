package fr.truffaut.ccos;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static Stage instance;
    private AnchorPane anchorPane;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Arcade Menu");
        primaryStage.setHeight(900);
        primaryStage.setWidth(1440);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);

        anchorPane = new AnchorPane();
        scene = new Scene(anchorPane, primaryStage.getWidth(), primaryStage.getHeight());


        scene.setFill(Color.web("#333333"));
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/main.css")).toExternalForm());

        //TODO : Background and some things on the anchor pane.

        primaryStage.setScene(scene);


        primaryStage.show();
        instance = primaryStage;
    }
}
