package states;

import gfx.Assets;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;

import main.Handler;
import saving.Load;
import ui.Button;
import ui.UIManager;
import worlds.World;

// Referenced classes of package states:
//            State

public class LoadState extends State
{

	private World world;
	private UIManager uiManager;
    private static String saveName;
    private static boolean loading = false;
    private static boolean toLoad = false;
    private static boolean cantLoad = false;
    private static boolean saveSelected = false;
    private static int worldToLoad = 1;
    private static int saveToLoad;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    
	private int buttonX;
	private int button2X;
	private int button3X;
	private int button4X;
	private int button5X;
	private int button6X;
	private int button7X;
	private int buttonY;
	private int buttonWidth;
	private int smallButtonWidth;
	private int buttonHeight;
	
    public LoadState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        buttonX = (handler.getWidth() / 8) * 2 + 105;
        button2X = (handler.getWidth() / 8) * 4 + 105;
        
        button3X = (handler.getWidth() / 6);
        button4X = (handler.getWidth() / 6) * 2;
        button5X = (handler.getWidth() / 6) * 3;
        button6X = (handler.getWidth() / 6) * 4;
        button7X = (handler.getWidth() / 6) * 5;
        
        buttonY = (handler.getHeight() / 3) * 2;
        buttonWidth = 210;
        buttonHeight = 120;
        smallButtonWidth = 105;
        
        button1 = new Button(Assets.smallButton, buttonX, buttonY, buttonWidth, buttonHeight, true, "Yes", handler);
        button2 = new Button(Assets.smallButton, button2X, buttonY, buttonWidth, buttonHeight, true, "No", handler);
        button3 = new Button(Assets.smallestButton, button3X, buttonY, smallButtonWidth, buttonHeight, true, "1", handler);
        button4 = new Button(Assets.smallestButton, button4X, buttonY, smallButtonWidth, buttonHeight, true, "2", handler);
        button5 = new Button(Assets.smallestButton, button5X, buttonY, smallButtonWidth, buttonHeight, true, "3", handler);
        button6 = new Button(Assets.smallestButton, button6X, buttonY, smallButtonWidth, buttonHeight, true, "4", handler);
        button7 = new Button(Assets.smallestButton, button7X, buttonY, smallButtonWidth, buttonHeight, true, "5", handler);
        button8 = new Button(Assets.button, handler.getWidth() / 2, buttonY, 480, 120, true, "Continue", handler);
    }
    
    

    public static int getWorldToLoad() {
		return worldToLoad;
	}



	public static void setWorldToLoad(int world) {
		worldToLoad = world;
	}



	public void tick()
    {
		if(!(loading && toLoad)) {
			button1.tick();
			button2.tick();
		}
		if(button1 != null && button1.isClicked()) {
//			loading = true;
//            toLoad = true;
//            button1 = null;
            try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if(!cantLoad) {
                saveSelected = true;
    			State.setState(handler.getGame().saveSelectState);
            }
		}else if(button2.isClicked() || cantLoad) {
			world = new World(handler, "res/worlds/world1.txt");
    		handler.setWorld(world);
        
    		//handler.getWorld().addEntities();
    		handler.getWorld().setLoaded(false);
    		handler.getWorld().setCount(World.getCount() + 1);
    		handler.getWorld().setLoadedWorld(World.getCount());
    		handler.getWorld().setCurrentWorld(1);
    		try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		State.setPreviousState(handler.getGame().loadState);
    		State.setState(handler.getGame().modeState);
		}
        if(toLoad && loading) {
        	System.out.println(saveToLoad);
        	saveName = "save" + saveToLoad;
        	loadStuff(saveToLoad);
        }
    }

    public void render(Graphics g)
    {
        if(!loading && !cantLoad)
        {
        	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
            Text.drawString(g, "Do you want to load save data?", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font40);
            button1.render(g);
            button2.render(g);
        }
    }

    public static void setWorldName(String name)
    {
        saveName = name;
    }

    public static boolean isLoading() {
		return loading;
	}

	public static void setLoading(boolean loading) {
		LoadState.loading = loading;
	}

	public static boolean isToLoad() {
		return toLoad;
	}

	public static void setToLoad(boolean toLoad) {
		LoadState.toLoad = toLoad;
	}

	public static int getSaveToLoad() {
		return saveToLoad;
	}

	public static void setSaveToLoad(int saveToLoad) {
		LoadState.saveToLoad = saveToLoad;
	}

	public static boolean isCantLoad() {
		return cantLoad;
	}

	public static void setCantLoad(boolean cantLoad) {
		LoadState.cantLoad = cantLoad;
	}

	public void loadStuff(int num)
    {
		System.out.println(num);
    	Load.loadOtherWorldData(saveName, handler);
    	if(handler.getGame().getGameType().contains("story"))
    		world = new World(handler, "res/worlds/world" + worldToLoad + ".txt");
    	else
    		world = new World(handler, true, num, worldToLoad);
    	handler.setWorld(world);
        handler.getWorld().setLoadedWorld(num);
        handler.getWorld().setCurrentWorld(worldToLoad);
        handler.getWorld().setFurthestWorldVisited(SaveSelectState.getFurthestWorld());
       // handler.getWorld().addEntities();
        Load.loadPlayerData(handler.getWorld().getEntityManager().getPlayer(), saveName);
        Load.loadNPCData(handler.getWorld().getEntityManager().getPlayer(), saveName, handler);
        //Load.loadEntityData(handler, saveName);
        Load.loadItemData(saveName);
        Load.loadSaveData(saveName, handler);
        handler.getWorld().setLoaded(true);
        if(handler.getWorld().getCurrentWorld() == 1)
        	handler.getWorld().setWorld1Loaded(true);
        if(handler.getWorld().getCurrentWorld() == 2)
        	handler.getWorld().setWorld2Loaded(true);
        if(handler.getWorld().getCurrentWorld() == 3)
        	handler.getWorld().setWorld3Loaded(true);
        if(handler.getWorld().getCurrentWorld() == 4)
        	handler.getWorld().setWorld4Loaded(true);
		State.setPreviousState(handler.getGame().loadState);
        State.setState(handler.getGame().worldLoadState);
    }
}
