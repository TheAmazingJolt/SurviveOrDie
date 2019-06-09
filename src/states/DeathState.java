package states;

import gfx.Assets;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import main.Handler;
import ui.Button;
import ui.UIManager;

// Referenced classes of package states:
//            State

public class DeathState extends State
{
	
	private UIManager uiManager;
	
	private Button button1;
	private Button button2;
	
	private int buttonX;
	private int button2X;
	private int buttonY;
	private int buttonWidth;
	private int buttonHeight;

    public DeathState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        buttonX = (handler.getWidth() / 8) * 2 + 15;
        button2X = (handler.getWidth() / 8) * 4 + 15;
        buttonY = (handler.getHeight() / 3) * 2;
        buttonWidth = 210;
        buttonHeight = 120;
        
        button1 = new Button(Assets.smallButton, buttonX, buttonY, buttonWidth, buttonHeight, false, "Respawn", handler);
        button2 = new Button(Assets.smallButton, button2X, buttonY, buttonWidth, buttonHeight, false, "Exit", handler);
    }

    public void tick()
    {
    	button1.tick();
    	button2.tick();
    	if(button1.isClicked()) {
    		State.setPreviousState(handler.getGame().deathState);
            State.setState(handler.getGame().gameState);
    	}else if(button2.isClicked()) {
			System.exit(0);
    	}
        uiManager.tick();
    }

    public void render(Graphics g)
    {
    	g.drawImage(Assets.deathScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
    	g.drawImage(Assets.player_dead, handler.getWidth() / 2 - 120, handler.getHeight() / 2 - 180, 240, 240, null);
        Text.drawString(g, "You Died!", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font60);
        button1.render(g);
        button2.render(g);
        uiManager.render(g);
    }
    
}
