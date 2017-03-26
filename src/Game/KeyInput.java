package Game;


import Engine.GameObject;
import Engine.GameObjectHandler;
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
        
        for (int i = 0; i < gameObjectHandler.getGameobjects().size(); i++) {
            
            Player tempPlayer= (Player) gameObjectHandler.getGameobjects().get(i);            
            if (e.getKeyCode() == tempPlayer.upKey) {
                if (tempPlayer.getCurrentDirection() != Direction.DOWN){
                    tempPlayer.setCurrentDirection(Direction.UP);
            }
            } else if (e.getKeyCode() == tempPlayer.downKey) {
                if (tempPlayer.getCurrentDirection()!= Direction.UP){
                    tempPlayer.setCurrentDirection(Direction.DOWN);
                }
            } else if (e.getKeyCode() == tempPlayer.rightKey) {
                if (tempPlayer.getCurrentDirection() != Direction.LEFT){
                    tempPlayer.setCurrentDirection(Direction.RIGHT);
                }
            } else if (e.getKeyCode() == tempPlayer.leftKey) {
                if (tempPlayer.getCurrentDirection() != Direction.RIGHT){
                    tempPlayer.setCurrentDirection(Direction.LEFT);
                }                    
            }
        }
    }
    
    public void keyReleased(KeyEvent e) {
         int key =e.getKeyCode();
    }
}
