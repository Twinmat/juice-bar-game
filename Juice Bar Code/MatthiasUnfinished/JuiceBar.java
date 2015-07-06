//package shoot_the_duck;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * The duck class.
 * 
 */

public class JuiceBar {
    
    /**
     * How much time must pass in order to create a new bar?
     */
    public static long timeBetween = Framework.secInNanosec / 2;
    /**
     * Last time when the bar was created.
     */
    public static long lastTime = 0;
    
    /*
     * Duck lines.
     * Where is starting location for the duck?
     * Speed of the duck?
     * How many points is a duck worth?
    public static int[][] duckLines = {
                                       {Framework.frameWidth, (int)(Framework.frameHeight * 0.60), -2, 20},
                                       {Framework.frameWidth, (int)(Framework.frameHeight * 0.65), -3, 30},
                                       {Framework.frameWidth, (int)(Framework.frameHeight * 0.70), -4, 40},
                                       {Framework.frameWidth, (int)(Framework.frameHeight * 0.78), -5, 50}
                                      };

    /**
     * Indicate which is next duck line.
     */
    // public static int nextDuckLines = 0;
    
    
    /**
     * X coordinate of the duck.
     */
    public int x;
    /**
     * Y coordinate of the duck.
     */
    public int y;
    
    /**
     * How many points accumulated?
     */
    public int score;
    
    /**
     * Juice image.
     */
    private BufferedImage juiceImg;
    
    
    /**
     * Creates new duck.
     * 
     * @param x Starting x coordinate.
     * @param y Starting y coordinate.
     * @param speed The speed of this duck.
     * @param score How many points this duck is worth?
     * @param duckImg Image of the duck.
     */
    public JuiceGame(int x, int y, int speed, int score, BufferedImage duckImg)
    {
        this.x = x;
        this.y = y;
        
        this.speed = speed;
        
        this.score = score;
        
        this.juiceImg = juiceImg;        
    }
    
    
    /**
     * Check the mouse position, need?
     */
    public void Update()
    {
        x += speed;
    }
    
    /**
     * Draw the duck to the screen.
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(juiceImg, x, y, null);
    }
}
