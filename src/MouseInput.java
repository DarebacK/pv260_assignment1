
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

    private GameObjectHandler gameObjectHandler;
    private boolean pressright;
    private boolean pressleft;
    private boolean pressmiddle;
    
    public MouseInput(GameObjectHandler gameObjectHandler)
    {
        this.gameObjectHandler=gameObjectHandler;
    }
    
    public void mousePressed(MouseEvent e) {      
        checkDirection(e);
        
        for (int i = 0; i < gameObjectHandler.gameobjects.size(); i++) {
        GameObject tempObject=gameObjectHandler.gameobjects.get(i); 

        if (isPathContainPoint(tempObject, e.getX(), e.getY())) {
            if(pressleft && tempObject.getCurrentDirection() != Direction.RIGHT){
                tempObject.currentDirection = Direction.LEFT;
            }
            else if(pressright && tempObject.getCurrentDirection() != Direction.LEFT){
              tempObject.currentDirection = Direction.RIGHT;  
            }
            else if(pressmiddle)
            {
                if (tempObject.getCurrentDirection() != Direction.DOWN){
                tempObject.currentDirection = Direction.UP;
                }
                else if(tempObject.getCurrentDirection() != Direction.UP){
                tempObject.currentDirection = Direction.UP;
                }
            }
        }
        }
    }

    private void checkDirection(MouseEvent e) {
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
            pressleft=true;
            pressright=false;
            pressmiddle=false;
        }
        else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
            pressleft=false;
            pressright=true;
            pressmiddle=false;
        }
        else if ((e.getModifiers() & InputEvent.BUTTON2_MASK) != 0){
            pressleft=false;
            pressright=false;
            pressmiddle=true;
        }
        else{
            pressleft=false;
            pressright=false;
            pressmiddle=false;
        }
    }
    
    private boolean isPathContainPoint(GameObject tempObject, int x, int y)
    {
        boolean pathXContain=false;
        boolean pathyContain=false;
        
        for (int i = -5; i < 5; i++) {
            if(!pathXContain)
            {
                pathXContain=tempObject.getPathX().contains(x+i);
            }
            if(!pathyContain)
            {
                pathyContain=tempObject.getPathY().contains(y+i);
            }
        }
        return pathyContain && pathXContain;
    }
}