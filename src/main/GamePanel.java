package main;

import javax.swing.*;
import java.awt.*;
import UI.TitleScreen;

public class GamePanel extends JPanel implements Runnable {

    int screenWidth = 800;
    int screenHeight = 600;
    final int FPS = 60;
    long sleeptime = 1000L / FPS;

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(null);
        this.setDoubleBuffered(true);
    }

    @Override
    public void run() {

        while(gameThread != null) {
            update();

            repaint();

            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void endGameThread() {
        gameThread = null;
    }

    public void update() {
        updateScreenDimension();
    }

    private void updateScreenDimension(){

        screenWidth = getWidth();
        screenHeight = getHeight();

        if (screenWidth > screenHeight * 2) {
            screenWidth = screenHeight * 2;
        } else if (screenHeight > screenWidth) {
            screenHeight = screenWidth;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        TitleScreen.drawTitleScreen(g2d, screenWidth, screenHeight);
    }
}
