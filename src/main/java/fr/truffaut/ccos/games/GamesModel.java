package fr.truffaut.ccos.games;

import lombok.Getter;
import lombok.Setter;

public class GamesModel {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String imagePath;

    @Getter
    @Setter
    private String romPath;

    public GamesModel(String name, String imagePath, String romPath) {
        this.name = name;
        this.imagePath = imagePath;
        this.romPath = romPath;
    }
}
