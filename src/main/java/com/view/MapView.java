package main.java.com.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class MapView extends JPanel {

    private static final int CELL_SIZE = 32; // Hard coded, should be changed to be responsive to the window size.

    private final int xMapSize;
    private final int ymapSize;

    public MapView(final int x, final int y) {
        xMapSize = x;
        ymapSize = y;
    }

    /**
     * @param g
     */
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255));
        for (int i = 0; i <= xMapSize; i++) {
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, ymapSize * CELL_SIZE);
            if (i <= xMapSize) {
                g.drawLine(0, i * CELL_SIZE, xMapSize * CELL_SIZE, i * CELL_SIZE);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void render(final Graphics g) {
        g.setColor(new Color(255));
        for (int i = 0; i <= xMapSize; i++) {
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, ymapSize * CELL_SIZE);
            if (i <= xMapSize) {
                g.drawLine(0, i * CELL_SIZE, xMapSize * CELL_SIZE, i * CELL_SIZE);
            }
        }
    }

}
