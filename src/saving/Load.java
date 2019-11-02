package saving;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entities.Entity;
import entities.creatures.Player;
import entities.creatures.Zombie;
import entities.creatures.npcs.NPC;
import entities.statics.Flint;
import entities.statics.Iron;
import entities.statics.Stone;
import entities.statics.Tree;
import inventory.Inventory;
import items.Item;
import main.Handler;
import states.LoadState;
import utils.Utils;
import worlds.World;

public class Load
{
	
    static String line = null;
    static int lineInt;
    static int lineNum;
    static int amt;
    
    public static void loadSaveData(String worldName, Handler handler) {
    	String fileName = (new StringBuilder("res/saves/")).append(worldName).append("/saveData.txt").toString();
    	lineNum = 0;
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
            {
            	lineNum++;
            	if(lineNum == 1) {
            		handler.getGame().setCreationDate(line);
            		handler.getGame().setCreationDateSet(true);
            	}else if(lineNum == 2) {
            		handler.getGame().setModifiedDate(line);
            	}else if(lineNum == 3) {
            		handler.getGame().setGameType(line);
            	}
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(fileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(fileName).append("'").toString());
        }
    }
    
    public static void loadSettings( Handler handler) {
    	String fileName = "res/saves/settings.txt";
    	try {
    		FileReader fileReader = new FileReader(fileName);
    		BufferedReader bufferedReader = new BufferedReader(fileReader);
    		while((line = bufferedReader.readLine()) != null) {
    			lineNum++;
    			if(lineNum == 1)
    				handler.getAudioManager().setAmbientVolume(Float.parseFloat(line));
    			else if(lineNum == 2)
    				handler.getAudioManager().setEffectVolume(Float.parseFloat(line));
    		}
    		bufferedReader.close();
    	}catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(fileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(fileName).append("'").toString());
        }
    }
    
    public static void loadOtherWorldData(String worldName, Handler handler) {
    	String itemFileName = (new StringBuilder("res/saves/")).append(worldName).append("/worldNumSave.txt").toString();
    	lineNum = 0;
        try
        {
            FileReader fileReader = new FileReader(itemFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
            {
            	lineNum++;
            	if(lineNum == 1) {
            		LoadState.setWorldToLoad(Utils.parseInt(line));
            	}
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(itemFileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(itemFileName).append("'").toString());
        }
    }
    
    public static void loadItemData(String worldName)
    {
    	Item item = null;
        String itemFileName = (new StringBuilder("res/saves/")).append(worldName).append("/itemSave.txt").toString();
        Item.addItemsToList();
        try
        {
            FileReader fileReader = new FileReader(itemFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
            {
                lineNum++;
                if(lineNum % 2 == 0)
                {
                    lineInt = Utils.parseInt(line);
                    for(Item i:Item.getItems())
                    {
                        if(i.getId() == lineInt) {
                            item = i;
                        }
                    }

                } else
                if(lineNum % 2 != 0)
                {
                    lineInt = Utils.parseInt(line);
                    amt = lineInt;
                }
                Inventory.loadItem(item, amt);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(itemFileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(itemFileName).append("'").toString());
        }
    }

    public static void loadEntityData(Handler handler, String worldName)
    {
        String entityFileName = (new StringBuilder("res/saves/")).append(worldName).append("/entitySave.txt").toString();
        ArrayList<Entity> list = new ArrayList<Entity>();
        if(handler.getWorld().getCurrentWorld() == 1) {
        	list.addAll(handler.getWorld().getEntityManager().getEntities());
    		handler.getWorld().getEntityManager().getE1overflow1().remove(handler.getWorld().getEntityManager().getPlayer());
    		list.addAll(handler.getWorld().getEntityManager().getE1overflow1());
    		handler.getWorld().getEntityManager().getE1overflow2().remove(handler.getWorld().getEntityManager().getPlayer());
    		list.addAll(handler.getWorld().getEntityManager().getE1overflow2());
        }else if(handler.getWorld().getCurrentWorld() == 2) {
        	list.addAll(handler.getWorld().getEntityManager().getEntities2());
    		handler.getWorld().getEntityManager().getE2overflow1().remove(handler.getWorld().getEntityManager().getPlayer());
    		list.addAll(handler.getWorld().getEntityManager().getE2overflow1());
    		handler.getWorld().getEntityManager().getE2overflow2().remove(handler.getWorld().getEntityManager().getPlayer());
    		list.addAll(handler.getWorld().getEntityManager().getE2overflow2());
        }else if(handler.getWorld().getCurrentWorld() == 3) {
        	list.addAll(handler.getWorld().getEntityManager().getEntities3());
    		handler.getWorld().getEntityManager().getE3overflow1().remove(handler.getWorld().getEntityManager().getPlayer());
    		list.addAll(handler.getWorld().getEntityManager().getE3overflow1());
    		handler.getWorld().getEntityManager().getE3overflow2().remove(handler.getWorld().getEntityManager().getPlayer());
    		list.addAll(handler.getWorld().getEntityManager().getE3overflow2());
        }
    	
        int lineNum = 0;
        try
        {
            FileReader fileReader = new FileReader(entityFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
            {
                lineNum++;
                for(int i = 1;i<list.size();i++) {
                	for(Entity e : list) {
                		if(e == handler.getWorld().getEntityManager().getPlayer())
                			continue;
                		if(e.getId() == lineNum) {
                			if(Utils.parseInt(line) == 0) {
                				e.setActive(true);
                				break;
                			}else if(Utils.parseInt(line) == 1) {
                				e.setActive(false);
                				break;
                			}
                		}else {
                			continue;
                		}
                	}
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(entityFileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(entityFileName).append("'").toString());
        }
    }
    
    public static ArrayList<Entity> loadGeneratedEntityData(Handler handler, String worldName)
    {
        String entityFileName = (new StringBuilder("res/saves/")).append(worldName).append("/entitySave.txt").toString();
        ArrayList<Entity> list = new ArrayList<Entity>();
        int lineNum = 0;
        int size = 0;
        int entityNum = 0;
        String tokens[];
        try
        {
            FileReader fileReader = new FileReader(entityFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
            {
                lineNum++;
                if(lineNum == 1) {
                	size = Utils.parseInt(line);
                }
                if(lineNum > 1 && list.size() < size) {
                	entityNum++;
                	tokens = line.split("\\s+");
                	if(tokens[0].contains("Zombie")) {
                		list.add(new Zombie(handler, Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), entityNum, handler.getWorld().getEntityManager().getPlayer()));
                	}else if(tokens[0].contains("Tree")) {
                		list.add(new Tree(handler, Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), entityNum));
                	}else if(tokens[0].contains("Stone")) {
                		list.add(new Stone(handler, Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), entityNum));
                	}else if(tokens[0].contains("Flint")) {
                		list.add(new Flint(handler, Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), entityNum));
                	}else if(tokens[0].contains("Iron")) {
                		list.add(new Iron(handler, Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), entityNum));
                	}
                }
            }
            bufferedReader.close();
            return list;
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(entityFileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(entityFileName).append("'").toString());
        }
        return list;
    }

    public static boolean loadOtherData()
    {
    	boolean cantLoad = true;
        String fileName = "res/saves/basicSave.txt";
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
                World.setCount(Utils.parseInt(line));
            cantLoad = false;
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(fileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(fileName).append("'").toString());
        }
        return cantLoad;
    }

    public static void loadPlayerData(Player player, String worldName)
    {
        String entityFileName = (new StringBuilder("res/saves/")).append(worldName).append("/playerSave.txt").toString();
        int lineNum = 0;
        try
        {
            FileReader fileReader = new FileReader(entityFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
                if(++lineNum == 1)
                {
                    player.setHealth(Utils.parseInt(line));
                }else if(lineNum == 2) {
                	player.setKilledEnemies(-(player.getKilledEnemies()));
                    player.setKilledEnemies(Utils.parseInt(line));
                }else if(lineNum == 3) {
                    player.setKilledBosses(Utils.parseInt(line));
                }else if(lineNum == 4) {
                	player.setMaxHealth(Utils.parseInt(line));
                }else if(lineNum == 5) {
                	if(line.contains("0"))
                		player.getInventory().setLargeUnlocked(false);
                	else if(line.contains("1"))
                		player.getInventory().setLargeUnlocked(true);
                }else if(lineNum == 6) {
                	player.setX(Utils.parseInt(line));
                }else if(lineNum == 7) {
                	player.setY(Utils.parseInt(line));
                }else if(lineNum == 8) {
                	player.setSpeed(Utils.parseInt(line));
                }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(entityFileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(entityFileName).append("'").toString());
        }
    }
    
    public static void loadNPCData(Player player, String worldName, Handler handler)
    {
        String entityFileName = (new StringBuilder("res/saves/")).append(worldName).append("/npcSave.txt").toString();
        int lineNum = 0;
        try
        {
            FileReader fileReader = new FileReader(entityFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
                if(++lineNum == 1) {
                    player.setCurrentDialougueF(Integer.parseInt(line));
                }else if(lineNum == 2) {
                	player.setCurrentDialougueS(Integer.parseInt(line));
                }else if(lineNum == 3) {
                	player.setCurrentDialougueC(Integer.parseInt(line));
                }else if(lineNum == 4) {
                	if(Integer.parseInt(line) == 2) {
                		for(NPC n : handler.getWorld().getEntityManager().getNpcs()) {
                			if(n.getId() == -3) {
                				handler.getWorld().getEntityManager().addNPC2Main(n);
                				n.setX(player.getStartX());
                				n.setY(player.getStartY());
                			}
                		}
                	}
                }else if(lineNum == 5) {
                	if(Integer.parseInt(line) == 1) {
                		for(NPC n : handler.getWorld().getEntityManager().getNpcs2()) {
                			n.setActive(false);
                		}
                	}
                }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println((new StringBuilder("Unable to open file '")).append(entityFileName).append("'").toString());
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error reading file '")).append(entityFileName).append("'").toString());
        }
    }

}
