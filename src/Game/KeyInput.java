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
    
    private Player player;
    
    public KeyInput(Player player)
    {
        this.player=player;
    }
    
    public void keyPressed(KeyEvent e) {
            
            Player tempPlayer= (Player) player;            
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
    
    public void keyReleased(KeyEvent e) {
         int key =e.getKeyCode();
    }
}
