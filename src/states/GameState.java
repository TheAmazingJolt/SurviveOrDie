package states;

import java.awt.Graphics;

import audio.Audio;
import entities.creatures.Player;
import main.Handler;

// Referenced classes of package states:
//            State

public class GameState extends State
{
	
    private boolean canPause;
    private Player player;
    
    private Audio ambience;
	
    public GameState(Handler handler)
    {
        super(handler);
        
        canPause = true; 
    }

    public void tick()
    {
    	player = handler.getWorld().getEntityManager().getPlayer();
        if(player.getInventory().isActive() || 
        		player.getWeapons().isActive())
            canPause = false;
        else
            canPause = true;
        if(handler.getWorld().getCurrentWorld() == 1) {
        	if(player.getKilledBosses() >= 1 && player.getX() >= 1472 && player.getY() >= 0 && player.getX() <= 1536 && player.getY() <= 64) {
            	handler.getWorld().setCurrentWorld(player.getKilledBosses() + 1);
            	handler.getWorld().getEntityManager().getPlayer().setKilledEnemies(0);
            	handler.getWorld().getEntityManager().getPlayer().setPos(handler.getWorld().getEntityManager().getPlayer().getStartX(), 
            			handler.getWorld().getEntityManager().getPlayer().getStartY());
            	State.setPreviousState(handler.getGame().gameState);
            	State.setState(handler.getGame().worldLoadState);
            	return;
        	}
        }else if(handler.getWorld().getCurrentWorld() == 2) {
        	if(player.getKilledBosses() >= 2 && player.getX() >= 1472 && player.getY() >= 0 && player.getX() <= 1536 && player.getY() <= 64) {
        		handler.getWorld().setCurrentWorld(player.getKilledBosses() + 1);
            	handler.getWorld().getEntityManager().getPlayer().setKilledEnemies(0);
            	handler.getWorld().getEntityManager().getPlayer().setPos(handler.getWorld().getEntityManager().getPlayer().getStartX(), 
            			handler.getWorld().getEntityManager().getPlayer().getStartY());
            	State.setPreviousState(handler.getGame().gameState);
            	State.setState(handler.getGame().worldLoadState);
            	return;
            }
        }else if(handler.getWorld().getCurrentWorld() == 3) {
        	if(player.getKilledBosses() >= 3 && player.getX() >= 1472 && player.getY() >= 0 && player.getX() <= 1536 && player.getY() <= 64) {
        		handler.getWorld().setCurrentWorld(player.getKilledBosses() + 1);
            	handler.getWorld().getEntityManager().getPlayer().setKilledEnemies(0);
            	handler.getWorld().getEntityManager().getPlayer().setPos(handler.getWorld().getEntityManager().getPlayer().getStartX(), 
            			handler.getWorld().getEntityManager().getPlayer().getStartY());
            	State.setPreviousState(handler.getGame().gameState);
            	State.setState(handler.getGame().worldLoadState);
            	return;
            }
        }
        handler.getWorld().tick();
        if(ambience == null)
        	ambience = new Audio("ambientnature.m4a", true, false, handler);
        if(handler.getKeyManager().keyJustPressed(27) && canPause) {
        	handler.getAudioManager().stopAmbient();
        	State.setPreviousState(handler.getGame().gameState);
            State.setState(handler.getGame().pauseState);
        }
    }

    public void render(Graphics g)
    {
        handler.getWorld().render(g);
    }

    
}
