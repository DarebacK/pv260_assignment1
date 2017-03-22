
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mari
 */
public abstract class GameObject {
    protected int centreX;
    protected int centreY;
    protected Direction currentDirection;
    
    //TODO variables shouldn't be here ?????
    protected int screenHeight;
    protected int screenWidth;  
    
    //TODO this only belongs to player what if other objects also added
    protected List<Integer> pathX = new ArrayList();
    protected List<Integer> pathY = new ArrayList();
    
    GameObject(int centreX, int centreY, Direction currentDirection, int screenHeight, int screenWidth)
    {
        this.centreX=centreX;
        this.centreY=centreY;
        this.currentDirection=currentDirection;
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics2D graphics, int framesRendered);
    
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

}
