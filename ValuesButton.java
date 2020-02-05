import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * When the ValuesButton is clicked, it sorts the scoreboards by values
 * 
 * @author Yuki Tsukamoto
 * @version 12/03/2019
 */
public class ValuesButton extends ActionButton
{
    /**
     * Constractor for the ValuesButton
     */
    public ValuesButton() 
    {
        super("Sort by Values");//display "Sort by Values" on the board
    }

    /**
     * performAction - This method is called when the "Sort by Values"
     * button is clicked.
     */
    public void performAction() 
    {
        ((MyWorld)getWorld()).sortCountersByValue(); //call the method from MyWorld class to sort by values          
    } 
}
