import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The "scoring" absorber for project 3. This class just provides
 * the abiity to construct and utilize an Absorber. 
 * 
 */
public class Absorber extends Actor
{
    private int scoreValue; // the value of this Absorber
    
    /**
     * Constructor for an Absorber. 
     * @param value - the score value for this Absorber
     */
    public Absorber(int value)
    {
        scoreValue = value;
        redraw(); // draw the Absorber
    }
    
    // draws the image for an absorber and uses it
    private void redraw()
    {
        // build a new image of correct size
        GreenfootImage img = new GreenfootImage(40,40);
        
        // draw the absorber as a green ball with score text in it
        img.setColor(Color.GREEN);
        img.fillOval(0,0,40,40);
        img.setColor(Color.BLACK);
        img.drawString(""+scoreValue, 12,25);
        
        // use the image just created and drawn
        setImage(img);
    }
    
    /**
     * getScoreValue - gets the value of this absorber object
     * @return the value of this Absorber
     */
    public int getScoreValue()
    {
        return scoreValue;
    }
   
}
