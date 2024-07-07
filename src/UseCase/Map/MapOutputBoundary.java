package UseCase.Map;

import java.awt.Color;

public interface MapOutputBoundary {
    void presentMap(MapOutputData outputData);
    void setPlayerPosition(int[] position);
    void setPlayerColor(Color color);
}
