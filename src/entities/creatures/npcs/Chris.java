package entities.creatures.npcs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sun.glass.events.KeyEvent;

import audio.Audio;
import entities.creatures.npcs.tasks.Task;
import gfx.Animation;
import gfx.Assets;
import items.Item;
import main.Handler;
import utils.ReadFile;

public class Chris extends NPC
{

	private Animation animDown;
    private Animation animUp;
    
    private static int maxHealth = 2000;
    private static int startHealth;
    
    private boolean checked = false;
    
    private Task getPistol;
    
    private Audio currentAudio;
	
    public Chris(Handler handler, float x, float y, int id)
    {
        super(handler, x, y, 64, 64, maxHealth, id, "Chris");
        this.id = id;
        startX = x;
        startY = y;
        this.health = maxHealth;
        startHealth = this.health;
        bounds.x = 20;
        bounds.y = 30;
        bounds.width = 20;
        bounds.height = 20;
        animDown = new Animation(500, Assets.chris_down);
        animUp = new Animation(500, Assets.chris_up);
        this.isPassive = true;
        this.isNpc = true;
		this.dialougue = ReadFile.readDialougue("/chris/chris.txt");
		this.playerresponses = ReadFile.readDialougue("/chris/pcresponse.txt");
		this.face = Assets.chris_face;
		
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
        checkAttacks();
        move();
        checkBounds();
        if(isTalking)
        	talk();
        else if(completed)
    		checkTasks();
        if(!checked) {
    		if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueC()-1 == 1) {
    			System.out.println("yeet");
    			dialougue = ReadFile.readDialougue("/chris/chris2.txt");
    			playerresponses = ReadFile.readDialougue("/chris/pcresponse2.txt");
    			currentDialougueNum = 0;
    			currentResponseNum = -1;
    			currentSpeaker = 0;
    			currentDialougueNum2 = 0;
    			handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueC(2);
    			checked = true;
    			return;
    		}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueC()-1 == 2) {
    			dialougue = ReadFile.readDialougue("/chris/chris2.txt");
    			playerresponses = ReadFile.readDialougue("/chris/pcresponse2.txt");
    			currentDialougueNum = 0;
    			currentResponseNum = -1;
    			currentSpeaker = 0;
    			currentDialougueNum2 = 0;
    			this.active = false;
    			handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueC(3);
    			checked = true;
    			return;
    		}
    		checked = true;
    		return;
        }
        setTextbox();
    }
    
    private void checkTasks() {
    	if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueC() == 2) {
    		if(getPistol == null) {
    			getPistol = new Task("3", null, Item.pistolItem, handler);
    		}else if(getPistol != null) {
    			getPistol.tick();
    			if(getPistol.isCompleted()) {
    				completed = false;
    			}
    		}
		}
    }
    
    private void checkDialougue() {
        if(currentDialougueNum >= dialougue.size() || currentResponseNum >= playerresponses.size()) {
			isTalking = false;
			completed = true;
			currentDialougue = null;
			if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueC() == 1) {
				dialougue = ReadFile.readDialougue("/chris/chris2.txt");
				playerresponses = ReadFile.readDialougue("/chris/pcresponse2.txt");
				currentDialougueNum = 0;
				currentResponseNum = -1;
				currentSpeaker = 0;
				currentDialougueNum2 = 0;
				handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueC(2);
			}else if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueC() == 2) {
				//dialougue = ReadFile.readDialougue("/chris/chris2.txt");
				//playerresponses = ReadFile.readDialougue("/chris/pcresponse2.txt");
				currentDialougueNum = 0;
				currentResponseNum = -1;
				currentSpeaker = 0;
				currentDialougueNum2 = 0;
				this.active = false;
				handler.getWorld().getEntityManager().getPlayer().setCurrentDialougueC(3);
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
        if(handler.getGame().isDebug()) {
    		g.setColor(Color.WHITE);
    	    g.drawRect((int) (this.getCollisionBounds(0.0f, 0.0f).x - handler.getGameCamera().getxOffset()), (int) (this.getCollisionBounds(0.0f, 0.0f).y - handler.getGameCamera().getyOffset()), this.getCollisionBounds(0.0f, 0.0f).width, this.getCollisionBounds(0.0f, 0.0f).height);
    	    g.setColor(null);
    	}
    }

    private BufferedImage getCurrentAnimationFrame()
    {
    	BufferedImage toReturn = null;
        if(yMove < 0.0F)
         	toReturn = animUp.getCurrentFrame();
        else
            toReturn = animDown.getCurrentFrame();
    	return toReturn;
    }
    
    public void talk() {
    	checkDialougue();
    	if(handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueC() > 2)
    		return;
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
					currentAudio = new Audio("chris/" + handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueC() + "/" + Integer.toString(currentDialougueNum + 1) + ".m4a",
							false, true, handler);
				}
				currentDialougue = dialougue.get(currentDialougueNum);
			}else if(currentSpeaker == 1) {
				if(currentAudio == null) {
					handler.getAudioManager().setEffectVolume(1.0f);
					currentAudio = new Audio("chris/" + handler.getWorld().getEntityManager().getPlayer().getCurrentDialougueC() + "/" + Integer.toString(currentDialougueNum + 1) + "p.m4a",
							false, true, handler);
				}
				currentDialougue = playerresponses.get(currentResponseNum);
			}
			
			return;
		}
	}

}