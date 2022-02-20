package main.java.com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GameStart implements BasicWindow {

    private static final String IMG_NAME = "/start.png";

    private GameObserver observer;
    private final JFrame frame;

    public GameStart() {
        frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        final ImageIcon imgStart = new ImageIcon(getClass().getResource(IMG_NAME));
        final JButton start = new JButton(imgStart);
        start.setPreferredSize(new Dimension(imgStart.getIconWidth(), imgStart.getIconHeight()));
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
