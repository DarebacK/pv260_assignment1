package Game;


import Engine.GameObject;
import Engine.GameObjectHandler;
import java.awt.Window;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mari
 */
public class MouseController extends MouseAdapter implements Controller {

    private Player controlledPlayer;
    
    public MouseController(Player controlledPlayer)
    {
        this.controlledPlayer = controlledPlayer;
    }
    
    public void mousePressed(MouseEvent e) {        
        
        Player tempPlayer= controlledPlayer; 
                if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
                    tempPlayer.setCurrentDirection(tempPlayer.currentDirection.min());
                }
                else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0){
                    tempPlayer.setCurrentDirection(tempPlayer.currentDirection.add());
                }
    }

    @Override
    public void init(Window window) {
        window.addMouseListener(this);
    }
    
    
}