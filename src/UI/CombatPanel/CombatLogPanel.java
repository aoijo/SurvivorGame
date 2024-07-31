package UI.CombatPanel;

import InterfaceAdapter.CombatAdapter;
import Utils.DefaultScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CombatLogPanel extends JPanel {
    private CombatAdapter combatAdapter;
    private JTextArea combatLogs;
    private List<String> allLogs; // List to store all combat logs

    private String playerName;
    private String enemyName;

    public CombatLogPanel(CombatAdapter combatAdapter) {
        this.combatAdapter = combatAdapter;
        this.playerName = combatAdapter.getName(true);
        this.enemyName = combatAdapter.getName(false);
        this.allLogs = new ArrayList<>();
        setOpaque(false);

        setLayout(new CardLayout());
        setPreferredSize(new Dimension(300, 400));
        setMaximumSize(new Dimension(300, 400));
        setMinimumSize(new Dimension(300, 400));
        combatLogs = new JTextArea();
        combatLogs.setEditable(false);
        combatLogs.setLineWrap(true);
        combatLogs.setWrapStyleWord(true);
        combatLogs.setOpaque(false);
        DefaultScrollPane scrollPane = new DefaultScrollPane(combatLogs);
        add(scrollPane);

        addStringCombatLog("\n\n  " + playerName + " started combat with " + enemyName);
        addNewTurnLog();
    }

    public void addStringCombatLog(String combatLog) {
        allLogs.add(combatLog);
        combatLogs.setText("");
        for (String log : allLogs) {
            combatLogs.append("  " + log + "\n");
        }
    }
    public void addSkillCombatLog() {
        String skillCombatLog = combatAdapter.getCurrentCombatLog();
        addStringCombatLog(skillCombatLog);
    }
    public void addVictoryCombatLog() {
        String victoryCombatLog = String.format("%n%nDefeated  %s%n  %s  Gained %d experience%n  Press any skill button to exit combat",
                combatAdapter.getName(false),
                combatAdapter.getCombatUseCase().getEnemyLootDrop(),
                combatAdapter.getCombatUseCase().getEnemyEXP());
        addStringCombatLog(victoryCombatLog);
    }
    public void addNewTurnLog(){
        int turnCount = combatAdapter.getTurnCount();
        addStringCombatLog("\nTurn #" + turnCount+ "\n");
    }
    public void addEscapeFailLog(){
        addStringCombatLog("\nFailed to escape!\n");
    }
    public void addEscapeSuccessLog(){
        addStringCombatLog("\nSuccessfully escaped!\n  Press any skill button to exit combat");
    }
}
