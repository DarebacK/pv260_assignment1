
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
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
public class Tron extends Engine {
    
    private static final int PLAYER_FILL_WIDTH = 10;
    private static final int PLAYER_FILL_HEIGHT = 10;
    
    private int framesRendered = 0;
    
    public static void main(String[] args) {
        new Tron().run();
    }

    @Override
    protected void update(long timePassed) {
        checkPlayersOnEdges();
    }

    @Override
    protected void onDraw(Graphics2D graphics) {
        checkForCollisions();
        ++framesRendered;
        
        renderAllPlayers(graphics);

    }
    
    @Override
    protected void onInit() {
        initControls();  
        initPlayers();
    }

    private void initControls() {
        window.addKeyListener(new KeyInput(gameObjectHandler));
        window.addMouseListener(new MouseInput(gameObjectHandler));
        window.addMouseMotionListener(new MouseInput(gameObjectHandler));
    }

    private void initPlayers() {
        gameObjectHandler.addGameObject(new Player(40, 40, Direction.RIGHT,
                KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, Color.green));
        
        gameObjectHandler.addGameObject(new Player(600, 440, Direction.LEFT,
                KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A, Color.red));
        
        gameObjectHandler.addGameObject(new Player(40, 840, Direction.RIGHT,
                KeyEvent.VK_I, KeyEvent.VK_L, KeyEvent.VK_K, KeyEvent.VK_J, Color.yellow));
    }

    private void checkForCollisions() {
        List<GameObject> players = gameObjectHandler.getGameobjects();
        
        //TODO: find more efficient way
        for (int frameIndex = 0; frameIndex < framesRendered; frameIndex++){
            for (GameObject player1 : players) {
                for (GameObject player2 : players) {
                    if(playerCollidedWithPlayer(player1, player2, frameIndex)) {
                        System.out.println("Collision detected, ending the game");
                        stop();
                    }
                }
            }     
        }
    }

    private static boolean playerCollidedWithPlayer(GameObject player1, GameObject player2, int frameIndex) {
        return player1.getCentreX() == player2.getPathX().get(frameIndex) &&
                player1.getCentreY() == player2.getPathY().get(frameIndex);
    }

    private void renderAllPlayers(Graphics2D graphics) {
        List<GameObject> players = gameObjectHandler.getGameobjects();
        
        for (int i = 0; i < players.size(); i++) {
            Player currentPlayer = (Player) players.get(i);

            renderPlayer(graphics, currentPlayer);
        }
    }

    private void renderPlayer(Graphics2D graphics, Player currentPlayer) {
        for (int frameIndex = 0; frameIndex < framesRendered; frameIndex++) {
            graphics.setColor(currentPlayer.getColor());
            graphics.fillRect(currentPlayer.getPathX().get(frameIndex), currentPlayer .getPathY().get(frameIndex),
                    PLAYER_FILL_WIDTH, PLAYER_FILL_HEIGHT);
        }
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
        if(player.getCentreY() < 0){
            return false;
        }

        if(player.getCentreY() > getScreenHeight()){
            return false;
        }

        if(player.getCentreX() < 0){
            return false;
        }

        if(player.getCentreX() > getScreenWidth()){
            return false;
        }

        return true;
    }
    
    private void swingPlayerToOtherSide(GameObject player){
        if(player.getCentreX() <= 0){
            player.setCentreX(getScreenWidth());
        }
        else if(player.getCentreX() >= getScreenWidth()){
            player.setCentreX(0);
        }
        else if(player.getCentreY() <= 0){
            player.setCentreY(getScreenHeight());
        }
        else{
            player.setCentreY(0);
        }
    }
    
}
