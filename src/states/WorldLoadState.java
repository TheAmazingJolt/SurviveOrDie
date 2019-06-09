package states;

import gfx.Assets;
import gfx.Text;

import java.awt.Color;
import java.awt.Graphics;

import main.Handler;
import saving.Load;
import saving.Save;
import tiles.Tile;
import ui.UIManager;
import worlds.World;

// Referenced classes of package states:
//            State

public class WorldLoadState extends State
{
	
	private UIManager uiManager;

	private World world;
	
	private boolean started = false;
	
    public WorldLoadState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
    }

    public void tick()
    {
    	Save.saveItemData(handler, handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems(), "save" + handler.getWorld().getLoadedSave());
    	//Save.saveEntityData(handler, "save" + handler.getWorld().getLoadedSave());
        Save.savePlayerData(handler.getWorld().getEntityManager().getPlayer(), "save" + handler.getWorld().getLoadedSave());
        Save.saveNPCData(handler.getWorld().getEntityManager().getPlayer(), "save" + handler.getWorld().getLoadedSave());
        Save.saveOtherData(World.getCount());
    	
    	handler.getWorld().unloadWorld();
    	Tile.getTiles().removeAll(Tile.getTiles());
    	
        world = new World(handler, "res/worlds/world" + handler.getWorld().getCurrentWorld() + ".txt");
        
        handler.setWorld(world);
        
        Load.loadItemData("save" + handler.getWorld().getLoadedSave());
        Load.loadPlayerData(handler.getWorld().getEntityManager().getPlayer(), "save" + handler.getWorld().getLoadedSave());
        Load.loadNPCData(handler.getWorld().getEntityManager().getPlayer(), "save" + handler.getWorld().getLoadedSave(), handler);
        if(!started) {
        	Load.loadEntityData(handler, "save" + handler.getWorld().getLoadedSave());
        	started = true;
        }
        
        Save.saveWorldData(handler, "save" + handler.getWorld().getLoadedSave());
        
        handler.getWorld().getEntityManager().addToOverflow();
        
        State.setPreviousState(handler.getGame().worldLoadState);
        State.setState(handler.getGame().gameState);
    }

    public void render(Graphics g)
    {
    	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
    	Text.drawString(g, "Loading...", handler.getWidth() / 2, handler.getHeight() / 3 - 100, true, Color.WHITE, Assets.font40);
        uiManager.render(g);
    }
    
}
