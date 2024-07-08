package UseCase;

import Entity.Player;
import Enums.Race;

import java.awt.*;

public class PlayerUseCase {
    private Player player;

    public PlayerUseCase(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        } else{
            this.player = player;
        }
        initialize(player.getRace());
        this.player.setHealth(this.player.getMaxHealth());
        this.player.setHunger(this.player.getMaxHunger());
        this.player.setHydration(this.player.getMaxHydration());
        this.player.setSanity(this.player.getMaxSanity());
    }

    public PlayerUseCase(String name, Color color, Race race) {
        if (name == null || color == null || race == null) {
            throw new IllegalArgumentException("None of the parameters can be null");
        } else {
            this.player = new Player(name, color, race);
        }
        initialize(player.getRace());
        this.player.setHealth(this.player.getMaxHealth());
        this.player.setHunger(this.player.getMaxHunger());
        this.player.setHydration(this.player.getMaxHydration());
        this.player.setSanity(this.player.getMaxSanity());
        this.player.setPosition(new int[]{0,0});
    }

    private void initialize(Race race) {
        switch (race) {
            case HUMAN:
                player.setMaxHealth(200);
                player.setMaxHunger(100);
                player.setMaxHydration(100);
                player.setMaxSanity(100);
                player.setMaxWeight(100);
                player.setBaseAttack(3);
                player.setBaseDefense(0);
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
                player.setBaseAttack(2);
                player.setBaseDefense(0);
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayerPosition(int dx, int dy) {
        int[] position = new int[]{dx, dy};
        player.setPosition(position);
    }

    public void changeHealth(int dh) {
        int health = player.getHealth() + dh;
        health = Math.max(0, Math.min(health, player.getMaxHealth()));
        player.setHealth(health);
    }

    public void changeHunger(int dh) {
        int hunger = player.getHunger() + dh;
        hunger = Math.max(0, Math.min(hunger, player.getMaxHunger()));
        player.setHunger(hunger);
    }

    public void changeHydration(int dh) {
        int hydration = player.getHydration() + dh;
        hydration = Math.max(0, Math.min(hydration, player.getMaxHydration()));
        player.setHydration(hydration);
    }

    public void changeSanity(int ds) {
        int sanity = player.getSanity() + ds;
        sanity = Math.max(0, Math.min(sanity, player.getMaxSanity()));
        player.setSanity(sanity);
    }

    public void changeExperience(int de) {
        int experience = player.getExperience() + de;
        if (experience >= player.getMaxExperience()) {
            levelUp();
        } else if (experience < 0) {
            experience = 0;
        }
        player.setExperience(experience);
    }

    public void changeBaseAttack(int da) {
        player.setBaseAttack(player.getBaseAttack() + da);
    }

    public void changeBaseDefense(int dd) {
        player.setBaseDefense(player.getBaseDefense() + dd);
    }

    private void levelUp() {
        player.setHealth(player.getHealth() + player.getLevelUpHealth());
        player.setMaxHealth(player.getMaxHealth() + player.getLevelUpHealth());
        player.setHunger(player.getHunger() + player.getLevelUpHunger());
        player.setMaxHunger(player.getMaxHunger() + player.getLevelUpHunger());
        player.setHydration(player.getHydration() + player.getLevelUpHydration());
        player.setMaxHydration(player.getMaxHydration() + player.getLevelUpHydration());

        player.setExperience(player.getExperience() - player.getMaxExperience());
        player.setLevel(player.getLevel() + 1);
        player.setMaxExperience(10 * player.getLevel() * player.getLevel());
        player.setAttributePoint(player.getAttributePoint() + player.getLevelUpPoints());
    }
}

