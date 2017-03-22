
import java.awt.Graphics2D;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mari
 */
public class GameObjectHandler {
    LinkedList<GameObject> gameobjects=new LinkedList<GameObject>();      
    private int framesRendered = 0;   
    
    public void tick()
    {
        for (int i = 0; i < gameobjects.size(); i++) {
            GameObject tempObject=gameobjects.get(i);
            tempObject.tick();
        }
    }
    
    public void render(Graphics2D graphics)
    {
        for (int i = 0; i < gameobjects.size(); i++) {
            GameObject tempObject=gameobjects.get(i);
            tempObject.render(graphics, framesRendered);
        }   
        
        ++framesRendered;
    }    
    
    //TODO it should be changed 
    public boolean checkForCollisions()
    {
        for (int frameIndex = 0; frameIndex < framesRendered; frameIndex++){    
            for (GameObject player1 : gameobjects) {              
                 for (GameObject player2 : gameobjects) {
                   if(player1.getCentreX() == player2.getPathX().get(frameIndex) && 
                          player1.getCentreY() == player2.getPathY().get(frameIndex)) {
                         return true;
                    }
               }              
            }     
        }
        return false;
    }
    
    public void addGameObject(GameObject gameObject)
    {
        this.gameobjects.add(gameObject);
    }
    
    public void removeGameObject(GameObject gameObject)
    {
        this.gameobjects.remove(gameObject);
    }
    
}

