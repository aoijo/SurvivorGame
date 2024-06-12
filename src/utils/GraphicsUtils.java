package utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class GraphicsUtils {
    /**
     * Draws text on the specified Graphics2D context with the given parameters.
     *
     * @param g2d    The Graphics2D context
     * @param text   The text to draw
     * @param x      The x-coordinate of the textbox
     * @param y      The y-coordinate of the textbox
     * @param width  The boundary width of the textbox, no boundary when value = -1
     * @param height The boundary height of the textbox, no boundary when value = -1
     * @param anchorPoint The point of alignment, possible inputs are "upper left", "upper center", "upper right", "center left", "center", "center right", "lower left", "lower center", "lower right"
     * @param font   The font of the text
     * @param color  The color of the text
     */
    public static void drawText(Graphics2D g2d, String text, int x, int y, int width, int height, String anchorPoint, Font font, Color color) {
        // Set font and color
        g2d.setFont(font);
        g2d.setColor(color);

        // Check for actual text dimensions
        FontMetrics metrics = g2d.getFontMetrics(font);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        // Set text boundary for width and height
        if (width < -1) {
            throw new IllegalArgumentException("Invalid width value: " + width);
        } else if(width != -1 && textWidth > width){
            textWidth = width;
        }
        if (height < -1) {
            throw new IllegalArgumentException("Invalid height value: " + height);
        } else if(height != -1 && textHeight > height){
            textHeight = height;
        }

        // Change anchor point
        int[] anchor = AnchorPoint(anchorPoint, x, y, textWidth, textHeight);
        int drawX = anchor[0];
        int drawY = anchor[1] + metrics.getAscent();

        // Draw the string
        g2d.drawString(text, drawX, drawY);
    }

    /**
     * Draws shape on the specified Graphics2D context with the given parameters.
     *
     * @param g2d    The Graphics2D context
     * @param x      The x-coordinate of the shape
     * @param y      The y-coordinate of the shape
     * @param width  The width of the shape
     * @param height The height of the shape
     * @param anchorPoint The point of alignment, possible inputs are "upper left", "upper center", "upper right", "center left", "center", "center right", "lower left", "lower center", "lower right"
     * @param edgeWidth The width of edge
     * @param fillColor The fill color of the shape, nothing if set to null
     * @param edgeColor The edge color of the shape, nothing if set to null
     */
    public static void drawRect(Graphics2D g2d, int x, int y, int width, int height, String anchorPoint, int edgeWidth, Color fillColor, Color edgeColor) {
        // Change anchor point
        int[] anchor = AnchorPoint(anchorPoint, x, y, width, height);
        int drawX = anchor[0];
        int drawY = anchor[1];

        // Change position, scale and filling color
        Rectangle2D Rect = new Rectangle2D.Double(drawX, drawY, width, height);
        g2d.setColor(fillColor);
        g2d.fill(Rect);

        // Draw edge
        g2d.setStroke(new BasicStroke(edgeWidth));
        g2d.setColor(edgeColor);
        g2d.draw(Rect);
    }

    /**
     * Convert alignment string into a 2d anchorpoint
     * @param alignment The point of alignment, possible inputs are "upper left", "upper center", "upper right", "center left", "center", "center right", "lower left", "lower center", "lower right"
     * @param x         The x-coordinate of the shape
     * @param y         The y-coordinate of the shape
     * @param width     The width of the shape
     * @param height    The height of the shape
     * @return          The modified anchor point {x,y}
     */
    private static int[] AnchorPoint(String alignment, int x, int y, int width, int height) {
        int drawX;
        int drawY;
        switch (alignment) {
            case "upper left":
                drawX = x;
                drawY = y;
                break;
            case "upper center":
                drawX = x - width / 2;
                drawY = y;
                break;
            case "upper right":
                drawX = x - width;
                drawY = y;
                break;
            case "center left":
                drawX = x;
                drawY = y- height / 2;
                break;
            case "center":
                drawX = x - width / 2;
                drawY = y - height / 2;
                break;
            case "center right":
                drawX = x - width;
                drawY = y - height / 2;
                break;
            case "lower left":
                drawX = x;
                drawY = y - height;
                break;
            case "lower center":
                drawX = x - width / 2;
                drawY = y - height;
                break;
            case "lower right":
                drawX = x - width;
                drawY = y - height;
                break;
            default:
                throw new IllegalArgumentException("Invalid alignment value: " + alignment);
        }
        return new int[]{drawX, drawY};
    }
}
