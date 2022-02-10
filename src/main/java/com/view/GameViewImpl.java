package main.java.com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameViewImpl implements GameView {

    private static final String FRAME_NAME = "Snake Game";
    private static final String SCORE = "Score:";
    private static final String PAUSE = "Pause";
    private static final String RESET = "Reset";
    private static final String QUIT = "Quit";
    private static final Dimension WINDOW_SIZE = new Dimension(5 * 320, 5 * 200);

    private GameObserver observer;
    private JFrame frame = new JFrame(FRAME_NAME);
    private MapView mapView = new MapView(21, 21);

    public GameViewImpl() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_SIZE);
        frame.getContentPane().add(new JPanel(new BorderLayout()));
        final JPanel pTop = new JPanel(new FlowLayout());
        final JLabel lScore = new JLabel(SCORE);
        pTop.add(lScore);
        final JPanel pBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton bPause = new JButton(PAUSE);
        final JButton bReset = new JButton(RESET);
        final JButton bQuit = new JButton(QUIT);
        pBottom.add(bPause);
        pBottom.add(bReset);
        pBottom.add(bQuit);
        bPause.addActionListener(e -> observer.pauseGame());
        bReset.addActionListener(e -> {
            if (confirmDialog("Confirm resetting?", "Reset")) {
                observer.resetGame();
            }
        });
        bQuit.addActionListener(e -> {
            if (confirmDialog("Confirm quitting?", "Quit")) {
                observer.quit();
            }
        });
        //final JPanel pMain = new JPanel();
        //pMain.setBackground(new Color(0));
        mapView.setBackground(new Color(0));
        frame.getContentPane().add(pTop, BorderLayout.NORTH);
        frame.getContentPane().add(pBottom, BorderLayout.SOUTH);
        frame.getContentPane().add(mapView, BorderLayout.CENTER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObserver(final GameObserver observer) {
        this.observer = observer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.frame.setVisible(true);
    }

    @Override
    public void updateView() {
        // TODO Auto-generated method stub

    }

    private boolean confirmDialog(final String question, final String name) {
        return JOptionPane.showConfirmDialog(frame, question, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

}
