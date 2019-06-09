package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.ArrayList;

import entities.Entity;
import main.Handler;
import utils.Timer;

public class Client {
	
	private static Socket socket = null;
	private static DataOutputStream out = null;
	private static boolean active = false;
	private static int intToSend;
	private static String strToSend;
	private static Handler handler;
	private static Timer timer;
	
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	private static ArrayList<Entity> toremove = new ArrayList<Entity>();
	
	public Client(String address, int port) {
		try {
			socket = new Socket(address, port);
			System.out.println("Connected");
			
			out = new DataOutputStream(socket.getOutputStream());
		}catch(UnknownHostException u){
			u.printStackTrace();
		}catch(IOException i) {
			i.printStackTrace();
		}
		
		active = true;
		
	}
	
	public static void tick() {
		if(active) {
			try{
				out.writeFloat(handler.getWorld().getEntityManager().getPlayer().getX());
				out.writeFloat(handler.getWorld().getEntityManager().getPlayer().getY());
				out.writeInt(handler.getWorld().getEntityManager().getPlayer().getHealth());
				out.writeInt(handler.getWorld().getEntityManager().getPlayer2().getHealth());
				out.writeFloat(handler.getWorld().getEntityManager().getPlayer().getxMove());
				out.writeFloat(handler.getWorld().getEntityManager().getPlayer().getyMove());
				entities = setEntityList();
				out.writeInt(entities.size());
				for(int i = 1;i<entities.size();i++) {
			       	for(Entity e : entities) {
			           	if(e.getId() < 0)
			           		continue;
			           	if(e.getId() == i) {
			           		if(e.isActive()) {
			           			out.write(i - 1);
			           			out.write(0);
			               	}else if(!e.isActive()) {
			           			out.write(i - 1);
			               		out.write(1);
			               	}
			           	}else {
			           		continue;
			           	}
			       	}
			    }
				return;
			}catch(IOException i) {
				i.printStackTrace();
				return;
			}
		}
		
		try {
			out.close();
			socket.close();
		}catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	public static void start(int port, Handler handle, String ip) {
		handler = handle;
		Client client = new Client(ip, port);
	}

	public static boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static int getIntToSend() {
		return intToSend;
	}

	public static void setIntToSend(int intToSend) {
		Client.intToSend = intToSend;
	}

	public static String getStrToSend() {
		return strToSend;
	}

	public static void setStrToSend(String strToSend) {
		Client.strToSend = strToSend;
	}
	
	public static ArrayList<Entity> setEntityList() {
		ArrayList<Entity> list = new ArrayList<Entity>();
		if(handler.getWorld().getCurrentWorld() == 1) {
    		list.addAll(handler.getWorld().getEntityManager().getEntities());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow1());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow2());
        	for(Entity e : list) {
        		if(e.getId() < 0)
        			toremove.add(e);
        	}
        	list.removeAll(toremove);
        	toremove.clear();
    	}
		return list;
	}
	
}
