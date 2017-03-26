package Engine;


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
    
    public GameObject(int centreX, int centreY)
    {
        this.centreX=centreX;
        this.centreY=centreY;
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

}
