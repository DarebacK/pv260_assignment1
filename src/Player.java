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
    
    private int upKey;
    private int rightKey;
    private int downKey;
    private int leftKey;
    private Color color;
    
    //TODO private int framesRendered  where this variable should be   
    private static final int PLAYER_FILL_WIDTH = 10;
    private static final int PLAYER_FILL_HEIGHT = 10;
    private int moveAmount = 5;

    public Player(int startPositionX, int startPositionY, Direction currentDirection,
            int upKey, int rightKey, int downKey, int leftKey, Color color, int screenHeight, int screenWidth) {
        super(startPositionX, startPositionY, currentDirection, screenHeight, screenWidth);
        this.upKey = upKey;
        this.rightKey = rightKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.color = color;
    }
    
    
    private void movePlayer(){
        if(isPlayerInPlayArea()){
            move(moveAmount);
        }
        else {
            swingPlayerToOtherSide();
            move(0);
        }
        
    }   
    
    private boolean isPlayerInPlayArea(){
        if(this.getCentreY() < 0){
            return false;
        }

        if(this.getCentreY() > screenHeight){
            return false;
        }

        if(this.getCentreX() < 0){
            return false;
        }

        if(this.getCentreX() > screenWidth){
            return false;
        }

        return true;
    }

    private void swingPlayerToOtherSide(){
        switch(this.getCurrentDirection()) {
            case UP:
                this.setCentreY(screenHeight);
                break;

            case RIGHT:
                this.setCentreX(0);
                break;

            case DOWN:
                this.setCentreY(0);
                break;

            case LEFT:
                this.setCentreX(screenWidth);
                break;
        }
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
    }

    //TODO framesRendered???????? 
    //is it Okay?
    @Override
    public void render(Graphics2D graphics, int framesRendered) {
        for (int frameIndex = 0; frameIndex < framesRendered; frameIndex++) {
                graphics.setColor(this.getColor());
                graphics.fillRect(this.getPathX().get(frameIndex), this.getPathY().get(frameIndex),
                        PLAYER_FILL_WIDTH, PLAYER_FILL_HEIGHT);
            
        }
    }

    
}
