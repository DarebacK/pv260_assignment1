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
    
    
    private Color color;
    
    private int moveSpeed = 5;

    public Player(int startPositionX, int startPositionY, Direction currentDirection,
            int upKey, int rightKey, int downKey, int leftKey, Color color) {
        super(startPositionX, startPositionY, currentDirection, upKey, rightKey, downKey, leftKey);
     
        this.color = color;
    }
    
    private void movePlayer(){
        move(moveSpeed);
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

    public void tick() {
        movePlayer();
        rememberPath();
    }   
    
    private void rememberPath() {
        pathX.add(centreX);
        pathY.add(centreY);
    }   
}

