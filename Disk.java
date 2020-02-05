import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The Disk that drops down the screen.
 * 
 * @author Yuki Tsukamoto
 * @version 12/203/2019
 */
public class Disk extends Actor
{
    public static final int DISK_SIZE = 20; // the diameter of a disk
    private double xSpeed; //speed of the disk in x direction
    private double ySpeed; //speed of the disk in y direction
    private double x; //x position
    private double y; //y position
    
    /**
     * Constructor for Disk
     */
    public Disk()
    {
        redraw();  // draw the disk
        xSpeed = 0; //initially, the speed is 0
        ySpeed = 0; //initially, the speed is 0
        x=0; //the value is zero until the position is determined
        y=0; //the value is zero until the position is determined
    }
    
    /**
     *redraw - draws a red disk for our image 
     */
    
    private void redraw()
    {
        // get the current image, and if it is not the correct
        // size, redraw it. 
        GreenfootImage img=getImage();       
        if  (img==null || 
             img.getWidth()!=DISK_SIZE || 
             img.getHeight()!=DISK_SIZE)
        {
            img = new GreenfootImage(DISK_SIZE, 
                                     DISK_SIZE);
        }
        
        // draw a red disk
        img.setColor(Color.RED);
        img.fillOval(0,0,DISK_SIZE,DISK_SIZE);
        
        // use the new image
        setImage(img);
    }
    
       
    /**
     * act - has Disk drop, bounces off pins, and be absorbed by 
     *    Absorber to get game points
     */
    public void act() 
    { 
       boolean inGame = true; //while true, the disk in the game. 
       //When false the disk should be removed
        
       //first initialization with correct x and y values
       if (x == 0 && y == 0) 
       {
           x = getX(); //correct x position
           y = getY(); //correct y position
       }
        
       //check if we touch any absorber
       //if so - add points and mark disk to remove
       List<Absorber> absorbers = getWorld().getObjects(Absorber.class);//get ibjects in Absorber class
       for (int i=0; i < absorbers.size(); i++) //i is the index of absorbers list
       { 
         //calculate the distnace between this disk and each absorber
         double distance = calculateDistance(absorbers.get(i).getX(), absorbers.get(i).getY());
         if (distance < DISK_SIZE/2 + 20) //if the disk is absorbed...
         {
            inGame =false;//this implies that the disk is removed
            List<AbsorberCount> counters = getWorld().getObjects(AbsorberCount.class);//get objects in AbsorberCount class
            int j = 0; //j is the index of counters list
            //add appropriate scores 
            while (counters.get(j).getScoreValue() != absorbers.get(i).getScoreValue())
                   j++;
                
            counters.get(j).incHits();//increment the number of hits by one
            //show the appropriate score on the board
            ((MyWorld)getWorld()).getObjects(Score.class).get(0).addTo(counters.get(j).getScoreValue());
         }//if
        }//for
       //no absorser contact, then calculate next movement
       if (inGame) 
        {
            //left and right bounds of the game field
            if (x <= DISK_SIZE/2 || x >= 400-DISK_SIZE/2) {
                xSpeed= -xSpeed;//when it hits the edge, then it should change the speed to the opposite direction
            }
            //bottom of the game field
            if (y>=600-DISK_SIZE/2) {
                inGame = false;//the disk is removed
            }
            
            //check if the disk hits any pin
            List<Pin> pins = getWorld().getObjects(Pin.class);//get the objects in Pin class
            for (int i=0; i< pins.size(); i++)//i is index of pin list
            {
                //calculate the distance between this boat and each pin
                double distance = calculateDistance(pins.get(i).getX(), pins.get(i).getY());
                if (distance < 12.6) { //when it actually hits the pin...
                    ySpeed = -0.4*ySpeed; //y speed should be multiplied by 0.4
                    double xdist = x - pins.get(i).getX(); //calculate x dis 
                    if (Math.abs(xdist) > 0.01) { //when |xdis| is big enough..
                        xSpeed = 0.2*Math.abs(xdist)/xdist; //new x speed
                    } else { //is xdis is very small,generate 0 or 1 with equal probability
                        if (Greenfoot.getRandomNumber(1) == 0) { //if it is 0...
                            xSpeed = -0.2; //new x speed
                        } else { //if it is 1...
                            xSpeed = 0.2; //new x speed
                        }
                    }
                }//if
            }
            ySpeed += 0.01;//add gravity
            x+=xSpeed; //new x by adding calculated xSpeed
            y+=ySpeed; //new y by adding calculated ySpeed
       }
       if (!inGame) 
        {
           ((MyWorld)getWorld()).removeDisk(); //if inGame is false, remove the disk
        } 
       else 
        {
            setLocation((int) x, (int) y); //apply new speeds
        }
    }
    
    /**
     * calculateDistance - calculates the distace between this disk and specified another object
     * @param x and y position of the specified object
     * @return distance between two objects, as double
     */
    private double calculateDistance(int objectX, int objectY) {
        return Math.sqrt((objectX-x)*(objectX-x)+(objectY-y)*(objectY-y)); //sqrt((X-x)^2 + (Y-y)^2)
    }
}
