package DataTransferObjects;

import java.awt.*;


public class TileDTO {
    private final String shortName;
    private final Color tileColor;

    public TileDTO(String shortName, Color tileColor) {
        this.shortName = shortName;
        this.tileColor = tileColor;
    }

    public String getShortName() {
        return shortName;
    }

    public Color getTileColor() {
        return tileColor;
    }
}

