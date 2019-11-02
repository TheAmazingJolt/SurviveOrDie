package entities.statics;

import gfx.Assets;
import items.Item;

import java.awt.Color;
import java.awt.Graphics;
import main.Handler;

// Referenced classes of package entities.statics:
//            StaticEntity

public class Tree extends StaticEntity
{

	private static int maxHealth = 15;
	
    public Tree(Handler handler, float x, float y, int id)
    {
        super(handler, x, y, 64, 128, maxHealth, id, "Tree");
        this.id = id;
        this.health = maxHealth;
        bounds.x = 10;
        bounds.y = (int)((float)height / 1.5F);
        bounds.width = width - 20;
        bounds.height = (int)((float)height - (float)height / 1.5F);
    }

    public void tick()
    {
        if(!active)
        {
            bounds.width = 0;
            bounds.height = 0;
            return;
        }
        bounds.width = width - 20;
        bounds.height = (int)((float)height - (float)height / 1.5F);
        if(!attacked)
            health = 10;
    }

    public void die()
    {
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)x + 16, (int)y + 64, false, false, false));
    }

    public void render(Graphics g)
    {
        g.drawImage(Assets.tree, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
    	if(handler.getGame().isDebug()) {
    		g.setColor(Color.WHITE);
    	    g.drawRect((int) (this.getCollisionBounds(0.0f, 0.0f).x - handler.getGameCamera().getxOffset()), (int) (this.getCollisionBounds(0.0f, 0.0f).y - handler.getGameCamera().getyOffset()), this.getCollisionBounds(0.0f, 0.0f).width, this.getCollisionBounds(0.0f, 0.0f).height);
    	    g.setColor(null);
    	}
    }

}
