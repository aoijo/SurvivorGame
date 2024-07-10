package UseCase.Players;

import Enums.RaceType;
import Entity.Player;

public class PlayerPresets {

    public static void initialize(Player player, RaceType race) {
        switch (race) {
            case HUMAN:
                player.setMaxHealth(200);
                player.setMaxHunger(100);
                player.setMaxHydration(100);
                player.setMaxSanity(100);
                player.setMaxWeight(100);
                player.setAttack(3);
                player.setDefense(0);
                player.setLevelUpHealth(10);
                player.setLevelUpHunger(2);
                player.setLevelUpHydration(2);
                player.setLevelUpWeight(5);
                player.setLevelUpPoints(1);
                player.setLevel(1);
                player.setMaxExperience(10);
                break;
            case SLIME:
                player.setMaxHealth(250);
                player.setMaxHunger(120);
                player.setMaxHydration(120);
                player.setMaxSanity(100);
                player.setMaxWeight(20);
                player.setAttack(2);
                player.setDefense(0);
                player.setLevelUpHealth(10);
                player.setLevelUpHunger(2);
                player.setLevelUpHydration(2);
                player.setLevelUpWeight(5);
                player.setLevelUpPoints(1);
                player.setLevel(1);
                player.setMaxExperience(10);
                break;
            // Add other race initializations as needed
            default:
                throw new IllegalArgumentException("Unknown race: " + race);
        }
    }
}

