
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
    protected int upKey;
    protected int rightKey;
    protected int downKey;
    protected int leftKey;
    protected Direction currentDirection;
    
    //TODO this only belongs to player what if other objects also added
    //yes we need to get rid of this, either move it to player or tron (player makes more sense i guess)
    protected List<Integer> pathX = new ArrayList();
    protected List<Integer> pathY = new ArrayList();
    
    GameObject(int centreX, int centreY, Direction currentDirection,
            int upKey, int rightKey, int downKey, int leftKey)
    {
        this.centreX=centreX;
        this.centreY=centreY;
        this.currentDirection=currentDirection;
        this.upKey = upKey;
        this.rightKey = rightKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
    }
    
    public abstract void tick();
    
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
