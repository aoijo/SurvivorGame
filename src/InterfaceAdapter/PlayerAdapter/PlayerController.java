package InterfaceAdapter.PlayerAdapter;

import InterfaceAdapter.UseCaseManager;
import UseCase.PlayerUseCase;

public class PlayerController {
    private PlayerUseCase playerUseCase;
    private int[] playerPosition;

    public PlayerController(UseCaseManager useCaseManager) {
        this.playerUseCase = useCaseManager.getPlayerUseCase();
        this.playerPosition = this.playerUseCase.getPlayer().getPosition();
    }

    public int[] getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int[] playerPosition) {
        this.playerPosition = playerPosition;
    }

    public PlayerUseCase getPlayerUseCase(){
        return playerUseCase;
    }

}
