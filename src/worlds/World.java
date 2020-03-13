package worlds;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import entities.Entity;
import entities.EntityManager;
import entities.creatures.Boss1;
import entities.creatures.Boss2;
import entities.creatures.Boss3;
import entities.creatures.HellZombie;
import entities.creatures.IcyZombie;
import entities.creatures.Penguin;
import entities.creatures.Player;
import entities.creatures.Zombie;
import entities.statics.Coal;
import entities.statics.Flint;
import entities.statics.HellTree;
import entities.statics.IcyTree;
import entities.statics.Iron;
import entities.statics.Stone;
import entities.statics.Tree;
import items.ItemManager;
import main.Handler;
import states.State;
import tiles.DoorTile;
import tiles.DoorTile2;
import tiles.SandyDoorTile;
import tiles.SandyDoorTile2;
import tiles.Tile;
import tiles.structures.Structure;
import utils.Utils;

public class World
{

	public static final int DEFAULT_TREE_HEALTH = 15;
    public static final int DEFAULT_ROCK_HEALTH = 20;
    private static Handler handler;
    private int width;
    private int height;
    private int height2;
    private int spawnX = 0;
    private int spawnY = 0;
    private int[][] tiles;
    private static int count = 0;
    private static int worldNum;
    private static int loadedSave = 1;
    private static boolean loaded;
    private static EntityManager entityManager;
    private ItemManager itemManager;
    private static int currentWorld;
    private static int totalEntities1;
    private static int totalEntities2;
    
    private int furthestWorldVisited = 1;
    
    private int worldGenWidth = 100;
    private int worldGenHeight = 100;
    
    private WorldGenerator generator;
    
    private boolean setup = false;
    
    private boolean world1Loaded = false;
    private boolean world2Loaded = false;
    private boolean world3Loaded = false;
    private boolean world4Loaded = false;
    
    private boolean aboutToChange = false;
	
    public World(Handler handle, String path)
    {
        handler = handle;
        entityManager = new EntityManager(handler);
        itemManager = new ItemManager(handler);
        if(handler.getGame().getGameType().contains("story")) {
            spawnX = 3264;
            spawnY = 3264;
        	entityManager.setup(new Player(handler, spawnX, spawnY, 1, -1));
            loadWorld(path);
        	setup = true;
        }
    }
    
    public World(Handler handle, boolean isLoading)
    {
        handler = handle;
        entityManager = new EntityManager(handler);
        itemManager = new ItemManager(handler);
        loaded = isLoading;
	    if(handler.getGame().getGameType().contains("survival") || handler.getGame().getGameType().contains("creative")) {
	      	entityManager.setup(new Player(handler, (worldGenWidth / 2) * 64, (worldGenHeight / 2) * 64, 1, -1));
	       	generateWorld();
	       	setup = true;
	    }
    }
    
    public World(Handler handle, boolean isLoading, int world)
    {
        handler = handle;
        entityManager = new EntityManager(handler);
        itemManager = new ItemManager(handler);
        loaded = isLoading;
        currentWorld = world;
	    if(handler.getGame().getGameType().contains("survival") || handler.getGame().getGameType().contains("creative")) {
	      	entityManager.setup(new Player(handler, (worldGenWidth / 2) * 64, (worldGenHeight / 2) * 64, 1, -1));
	    	entityManager.getPlayer().setKilledEnemies(14);
	       	generateWorld();
	       	setup = true;
	    }
    }
    
    public World(Handler handle, boolean isLoading, int saveToLoad, int world)
    {
        handler = handle;
        entityManager = new EntityManager(handler);
        itemManager = new ItemManager(handler);
        loaded = isLoading;
        loadedSave = saveToLoad;
        currentWorld = world;
	    if(handler.getGame().getGameType().contains("survival") || handler.getGame().getGameType().contains("creative")) {
	      	entityManager.setup(new Player(handler, (worldGenWidth / 2) * 64, (worldGenHeight / 2) * 64, 1, -1));
	    	entityManager.getPlayer().setKilledEnemies(14);
	       	generateWorld();
	       	setup = true;
	    }
    }

    public void tick()
    {
        entityManager.tick();
        itemManager.tick();
        if(handler.getWorld().getEntityManager().getPlayer().getKilledEnemies() >= 15) {
        	if(handler.getWorld().getCurrentWorld() == 4) {
        		SandyDoorTile.open();
        		return;
        	}
            DoorTile.open();
        }else {
        	if(handler.getWorld().getCurrentWorld() == 4) {
        		SandyDoorTile.close();
        		return;
        	}
        	DoorTile.close();
        }
        if(handler.getWorld().getEntityManager().getPlayer().getKilledBosses() >= 1 * handler.getWorld().getCurrentWorld()) {
        	if(handler.getWorld().getCurrentWorld() == 4) {
        		SandyDoorTile2.open();
        		return;
        	}
            DoorTile2.open();
    	}else {
        	if(handler.getWorld().getCurrentWorld() == 4) {
        		SandyDoorTile2.close();
        		return;
        	}
        	DoorTile2.close();
    	}
    }

    public void render(Graphics g)
    {
        int xStart = (int)Math.max(0.0F, handler.getGameCamera().getxOffset() / 64F);
        int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset() + (float)handler.getWidth()) / 64F + 1.0F);
        int yStart = (int)Math.max(0.0F, handler.getGameCamera().getyOffset() / 64F);
        int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffset() + (float)handler.getHeight()) / 64F + 1.0F);
        for(int y = yStart; y < yEnd; y++)
        {
            for(int x = xStart; x < xEnd; x++) {
                if(getStructure(x, y) == null) {
                    getTile(x, y).render(g, (int)((float)(x * 64) - handler.getGameCamera().getxOffset()), (int)((float)(y * 64) - handler.getGameCamera().getyOffset()));
                }else if(getStructure(x,y) != null) {
                    getTile(x, y).render(g, (int)((float)(x * 64) - handler.getGameCamera().getxOffset()), (int)((float)(y * 64) - handler.getGameCamera().getyOffset()));
                    getStructure(x, y).render(g, (int)((float)(x * 64) - handler.getGameCamera().getxOffset()), (int)((float)(y * 64) - handler.getGameCamera().getyOffset()));
                }
            }

        }

        itemManager.render(g);
        entityManager.render(g);
        handler.getGuiManager().render(g);
    }

    public int getFurthestWorldVisited() {
		return furthestWorldVisited;
	}

	public void setFurthestWorldVisited(int furthestWorldVisited) {
		this.furthestWorldVisited = furthestWorldVisited;
	}

	public boolean isAboutToChange() {
		return aboutToChange;
	}

	public void setAboutToChange(boolean aboutToChange) {
		this.aboutToChange = aboutToChange;
	}

	public boolean isWorld1Loaded() {
		return world1Loaded;
	}

	public void setWorld1Loaded(boolean world1Loaded) {
		this.world1Loaded = world1Loaded;
	}

	public boolean isWorld2Loaded() {
		return world2Loaded;
	}

	public void setWorld2Loaded(boolean world2Loaded) {
		this.world2Loaded = world2Loaded;
	}

	public boolean isWorld3Loaded() {
		return world3Loaded;
	}

	public void setWorld3Loaded(boolean world3Loaded) {
		this.world3Loaded = world3Loaded;
	}

	public boolean isWorld4Loaded() {
		return world4Loaded;
	}

	public void setWorld4Loaded(boolean world4Loaded) {
		this.world4Loaded = world4Loaded;
	}

	public Tile getTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;
        if(y == 0) {
        	Tile t = Tile.getTiles().get(x);
        	if(t == null)
                return Tile.dirtTile;
            else
                return t;
        }else {
        	Tile t = Tile.getTiles().get((width * y) + x);
        	if(t == null)
                return Tile.dirtTile;
            else
                return t;
        }
        
    }
    
    public Structure getStructure(int x, int y)
    {
    	if(Structure.getStructures().size() == 0) {
    		return null;
    	}
    	Structure t = null;
        if(x < 0 || y < 0 || x >= width || y >= height)
            return t;
        if(y == 0) {
        	for(Structure s : Structure.getStructures()) {
        		if(s.getLocationX() == x) {
        			if(s.getLocationY() == y) {
        				t = s;
        			}
        		}
        	}
        	if(t == null)
                return t;
            else
                return t;
        }else {
        	for(Structure s : Structure.getStructures()) {
        		if(s.getLocationX() == x) {
        			if(s.getLocationY() == y) {
        				t = s;
        			}
        		}
        	}
        	if(t == null)
                return t;
            else
                return t;
        }
        
    }

    public int getLoadedSave() {
		return loadedSave;
	}

	public void setLoadedWorld(int loadedWorld) {
		loadedSave = loadedWorld;
	}

	public void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String file2 = Utils.loadFileAsString(path.replace(".txt", "entities.txt")); 
        String tokens[] = file.split("\\s+");
        String tokens2[] = file2.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        height2 = Utils.parseInt(tokens2[0]);
//        this.spawnX = Utils.parseInt(tokens[2]);
//        this.spawnY = Utils.parseInt(tokens[3]);
        tiles = new int[width][height];
        String token[];
        String token2[];
        String token3[];
        String entityName;
        int entityNum = 0;
        int entityNum2 = 0;
        int entityNum3 = 0;
        for(int y = 1; y <= height2; y++)
        {
        	try {
                token2 = tokens2[y].split("\\:");
                entityName = token2[0];
                token3 = token2[1].split("\\,");
               	if(currentWorld == 1) {
               		entityNum++;
               		if(entityName.contains("t")) {
                       	entityManager.addEntity1(new Tree(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum));
                  	}else if(entityName.contains("s")) {
                   		entityManager.addEntity1(new Stone(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum));
                   	}else if(entityName.contains("i")) {
                   		entityManager.addEntity1(new Iron(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum));
                    }else if(entityName.contains("f")) {
                   		entityManager.addEntity1(new Flint(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum));
                   	}else if(entityName.contains("z")) {
                   		entityManager.addEntity1(new Zombie(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum, getEntityManager().getPlayer()));
                   	}else if(entityName.contains("b")) {
                  		entityManager.addEntity1(new Boss1(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, 20, entityNum));
                   	}
               	}else if(currentWorld == 2) {
               		entityNum2++;
               		if(entityName.contains("t")) {
                       	entityManager.addEntity2(new HellTree(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum2));
                   	}else if(entityName.contains("s")) {
                  		entityManager.addEntity2(new Stone(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum2));
                   	}else if(entityName.contains("i")) {
                  		entityManager.addEntity2(new Iron(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum2));
                   	}else if(entityName.contains("f")) {
                  		entityManager.addEntity2(new Flint(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum2));
                  	}else if(entityName.contains("z")) {
                  		entityManager.addEntity2(new HellZombie(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum2, getEntityManager().getPlayer()));
                  	}else if(entityName.contains("c")) {
                 		entityManager.addEntity2(new Coal(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum2));
                   	}else if(entityName.contains("b")) {
                   		entityManager.addEntity2(new Boss2(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, 50, entityNum2));
                   	}
              	}else if(currentWorld == 3) {
               		entityNum3++;
                    if(entityName.contains("t")) {
                       	entityManager.addEntity3(new IcyTree(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum3));
                   	}else if(entityName.contains("s")) {
                  		entityManager.addEntity3(new Stone(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum3));
                   	}else if(entityName.contains("i")) {
                  		entityManager.addEntity3(new Iron(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum3));
                   	}else if(entityName.contains("f")) {
                  		entityManager.addEntity3(new Flint(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum3));
                  	}else if(entityName.contains("z")) {
                  		entityManager.addEntity3(new IcyZombie(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum3, getEntityManager().getPlayer()));
                  	}else if(entityName.contains("c")) {
                 		entityManager.addEntity3(new Coal(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum3));
                   	}else if(entityName.contains("p")) {
                 		entityManager.addEntity3(new Penguin(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, entityNum3));
                   	}else if(entityName.contains("b")) {
                   		entityManager.addEntity3(new Boss3(handler, (float) Integer.parseInt(token3[0]) * 64, (float) Integer.parseInt(token3[1]) * 64, 50, entityNum3));
                   	}
              	}
                	
        	}catch(ArrayIndexOutOfBoundsException e) {
               	
            }
        }
      
        for(int y = 0; y < height; y++) {
        	for(int x = 0; x < width; x++) {
            	token = tokens[x + y * width + 4].split("\\ ");
                tiles[x][y] = Utils.parseInt(token[0]);
        		if(tiles[x][y] == 0) {
        			Tile.getTiles().add(Tile.grassTile);
        		}else if(tiles[x][y] == 1) {
        			Tile.getTiles().add(Tile.dirtTile);
        		}else if(tiles[x][y] == 2) {
        			Tile.getTiles().add(Tile.rockTile);
        		}else if(tiles[x][y] == 3) {
        			Tile.getTiles().add(Tile.stoneTile);
        		}else if(tiles[x][y] == 4) {
        			Tile.getTiles().add(Tile.waterTile);
        		}else if(tiles[x][y] == 5) {
        			Tile.getTiles().add(Tile.doorTile);
        		}else if(tiles[x][y] == 6) {
        			Tile.getTiles().add(Tile.warpTile);
        		}else if(tiles[x][y] == 7) {
        			Tile.getTiles().add(Tile.hellRockTile);
        		}else if(tiles[x][y] == 8) {
        			Tile.getTiles().add(Tile.hellStoneTile);
        		}else if(tiles[x][y] == 9) {
        			Tile.getTiles().add(Tile.hellGrassTile);
        		}else if(tiles[x][y] == 11) {
        			Tile.getTiles().add(Tile.icyRockTile);
        		}else if(tiles[x][y] == 12) {
        			Tile.getTiles().add(Tile.icyStoneTile);
        		}else if(tiles[x][y] == 13) {
        			Tile.getTiles().add(Tile.icyGrassTile);
        		}else if(tiles[x][y] == 14) {
        			Tile.getTiles().add(Tile.doorTile2);
        		}else if(tiles[x][y] == 15) {
        			Tile.getTiles().add(Tile.sandTile);
        		}else if(tiles[x][y] == 16) {
        			Tile.getTiles().add(Tile.sandStoneTile);
        		}else if(tiles[x][y] == 17) {
        			Tile.getTiles().add(Tile.sandStoneWallTile);
        		}else if(tiles[x][y] == 18) {
        			Tile.getTiles().add(Tile.sandyDoorTile);
        		}else if(tiles[x][y] == 19) {
        			Tile.getTiles().add(Tile.sandyDoorTile2);
        		}else if(tiles[x][y] == 20) {
        			Tile.getTiles().add(Tile.warpDownTile);
        		}
        	}
        }
    }
	
	public void generateWorld()
    {
		width = 100;
		height = 100;
        tiles = new int[width][height];
        
        if(!loaded) {
			generator = new WorldGenerator(width, height, 10, currentWorld, handler);
	    	generator.generate();
	    	
	    	for(Entity e : generator.getEntities()) {
	    		if(currentWorld == 1) {
	    			entityManager.addEntity1(e);
	    		}else if(currentWorld == 2) {
	    			entityManager.addEntity2(e);
	    		}else if(currentWorld == 3) {
	    			entityManager.addEntity3(e);
	    		}else if(currentWorld == 4) {
	    			entityManager.addEntity4(e);
	    		}
	    	}
	        
	        for(int y = 0; y < height; y++) {
	        	for(int x = 0; x < width; x++) {
	                tiles[x][y] = generator.getTile(x, y);
	        		if(tiles[x][y] == 0) {
	        			Tile.getTiles().add(Tile.grassTile);
	        		}else if(tiles[x][y] == 1) {
	        			Tile.getTiles().add(Tile.dirtTile);
	        		}else if(tiles[x][y] == 2) {
	        			Tile.getTiles().add(Tile.stoneTile);
	        		}else if(tiles[x][y] == 3) {
	        			Tile.getTiles().add(Tile.rockTile);
	        		}else if(tiles[x][y] == 4) {
	        			Tile.getTiles().add(Tile.waterTile);
	        		}else if(tiles[x][y] == 5) {
	        			Tile.getTiles().add(Tile.doorTile);
	        		}else if(tiles[x][y] == 6) {
	        			Tile.getTiles().add(Tile.warpTile);
	        		}else if(tiles[x][y] == 7) {
	        			Tile.getTiles().add(Tile.hellRockTile);
	        		}else if(tiles[x][y] == 8) {
	        			Tile.getTiles().add(Tile.hellStoneTile);
	        		}else if(tiles[x][y] == 9) {
	        			Tile.getTiles().add(Tile.hellGrassTile);
	        		}else if(tiles[x][y] == 11) {
	        			Tile.getTiles().add(Tile.icyRockTile);
	        		}else if(tiles[x][y] == 12) {
	        			Tile.getTiles().add(Tile.icyStoneTile);
	        		}else if(tiles[x][y] == 13) {
	        			Tile.getTiles().add(Tile.icyGrassTile);
	        		}else if(tiles[x][y] == 14) {
	        			Tile.getTiles().add(Tile.doorTile2);
	        		}else if(tiles[x][y] == 15) {
	        			Tile.getTiles().add(Tile.sandTile);
	        		}else if(tiles[x][y] == 16) {
	        			Tile.getTiles().add(Tile.sandStoneTile);
	        		}else if(tiles[x][y] == 17) {
	        			Tile.getTiles().add(Tile.sandStoneWallTile);
	        		}else if(tiles[x][y] == 18) {
	        			Tile.getTiles().add(Tile.sandyDoorTile);
	        		}else if(tiles[x][y] == 19) {
	        			Tile.getTiles().add(Tile.sandyDoorTile2);
	        		}else if(tiles[x][y] == 20) {
	        			Tile.getTiles().add(Tile.warpDownTile);
	        		}
	        	}
	        }
        }else if(loaded) {
        	String save = "save" + loadedSave;
//        	if(currentWorld == 2 && furthestWorldVisited == 1) {
//        		loaded = false;
//        	}
        	if(currentWorld < 1) {
        		currentWorld = 1;
        	}
        	String path2 = "res/saves/" + save + "/world" + currentWorld + "/worldSave.txt";
        	String file3 = Utils.loadFileAsString(path2);
            String tokens3[] = file3.split("\\s+");
            String token4[];
            for(int y = 0; y < height; y++) {
	        	for(int x = 0; x < width; x++) {
	            	token4 = tokens3[x + y * width].split("\\ ");
	                tiles[x][y] = Utils.parseInt(token4[0]);
	        		if(tiles[x][y] == 0) {
	        			Tile.getTiles().add(Tile.grassTile);
	        		}else if(tiles[x][y] == 1) {
	        			Tile.getTiles().add(Tile.dirtTile);
	        		}else if(tiles[x][y] == 2) {
	        			Tile.getTiles().add(Tile.stoneTile);
	        		}else if(tiles[x][y] == 3) {
	        			Tile.getTiles().add(Tile.rockTile);
	        		}else if(tiles[x][y] == 4) {
	        			Tile.getTiles().add(Tile.waterTile);
	        		}else if(tiles[x][y] == 5) {
	        			Tile.getTiles().add(Tile.doorTile);
	        		}else if(tiles[x][y] == 6) {
	        			Tile.getTiles().add(Tile.warpTile);
	        		}else if(tiles[x][y] == 7) {
	        			Tile.getTiles().add(Tile.hellRockTile);
	        		}else if(tiles[x][y] == 8) {
	        			Tile.getTiles().add(Tile.hellStoneTile);
	        		}else if(tiles[x][y] == 9) {
	        			Tile.getTiles().add(Tile.hellGrassTile);
	        		}else if(tiles[x][y] == 11) {
	        			Tile.getTiles().add(Tile.icyRockTile);
	        		}else if(tiles[x][y] == 12) {
	        			Tile.getTiles().add(Tile.icyStoneTile);
	        		}else if(tiles[x][y] == 13) {
	        			Tile.getTiles().add(Tile.icyGrassTile);
	        		}else if(tiles[x][y] == 14) {
	        			Tile.getTiles().add(Tile.doorTile2);
	        		}else if(tiles[x][y] == 15) {
	        			Tile.getTiles().add(Tile.sandTile);
	        		}else if(tiles[x][y] == 16) {
	        			Tile.getTiles().add(Tile.sandStoneTile);
	        		}else if(tiles[x][y] == 17) {
	        			Tile.getTiles().add(Tile.sandStoneWallTile);
	        		}else if(tiles[x][y] == 18) {
	        			Tile.getTiles().add(Tile.sandyDoorTile);
	        		}else if(tiles[x][y] == 19) {
	        			Tile.getTiles().add(Tile.sandyDoorTile2);
	        		}else if(tiles[x][y] == 20) {
	        			Tile.getTiles().add(Tile.warpDownTile);
	        		}
	        	}
	        }
        }

    }
    
    public WorldGenerator getGenerator() {
		return generator;
	}

	public void unloadWorld() {
    	width = 0;
    	height = 0;
    	spawnX = 0;
    	spawnY = 0;
    	tiles = null;
    	aboutToChange = false;
    	getEntityManager().clearEntities();
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public boolean isSetup() {
		return setup;
	}

	public void setSetup(boolean setup) {
		this.setup = setup;
	}

	public static void setCount(int Count)
    {
        count = Count;
    }

    public static int getCount()
    {
        return count;
    }

    public int getTotalEntities1() {
		return totalEntities1;
	}

	public static void setTotalEntities1(int totalEntities1) {
		World.totalEntities1 = totalEntities1;
	}

	public static int getTotalEntities2() {
		return totalEntities2;
	}

	public static void setTotalEntities2(int totalEntities2) {
		World.totalEntities2 = totalEntities2;
	}
	
	public int[][] getTiles(){
		return tiles;
	}

	public int getCurrentWorld() {
		return currentWorld;
	}

	public void setCurrentWorld(int currentWorl) {
		currentWorld = currentWorl;
	}

	public boolean isLoaded()
    {
        return loaded;
    }

    public static void setLoaded(boolean loade)
    {
        loaded = loade;
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public static int getWorldNum()
    {
        return worldNum;
    }

    public static void setWorldNum(int num)
    {
        worldNum = num;
    }

    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handle)
    {
        handler = handle;
    }

    public ItemManager getItemManager()
    {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager)
    {
        this.itemManager = itemManager;
    }

    

}