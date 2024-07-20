package InterfaceAdapter.MapAdapter;

import Enums.BuffType;
import InterfaceAdapter.UseCaseManager;
import UseCase.PlayerUseCase;

public class BuffAdapter {
    private PlayerUseCase playerUseCase;

    public BuffAdapter(UseCaseManager useCaseManager) {
        this.playerUseCase = useCaseManager.getPlayerUseCase();
    }

    public int getBuffIdByIndex(int index) {
        return playerUseCase.getPlayer().getBuffs().get(index).getId();
    }

    public String getBuffNameByIndex(int index) {
        return playerUseCase.getPlayer().getBuffs().get(index).getName();
    }

    public String getBuffDescriptionByIndex(int index) {
        return playerUseCase.getPlayer().getBuffs().get(index).getDescription();
    }

    public BuffType getBuffTypeByIndex(int index) {
        return playerUseCase.getPlayer().getBuffs().get(index).getType();
    }

    public int getBuffStackByIndex(int index) {
        return playerUseCase.getPlayer().getBuffs().get(index).getStack();
    }

    public int getBuffMaxStackByIndex(int index) {
        return playerUseCase.getPlayer().getBuffs().get(index).getMaxStack();
    }

    public boolean isBuffCombatByIndex(int index) {
        return playerUseCase.getPlayer().getBuffs().get(index).isCombat();
    }

    public boolean isBuffActiveByIndex(int index) {
        return playerUseCase.getPlayer().getBuffs().get(index).isActive();
    }
}
