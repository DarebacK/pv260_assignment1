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
public class MousePadListener implements MouseListener {

    private int mouseX, mouseY;
    private boolean clicked;
    
    @Override
    public void mouseClicked(MouseEvent me) {
        mouseX=me.getX();
        mouseY=me.getY();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        mouseClicked(me);
        clicked=true;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        clicked=false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
