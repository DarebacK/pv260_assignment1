package Engine;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Window;

/**
 * Game engine class responsible for managing gameLoop, draw and tick calls
 * @author Mari
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public abstract class Engine {

    protected Window window;
    protected ScreenManager screenManager;
    protected Color clearGameWindowColor;
    protected Graphics2D graphics;

    private boolean running;

    private static final int TARGET_FPS = 60;
    private static final long OPTIMAL_TIME_IN_MILIS = 1000 / TARGET_FPS;

    protected Engine() {
        running = false;
        screenManager = new ScreenManager();
        clearGameWindowColor = Color.BLACK;
    }

    /**
     *Method is responsible for interrupt gameLoop
     */
    protected final void stop() {
        running = false;
    }

    /**
     *Method is responsible for initialization of screen, starting gameLoop  
     */
    protected final void run() {
        try {
            init();
            gameLoop();
        } finally {
            graphics.dispose();
            screenManager.restoreScreen();
        }
    }

    /**
     *
     * @param deltaTime
     * This method will be used in child class to update player
     */
    protected void onTick(double deltaTime) {
    }

    /**
     *
     * @param graphics
     * This method will be used in child class to draw player
     */
    protected void onDraw(Graphics2D graphics) {
    }

    /**
     * This method will be used in child class to initialization players
     */
    protected void onInit() {
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
        window.setFont(new Font("Arial", Font.PLAIN, 20));
        window.setBackground(Color.WHITE);
        window.setForeground(Color.RED);
    }

    private void draw() {
        graphics = screenManager.getGraphics();

        clearGameWindow(graphics);
        onDraw(graphics);
        screenManager.update();
    }

    private void clearGameWindow(Graphics2D graphics) {
        graphics.setColor(clearGameWindowColor);
        graphics.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());
    }

    /**
     * main game loop, implements variable timestep, runs until stop() has been called
     */
    private void gameLoop() {
        long startTime = System.currentTimeMillis();
        long cumulativeTime = startTime;

        while (running) {
            long loopBeginTime = System.currentTimeMillis();
            long timePassed = loopBeginTime - cumulativeTime;
            double deltaTime = (double) timePassed / OPTIMAL_TIME_IN_MILIS;
            cumulativeTime += timePassed;

            onTick(deltaTime);
            draw();

            sleep(loopBeginTime - System.currentTimeMillis() + OPTIMAL_TIME_IN_MILIS);
        }
    }

    private void sleep(long timePassed) {
        try {
            Thread.sleep(timePassed);
        } catch (Exception ex) {
        }
    }

    

}
