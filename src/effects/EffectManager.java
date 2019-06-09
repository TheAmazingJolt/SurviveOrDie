package effects;

import java.awt.Graphics;
import java.util.ArrayList;

import main.Handler;

public class EffectManager
{

	private Handler handler;
    private ArrayList<Effect> effects;
	
    public EffectManager(Handler handler)
    {
        this.handler = handler;
        effects = new ArrayList<Effect>();
    }

    public void tick()
    {
    	
    }

    public void render(Graphics g)
    {
    	
    }

    public void addEffect(Effect e)
    {
        effects.add(e);
    }

    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }

    
}