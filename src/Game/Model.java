/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Engine.GameObject;
import Engine.GameObjectHandler;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.Window;
import java.util.ArrayList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public class Model {
    
    private GameObjectHandler gameObjectHandler; 
    private Window window;
    
    private List<CollisionListener> collisionListeners = new ArrayList<>();

    public Model(Window window) {
        gameObjectHandler = new GameObjectHandler();
        this.window = window;
    }
    
    public void init() {
        initPlayers();
    }
    
    public void tick(double deltaTime) {
        gameObjectHandler.tick(deltaTime);
        checkPlayersOnEdges();
        checkForCollisions();
    }
    
    public List<GameObject> getGameobjects(){
        return gameObjectHandler.getGameobjects();
    }

    public void addCollisionListener(CollisionListener toAdd){
        collisionListeners.add(toAdd);
    }
    
    private void initPlayers() {
        Player player1 = new Player(40, 40, Direction.RIGHT, Color.green);
        Player player2 = new Player(600, 440, Direction.LEFT, Color.red);
        Player player3 = new Player(40, 840, Direction.RIGHT, Color.yellow);
        
        createPlayer(player1, new KeyController(player1, KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT));
        createPlayer(player2, new KeyController(player2, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A));
        //createPlayer(player3, new MouseController(player3));
    }
    
    private void createPlayer(Player player, Controller controller) {       
        controller.init(window);
        gameObjectHandler.addGameObject(player);
    }
    
    private void checkForCollisions() {
        List<Player> players = (List<Player>)(List<?>) gameObjectHandler.getGameobjects();
        
        for (Player player1 : players) {
            for (Player player2 : players) {
                if(playerCollidedWithPlayer(player1, player2)) {
                    invokeCollision();
                }
            }
        }     
    }

    private static boolean playerCollidedWithPlayer(Player player1, Player player2) {
        //TODO: issue because of deltaTime
        
        for(int i = 0; i < player2.getPathX().size() - 1; ++i) {
            if (player1.getCentreX() == player2.getPathX().get(i) &&
                player1.getCentreY() == player2.getPathY().get(i))
            {
                return true;
            }
        }
        
        return false;
    }
    
    private void checkPlayersOnEdges() {
        List<GameObject> players = gameObjectHandler.getGameobjects();
        
        for (int i = 0; i < players.size(); ++i){
            GameObject currentPlayer = players.get(i);
            
            if(!isPlayerInPlayArea(currentPlayer)) {
                swingPlayerToOtherSide(currentPlayer);
            }
        }
    }
    
    private boolean isPlayerInPlayArea(GameObject player){
        if(player.getCentreY() < 0 ||
           player.getCentreY() > window.getHeight() ||
           player.getCentreX() < 0 ||
           player.getCentreX() > window.getWidth()) 
        {
            return false;
        }

        return true;
    }
    
    private void swingPlayerToOtherSide(GameObject player){
        if(player.getCentreX() <= 0){
            player.setCentreX(window.getWidth());
        }
        else if(player.getCentreX() >= window.getWidth()){
            player.setCentreX(0);
        }
        else if(player.getCentreY() <= 0){
            player.setCentreY(window.getHeight());
        }
        else{
            player.setCentreY(0);
        }
    }
    
    private void invokeCollision(){
        for (CollisionListener listener : collisionListeners){
            listener.onCollision();
        }
    }
}
