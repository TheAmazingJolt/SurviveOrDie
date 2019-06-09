package states;

import gfx.Assets;
import gfx.Text;

import java.awt.Color;
import java.awt.Graphics;

import main.Handler;
import tiles.Tile;
import ui.UIManager;
import worlds.World;

// Referenced classes of package states:
//            State

public class MultiplayerWorldLoadState extends State
{
	
	private UIManager uiManager;
	private World world;
	
	
    public MultiplayerWorldLoadState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
    }

    public void tick()
    {
    	handler.getWorld().unloadWorld();
    	Tile.getTiles().removeAll(Tile.getTiles());
    	
        world = new World(handler, "res/worlds/world" + handler.getWorld().getCurrentWorld() + ".txt");
        
        handler.setWorld(world);
        
        MultiplayerState.start();
        
        //handler.getWorld().getEntityManager().createPlayer2();
        
        State.setState(handler.getGame().multiplayerGameState);
    }

    public void render(Graphics g)
    {
    	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
    	Text.drawString(g, "Loading...", handler.getWidth() / 2, handler.getHeight() / 3 - 100, true, Color.WHITE, Assets.font40);
        uiManager.render(g);
    }
    
}
