package Engine;

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

    /**
     *player's X coordinates 
     */
    protected int centreX;

    /**
     *player's Y coordinates 
     */
    protected int centreY;
    
    public GameObject(int centreX, int centreY)
    {
        this.centreX=centreX;
        this.centreY=centreY;
    }
    
    /**
     * This method should be override in child class 
     * This method will update player
     * @param deltaTime
     */
    public abstract void tick(double deltaTime);
    
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

}
