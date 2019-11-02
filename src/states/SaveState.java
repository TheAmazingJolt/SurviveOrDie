package states;

import gfx.Assets;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import main.Handler;
import saving.Save;
import ui.Button;
import ui.UIManager;
import worlds.World;

// Referenced classes of package states:
//            State

public class SaveState extends State
{

	private UIManager uiManager;
    private String saveName;
    
    private Button button1;
    private Button button2;
    
	private int buttonX;
	private int button2X;
	private int buttonY;
	private int buttonWidth;
	private int buttonHeight;
	
    public SaveState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        buttonX = (handler.getWidth() / 8) * 2 + 105;
        button2X = (handler.getWidth() / 8) * 4 + 105;
        buttonY = (handler.getHeight() / 3) * 2;
        buttonWidth = 210;
        buttonHeight = 120;
        button1 = new Button(Assets.smallButton, buttonX, buttonY, buttonWidth, buttonHeight, true, "Yes", handler);
        button2 = new Button(Assets.smallButton, button2X, buttonY, buttonWidth, buttonHeight, true, "No", handler);
    }

    public void tick()
    {
    	button1.tick();
    	button2.tick();
    	if(button1.isClicked()) {
    		saveName = "save"+handler.getWorld().getLoadedSave();
            Save.saveWorldData(handler, saveName);
            Save.saveItemData(handler, handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems(), saveName);
            if(handler.getGame().getGameType().contains("story")) {
                Save.saveEntityData(handler, saveName);
            }else if(handler.getGame().getGameType().contains("creative") || handler.getGame().getGameType().contains("survival")) {
            	Save.saveGeneratedEntityData(handler, saveName);
            }
            Save.savePlayerData(handler.getWorld().getEntityManager().getPlayer(), saveName);
            Save.saveNPCData(handler.getWorld().getEntityManager().getPlayer(), saveName);
            Save.saveOtherWorldData(handler, saveName);
            Save.saveOtherData(World.getCount());
            Save.saveSettings(handler);
            System.exit(0);
    	}else if(button2.isClicked()) {
            System.exit(0);
    	}
    }

    public void render(Graphics g)
    {
    	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
        Text.drawString(g, "Would you like to save data?", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font40);
        button1.render(g);
        button2.render(g);
    }

}
