package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gfx.Animation;
import gfx.Assets;
import items.Item;
import main.Handler;

public class Penguin extends Creature
{

	private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;
    
    private static int maxHealth = 20;
    private static int startHealth;
	
    public Penguin(Handler handler, float x, float y, int id)
    {
        super(handler, x, y, 64, 64, maxHealth, id, "Penguin");
        this.id = id;
        startX = x;
        startY = y;
        this.health = maxHealth;
        startHealth = this.health;
        bounds.x = 20;
        bounds.y = 30;
        bounds.width = 20;
        bounds.height = 20;
        animDown = new Animation(500, Assets.penguin_down);
        animUp = new Animation(500, Assets.penguin_up);
        animLeft = new Animation(500, Assets.penguin_left);
        animRight = new Animation(500, Assets.penguin_right);
        this.isPassive = true;
        this.isWandering = true;
        this.speed = 2f;
    }

    public void tick()
    {
        if(!active)
        {
            bounds.width = 0;
            bounds.height = 0;
            return;
        }
        if(health != startHealth && health >= startHealth)
            health = maxHealth;
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        if(this.isFollowing) {
        	follow(0, 100F);
        }else if(this.isWandering) {
        	wander();
        }
        move();
    }
    
    public void hurt(int amt)
    {
    	attacked = true;
        health -= amt;
        if(health <= 0)
        {
            active = false;
            die();
        }
    }

    public void die()
    {
    	handler.getWorld().getItemManager().addItem(Item.snow.createNew((int) x, (int) y, false, false, false));
    }

    public void render(Graphics g)
    {
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        if(handler.getGame().isDebug()) {
    		g.setColor(Color.WHITE);
    	    g.drawRect((int) (this.getCollisionBounds(0.0f, 0.0f).x - handler.getGameCamera().getxOffset()), (int) (this.getCollisionBounds(0.0f, 0.0f).y - handler.getGameCamera().getyOffset()), this.getCollisionBounds(0.0f, 0.0f).width, this.getCollisionBounds(0.0f, 0.0f).height);
    	    g.setColor(null);
    	}
    }

    private BufferedImage getCurrentAnimationFrame()
    {
        if(xMove < 0.0F)
            return animLeft.getCurrentFrame();
        if(xMove > 0.0F)
            return animRight.getCurrentFrame();
        if(yMove < 0.0F)
            return animUp.getCurrentFrame();
        else
            return animDown.getCurrentFrame();
    }

}