import java.awt.Canvas;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class yourclass {
        
    private Window window;
    //private ArrayList<Player> players = new ArrayList();
    //private int framesRendered = 0;
    //private int moveAmount = 5;
    private boolean running;
	
    private GameObjectHandler gameObjectHandler;
    
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
        
        //maybe it is better to create in constuctor
        gameObjectHandler=new GameObjectHandler();
        
        screenManager = new ScreenManager();
        DisplayMode displayMode = screenManager.findFirstCompatibaleMode();
        screenManager.setFullScreen(displayMode);
        window = screenManager.getFullScreenWindow();
        window.setFont(new Font("Arial",Font.PLAIN,20));
        window.setBackground(Color.WHITE);
        window.setForeground(Color.RED);
        window.setCursor(window.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null")); 
        window.addKeyListener(new KeyInput(gameObjectHandler));
        running = true;

        
        gameObjectHandler.addGameObject(new Player(40, 40, Direction.RIGHT,
        KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, Color.green, screenManager.getHeight(), screenManager.getWidth()));
        
        gameObjectHandler.addGameObject(new Player(600, 440, Direction.LEFT, 
        KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A, Color.red, screenManager.getHeight(), screenManager.getWidth()));
        
        gameObjectHandler.addGameObject(new Player(40, 840, Direction.RIGHT, 
        KeyEvent.VK_I, KeyEvent.VK_L, KeyEvent.VK_K, KeyEvent.VK_J, Color.yellow, screenManager.getHeight(), screenManager.getWidth()));
    }

    public static void main(String[] args) {
        new yourclass().run();
    }

    public void draw(Graphics2D graphics) {
        gameObjectHandler.tick();
        //movePlayers();

        if(gameObjectHandler.checkForCollisions())
        {
            System.out.println("Collision detected, ending the game");
            System.exit(0);
        }
        
        clearGameWindow(graphics);
        //renderPlayers(graphics);      
        
        gameObjectHandler.render(graphics);      
        
    }

    private void clearGameWindow(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());
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



