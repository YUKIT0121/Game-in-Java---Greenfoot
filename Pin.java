import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Pin Object for this class
 *
 * You probably do not want to modify this class, although you
 * are welcome to do so. 
 * 
 */
public class Pin extends Actor
{
    public static final int PIN_SIZE = 10; // diamater of a pin
    
    /**
     * COnstructor for a Pin - just gives the pin an image
     */
    public Pin()
    {
        redraw(); // draw the pin
    }
    
    // just draws the pin image
    private void redraw()
    {
        //adjust the image if size is not correct. 
        GreenfootImage img=getImage();        
        if  (img==null || 
             img.getWidth()!=PIN_SIZE || 
             img.getHeight()!=PIN_SIZE)
        {
            img = new GreenfootImage(PIN_SIZE, PIN_SIZE);
        }
        
        // draw gray circle in image
        img.setColor(Color.GRAY);
        img.fillOval(0,0,PIN_SIZE,PIN_SIZE);
        
        // use image just created 
        setImage(img);  
    }    
}
