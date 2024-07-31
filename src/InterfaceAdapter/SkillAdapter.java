package InterfaceAdapter;

import Enums.Rarity;
import Enums.SkillType;
import UseCase.Character.PlayerUseCase;

public class SkillAdapter {
    private PlayerUseCase playerUseCase;

    public SkillAdapter(UseCaseManager useCaseManager) {
        this.playerUseCase = useCaseManager.getPlayerUseCase();
    }

    public int getPlayerSkillIdByIndex(int index){
        return playerUseCase.getPlayer().getSkills().get(index).getId();
    }

    public String getPlayerCurrentSkillNameByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentSkills().get(index).getName();
    }

    public String getPlayerSkillDescriptionByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getDescription();
    }

    public float getPlayerSkillPowerLevelByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getPowerLevel();
    }

    public int getPlayerCurrentSkillCooldownByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentSkills().get(index).getCooldown();
    }

    public float getPlayerCurrentSkillWorldCooldownByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentSkills().get(index).getWorldCooldown();
    }

    public int getPlayerSkillMaxCooldownByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getMaxCooldown();
    }

    public int getPlayerSkillMaxWorldCooldownByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getMaxWorldCooldown();
    }

    public int getPlayerSkillUsageByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getUsage();
    }

    public boolean isPlayerSkillGeneralByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).isGeneral();
    }

    public boolean isPlayerSkillCombatByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).isCombat();
    }

    public int[] getPlayerSkillRaceRequirementByIndex(int index) {
        return playerUseCase.getPlayer().getSkills().get(index).getRaceRequirement();
    }

    public Rarity getPlayerCurrentSkillRarityByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentSkills().get(index).getRarity();
    }
    public boolean getPlayerCurrentSkillIsActiveByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentSkills().get(index).isActive();
    }
    public SkillType[] getPlayerCurrentSkillTypeByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentSkills().get(index).getSkillType();
    }
    public int getPlayerCurrentSkillDurabilityByIndex(int index) {
        return playerUseCase.getPlayer().getCurrentSkills().get(index).getDurability();
    }
}
