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
    protected Direction currentDirection;
    protected Color color;
    protected List<Integer> pathX = new ArrayList();
    protected List<Integer> pathY = new ArrayList();
    
    private final int MOVE_SPEED = 5;
    
    public Player(int centreX, int centreY, Direction currentDirection, Color color) {
        super(centreX, centreY);
        this.currentDirection = currentDirection;
        this.color = color;
    }
    
    private void movePlayer(double deltaTime){        
        int moveAmount = (int) (deltaTime * MOVE_SPEED);
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
    }   

    public Color getColor() {
        return color;
    }

    private void moveUp(long moveAmount){
        centreY -= moveAmount;
    }
    
    private void moveRight(long moveAmount){
        centreX += moveAmount;
    }
    
    private void moveDown(long moveAmount) {
        centreY += moveAmount;
    }
    
    private void moveLeft(long moveAmount) {
        centreX -= moveAmount;
    }

    @Override
    public void tick(double deltaTime) {
        movePlayer(deltaTime);
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

