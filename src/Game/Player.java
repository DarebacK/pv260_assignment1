package Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents player's functionality, his direction and his color
 * @author Mari
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public class Player extends GameObject { 
    protected Direction currentDirection;
    protected Color color;

    /**
     *Variable is used to save player's X coordinates for every movement
     */
    protected List<Integer> pathX = new ArrayList();

    /**
     *Variable is used to save player's Y coordinates for every movement
     */
    protected List<Integer> pathY = new ArrayList();
    
    private static final int MOVE_SPEED = 5;
    
    public Player(int centreX, int centreY, Direction currentDirection, Color color) {
        super(centreX, centreY);
        this.currentDirection = currentDirection;
        this.color = color;
    }
    
      

    /**
     * 
     * @return Player's color
     */
    public Color getColor() {
        return color;
    }

    

    /**
     * Called by engine to signalize a tick has happened
     * @param deltaTime 
     */
    @Override
    public void tick(double deltaTime) {
        movePlayer();
        rememberPath();
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
    
    /**
     * Does not make use of deltaTime because it causes issues with Collision and rendering
     */
    private void movePlayer(){        
        int moveAmount =  MOVE_SPEED;
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
    
    private void rememberPath() {
        pathX.add(centreX);
        pathY.add(centreY);
    }  
}

