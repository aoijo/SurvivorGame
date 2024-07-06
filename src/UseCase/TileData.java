package UseCase;

import java.awt.*;

public class TileData {
    private final String shortName;
    private final Color tileColor;

    public TileData(String shortName, Color tileColor) {
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
