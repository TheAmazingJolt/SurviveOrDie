package states;

import java.awt.Graphics;

import audio.Audio;
import entities.creatures.Player;
import main.Handler;
import saving.Save;
import utils.Timer;

// Referenced classes of package states:
//            State

public class GameState extends State
{
	
    private boolean canPause;
    private Player player;
    
    private Audio ambience;
    
    private Timer timer;
    private Timer timer2;
    private Timer timer3;
    private Timer timer4;
    private Timer timer5;
    private Timer timer6;
    
    public static boolean warping = false;
	
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
        if(handler.getGame().getGameType().contains("story")) {
	        if(handler.getWorld().getCurrentWorld() == 1) {
	        	if(player.getKilledBosses() >= 1 && player.getX() >= 1472 && player.getY() >= 0 && player.getX() <= 1536 && player.getY() <= 64) {
	        		handler.getWorld().setAboutToChange(true);
	            	handler.getWorld().setCurrentWorld(2);
	            	handler.getWorld().setFurthestWorldVisited(2);
	            	handler.getWorld().getEntityManager().getPlayer().setKilledEnemies(0);
	            	handler.getWorld().getEntityManager().getPlayer().setPos(handler.getWorld().getEntityManager().getPlayer().getStartX(), 
	            			handler.getWorld().getEntityManager().getPlayer().getStartY());
	            	WorldLoadState.setGoingDown(false);
	            	State.setPreviousState(handler.getGame().gameState);
	            	State.setState(handler.getGame().worldLoadState);
	            	return;
	        	}
	        }else if(handler.getWorld().getCurrentWorld() == 2) {
	        	if(player.getKilledBosses() >= 2 && player.getX() >= 1472 && player.getY() >= 0 && player.getX() <= 1536 && player.getY() <= 64) {
	        		handler.getWorld().setAboutToChange(true);
	        		handler.getWorld().setCurrentWorld(3);
	            	handler.getWorld().setFurthestWorldVisited(3);
	            	handler.getWorld().getEntityManager().getPlayer().setKilledEnemies(0);
	            	handler.getWorld().getEntityManager().getPlayer().setPos(handler.getWorld().getEntityManager().getPlayer().getStartX(), 
	            			handler.getWorld().getEntityManager().getPlayer().getStartY());
	            	WorldLoadState.setGoingDown(false);
	            	State.setPreviousState(handler.getGame().gameState);
	            	State.setState(handler.getGame().worldLoadState);
	            	return;
	            }
	        }else if(handler.getWorld().getCurrentWorld() == 3) {
	        	if(player.getKilledBosses() >= 3 && player.getX() >= 1472 && player.getY() >= 0 && player.getX() <= 1536 && player.getY() <= 64) {
	        		handler.getWorld().setAboutToChange(true);
	        		handler.getWorld().setCurrentWorld(4);
	            	handler.getWorld().setFurthestWorldVisited(4);
	            	handler.getWorld().getEntityManager().getPlayer().setKilledEnemies(0);
	            	handler.getWorld().getEntityManager().getPlayer().setPos(handler.getWorld().getEntityManager().getPlayer().getStartX(), 
	            			handler.getWorld().getEntityManager().getPlayer().getStartY());
	            	WorldLoadState.setGoingDown(false);
	            	State.setPreviousState(handler.getGame().gameState);
	            	State.setState(handler.getGame().worldLoadState);
	            	return;
	            }
	        }
        }else if(handler.getGame().getGameType().contains("survival") || handler.getGame().getGameType().contains("creative")) {
        	if(handler.getWorld().getCurrentWorld() == 1) {
	        	if(player.getX() >= 3168 && player.getY() >= 6240) {
	        		handler.getWorld().getEntityManager().getPlayer().setWarping(true);
	        		if(timer == null)
	        			timer = new Timer(1750, 1);
	        		timer.tick();
	        		if(timer.isCompleted()) {
	        			handler.getWorld().getEntityManager().getPlayer().setWarping(false);
		        		warping = true;
	        			timer = null;
		        		handler.getWorld().setAboutToChange(true);
		            	Save.saveGeneratedEntityData(handler, "save" + handler.getWorld().getLoadedSave());
		        		handler.getWorld().setCurrentWorld(2);
		            	handler.getWorld().getEntityManager().getPlayer().setPos(handler.getWorld().getEntityManager().getPlayer().getStartX(), 
		            			handler.getWorld().getEntityManager().getPlayer().getStartY());
		            	WorldLoadState.setGoingDown(false);
		            	State.setPreviousState(handler.getGame().gameState);
		            	State.setState(handler.getGame().worldLoadState);
		            	return;
	        		}
	        	}else {
	        		handler.getWorld().getEntityManager().getPlayer().setWarping(false);
	        		timer = null;
	        	}
        	}else if(handler.getWorld().getCurrentWorld() == 2) {
        		if(player.getX() >= 3168 && player.getY() >= 6240) {
	        		handler.getWorld().getEntityManager().getPlayer().setWarping(true);
	        		if(timer2 == null)
	        			timer2 = new Timer(1750, 1);
	        		timer2.tick();
	        		if(timer2.isCompleted()) {
	        			handler.getWorld().getEntityManager().getPlayer().setWarping(false);
		        		warping = true;
	        			timer2 = null;
		        		handler.getWorld().setAboutToChange(true);
		            	Save.saveGeneratedEntityData(handler, "save" + handler.getWorld().getLoadedSave());
		        		handler.getWorld().setCurrentWorld(3);
		            	handler.getWorld().getEntityManager().getPlayer().setPos(handler.getWorld().getEntityManager().getPlayer().getStartX(), 
		            			handler.getWorld().getEntityManager().getPlayer().getStartY());
		            	WorldLoadState.setGoingDown(false);
		            	State.setPreviousState(handler.getGame().gameState);
		            	State.setState(handler.getGame().worldLoadState);
		            	return;
	        		}
	        	}else {
	        		handler.getWorld().getEntityManager().getPlayer().setWarping(false);
	        		timer2 = null;
	        	}
	        	if(player.getX() >= 3040 && player.getY() >= 6240 && player.getX() <= 3040 + player.getWidth()) {
	        		handler.getWorld().getEntityManager().getPlayer().setWarping(true);
	        		if(timer3 == null)
	        			timer3 = new Timer(1750, 1);
	        		timer3.tick();
	        		if(timer3.isCompleted()) {
	        			handler.getWorld().getEntityManager().getPlayer().setWarping(false);
		        		warping = true;
		        		timer3 = null;
		        		handler.getWorld().setAboutToChange(true);
		            	Save.saveGeneratedEntityData(handler, "save" + handler.getWorld().getLoadedSave());
		        		handler.getWorld().setCurrentWorld(1);
		            	handler.getWorld().setFurthestWorldVisited(2);
		            	handler.getWorld().getEntityManager().getPlayer().setPos(handler.getWorld().getEntityManager().getPlayer().getStartX(), 
		            			handler.getWorld().getEntityManager().getPlayer().getStartY());
		            	WorldLoadState.setGoingDown(true);
		            	State.setPreviousState(handler.getGame().gameState);
		            	State.setState(handler.getGame().worldLoadState);
		            	return;
	        		}
	        	}else {
	        		handler.getWorld().getEntityManager().getPlayer().setWarping(false);
	        		timer3 = null;
	        	}
        	}else if(handler.getWorld().getCurrentWorld() == 3) {
        		if(player.getX() >= 3168 && player.getY() >= 6240) {
	        		handler.getWorld().getEntityManager().getPlayer().setWarping(true);
	        		if(timer4 == null)
	        			timer4 = new Timer(1750, 1);
	        		timer4.tick();
	        		if(timer4.isCompleted()) {
	        			handler.getWorld().getEntityManager().getPlayer().setWarping(false);
		        		warping = true;
	        			timer4 = null;
		        		handler.getWorld().setAboutToChange(true);
		            	Save.saveGeneratedEntityData(handler, "save" + handler.getWorld().getLoadedSave());
		        		handler.getWorld().setCurrentWorld(4);
		            	handler.getWorld().getEntityManager().getPlayer().setPos(handler.getWorld().getEntityManager().getPlayer().getStartX(), 
		            			handler.getWorld().getEntityManager().getPlayer().getStartY());
		            	WorldLoadState.setGoingDown(false);
		            	State.setPreviousState(handler.getGame().gameState);
		            	State.setState(handler.getGame().worldLoadState);
		            	return;
	        		}
	        	}else {
	        		handler.getWorld().getEntityManager().getPlayer().setWarping(false);
	        		timer4 = null;
	        	}
        		if(player.getX() >= 3040 && player.getY() >= 6240 && player.getX() <= 3040 + player.getWidth()) {
	        		handler.getWorld().getEntityManager().getPlayer().setWarping(true);
	        		if(timer5 == null)
	        			timer5 = new Timer(1750, 1);
	        		timer5.tick();
	        		if(timer5.isCompleted()) {
	        			handler.getWorld().getEntityManager().getPlayer().setWarping(false);
		        		warping = true;
		        		timer5 = null;
		        		handler.getWorld().setAboutToChange(true);
		            	Save.saveGeneratedEntityData(handler, "save" + handler.getWorld().getLoadedSave());
		        		handler.getWorld().setCurrentWorld(2);
		            	handler.getWorld().setFurthestWorldVisited(3);
		            	handler.getWorld().getEntityManager().getPlayer().setPos(handler.getWorld().getEntityManager().getPlayer().getStartX(), 
		            			handler.getWorld().getEntityManager().getPlayer().getStartY());
		            	WorldLoadState.setGoingDown(true);
		            	State.setPreviousState(handler.getGame().gameState);
		            	State.setState(handler.getGame().worldLoadState);
		            	return;
	        		}
	        	}else {
	        		handler.getWorld().getEntityManager().getPlayer().setWarping(false);
	        		timer5 = null;
	        	}
        	}else if(handler.getWorld().getCurrentWorld() == 4) {
        		if(player.getX() >= 3040 && player.getY() >= 6240 && player.getX() <= 3040 + player.getWidth()) {
	        		handler.getWorld().getEntityManager().getPlayer().setWarping(true);
	        		if(timer6 == null)
	        			timer6 = new Timer(1750, 1);
	        		timer6.tick();
	        		if(timer6.isCompleted()) {
	        			handler.getWorld().getEntityManager().getPlayer().setWarping(false);
		        		warping = true;
		        		timer6 = null;
		        		handler.getWorld().setAboutToChange(true);
		            	Save.saveGeneratedEntityData(handler, "save" + handler.getWorld().getLoadedSave());
		        		handler.getWorld().setCurrentWorld(3);
		            	handler.getWorld().setFurthestWorldVisited(4);
		            	handler.getWorld().getEntityManager().getPlayer().setPos(handler.getWorld().getEntityManager().getPlayer().getStartX(), 
		            			handler.getWorld().getEntityManager().getPlayer().getStartY());
		            	WorldLoadState.setGoingDown(true);
		            	State.setPreviousState(handler.getGame().gameState);
		            	State.setState(handler.getGame().worldLoadState);
		            	return;
	        		}
	        	}else {
	        		handler.getWorld().getEntityManager().getPlayer().setWarping(false);
	        		timer6 = null;
	        	}
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
