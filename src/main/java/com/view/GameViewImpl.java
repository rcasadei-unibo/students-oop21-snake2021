package main.java.com.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
    private static final Dimension WINDOW_SIZE = new Dimension(320, 200);

    private GameObserver observer;
    private JFrame frame = new JFrame(FRAME_NAME);

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
        frame.getContentPane().add(pTop, BorderLayout.NORTH);
        frame.getContentPane().add(pBottom, BorderLayout.SOUTH);
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
