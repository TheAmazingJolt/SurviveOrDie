package saving;

import entities.Entity;
import entities.creatures.Player;
import entities.creatures.npcs.NPC;
import items.Item;
import java.io.*;
import java.util.ArrayList;
import main.Handler;

public class Save
{

    static String worldName;
    static boolean created = false;
    static boolean npcIn2 = false;
    private static Handler handler;

    public static void createDirectory()
    {
        if(created)
            return;
        if(!created)
        {
            File directory = new File("res/saves/save"+handler.getWorld().getLoadedSave());
            created = directory.mkdir();
        }
    }

    public static void saveSettings(Handler handle) {
    	handler = handle;
    	createDirectory();
    	String fileName = "res/saves/settings.txt";
    	try {
    		FileWriter fileWriter = new FileWriter(fileName);
    		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    		
    		bufferedWriter.write(Float.toString(handler.getAudioManager().getAmbientVolume()));
    		bufferedWriter.newLine();
    		bufferedWriter.write(Float.toString(handler.getAudioManager().getEffectVolume()));
    		bufferedWriter.close();
    	}catch(IOException ex) {
    		createDirectory();
    		saveSettings(handler);
    		System.out.println("Error writing to file '" + fileName +"'");
    	}
    }
    
    public static void saveWorldData(Handler handle, String name) {
    	handler = handle;
        createDirectory();
        String itemFileName = (new StringBuilder("res/saves/")).append(name).append("/worldSave.txt").toString();
        try
        {
            FileWriter fileWriter = new FileWriter(itemFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            bufferedWriter.write(Integer.toString(handler.getWorld().getCurrentWorld()));

            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            createDirectory();
            saveWorldData(handler, name);
            System.out.println((new StringBuilder("Error writing to file '")).append(itemFileName).append("'").toString());
        }
    }
    
    public static void saveItemData(Handler handle, ArrayList<Item> list, String name)
    {
        handler = handle;
        createDirectory();
        String itemFileName = (new StringBuilder("res/saves/")).append(name).append("/itemSave.txt").toString();
        try
        {
            FileWriter fileWriter = new FileWriter(itemFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Item i : list)
            {
                bufferedWriter.write(Integer.toString(i.getId()));
                bufferedWriter.newLine();
                bufferedWriter.write(Integer.toString(i.getCount()));
                bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            createDirectory();
            saveItemData(handler, handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems(), name);
            System.out.println((new StringBuilder("Error writing to file '")).append(itemFileName).append("'").toString());
        }
    }

    public static void saveEntityData(Handler handle, String name)
    {
    	handler = handle;
    	ArrayList<Entity> list = new ArrayList<Entity>();
    	list.clear();
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
    	
        createDirectory();
        String entityFileName = (new StringBuilder("res/saves/")).append(name).append("/entitySave.txt").toString();
        try
        {
            FileWriter fileWriter = new FileWriter(entityFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            for(int i = 1;i<list.size();i++) {
            	for(Entity e : list) {
            		if(e == handler.getWorld().getEntityManager().getPlayer())
            			continue;
            		if(e.getId() == i) {
            			if(e.isActive()) {
                			bufferedWriter.write(Integer.toString(0));
                			bufferedWriter.newLine();
                			break;
                		}else if(!e.isActive()) {
                			bufferedWriter.write(Integer.toString(1));
                			bufferedWriter.newLine();
                			break;
                		}
            		}else {
            			continue;
            		}
            	}
            }
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            createDirectory();
            saveEntityData(handler, name);
            System.out.println((new StringBuilder("Error writing to file '")).append(entityFileName).append("'").toString());
        }
    }

    public static void saveOtherData(int worldCount)
    {
        String fileName = "res/saves/basicSave.txt";
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(worldCount));
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
        }
    }

    public static void saveNPCData(Player player, String name) {
    	createDirectory();
        String entityFileName = (new StringBuilder("res/saves/")).append(name).append("/npcSave.txt").toString();
        try
        {
            FileWriter fileWriter = new FileWriter(entityFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(player.getCurrentDialougueF()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(player.getCurrentDialougueS()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(player.getCurrentDialougueC()));
            bufferedWriter.newLine();
            NPC n1 = null;
            for(NPC n : handler.getWorld().getEntityManager().getNpcs()) {
            	if(n.getId() == -3) {
            		n1 = n;
            	}
            }
            for(NPC n : handler.getWorld().getEntityManager().getNpcs2()) {
            	if(n == n1) {
            		npcIn2 = true;
            		bufferedWriter.write("2");
            		bufferedWriter.newLine();
            	}else {
            		continue;
            	}
            }
            if(!npcIn2) {
            	bufferedWriter.write("1");
            	bufferedWriter.newLine();
            }
            for(NPC n : handler.getWorld().getEntityManager().getNpcs2()) {
            	if(n.getId() == -4) {
            		if(n.isActive())
            			bufferedWriter.write("0");
            		else if(!n.isActive())
            			bufferedWriter.write("1");
            		bufferedWriter.close();
            	}else {
            		continue;
            	}
            }
        }
        catch(IOException ex)
        {
            createDirectory();
            savePlayerData(handler.getWorld().getEntityManager().getPlayer(), name);
            System.out.println((new StringBuilder("Error writing to file '")).append(entityFileName).append("'").toString());
        }
    }
    
    public static void savePlayerData(Player player, String name)
    {
        createDirectory();
        String entityFileName = (new StringBuilder("res/saves/")).append(name).append("/playerSave.txt").toString();
        try
        {
            FileWriter fileWriter = new FileWriter(entityFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(player.getHealth()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(player.getKilledEnemies()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(player.getKilledBosses()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(player.getMaxHealth()));
            bufferedWriter.newLine();
            if(handler.getWorld().getEntityManager().getPlayer().getInventory().isLargeUnlocked()) {
            	bufferedWriter.write(Integer.toString(1));
            }else if(!handler.getWorld().getEntityManager().getPlayer().getInventory().isLargeUnlocked()) {
            	bufferedWriter.write(Integer.toString(0));
            }
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString((int) player.getX()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString((int) player.getY()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString((int) player.getSpeed()));
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            createDirectory();
            savePlayerData(handler.getWorld().getEntityManager().getPlayer(), name);
            System.out.println((new StringBuilder("Error writing to file '")).append(entityFileName).append("'").toString());
        }
    }

    public static void setWorldName(String name)
    {
        worldName = name;
    }



}
