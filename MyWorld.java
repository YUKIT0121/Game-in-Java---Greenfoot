import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.FileDialog;
import java.io.*;
import java.util.*;


/**
 * World for the "plinko like" game where disks are dropped from
 * the top of the world , hit pins and bounce off of them, and
 * are "absorbed" by scoring units. 
 * 
 * @author Yuki Tsukamoto
 * @version 12/03/2019
 */
public class MyWorld extends World
{ 
    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld()
    {    
        // Create a new world with 600x600 cells with a cell size of 1x1 pixels.
        super(600, 600, 1);  
        
        // draw an area to place the game score, buttons, and hit counts
        getBackground().setColor(Color.LIGHT_GRAY);
        getBackground().fillRect(400,0,600,600);
        
        addObject(new Score(), 500, 15); //add green score board
        addObject(new CountButton(), 500, 40); //add "Sort by Count" button
        addObject(new ValuesButton(), 500, 65); //add "Sort by Value" button
        addObject(new LoadButton(), 500, 90); //add "Load" button
        addObject(new DiskDropper(), 200, 10); //add a disk dropper
        
    }
    /**
     * reset - resets scores and removes all objects in Absorber, AbsorberCount and Pin class
     */
    public void reset() {
        removeObjects(getObjects(Absorber.class));//remove objects in Absorber class
        removeObjects(getObjects(AbsorberCount.class));//remove objects in AbsorberCount class
        removeObjects(getObjects(Pin.class));//remove objects in Pin class
        getObjects(Score.class).get(0).reset();//reset all scores
    }
    
    /**
     * loadBoardFromFile - loads pins and absorbers from file
     */
   
    public void loadBoardFromFile()
    {
        // create dialog to choose the input file.
        FileDialog fd = null;
        //call a user prompt to choose an input fila
        fd = new FileDialog(fd, "Choose a Data File", FileDialog.LOAD); 
        //make the file visible
        fd.setVisible(true);
            
        // gets input file information.
        String filename = fd.getFile(); //the "what"
        String pathname = fd.getDirectory(); //the "where"
        String fullname = pathname + filename; //place + name
        
        // input file gets selected .
        File myFile = new File(fullname);
            
        //create scanner
        Scanner myReader = null;
        try 
        {   
            myReader = new Scanner(myFile);
        } 
        // if such input could not open or does not exist, 
        // deals with exception by printing the catch condition and quits the method.
        catch (FileNotFoundException e) 
        {
            System.out.println("File is not found: " + fullname);
            return;
        } 
        int counterYPosition = 130;//y posision of the top counter board
        // reads the input file and executes the following...
        while(myReader.hasNext()) 
        {
            String pinOrAbsorder = myReader.next();
            //if the string is "pin"...
            if(pinOrAbsorder.equalsIgnoreCase("pin"))
            {
              int x = myReader.nextInt(); //x position of the pin
              int y = myReader.nextInt(); //y position of the pin
              
              //add pin at the specified location
              addObject(new Pin(), x, y);
            }
            List<AbsorberCount> absorberCounter = getObjects(AbsorberCount.class);
            //if the string is "tractor"...
            if(pinOrAbsorder.equalsIgnoreCase("absorber"))
            {
              int point = myReader.nextInt();
              int x = myReader.nextInt(); //x position of the source
              int y = myReader.nextInt(); //y position of the source
              //add source a the specified location
              addObject(new Absorber(point), x, y);
              
              boolean counterAlreadyExists = false;//boolean to check if the counter boards already exist
              for (int i=0;i<absorberCounter.size();i++) 
              {
                  if (absorberCounter.get(i).getScoreValue() == point) 
                  {
                     counterAlreadyExists = true; //the board for specified value was already added
                  }
              }
              if (!counterAlreadyExists) 
              {
                  addObject(new AbsorberCount(point), 500, counterYPosition); //add new AbsorberCount object
                  counterYPosition+=20; //add 20 to the Y position to determine the y pos of the new board
              }
            }// if absorber
         }//while
       
    }
    
    /**
     * sortCounterByValue - sorts counter boards by values
     */
    public void sortCountersByValue() 
    {
     List<AbsorberCount> absorberCounters = getObjects(AbsorberCount.class);//get objects in absorberCounters class
     int numberOfBoard = absorberCounters.size(); //check how many count board is in the World
     AbsorberCount boards[] = new AbsorberCount[numberOfBoard]; //create a new array of AbsorberCount objects
     for(int i = 0; i < numberOfBoard; i++)
     {
       boards[i] = getObjects(AbsorberCount.class).get(i); //create an array with the current order of the boards
     }
     
     boolean swapMade;//boolean to check if the swap is made
        
     do
     {
        swapMade=false;//swap is initially false
        for(int currIndex=0; currIndex < absorberCounters.size()-1; currIndex++)
        {
          int first;//the first score value
          int second; //the second score value
           
          first = boards[currIndex].getScoreValue();//the score value of the upper board
          second = boards[currIndex + 1].getScoreValue();//the score value of the lower board
          if (first > second)//if the score of the upper board is bigger than the lower one...
          {
             AbsorberCount abs = boards[currIndex]; //store the first object as a variable
             boards[currIndex] = boards[currIndex + 1]; //move the second object to the first one's initial index position
             boards[currIndex + 1] = abs; //move the first object to the second one's initial index position
             swapMade=true; //swap is made
          }
        }
     }
     while (swapMade);//swap is continued unitl the correct order is made
     
     int counterYPosition = 130;//y position of the top counter board
     for (int i = 0; i < boards.length; i++) 
     {
        boards[i].setLocation(500, counterYPosition);//move the specified board to the correct location
        counterYPosition+=20;//as the index increases, the y position also increases by 20
     }
    }
   
    
    /**
     * sortCounterByCount - sorts counter boards by counts 
     */
    public void sortCounterByCount()
    {
     List<AbsorberCount> absorberCounters = getObjects(AbsorberCount.class);//get objects in absorberCounters class
     int numberOfBoard = absorberCounters.size(); //check how many count board is in the World
     AbsorberCount boards[] = new AbsorberCount[numberOfBoard]; //create a new array of AbsorberCount objects
     for(int i = 0; i < numberOfBoard; i++)
     {
       boards[i] = getObjects(AbsorberCount.class).get(i); //create an array with the current order of the boards
     }
     
     boolean swapMade;//boolean to check if the swap is made
        
     do
     {
        swapMade=false;//swap is initially false
        for(int currIndex=0; currIndex < absorberCounters.size()-1; currIndex++)
        {
          int first;//the first score value
          int second; //the second score value
           
          first = boards[currIndex].getHitCount();//the hit count of the upper board
          second = boards[currIndex + 1].getHitCount();//the hit count of the lower board
          if (first < second)//if the score of the upper board is less than the lower one...
          {
             AbsorberCount abs = boards[currIndex]; //store the first object as a variable
             boards[currIndex] = boards[currIndex + 1]; //move the second object to the first one's initial index position
             boards[currIndex + 1] = abs; //move the first object to the second one's initial index position
             swapMade=true; //swap is made
          }
        }
     }
     while (swapMade);//swap is continued unitl the correct order is made
     
     int counterYPosition = 130;//y position of the top counter board
     for (int i = 0; i < boards.length; i++) 
     {
        boards[i].setLocation(500, counterYPosition);//move the specified board to the correct location
        counterYPosition+=20;//as the index increases, the y position also increases by 20
     }
    }        
    

    /**
     * createDisk - generates(drop) a disk 
     */
    public void createDisk() 
    {
        if (getObjects(Disk.class).isEmpty()) //if the disk currently does not exist
        {
            DiskDropper dd = getObjects(DiskDropper.class).get(0);//get the DiskDropper object
            addObject(new Disk(), dd.getX(), DiskDropper.DROPPER_SIZE);//drop a Disk from the DiskDropper
        }
    }
    
    
    /**
     *removeDisk - removes a disk 
     */
    public void removeDisk() 
    {
        if (!getObjects(Disk.class).isEmpty()) //if the disk currently exists
        {
            removeObject(getObjects(Disk.class).get(0));//remove the Disk from the World
        }
    }
 }
