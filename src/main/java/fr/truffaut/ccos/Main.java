package fr.truffaut.ccos;


import com.google.gson.Gson;
import fr.truffaut.ccos.games.GamesModel;
import fr.truffaut.ccos.utils.Config;
import fr.truffaut.ccos.utils.InterpolatorCustom;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Main extends Application {
    public static Stage instance;
    @Getter
    public static List<GamesModel> gamesList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws IOException {
        startup();
        primaryStage.setTitle("Arcade Menu");
        primaryStage.setHeight(Double.parseDouble(Config.getPropertyOrDefault("ui.height")));
        primaryStage.setWidth(Double.parseDouble(Config.getPropertyOrDefault("ui.width")));
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(false);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/main.css")).toExternalForm());
        scene.getRoot().setOpacity(0);
        primaryStage.setScene(scene);
        primaryStage.show();
        new Timeline(
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(scene.getRoot().opacityProperty(), 1, InterpolatorCustom.EASE_OUT)
                )).play();
        instance = primaryStage;
    }

    public void startup() {
        Gson gson = new Gson();
        try(Stream<Path> walk = Files.walk(Path.of("games/"))) {
            walk.filter(Files::isRegularFile).filter(path -> path.toString().contains(".json")).map(Path::toFile).forEach(file -> {
                try {
                    gamesList.add(gson.fromJson(Files.readString(file.toPath()), GamesModel.class));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
