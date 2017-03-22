
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mari
 */
public class  KeyInput extends KeyAdapter {
    
    private GameObjectHandler gameObjectHandler;
    
    public KeyInput(GameObjectHandler gameObjectHandler)
    {
        this.gameObjectHandler=gameObjectHandler;
    }
    
    public void keyPressed(KeyEvent e) {
        int key =e.getKeyCode();
        
        for (int i = 0; i < gameObjectHandler.gameobjects.size(); i++) {
            
            GameObject tempObject=gameObjectHandler.gameobjects.get(i);            
            //TODO this should be changed somehow
            if(i==0) //first player which is moving 
            {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (tempObject.getCurrentDirection() != Direction.DOWN){
                    tempObject.currentDirection = Direction.UP;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (tempObject.getCurrentDirection()!= Direction.UP){
                    tempObject.currentDirection = Direction.DOWN;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (tempObject.getCurrentDirection() != Direction.LEFT){
                    tempObject.currentDirection = Direction.RIGHT;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (tempObject.getCurrentDirection() != Direction.RIGHT){
                    tempObject.currentDirection = Direction.LEFT;
                    }                    
                }
            }
        }
    }
    
     public void keyReleased(KeyEvent e) {
         int key =e.getKeyCode();
     }
}
