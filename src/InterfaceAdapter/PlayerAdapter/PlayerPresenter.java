package InterfaceAdapter.PlayerAdapter;

import Enums.Rarity;
import InterfaceAdapter.UseCaseManager;
import UseCase.Character.PlayerUseCase;

import java.awt.Color;

public class PlayerPresenter {
    private PlayerUseCase playerUseCase;
    private PlayerController playerController;
    private Color healthColor = new Color(255, 0, 0);
    private Color hungerColor = new Color(255, 255, 0, 128);
    private Color hydrationColor = new Color(0, 0, 255);
    private Color sanityColor = new Color(255, 0, 255, 128);

    public PlayerPresenter(UseCaseManager useCaseManager, PlayerController playerController) {
        this.playerUseCase = useCaseManager.getPlayerUseCase();
        this.playerController = playerController;
        playerController.setPlayerPresenter(this);
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
    public int getPlayerLevel(){
        return this.playerUseCase.getPlayer().getLevel();
    }
    public int getHealth(){
        return this.playerUseCase.getPlayer().getHealth();
    }
    public int getMaxHealth(){
        return this.playerUseCase.getPlayer().getMaxHealth();
    }
    public int getCurrentMaxHealth(){
        return this.playerUseCase.getPlayer().getCurrentMaxHealth();
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
    public float getWeight(){
        return this.playerUseCase.getPlayer().getWeight();
    }
    public float getMaxWeight(){
        return this.playerUseCase.getPlayer().getMaxWeight();
    }
    public int getExperience(){
        return this.playerUseCase.getPlayer().getExperience();
    }
    public int getMaxExperience(){
        return this.playerUseCase.getPlayer().getMaxExperience();
    }
    public float getCurrentMaxWeight(){
        return this.playerUseCase.getPlayer().getCurrentMaxWeight();
    }
    public int getCurrentMaxHunger(){
        return this.playerUseCase.getPlayer().getCurrentMaxHunger();
    }
    public int getCurrentMaxHydration(){
        return this.playerUseCase.getPlayer().getCurrentMaxHydration();
    }
    public PlayerUseCase getPlayerUseCase() {
        return playerUseCase;
    }
    public String getPlayerName(){
        return this.playerUseCase.getPlayer().getName();
    }
    public int getForgeEXP(){
        return this.playerUseCase.getPlayer().getForgeExperience();
    }
    public String getEquipmentName(String equipmentType) {
        return switch (equipmentType) {
            case "weapon", "Weapon" -> playerUseCase.getPlayer().getWeapon().getName();
            case "armor", "Armor" -> playerUseCase.getPlayer().getArmor().getName();
            case "tool", "Tool" -> playerUseCase.getPlayer().getTool().getName();
            case "bag", "Bag" -> playerUseCase.getPlayer().getBag().getName();
            case "amulet1", "Amulet1" -> playerUseCase.getPlayer().getAmulet()[0].getName();
            case "amulet2", "Amulet2" -> playerUseCase.getPlayer().getAmulet()[1].getName();
            case "amulet3", "Amulet3" -> playerUseCase.getPlayer().getAmulet()[2].getName();
            case "amulet4", "Amulet4" -> playerUseCase.getPlayer().getAmulet()[3].getName();
            default -> {
                System.out.println("Unexpected value: " + equipmentType);
                yield null; // Return null by default
            }
        };
    }
    public Rarity getEquippedRarity(String equipmentType){
        return switch (equipmentType){
            case "weapon","Weapon" -> playerUseCase.getPlayer().getWeapon().getRarity();
            case "armor", "Armor" -> playerUseCase.getPlayer().getArmor().getRarity();
            case "tool", "Tool" -> playerUseCase.getPlayer().getTool().getRarity();
            case "bag", "Bag" -> playerUseCase.getPlayer().getBag().getRarity();
            case "amulet1", "Amulet1" -> playerUseCase.getPlayer().getAmulet()[0].getRarity();
            case "amulet2", "Amulet2" -> playerUseCase.getPlayer().getAmulet()[1].getRarity();
            case "amulet3", "Amulet3" -> playerUseCase.getPlayer().getAmulet()[2].getRarity();
            case "amulet4", "Amulet4" -> playerUseCase.getPlayer().getAmulet()[3].getRarity();
            default -> {
                System.out.println("Unexpected value: " + equipmentType);
                yield null; // Return null by default
            }
        };
    }
    public boolean checkEquipment(String equipmentType){
        return switch (equipmentType){
            case "weapon","Weapon" -> playerUseCase.getPlayer().getWeapon() != null;
            case "armor", "Armor" -> playerUseCase.getPlayer().getArmor() != null;
            case "tool", "Tool" -> playerUseCase.getPlayer().getTool() != null;
            case "bag", "Bag" -> playerUseCase.getPlayer().getBag() != null;
            case "amulet1", "Amulet1" -> playerUseCase.getPlayer().getAmulet()[0] != null;
            case "amulet2", "Amulet2" -> playerUseCase.getPlayer().getAmulet()[1] != null;
            case "amulet3", "Amulet3" -> playerUseCase.getPlayer().getAmulet()[2] != null;
            case "amulet4", "Amulet4" -> playerUseCase.getPlayer().getAmulet()[3] != null;
            default -> {
                System.out.println("Unexpected value: " + equipmentType);
                yield false; // Return null by default
            }
        };
    }
    public int getEquipmentIndex(String equipmentType){
        return switch (equipmentType){
            case "weapon","Weapon" -> playerUseCase.getIndexByItem(playerUseCase.getPlayer().getWeapon());
            case "armor", "Armor" -> playerUseCase.getIndexByItem(playerUseCase.getPlayer().getArmor());
            case "tool", "Tool" -> playerUseCase.getIndexByItem(playerUseCase.getPlayer().getTool());
            case "bag" , "Bag" -> playerUseCase.getIndexByItem(playerUseCase.getPlayer().getBag());
            case "amulet1", "Amulet1" -> playerUseCase.getIndexByItem(playerUseCase.getPlayer().getAmulet()[0]);
            case "amulet2", "Amulet2" -> playerUseCase.getIndexByItem(playerUseCase.getPlayer().getAmulet()[1]);
            case "amulet3", "Amulet3" -> playerUseCase.getIndexByItem(playerUseCase.getPlayer().getAmulet()[2]);
            case "amulet4", "Amulet4" -> playerUseCase.getIndexByItem(playerUseCase.getPlayer().getAmulet()[3]);
            default -> {
                System.out.println("Unexpected value: " + equipmentType);
                yield 0; // Return null by default
            }
        };
    }
    public int getPlayerAmuletCount(){
        int amuletCount = 0;
        for (int i = 0; i < playerUseCase.getPlayer().getAmulet().length; i++){
            if (playerUseCase.getPlayer().getAmulet()[i] != null){
                amuletCount++;
            }
        }
        return amuletCount;
    }
    public int getSortedItemCount(){
        return playerUseCase.getSortedItems().size();
    }
    public PlayerController getPlayerController() {
        return playerController;
    }
    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }
}
