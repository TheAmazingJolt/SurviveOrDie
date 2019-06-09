package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import gfx.Animation;
import gfx.Assets;
import items.Item;
import main.Handler;
import tiles.Tile;
import utils.Timer;

public class HellZombie extends Creature
{

	private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;
    
    private static int maxHealth = 25;
    private static int startHealth;
    
    private long attackCooldown;
    
    private Player player;
    private Random random = new Random();
    
    private Timer timer;
	
    public HellZombie(Handler handler, float x, float y, int id, Player player)
    {
        super(handler, x, y, 64, 64, maxHealth, id);
        this.id = id;
        this.player = player;
        startX = x;
        startY = y;
        attackCooldown = 200L;
        this.health = maxHealth;
        startHealth = this.health;
        bounds.x = 22;
        bounds.y = 30;
        bounds.width = 20;
        bounds.height = 34;
        this.isFollowing = true;
        animDown = new Animation(500, Assets.hell_zombie_down);
        animUp = new Animation(500, Assets.hell_zombie_up);
        animLeft = new Animation(500, Assets.hell_zombie_left);
        animRight = new Animation(500, Assets.hell_zombie_right);
        this.following = handler.getWorld().getEntityManager().getPlayer();
        this.newSpeed = player.getSpeed() - (player.getSpeed() / 10);
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
        checkAttacks();
        move();
        checkCurrentTile();
        for(int i = 0; i < Tile.getTiles().size(); i++) {
        	Tile t = Tile.getTiles().get(i);
        	if(i == (tileY * 104) + tileX) {
        		if(t.getId() == 4)
        			this.speed = (float) (DEFAULT_SPEED * 0.75);
        		else
        			this.speed = newSpeed;
       	 	}
        }
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
        	this.follow(arSize, 150F);
        }else if(this.isWandering) {
        	wander();
        }
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0.0F, 0.0F).intersects(ar))
            handler.getWorld().getEntityManager().getPlayer().hurt(3);
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
        handler.getWorld().getItemManager().addItem(Item.rottenFleshItem.createNew((int)x, (int)y, false, false, false));
        handler.getWorld().getItemManager().addItem(Item.rottenFleshItem.createNew((int)x, (int)y, false, false, false));
        if(random.nextInt(75) == 1) {
        	player.getInventory().addItem(Item.speedUpgrade, 1); 
        }
        player.setKilledEnemies(player.getKilledEnemies() + 1);
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