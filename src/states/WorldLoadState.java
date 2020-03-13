package states;

import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Entity;
import entities.creatures.Player;
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
	private static boolean goingDown = false;
	
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private Date date = new Date(); 
    
    private boolean toLoadEntities = false;
    
    private boolean world1Loadable = false;
    private boolean world2Loadable = false;
    private boolean world3Loadable = false;
    private boolean world4Loadable = false;
	
    public WorldLoadState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
    }

    public void tick() {
    	//game is setup after the world is first loaded
    	if(handler.getWorld().isSetup()) {
    		Save.saveItemData(handler, "save" + handler.getWorld().getLoadedSave());
    		Save.savePlayerData(handler.getWorld().getEntityManager().getPlayer(), "save" + handler.getWorld().getLoadedSave());
    		if(handler.getGame().getGameType().contains("story")) {
        		Save.saveNPCData(handler.getWorld().getEntityManager().getPlayer(), "save" + handler.getWorld().getLoadedSave());
    		}else if(!handler.getWorld().isAboutToChange()) {
    			Save.saveWorldData(handler, "save" + handler.getWorld().getLoadedSave());
    		}
    	}
    	Save.saveOtherData(World.getCount());
    	
    	//checks and see which worlds have saved and can be loaded
    	if(Load.checkIfEnitiesSaved("save" + handler.getWorld().getLoadedSave(), 1)) {
    		world1Loadable = true;
    	}
    	if(Load.checkIfEnitiesSaved("save" + handler.getWorld().getLoadedSave(), 2)) {
    		world2Loadable = true;
    	}
    	if(Load.checkIfEnitiesSaved("save" + handler.getWorld().getLoadedSave(), 3)) {
    		world3Loadable = true;
    	}
    	if(Load.checkIfEnitiesSaved("save" + handler.getWorld().getLoadedSave(), 4)) {
    		world4Loadable = true;
    	}
    	
    	//unloads the world to make the new one able to be loaded
    	handler.getWorld().unloadWorld();
    	Tile.getTiles().removeAll(Tile.getTiles());
    	
    	//makes sure the world will actually load and save
    	if(handler.getWorld().getCurrentWorld() <= 0) {
    		handler.getWorld().setCurrentWorld(1);
    	}

    	System.out.println("Current World as of WLS: " + handler.getWorld().getCurrentWorld());
    	
    	if(handler.getWorld().getCurrentWorld() == 1 && world1Loadable) {
    		world = new World(handler, true, 1);
    		world.setCurrentWorld(1);
    		toLoadEntities = true;
    	}else if(handler.getWorld().getCurrentWorld() == 1) {
    		world = new World(handler, false, 1);
    		world.setCurrentWorld(1);
    		toLoadEntities = false;
    	}
    	
    	if(handler.getWorld().getCurrentWorld() == 2 && world2Loadable) {
    		world = new World(handler, true, 2);
    		world.setCurrentWorld(2);
    		toLoadEntities = true;
    	}else if(handler.getWorld().getCurrentWorld() == 2) {
    		world = new World(handler, false, 2);
    		world.setCurrentWorld(2);
    		toLoadEntities = false;
    	}
    	
    	if(handler.getWorld().getCurrentWorld() == 3 && world3Loadable) {
    		world = new World(handler, true, 3);
    		world.setCurrentWorld(3);
    		toLoadEntities = true;
    	}else if(handler.getWorld().getCurrentWorld() == 3){
    		world = new World(handler, false, 3);
    		world.setCurrentWorld(3);
    		toLoadEntities = false;
    	}
    	
    	if(handler.getWorld().getCurrentWorld() == 4 && world4Loadable) {
    		world = new World(handler, true, 4);
    		world.setCurrentWorld(4);
    		toLoadEntities = true;
    	}else if(handler.getWorld().getCurrentWorld() == 4) {
    		world = new World(handler, false, 4);
    		world.setCurrentWorld(4);
    		toLoadEntities = false;
    	}
    	
    	handler.setWorld(world);
    	
    	Load.loadItemData("save" + handler.getWorld().getLoadedSave());
    	Load.loadPlayerData(handler.getWorld().getEntityManager().getPlayer(), "save" + handler.getWorld().getLoadedSave());
    	if(handler.getGame().getGameType().contains("story")) {
            Load.loadNPCData(handler.getWorld().getEntityManager().getPlayer(), "save" + handler.getWorld().getLoadedSave(), handler);
    	}else if(!(handler.getGame().getGameType().contains("story")) && toLoadEntities) {
    		for(Entity e : Load.loadGeneratedEntityData(handler, "save" + handler.getWorld().getLoadedSave())) {
    			if(handler.getWorld().getCurrentWorld() == 1)
    				handler.getWorld().getEntityManager().addEntity1(e);
    			else if(handler.getWorld().getCurrentWorld() == 2)
    				handler.getWorld().getEntityManager().addEntity2(e);
    			else if(handler.getWorld().getCurrentWorld() == 3)
    				handler.getWorld().getEntityManager().addEntity3(e);
    			else if(handler.getWorld().getCurrentWorld() == 4)
    				handler.getWorld().getEntityManager().addEntity4(e);
    		}
    	}
    	
    	if(started) {
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

        if(!goingDown)
        	handler.getWorld().setFurthestWorldVisited(handler.getWorld().getCurrentWorld());
        
        GameState.warping = false;
        
        State.setPreviousState(handler.getGame().worldLoadState);
        State.setState(handler.getGame().gameState);
    }

    public void render(Graphics g)
    {
    	g.dispose();
    	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
    	Text.drawString(g, "Loading...", handler.getWidth() / 2, handler.getHeight() / 3 - 100, true, Color.WHITE, Assets.font40);
        uiManager.render(g);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public boolean isGoingDown() {
		return goingDown;
	}

	public static void setGoingDown(boolean down) {
		goingDown = down;
	}
    
}
