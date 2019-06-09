package entities.statics;

import gfx.Assets;
import items.Item;

import java.awt.Color;
import java.awt.Graphics;
import main.Handler;

// Referenced classes of package entities.statics:
//            StaticEntity

public class Stone extends StaticEntity
{
	
	private static int maxHealth = 20;
	
    public Stone(Handler handler, float x, float y, int id)
    {
        super(handler, x, y, 64, 64, maxHealth, id);
        this.id = id;
        this.health = maxHealth;
        bounds.x = 3;
        bounds.y = (int)((float)height / 2.0F);
        bounds.width = width - 6;
        bounds.height = (int)((float)height - (float)height / 2.0F);
    }

    public void tick()
    {
        if(!active)
        {
            bounds.width = 0;
            bounds.height = 0;
            return;
        }
        bounds.width = width - 6;
        bounds.height = (int)((float)height - (float)height / 2.0F);
    }

    public void die()
    {
        handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int)x, (int)y, false, false, false));
    }

    public void render(Graphics g)
    {
        g.drawImage(Assets.rock, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        if(handler.getGame().isDebug()) {
    		g.setColor(Color.WHITE);
    	    g.drawRect((int) (this.getCollisionBounds(0.0f, 0.0f).x - handler.getGameCamera().getxOffset()), (int) (this.getCollisionBounds(0.0f, 0.0f).y - handler.getGameCamera().getyOffset()), this.getCollisionBounds(0.0f, 0.0f).width, this.getCollisionBounds(0.0f, 0.0f).height);
    	    g.setColor(null);
    	}
    }
}
