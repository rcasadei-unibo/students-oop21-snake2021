package main.java.com.view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GameStartButton extends JButton {

    private static final long serialVersionUID = -6958276029168596365L;
    private static final String G_S_PATH = "res" + System.getProperty("file.separator") + "start.png";

    public GameStartButton() {
        super(new ImageIcon(G_S_PATH));
        setPreferredSize(new Dimension(new ImageIcon(G_S_PATH).getIconWidth(), new ImageIcon(G_S_PATH).getIconHeight()));
    }

}
