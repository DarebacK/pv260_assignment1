package Game;

import java.util.LinkedList;
import java.util.List;

/**
 * Manages game objects
 * @author Mari
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public class GameObjectHandler {

    List<GameObject> gameobjects = new LinkedList<>();

    /**
     * Calls tick methods on all game objects
     * @param deltaTime 
     */
    public void tick(double deltaTime) {
        for (int i = 0; i < gameobjects.size(); i++) {
            GameObject tempObject = gameobjects.get(i);
            tempObject.tick(deltaTime);
        }
    }

    public void addGameObject(GameObject gameObject) {
        this.gameobjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        this.gameobjects.remove(gameObject);
    }

    public List<GameObject> getGameobjects() {
        return gameobjects;
    }

}
