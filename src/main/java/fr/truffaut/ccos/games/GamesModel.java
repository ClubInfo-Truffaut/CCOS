package fr.truffaut.ccos.games;

import lombok.Getter;
import lombok.Setter;

public class GamesModel {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String path;

    public GamesModel(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
