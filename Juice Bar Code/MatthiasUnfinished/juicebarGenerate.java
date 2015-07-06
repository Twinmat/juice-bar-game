//juicebar progress generator

import java.awt.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * Creates frame and set its properties.
 * 
 * @author Matthias Philippine
 */

class JuiceBarGenerate extends JPanel {

    //random seed for random difficulty juice bar
    private Random random;
    //long to record reaction time
    private long reactionTime;
    //difficulty returns the proportion used
    private String difficulty;
    
    public JuiceBarGenerate () {
        random = new Random();
        JProgressBar jpb = new JProgressBar(JProgressBar.VERTICAL);
        jpb.setUI(new MyProgressUI());
        jpb.setEnabled(true);
        jpb.setStringPainted(true);
        
        jpb.setForeground(Color.red);
        jpb.setBackground(Color.blue);
        
        jpb.setString("");

        //set difficulty here
        int value = random.nextInt(100);
        jpb.setValue(value);
        
        jpb.setBounds (500, -50, 50, 800);
        
        jpb.setIndeterminate(false);
        
        this.add(jpb);

        if (value == 0 || value == 25 || value == 50 || value == 75 || value == 100 || value == 20 || value == 80 || value == 30 || value == 60) {
            difficulty = "easy";
        }
        else if (value == 5 || value == 15 || value == 26 || value == 36 || value == 33 || value == 67 || value == 69 || value == 81 || value == 83 || value == 95) {
            difficulty = "medium";
        }
        else {
            difficulty = "hard";
        }
    }

    public long getTime() {
        return reactionTime;
    }

    public int getDifficulty() {
        return difficulty;
    }
    
    private static class MyProgressUI extends BasicProgressBarUI { }
}