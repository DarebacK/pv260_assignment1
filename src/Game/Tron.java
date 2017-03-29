package Game;

import Engine.Engine;
import java.awt.Graphics2D;
import java.util.List;

/**
 * Presentation part of the game
 * @author Mari
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public class Tron extends Engine implements CollisionListener {

    private static final int PLAYER_FILL_WIDTH = 10;
    private static final int PLAYER_FILL_HEIGHT = 10;

    private Model model;

    /**
     * Main method of the game
     * @param args 
     */
    public static void main(String[] args) {
        new Tron().run();
    }

    /**
     * Implementing method of CollisionListener interface, ends the game when called
     */
    @Override
    public void onCollision() {
        System.out.println("Collision detected, ending the game");
        stop();
    }
    
    @Override
    protected void onTick(double deltaTime) {
        model.tick(deltaTime);
    }

    @Override
    protected void onDraw(Graphics2D graphics) {
        renderAllPlayers(graphics);
    }

    @Override
    protected void onInit() {
        model = new Model(window);
        model.init();
        model.addCollisionListener(this);
    }

    private void renderAllPlayers(Graphics2D graphics) {
        List<GameObject> players = model.getGameobjects();

        for (int i = 0; i < players.size(); i++) {
            Player currentPlayer = (Player) players.get(i);

            renderPlayer(graphics, currentPlayer);
        }
    }

    private void renderPlayer(Graphics2D graphics, Player currentPlayer) {
        for (int i = 0; i < currentPlayer.getPathX().size(); i++) {
            graphics.setColor(currentPlayer.getColor());
            graphics.fillRect(currentPlayer.getPathX().get(i), currentPlayer.getPathY().get(i),
                    PLAYER_FILL_WIDTH, PLAYER_FILL_HEIGHT);
        }
    }

}
