import java.awt.Canvas;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Engine {
        
    protected Window window;
    protected GameObjectHandler gameObjectHandler;    
    protected ScreenManager screenManager;
    protected Color clearGameWindowColor;
    
    private boolean running;	

    protected Engine() {
        running = false;
        
        gameObjectHandler= new GameObjectHandler();
        screenManager = new ScreenManager();
        clearGameWindowColor = Color.BLACK;
    }
	 
    protected final void stop(){
        running = false;
    }
	
    protected final void run(){
        try{
            init();
            gameLoop();
        }finally{
            screenManager.restoreScreen();
        }
    }
    
    private void init() {
        DisplayMode displayMode = screenManager.findFirstCompatibaleMode();
        screenManager.setFullScreen(displayMode);
        window = screenManager.getFullScreenWindow();
        
        initDefaultWindowGraphicSettings();
        
        onInit();
        
        running = true;
    }

    private void initDefaultWindowGraphicSettings() throws HeadlessException, IndexOutOfBoundsException {
        window.setFont(new Font("Arial",Font.PLAIN,20));
        window.setBackground(Color.WHITE);
        window.setForeground(Color.RED);
        window.setCursor(window.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null"));
    }

    private void draw(Graphics2D graphics) {
        clearGameWindow(graphics);
        
        onDraw(graphics);    
    }

    private void clearGameWindow(Graphics2D graphics) {
        graphics.setColor(clearGameWindowColor);
        graphics.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());
    }
  
    private void gameLoop(){
        long startTime = System.currentTimeMillis();
        long cumulativeTime = startTime;

        while (running){
            long timePassed = System.currentTimeMillis() - cumulativeTime;
            cumulativeTime += timePassed;
            update(timePassed);
            gameObjectHandler.tick();
            Graphics2D graphics = screenManager.getGraphics();
            draw(graphics);
            graphics.dispose();
            screenManager.update();

            try{
                Thread.sleep(20);
            }catch(Exception ex){}
        }
    }
    
    protected final int getScreenWidth(){
        return screenManager.getWidth();
    }
    
    protected final int getScreenHeight(){
        return screenManager.getHeight();
    }	
    
    protected void update(long timePassed) {}
	
    protected void onDraw(Graphics2D graphics) {}
    
    protected void onInit() {}
       
}



