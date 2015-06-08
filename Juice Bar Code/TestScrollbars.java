import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicProgressBarUI;


public class TestScrollbars {
    
    protected void initUI() {
        final JFrame frame = new JFrame();
        frame.setTitle(TestScrollbars.class.getSimpleName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //timer interval
        //final static int interval = 1000;
        
        ////////////////////////////////////
        JProgressBar bar = new JProgressBar(JProgressBar.VERTICAL);
        bar.setUI(new MyProgressUI());
        bar.setEnabled(true);
        bar.setStringPainted(true);

        bar.setBackground(Color.BLUE);
        bar.setForeground(Color.RED);
        
        bar.setStringPainted(false);
        //bar.setString("");
        
        bar.setValue(75);
        ////////////////////////////////////
        /*
        // Changing value with timer...
        Timer timer = new Timer(interval);
        timer.start();
        
        timer = new Timer(interval, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (i == 20){
                    Toolkit.getDefaultToolkit().beep();
                    timer.stop();
                    button.setEnabled(true);
                    pb.setValue(0);
                    
                }
                i = i + 1;
                pb.setValue(i);
            }
        });
         */
        
        frame.setLayout(new BorderLayout());
        frame.add(bar, BorderLayout.CENTER);
        frame.setSize(100, 400);
        frame.setVisible(true);
    }
    
    // Custom UI Delegate
    // NOTE: When running on Mac, this prevents default Aqua UI bar from appearing
    private static class MyProgressUI extends BasicProgressBarUI {
        
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestScrollbars().initUI();
            }
        });
    }
    
}