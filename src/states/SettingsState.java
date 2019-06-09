package states;

import java.awt.Color;
import java.awt.Graphics;

import audio.Audio;
import gfx.Assets;
import gfx.Text;
import main.Handler;
import ui.Button;
import ui.Slider;
import ui.UIManager;

// Referenced classes of package states:
//            State

public class SettingsState extends State
{

	private UIManager uiManager;
	private Handler handler;

	private Slider slider1;
	private Slider slider2;
	
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	
	private int buttonX;
	private int buttonY;
	private int buttonX2;
	private int buttonWidth;
	private int buttonHeight;
	
	private boolean isVolume = false;
	private boolean isMenu = true;
	
    public SettingsState(Handler handler)
    {
        super(handler);
        this.handler = handler;
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        buttonX = handler.getWidth() / 6;
        buttonX2 = handler.getWidth() / 3 * 2;
        buttonY = (handler.getHeight() / 5) * 4;
        buttonWidth = 210;
        buttonHeight = 120;
        button1 = new Button(Assets.smallButton, buttonX, buttonY, buttonWidth, buttonHeight, false, "Volume", handler);
        button2 = new Button(Assets.smallButton, buttonX2, buttonY, buttonWidth, buttonHeight, false, "Cancel", handler);
        slider1 = new Slider(handler.getWidth() / 6 + 105, handler.getHeight() / 3 * 2, 250, 60, "Effects", 100, 0, 50, 25, 75, true, handler);
        slider2 = new Slider(handler.getWidth() / 3 * 2 + 105, handler.getHeight() / 3 * 2, 250, 60, "Ambient", 100, 0, 50, 25, 75, true, handler);
        button3 = new Button(Assets.smallButton, buttonX, buttonY, buttonWidth, buttonHeight, false, "Save", handler);
        button4 = new Button(Assets.smallButton, buttonX2, buttonY, buttonWidth, buttonHeight, false, "Cancel", handler);
    }

    public void tick()
    {
    	if(isMenu) {
    		button1.tick();
    		button2.tick();
    		if(button1.isClicked()) {
    			isMenu = false;
    			isVolume = true;
    			try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			return;
    		}else if(button2.isClicked()) {
    			try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			State.setState(State.getPreviousState());
    		}
    	}else if(isVolume) {
    		slider1.tick();
    		slider2.tick();
    		button3.tick();
    		button4.tick();
    		if(button3.isClicked()) {
    			for(Audio a : handler.getAudioManager().getSounds()) {
    				if(a.isAmbient()) {
    					a.getMediaPlayer().setVolume(slider2.getCurrentSliderValue());
    				}else if(!a.isAmbient()) {
    					a.getMediaPlayer().setVolume(slider1.getCurrentSliderValue());
    				}
    			}
            	handler.getAudioManager().setEffectVolume(slider1.getCurrentSliderValue());
            	handler.getAudioManager().setAmbientVolume(slider2.getCurrentSliderValue());
            	try {
    				Thread.sleep(150);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
            	isMenu = true;
            	isVolume = false;
        	}else if(button4.isClicked()) {
        		try {
    				Thread.sleep(150);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
            	isMenu = true;
            	isVolume = false;
        	}
    	}
        uiManager.tick();
    }

    public void render(Graphics g)
    {
    	g.drawImage(Assets.settingScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
		//g.drawImage(Assets.title, handler.getWidth() / 2 - 240, handler.getWidth() / 7 - 130, 480, 240, null);
    	if(isMenu)
    		Text.drawString(g, "Settings", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font60);
    	else if(isVolume)
    		Text.drawString(g, "Volume", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font60);
    	if(isMenu) {
    		button1.render(g);
    		button2.render(g);
    	}else if(isVolume) {
    		slider1.render(g);
    		slider2.render(g);
    		button3.render(g);
    		button4.render(g);
    	}
        uiManager.render(g);
    }

}
