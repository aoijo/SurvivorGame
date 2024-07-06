package UseCase;

import java.awt.*;

public class TileData {
    private final String shortName;
    private final Color tileColor;
    private final boolean isSpecial;

    public TileData(String shortName, Color tileColor, boolean isSpecial) {
        this.shortName = shortName;
        this.tileColor = tileColor;
        this.isSpecial = isSpecial;
    }

    public String getShortName() {
        return shortName;
    }

    public Color getTileColor() {
        return tileColor;
    }

    public boolean isSpecial() {
        return isSpecial;
    }
}
