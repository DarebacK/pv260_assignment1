package Engine;


import java.awt.Graphics2D;
import java.util.LinkedList;
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
public class GameObjectHandler {
    List<GameObject> gameobjects = new LinkedList<GameObject>();      
    
    public void tick(long timePassed)
    {
        for (int i = 0; i < gameobjects.size(); i++) {
            GameObject tempObject = gameobjects.get(i);
            tempObject.tick(timePassed);
        }
    }
    
    public void addGameObject(GameObject gameObject)
    {
        this.gameobjects.add(gameObject);
    }
    
    public void removeGameObject(GameObject gameObject)
    {
        this.gameobjects.remove(gameObject);
    }

    public List<GameObject> getGameobjects() {
        return gameobjects;
    }
    
}

