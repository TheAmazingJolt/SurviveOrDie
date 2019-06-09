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
    private static int worldToLoad = 1;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    
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
    }
    
    

    public static int getWorldToLoad() {
		return worldToLoad;
	}



	public static void setWorldToLoad(int world) {
		worldToLoad = world;
	}



	public void tick()
    {
		if(loading && toLoad) {
        	button3.tick();
			button4.tick();
			button5.tick();
			button6.tick();
			button7.tick();
		}else if(!(loading && toLoad)) {
			button1.tick();
			button2.tick();
		}
		if(button1 != null && button1.isClicked()) {
			loading = true;
            toLoad = true;
            button1 = null;
            try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(button2.isClicked()) {
			world = new World(handler, "res/worlds/world1.txt");
    		handler.setWorld(world);
        
    		//handler.getWorld().addEntities();
    		handler.getWorld().setLoaded(false);
    		handler.getWorld().setCount(World.getCount() + 1);
    		handler.getWorld().setLoadedWorld(World.getCount());
    		handler.getWorld().setCurrentWorld(1);
    		State.setPreviousState(handler.getGame().loadState);
    		State.setState(handler.getGame().worldLoadState);
		}
        if(toLoad && loading)
            if(World.getCount() == 0)
            {
            	world = new World(handler, "res/worlds/world1.txt");
                handler.setWorld(world);
                
                //handler.getWorld().addEntities();
                handler.getWorld().setLoaded(false);
                handler.getWorld().setCount(World.getCount() + 1);
                handler.getWorld().setLoadedWorld(World.getCount());
                handler.getWorld().setCurrentWorld(1);
        		State.setPreviousState(handler.getGame().loadState);
                State.setState(handler.getGame().worldLoadState);
            } else
            if(World.getCount() == 1)
            {
                saveName = "save1";
                loadStuff(1);
            } else
            if(World.getCount() == 2)
            {
            	button3.setX(handler.getWidth() / 3 - 52);
            	button4.setX((handler.getWidth() / 3) * 2 - 52);
            	button3.setTextX();
            	button4.setTextX();
            	if(button3.isClicked()) {
                    saveName = "save1";
                    loadStuff(1);
            	}else if(button4.isClicked()) {
                    saveName = "save2";
                    loadStuff(2);
            	}
            	button5.setActive(false);
            	button6.setActive(false);
            	button7.setActive(false);
            } else
            if(World.getCount() == 3)
            {
            	button3.setX(handler.getWidth() / 4 - 52);
            	button4.setX((handler.getWidth() / 4) * 2 - 52);
            	button5.setX((handler.getWidth() / 4) * 3 - 52);
            	button3.setTextX();
            	button4.setTextX();
            	button5.setTextX();
            	//finish this!!!!!!!!!!
            	if(button3.isClicked()) {
                    saveName = "save1";
                    loadStuff(1);
            	}else if(button4.isClicked()) {
                    saveName = "save2";
                    loadStuff(2);
            	}else if(button5.isClicked()) {
                    saveName = "save3";
                    loadStuff(3);
            	}
            	button6.setActive(false);
            	button7.setActive(false);
            } else
            if(World.getCount() == 4)
            {
            	button3.setX(handler.getWidth() / 5 - 52);
            	button4.setX((handler.getWidth() / 5) * 2 - 52);
            	button5.setX((handler.getWidth() / 5) * 3 - 52);
            	button6.setX((handler.getWidth() / 5) * 4 - 52);
            	button3.setTextX();
            	button4.setTextX();
            	button5.setTextX();
            	button6.setTextX();
            	if(button3.isClicked()) {
                    saveName = "save1";
                    loadStuff(1);
            	}else if(button4.isClicked()) {
                    saveName = "save2";
                    loadStuff(2);
            	}else if(button5.isClicked()) {
                    saveName = "save3";
                    loadStuff(3);
            	}else if(button6.isClicked()) {
                    saveName = "save4";
                    loadStuff(4);
            	}
            	button7.setActive(false);
            } else
            if(World.getCount() == 5) {
            	button3.setX(handler.getWidth() / 6 - 52);
            	button4.setX((handler.getWidth() / 6) * 2 - 52);
            	button5.setX((handler.getWidth() / 6) * 3 - 52);
            	button6.setX((handler.getWidth() / 6) * 4 - 52);
            	button7.setX((handler.getWidth() / 6) * 5 - 52);
            	button3.setTextX();
            	button4.setTextX();
            	button5.setTextX();
            	button6.setTextX();
            	button7.setTextX();
            	if(button3.isClicked()) {
                    saveName = "save1";
                    loadStuff(1);
            	}else if(button4.isClicked()) {
                    saveName = "save2";
                    loadStuff(2);
            	}else if(button5.isClicked()) {
                    saveName = "save3";
                    loadStuff(3);
            	}else if(button6.isClicked()) {
                    saveName = "save4";
                    loadStuff(4);
            	}else if(button7.isClicked()) {
                    saveName = "save5";
                    loadStuff(5);
            	}
            }else if(World.getCount() > 5) {
            	World.setCount(5);
            }
        	
    }

    public void render(Graphics g)
    {
        if(!loading)
        {
        	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
            Text.drawString(g, "Do you want to load save data?", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font40);
            button1.render(g);
            button2.render(g);
        } else
        if(loading)
        {
            if(World.getCount() == 0)
            {
            	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
                Text.drawString(g, "Can't load. No save files", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font28);
                Text.drawString(g, "Press Enter to continue", handler.getWidth() / 2, 3 * (handler.getHeight() / 4), true, Color.WHITE, Assets.font20);
            }
            g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
            Text.drawString(g, "Which save do you want to load?", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font40);
            button3.render(g);
            button4.render(g);
            button5.render(g);
            button6.render(g);
            button7.render(g);
        }
    }

    public static void setWorldName(String name)
    {
        saveName = name;
    }

    public void loadStuff(int num)
    {
    	Load.loadWorldData(saveName, handler);
    	world = new World(handler, "res/worlds/world" + worldToLoad + ".txt");
        handler.setWorld(world);
        handler.getWorld().setLoadedWorld(num);
        handler.getWorld().setCurrentWorld(worldToLoad);
       // handler.getWorld().addEntities();
        Load.loadPlayerData(handler.getWorld().getEntityManager().getPlayer(), saveName);
        Load.loadNPCData(handler.getWorld().getEntityManager().getPlayer(), saveName, handler);
        //Load.loadEntityData(handler, saveName);
        Load.loadItemData(saveName);
        handler.getWorld().setLoaded(true);
		State.setPreviousState(handler.getGame().loadState);
        State.setState(handler.getGame().worldLoadState);
    }
}
