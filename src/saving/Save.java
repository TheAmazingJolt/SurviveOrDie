package saving;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entities.Entity;
import entities.creatures.Player;
import entities.creatures.npcs.NPC;
import items.Item;
import main.Handler;

public class Save
{

    static String worldName;
    static boolean created = false;
    static boolean subCreated1 = false;
    static boolean subCreated2 = false;
    static boolean subCreated3 = false;
    static boolean subCreated4 = false;
    static boolean npcIn2 = false;
    private static Handler handler;
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private static Date date = new Date();  

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
    
    public static void createSubDirectory1()
    {
        if(subCreated1)
            return;
        if(!subCreated1)
        {
            File directory = new File("res/saves/save"+handler.getWorld().getLoadedSave()+"/world1");
            subCreated1 = directory.mkdir();
        }
    }
    
    public static void createSubDirectory2()
    {
        if(subCreated2)
            return;
        if(!subCreated2)
        {
            File directory = new File("res/saves/save"+handler.getWorld().getLoadedSave()+"/world2");
            subCreated1 = directory.mkdir();
        }
    }
    
    public static void createSubDirectory3()
    {
        if(subCreated3)
            return;
        if(!subCreated3)
        {
            File directory = new File("res/saves/save"+handler.getWorld().getLoadedSave()+"/world3");
            subCreated3 = directory.mkdir();
        }
    }
    
    public static void createSubDirectory4()
    {
        if(subCreated4)
            return;
        if(!subCreated4)
        {
            File directory = new File("res/saves/save"+handler.getWorld().getLoadedSave()+"/world4");
            subCreated4 = directory.mkdir();
        }
    }

    
    
    public static void saveSaveData(Handler handle, String name) {
    	handler = handle;
    	createDirectory();
    	String fileName = (new StringBuilder("res/saves/")).append(name).append("/saveData.txt").toString();
    	try {
    		FileWriter fileWriter = new FileWriter(fileName);
    		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    		
    		if(handler.getGame().creationDateSet)
    			bufferedWriter.write(handler.getGame().creationDate);//date created
    		else if(!handler.getGame().creationDateSet)
    			bufferedWriter.write(dateFormat.format(date));
    		bufferedWriter.newLine();
    		bufferedWriter.write(dateFormat.format(date)); //date modified
    		bufferedWriter.newLine();
    		bufferedWriter.write(handler.getGame().getGameType());
    		bufferedWriter.newLine();
    		bufferedWriter.write(Integer.toString(handler.getWorld().getFurthestWorldVisited()));
    		bufferedWriter.close();
    	}catch(IOException ex) {
    		createDirectory();
    		saveSettings(handler);
    		System.out.println("Error writing to file '" + fileName +"'");
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
    
    public static void saveOtherWorldData(Handler handle, String name) {
    	handler = handle;
        createDirectory();
        String fileName = (new StringBuilder("res/saves/")).append(name).append("/worldNumSave.txt").toString();
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            bufferedWriter.write(Integer.toString(handler.getWorld().getCurrentWorld()));
            
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            createDirectory();
            saveOtherWorldData(handler, name);
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
        }
    }
    
    public static void saveWorldData(Handler handle, String name) {
    	handler = handle;
        createDirectory();
    	createSubDirectory1();
    	createSubDirectory2();
    	createSubDirectory3();
    	createSubDirectory4();
    	String fileName = "res/saves/" + name + "/world" + handler.getWorld().getCurrentWorld() + "/worldSave.txt";
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            int[][] tiles = handler.getWorld().getTiles();
            
            for(int y = 0; y < tiles.length; y++) {
            	for(int x = 0; x < tiles.length; x++) {
            		bufferedWriter.write(tiles[x][y] + " ");
            	}
            	bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            createDirectory();
        	createSubDirectory1();
        	createSubDirectory2();
        	createSubDirectory3();
        	createSubDirectory4();
            saveWorldData(handler, name);
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
        }
    }
    
    public static void saveItemData(Handler handle, String name)
    {
    	handler = handle;
        createDirectory();
    	String fileName = "res/saves/" + name + "/itemSave.txt";
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            ArrayList<Item> items = handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems();
            
            for(Item i : items) {
            	bufferedWriter.write(i.getName() + " " + i.getCount());
            	bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            createDirectory();
            saveItemData(handler, name);
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
        }
    }

    public static void saveGeneratedEntityData(Handler handle, String name) {
    	handler = handle;
    	ArrayList<Entity> list = new ArrayList<Entity>();
    	list.clear();
    	if(handler.getWorld().getCurrentWorld() == 1) {
    		list.addAll(handler.getWorld().getEntityManager().getEntities());
        	handler.getWorld().getEntityManager().getE1overflow1().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow1());
        	handler.getWorld().getEntityManager().getE1overflow2().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow2());
        	handler.getWorld().getEntityManager().getE1overflow3().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow3());
        	handler.getWorld().getEntityManager().getE1overflow4().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow4());
        	handler.getWorld().getEntityManager().getE1overflow5().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow5());
        	handler.getWorld().getEntityManager().getE1overflow6().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow6());
        	handler.getWorld().getEntityManager().getE1overflow7().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow7());
        	handler.getWorld().getEntityManager().getE1overflow8().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow8());
        	handler.getWorld().getEntityManager().getE1overflow9().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow9());
        	handler.getWorld().getEntityManager().getE1overflow10().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow10());
        	handler.getWorld().getEntityManager().getE1overflow11().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow11());
        	handler.getWorld().getEntityManager().getE1overflow12().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow12());
        	handler.getWorld().getEntityManager().getE1overflow13().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow13());
        	handler.getWorld().getEntityManager().getE1overflow14().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow14());
        	handler.getWorld().getEntityManager().getE1overflow15().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow15());
        	handler.getWorld().getEntityManager().getE1overflow16().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow16());
    	}else if(handler.getWorld().getCurrentWorld() == 2) {
    		list.addAll(handler.getWorld().getEntityManager().getEntities2());
    		handler.getWorld().getEntityManager().getE2overflow1().remove(handler.getWorld().getEntityManager().getPlayer());
    		list.addAll(handler.getWorld().getEntityManager().getE2overflow1());
    		handler.getWorld().getEntityManager().getE2overflow2().remove(handler.getWorld().getEntityManager().getPlayer());
    		list.addAll(handler.getWorld().getEntityManager().getE2overflow2());
    		handler.getWorld().getEntityManager().getE2overflow3().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow3());
        	handler.getWorld().getEntityManager().getE2overflow4().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow4());
        	handler.getWorld().getEntityManager().getE2overflow5().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow5());
        	handler.getWorld().getEntityManager().getE2overflow6().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow6());
        	handler.getWorld().getEntityManager().getE2overflow7().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow7());
        	handler.getWorld().getEntityManager().getE2overflow8().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow8());
        	handler.getWorld().getEntityManager().getE2overflow9().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow9());
        	handler.getWorld().getEntityManager().getE2overflow10().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow10());
        	handler.getWorld().getEntityManager().getE2overflow11().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow11());
        	handler.getWorld().getEntityManager().getE2overflow12().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow12());
        	handler.getWorld().getEntityManager().getE2overflow13().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow13());
        	handler.getWorld().getEntityManager().getE2overflow14().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow14());
        	handler.getWorld().getEntityManager().getE2overflow15().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow15());
        	handler.getWorld().getEntityManager().getE2overflow16().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE2overflow16());
    	}else if(handler.getWorld().getCurrentWorld() == 3) {
    		list.addAll(handler.getWorld().getEntityManager().getEntities3());
        	handler.getWorld().getEntityManager().getE3overflow1().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow1());
        	handler.getWorld().getEntityManager().getE3overflow2().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow2());
        	handler.getWorld().getEntityManager().getE3overflow3().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow3());
        	handler.getWorld().getEntityManager().getE3overflow4().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow4());
        	handler.getWorld().getEntityManager().getE3overflow5().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow5());
        	handler.getWorld().getEntityManager().getE3overflow6().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow6());
        	handler.getWorld().getEntityManager().getE3overflow7().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow7());
        	handler.getWorld().getEntityManager().getE3overflow8().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow8());
        	handler.getWorld().getEntityManager().getE3overflow9().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow9());
        	handler.getWorld().getEntityManager().getE3overflow10().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow10());
        	handler.getWorld().getEntityManager().getE3overflow11().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow11());
        	handler.getWorld().getEntityManager().getE3overflow12().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow12());
        	handler.getWorld().getEntityManager().getE3overflow13().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow13());
        	handler.getWorld().getEntityManager().getE3overflow14().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow14());
        	handler.getWorld().getEntityManager().getE3overflow15().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow15());
        	handler.getWorld().getEntityManager().getE3overflow16().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE3overflow16());
    	}else if(handler.getWorld().getCurrentWorld() == 4) {
    		list.addAll(handler.getWorld().getEntityManager().getEntities4());
        	handler.getWorld().getEntityManager().getE4overflow1().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow1());
        	handler.getWorld().getEntityManager().getE4overflow2().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow2());
        	handler.getWorld().getEntityManager().getE4overflow3().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow3());
        	handler.getWorld().getEntityManager().getE4overflow4().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow4());
        	handler.getWorld().getEntityManager().getE4overflow5().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow5());
        	handler.getWorld().getEntityManager().getE4overflow6().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow6());
        	handler.getWorld().getEntityManager().getE4overflow7().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow7());
        	handler.getWorld().getEntityManager().getE4overflow8().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow8());
        	handler.getWorld().getEntityManager().getE4overflow9().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow9());
        	handler.getWorld().getEntityManager().getE4overflow10().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow10());
        	handler.getWorld().getEntityManager().getE4overflow11().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow11());
        	handler.getWorld().getEntityManager().getE4overflow12().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow12());
        	handler.getWorld().getEntityManager().getE4overflow13().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow13());
        	handler.getWorld().getEntityManager().getE4overflow14().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow14());
        	handler.getWorld().getEntityManager().getE4overflow15().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow15());
        	handler.getWorld().getEntityManager().getE4overflow16().remove(handler.getWorld().getEntityManager().getPlayer());
        	list.addAll(handler.getWorld().getEntityManager().getE4overflow16());
    	}
    	createDirectory();
    	createSubDirectory1();
    	createSubDirectory2();
    	createSubDirectory3();
    	createSubDirectory4();
    	String fileName = "res/saves/" + name + "/world" + handler.getWorld().getCurrentWorld() + "/entitySave.txt";
//    	System.out.println(fileName);
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            bufferedWriter.write(Integer.toString(list.size()));
            bufferedWriter.newLine();
            
            for(Entity e : list) {
            	bufferedWriter.write(e.getName() + " " + Float.toString(e.getX()) + " " + Float.toString(e.getY()) + " " + e.isActive());
            	bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            createDirectory();
        	createSubDirectory1();
        	createSubDirectory2();
        	createSubDirectory3();
        	createSubDirectory4();
            saveGeneratedEntityData(handler, name);
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
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
    	createSubDirectory1();
    	createSubDirectory2();
    	createSubDirectory3();
    	createSubDirectory4();
    	String fileName = "res/saves/" + name + "/world" + handler.getWorld().getCurrentWorld() + "/entitySave.txt";
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
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
        	createSubDirectory1();
        	createSubDirectory2();
        	createSubDirectory3();
        	createSubDirectory4();
            saveEntityData(handler, name);
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
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
        String fileName = (new StringBuilder("res/saves/")).append(name).append("/npcSave.txt").toString();
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
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
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
        }
    }
    
    public static void savePlayerData(Player player, String name)
    {
        createDirectory();
        String fileName = (new StringBuilder("res/saves/")).append(name).append("/playerSave.txt").toString();
        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
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
            System.out.println((new StringBuilder("Error writing to file '")).append(fileName).append("'").toString());
        }
    }

    public static void setWorldName(String name)
    {
        worldName = name;
    }



}
