package UI.CombatPanel;

import javax.swing.*;
import java.util.ArrayList;

public class CombatLogPanel extends JPanel {
    private CombatScreen combatScreen;
    private ArrayList<String> combatLogs;

    public CombatLogPanel(CombatScreen combatScreen) {
        this.combatScreen = combatScreen;
        this.combatLogs = new ArrayList<>();


    }

    public void addCombatLog(String combatLog) {}
}
