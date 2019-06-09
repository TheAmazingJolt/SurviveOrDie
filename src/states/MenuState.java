package states;

import java.awt.Graphics;

import gfx.Assets;
import main.Handler;
import saving.Load;
import ui.Button;
import ui.UIManager;

// Referenced classes of package states:
//            State

public class MenuState extends State
{

	private UIManager uiManager;
	private Handler handler;

	private Button button1;
	private Button button2;
	
	private int buttonX;
	private int buttonY;
	private int buttonWidth;
	private int buttonHeight;
	
	private boolean settingLoaded = false;
	
    public MenuState(Handler handler)
    {
        super(handler);
        this.handler = handler;
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        buttonX = handler.getWidth() / 2;
        buttonY = (handler.getHeight() / 3) * 2 - 30;
        buttonWidth = 480;
        buttonHeight = 120;
        if(!handler.getGame().isMultiplayer())
        	button1 = new Button(Assets.button, buttonX, buttonY, buttonWidth, buttonHeight, true, "Start Game", handler);
        else if(handler.getGame().isMultiplayer())
        	button1 = new Button(Assets.button, buttonX, buttonY, buttonWidth, buttonHeight, true, "Start Multi", handler);
        button2 = new Button(Assets.button, buttonX, buttonY + buttonHeight + 10, buttonWidth, buttonHeight, true, "Settings", handler);
    }

    public void tick()
    {
    	if(!settingLoaded) {
    		Load.loadSettings(handler);
    		settingLoaded = true;
    	}
    	button1.tick();
    	button2.tick();
    	if(button1.isClicked()) {
    		if(!handler.getGame().isMultiplayer()) {
    			Load.loadOtherData();
        		try {
    				Thread.sleep(150);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			State.setPreviousState(handler.getGame().menuState);
        		State.setState(handler.getGame().loadState);
    		}else if(handler.getGame().isMultiplayer()){
    			try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			State.setPreviousState(handler.getGame().menuState);
                State.setState(handler.getGame().multiplayerState);
    		}
    	}else if(button2.isClicked()) {
    		try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			State.setPreviousState(handler.getGame().menuState);
    		State.setState(handler.getGame().settingsState);
    	}
        uiManager.tick();
    }

    public void render(Graphics g)
    {
    	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
		g.drawImage(Assets.title, handler.getWidth() / 2 - 240, handler.getWidth() / 7 - 130, 480, 240, null);
    	button1.render(g);
    	button2.render(g);
        uiManager.render(g);
    }

}
