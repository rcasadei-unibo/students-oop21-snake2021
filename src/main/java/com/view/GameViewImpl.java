package main.java.com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameViewImpl implements GameView {

    private static final String FRAME_NAME = "Snake Game";
    private static final String SCORE = "Score:";
    private static final String PAUSE = "Pause";
    private static final String RESET = "Reset";
    private static final String QUIT = "Quit";
    private static final Dimension WINDOW_SIZE = new Dimension(1000, 800);

    private GameObserver observer;
    private final JFrame frame = new JFrame(FRAME_NAME);
    private final MapView mapView;

    public GameViewImpl() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_SIZE);
        frame.getContentPane().setLayout(new BorderLayout());
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
        mapView = new MapView(21, 21);
        mapView.setBackground(new Color(0));
        mapView.setPreferredSize(WINDOW_SIZE);
        frame.getContentPane().add(pTop, BorderLayout.NORTH);
        frame.getContentPane().add(pBottom, BorderLayout.SOUTH);
        frame.getContentPane().add(mapView);
        frame.setLocationRelativeTo(null); // Centers the frame on the screen.
        frame.setResizable(true);
        frame.pack();
    }

    /**
     * {@inheritDoc}
     */
    public MapView getMapView() {
        return mapView;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        frame.repaint();
    }

    private boolean confirmDialog(final String question, final String name) {
        return JOptionPane.showConfirmDialog(frame, question, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

}
