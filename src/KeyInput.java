
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
        
        for (int i = 0; i < gameObjectHandler.gameobjects.size(); i++) {
            
            GameObject tempObject=gameObjectHandler.gameobjects.get(i);            
            if (e.getKeyCode() == tempObject.upKey) {
                if (tempObject.getCurrentDirection() != Direction.DOWN){
                tempObject.currentDirection = Direction.UP;
            }
            } else if (e.getKeyCode() == tempObject.downKey) {
                if (tempObject.getCurrentDirection()!= Direction.UP){
                tempObject.currentDirection = Direction.DOWN;
                }
            } else if (e.getKeyCode() == tempObject.rightKey) {
                if (tempObject.getCurrentDirection() != Direction.LEFT){
                tempObject.currentDirection = Direction.RIGHT;
                }
            } else if (e.getKeyCode() == tempObject.leftKey) {
                if (tempObject.getCurrentDirection() != Direction.RIGHT){
                tempObject.currentDirection = Direction.LEFT;
                }                    
            }
        }
    }
    
    public void keyReleased(KeyEvent e) {
         int key =e.getKeyCode();
    }
}
