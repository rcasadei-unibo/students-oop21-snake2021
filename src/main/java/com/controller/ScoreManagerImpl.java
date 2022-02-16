package main.java.com.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import main.java.com.model.Model;
import main.java.com.view.GameView;

public class ScoreManagerImpl implements ScoreManager {

    private static final String SCORE = "Score: ";
    private static final String HI_SCORE = "Highscore: ";
    private static final String PATH = "highscore.txt";

    private final GameView view;
    private final Model model;
    private final File file;

    public ScoreManagerImpl(final GameView gv, final Model gm) {
       view = gv;
       model = gm;
       file = new File(PATH);
       try {
           // If the file is not already present it is created with 0 already written.
           if (file.createNewFile()) {
               try (BufferedWriter w = new BufferedWriter(new FileWriter(file))) {
                   w.write("0");
                   file.setWritable(false);
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateScore() {
        view.getScoreLabel().setText(SCORE + model.getScore());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveScore() {
        file.setWritable(true);
        if (readScore() < model.getScore()) {
            try (Writer w = new BufferedWriter(new FileWriter(file))) {
                w.write(String.valueOf(model.getScore()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        file.setWritable(false);
    }

    /**
     * Reads the file containing the highscore value.
     * @return the highscore as an int.
     */
    private int readScore() {
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            return Integer.parseInt(r.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    public void showHiScore() {
        view.getHiScoreLabel().setText(HI_SCORE + readScore());
    }

}
