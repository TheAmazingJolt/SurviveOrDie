package states;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import gfx.Text;
import main.Handler;
import ui.Button;
import ui.UIManager;

public class ModeState extends State{

	private UIManager uiManager;
	private Handler handler;
	
	private Button button1;
	private Button button2;
	private Button button3;
	
	private int buttonX;
	private int buttonY;
	private int buttonX2;
	private int buttonY2;
	private int buttonX3;
	private int buttonWidth;
	private int buttonHeight;
	
	public ModeState(Handler handler) {
		super(handler);
		this.handler = handler;
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		buttonX = handler.getWidth() / 2;
		buttonY = (handler.getHeight() / 3) * 2 - 30;
		buttonX2 = handler.getWidth() / 4;
		buttonY2 = buttonY + 125;
		buttonX3 = (handler.getWidth() / 4) * 3;
		buttonWidth = 480;
		buttonHeight = 120;
		button1 = new Button(Assets.button, buttonX, buttonY, buttonWidth, buttonHeight, true, "Story Mode", handler);
		button2 = new Button(Assets.button, buttonX2 + 3, buttonY2, buttonWidth - 15, buttonHeight, true, "Survival Mode", handler);
		button3 = new Button(Assets.button, buttonX3 - 3, buttonY2, buttonWidth - 15, buttonHeight, true, "Creative Mode", handler);
	}

	public void tick() {
		button1.tick();
		button2.tick();
		button3.tick();
		if(button1.isClicked()) {
			handler.getGame().setGameType("story");
			State.setPreviousState(handler.getGame().modeState);
			State.setState(handler.getGame().worldLoadState);
		}else if(button2.isClicked()) {
			handler.getGame().setGameType("survival");
			State.setPreviousState(handler.getGame().modeState);
			State.setState(handler.getGame().worldLoadState);
		}else if(button3.isClicked()) {
			handler.getGame().setGameType("creative");
			State.setPreviousState(handler.getGame().modeState);
			State.setState(handler.getGame().worldLoadState);
		}
	}

	public void render(Graphics g) {
		g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
		Text.drawString(g, "Pick Gamemode", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font60);
		button1.render(g);
		button2.render(g);
		button3.render(g);
		uiManager.render(g);
	}

}
