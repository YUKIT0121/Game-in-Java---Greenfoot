import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * When the CountButton is clicked, it sorts the scoreboards by counts
 * 
 */
public class CountButton extends ActionButton
{
    /**
     * Constractor for the CountButton
     */
    public CountButton() 
    {
        super("Sort by Count"); //display "Sort by Count" on the board
    }
    
    /**
     * performAction - This method is called when the "Sort by Count"
     * button is clicked.
     */
    
    public void performAction() 
    {
         ((MyWorld)getWorld()).sortCounterByCount(); //call the method from MyWorld class to sort by counts      
    }
}
