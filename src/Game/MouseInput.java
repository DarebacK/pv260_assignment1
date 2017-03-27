package Game;


import Engine.GameObject;
import Engine.GameObjectHandler;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mari
 */
public class MouseInput extends MouseAdapter {

    private Player player;
    
    public MouseInput(Player player)
    {
        this.player=player;
    }
    
    public void mousePressed(MouseEvent e) {        
        
        Player tempPlayer= player; 
                if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
                    tempPlayer.setCurrentDirection(tempPlayer.currentDirection.min());
                }
                else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0){
                    tempPlayer.setCurrentDirection(tempPlayer.currentDirection.add());
                }
    }
}