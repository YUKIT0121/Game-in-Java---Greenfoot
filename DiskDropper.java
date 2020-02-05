import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Rectangle that can move left (left arrow), right (right arrow),
 *   and/or drop disks (space bar). 
 * 
 */
public class DiskDropper extends Actor
{
    public static final int DROPPER_SIZE = 25; // dimension of image
    
    /**
     * COnstructor that simply draws the image for a disk dropper
     */
    public DiskDropper()
    {
        redraw(); //yeah, go ahead and draw the image
    }
    
    /**
     * redraw - draws gray rectangle of correct size for image
     */
    
    private void redraw()
    {
        // get current image and adjust size if needed. 
        GreenfootImage img=getImage();        
        if  (img==null || 
             img.getWidth()!=DROPPER_SIZE || 
             img.getHeight()!=DROPPER_SIZE)
        {
            img = new GreenfootImage(DROPPER_SIZE, DROPPER_SIZE);
        }
        
        // go ahead and fill image with grat.
        img.setColor(Color.GRAY);
        img.fill();
      
        // use gray box we just created. 
        setImage(img);
    }
    
    /**
     * act - allows us to move left or right one pixel at a time, stopping
     *   at edges of playing area. Also allows a new disk to be added.
     */
    public void act() 
    {   
        //it moves to left when the left key is pressed and it is not at the left edge of the world
        if (Greenfoot.isKeyDown("left") && getX() > DROPPER_SIZE/2+1) {
            move(-1);
        }
        //it moves to right when the left key is pressed and it is not at the right edge (gray area) of the world
        if (Greenfoot.isKeyDown("right") && getX() < 400 - DROPPER_SIZE/2-1) {
            move(1);
        }
        //it generates a disk when the space key is pressed
        if (Greenfoot.isKeyDown("space")) {
            ((MyWorld) getWorld()).createDisk();
        }
    }    
}
