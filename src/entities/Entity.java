package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import entities.creatures.npcs.NPC;
import main.Handler;

public abstract class Entity
{
	
	public static final int DEFAULT_HEALTH = 10;
    protected Handler handler;
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected int health;
    protected int maxHealth;
    protected int startHealth;
    protected float startX;
    protected float startY;
    protected boolean active;
    protected boolean attacked;
    protected boolean ammo;
    protected Rectangle bounds;
    protected int id;
    protected boolean isNpc = false;
    
    protected String name = "";

    public Entity(Handler handler, float x, float y, int width, int height, int health, int id, String name)
    {
        active = true;
        attacked = false;
        this.handler = handler;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health;
        maxHealth = health;
        this.startHealth = health;
        this.startX = x;
        this.startY = y;
        this.id = id;
        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void die();

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void revive()
    {
        attacked = false;
        active = true;
        health = startHealth;
        x = startX;
        y = startY;
    }

    public void suicide() {
    	this.active = false;
    }
    
    public void setStartData() {
    	this.health = startHealth;
    	this.x = startX;
    	this.y = startY;
    }
    
    public void hurt(int amt)
    {
        this.attacked = true;
        health -= amt;
        if(health <= 0)
        {
            active = false;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset)
    {
    	if(handler.getWorld().getCurrentWorld() == 1) {
    		for(Entity e : handler.getWorld().getEntityManager().getEntities())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow1())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow2())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow3())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow4())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow5())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow6())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow7())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow8())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow9())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow10())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow11())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow12())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow13())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow14())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow15())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE1overflow16())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(NPC n : handler.getWorld().getEntityManager().getNpcs()) {
    			if(!n.equals(this) && n.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
    		}
    	}else if(handler.getWorld().getCurrentWorld() == 2) {
    		for(Entity e : handler.getWorld().getEntityManager().getEntities2())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow1())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow2())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow3())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow4())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow5())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow6())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow7())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow8())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow9())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow10())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow11())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow12())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow13())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow14())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow15())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE2overflow16())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(NPC n : handler.getWorld().getEntityManager().getNpcs2()) {
    			if(!n.equals(this) && n.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
    		}
    	}else if(handler.getWorld().getCurrentWorld() == 3) {
    		for(Entity e : handler.getWorld().getEntityManager().getEntities3())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow1())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow2())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow3())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow4())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow5())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow6())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow7())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow8())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow9())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow10())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow11())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow12())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow13())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow14())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow15())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE3overflow16())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    	}else if(handler.getWorld().getCurrentWorld() == 4) {
    		for(Entity e : handler.getWorld().getEntityManager().getEntities4())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow1())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow2())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow3())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow4())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow5())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow6())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow7())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow8())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow9())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow10())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow11())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow12())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow13())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow14())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow15())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    		for(Entity e : handler.getWorld().getEntityManager().getE4overflow16())
            {
    			if(handler.getWorld().getEntityManager().getPlayer2() != null && e.equals(handler.getWorld().getEntityManager().getPlayer2()))
    				return false;
                if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
    	}
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset)
    {
        return new Rectangle((int)(x + (float)bounds.x + xOffset), (int)(y + (float)bounds.y + yOffset), bounds.width, bounds.height);
    }

    public float getX()
    {
        return x;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public int getId() {
		return id;
	}

	public boolean isAmmo() {
		return ammo;
	}

	public void setAmmo(boolean ammo) {
		this.ammo = ammo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public boolean isAttacked()
    {
        return attacked;
    }

    
}
