package Game;


import Engine.GameObject;
import Engine.Engine;
import Engine.GameObjectHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public class Tron extends Engine implements CollisionListener {
    
    private static final int PLAYER_FILL_WIDTH = 10;
    private static final int PLAYER_FILL_HEIGHT = 10;
    
    private Model model;
    
    public static void main(String[] args) {
        new Tron().run();
    }

    @Override
    protected void onTick(long timePassed) {
        model.tick(timePassed);
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

    @Override
    public void onCollision() {
        System.out.println("Collision detected, ending the game");
        stop();
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
            graphics.fillRect(currentPlayer.getPathX().get(i), currentPlayer .getPathY().get(i),
                    PLAYER_FILL_WIDTH, PLAYER_FILL_HEIGHT);
        }
    }
    
    
    
}
