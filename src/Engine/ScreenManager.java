package Engine;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * Class responsible for managing game graphics(screen),
 * already implemented in task assignment
 * @author Mari
 * @author Pavel Morcinek (433491@mail.muni.cz)
 */
public class ScreenManager {

    private static final DisplayMode modes[]
            = {
                //new DisplayMode(1920,1080,32,0),
                new DisplayMode(1680, 1050, 32, 0),
                //new DisplayMode(1280,1024,32,0),
                new DisplayMode(800, 600, 32, 0),
                new DisplayMode(800, 600, 24, 0),
                new DisplayMode(800, 600, 16, 0),
                new DisplayMode(640, 480, 32, 0),
                new DisplayMode(640, 480, 24, 0),
                new DisplayMode(640, 480, 16, 0),};

    private GraphicsDevice graphicsDevice;

    /**
     *
     */
    public ScreenManager() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
    }

    /**
     *
     * @return
     */
    public DisplayMode[] getCompatibleDisplayModes() {
        return graphicsDevice.getDisplayModes();
    }

    /**
     *
     * @return
     */
    public DisplayMode findFirstCompatibaleMode() {

        DisplayMode goodModes[] = graphicsDevice.getDisplayModes();
        for (DisplayMode mode : modes) {
            for (DisplayMode goodMode : goodModes) {
                if (displayModesMatch(mode, goodMode)) {
                    return mode;
                }
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public DisplayMode getCurrentDM() {
        return graphicsDevice.getDisplayMode();
    }

    /**
     *
     * @param displayMode1
     * @param displayMode2
     * @return
     */
    public boolean displayModesMatch(DisplayMode displayMode1, DisplayMode displayMode2) {
        if (displayMode1.getWidth() != displayMode2.getWidth() || displayMode1.getHeight() != displayMode2.getHeight()) {
            return false;
        }
        if (displayMode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && displayMode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && displayMode1.getBitDepth() != displayMode2.getBitDepth()) {
            return false;
        }
        if (displayMode1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && displayMode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && displayMode1.getRefreshRate() != displayMode2.getRefreshRate()) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param displayMode
     */
    public void setFullScreen(DisplayMode displayMode) {
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setIgnoreRepaint(true);
        frame.setResizable(false);
        graphicsDevice.setFullScreenWindow(frame);

        if (displayMode != null && graphicsDevice.isDisplayChangeSupported()) {
            try {
                graphicsDevice.setDisplayMode(displayMode);
            } catch (Exception ex) {
            }
            frame.createBufferStrategy(2);
        }
    }

    /**
     *
     * @return
     */
    public Graphics2D getGraphics() {
        Window window = graphicsDevice.getFullScreenWindow();
        if (window != null) {
            BufferStrategy bs = window.getBufferStrategy();
            return (Graphics2D) bs.getDrawGraphics();
        } else {
            return null;
        }
    }

    /**
     *Method is used to update screen after player update
     */
    public void update() {
        Window window = graphicsDevice.getFullScreenWindow();
        if (window != null) {
            BufferStrategy bs = window.getBufferStrategy();
            if (!bs.contentsLost()) {
                bs.show();
            }
        }
    }

    /**
     *
     * @return
     * Method is responsible to guarantee  full screen mode for uses
     */
    public Window getFullScreenWindow() {
        return graphicsDevice.getFullScreenWindow();
    }

    /**
     *
     * @return
     * Return screen width
     */
    public int getWidth() {
        Window window = graphicsDevice.getFullScreenWindow();
        if (window != null) {
            return window.getWidth();
        } else {
            return 0;
        }
    }

    /**
     *
     * @return
     * Return screen height
     */
    public int getHeight() {
        Window window = graphicsDevice.getFullScreenWindow();
        if (window != null) {
            return window.getHeight();
        } else {
            return 0;
        }
    }

    /**
     *Method is call at the end of the game
     */
    public void restoreScreen() {
        Window window = graphicsDevice.getFullScreenWindow();
        if (window != null) {
            window.dispose();
        }
        graphicsDevice.setFullScreenWindow(null);
    }

    /**
     *
     * @param width
     * @param height
     * @param transparency
     * @return
     */
    public BufferedImage createCompatibaleimage(int width, int height, int transparency) {
        Window win = graphicsDevice.getFullScreenWindow();
        if (win != null) {
            GraphicsConfiguration graphicsConfiguration = win.getGraphicsConfiguration();
            return graphicsConfiguration.createCompatibleImage(width, height, transparency);
        } else {
            return null;
        }
    }

}
