package InterfaceAdapter;

import UseCase.PlayerUseCase;

import java.awt.Color;

public class PlayerPresenter {
    private PlayerUseCase playerUseCase;
    private Color healthColor = new Color(255, 0, 0);
    private Color hungerColor = new Color(255, 255, 0, 128);
    private Color hydrationColor = new Color(0, 0, 255);
    private Color sanityColor = new Color(255, 0, 255, 128);

    public PlayerPresenter(PlayerUseCase playerUseCase) {
        this.playerUseCase = playerUseCase;
    }

    public PlayerUseCase getPlayerUseCase() {
        return playerUseCase;
    }
    public void setPlayerUseCase(PlayerUseCase playerUseCase) {
        this.playerUseCase = playerUseCase;
    }

    public Color getHealthColor() {
        return healthColor;
    }
    public void setHealthColor(Color healthColor) {
        this.healthColor = healthColor;
    }

    public Color getHungerColor() {
        return hungerColor;
    }

    public void setHungerColor(Color hungerColor) {
        this.hungerColor = hungerColor;
    }

    public Color getHydrationColor() {
        return hydrationColor;
    }

    public void setHydrationColor(Color hydrationColor) {
        this.hydrationColor = hydrationColor;
    }

    public Color getSanityColor() {
        return sanityColor;
    }

    public void setSanityColor(Color sanityColor) {
        this.sanityColor = sanityColor;
    }

    public Color getPlayerColor(){
        return this.playerUseCase.getPlayer().getColor();
    }

    public void setPlayerColor(Color playerColor){
        this.playerUseCase.getPlayer().setColor(playerColor);
    }

    public int[] getPlayerPosition(){
        return this.playerUseCase.getPlayer().getPosition();
    }

    public int getHealth(){
        return this.playerUseCase.getPlayer().getHealth();
    }
    public int getMaxHealth(){
        return this.playerUseCase.getPlayer().getMaxHealth();
    }
    public int getHunger(){
        return this.playerUseCase.getPlayer().getHunger();
    }
    public int getMaxHunger(){
        return this.playerUseCase.getPlayer().getMaxHunger();
    }
    public int getHydration(){
        return this.playerUseCase.getPlayer().getHydration();
    }
    public int getMaxHydration(){
        return this.playerUseCase.getPlayer().getMaxHydration();
    }
    public int getSanity(){
        return this.playerUseCase.getPlayer().getSanity();
    }
    public int getMaxSanity(){
        return this.playerUseCase.getPlayer().getMaxSanity();
    }
    public int getLevel(){
        return this.playerUseCase.getPlayer().getLevel();
    }
    public int getWeight(){
        return this.playerUseCase.getPlayer().getWeight();
    }
    public int getMaxWeight(){
        return this.playerUseCase.getPlayer().getMaxWeight();
    }
    public int getExperience(){
        return this.playerUseCase.getPlayer().getExperience();
    }
    public int getMaxExperience(){
        return this.playerUseCase.getPlayer().getMaxExperience();
    }
}
