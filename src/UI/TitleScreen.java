package UI;
import java.awt.*;
import javax.swing.*;
import utils.GraphicsUtils;

public class TitleScreen {
    public static void drawTitleScreen(Graphics2D g, int ScreenWidth, int ScreenHeight) {
        TitleBackGround(g, ScreenWidth, ScreenHeight);
        TitleTextBox(g, ScreenWidth, ScreenHeight);
        TitleText(g, ScreenWidth, ScreenHeight);
    }

    private static void TitleBackGround(Graphics g, int ScreenWidth, int ScreenHeight){
        GraphicsUtils.drawRect((Graphics2D) g,0, 0, ScreenWidth, ScreenHeight, "upper left", 1, Color.white, Color.black);
    }

    private static void TitleTextBox(Graphics g, int ScreenWidth, int ScreenHeight){
        GraphicsUtils.drawRect((Graphics2D) g,ScreenWidth/2, ScreenHeight/4, ScreenWidth/2, ScreenHeight/10, "center", 5, Color.blue, Color.black);
    }

    private static void TitleText(Graphics g, int ScreenWidth, int ScreenHeight){
        String title = "Survivor Game";
        Font titleFont = new Font("Arial", Font.PLAIN, 40);
        GraphicsUtils.drawText((Graphics2D) g, title, ScreenWidth/2, ScreenHeight/4, ScreenWidth/2, ScreenHeight/2, "center", titleFont, Color.white);
    }
}
