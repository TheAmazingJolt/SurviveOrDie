package main;

import audio.AudioManager;
import gfx.GameCamera;
import guis.GuiManager;
import input.KeyManager;
import input.MouseManager;
import worlds.World;

// Referenced classes of package main:
//            Game

public class Handler
{
	
	private Game game;
	private World world;

    public Handler(Game game)
    {
        this.game = game;
    }

    public AudioManager getAudioManager() {
    	return game.getAudioManager();
    }
    
    public int getWidth()
    {
        return game.getWidth();
    }

    public int getHeight()
    {
        return game.getHeight();
    }

    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }
    
    public GuiManager getGuiManager()
    {
        return game.getGuiManager();
    }

    public MouseManager getMouseManager()
    {
        return game.getMouseManager();
    }

    public GameCamera getGameCamera()
    {
        return game.getGameCamera();
    }

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public World getWorld()
    {
        return world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

   
}
