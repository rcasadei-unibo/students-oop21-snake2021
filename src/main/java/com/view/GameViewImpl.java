package main.java.com.view;

import java.awt.BorderLayout;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameViewImpl implements GameView {

    private static final String FRAME_NAME = "Snake Game";
    private static final String SCORE = "Score: 0";
    private static final String HI_SCORE = "Highscore: ";
    private static final String PAUSE = "Pause";
    private static final String RESET = "Reset";
    private static final String QUIT = "Quit";
    private static final int FONT_SIZE = 21;
    private static final String FONT_NAME = "Tahoma";
    private static final Dimension WINDOW_SIZE = new Dimension(1000, 800);

    private GameObserver observer;
    private final JFrame frame;
    private final JLabel lScore;
    private final JLabel lHiScore;
    private final MapView mapView;

    public GameViewImpl(final int xMapSize, final int yMapSize) {
        frame = new JFrame(FRAME_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_SIZE);
        frame.getContentPane().setLayout(new BorderLayout());

        final JPanel pTop = new JPanel(new FlowLayout());
        lScore = new JLabel(SCORE);
        lHiScore = new JLabel(HI_SCORE);
        lScore.setForeground(Color.WHITE);
        lHiScore.setForeground(Color.WHITE);
        lScore.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        lHiScore.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        pTop.setBackground(Color.BLACK);
        pTop.add(lScore);
        pTop.add(lHiScore);

        mapView = new MapView(xMapSize, yMapSize);
        mapView.setBackground(Color.BLACK);
        mapView.setPreferredSize(WINDOW_SIZE);
        mapView.setFocusable(true);

        final JPanel pBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton bPause = new JButton(PAUSE);
        final JButton bReset = new JButton(RESET);
        final JButton bQuit = new JButton(QUIT);
        final Set<JButton> btns = new HashSet<>(Stream.of(bReset, bPause, bQuit).collect(Collectors.toSet()));
        pBottom.setBackground(Color.BLACK);
        btns.stream().forEach(btn -> {
            btn.setBackground(Color.WHITE);
            btn.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
            pBottom.add(btn);
        });
        bPause.addActionListener(e -> observer.pauseGame());
        bReset.addActionListener(e -> {
            observer.pauseGame();
            if (confirmDialog("Confirm resetting?", "Reset")) {
                observer.resetGame();
            } else {
                mapView.requestFocusInWindow();
                observer.pauseGame();
            }
        });
        bQuit.addActionListener(e -> {
            observer.pauseGame();
            if (confirmDialog("Confirm quitting?", "Quit")) {
                observer.quit();
            } else {
                mapView.requestFocusInWindow();
                observer.pauseGame();
            }
        });

        frame.getContentPane().add(pTop, BorderLayout.NORTH);
        frame.getContentPane().add(pBottom, BorderLayout.SOUTH);
        frame.getContentPane().add(mapView);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null); // Centers the frame on the screen.
        frame.pack();
        mapView.requestFocusInWindow();
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
    public JFrame getFrame() {
        return frame;
    }

    /**
     * {@inheritDoc}
     */
    public JLabel getScoreLabel() {
        return lScore;
    }

    /**
     * {@inheritDoc}
     */
    public JLabel getHiScoreLabel() {
        return lHiScore;
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
