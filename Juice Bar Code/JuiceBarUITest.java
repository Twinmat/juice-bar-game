import java.awt.*;
import javax.swing.*;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * Creates frame and set its properties.
 * 
 * @author Alex Sharata
 */

class JuiceBarUITest extends JPanel {
    
    public JuiceBarUITest () {
        JProgressBar jpb = new JProgressBar(JProgressBar.VERTICAL);
        jpb.setUI(new MyProgressUI());
        jpb.setEnabled(true);
        jpb.setStringPainted(true);
        
        jpb.setForeground(Color.red);
        jpb.setBackground(Color.blue);
        
        jpb.setString("");
        jpb.setValue(50);
        
        jpb.setBounds (500, -50, 50, 800);
        
        jpb.setIndeterminate(false);
        
        this.add(jpb);
    }
    
    private static class MyProgressUI extends BasicProgressBarUI { }
}