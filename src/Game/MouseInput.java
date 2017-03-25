
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
    
    public MouseInput(GameObjectHandler gameObjectHandler)
    {
        this.gameObjectHandler=gameObjectHandler;
    }
    
    public void mousePressed(MouseEvent e) {        
        
        for (int i = 0; i < gameObjectHandler.gameobjects.size(); i++) {
        GameObject tempObject=gameObjectHandler.gameobjects.get(i); 
            if (isPathContainPoint(tempObject, e.getX(), e.getY())) {
                if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
                    tempObject.currentDirection=getDirectionOnLeftButtonClick(tempObject.currentDirection);
                }
                else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0)            {
                    tempObject.currentDirection=getDirectionOnRightButtonClick(tempObject.currentDirection);
                }
            }
        }
    }
    
    private Direction getDirectionOnLeftButtonClick(Direction currentDirection)
    {
        if(currentDirection==Direction.UP)
        {            
            return Direction.LEFT;
        }
        else if(currentDirection==Direction.RIGHT)
        {            
            return Direction.UP;
        }
        else if(currentDirection==Direction.DOWN)
        {            
            return Direction.RIGHT;
        }
        else 
        {            
            return Direction.DOWN;
        }
    }
    
    private Direction getDirectionOnRightButtonClick(Direction currentDirection)
    {
        if(currentDirection==Direction.UP)
        {            
            return Direction.RIGHT;
        }
        else if(currentDirection==Direction.RIGHT)
        {            
            return Direction.DOWN;
        }
        else if(currentDirection==Direction.DOWN)
        {            
            return Direction.LEFT;
        }
        else 
        {            
            return Direction.UP;
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