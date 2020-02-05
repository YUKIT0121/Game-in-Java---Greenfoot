import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * When the LoadButton clicked, this sets up a FileDialog box for the user to select an input file. 
 * 
 */
public class LoadButton extends ActionButton
{
    /**
     * Constractor for the LoadButton
     */
    public LoadButton() 
    {
        super("Load"); //display "Load" on the board
    }
    
    /**
     * performAction - This method is called when the "Load"
     * button is clicked.
     */
    public void performAction() 
    {
        ((MyWorld)getWorld()).reset(); //call the method from MyWorld class to reset World (remove all specified objects)
        ((MyWorld)getWorld()).loadBoardFromFile(); //call the method from MyWorld class to load file
    }
}
