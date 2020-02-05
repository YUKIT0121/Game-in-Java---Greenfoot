import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Object to hold the current game's score
 * 
 * You probably do not want to modify this class, although you
 * are welcome to do so.
 * 
 * @author Stephen Blythe
 * @version 10/2019
 */
public class Score extends Actor
{
    private int score; // the current game score
    
    /**
     * Constructor that initializes game score to 0 (all games
     *   start with a score of 0) and draws the corresponding image.
     */
    public Score()
    {
        score=0;
        redraw();
    }
    
    // Redraws scrore box image
    private void redraw()
    {
        // get current image and re-adjust size if needed
        GreenfootImage img = getImage();
        if (img==null || 
            img.getWidth()!=190 || 
            img.getHeight()!=20)
        {
            img = new GreenfootImage(100,20);
            setImage(img);
        }
        
        // draw green background for score image
        img.setColor(Color.GREEN);
        img.fill();
        
        // add current score info into image
        img.setColor(Color.BLACK);
        img.drawRect(0,0,99,19); 
        img.drawString("Total Score:"+score, 5, 15); 
    }
    
    /**
     * Adds a value to current score
     * @param increment the value to add to the current score
     */
    public void addTo(int increment)
    {
        score+=increment;
        redraw();  //adjusted the score, so should redraw image
    }
    
    /**
     * resets score value to zero. 
     */
    public void reset()
    {
        score=0;
        redraw(); //adjusted the score, so should redraw iamge
    }
  
}
