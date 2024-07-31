package InterfaceAdapter;

import Enums.BuffType;
import UseCase.Character.PlayerUseCase;

public class BuffAdapter {
    private PlayerUseCase playerUseCase;

    public BuffAdapter(UseCaseManager useCaseManager) {
        this.playerUseCase = useCaseManager.getPlayerUseCase();
    }

    public int getBuffIdByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentBuffs().get(index).getId();
    }

    public String getCurrentBuffNameByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentBuffs().get(index).getName();
    }

    public String getBuffDescriptionByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentBuffs().get(index).getDescription();
    }

    public BuffType getBuffTypeByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentBuffs().get(index).getType();
    }

    public int getCurrentBuffStackByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentBuffs().get(index).getStack();
    }

    public int getBuffMaxStackByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentBuffs().get(index).getMaxStack();
    }

    public boolean isBuffCombatByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentBuffs().get(index).isCombat();
    }

    public boolean isBuffActiveByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentBuffs().get(index).isActive();
    }
    public float getBuffTimeLeftByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentBuffs().get(index).getTimeRemain();
    }
    public boolean checkBuffExist() {
        return !playerUseCase.getPlayer().getCurrentBuffs().isEmpty();
    }
    public int getBuffTurnLeftByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentBuffs().get(index).getTurnRemain();
    }
}
