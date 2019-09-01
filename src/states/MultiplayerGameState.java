package states;

import java.awt.Graphics;

import main.Handler;

// Referenced classes of package states:
//            State

public class MultiplayerGameState extends State
{
	
    private boolean canPause;
	
    public MultiplayerGameState(Handler handler)
    {
        super(handler);
        
        canPause = true; 
    }

    public void tick()
    {
//        if(handler.getWorld().getEntityManager().getPlayer().getInventory().isActive())
//            canPause = false;
//        else
//            canPause = true;
//        if(handler.getKeyManager().keyJustPressed(27) && canPause)
//            State.setState(handler.getGame().pauseState);
//        if(handler.getWorld().getEntityManager().getPlayer().getKilledBosses() <= 1 && 
//        		handler.getWorld().getEntityManager().getPlayer().getX() >= 1079 &&
//        		handler.getWorld().getEntityManager().getPlayer().getY() >= 2239) {
//        	handler.getWorld().setCurrentWorld(handler.getWorld().getEntityManager().getPlayer().getKilledBosses() + 1);
//        	//1000,2100
//        	State.setState(handler.getGame().worldLoadState);
//          }
//        handler.getWorld().tick();
    }

    public void render(Graphics g)
    {
//        handler.getWorld().render(g);
    }

    
}
