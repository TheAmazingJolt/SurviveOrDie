package inventory;

import gfx.Assets;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

import audio.Audio;
import entities.creatures.npcs.NPC;
import gfx.Text;
import items.Item;
import main.Handler;
import utils.ReadFile;

// Referenced classes of package inventory:
//            Inventory

public class Help
{

	private static Handler handler;
    private static boolean active = false;
    private ArrayList<String> help;
    
    private int helpX;
    private int helpY;
    private int helpWidth;
    private int helpHeight;
    
    private int line1X;
    private int line1Y;
    private int line2X;
    private int line2Y;
    private int line3X;
    private int line3Y;
    
    private boolean completed;
    private boolean audioStarted = false;
    
    private String currentHelp;
    private String currentHelp2;
    private String currentHelp3;
    private String currentHelp4;
    private String currentHelp5;
    private String currentHelp6;
    
    private Audio help1;
    private Audio help2;
    private Audio help3;
    private Audio help4;
    private Audio help5;
    private Audio help6;
    
    private int currentHelpNum = 0;
    private int size;
	
    public Help(Handler handle)
    {
    	helpX = 17;
    	helpY = 573;
        helpWidth = 300;
        helpHeight = 128;
        line1X = helpX + 150;
        line1Y = helpY + 30;
        line2X = line1X;
        line2Y = line1Y + 34;
        line3X = line1X;
        line3Y = line2Y + 34;
        handler = handle;
        help = new ArrayList<String>();
        help = ReadFile.readDialougue("/help.txt");
        currentHelp4 = "Use WASD to Move";
        currentHelp5 = "Left Click to Attack";
        currentHelp6 = "In Direction Facing";
    }

    public void tick()
    {
    	if(handler.getGame().getGameType().contains("story")) {
	    	if(!handler.getWorld().isLoaded() && !completed && handler.getWorld().getCurrentWorld() == 1) {
	    		active = true;
	    		if(!audioStarted) {
	    			audioStarted = true;
	    			if(help1 == null)
	    				help1 = new Audio("help/help1.m4a", false, true, handler);
	    		}
	    	}
	    	else if(handler.getWorld().isLoaded()) {
	    		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_H)) {
	            	active = true;
	            	if(help1 == null)
	            		help1 = new Audio("help/help1.m4a", false, true, handler);
	            }
	    	}
	    	if(!active)
	    		return;
	    	size = help.size();
	    	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && completed) {
	    		currentHelpNum++;
	    	}
	    	if(!completed) {
	    		if(currentHelpNum == 0) {
	    			for(Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()) {
	    				if(i.getName().contains("Wood")) {
	    					currentHelpNum++;
	    					if(help2 == null)
	    						help2 = new Audio("help/help2.m4a", false, true, handler);
	    				}
	    			}
	    		}else if(currentHelpNum == 1) {
	    			for(Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()) {
	    				if(i.getName().contains("Rock")) {
	    					currentHelpNum++;
	    					if(help3 == null)
	    						help3 = new Audio("help/help3.m4a", false, true, handler);
	    				}
	    			}
	    		}else if(currentHelpNum == 2) {
	    			for(Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()) {
	    				if(i.getName().contains("Axe")) {
	    					currentHelpNum++;
	    					if(help4 == null)
	    						help4 = new Audio("help/help4.m4a", false, true, handler);
	    				}
	    			}
	    		}else if(currentHelpNum == 3) {
	    			if(handler.getWorld().getEntityManager().getPlayer().getInventory().checkIfContains(Item.ironOreItem.getId(), Item.flintItem.getId())) {
	    				currentHelpNum++;
	    				if(help5 == null)
							help5 = new Audio("help/help5.m4a", false, true, handler);
	    			}
	    		}else if(currentHelpNum == 4) {
	    			float px = handler.getWorld().getEntityManager().getPlayer().getX();
	    			float py = handler.getWorld().getEntityManager().getPlayer().getY();
	    			if(px <= 1664 && py <= 1664) {
	    				currentHelpNum++;
	    				if(help6 == null)
							help6 = new Audio("help/help6.m4a", false, true, handler);
	    			}
	    		}else if(currentHelpNum == 5) {
	    			float px = handler.getWorld().getEntityManager().getPlayer().getX();
	    			float py = handler.getWorld().getEntityManager().getPlayer().getY();
	    			for(NPC n : handler.getWorld().getEntityManager().getNpcs()) {
	    				if(px >= n.getX() - 500 && px <= n.getX() + n.getWidth() + 500) {
	    					if(py >= n.getY() - 500 && py <= n.getY() + n.getHeight() + 500) {
	    						currentHelpNum++;
	    					}
	    				}
	    			}
	    		}
	    	}
	    	currentHelp = null;
	    	currentHelp2 = null;
	    	currentHelp3 = null;
	    	if(currentHelpNum == 0) {
	    		if(size == 1) {
	        		currentHelp = help.get(currentHelpNum);
	        	}else if(size == 2) {
	        		currentHelp = help.get(currentHelpNum);
	        		currentHelp2 = help.get(currentHelpNum + 1);
	        	}else if(size >= 3) {
	        		currentHelp = help.get(currentHelpNum);
	        		currentHelp2 = help.get(currentHelpNum + 1);
	        		currentHelp3 = help.get(currentHelpNum + 2);
	        	}
	    	}if(currentHelpNum == 1) {
	    		if(size == 4) {
	        		currentHelp = help.get(currentHelpNum + 2);
	    		}else if(size == 5) {
	        		currentHelp = help.get(currentHelpNum + 2);
	        		currentHelp2 = help.get(currentHelpNum + 3);
	    		}else if(size >= 6) {
	        		currentHelp = help.get(currentHelpNum + 2);
	        		currentHelp2 = help.get(currentHelpNum + 3);
	        		currentHelp3 = help.get(currentHelpNum + 4);
	    		}
	    	}if(currentHelpNum == 2) {
	    		if(size == 7) {
	        		currentHelp = help.get(currentHelpNum + 4);
	    		}else if(size == 8) {
	        		currentHelp = help.get(currentHelpNum + 4);
	        		currentHelp2 = help.get(currentHelpNum + 5);
	    		}else if(size >= 9) {
	        		currentHelp = help.get(currentHelpNum + 4);
	        		currentHelp2 = help.get(currentHelpNum + 5);
	        		currentHelp3 = help.get(currentHelpNum + 6);
	    		}
	    	}if(currentHelpNum == 3) {
	    		if(size == 10) {
	        		currentHelp = help.get(currentHelpNum + 6);
	    		}else if(size == 11) {
	        		currentHelp = help.get(currentHelpNum + 6);
	        		currentHelp2 = help.get(currentHelpNum + 7);
	    		}else if(size >= 12) {
	        		currentHelp = help.get(currentHelpNum + 6);
	        		currentHelp2 = help.get(currentHelpNum + 7);
	        		currentHelp3 = help.get(currentHelpNum + 8);
	    		}
	    	}if(currentHelpNum == 4) {
	    		if(size == 13) {
	        		currentHelp = help.get(currentHelpNum + 8);
	    		}else if(size == 14) {
	        		currentHelp = help.get(currentHelpNum + 8);
	        		currentHelp2 = help.get(currentHelpNum + 9);
	    		}else if(size >= 15) {
	        		currentHelp = help.get(currentHelpNum + 8);
	        		currentHelp2 = help.get(currentHelpNum + 9);
	        		currentHelp3 = help.get(currentHelpNum + 10);
	    		}
	    	}if(currentHelpNum == 5) {
	    		if(size == 16) {
	        		currentHelp = help.get(currentHelpNum + 10);
	    		}else if(size == 17) {
	        		currentHelp = help.get(currentHelpNum + 10);
	        		currentHelp2 = help.get(currentHelpNum + 11);
	    		}else if(size >= 18) {
	        		currentHelp = help.get(currentHelpNum + 10);
	        		currentHelp2 = help.get(currentHelpNum + 11);
	        		currentHelp3 = help.get(currentHelpNum + 12);
	    		}
	    	}
	    	if(currentHelp == null && currentHelp2 == null && currentHelp3 == null) {
	    		currentHelpNum = 0;
	    		active = false;
	    		completed = true;
	    	}
    	}
    	
    }

    public void render(Graphics g)
    {
        if(!active)
            return;
        g.drawImage(Assets.textbox, helpX, helpY, helpWidth, helpHeight, null);
        if(currentHelpNum < 5)
        	g.drawImage(Assets.textbox, handler.getWidth() - helpWidth - helpX, helpY, helpWidth, helpHeight, null);
        if(currentHelp != null) {
        	Text.drawString(g, currentHelp, line1X, line1Y, true, Color.BLACK, Assets.font28);
        	if(currentHelp2 != null) {
            	Text.drawString(g, currentHelp2, line2X, line2Y, true, Color.BLACK, Assets.font28);
            	if(currentHelp3 != null) {
                	Text.drawString(g, currentHelp3, line3X, line3Y, true, Color.BLACK, Assets.font28);
                }
        	}
        }
        if(currentHelpNum < 5 && currentHelpNum != 2) {
            Text.drawString(g, currentHelp4, handler.getWidth() - helpWidth - helpX + 150, line1Y, true, Color.BLACK, Assets.font24);
            Text.drawString(g, currentHelp5, handler.getWidth() - helpWidth - helpX + 150, line2Y, true, Color.BLACK, Assets.font24);
            Text.drawString(g, currentHelp6, handler.getWidth() - helpWidth - helpX + 150, line3Y, true, Color.BLACK, Assets.font24);
        }else if(currentHelpNum == 2) {
        	Text.drawString(g, "Use E for Inventory", handler.getWidth() - helpWidth - helpX + 150, line1Y, true, Color.BLACK, Assets.font24);
            Text.drawString(g, "R Click to Select", handler.getWidth() - helpWidth - helpX + 150, line2Y, true, Color.BLACK, Assets.font24);
            Text.drawString(g, "L Click to Move", handler.getWidth() - helpWidth - helpX + 150, line3Y, true, Color.BLACK, Assets.font24);
        }
    }
    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handle)
    {
        handler = handle;
    }

    public static boolean isActive()
    {
        return active;
    }

    public void setActive(boolean activ)
    {
        active = activ;
    }
}