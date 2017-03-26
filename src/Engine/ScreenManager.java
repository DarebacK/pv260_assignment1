package Engine;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class ScreenManager {	
    
	private static final DisplayMode modes[] = 
        {
            //new DisplayMode(1920,1080,32,0),
            new DisplayMode(1680,1050,32,0),
            //new DisplayMode(1280,1024,32,0),
            new DisplayMode(800,600,32,0),
            new DisplayMode(800,600,24,0),
            new DisplayMode(800,600,16,0),
            new DisplayMode(640,480,32,0),
            new DisplayMode(640,480,24,0),
            new DisplayMode(640,480,16,0),
        };
        
	private GraphicsDevice graphicsDevice;
	
	public ScreenManager(){
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
	}
	
	public DisplayMode[] getCompatibleDisplayModes(){
            return graphicsDevice.getDisplayModes();
	}
	
	public DisplayMode findFirstCompatibaleMode(){
		
            DisplayMode goodModes[] = graphicsDevice.getDisplayModes();
            for(int x = 0; x<modes.length;x++){
                for(int y = 0;y<goodModes.length;y++){
                    if(displayModesMatch(modes[x],goodModes[y])){
                        return modes[x];
                    }
                }
            }
            return null;
	}
	
	public DisplayMode getCurrentDM(){
            return graphicsDevice.getDisplayMode();
	}
	
	public boolean displayModesMatch(DisplayMode displayMode1, DisplayMode displayMode2){
            if(displayMode1.getWidth() != displayMode2.getWidth() || displayMode1.getHeight() != displayMode2.getHeight()){
                return false;
            }
            if(displayMode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && displayMode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && displayMode1.getBitDepth() != displayMode2.getBitDepth()){
                return false;
            }
            if(displayMode1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && displayMode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && displayMode1.getRefreshRate() != displayMode2.getRefreshRate()){
                return false;
            }
            return true;
	}
	
	public void setFullScreen(DisplayMode displayMode){
            JFrame frame = new JFrame();
            frame.setUndecorated(true);
            frame.setIgnoreRepaint(true);
            frame.setResizable(false);
            graphicsDevice.setFullScreenWindow(frame);

            if(displayMode != null && graphicsDevice.isDisplayChangeSupported()){
                try{
                    graphicsDevice.setDisplayMode(displayMode);
                }catch(Exception ex){}
                frame.createBufferStrategy(2);
            }
	}
	
	public Graphics2D getGraphics(){
            Window window = graphicsDevice.getFullScreenWindow();
            if(window != null){
                BufferStrategy bs = window.getBufferStrategy();
                return (Graphics2D)bs.getDrawGraphics();
            }
            else{
                return null;
            }
	}
	
	public void update(){
            Window window = graphicsDevice.getFullScreenWindow();
            if(window != null){
                BufferStrategy bs = window.getBufferStrategy();
                if(!bs.contentsLost()){
                    bs.show();
                }
            }
	}
	
	public Window getFullScreenWindow(){
            return graphicsDevice.getFullScreenWindow();
	}
	
	public int getWidth(){
            Window window = graphicsDevice.getFullScreenWindow();
            if(window != null){
                return window.getWidth();
            }else{
                return 0;
            }
	}
	
	public int getHeight(){
            Window window = graphicsDevice.getFullScreenWindow();
            if(window != null){
                return window.getHeight();
            }else{
                return 0;
            }
	}
	
	public void restoreScreen(){
            Window window = graphicsDevice.getFullScreenWindow();
            if(window != null){
                window.dispose();
            }
            graphicsDevice.setFullScreenWindow(null);
	}
	
	public BufferedImage createCompatibaleimage(int width, int height, int transparency){
            Window win = graphicsDevice.getFullScreenWindow();
            if(win != null){
                GraphicsConfiguration graphicsConfiguration = win.getGraphicsConfiguration();
                return graphicsConfiguration.createCompatibleImage(width,height,transparency);
            }else{
                return null;
            }	
        }
	
}
