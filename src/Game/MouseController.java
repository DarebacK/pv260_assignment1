package Game;

import java.awt.Window;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controls a player by mouse buttons
 * @author Mari
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public class MouseController extends MouseAdapter implements Controller {

    private final Player controlledPlayer;

    public MouseController(Player controlledPlayer) {
        this.controlledPlayer = controlledPlayer;
    }

    /**
     *
     * @param e
     * Method is responsible for changing player's direction on mouse left/right button pressed
     */
    @Override
    public void mousePressed(MouseEvent e) {

        Player tempPlayer = controlledPlayer;
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
            tempPlayer.setCurrentDirection(tempPlayer.currentDirection.GetDirectionForLeftButton());
        } else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
            tempPlayer.setCurrentDirection(tempPlayer.currentDirection.GetDirectionForRightButton());
        }
    }

    /**
     * Call this to let the controller initialize itself
     * @param window 
     */
    @Override
    public void init(Window window) {
        window.addMouseListener(this);
    }

}
