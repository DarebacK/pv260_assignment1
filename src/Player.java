import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public class Player implements KeyListener, MouseListener {
    private int centreX;
    private int centreY;
    private Direction currentDirection;
    private List<Integer> pathX = new ArrayList();
    private List<Integer> pathY = new ArrayList();
    private int upKey;
    private int rightKey;
    private int downKey;
    private int leftKey;
    private Color color;

    public Player(int startPositionX, int startPositionY, Direction currentDirection,
            int upKey, int rightKey, int downKey, int leftKey, Color color) {
        this.centreX = startPositionX;
        this.centreY = startPositionY;
        this.currentDirection = currentDirection;
        this.upKey = upKey;
        this.rightKey = rightKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.color = color;
    }
    
    public void move(int moveAmount){
        switch(currentDirection){
            case UP: 
                moveUp(moveAmount);
                break;
                
            case RIGHT:
                moveRight(moveAmount);
                break;
                
            case DOWN:
                moveDown(moveAmount);
                break;
                
            case LEFT:
                moveLeft(moveAmount);
                break;
        }
        
        pathX.add(centreX);
        pathY.add(centreY);
    }
    
    public void setCentreX(int centreX) {
        this.centreX = centreX;
    }

    public void setCentreY(int centreY) {
        this.centreY = centreY;
    }

    public int getCentreX() {
        return centreX;
    }

    public int getCentreY() {
        return centreY;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public List<Integer> getPathX() {
        return pathX;
    }

    public List<Integer> getPathY() {
        return pathY;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == upKey) {
            if (getCurrentDirection() != Direction.DOWN){
                currentDirection = Direction.UP;
            }
        } else if (e.getKeyCode() == downKey) {
            if (getCurrentDirection() != Direction.UP){
                currentDirection = Direction.DOWN;
            }
        } else if (e.getKeyCode() == rightKey) {
            if (getCurrentDirection() != Direction.LEFT){
                currentDirection = Direction.RIGHT;
            }
        } else if (e.getKeyCode() == leftKey) {
            if (getCurrentDirection() != Direction.RIGHT){
                currentDirection = Direction.LEFT;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    private void moveUp(int moveAmount){
        centreY -= moveAmount;
    }
    
    private void moveRight(int moveAmount){
        centreX += moveAmount;
    }
    
    private void moveDown(int moveAmount) {
        centreY += moveAmount;
    }
    
    private void moveLeft(int moveAmount) {
        centreX -= moveAmount;
    }
    
}
