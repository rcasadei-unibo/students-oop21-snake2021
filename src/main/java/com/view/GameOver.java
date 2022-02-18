package main.java.com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameOver {

    private static final String GAME_OVER = "Game Over";
    private static final String RESTART = "Restart";
    private static final String QUIT = "Quit";
    private static final String G_O_PATH = "res" + System.getProperty("file.separator") + "game_over.png";
    private static final Color TRANSPARENT = new Color(1.0f, 1.0f, 1.0f, 0.0f);

    private GameObserver observer;
    private final JFrame frame;

    public GameOver() {
        frame = new JFrame(GAME_OVER);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        final JLabel lGameOver = new JLabel(GAME_OVER, SwingConstants.CENTER);
        final JLabel lImg = new JLabel(new ImageIcon(G_O_PATH), SwingConstants.CENTER);
        frame.getContentPane().add(lImg, BorderLayout.CENTER);

        final JPanel pBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JButton bRestart = new JButton(RESTART);
        final JButton bQuit = new JButton(QUIT);
        bRestart.addActionListener(e -> {
            observer.resetGame();
            frame.setVisible(false);
        });
        bQuit.addActionListener(e -> {
            observer.quit();
        });
        pBottom.add(bRestart);
        pBottom.add(bQuit);
        pBottom.setBackground(TRANSPARENT);
        frame.getContentPane().add(pBottom, BorderLayout.SOUTH);

        frame.setUndecorated(true);
        frame.setBackground(TRANSPARENT);
        frame.setAlwaysOnTop(true);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }

    public void show() {
        frame.setVisible(true);
    }

    public void setObserver(final GameObserver obs) {
        observer = obs;
    }

}
