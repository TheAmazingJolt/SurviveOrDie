package states;

import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Entity;
import gfx.Assets;
import gfx.Text;
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
	
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private Date date = new Date();  
	
    public WorldLoadState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
    }

    public void tick()
    {
    	if(handler.getWorld().isSetup()) {
    		Save.saveItemData(handler, handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems(), "save" + handler.getWorld().getLoadedSave());
        	//Save.saveEntityData(handler, "save" + handler.getWorld().getLoadedSave());
            Save.savePlayerData(handler.getWorld().getEntityManager().getPlayer(), "save" + handler.getWorld().getLoadedSave());
            Save.saveNPCData(handler.getWorld().getEntityManager().getPlayer(), "save" + handler.getWorld().getLoadedSave());
    	}
        Save.saveOtherData(World.getCount());
    	
    	handler.getWorld().unloadWorld();
    	Tile.getTiles().removeAll(Tile.getTiles());
    
    	if(handler.getWorld().getCurrentWorld() == 0) {
    		handler.getWorld().setCurrentWorld(1);
    	}
    	
        world = new World(handler, "res/worlds/world" + handler.getWorld().getCurrentWorld() + ".txt");
        
        handler.setWorld(world);
        
        Load.loadItemData("save" + handler.getWorld().getLoadedSave());
        Load.loadPlayerData(handler.getWorld().getEntityManager().getPlayer(), "save" + handler.getWorld().getLoadedSave());
        Load.loadNPCData(handler.getWorld().getEntityManager().getPlayer(), "save" + handler.getWorld().getLoadedSave(), handler);
        if(!started) {
        	if(handler.getGame().getGameType().contains("story"))
        		Load.loadEntityData(handler, "save" + handler.getWorld().getLoadedSave());
        	else {
        		for(Entity e : Load.loadGeneratedEntityData(handler, "save" + handler.getWorld().getLoadedSave())) {
        			handler.getWorld().getEntityManager().addEntity1(e);
        		}
        	}
        	started = true;
        }
        
        Save.saveWorldData(handler, "save" + handler.getWorld().getLoadedSave());
        Save.saveOtherWorldData(handler, "save" + handler.getWorld().getLoadedSave());
        if(!handler.getGame().creationDateSet && !handler.getWorld().isLoaded()) {
        	handler.getGame().setCreationDate(dateFormat.format(date));
        	System.out.println(handler.getGame().getCreationDate());
        }
        Save.saveSaveData(handler, "save" + handler.getWorld().getLoadedSave());
        
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
