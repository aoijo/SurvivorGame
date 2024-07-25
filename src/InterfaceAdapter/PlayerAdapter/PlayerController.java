package InterfaceAdapter.PlayerAdapter;

import InterfaceAdapter.UseCaseManager;
import UseCase.Character.PlayerUseCase;

public class PlayerController {
    private PlayerUseCase playerUseCase;
    private PlayerPresenter playerPresenter;
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

    public void equipByIndex(int index){
        playerUseCase.equipByIndex(playerUseCase.getPlayer(),index);
    }

    public void unEquipByIndex(int index){
        playerUseCase.unEquipByIndex(playerUseCase.getPlayer(),index);
    }

    public PlayerPresenter getPlayerPresenter() {
        return playerPresenter;
    }

    public void setPlayerPresenter(PlayerPresenter playerPresenter) {
        this.playerPresenter = playerPresenter;
    }
}
