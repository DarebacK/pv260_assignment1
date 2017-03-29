package Game;

import java.awt.Window;

/**
 *
 * @author Mari
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public interface Controller {
    
    /**
     * Call this to let the controller initialize itself
     * @param window 
     */
    void init(Window window);
    
}
