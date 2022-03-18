package fr.truffaut.ccos.utils;

import fr.truffaut.ccos.Main;

import java.time.LocalDate;

public class ThemeManager {

    public void loadTheme() {
        Main.instance.getScene().getStylesheets().clear();
        Main.instance.getScene().getStylesheets().add(getCurrentTheme());
    }

    private String getCurrentTheme() {

        switch (LocalDate.now().getMonth().toString()) {
            case "January" -> {

            }

            case "February" -> {
            }


            case "March" -> {
                return ClassLoader.getSystemResource("css/main.css").getPath();
            }

            case "April" -> {

            }

            case "June" -> {
                return ClassLoader.getSystemResource("css/main.css").getPath();
            }


            case "July" -> {
                return ClassLoader.getSystemResource("css/main.css").getPath();
            }

            case "September" -> {
            }


            case "October" -> {
                return ClassLoader.getSystemResource("css/main.css").getPath();
            }


            case "November" -> {
            }


            case "December" -> {
                return ClassLoader.getSystemResource("css/main.css").getPath();
            }
            default -> {
                return ClassLoader.getSystemResource("css/main.css").getPath();
            }
        }
        return ClassLoader.getSystemResource("css/main.css").getPath();
    }
}
