//juicebar click generator

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
* the clickbar class
* 
* @author Matthias Philippine
*/
public class ClickBar {

    /**
     * X coordinate of the objective.
     */
    public int x;
    /**
     * Y coordinate of the objective ///////need?
     */
    public int y;
    /**
     * bar image.
     */
    private BufferedImage barImg;
    /**
    * long to record reaction time
    */
    private long reactionTime;
    /**
    * int for difficulty passed in
    */
    private int difficulty;
    
    public ClickBar (int diff, int x, int y, BufferedImage barImg) {
        difficulty = diff;
        this.x = x;
        this.y = y;
        this.barImg = barImg;

        //"temporary" reaction time stored to find real reaction time when bar is clicked using the initially created time
        this.reactionTime = System.nanoTime();
    }

    /**
    * Set the reaction time
    * @param time at which bar is clicked
    * @return reaction time calculated based on initial value (created time) and clicked time (input)
    */
    public long setTime (long time) {
        this.reactionTime = System.nanoTime() - this.reactionTime;
        return reactionTime;
    }

    /**
     * Draw the bar to the screen.
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(barImg, x, y, null);
    }

    /**
     * get reaction time for whatever reason if needed again since setTime
     * @return long of reactions time
     */
    public long getTime() {
        return reactionTime;
    }
}