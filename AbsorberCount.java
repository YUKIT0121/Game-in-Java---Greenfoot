import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The statistics holder for both value and number of hits. Note
 * that each absorber with the same value should utilize the same
 * AbsorberCount object, so there can be more Absorbers than there
 * are AbsorberCount objects. 
 * 
 */
public class AbsorberCount extends Actor
{
    private int score;  // the point value of this absorber type
    private int count;  // number of times hit.
    
    /**
     * Constructor that builds a stats holder for a given point
     * value. By default, the hit count is 0.
     * @param the score value for this AbsorberCount
     */
    public AbsorberCount(int scoreValue)
    {
        score = scoreValue;
        count=0;
        redraw(); // draw the (initial) image for this AbsorberCount
    }
    
    // redraws the image for this AbsorberCount  
    private void redraw()
    {
        // get the current image and make sure it is of the correct
        // size, rebuilding if necessary.
        GreenfootImage img = getImage();
        if (img==null || 
            img.getWidth()!=200 || 
            img.getHeight()!=20)
        {
            img = new GreenfootImage(200,20);
            setImage(img);
        }
        
        // draw the box for the image and fill with value and count stats
        img.setColor(Color.WHITE);
        img.fill();
        img.setColor(Color.BLACK);
        img.drawRect(0,0,199,19); 
        img.drawString("Value:"+score, 5, 15); 
        img.drawString("Hit Count:"+count, 110,15);
    }

    /**
     * gives the current score value 
     * @return the score value
     */
    public int getScoreValue()
    {
        return score;
    }
    
    /**
     * gives the current hit count. Remember, this is the number
     *   of hits accross all Absorbers of the same point value
     * @return the hit count
     */
    public int getHitCount()
    {
        return count;
    }
    
    /**
     * increments the number of hits by one
     */
    public void incHits()
    {
        count++;
        redraw(); // changed stats, so should redraw image
    }   
    
    /**
     * resets the number of hits to be zero. 
     *   NOTE: This probably isn't needed for the project, but is
     *   included for completeness' sake here. 
     */
    public void resetHits()
    {
        count=0;
        redraw(); // changed stats, so should redraw image
    }
    
    /**
     * sets the number of hits to a new value.
     *   NOTE: This probably isn't needed for the project, but is
     *   included for completeness' sake here. 
     * @param newHitCount the number of hits to set this stat keeper 
     */
    public void setHits(int newHitCount)
    {
        count=newHitCount;
        redraw(); // changed stats, so should redraw image
    }
}
