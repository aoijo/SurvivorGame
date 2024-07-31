package Utils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class DefaultScrollPane extends JScrollPane {

    public DefaultScrollPane(JComponent view) {
        super(view);

        // Apply custom scroll bar UI
        getVerticalScrollBar().setUI(new CustomScrollBarUI());
        getVerticalScrollBar().setUnitIncrement(10);
        getHorizontalScrollBar().setUI(new CustomScrollBarUI());

        // Remove border to prevent any white line
        setBorder(BorderFactory.createEmptyBorder());

        // Setting corners to null or empty components
        setCorner(JScrollPane.UPPER_LEFT_CORNER, null);
        setCorner(JScrollPane.UPPER_RIGHT_CORNER, null);
        setCorner(JScrollPane.LOWER_LEFT_CORNER, null);
        setCorner(JScrollPane.LOWER_RIGHT_CORNER, null);

        // Set background to transparent
        setOpaque(false);
        view.setOpaque(false);

        setWheelScrollingEnabled(true);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add custom mouse wheel listener to override scroll speed
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int scrollAmount = e.getScrollAmount();
                int scrollDirection = e.getWheelRotation();
                int unitIncrement = getVerticalScrollBar().getUnitIncrement();
                getVerticalScrollBar().setValue(getVerticalScrollBar().getValue() + scrollDirection * scrollAmount * unitIncrement);
            }
        });

        // Revalidate and repaint to ensure proper rendering
        revalidate();
        repaint();
    }

    // Custom Scroll Bar UI class
    private static class CustomScrollBarUI extends BasicScrollBarUI {

        private static final Color SCROLL_BAR_COLOR = Color.LIGHT_GRAY;
        private static final int SCROLL_BAR_WIDTH = 10;

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            return button;
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            g.setColor(SCROLL_BAR_COLOR);
            g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
        }

        @Override
        public Dimension getPreferredSize(JComponent c) {
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                return new Dimension(SCROLL_BAR_WIDTH, super.getPreferredSize(c).height);
            } else {
                return new Dimension(super.getPreferredSize(c).width, SCROLL_BAR_WIDTH);
            }
        }

        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = SCROLL_BAR_COLOR;
        }
    }
}
