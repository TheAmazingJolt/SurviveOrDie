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

	private ArrayList<Entity> entities2;
	private ArrayList<Entity> e2overflow1;
	private ArrayList<Entity> e2overflow2;

	private ArrayList<Entity> entities3;
	private ArrayList<Entity> e3overflow1;
	private ArrayList<Entity> e3overflow2;
	
	private ArrayList<Entity> toAdd;
	private ArrayList<NPC> toAddNpc2;
	private ArrayList<Entity> toRemove;
	
	private ArrayList<NPC> npcs;
	private ArrayList<NPC> npcs2;
	private ArrayList<NPC> npcs3;
	
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
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		if(handler.getGame().isMultiplayer()) {
			player2 = new Player(handler, 3164, 3164, 2, -2);
		}
		entities1 = new ArrayList<Entity>();
		e1overflow1 = new ArrayList<Entity>();
		e1overflow2 = new ArrayList<Entity>();
		entities2 = new ArrayList<Entity>();
		e2overflow1 = new ArrayList<Entity>();
		e2overflow2 = new ArrayList<Entity>();
		entities3 = new ArrayList<Entity>();
		e3overflow1 = new ArrayList<Entity>();
		e3overflow2 = new ArrayList<Entity>();
		toAdd = new ArrayList<Entity>();
		toAddNpc2 = new ArrayList<NPC>();
		toRemove = new ArrayList<Entity>();
		npcs = new ArrayList<NPC>();
		npcs2 = new ArrayList<NPC>();
		npcs3 = new ArrayList<NPC>();
		addEntity1(player);
		addEntity2(player);
		addEntity3(player);
		addEntity1(player2);
		addNPCMain(new Francisco(handler, 832f, 832f, -3));
		addNPC2Main(new Sierra(handler, 832f, 832f, -4));
		addNPC3Main(new Chris(handler, 832f, 832f, -5));
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
	        for(NPC n : npcs) {
	        	n.tick();
	        }
	        entities1.sort(renderSorter);
	        e1overflow1.sort(renderSorter);
	        e1overflow2.sort(renderSorter);
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
	        for(NPC n : npcs2) {
	        	n.tick();
	        }
	        entities2.sort(renderSorter);
	        e2overflow1.sort(renderSorter);
	        e2overflow2.sort(renderSorter);
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
	        for(NPC n : npcs3) {
	        	n.tick();
	        }
	        entities3.sort(renderSorter);
	        e3overflow1.sort(renderSorter);
	        e3overflow2.sort(renderSorter);
	        npcs3.sort(renderSorter);
	        entities3.addAll(toAdd);
	        toAdd.clear();
	        entities3.removeAll(toRemove);
	        toRemove.clear();
		}
    }

    public void render(Graphics g)
    {
    	if(!positionDetermined) {
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
    		}
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
	    	for(NPC n : npcs3) {
	    		n.render(g);
	    	}
    		player.postRender(g);
    	}
        
    }
    
    public void addToOverflow() {
    	e1overflow1.add(player);
    	e1overflow2.add(player);
    	e2overflow1.add(player);
    	e2overflow2.add(player);
    	e3overflow1.add(player);
    	e3overflow2.add(player);
    }
    
    public ArrayList<Entity> getE1overflow1() {
		return e1overflow1;
	}

	public ArrayList<Entity> getE1overflow2() {
		return e1overflow2;
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

	public void addEntity1(Entity e)
    {
    	if(entities1.size() >= 67) {
    		if(e1overflow1.size() >= 67) {
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
    	if(entities2.size() >= 67) {
    		if(e2overflow1.size() >= 67) {
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
	    if(entities3.size() >= 67) {
	   		if(e3overflow1.size() >= 67) {
	   			e3overflow2.add(e);
	   			return;
	   		}
	   		e3overflow1.add(e);
	    	return;
	    }
	    entities3.add(e);
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
