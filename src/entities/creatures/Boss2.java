package entities.creatures;

import items.Item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gfx.Animation;
import gfx.Assets;
import main.Handler;
import utils.Timer;

public class Boss2 extends Creature
{

	private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;
    private static int maxHealth = 50;
    private static int startHealth;
    private long attackCooldown;
    
    private Timer timer;
	
    public Boss2(Handler handler, float x, float y, int health, int id)
    {
        super(handler, x, y, 128, 128, maxHealth,id, "Boss2");
        this.id = id;
        attackCooldown = 350L;
        startHealth = health;
        this.health = health;
        bounds.x = 30;
        bounds.y = 30;
        bounds.width = 70;
        bounds.height = 74;
        animDown = new Animation(500, Assets.ashy_zombie_down);
        animUp = new Animation(500, Assets.ashy_zombie_up);
        animLeft = new Animation(500, Assets.ashy_zombie_left);
        animRight = new Animation(500, Assets.ashy_zombie_right);
        this.speed = handler.getWorld().getEntityManager().getPlayer().getSpeed() - (handler.getWorld().getEntityManager().getPlayer().getSpeed() / 10);
        this.following = handler.getWorld().getEntityManager().getPlayer();
        this.isFollowing = true;
    }

    public void tick()
    {
        if(!active)
        {
            bounds.width = 0;
            bounds.height = 0;
            return;
        }
        if(handler.getWorld().getEntityManager().getPlayer().getKilledBosses() > 1)
            suicide();
        if(health != startHealth && health >= startHealth)
            health = maxHealth;
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        move();
        checkAttacks();
    }

    private void checkAttacks()
    {
    	if(timer == null)
    		timer = new Timer(attackCooldown, 1);
    	timer.tick();
    	if(!timer.isCompleted()) {
    		return;
    	}else if(timer.isCompleted()) {
    		timer = null;
    	}
        cb = getCollisionBounds(0.0F, 0.0F);
        ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;
        if(this.isFollowing) {
        	follow(arSize, 150F);
        }else if(this.isWandering) {
        	wander();
        }
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0.0F, 0.0F).intersects(ar))
            handler.getWorld().getEntityManager().getPlayer().hurt(5);
    }

    public void hurt(int amt)
    {
        health -= amt;
        if(health <= 0)
        {
            active = false;
            die();
        }
    }

    public void die()
    {
        for(int i = 0; i < 5; i++)
            handler.getWorld().getItemManager().addItem(Item.rottenFleshItem.createNew((int)x - 2, (int)y, false, false, false));
        handler.getWorld().getItemManager().addItem(Item.steelBarItem.createNew((int)x-2, (int)y, false, false, false));
        handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(27, 1);
        handler.getWorld().getEntityManager().getPlayer().setKilledBosses(2);
    }

    public void suicide()
    {
        health = 0;
        active = false;
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