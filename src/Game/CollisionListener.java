package Game;

/**
 * Implement this interface and call addCollisionListener on the model class to
 * receive collision events
 * @author Mari
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public interface CollisionListener {
    
    /**
     * Collision callback
     */
    void onCollision();
}
