
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
    int x, y;
    
    public MouseInput(GameObjectHandler gameObjectHandler)
    {
        this.gameObjectHandler=gameObjectHandler;
    }
    
    public void mousePressed(MouseEvent e) {
      x = e.getX();
      y = e.getY();
    }
    
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - x;
        int dy = e.getY() - y;
        for (int i = 0; i < gameObjectHandler.gameobjects.size(); i++) {
            
            GameObject tempObject=gameObjectHandler.gameobjects.get(i);            
            //TODO this should be changed somehow
            if(i==0) //first player which is moving 
            {
                if (tempObject.getCentreX()==x && tempObject.getCentreY()==y ) {
                    tempObject.centreX += dx;
                    tempObject.centreY += dy;
                }
            x += dx;
            y += dy;
            }
        }    
    }
}
