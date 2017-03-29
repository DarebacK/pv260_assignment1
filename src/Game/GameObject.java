package Game;

/**
 * Abstract class describing a general GameObject in game. Has a position (centreX, centreY)
 * and receives tick calls
 * @author Mari
 * @author Pavel Morcinek (433491@mail.muni.cz)
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
