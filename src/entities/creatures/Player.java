package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Entity;
import gfx.Animation;
import gfx.Assets;
import gfx.Text;
import inventory.Grave;
import inventory.Help;
import inventory.Hotbar;
import inventory.Inventory;
import inventory.LargeInventory;
import items.Item;
import items.Upgrade;
import main.Handler;
import states.State;
import tiles.Tile;
import tiles.structures.Structure;
import tiles.structures.WoodStructure;
import utils.Timer;

// Referenced classes of package entities.creatures:
//            Creature

public class Player extends Creature
{

	private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;
    
    private long attackCooldown;
    private long attackCooldown2;
    
    private static int killedEnemies;
    private static int killedBosses = 0;
    private static int maxHealth;
    private int playerId;
    private int currentDialougueFran = 1;
    private int currentDialougueSierra = 1;
    private int currentDialougueChris = 1;
    
    private static ArrayList<Upgrade> upgrades;
    
    private float startX;
    private float startY;
    
    private Inventory inventory;
    private Grave grave;
    private Help help;
    private Hotbar hotbar;
    private LargeInventory weapons;
    
    private Rectangle ar;
    
    private String directionMoving = "";
    
    private Timer timer;
    private Timer timer2;
	
    public Player(Handler handler, float x, float y, int playerId, int id)
    {
        super(handler, x, y, 64, 64, 10, id, "Player");
        this.playerId = playerId;
        attackCooldown = 200L;
        attackCooldown2 = 250;
        startX = x;
        startY = y;
        health = 10;
        bounds.x = 22;
        bounds.y = 30;
        bounds.width = 20;
        bounds.height = 34;
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animLeft = new Animation(500, Assets.player_left);
        animRight = new Animation(500, Assets.player_right);
        inventory = new Inventory(handler);
        hotbar = new Hotbar(handler);
        help = new Help(handler);
        weapons = new LargeInventory(handler);
        upgrades = new ArrayList<Upgrade>();
        maxHealth = 10;
        ar = new Rectangle();
    }

    public void tick()
    {
        if(health <= 0)
            die();
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        if(playerId == 1) {
        	if(handler.getGame().getGameType().contains("creative"))
        		this.health = 100;
        	if(grave != null) {
        		if(!grave.isCollected())
        			grave.tick();
        		else if(grave.isCollected())
        			grave = null;
        	}
        	getInput();
            move();
            handler.getGameCamera().centerOnEntity(this);
            checkAttacks();
            if(handler.getMouseManager().isLeftPressed() && !inventory.isActive()) {
                checkRangedAttacks();
                buildStructure();
        		if(handler.getGame().isDebug()) {
        			checkClicked();
       			}
            }
            inventory.tick();
            hotbar.tick();
            help.tick();
            weapons.tick();
            for(Upgrade u : upgrades) {
            	if(u.getEffectType().isInventoryExpansion()) {
            		if(!inventory.isLargeUnlocked()){
            			inventory.setLargeUnlocked(true);
            		}
           	 	}else if(u.getEffectType().isSpeed()) {
           	 		this.newSpeed = this.DEFAULT_SPEED * u.getEffectType().getMultiplier();
           	 	}
            }
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
    }

    private void checkClicked() {
    	float mouseX = handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset();
    	float mouseY = handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset();
    	int tX = (int) Math.floor(mouseX/64);
    	int tY = (int) Math.floor(mouseY/64);
	    System.out.println(tX + " " + tY);
    }
    
    private void buildStructure() {
    	float mouseX = handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset();
    	float mouseY = handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset();
    	int tileX = (int) Math.floor(mouseX/64);
    	int tileY = (int) Math.floor(mouseY/64);
    	for(Item i : hotbar.getInventoryItems()) {
    		if(i.isTile()) {
    			if(i.getTileType().getId() == 9) {
    				Structure s = new WoodStructure(9);
    				Tile.getStructures().add(s);
    				s.setLocation(tileX, tileY);
    				inventory.removeItem(i, 1);
           		 	try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    				return;
    			}
    		}
    	}
    }
    
    private void checkRangedAttacks() {
    	if(inventory.isActive())
    		return;
    	if(playerId == 1) {
        	if(timer2 == null)
        		timer2 = new Timer(attackCooldown2, 1);
        	timer2.tick();
        	if(!timer2.isCompleted()) {
        		return;
        	}else if(timer2.isCompleted()) {
        		timer2 = null;
        	}
            if(inventory.isActive())
                return;
            float mouseX = handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset();
           	float mouseY = handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset();
           	if(mouseX >= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height) {
           		directionMoving = "right";
           	}else if(mouseX <= this.x && mouseY >= this.y && mouseY <= this.y + this.height) {
           		directionMoving = "left";
           	}else if(mouseY >= this.y + this.height && mouseX >= this.x && mouseX <= this.x + this.width) {
           		directionMoving = "down";
           	}else if(mouseY <= this.y && mouseX >= this.x && mouseX <= this.x + this.width) {
           		directionMoving = "up";
           	}else {
           		directionMoving = "";
           	}
            if(hotbar.getInventoryItems().size() > 0) {
            	hotbar.useRanged(hotbar.getInventoryItems().get(hotbar.getSelectedItem()));
            }
    	}
    }
    
    private void checkAttacks()
    {
    	if(playerId == 1) {
    		if(health <= 0)
                die();
            if(hotbar.getInventoryItems().size() > 0) {
            	if(hotbar.getSelectedItem() >= hotbar.getInventoryItems().size()) {
            		return;
            	}
                this.attackStrength = ((Item)hotbar.getInventoryItems().get(hotbar.getSelectedItem())).getDamage();
            }
            else
                this.attackStrength = 1;
        	if(timer == null)
        		timer = new Timer(attackCooldown, 1);
        	timer.tick();
        	if(!timer.isCompleted()) {
        		return;
        	}else if(timer.isCompleted()) {
        		timer = null;
        	}
            if(inventory.isActive())
                return;
            Rectangle cb = getCollisionBounds(0.0F, 0.0F);
            int arSize = 20;
            ar.width = arSize;
            ar.height = arSize;
            if(yMove < 0.0F && xMove == 0)
            {
                ar.x = (cb.x + cb.width / 2) - arSize / 2;
                ar.y = cb.y - arSize;
            } else
            if(yMove > 0.0F && xMove == 0|| yMove == 0 && xMove == 0)
            {
                ar.x = (cb.x + cb.width / 2) - arSize / 2;
                ar.y = cb.y + cb.height;
                
            }
            
            if(xMove < 0.0F && yMove == 0)
            {
                ar.x = cb.x - arSize;
                ar.y = (cb.y + cb.height / 2) - arSize / 2;
            } else
            if(xMove > 0.0F && yMove == 0)
            {
                ar.x = cb.x + cb.width;
                ar.y = (cb.y + cb.height / 2) - arSize / 2;
            }
            
            if(xMove > 0.0f && yMove < 0.0f) {
            	ar.x = cb.x + cb.width;
            	ar.y = cb.y - arSize;
            }else if(xMove > 0.0f && yMove > 0.0f) {
            	ar.x = cb.x + cb.width;
            	ar.y = cb.y + cb.height;
            }
            
            if(xMove < 0.0f && yMove < 0.0f) {
            	ar.x = cb.x - cb.width;
            	ar.y = cb.y - arSize;
            }else if(xMove < 0.0f && yMove > 0.0f) {
            	ar.x = cb.x - cb.width;
            	ar.y = cb.y + cb.height;
            }
            

            if(handler.getMouseManager().isLeftPressed()) {
                checkTiles();
            	if(handler.getWorld().getCurrentWorld() == 1) {
                	for(Entity e : handler.getWorld().getEntityManager().getEntities())
                	{
                		if(e != this && e.getCollisionBounds(0.0F, 0.0F).intersects(ar))
                		{
                			int healthBefore = health;
                			e.hurt(this.attackStrength);
                			if(health != healthBefore)
                				health = healthBefore;
                			else
                				return;
                		}
                	}
                	for(Entity e : handler.getWorld().getEntityManager().getE1overflow1())
                	{
                		if(e != this && e.getCollisionBounds(0.0F, 0.0F).intersects(ar))
                		{
                			int healthBefore = health;
                			e.hurt(this.attackStrength);
                			if(health != healthBefore)
                				health = healthBefore;
                			else
                				return;
                		}
                	}
                	for(Entity e : handler.getWorld().getEntityManager().getE1overflow2())
                	{
                		if(e != this && e.getCollisionBounds(0.0F, 0.0F).intersects(ar))
                		{
                			int healthBefore = health;
                			e.hurt(this.attackStrength);
                			if(health != healthBefore)
                				health = healthBefore;
                			else
                				return;
                		}
                	}
                }else if(handler.getWorld().getCurrentWorld() == 2) {
                	for(Entity e : handler.getWorld().getEntityManager().getEntities2())
                	{
                		if(e != this && e.getCollisionBounds(0.0F, 0.0F).intersects(ar))
                		{
                			int healthBefore = health;
                			e.hurt(this.attackStrength);
                			if(health != healthBefore)
                				health = healthBefore;
                			else
                				return;
                		}
                	}
                	for(Entity e : handler.getWorld().getEntityManager().getE2overflow1())
                	{
                		if(e != this && e.getCollisionBounds(0.0F, 0.0F).intersects(ar))
                		{
                			int healthBefore = health;
                			e.hurt(this.attackStrength);
                			if(health != healthBefore)
                				health = healthBefore;
                			else
                				return;
                		}
                	}
                	for(Entity e : handler.getWorld().getEntityManager().getE2overflow2())
                	{
                		if(e != this && e.getCollisionBounds(0.0F, 0.0F).intersects(ar))
                		{
                			int healthBefore = health;
                			e.hurt(this.attackStrength);
                			if(health != healthBefore)
                				health = healthBefore;
                			else
                				return;
                		}
                	}
                }else if(handler.getWorld().getCurrentWorld() == 3) {
                	for(Entity e : handler.getWorld().getEntityManager().getEntities3())
                	{
                		if(e != this && e.getCollisionBounds(0.0F, 0.0F).intersects(ar))
                		{
                			int healthBefore = health;
                			e.hurt(this.attackStrength);
                			if(health != healthBefore)
                				health = healthBefore;
                			else
                				return;
                		}
                	}
                	for(Entity e : handler.getWorld().getEntityManager().getE3overflow1())
                	{
                		if(e != this && e.getCollisionBounds(0.0F, 0.0F).intersects(ar))
                		{
                			int healthBefore = health;
                			e.hurt(this.attackStrength);
                			if(health != healthBefore)
                				health = healthBefore;
                			else
                				return;
                		}
                	}
                	for(Entity e : handler.getWorld().getEntityManager().getE3overflow2())
                	{
                		if(e != this && e.getCollisionBounds(0.0F, 0.0F).intersects(ar))
                		{
                			int healthBefore = health;
                			e.hurt(this.attackStrength);
                			if(health != healthBefore)
                				health = healthBefore;
                			else
                				return;
                		}
                	}
                }else if(handler.getWorld().getCurrentWorld() == 4) {
                	for(Entity e : handler.getWorld().getEntityManager().getEntities4())
                	{
                		if(e != this && e.getCollisionBounds(0.0F, 0.0F).intersects(ar))
                		{
                			int healthBefore = health;
                			e.hurt(this.attackStrength);
                			if(health != healthBefore)
                				health = healthBefore;
                			else
                				return;
                		}
                	}
                	for(Entity e : handler.getWorld().getEntityManager().getE4overflow1())
                	{
                		if(e != this && e.getCollisionBounds(0.0F, 0.0F).intersects(ar))
                		{
                			int healthBefore = health;
                			e.hurt(this.attackStrength);
                			if(health != healthBefore)
                				health = healthBefore;
                			else
                				return;
                		}
                	}
                	for(Entity e : handler.getWorld().getEntityManager().getE4overflow2())
                	{
                		if(e != this && e.getCollisionBounds(0.0F, 0.0F).intersects(ar))
                		{
                			int healthBefore = health;
                			e.hurt(this.attackStrength);
                			if(health != healthBefore)
                				health = healthBefore;
                			else
                				return;
                		}
                	}
                }
            }
    	}
    	if(playerId == 2) {
    		if(health <= 0)
                die();
    	}
    }

    private void checkTiles() {
    	if(xMove > 0.0F)
        {
            int tx = (int)(x + xMove + (float)bounds.x + (float)bounds.width) / 64;
            int ty = (int)(y + (float)bounds.y) / 64;
            int ty2 = (int)(y + (float)bounds.y + (float)bounds.height) / 64;
            if(collisionWithStructure(tx, ty) && collisionWithStructure(tx, ty2)) {
            	if(tile.getLocationX() == tx) {
        			if(tile.getLocationY() == ty || tile.getLocationY() == ty2) {
        				tile.hurt(attackStrength);

        			}
        		}
            }
        } else
        if(xMove < 0.0F)
        {
            int tx = (int)(x + xMove + (float)bounds.x) / 64;
            int ty = (int)(y + (float)bounds.y) / 64;
            int ty2 = (int)(y + (float)bounds.y + (float)bounds.height) / 64;
            if(collisionWithStructure(tx, ty) && collisionWithStructure(tx, ty2)) {
            	if(tile.getLocationX() == tx) {
        			if(tile.getLocationY() == ty || tile.getLocationY() == ty2) {
        				tile.hurt(attackStrength);
        			}
        		}
            }
        }
    	if(yMove < 0.0F)
        {
            int ty = (int)(y + yMove + (float)bounds.y) / 64;
            int tx = (int)(x + (float)bounds.x) / 64;
            int tx2 = (int)(x + (float)bounds.x + (float)bounds.width) / 64;
            if(collisionWithStructure(tx, ty) && collisionWithStructure(tx2, ty)) {
            	if(tile.getLocationX() == tx || tile.getLocationX() == tx2) {
        			if(tile.getLocationY() == ty) {
        				tile.hurt(attackStrength);
        			}
        		}
            }
        } else
        if(yMove > 0.0F)
        {
            int ty = (int)(y + yMove + (float)bounds.y + (float)bounds.height) / 64;
            int tx = (int)(x + (float)bounds.x) / 64;
            int tx2 = (int)(x + (float)bounds.x + (float)bounds.width) / 64;
            if(collisionWithStructure(tx, ty) && collisionWithStructure(tx2, ty)) {
            	if(tile.getLocationX() == tx || tile.getLocationX() == tx2) {
        			if(tile.getLocationY() == ty) {
        				tile.hurt(attackStrength);
        			}
        		}
            }
        }
    }
    
    public void hurtMe(int dmg, Creature c)
    {
        health -= dmg;
        if(health <= 0)
        {
            active = false;
            die();
        }
    }

    public void die()
    {
        hotbar.getInventoryItems().clear();
        if(grave == null)
        	grave = new Grave(inventory.getInventoryItems(), this, this.x, this.y, handler);
    	inventory.clear();
//        for(Iterator<Item> iterator = inventory.getInventoryItems().iterator(); iterator.hasNext();)
//        {
//            Item i = (Item)iterator.next();
//            droppedItems.add(i);
//            if(inventory.getInventoryItems().size() == droppedItems.size())
//            {
//                inventory.dropItem(droppedItems);
//                State.setState(handler.getGame().deathState);
//                active = true;
//                health = maxHealth;
//                x = startX;
//                y = startY;
//                Item ii;
//                for(Iterator<Item> iterator1 = droppedItems.iterator(); iterator1.hasNext(); ii.setCount(0))
//                    ii = (Item)iterator1.next();
//
//                droppedItems.removeAll(droppedItems);
//                inventory.getInventoryItems().removeAll(inventory.getInventoryItems());
//                return;
//            }
//        }
        
//        for(Item item : inventory.getInventoryItems()) {
//        	inventory.dropItem(item, item.getCount());
//        }
        
        active = true;
        health = maxHealth;
        x = startX;
        y = startY;
        State.setState(handler.getGame().deathState);
    }

    private void getInput()
    {
    	if(playerId == 1) {
    		xMove = 0.0F;
            yMove = 0.0F;
            if(inventory.isActive())
                return;
            if(handler.getKeyManager().up)
                yMove = -speed;
            if(handler.getKeyManager().down) {
            	yMove = speed;
            	}
            if(handler.getKeyManager().left)
                xMove = -speed;
            if(handler.getKeyManager().right)
                xMove = speed;
    	}
    }

    public void render(Graphics g)
    {
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        if(grave != null && !grave.isCollected())
        	grave.render(g);
        if(handler.getGame().isDebug()) {
        	g.setColor(Color.WHITE);
        	g.drawRect((int) (ar.x - handler.getGameCamera().getxOffset()), (int) (ar.y - handler.getGameCamera().getyOffset()), ar.width, ar.height);
        	g.setColor(null);
        }
    }

    public void postRender(Graphics g)
    {
    	if(playerId == 1) {
    		Text.drawString(g, (new StringBuilder("Health: ")).append(Integer.toString(health)).toString(), 10, 20, false, Color.WHITE, Assets.font20);
    		Text.drawString(g, (new StringBuilder("X: ")).append(Integer.toString((int)x)).append(" Y: ").append(Integer.toString((int)y)).toString(), 770, 15, false, Color.WHITE, Assets.font20);
        }
        inventory.render(g);
        hotbar.render(g);
        help.render(g);
        weapons.render(g);
        
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

    public Inventory getInventory()
    {
        return inventory;
    }

	public Hotbar getHotbar() {
		return hotbar;
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public float getStartX() {
		return startX;
	}

	public void setStartX(float startX) {
		this.startX = startX;
	}

	public float getStartY() {
		return startY;
	}

	public void setStartY(float startY) {
		this.startY = startY;
	}

	public LargeInventory getWeapons() {
		return weapons;
	}

	public int getKilledEnemies()
    {
        return killedEnemies;
    }

    public long getAttackCooldown2() {
		return attackCooldown2;
	}

	public void setAttackCooldown2(long attackCooldown2) {
		this.attackCooldown2 = attackCooldown2;
	}

	public ArrayList<Upgrade> getUpgrades() {
		return upgrades;
	}

	public void setKilledEnemies(int killedEnemy)
    {
        killedEnemies = killedEnemy;
    }

    public int getKilledBosses()
    {
        return killedBosses;
    }

    public void setKilledBosses(int killedBoss)
    {
        killedBosses = killedBoss;
    }

    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }
    public void setMaxHealth(int h)
    {
        maxHealth = h;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

	public String getDirectionMoving() {
		return directionMoving;
	}
	
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
		this.startX = x;
		this.startY = y;
	}

	public int getCurrentDialougueF() {
		return currentDialougueFran;
	}

	public void setCurrentDialougueF(int currentDialougueF) {
		this.currentDialougueFran = currentDialougueF;
	}
	
	public int getCurrentDialougueS() {
		return currentDialougueSierra;
	}

	public void setCurrentDialougueS(int currentDialougueS) {
		this.currentDialougueSierra = currentDialougueS;
	}
	
	public int getCurrentDialougueC() {
		return currentDialougueChris;
	}

	public void setCurrentDialougueC(int currentDialougueC) {
		this.currentDialougueChris = currentDialougueC;
	}

}