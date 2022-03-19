package fr.truffaut.ccos;


import fr.truffaut.ccos.utils.Config;
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
        System.out.println(Config.getPropertyOrDefault("ui.height"));
        primaryStage.setTitle("Arcade Menu");
        primaryStage.setHeight(Integer.valueOf(Config.getPropertyOrDefault("ui.height")));
        primaryStage.setWidth(Integer.valueOf(Config.getPropertyOrDefault("ui.width")));
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(false);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);


        primaryStage.show();
        instance = primaryStage;
    }
}
