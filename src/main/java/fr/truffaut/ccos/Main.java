package fr.truffaut.ccos;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Arcade Menu");
        primaryStage.setHeight(900);
        primaryStage.setWidth(1440);
        primaryStage.setFullScreen(false);
        primaryStage.show();
    }
}
