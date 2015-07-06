//package shoot_the_duck;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Actual game.
 * 
 */

public class Game {
    
    /**
     * We use this to generate a random number.
     */
    private Random random;

    /**
     * We use this to generate a beginning bar.
     */
    private boolean bar1;

    /**
     * We use this to determine which trial and what difficulty to use.
     */
    private int trialNum;
    
    /**
     * Font that we will use to write statistic to the screen.
     */
    private Font font;
    
    /**
     * Array list of the ducks.
     */
    //private ArrayList<Duck> ducks;

    /**
     * Array list of the bars, temporary for bar, to be replaced with rectangles.
     */
    private ArrayList<ClickBar> temp;
    
    /**
     * How many ducks leave the screen alive?
     */
    //private int runawayDucks;
    
   /**
     * How many ducks the player killed?
     */
    //private int killedDucks;
    
    /**
     * For each correct proportion, the player gets points.
     */
    private int score;
    
   /**
     * How many bars a player has gone through?
     */
    private int barsHit; //replaced shoots
    
    /**
     * Last time of the shoot.
     */
    private long lastTimeClick;    
    /**
     * The time which must elapse between shots.
     */
    private long timeBetweenClick;

    /**
     * Game background image.
     */
    private BufferedImage backgroundImg;
    
    /**
     * Character image.
     */
    private BufferedImage character; //replaced grassImg
    
    /**
     * Juice Bar image.
     */
    private BufferedImage barImg; //replaced duckImg
    
    /**
     * Cursor image.
     */
    private BufferedImage sightImg;
    
    /**
     * Middle width of the sight image.
     */
    private int sightImgMiddleWidth;
    /**
     * Middle height of the sight image.
     */
    private int sightImgMiddleHeight;
    

    public Game()
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Sets variables and objects for the game.
                Initialize();
                // Load game files (images, sounds, ...)
                LoadContent();
                
                Framework.gameState = Framework.GameState.PLAYING;
            }
        };
        threadForInitGame.start();
    }
    
    
   /**
     * Set variables and objects for the game.
     */
    private void Initialize()
    {
        random = new Random();        
        font = new Font("monospaced", Font.BOLD, 18);
        
        //ducks = new ArrayList<Duck>();
        temp = new ArrayList<ClickBar>();
        
        //runawayDucks = 0;
        //killedDucks = 0;
        score = 0;
        barsHit = 0;
        bar1 = false;
        trialNum = 1;
        
        lastTimeClick = 0;
        //wait 1 second before click again
        timeBetweenClick = Framework.milisecInNanosec * 1000000L;
    }
    
    /**
     * Load game files - images, sounds, ...
     */
    private void LoadContent()
    {
        try
        {
            //URL backgroundImgUrl = this.getClass().getResource("/shoot_the_duck/resources/images/background.jpg");
            URL backgroundImgUrl = this.getClass().getResource("./resources/images/background.jpg");
            backgroundImg = ImageIO.read(backgroundImgUrl);
            
            //URL grassImgUrl = this.getClass().getResource("/shoot_the_duck/resources/images/grass.png");
            //URL grassImgUrl = this.getClass().getResource("./resources/images/grass.png");
            //grassImg = ImageIO.read(grassImgUrl);

            //replace grass with character for juice bar game////////todo

            //URL duckImgUrl = this.getClass().getResource("/shoot_the_duck/resources/images/duck.png");
            //URL duckImgUrl = this.getClass().getResource("./resources/images/duck.png");
            //duckImg = ImageIO.read(duckImgUrl);

            //todo
            //replace with juice bar rectangle, empty rectangle and 1 colored square multiplied by 100 for loop
            URL barImgUrl = this.getClass().getResource("./resources/images/bar.png");
            barImg = ImageIO.read(barImgUrl);
            
            //URL sightImgUrl = this.getClass().getResource("/shoot_the_duck/resources/images/sight.png");
            URL sightImgUrl = this.getClass().getResource("./resources/images/sight.png");
            sightImg = ImageIO.read(sightImgUrl);
            sightImgMiddleWidth = sightImg.getWidth() / 2;
            sightImgMiddleHeight = sightImg.getHeight() / 2;
        }
        catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Restart game - reset some variables.
     */
    public void RestartGame()
    {
        // Removes all of the ducks from this list.
        //ducks.clear();
        
        // We set last duckt time to zero.
        //Duck.lastDuckTime = 0;
        
       // runawayDucks = 0;
       // killedDucks = 0;
        score = 0;
        barsHit = 0;
        bar1 = false;
        trialNum = 1;
        
        lastTimeClick = 0;
    }
    
    
    /**
     * Update game logic.
     * 
     * @param gameTime gameTime of the game.
     * @param mousePosition current mouse position.
     */
    public void UpdateGame(long gameTime, Point mousePosition)
    {

        //add first bar, use boolean to check if need to add new one
        //todo change random number seed with trial check and difficulty seeding
        if (!bar1) {
            temp.add(new ClickBar(random.nextInt(), 0, 0, barImg));
            bar1 = true;
            //notes:
            //left off here : generate new bar using boolean, set to true after making (see below and constructor)
            //need to add rectangles for zones and 100 colorized blocks to match the "difficulty" (proportion chosen)
        }

        /* Duck updates, do not need for juicebar game
        // Creates a new duck, if it's the time, and add it to the array list.
        if(System.nanoTime() - Duck.lastDuckTime >= Duck.timeBetweenDucks)
        {
            
            // Here we create new duck and add it to the array list.
            ducks.add(new Duck(Duck.duckLines[Duck.nextDuckLines][0] + random.nextInt(200), Duck.duckLines[Duck.nextDuckLines][1], Duck.duckLines[Duck.nextDuckLines][2], Duck.duckLines[Duck.nextDuckLines][3], duckImg));
            
            // Here we increase nextDuckLines so that next duck will be created in next line.
            Duck.nextDuckLines++;
            if(Duck.nextDuckLines >= Duck.duckLines.length)
                Duck.nextDuckLines = 0;
            
            Duck.lastDuckTime = System.nanoTime();
        }
        
        // Update all of the ducks.
        for(int i = 0; i < ducks.size(); i++)
        {
            // Move the duck.
            ducks.get(i).Update();
            
            // Checks if the duck leaves the screen and remove it if it does.
            if(ducks.get(i).x < 0 - duckImg.getWidth())
            {
                ducks.remove(i);
                runawayDucks++;
            }
        }   */
        
        // Does player click?
        if(Canvas.mouseButtonState(MouseEvent.BUTTON1))
        {
            // Checks if it can shoot again.
            if(System.nanoTime() - lastTimeClick >= timeBetweenClick)
            {
                
                // We go over all the ducks and we look if any of them was shoot.
                //todo replace with check for rectangles and juice bar
                //for(int i = 0; i < ducks.size(); i++)
                {
                    // We check, if the mouse was over ducks head or body, when player has shot.
                   //if(new Rectangle(ducks.get(i).x + 18, ducks.get(i).y     , 27, 30).contains(mousePosition) ||
                     //  new Rectangle(ducks.get(i).x + 30, ducks.get(i).y + 30, 88, 25).contains(mousePosition))
                    if (new Rectangle(temp.get(0).x + 30, temp.get(0).y + 30, 88, 25).contains(mousePosition))
                    {
                        //killedDucks++;
                        //score += ducks.get(i).score;
                        barsHit++;
                        
                        // Remove the duck from the array list.
                        //replaced with deactivate target zones of rectangles in array
                        //todo
                        //ducks.remove(i);
                        
                        // We found the duck that player shoot so we can leave the for loop.
                        //todo put break back once loop is implemented for rectangles
                        //break;
                    }
                }
                
                lastTimeClick = System.nanoTime();
            }
        }
        
        // When 200 ducks runaway, the game ends.
        //todo chanage win condition
        //if(runawayDucks >= 200)
          //  Framework.gameState = Framework.GameState.GAMEOVER;
    }
    
    /**
     * Draw the game to the screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition current mouse position.
     */
    public void Draw(Graphics2D g2d, Point mousePosition)
    {
        g2d.drawImage(backgroundImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        
        // Here we draw all the ducks.
        //for(int i = 0; i < ducks.size(); i++)
        //todo repalce with colored rectangles in array that correspond to "difficulty"
        {
            //ducks.get(i).Draw(g2d);
        }

        temp.get(0).Draw(g2d);
        
        //g2d.drawImage(grassImg, 0, Framework.frameHeight - grassImg.getHeight(), Framework.frameWidth, grassImg.getHeight(), null);
        
        g2d.drawImage(sightImg, mousePosition.x - sightImgMiddleWidth, mousePosition.y - sightImgMiddleHeight, null);
        
        g2d.setFont(font);
        g2d.setColor(Color.darkGray);
        
        //g2d.drawString("RUNAWAY: " + runawayDucks, 10, 21);
        //g2d.drawString("KILLS: " + killedDucks, 160, 21);
        g2d.drawString("BARS: " + barsHit, 299, 21);
        //g2d.drawString("SCORE: " + score, 440, 21);
        
    }
    
    
    /**
     * Draw the game over screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition Current mouse position.
     */
    public void DrawGameOver(Graphics2D g2d, Point mousePosition)
    {
        Draw(g2d, mousePosition);
        
        // The first text is used for shade.
        g2d.setColor(Color.black);
        g2d.drawString("Game Over", Framework.frameWidth / 2 - 39, (int)(Framework.frameHeight * 0.65) + 1);
        g2d.drawString("Press space or enter to restart.", Framework.frameWidth / 2 - 149, (int)(Framework.frameHeight * 0.70) + 1);
        g2d.setColor(Color.red);
        g2d.drawString("Game Over", Framework.frameWidth / 2 - 40, (int)(Framework.frameHeight * 0.65));
        g2d.drawString("Press space or enter to restart.", Framework.frameWidth / 2 - 150, (int)(Framework.frameHeight * 0.70));
    }
}
