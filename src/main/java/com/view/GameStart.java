package main.java.com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GameStart implements BasicWindow {

    private GameObserver observer;
    private final JFrame frame;

    public GameStart() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        final JButton start = new GameStartButton();
        start.addActionListener(e -> {
            frame.setVisible(false);
            observer.start();
        });
        frame.getContentPane().add(start, BorderLayout.CENTER);
        frame.setUndecorated(true);
        frame.setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObserver(final GameObserver obs) {
        observer = obs;
    }

}
