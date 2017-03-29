package Game;

import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Controls a player by 4 keys
 * @author Mari
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public class KeyController extends KeyAdapter implements Controller {

    private final Player controlledPlayer;
    private final int upKey;
    private final int rightKey;
    private final int downKey;
    private final int leftKey;

    public KeyController(Player controlledPlayer, int upKey, int rightKey, int downKey, int leftKey) {
        this.controlledPlayer = controlledPlayer;
        this.upKey = upKey;
        this.rightKey = rightKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
    }

    /**
     *
     * @param e
     * Method is responsible for changing direction of the player on keyboard's key pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        Player tempPlayer = controlledPlayer;
        if (e.getKeyCode() == upKey) {
            if (tempPlayer.getCurrentDirection() != Direction.DOWN) {
                tempPlayer.setCurrentDirection(Direction.UP);
            }
        } else if (e.getKeyCode() == downKey) {
            if (tempPlayer.getCurrentDirection() != Direction.UP) {
                tempPlayer.setCurrentDirection(Direction.DOWN);
            }
        } else if (e.getKeyCode() == rightKey) {
            if (tempPlayer.getCurrentDirection() != Direction.LEFT) {
                tempPlayer.setCurrentDirection(Direction.RIGHT);
            }
        } else if (e.getKeyCode() == leftKey) {
            if (tempPlayer.getCurrentDirection() != Direction.RIGHT) {
                tempPlayer.setCurrentDirection(Direction.LEFT);
            }
        }
    }

    /**
     * Call this to let the controller initialize itself
     * @param window 
     */
    @Override
    public void init(Window window) {
        window.addKeyListener(this);
    }
}
