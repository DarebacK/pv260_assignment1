package Game;

import java.awt.Window;
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
public class KeyController extends KeyAdapter implements Controller {

    private Player controlledPlayer;
    private int upKey;
    private int rightKey;
    private int downKey;
    private int leftKey;

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

    @Override
    public void init(Window window) {
        window.addKeyListener(this);
    }
}
