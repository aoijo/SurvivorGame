package UI.GameScreenPanels.World;

import java.awt.*;
import java.util.ArrayList;

import InterfaceAdapter.PlayerAdapter.PlayerController;
import InterfaceAdapter.PlayerAdapter.PlayerPresenter;
import InterfaceAdapter.TimeAdapter;
import UI.AdapterManager;

import javax.swing.*;

public class LogPanel extends JPanel{
    private ArrayList<String> logs;
    private String lastLog;
    private int maxLogCount = 1000;
    PlayerPresenter playerPresenter;
    PlayerController playerController;
    TimeAdapter timeAdapter;

    public LogPanel(AdapterManager adapterManager){
        setLayout(new GridLayout());
        //setPreferredSize(new Dimension(400,200));
        this.timeAdapter = adapterManager.getTimeAdapter();
        this.playerPresenter = adapterManager.getPlayerPresenter();
        this.playerController = adapterManager.getPlayerController();
        this.logs = new ArrayList<>();
    }

    private void addLog(String log){
        logs.add(log);
        removeAll();
        lastLog = log;
        add(new JLabel(lastLog));
        while (logs.size() > maxLogCount){
            logs.remove(0);
        }
    }

    public void addHarvestLog(String resourceName){
        int hour = this.timeAdapter.getHour();
        int minute = this.timeAdapter.getMinute();
        int x = this.playerController.getPlayerPosition()[0];
        int y = this.playerController.getPlayerPosition()[1];
        String harvestLog = String.format("[%02d:%02d] Harvested <" + resourceName + "> at position[%d,%d]",hour,minute,x,y);
        addLog(harvestLog);
    }

    public void addMoveLog(){
        int hour = this.timeAdapter.getHour();
        int minute = this.timeAdapter.getMinute();
        int x = this.playerController.getPlayerPosition()[0];
        int y = this.playerController.getPlayerPosition()[1];
        String MoveLog = String.format("[%02d:%02d] Moved to position[%d,%d]",hour,minute,x,y);
        addLog(MoveLog);
    }

    public void addPickUpLog(String itemName, int Quantity){
        int hour = this.timeAdapter.getHour();
        int minute = this.timeAdapter.getMinute();
        int x = this.playerController.getPlayerPosition()[0];
        int y = this.playerController.getPlayerPosition()[1];
        String harvestLog = String.format("[%02d:%02d] Picked up %d " + itemName + "at position[%d,%d]",hour,minute,Quantity,x,y);
        addLog(harvestLog);
    }

    public void addDefeatEnemyLog(String enemyName){
        int hour = this.timeAdapter.getHour();
        int minute = this.timeAdapter.getMinute();
        int x = this.playerController.getPlayerPosition()[0];
        int y = this.playerController.getPlayerPosition()[1];
        String defeatEnemyLog = String.format("[%02d:%02d] Defeated " + enemyName + "at position[%d,%d]",hour,minute,x,y);
        addLog(defeatEnemyLog);
    }
}
