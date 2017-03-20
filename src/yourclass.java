import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class yourclass  {
    
    private static final int PLAYER_FILL_WIDTH = 10;
    private static final int PLAYER_FILL_HEIGHT = 10;
    
    private Window window;
    private ArrayList<Player> players = new ArrayList();
    private int framesRendered = 0;
    private int moveAmount = 5;
    private boolean running;
	
    
    protected ScreenManager screenManager;
	
    public void stop(){
        running = false;
    }
	
    public void run(){
        try{
            init();
            gameLoop();
        }finally{
            screenManager.restoreScreen();
        }
    }
	

    public void init() {
         screenManager = new ScreenManager();
            DisplayMode displayMode = screenManager.findFirstCompatibaleMode();
            screenManager.setFullScreen(displayMode);
            window = screenManager.getFullScreenWindow();
            window.setFont(new Font("Arial",Font.PLAIN,20));
            window.setBackground(Color.WHITE);
            window.setForeground(Color.RED);
            window.setCursor(window.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null")); 
            running = true;

        //window = screenManager.getFullScreenWindow();
        
        createNewPlayer(new Player(40, 40, Direction.RIGHT,
        KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, Color.green));
        
        createNewPlayer(new Player(600, 440, Direction.LEFT, 
        KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A, Color.red));
        
        createNewPlayer(new Player(40, 840, Direction.RIGHT, 
        KeyEvent.VK_I, KeyEvent.VK_L, KeyEvent.VK_K, KeyEvent.VK_J, Color.yellow));
    }

    public static void main(String[] args) {
        new yourclass().run();
    }

    public void draw(Graphics2D graphics) {
        movePlayers();

        checkForCollisions();
        
        clearGameWindow(graphics);
        renderPlayers(graphics);
        
        ++framesRendered;
    }

    private void clearGameWindow(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());
    }

    private void renderPlayers(Graphics2D graphics) {
        for (int frameIndex = 0;frameIndex < framesRendered; frameIndex++) {
            for(Player player : players) {
                graphics.setColor(player.getColor());
                graphics.fillRect(player.getPathX().get(frameIndex), player.getPathY().get(frameIndex),
                        PLAYER_FILL_WIDTH, PLAYER_FILL_HEIGHT);
            }
        }
    }

    private void movePlayers() {
        for(Player player : players) {
            movePlayer(player, moveAmount);
        }
    }
        
    private void checkForCollisions() {
        for (int frameIndex = 0; frameIndex < framesRendered; frameIndex++){    
             for (Player player1 : players) {              
                 for (Player player2 : players) {
                     if(player1.getCentreX() == player2.getPathX().get(frameIndex) && 
                             player1.getCentreY() == player2.getPathY().get(frameIndex)) {
                         System.out.println("Collision detected, ending the game");
                         System.exit(0);
                     }
                 }
             }
        }
    }
        
    private void movePlayer(Player player, int moveAmount){
        if(isPlayerInPlayArea(player)){
            player.move(moveAmount);
        }
        else {
            swingPlayerToOtherSide(player);
            player.move(0);
        }
        
    }

    private boolean isPlayerInPlayArea(Player player){
        if(player.getCentreY() < 0){
            return false;
        }

        if(player.getCentreY() > screenManager.getHeight()){
            return false;
        }

        if(player.getCentreX() < 0){
            return false;
        }

        if(player.getCentreX() > screenManager.getWidth()){
            return false;
        }

        return true;
    }

    private void swingPlayerToOtherSide(Player player){
        switch(player.getCurrentDirection()) {
            case UP:
                player.setCentreY(screenManager.getHeight());
                break;

            case RIGHT:
                player.setCentreX(0);
                break;

            case DOWN:
                player.setCentreY(0);
                break;

            case LEFT:
                player.setCentreX(screenManager.getWidth());
                break;
        }
    }
    
    private Player createNewPlayer(Player player) {
        window.addKeyListener(player);
        players.add(player);
        
        return player;
    }
    
    public void gameLoop(){
            long startTime = System.currentTimeMillis();
            long cumTime = startTime;

            while (running){
                long timePassed = System.currentTimeMillis()-cumTime;
                cumTime+= timePassed;
                update(timePassed);
                Graphics2D graphics = screenManager.getGraphics();
                draw(graphics);
                graphics.dispose();
                screenManager.update();

                try{
                    Thread.sleep(20);
                }catch(Exception ex){}
            }
	}
	
	public void update(long timePassed){}
	
}
