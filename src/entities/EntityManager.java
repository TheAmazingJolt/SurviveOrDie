package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import main.Handler;
import entities.creatures.Player;
import entities.creatures.npcs.Chris;
import entities.creatures.npcs.Francisco;
import entities.creatures.npcs.NPC;
import entities.creatures.npcs.Sierra;

public class EntityManager {

	private Handler handler;
	private Player player;
	private Player player2;
	
	private ArrayList<Entity> entities1;
	private ArrayList<Entity> e1overflow1;
	private ArrayList<Entity> e1overflow2;
	private ArrayList<Entity> e1overflow3;
	private ArrayList<Entity> e1overflow4;
	private ArrayList<Entity> e1overflow5;
	private ArrayList<Entity> e1overflow6;
	private ArrayList<Entity> e1overflow7;
	private ArrayList<Entity> e1overflow8;
	private ArrayList<Entity> e1overflow9;
	private ArrayList<Entity> e1overflow10;
	private ArrayList<Entity> e1overflow11;
	private ArrayList<Entity> e1overflow12;
	private ArrayList<Entity> e1overflow13;
	private ArrayList<Entity> e1overflow14;
	private ArrayList<Entity> e1overflow15;
	private ArrayList<Entity> e1overflow16;

	private ArrayList<Entity> entities2;
	private ArrayList<Entity> e2overflow1;
	private ArrayList<Entity> e2overflow2;
	private ArrayList<Entity> e2overflow3;
	private ArrayList<Entity> e2overflow4;
	private ArrayList<Entity> e2overflow5;
	private ArrayList<Entity> e2overflow6;
	private ArrayList<Entity> e2overflow7;
	private ArrayList<Entity> e2overflow8;
	private ArrayList<Entity> e2overflow9;
	private ArrayList<Entity> e2overflow10;
	private ArrayList<Entity> e2overflow11;
	private ArrayList<Entity> e2overflow12;
	private ArrayList<Entity> e2overflow13;
	private ArrayList<Entity> e2overflow14;
	private ArrayList<Entity> e2overflow15;
	private ArrayList<Entity> e2overflow16;

	private ArrayList<Entity> entities3;
	private ArrayList<Entity> e3overflow1;
	private ArrayList<Entity> e3overflow2;
	private ArrayList<Entity> e3overflow3;
	private ArrayList<Entity> e3overflow4;
	private ArrayList<Entity> e3overflow5;
	private ArrayList<Entity> e3overflow6;
	private ArrayList<Entity> e3overflow7;
	private ArrayList<Entity> e3overflow8;
	private ArrayList<Entity> e3overflow9;
	private ArrayList<Entity> e3overflow10;
	private ArrayList<Entity> e3overflow11;
	private ArrayList<Entity> e3overflow12;
	private ArrayList<Entity> e3overflow13;
	private ArrayList<Entity> e3overflow14;
	private ArrayList<Entity> e3overflow15;
	private ArrayList<Entity> e3overflow16;

	private ArrayList<Entity> entities4;
	private ArrayList<Entity> e4overflow1;
	private ArrayList<Entity> e4overflow2;
	private ArrayList<Entity> e4overflow3;
	private ArrayList<Entity> e4overflow4;
	private ArrayList<Entity> e4overflow5;
	private ArrayList<Entity> e4overflow6;
	private ArrayList<Entity> e4overflow7;
	private ArrayList<Entity> e4overflow8;
	private ArrayList<Entity> e4overflow9;
	private ArrayList<Entity> e4overflow10;
	private ArrayList<Entity> e4overflow11;
	private ArrayList<Entity> e4overflow12;
	private ArrayList<Entity> e4overflow13;
	private ArrayList<Entity> e4overflow14;
	private ArrayList<Entity> e4overflow15;
	private ArrayList<Entity> e4overflow16;
	
	private ArrayList<Entity> toAdd;
	private ArrayList<NPC> toAddNpc2;
	private ArrayList<Entity> toRemove;
	
	private ArrayList<NPC> npcs;
	private ArrayList<NPC> npcs2;
	private ArrayList<NPC> npcs3;
	private ArrayList<NPC> npcs4;
	
	private boolean positionDetermined = false;
	private int currentSorting = 0;
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
				return -1;//a is rendered after b
			}
			return 1;//a is rendered before b
		}
	};
	
	public EntityManager(Handler handler) {
		this.handler = handler;
	}
	
	public void setup(Player player) {
		this.player = player;
		if(handler.getGame().isMultiplayer()) {
			player2 = new Player(handler, 3164, 3164, 2, -2);
		}
		entities1 = new ArrayList<Entity>();
		e1overflow1 = new ArrayList<Entity>();
		e1overflow2 = new ArrayList<Entity>();
		e1overflow3 = new ArrayList<Entity>();
		e1overflow4 = new ArrayList<Entity>();
		e1overflow5 = new ArrayList<Entity>();
		e1overflow6 = new ArrayList<Entity>();
		e1overflow7 = new ArrayList<Entity>();
		e1overflow8 = new ArrayList<Entity>();
		e1overflow9 = new ArrayList<Entity>();
		e1overflow10 = new ArrayList<Entity>();
		e1overflow11 = new ArrayList<Entity>();
		e1overflow12 = new ArrayList<Entity>();
		e1overflow13 = new ArrayList<Entity>();
		e1overflow14 = new ArrayList<Entity>();
		e1overflow15 = new ArrayList<Entity>();
		e1overflow16 = new ArrayList<Entity>();
		entities2 = new ArrayList<Entity>();
		e2overflow1 = new ArrayList<Entity>();
		e2overflow2 = new ArrayList<Entity>();
		e2overflow3 = new ArrayList<Entity>();
		e2overflow4 = new ArrayList<Entity>();
		e2overflow5 = new ArrayList<Entity>();
		e2overflow6 = new ArrayList<Entity>();
		e2overflow7 = new ArrayList<Entity>();
		e2overflow8 = new ArrayList<Entity>();
		e2overflow9 = new ArrayList<Entity>();
		e2overflow10 = new ArrayList<Entity>();
		e2overflow11 = new ArrayList<Entity>();
		e2overflow12 = new ArrayList<Entity>();
		e2overflow13 = new ArrayList<Entity>();
		e2overflow14 = new ArrayList<Entity>();
		e2overflow15 = new ArrayList<Entity>();
		e2overflow16 = new ArrayList<Entity>();
		entities3 = new ArrayList<Entity>();
		e3overflow1 = new ArrayList<Entity>();
		e3overflow2 = new ArrayList<Entity>();
		e3overflow3 = new ArrayList<Entity>();
		e3overflow4 = new ArrayList<Entity>();
		e3overflow5 = new ArrayList<Entity>();
		e3overflow6 = new ArrayList<Entity>();
		e3overflow7 = new ArrayList<Entity>();
		e3overflow8 = new ArrayList<Entity>();
		e3overflow9 = new ArrayList<Entity>();
		e3overflow10 = new ArrayList<Entity>();
		e3overflow11 = new ArrayList<Entity>();
		e3overflow12 = new ArrayList<Entity>();
		e3overflow13 = new ArrayList<Entity>();
		e3overflow14 = new ArrayList<Entity>();
		e3overflow15 = new ArrayList<Entity>();
		e3overflow16 = new ArrayList<Entity>();
		entities4 = new ArrayList<Entity>();
		e4overflow1 = new ArrayList<Entity>();
		e4overflow2 = new ArrayList<Entity>();
		e4overflow3 = new ArrayList<Entity>();
		e4overflow4 = new ArrayList<Entity>();
		e4overflow5 = new ArrayList<Entity>();
		e4overflow6 = new ArrayList<Entity>();
		e4overflow7 = new ArrayList<Entity>();
		e4overflow8 = new ArrayList<Entity>();
		e4overflow9 = new ArrayList<Entity>();
		e4overflow10 = new ArrayList<Entity>();
		e4overflow11 = new ArrayList<Entity>();
		e4overflow12 = new ArrayList<Entity>();
		e4overflow13 = new ArrayList<Entity>();
		e4overflow14 = new ArrayList<Entity>();
		e4overflow15 = new ArrayList<Entity>();
		e4overflow16 = new ArrayList<Entity>();
		toAdd = new ArrayList<Entity>();
		toAddNpc2 = new ArrayList<NPC>();
		toRemove = new ArrayList<Entity>();
		npcs = new ArrayList<NPC>();
		npcs2 = new ArrayList<NPC>();
		npcs3 = new ArrayList<NPC>();
		npcs4 = new ArrayList<NPC>();
		addEntity1(player);
		addEntity2(player);
		addEntity3(player);
		addEntity4(player);
		if(handler.getGame().isMultiplayer())
			addEntity1(player2);
		if(handler.getGame().getGameType().contains("story")) {
			addNPCMain(new Francisco(handler, 832f, 832f, -3));
			addNPC2Main(new Sierra(handler, 832f, 832f, -4));
			addNPC3Main(new Chris(handler, 832f, 832f, -5));
		}
	}
	
	public void tick()
    {
		if(handler.getGame().isMultiplayer()) {
			player.setHealth(player.getMaxHealth());
			player2.setHealth(player2.getMaxHealth());
		}
		if(handler.getWorld().getCurrentWorld() == 1) {
	        for(Entity e : entities1) {
	            e.tick();
	        }
	        for(Entity e : e1overflow1) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow2) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow3) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow4) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow5) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow6) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }for(Entity e : e1overflow7) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow8) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow9) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow10) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow11) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow12) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }for(Entity e : e1overflow13) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow14) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow15) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e1overflow16) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(NPC n : npcs) {
	        	n.tick();
	        }
	        entities1.sort(renderSorter);
	        e1overflow1.sort(renderSorter);
	        e1overflow2.sort(renderSorter);
	        e1overflow3.sort(renderSorter);
	        e1overflow4.sort(renderSorter);
	        e1overflow5.sort(renderSorter);
	        e1overflow6.sort(renderSorter);
	        e1overflow7.sort(renderSorter);
	        e1overflow8.sort(renderSorter);
	        e1overflow9.sort(renderSorter);
	        e1overflow10.sort(renderSorter);
	        e1overflow11.sort(renderSorter);
	        e1overflow12.sort(renderSorter);
	        e1overflow13.sort(renderSorter);
	        e1overflow14.sort(renderSorter);
	        e1overflow15.sort(renderSorter);
	        e1overflow16.sort(renderSorter);
	        npcs.sort(renderSorter);
	        entities1.addAll(toAdd);
	        toAdd.clear();
	        entities1.removeAll(toRemove);
	        toRemove.clear();
		}
		if(handler.getWorld().getCurrentWorld() == 2) {
			for(Entity e : entities2) {
	            e.tick();
	        }
	        for(Entity e : e2overflow1) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow2) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow3) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow4) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow5) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow6) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }for(Entity e : e2overflow7) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow8) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow9) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow10) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow11) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow12) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow13) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow14) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow15) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e2overflow16) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(NPC n : npcs2) {
	        	n.tick();
	        }
	        entities2.sort(renderSorter);
	        e2overflow1.sort(renderSorter);
	        e2overflow2.sort(renderSorter);
	        e2overflow3.sort(renderSorter);
	        e2overflow4.sort(renderSorter);
	        e2overflow5.sort(renderSorter);
	        e2overflow6.sort(renderSorter);
	        e2overflow7.sort(renderSorter);
	        e2overflow8.sort(renderSorter);
	        e2overflow9.sort(renderSorter);
	        e2overflow10.sort(renderSorter);
	        e2overflow11.sort(renderSorter);
	        e2overflow12.sort(renderSorter);
	        e2overflow13.sort(renderSorter);
	        e2overflow14.sort(renderSorter);
	        e2overflow15.sort(renderSorter);
	        e2overflow16.sort(renderSorter);
	        npcs2.sort(renderSorter);
	        entities2.addAll(toAdd);
	        npcs2.addAll(toAddNpc2);
	        toAddNpc2.clear();
	        toAdd.clear();
	        entities2.removeAll(toRemove);
	        toRemove.clear();
		}
		if(handler.getWorld().getCurrentWorld() == 3) {
			for(Entity e : entities3) {
	            e.tick();
	        }
	        for(Entity e : e3overflow1) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow2) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow3) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow4) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow5) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow6) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow7) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow8) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow9) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow10) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow11) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow12) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow13) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow14) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow15) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e3overflow16) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(NPC n : npcs3) {
	        	n.tick();
	        }
	        entities3.sort(renderSorter);
	        e3overflow1.sort(renderSorter);
	        e3overflow2.sort(renderSorter);
	        e3overflow3.sort(renderSorter);
	        e3overflow4.sort(renderSorter);
	        e3overflow5.sort(renderSorter);
	        e3overflow6.sort(renderSorter);
	        e3overflow7.sort(renderSorter);
	        e3overflow8.sort(renderSorter);
	        e3overflow9.sort(renderSorter);
	        e3overflow10.sort(renderSorter);
	        e3overflow11.sort(renderSorter);
	        e3overflow12.sort(renderSorter);
	        e3overflow13.sort(renderSorter);
	        e3overflow14.sort(renderSorter);
	        e3overflow15.sort(renderSorter);
	        e3overflow16.sort(renderSorter);
	        npcs3.sort(renderSorter);
	        entities3.addAll(toAdd);
	        toAdd.clear();
	        entities3.removeAll(toRemove);
	        toRemove.clear();
		}
		if(handler.getWorld().getCurrentWorld() == 4) {
			for(Entity e : entities4) {
	            e.tick();
	        }
	        for(Entity e : e4overflow1) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow2) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow3) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow4) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow5) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow6) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow7) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow8) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow9) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow10) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow11) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow12) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow13) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow14) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow15) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(Entity e : e4overflow16) {
	            if(e == player) {
	            	continue;
	            }
	            e.tick();
	        }
	        for(NPC n : npcs4) {
	        	n.tick();
	        }
	        entities4.sort(renderSorter);
	        e4overflow1.sort(renderSorter);
	        e4overflow2.sort(renderSorter);
	        e4overflow3.sort(renderSorter);
	        e4overflow4.sort(renderSorter);
	        e4overflow5.sort(renderSorter);
	        e4overflow6.sort(renderSorter);
	        e4overflow7.sort(renderSorter);
	        e4overflow8.sort(renderSorter);
	        e4overflow9.sort(renderSorter);
	        e4overflow10.sort(renderSorter);
	        e4overflow11.sort(renderSorter);
	        e4overflow12.sort(renderSorter);
	        e4overflow13.sort(renderSorter);
	        e4overflow14.sort(renderSorter);
	        e4overflow15.sort(renderSorter);
	        e4overflow16.sort(renderSorter);
	        npcs4.sort(renderSorter);
	        entities4.addAll(toAdd);
	        toAdd.clear();
	        entities4.removeAll(toRemove);
	        toRemove.clear();
		}
    }

    public void render(Graphics g)
    {
    	if(!positionDetermined) {
    		determinePositions();
    	}
    		
    	if(player.getxMove() != 0 || player.getyMove() != 0) {
    		positionDetermined = false;
    	}
    	if(handler.getWorld().getCurrentWorld() == 1) {
    		if(currentSorting == 0)
    			player.render(g);
    		for(Entity e : entities1)
    		{
    			if(e == player && currentSorting != 1) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow1)
    		{
    			if(e == player && currentSorting != 2) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow2)
    		{
    			if(e == player && currentSorting != 3) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow3)
    		{
    			if(e == player && currentSorting != 4) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow4)
    		{
    			if(e == player && currentSorting != 5) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow5)
    		{
    			if(e == player && currentSorting != 6) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow6)
    		{
    			if(e == player && currentSorting != 7) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow7)
    		{
    			if(e == player && currentSorting != 8) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow8)
    		{
    			if(e == player && currentSorting != 9) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow9)
    		{
    			if(e == player && currentSorting != 10) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow10)
    		{
    			if(e == player && currentSorting != 11) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow11)
    		{
    			if(e == player && currentSorting != 12) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow12)
    		{
    			if(e == player && currentSorting != 13) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}for(Entity e : e1overflow13)
    		{
    			if(e == player && currentSorting != 14) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow14)
    		{
    			if(e == player && currentSorting != 15) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow15)
    		{
    			if(e == player && currentSorting != 16) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e1overflow16)
    		{
    			if(e == player && currentSorting != 17) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
	    	for(NPC n : npcs) {
	    		n.render(g);
	    	}
    		player.postRender(g);
    	}else if(handler.getWorld().getCurrentWorld() == 2){
    		if(currentSorting == 0)
    			player.render(g);
    		for(Entity e : entities2)
    		{
    			if(e == player && currentSorting != 1) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow1)
    		{
    			if(e == player && currentSorting != 2) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow2)
    		{
    			if(e == player && currentSorting != 3) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow3)
    		{
    			if(e == player && currentSorting != 4) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow4)
    		{
    			if(e == player && currentSorting != 5) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow5)
    		{
    			if(e == player && currentSorting != 6) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow6)
    		{
    			if(e == player && currentSorting != 7) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow7)
    		{
    			if(e == player && currentSorting != 8) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow8)
    		{
    			if(e == player && currentSorting != 9) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow9)
    		{
    			if(e == player && currentSorting != 10) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow10)
    		{
    			if(e == player && currentSorting != 11) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow11)
    		{
    			if(e == player && currentSorting != 12) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow12)
    		{
    			if(e == player && currentSorting != 13) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow13)
    		{
    			if(e == player && currentSorting != 14) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow14)
    		{
    			if(e == player && currentSorting != 15) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow15)
    		{
    			if(e == player && currentSorting != 16) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e2overflow16)
    		{
    			if(e == player && currentSorting != 17) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
	    	for(NPC n : npcs2) {
	    		n.render(g);
	    	}
    		player.postRender(g);
    	}else if(handler.getWorld().getCurrentWorld() == 3){
    		if(currentSorting == 0)
    			player.render(g);
    		for(Entity e : entities3)
    		{
    			if(e == player && currentSorting != 1) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow1)
    		{
    			if(e == player && currentSorting != 2) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow2)
    		{
    			if(e == player && currentSorting != 3) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow3)
    		{
    			if(e == player && currentSorting != 4) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow4)
    		{
    			if(e == player && currentSorting != 5) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow5)
    		{
    			if(e == player && currentSorting != 6) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow6)
    		{
    			if(e == player && currentSorting != 7) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow7)
    		{
    			if(e == player && currentSorting != 8) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow8)
    		{
    			if(e == player && currentSorting != 9) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow9)
    		{
    			if(e == player && currentSorting != 10) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow10)
    		{
    			if(e == player && currentSorting != 11) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow11)
    		{
    			if(e == player && currentSorting != 12) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow12)
    		{
    			if(e == player && currentSorting != 13) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow13)
    		{
    			if(e == player && currentSorting != 14) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow14)
    		{
    			if(e == player && currentSorting != 15) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow15)
    		{
    			if(e == player && currentSorting != 16) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e3overflow16)
    		{
    			if(e == player && currentSorting != 17) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
	    	for(NPC n : npcs3) {
	    		n.render(g);
	    	}
    		player.postRender(g);
    	}else if(handler.getWorld().getCurrentWorld() == 4){
    		if(currentSorting == 0)
    			player.render(g);
    		for(Entity e : entities4)
    		{
    			if(e == player && currentSorting != 1) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow1)
    		{
    			if(e == player && currentSorting != 2) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow2)
    		{
    			if(e == player && currentSorting != 3) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow3)
    		{
    			if(e == player && currentSorting != 4) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow4)
    		{
    			if(e == player && currentSorting != 5) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow5)
    		{
    			if(e == player && currentSorting != 6) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow6)
    		{
    			if(e == player && currentSorting != 7) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow7)
    		{
    			if(e == player && currentSorting != 8) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow8)
    		{
    			if(e == player && currentSorting != 9) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow9)
    		{
    			if(e == player && currentSorting != 10) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow10)
    		{
    			if(e == player && currentSorting != 11) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow11)
    		{
    			if(e == player && currentSorting != 12) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow12)
    		{
    			if(e == player && currentSorting != 13) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow13)
    		{
    			if(e == player && currentSorting != 14) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow14)
    		{
    			if(e == player && currentSorting != 15) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow15)
    		{
    			if(e == player && currentSorting != 16) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
    		for(Entity e : e4overflow16)
    		{
    			if(e == player && currentSorting != 17) {
    				continue;
    			}
    			if(e.isActive())
    				e.render(g);
    		}
	    	for(NPC n : npcs4) {
	    		n.render(g);
	    	}
    		player.postRender(g);
    	}
    }

    public void determinePositions() {
    	if(handler.getWorld().getCurrentWorld() == 1) {
	    	for(Entity e : entities1) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 1;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow1) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 2;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow2) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 3;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow3) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 4;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow4) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 5;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow5) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 6;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow6) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 7;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow7) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 8;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow8) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 9;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow9) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 10;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow10) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 11;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow11) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 12;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow12) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 13;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow13) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 14;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow14) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 15;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow15) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 16;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e1overflow16) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 17;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
		}else if(handler.getWorld().getCurrentWorld() == 2) {
			for(Entity e : entities2) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 1;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow1) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 2;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow2) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 3;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow3) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 4;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow4) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 5;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow5) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 6;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow6) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 7;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow7) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 8;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow8) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 9;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow9) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 10;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow10) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 11;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow11) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 12;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow12) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 13;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow13) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 14;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow14) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 15;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow15) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 16;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e2overflow16) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 17;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
		}else if(handler.getWorld().getCurrentWorld() == 3) {
			for(Entity e : entities3) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 1;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow1) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 2;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow2) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 3;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow3) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 4;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow4) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 5;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow5) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 6;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow6) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 7;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow7) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 8;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow8) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 9;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow9) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 10;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow10) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 11;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow11) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 12;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow12) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 13;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow13) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 14;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow14) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 15;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow15) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 16;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e3overflow16) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 17;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
		}else if(handler.getWorld().getCurrentWorld() == 4) {
			for(Entity e : entities4) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 1;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow1) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 2;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow2) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 3;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow3) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 4;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow4) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 5;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow5) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 6;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow6) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 7;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow7) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 8;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow8) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 9;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow9) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 10;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow10) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 11;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow11) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 12;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow12) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 13;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow13) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 14;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow14) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 15;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow15) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 16;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
	    	for(Entity e : e4overflow16) {
	    		if(e == player)
	    			continue;
	    		if(player.getX() >= e.getX() - 5 && player.getX() <= e.getX() + e.getWidth() + 5) {
	    			if(player.getY() >= e.getY() - 5 && player.getY() <= e.getY() + e.getHeight() + 5) {
	    				currentSorting = 17;
	    				positionDetermined = true;
	    			}else {
	    				continue;
	    			}
	    		}else {
    				continue;
	    		}
	    	}
		}
	}
    	
    public void addToOverflow() {
    	e1overflow1.add(player);
    	e1overflow2.add(player);
    	e1overflow3.add(player);
    	e1overflow4.add(player);
    	e1overflow5.add(player);
    	e1overflow6.add(player);
    	e1overflow7.add(player);
    	e1overflow8.add(player);
    	e1overflow9.add(player);
    	e1overflow10.add(player);
    	e1overflow11.add(player);
    	e1overflow12.add(player);
    	e1overflow13.add(player);
    	e1overflow14.add(player);
    	e1overflow15.add(player);
    	e1overflow16.add(player);
    	
    	e2overflow1.add(player);
    	e2overflow2.add(player);
    	e2overflow3.add(player);
    	e2overflow4.add(player);
    	e2overflow5.add(player);
    	e2overflow6.add(player);
    	e2overflow7.add(player);
    	e2overflow8.add(player);
    	e2overflow9.add(player);
    	e2overflow10.add(player);
    	e2overflow11.add(player);
    	e2overflow12.add(player);
    	e2overflow13.add(player);
    	e2overflow14.add(player);
    	e2overflow15.add(player);
    	e2overflow16.add(player);
    	
    	e3overflow1.add(player);
    	e3overflow2.add(player);
    	e3overflow3.add(player);
    	e3overflow4.add(player);
    	e3overflow5.add(player);
    	e3overflow6.add(player);
    	e3overflow7.add(player);
    	e3overflow8.add(player);
    	e3overflow9.add(player);
    	e3overflow10.add(player);
    	e3overflow11.add(player);
    	e3overflow12.add(player);
    	e3overflow13.add(player);
    	e3overflow14.add(player);
    	e3overflow15.add(player);
    	e3overflow16.add(player);
    	
    	e4overflow1.add(player);
    	e4overflow2.add(player);
    	e4overflow3.add(player);
    	e4overflow4.add(player);
    	e4overflow5.add(player);
    	e4overflow6.add(player);
    	e4overflow7.add(player);
    	e4overflow8.add(player);
    	e4overflow9.add(player);
    	e4overflow10.add(player);
    	e4overflow11.add(player);
    	e4overflow12.add(player);
    	e4overflow13.add(player);
    	e4overflow14.add(player);
    	e4overflow15.add(player);
    	e4overflow16.add(player);
    }
    
    public void clearEntities() {
    	if(entities1 != null) {
	    	entities1.clear();
	    	e1overflow1.clear();
	    	e1overflow2.clear();
	    	e1overflow3.clear();
	    	e1overflow4.clear();
	    	e1overflow5.clear();
	    	e1overflow6.clear();
	    	e1overflow7.clear();
	    	e1overflow8.clear();
	    	e1overflow9.clear();
	    	e1overflow10.clear();
	    	e1overflow11.clear();
	    	e1overflow12.clear();
	    	e1overflow13.clear();
	    	e1overflow14.clear();
	    	e1overflow15.clear();
	    	e1overflow16.clear();
    	}
    	if(entities2 != null) {
	    	entities2.clear();
	    	e2overflow1.clear();
	    	e2overflow2.clear();
	    	e2overflow3.clear();
	    	e2overflow4.clear();
	    	e2overflow5.clear();
	    	e2overflow6.clear();
	    	e2overflow7.clear();
	    	e2overflow8.clear();
	    	e2overflow9.clear();
	    	e2overflow10.clear();
	    	e2overflow11.clear();
	    	e2overflow12.clear();
	    	e2overflow13.clear();
	    	e2overflow14.clear();
	    	e2overflow15.clear();
	    	e2overflow16.clear();
    	}
    	if(entities3 != null) {
	    	entities3.clear();
	    	e3overflow1.clear();
	    	e3overflow2.clear();
	    	e3overflow3.clear();
	    	e3overflow4.clear();
	    	e3overflow5.clear();
	    	e3overflow6.clear();
	    	e3overflow7.clear();
	    	e3overflow8.clear();
	    	e3overflow9.clear();
	    	e3overflow10.clear();
	    	e3overflow11.clear();
	    	e3overflow12.clear();
	    	e3overflow13.clear();
	    	e3overflow14.clear();
	    	e3overflow15.clear();
	    	e3overflow16.clear();
    	}
    	if(entities4 != null) {
	    	entities4.clear();
	    	e4overflow1.clear();
	    	e4overflow2.clear();
	    	e4overflow3.clear();
	    	e4overflow4.clear();
	    	e4overflow5.clear();
	    	e4overflow6.clear();
	    	e4overflow7.clear();
	    	e4overflow8.clear();
	    	e4overflow9.clear();
	    	e4overflow10.clear();
	    	e4overflow11.clear();
	    	e4overflow12.clear();
	    	e4overflow13.clear();
	    	e4overflow14.clear();
	    	e4overflow15.clear();
	    	e4overflow16.clear();
    	}
    }
    
    public ArrayList<Entity> getE1overflow7() {
		return e1overflow7;
	}

	public void setE1overflow7(ArrayList<Entity> e1overflow7) {
		this.e1overflow7 = e1overflow7;
	}

	public ArrayList<Entity> getE1overflow8() {
		return e1overflow8;
	}

	public void setE1overflow8(ArrayList<Entity> e1overflow8) {
		this.e1overflow8 = e1overflow8;
	}

	public ArrayList<Entity> getE1overflow9() {
		return e1overflow9;
	}

	public void setE1overflow9(ArrayList<Entity> e1overflow9) {
		this.e1overflow9 = e1overflow9;
	}

	public ArrayList<Entity> getE1overflow10() {
		return e1overflow10;
	}

	public void setE1overflow10(ArrayList<Entity> e1overflow10) {
		this.e1overflow10 = e1overflow10;
	}

	public ArrayList<Entity> getE1overflow11() {
		return e1overflow11;
	}

	public void setE1overflow11(ArrayList<Entity> e1overflow11) {
		this.e1overflow11 = e1overflow11;
	}

	public ArrayList<Entity> getE1overflow12() {
		return e1overflow12;
	}

	public ArrayList<Entity> getE1overflow13() {
		return e1overflow13;
	}

	public ArrayList<Entity> getE4overflow3() {
		return e4overflow3;
	}

	public ArrayList<Entity> getE4overflow4() {
		return e4overflow4;
	}

	public ArrayList<Entity> getE4overflow5() {
		return e4overflow5;
	}

	public ArrayList<Entity> getE4overflow6() {
		return e4overflow6;
	}

	public ArrayList<Entity> getE4overflow7() {
		return e4overflow7;
	}

	public ArrayList<Entity> getE4overflow8() {
		return e4overflow8;
	}

	public ArrayList<Entity> getE4overflow9() {
		return e4overflow9;
	}

	public ArrayList<Entity> getE4overflow10() {
		return e4overflow10;
	}

	public ArrayList<Entity> getE4overflow11() {
		return e4overflow11;
	}

	public ArrayList<Entity> getE4overflow12() {
		return e4overflow12;
	}

	public ArrayList<Entity> getE4overflow13() {
		return e4overflow13;
	}

	public ArrayList<Entity> getE4overflow14() {
		return e4overflow14;
	}

	public ArrayList<Entity> getE4overflow15() {
		return e4overflow15;
	}

	public ArrayList<Entity> getE4overflow16() {
		return e4overflow16;
	}

	public void setE1overflow13(ArrayList<Entity> e1overflow13) {
		this.e1overflow13 = e1overflow13;
	}

	public ArrayList<Entity> getE1overflow14() {
		return e1overflow14;
	}

	public void setE1overflow14(ArrayList<Entity> e1overflow14) {
		this.e1overflow14 = e1overflow14;
	}

	public ArrayList<Entity> getE1overflow15() {
		return e1overflow15;
	}

	public void setE1overflow15(ArrayList<Entity> e1overflow15) {
		this.e1overflow15 = e1overflow15;
	}

	public ArrayList<Entity> getE1overflow16() {
		return e1overflow16;
	}

	public void setE1overflow16(ArrayList<Entity> e1overflow16) {
		this.e1overflow16 = e1overflow16;
	}

	public ArrayList<Entity> getE2overflow13() {
		return e2overflow13;
	}

	public void setE2overflow13(ArrayList<Entity> e2overflow13) {
		this.e2overflow13 = e2overflow13;
	}

	public ArrayList<Entity> getE2overflow14() {
		return e2overflow14;
	}

	public void setE2overflow14(ArrayList<Entity> e2overflow14) {
		this.e2overflow14 = e2overflow14;
	}

	public ArrayList<Entity> getE2overflow15() {
		return e2overflow15;
	}

	public void setE2overflow15(ArrayList<Entity> e2overflow15) {
		this.e2overflow15 = e2overflow15;
	}

	public ArrayList<Entity> getE2overflow16() {
		return e2overflow16;
	}

	public ArrayList<Entity> getE3overflow3() {
		return e3overflow3;
	}

	public ArrayList<Entity> getE3overflow4() {
		return e3overflow4;
	}

	public ArrayList<Entity> getE3overflow5() {
		return e3overflow5;
	}

	public ArrayList<Entity> getE3overflow6() {
		return e3overflow6;
	}

	public ArrayList<Entity> getE3overflow7() {
		return e3overflow7;
	}

	public ArrayList<Entity> getE3overflow8() {
		return e3overflow8;
	}

	public ArrayList<Entity> getE3overflow9() {
		return e3overflow9;
	}

	public ArrayList<Entity> getE3overflow10() {
		return e3overflow10;
	}

	public ArrayList<Entity> getE3overflow11() {
		return e3overflow11;
	}

	public ArrayList<Entity> getE3overflow12() {
		return e3overflow12;
	}

	public ArrayList<Entity> getE3overflow13() {
		return e3overflow13;
	}

	public ArrayList<Entity> getE3overflow14() {
		return e3overflow14;
	}

	public ArrayList<Entity> getE3overflow15() {
		return e3overflow15;
	}

	public ArrayList<Entity> getE3overflow16() {
		return e3overflow16;
	}

	public void setE2overflow16(ArrayList<Entity> e2overflow16) {
		this.e2overflow16 = e2overflow16;
	}

	public void setE1overflow12(ArrayList<Entity> e1overflow12) {
		this.e1overflow12 = e1overflow12;
	}

	public ArrayList<Entity> getE1overflow1() {
		return e1overflow1;
	}

	public ArrayList<Entity> getE1overflow2() {
		return e1overflow2;
	}
	
	public ArrayList<Entity> getE1overflow3() {
		return e1overflow3;
	}

	public ArrayList<Entity> getE1overflow4() {
		return e1overflow4;
	}
	
	public ArrayList<Entity> getE1overflow5() {
		return e1overflow5;
	}

	public ArrayList<Entity> getE1overflow6() {
		return e1overflow6;
	}
	
	public ArrayList<Entity> getEntities1() {
		return entities1;
	}

	public ArrayList<Entity> getE2overflow3() {
		return e2overflow3;
	}

	public ArrayList<Entity> getE2overflow4() {
		return e2overflow4;
	}

	public ArrayList<Entity> getE2overflow5() {
		return e2overflow5;
	}

	public ArrayList<Entity> getE2overflow6() {
		return e2overflow6;
	}

	public ArrayList<Entity> getE2overflow7() {
		return e2overflow7;
	}

	public ArrayList<Entity> getE2overflow8() {
		return e2overflow8;
	}

	public ArrayList<Entity> getE2overflow9() {
		return e2overflow9;
	}

	public ArrayList<Entity> getE2overflow10() {
		return e2overflow10;
	}

	public ArrayList<Entity> getE2overflow11() {
		return e2overflow11;
	}

	public ArrayList<Entity> getE2overflow12() {
		return e2overflow12;
	}

	public ArrayList<Entity> getToAdd() {
		return toAdd;
	}

	public ArrayList<NPC> getToAddNpc2() {
		return toAddNpc2;
	}

	public ArrayList<Entity> getToRemove() {
		return toRemove;
	}

	public ArrayList<NPC> getNpcs3() {
		return npcs3;
	}

	public ArrayList<NPC> getNpcs4() {
		return npcs4;
	}

	public boolean isPositionDetermined() {
		return positionDetermined;
	}

	public int getCurrentSorting() {
		return currentSorting;
	}

	public Comparator<Entity> getRenderSorter() {
		return renderSorter;
	}

	public ArrayList<Entity> getE2overflow1() {
		return e2overflow1;
	}

	public ArrayList<Entity> getE2overflow2() {
		return e2overflow2;
	}
	
	public ArrayList<Entity> getE3overflow1() {
		return e3overflow1;
	}

	public ArrayList<Entity> getE3overflow2() {
		return e3overflow2;
	}
	
	public ArrayList<Entity> getE4overflow1() {
		return e4overflow1;
	}

	public ArrayList<Entity> getE4overflow2() {
		return e4overflow2;
	}

	public void addEntity1(Entity e)
    {
    	if(entities1.size() >= 60) {
    		if(e1overflow1.size() >= 60) {
    			if(e1overflow2.size() >= 60) {
    				if(e1overflow3.size() >= 60) {
    					if(e1overflow4.size() >= 60) {
    						if(e1overflow5.size() >= 60) {
    							if(e1overflow6.size() >= 60) {
    								if(e1overflow7.size() >= 60) {
    					    			if(e1overflow8.size() >= 60) {
    					    				if(e1overflow9.size() >= 60) {
    					    					if(e1overflow10.size() >= 60) {
    					    						if(e1overflow11.size() >= 60) {
    					    							if(e1overflow12.size() >= 60) {
    					    								if(e1overflow13.size() >= 60) {
    					    									if(e1overflow14.size() >= 60) {
    					    										if(e1overflow15.size() >= 60) {
    					    											if(e1overflow16.size() >= 60) {
    			    					    			    				
    			    					    			    			}
    			    					    			    			e1overflow16.add(e);
    			    					    			    			return;
    		    					    			    			}
    		    					    			    			e1overflow15.add(e);
    		    					    			    			return;
    	    					    			    			}
    	    					    			    			e1overflow14.add(e);
    	    					    			    			return;
        					    			    			}
        					    			    			e1overflow13.add(e);
        					    			    			return;
    					    			    			}
    					    			    			e1overflow12.add(e);
    					    			    			return;
    					    		    			}
    					    		    			e1overflow11.add(e);
    					    		    			return;
    					    	    			}
    					    	    			e1overflow10.add(e);
    					    	    			return;
    					        			}
    					        			e1overflow9.add(e);
    					        			return;
    					    			}
    					    			e1overflow8.add(e);
    					    			return;
    					    		}
    					    		e1overflow7.add(e);
    					    		return;
    			    			}
    			    			e1overflow6.add(e);
    			    			return;
    		    			}
    		    			e1overflow5.add(e);
    		    			return;
    	    			}
    	    			e1overflow4.add(e);
    	    			return;
        			}
        			e1overflow3.add(e);
        			return;
    			}
    			e1overflow2.add(e);
    			return;
    		}
    		e1overflow1.add(e);
    		return;
    	}
        entities1.add(e);
    }
	
    public void addEntity2(Entity e)
    {
    	if(entities2.size() >= 60) {
    		if(e2overflow1.size() >= 60) {
    			if(e2overflow2.size() >= 60) {
    				if(e2overflow3.size() >= 60) {
    					if(e2overflow4.size() >= 60) {
    						if(e2overflow5.size() >= 60) {
    							if(e2overflow6.size() >= 60) {
    								if(e2overflow7.size() >= 60) {
    					    			if(e2overflow8.size() >= 60) {
    					    				if(e2overflow9.size() >= 60) {
    					    					if(e2overflow10.size() >= 60) {
    					    						if(e2overflow11.size() >= 60) {
    					    							if(e2overflow12.size() >= 60) {
    					    								if(e2overflow13.size() >= 60) {
    			    					    					if(e2overflow14.size() >= 60) {
    			    					    						if(e2overflow15.size() >= 60) {
    			    					    							if(e2overflow16.size() >= 60) {
    			    					    			    				
    			    					    			    			}
    			    					    			    			e2overflow16.add(e);
    			    					    			    			return;
    			    					    		    			}
    			    					    		    			e2overflow15.add(e);
    			    					    		    			return;
    			    					    	    			}
    			    					    	    			e2overflow14.add(e);
    			    					    	    			return;
    			    					        			}
    			    					        			e2overflow13.add(e);
    			    					        			return;
    					    			    			}
    					    			    			e2overflow12.add(e);
    					    			    			return;
    					    		    			}
    					    		    			e2overflow11.add(e);
    					    		    			return;
    					    	    			}
    					    	    			e2overflow10.add(e);
    					    	    			return;
    					        			}
    					        			e2overflow9.add(e);
    					        			return;
    					    			}
    					    			e2overflow8.add(e);
    					    			return;
    					    		}
    					    		e2overflow7.add(e);
    					    		return;
    			    			}
    			    			e2overflow6.add(e);
    			    			return;
    		    			}
    		    			e2overflow5.add(e);
    		    			return;
    	    			}
    	    			e2overflow4.add(e);
    	    			return;
        			}
        			e2overflow3.add(e);
        			return;
    			}
    			e2overflow2.add(e);
    			return;
    		}
    		e2overflow1.add(e);
    		return;
    	}
        entities2.add(e);
    }
    
    public void addEntity3(Entity e)
    {
    	if(entities3.size() >= 60) {
    		if(e3overflow1.size() >= 60) {
    			if(e3overflow2.size() >= 60) {
    				if(e3overflow3.size() >= 60) {
    					if(e3overflow4.size() >= 60) {
    						if(e3overflow5.size() >= 60) {
    							if(e3overflow6.size() >= 60) {
    								if(e3overflow7.size() >= 60) {
    					    			if(e3overflow8.size() >= 60) {
    					    				if(e3overflow9.size() >= 60) {
    					    					if(e3overflow10.size() >= 60) {
    					    						if(e3overflow11.size() >= 60) {
    					    							if(e3overflow12.size() >= 60) {
    					    								if(e3overflow13.size() >= 60) {
    			    					    					if(e3overflow14.size() >= 60) {
    			    					    						if(e3overflow15.size() >= 60) {
    			    					    							if(e3overflow16.size() >= 60) {
    			    					    			    				
    			    					    			    			}
    			    					    			    			e3overflow16.add(e);
    			    					    			    			return;
    			    					    		    			}
    			    					    		    			e3overflow15.add(e);
    			    					    		    			return;
    			    					    	    			}
    			    					    	    			e3overflow14.add(e);
    			    					    	    			return;
    			    					        			}
    			    					        			e3overflow13.add(e);
    			    					        			return;
    					    			    			}
    					    			    			e3overflow12.add(e);
    					    			    			return;
    					    		    			}
    					    		    			e3overflow11.add(e);
    					    		    			return;
    					    	    			}
    					    	    			e3overflow10.add(e);
    					    	    			return;
    					        			}
    					        			e3overflow9.add(e);
    					        			return;
    					    			}
    					    			e3overflow8.add(e);
    					    			return;
    					    		}
    					    		e3overflow7.add(e);
    					    		return;
    			    			}
    			    			e3overflow6.add(e);
    			    			return;
    		    			}
    		    			e3overflow5.add(e);
    		    			return;
    	    			}
    	    			e3overflow4.add(e);
    	    			return;
        			}
        			e3overflow3.add(e);
        			return;
    			}
    			e3overflow2.add(e);
    			return;
    		}
    		e3overflow1.add(e);
    		return;
    	}
        entities3.add(e);
    }
    
    public void addEntity4(Entity e)
    {
    	if(entities4.size() >= 60) {
    		if(e4overflow1.size() >= 60) {
    			if(e4overflow2.size() >= 60) {
    				if(e4overflow3.size() >= 60) {
    					if(e4overflow4.size() >= 60) {
    						if(e4overflow5.size() >= 60) {
    							if(e4overflow6.size() >= 60) {
    								if(e4overflow7.size() >= 60) {
    					    			if(e4overflow8.size() >= 60) {
    					    				if(e4overflow9.size() >= 60) {
    					    					if(e4overflow10.size() >= 60) {
    					    						if(e4overflow11.size() >= 60) {
    					    							if(e4overflow12.size() >= 60) {
    					    								if(e4overflow13.size() >= 60) {
    					    									if(e4overflow14.size() >= 60) {
    					    										if(e4overflow15.size() >= 60) {
    					    											if(e4overflow16.size() >= 60) {
    			    					    			    				
    			    					    			    			}
    			    					    			    			e4overflow16.add(e);
    			    					    			    			return;
    		    					    			    			}
    		    					    			    			e4overflow15.add(e);
    		    					    			    			return;
    	    					    			    			}
    	    					    			    			e4overflow14.add(e);
    	    					    			    			return;
        					    			    			}
        					    			    			e4overflow13.add(e);
        					    			    			return;
    					    			    			}
    					    			    			e4overflow12.add(e);
    					    			    			return;
    					    		    			}
    					    		    			e4overflow11.add(e);
    					    		    			return;
    					    	    			}
    					    	    			e4overflow10.add(e);
    					    	    			return;
    					        			}
    					        			e4overflow9.add(e);
    					        			return;
    					    			}
    					    			e4overflow8.add(e);
    					    			return;
    					    		}
    					    		e4overflow7.add(e);
    					    		return;
    			    			}
    			    			e4overflow6.add(e);
    			    			return;
    		    			}
    		    			e4overflow5.add(e);
    		    			return;
    	    			}
    	    			e4overflow4.add(e);
    	    			return;
        			}
        			e4overflow3.add(e);
        			return;
    			}
    			e4overflow2.add(e);
    			return;
    		}
    		e4overflow1.add(e);
    		return;
    	}
        entities4.add(e);
    }
    
    public void add(Entity e)
    {
        toAdd.add(e);
    }
    
    public void remove(Entity e)
    {
        toRemove.add(e);
    }

    
    
    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
		addEntity1(player);
		addEntity2(player);
		addEntity3(player);
		addEntity4(player);
    }
    
    public Player getPlayer2()
    {
        return player2;
    }

    public void setPlayer2(Player player2)
    {
        this.player2 = player2;
    }

    public ArrayList<Entity> getEntities()
    {
        return entities1;
    }

    public void setEntities(ArrayList<Entity> entities)
    {
        this.entities1 = entities;
    }
    
    public ArrayList<Entity> getEntities2()
    {
        return entities2;
    }
    
    public ArrayList<Entity> getEntities3()
    {
        return entities3;
    }
    
    public ArrayList<Entity> getEntities4()
    {
        return entities4;
    }

    public void setEntities2(ArrayList<Entity> entities)
    {
        this.entities2 = entities;
    }
    
    public void createPlayer2() {
    	addEntity1(player2);
		addEntity2(player2);
    }

	public void addNPCMain(NPC npc) {
		this.npcs.add(npc);
	}
	
	public void addNPC2Main(NPC npc) {
		this.npcs2.add(npc);
	}
	
	public void addNPC3Main(NPC npc) {
		this.npcs3.add(npc);
	}
	
	public void addNPC2(NPC npc) {
		this.toAddNpc2.add(npc);
	}

	public ArrayList<NPC> getNpcs() {
		return npcs;
	}
	
	public ArrayList<NPC> getNpcs2() {
		return npcs2;
	}

	public void setNpcs(ArrayList<NPC> npcs) {
		this.npcs = npcs;
	}
	
}
