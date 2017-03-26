package Engine;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Window;

public abstract class Engine {
        
    protected Window window;   
    protected ScreenManager screenManager;
    protected Color clearGameWindowColor;
    protected Graphics2D graphics;
    
    private boolean running;	

    private static final int SLEEP_AMOUNT_IN_MILIS = 20;
    
    protected Engine() {
        running = false;

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
            graphics.dispose();
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
        window.setFont(new Font("Arial",Font.PLAIN, 20));
        window.setBackground(Color.WHITE);
        window.setForeground(Color.RED);
        //window.setCursor(window.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null"));
        window.setCursor(Cursor.getDefaultCursor());
    }

    private void draw() {
        screenManager.update(); 
        //TODO: according to feedback Graphics2D graphics = screenManager.getGraphics() 
        //      isn't supposed to be called every iteration, can't figure how to fix it more than this
        graphics = screenManager.getGraphics();
        
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
            onTick(timePassed);
            draw();
            
            sleep();
        }
    }

    private void sleep() {
        try{
            Thread.sleep(SLEEP_AMOUNT_IN_MILIS);
        }catch(Exception ex){}
    }
    
    protected final int getScreenWidth(){
        return screenManager.getWidth();
    }
    
    protected final int getScreenHeight(){
        return screenManager.getHeight();
    }	
    
    protected void onTick(long timePassed) {}
	
    protected void onDraw(Graphics2D graphics) {}
    
    protected void onInit() {}
       
}



