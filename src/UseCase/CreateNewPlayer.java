package UseCase;

import Entity.Player;
import Enums.Race;

import java.awt.Color;

public class CreateNewPlayer {

    public static Player createPlayer(String name, int[] position, Color color, Race race) {
        return new Player(name,position, color, race);
    }
}
