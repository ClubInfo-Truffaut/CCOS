package fr.truffaut.ccos.games;

import lombok.Getter;

public class GamesModel {
    @Getter
    public String name;
    @Getter
    public String imagePath;
    @Getter
    public String romPath;

    public GamesModel(String name, String imagePath, String romPath) {
        this.name = name;
        this.imagePath = imagePath;
        this.romPath = romPath;
    }
}
