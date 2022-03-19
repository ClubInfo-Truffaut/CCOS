package fr.truffaut.ccos;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static Stage instance;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Arcade Menu");
        primaryStage.setHeight(900);
        primaryStage.setWidth(1440);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);


        primaryStage.show();
        instance = primaryStage;
    }
}
