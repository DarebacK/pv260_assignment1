package Game;

import Engine.GameObject;
import java.awt.Color;
import java.awt.Graphics2D;
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
public class Player extends GameObject {
    
    protected int upKey;
    protected int rightKey;
    protected int downKey;
    protected int leftKey;
    
    protected Direction currentDirection;
    protected Color color;
    protected List<Integer> pathX = new ArrayList();
    protected List<Integer> pathY = new ArrayList();
    
    private final int MOVE_SPEED = 5;
    
    public Player(int centreX, int centreY, Direction currentDirection,
            int upKey, int rightKey, int downKey, int leftKey, Color color) {
        super(centreX, centreY);
        this.upKey = upKey;
        this.rightKey = rightKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.currentDirection = currentDirection;
        this.color = color;
    }
    
    
    
    private void movePlayer(){
        switch(currentDirection){
            case UP: 
                moveUp(MOVE_SPEED);
                break;
                
            case RIGHT:
                moveRight(MOVE_SPEED);
                break;
                
            case DOWN:
                moveDown(MOVE_SPEED);
                break;
                
            case LEFT:
                moveLeft(MOVE_SPEED);
                break;
        }
    }   

    public Color getColor() {
        return color;
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

    @Override
    public void tick() {
        movePlayer();
        rememberPath();
    }   
    
    private void rememberPath() {
        pathX.add(centreX);
        pathY.add(centreY);
    }   

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
    
    public List<Integer> getPathX() {
        return pathX;
    }

    public List<Integer> getPathY() {
        return pathY;
    }
    
}

