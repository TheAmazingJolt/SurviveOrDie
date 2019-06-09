package entities.creatures.npcs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sun.glass.events.KeyEvent;

import audio.Audio;
import entities.Entity;
import entities.creatures.npcs.tasks.Task;
import gfx.Animation;
import gfx.Assets;
import items.Item;
import main.Handler;
import utils.Countdown;
import utils.ReadFile;
import utils.Timer;

public class Francisco extends NPC
{

	private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;
    
    private Animation animDownAngry;
    private Animation animUpAngry;
    private Animation animLeftAngry;
    private Animation animRightAngry;
    
    private static int maxHealth = 2000;
    private static int startHealth;
    
    private Task killZombie;
    private Task potionCollect;
    private Task potionCraft;
    private Task killBoss;
    
    private boolean enraged = false;
    private boolean checked = false;
    private boolean withinArea = false;
	
    private Countdown timer;
    private Timer timer2;
    
    private Audio currentAudio;
    
    public Francisco(Handler handler, float x, float y, int id)
    {
        super(handler, x, y, 64, 64, maxHealth, id);
        this.id = id;
        startX = x;
        startY = y;
        this.health = maxHealth;
        startHealth = this.health;
        bounds.x = 20;
        bounds.y = 30;
        bounds.width = 20;
        bounds.height = 20;
        animDown = new Animation(500, Assets.fran_down);
        animUp = new Animation(500, Assets.fran_up);
        animLeft = new Animation(500, Assets.fran_left);
        animRight = new Animation(500, Assets.fran_right);
        animDownAngry = new Animation(500, Assets.angry_fran_down);
        animUpAngry = new Animation(500, Assets.angry_fran_up);
        animLeftAngry = new Animation(500, Assets.angry_fran_left);
        animRightAngry = new Animation(500, Assets.angry_fran_right);
        this.isPassive = true;
        this.isNpc = true;
		this.dialougue = ReadFile.readDialougue("/francisco/francisco.txt");
		this.playerresponses = ReadFile.readDialougue("/francisco/pfresponse.txt");
		this.face = Assets.fran_face;
		//this.speed = 5F;
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
        if(this.following == null) {
        	for(NPC n : handler.getWorld().getEntityManager().getNpcs2()) {
    			if(n.getId() == -4) {
    				this.following = n;
    			}
    		}
        }
        if(this.isFollowing && !withinArea) {
        	follow(0, 10000F);
        }
        move();
        checkBounds();
        if(isTalking)
        	talk();
        else if(completed)
    		checkTasks();
        if(!checked) {
    		if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF()-1 == 1) {
    			dialougue = ReadFile.readDialougue("/francisco/francisco2.txt");
    			playerresponses = ReadFile.readDialougue("/francisco/pfresponse2.txt");
    			currentDialougueNum = 0;
    			currentResponseNum = -1;
    			currentSpeaker = 0;
    			currentDialougueNum2 = 0;
    			handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(2);
    			checked = true;
    			return;
    		}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF()-1 == 2) {
    			dialougue = ReadFile.readDialougue("/francisco/francisco3.txt");
    			playerresponses = ReadFile.readDialougue("/francisco/pfresponse3.txt");
    			currentDialougueNum = 0;
    			currentResponseNum = -1;
    			currentSpeaker = 0;
    			currentDialougueNum2 = 0;
    			handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(3);
    			checked = true;
    			return;
    		}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF()-1 == 3) {
    			dialougue = ReadFile.readDialougue("/francisco/francisco4.txt");
    			playerresponses = ReadFile.readDialougue("/francisco/pfresponse4.txt");
    			currentDialougueNum = 0;
    			currentResponseNum = -1;
    			currentSpeaker = 0;
    			currentDialougueNum2 = 0;
    			handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(4);
    			checked = true;
    			return;
    		}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF()-1 == 4) {
    			dialougue = ReadFile.readDialougue("/francisco/francisco5.txt");
    			playerresponses = ReadFile.readDialougue("/francisco/pfresponse5.txt");
    			currentDialougueNum = 0;
    			currentResponseNum = -1;
    			currentSpeaker = 0;
    			currentDialougueNum2 = 0;
    			handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(5);
    			checked = true;
    			return;
    		}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF()-1 == 5) {
    			dialougue = ReadFile.readDialougue("/francisco/francisco6.txt");
    			playerresponses = ReadFile.readDialougue("/francisco/pfresponse6.txt");
    			currentDialougueNum = 0;
    			currentResponseNum = -1;
    			currentSpeaker = 0;
    			currentDialougueNum2 = 0;
    			handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(6);
    			checked = true;
    			return;
    		}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF()-1 == 6) {
    			dialougue.clear();
    			playerresponses.clear();
    			currentDialougueNum = 0;
    			currentResponseNum = -1;
    			currentSpeaker = 0;
    			currentDialougueNum2 = 0;
    			handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(7);
    			checked = true;
    			return;
    		}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF()-1 == 7) {
    			dialougue = ReadFile.readDialougue("/francisco/francisco7.txt");
    			playerresponses = ReadFile.readDialougue("/francisco/pfresponse7.txt");
    			currentDialougueNum = 0;
    			currentResponseNum = -1;
    			currentSpeaker = 0;
    			currentDialougueNum2 = 0;
    			handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(8);
    			checked = true;
    			return;
    		}
    		checked = true;
    		return;
        }
        setTextbox();
    }
    
    private void checkTasks() {
    	//System.out.println("eeeeee");
    	if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 2) {
    		if(killZombie == null) {
    			Entity zombieToKill = null;
        		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
        			if(e.getId() == 3) {
        				zombieToKill = e;
        			}else {
        				continue;
        			}
        		}
        		killZombie = new Task("1", zombieToKill, Item.rottenFleshItem, handler);
    		}else if(killZombie != null) {
    			killZombie.tick();
    			if(killZombie.isCompleted()) {
    				completed = false;
    			}
    		}
		}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 3) {
    		if(potionCollect == null) {
        		potionCollect = new Task("2", Item.crushedIronItem, Item.waterBottle, null, handler);
    		}else if(potionCollect != null) {
    			potionCollect.tick();
    			if(potionCollect.isCompleted()) {
    				completed = false;
    			}
    		}
		}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 4) {
    		if(potionCraft == null) {
    			potionCraft = new Task("3", null, Item.healingPotionItem, handler);
    		}else if(potionCraft != null) {
    			potionCraft.tick();
    			if(potionCraft.isCompleted()) {
    				completed = false;
    				handler.getWorld().getEntityManager().getPlayer().getInventory().removeItem(potionCraft.getToCollect(), 1);
    			}
    		}
		}if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 5) {
    		if(killBoss == null) {
    			Entity zombieToKill = null;
        		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
        			if(e.getId() == 4) {
        				zombieToKill = e;
        			}else {
        				continue;
        			}
        		}
        		killBoss = new Task("4", zombieToKill, null, handler);
    		}else if(killBoss != null) {
    			killBoss.tick();
    			if(killBoss.isCompleted()) {
    				completed = false;
    			}
    		}
		}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 7) {
			if(timer == null) {
				timer = new Countdown(30000L, "Francisco finds Sierra", handler);//make this 120000
			}else if(timer != null) {
				timer.tick();
				if(timer.isCompleted() && !withinArea) {
					if(this.x >= following.getX() && this.x <= following.getX() + 100) {
						if(this.y >= following.getY() && this.y <= following.getY() + 100) {
							withinArea = true;
							completed = false;
							xMove = 0;
							yMove = 0;
						}
					}
					this.isFollowing = true;
				}
			}
		}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 8) {
    		if(timer2 == null) {
    			timer2 = new Timer(1500L, 1);
    		}else if(timer2 != null) {
    			timer2.tick();
    			if(timer2.isCompleted()) {
    				for(NPC n : handler.getWorld().getEntityManager().getNpcs2()) {
    					if(n.getId() == -4) {
    						n.die();
    						completed = false;
    					}
    				}
    			}
    		}
		}
    }
    
    private void checkDialougue() {
        if(currentDialougueNum >= dialougue.size() || currentResponseNum >= playerresponses.size()) {
			isTalking = false;
			completed = true;
			currentDialougue = null;
			if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 1) {
				dialougue = ReadFile.readDialougue("/francisco/francisco2.txt");
				playerresponses = ReadFile.readDialougue("/francisco/pfresponse2.txt");
				currentDialougueNum = 0;
				currentResponseNum = -1;
				currentSpeaker = 0;
				currentDialougueNum2 = 0;
				handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(2);
			}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 2) {
				dialougue = ReadFile.readDialougue("/francisco/francisco3.txt");
				playerresponses = ReadFile.readDialougue("/francisco/pfresponse3.txt");
				currentDialougueNum = 0;
				currentResponseNum = -1;
				currentSpeaker = 0;
				currentDialougueNum2 = 0;
				handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(3);
			}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 3) {
				dialougue = ReadFile.readDialougue("/francisco/francisco4.txt");
				playerresponses = ReadFile.readDialougue("/francisco/pfresponse4.txt");
				currentDialougueNum = 0;
				currentResponseNum = -1;
				currentSpeaker = 0;
				currentDialougueNum2 = 0;
				handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(4);
			}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 4) {
				dialougue = ReadFile.readDialougue("/francisco/francisco5.txt");
				playerresponses = ReadFile.readDialougue("/francisco/pfresponse5.txt");
				currentDialougueNum = 0;
				currentResponseNum = -1;
				currentSpeaker = 0;
				currentDialougueNum2 = 0;
				handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(5);
			}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 5) {
				dialougue = ReadFile.readDialougue("/francisco/francisco6.txt");
				playerresponses = ReadFile.readDialougue("/francisco/pfresponse6.txt");
				currentDialougueNum = 0;
				currentResponseNum = -1;
				currentSpeaker = 0;
				currentDialougueNum2 = 0;
				handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(6);
			}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 6) {
				dialougue = ReadFile.readDialougue("/francisco/francisco7.txt");
				playerresponses = ReadFile.readDialougue("/francisco/pfresponse7.txt");
				currentDialougueNum = 0;
				currentResponseNum = -1;
				currentSpeaker = 0;
				currentDialougueNum2 = 0;
				handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(7);
			}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 7) {
				dialougue = ReadFile.readDialougue("/francisco/francisco8.txt");
				playerresponses = ReadFile.readDialougue("/francisco/pfresponse8.txt");
				currentDialougueNum = 0;
				currentResponseNum = -1;
				currentSpeaker = 0;
				currentDialougueNum2 = 0;
				handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(8);
			}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 8) {
				System.out.println("2");
				dialougue.clear();
				playerresponses.clear();
				currentDialougueNum = 0;
				currentResponseNum = -1;
				currentSpeaker = 0;
				currentDialougueNum2 = 0;
				die();
				handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(9);
			}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() == 9) {
				dialougue.clear();
				playerresponses.clear();
				currentDialougueNum = 0;
				currentResponseNum = -1;
				currentSpeaker = 0;
				currentDialougueNum2 = 0;
				handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueF(10);
			}
			return;
		}
    }
    
    private void checkBounds() {
    	float pX = handler.getWorld().getEntityManager().getPlayer().getX();
    	float pY = handler.getWorld().getEntityManager().getPlayer().getY();
		if(pX >= this.x - 400 && pX <= this.x + this.width + 400) {
			if(pY >= this.y - 400 && pY <= this.y + this.height + 400) {
				if(!completed)
					this.isTalking = true;
			}
		}
		float mX = handler.getMouseManager().getMouseX();
    	float mY = handler.getMouseManager().getMouseY();
		if(mX >= this.x && mX <= this.x + this.width) {
			if(mY >= this.y && mY <= this.y + this.height) {
				if(!completed)
					this.isTalking = true;
			}
		}
	}

	private void checkAttacks()
    {	
    	
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
    	this.active = false;
    }

    public void render(Graphics g)
    {	
    	if(!active)
    		return;
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        if(timer != null && !timer.isCompleted()) {
        	timer.render(g);
        }
        if(handler.getGame().isDebug()) {
    		g.setColor(Color.WHITE);
    	    g.drawRect((int) (this.getCollisionBounds(0.0f, 0.0f).x - handler.getGameCamera().getxOffset()), (int) (this.getCollisionBounds(0.0f, 0.0f).y - handler.getGameCamera().getyOffset()), this.getCollisionBounds(0.0f, 0.0f).width, this.getCollisionBounds(0.0f, 0.0f).height);
    	    g.setColor(null);
    	}
    }

    private BufferedImage getCurrentAnimationFrame()
    {
    	BufferedImage toReturn = null;
    	if(!enraged) {
    		if(xMove < 0.0F)
    			toReturn = animLeft.getCurrentFrame();
            if(xMove > 0.0F)
            	toReturn = animRight.getCurrentFrame();
            if(yMove < 0.0F)
            	toReturn = animUp.getCurrentFrame();
            else
            	toReturn = animDown.getCurrentFrame();
    	}else if(enraged) {
    		if(xMove < 0.0F)
    			toReturn = animLeftAngry.getCurrentFrame();
            if(xMove > 0.0F)
            	toReturn = animRightAngry.getCurrentFrame();
            if(yMove < 0.0F)
            	toReturn = animUpAngry.getCurrentFrame();
            else
            	toReturn = animDownAngry.getCurrentFrame();
    	}
    	return toReturn;
    }
    
    public void talk() {
    	checkDialougue();
		if(this.isTalking) {
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
				if(!currentAudio.isCompleted())
					currentAudio.stop();
				currentAudio = null;
				currentDialougueNum2++;
				if(currentDialougueNum2 % 2 == 0) {
					currentSpeaker = 0;
					currentDialougueNum++;
				}else if(currentDialougueNum2 % 2 != 0) {
					currentSpeaker = 1;
					currentResponseNum++;
				}
				currentDialougue = null;
				return;
			}
			if(currentSpeaker == 0) {
				if(currentAudio == null) {
					handler.getAudioManager().setEffectVolume(1.0f);
					currentAudio = new Audio("francisco/" + handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() + "/" + Integer.toString(currentDialougueNum + 1) + ".m4a",
							false, true, handler);
				}
				currentDialougue = dialougue.get(currentDialougueNum);
			}else if(currentSpeaker == 1) {
				if(currentAudio == null) {
					handler.getAudioManager().setEffectVolume(0.5f);
					currentAudio = new Audio("francisco/" + handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueF() + "/" + Integer.toString(currentDialougueNum + 1) + "p.m4a",
							false, true, handler);
				}
				currentDialougue = playerresponses.get(currentResponseNum);
			}
			
			return;
		}
	}

}