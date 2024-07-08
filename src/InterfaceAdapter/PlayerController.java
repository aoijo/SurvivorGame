package InterfaceAdapter;

import Enums.Race;
import UseCase.PlayerUseCase;

import java.awt.*;

public class PlayerController {
    private PlayerUseCase playerUseCase;
    private int[] playerPosition;

    public PlayerController(PlayerUseCase playerUseCase) {
        this.playerUseCase = playerUseCase;
        this.playerPosition = this.playerUseCase.getPlayer().getPosition();
    }
    public PlayerController(String name, Color color, Race race) {
        this.playerUseCase = new PlayerUseCase(name, color, race);
        this.playerPosition = this.playerUseCase.getPlayer().getPosition();
    }

    public PlayerUseCase getPlayerUseCase() {
        return playerUseCase;
    }

    public void setPlayerUseCase(PlayerUseCase playerUseCase) {
        this.playerUseCase = playerUseCase;
    }

    public int[] getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int[] playerPosition) {
        this.playerPosition = playerPosition;
    }

}
