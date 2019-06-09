package entities.creatures.npcs;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.creatures.Creature;
import gfx.Assets;
import guis.Gui;
import main.Handler;

public abstract class NPC extends Creature{
	
	protected ArrayList<String> dialougue;
	protected ArrayList<String> playerresponses;
	protected ArrayList<String> previousDialougue;
	
	protected String currentDialougue;
    protected String currentDialougues[];
    
    protected int currentSpeaker = 0;
    protected int currentDialougueNum = 0;
    protected int currentDialougueNum2 = 0;
    protected int currentResponseNum = -1;
	
	protected boolean isTalking;
	protected boolean completed;
	
	protected int current = 1;
	
    protected Gui textbox;
    
    protected BufferedImage face;

	public NPC(Handler handler, float x, float y, int width, int height, int health, int id) {
		super(handler, x, y, width, height, health, id);
		
		dialougue = new ArrayList<String>();
		playerresponses = new ArrayList<String>();
		previousDialougue = new ArrayList<String>();
	}
	
	
	
	public void setTextbox() {
		if(!completed) {
        	if(isTalking) {
        		if(currentDialougue == null) {
        			//if there is no current dialougue, we remove the textbox
        			handler.getGuiManager().getGuis().remove(textbox);
        			textbox = null;
        		}else if(textbox == null && currentDialougue != null) {
        			if(currentDialougue.length() > 21) {
        				//splits dialougue so it can fit if greater than 21 in length
        				currentDialougues = currentDialougue.split("/");
        				textbox = new Gui(Assets.textbox, null, null, handler.getWidth() / 2 - 225,
        						handler.getHeight() / 5 * 4, 450, 128, currentDialougues[0], currentDialougues[1], handler);
        			}else {
        				//if less than 21 in length
            			textbox = new Gui(Assets.textbox, null, null, handler.getWidth() / 2 - 225, 
            					handler.getHeight() / 5 * 4, 450, 128, currentDialougue, handler);
        			}
        			//if textbox is not made, and the dialougue is, make the textbox
        		}else if(currentDialougue != null && textbox != null) {
        			//if textbox is made
        	        if(currentSpeaker == 0) {
        	        	textbox.setExtra(face);
        	        	textbox.setSecondaryExtra(null);
        	        }else if(currentSpeaker == 1) {
        	        	textbox.setExtra(null);
        	        	textbox.setSecondaryExtra(Assets.player_face);
        	        }
        		}
        	}else if(!isTalking) {
        		//gets rid of textbox if not talking
        		handler.getGuiManager().getGuis().remove(textbox);
        		currentDialougue = null;
        		textbox = null;
        		
        	}
    	}
	}

	public int getCurrent() {
		return current;
	}
	
	public void setCurrent(int current) {
		this.current = current;
	}
	
}
