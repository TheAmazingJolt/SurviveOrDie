package server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import entities.Entity;
import main.Handler;
import utils.Timer;
import utils.Utils;

public class Server {

	private static Socket socket = null;
	private ServerSocket server = null;
	private static DataInputStream in = null;
	private static boolean active = false;
	private static Handler handler;
	
	private static ArrayList<Entity> entities = new ArrayList<Entity>();
	private static ArrayList<Entity> toRemove = new ArrayList<Entity>();
	
	private static Timer timer;
	
	public Server(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("Server Started");
			
			System.out.println("Waiting For A Connection");
			
			socket = server.accept();
			System.out.println("Client Accepted");
			
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		}catch(IOException i) {
			i.printStackTrace();
		}
		
		active = true;
		
	}
	
	public static void tick() {
		if(active) {
//			try {
//				handler.getWorld().getEntityManager().getPlayer2().setX(in.readFloat());
//				handler.getWorld().getEntityManager().getPlayer2().setY(in.readFloat());
//				handler.getWorld().getEntityManager().getPlayer2().setHealth(in.readInt());
//				handler.getWorld().getEntityManager().getPlayer().setHealth(in.readInt());
//				handler.getWorld().getEntityManager().getPlayer2().setxMove(in.readFloat());
//				handler.getWorld().getEntityManager().getPlayer2().setyMove(in.readFloat());
//				entities = setEntityList();
//				int size = in.read();
//				for(int i = 0; i < size; i++) {
//					Entity e = entities.get(i);
//		           	if(e.getId() < 0)
//		           		continue;
//					int activity = in.read();
//					if(activity == 1) {
//						e.suicide();
//					}
//				}
//				return;
//			}catch(IOException i) {
//				i.printStackTrace();
//				return;
//			}
		}
		
		active = false;
		
		try {
			socket.close();
			in.close();
		}catch(IOException i) {
			i.printStackTrace();
		}
		
		System.out.println("Closing Connection");
	}
	
	public static boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static void start(int port, Handler handle) {
		handler = handle;
		Server server = new Server(port);
	}
	
	public static ArrayList<Entity> setEntityList() {
		ArrayList<Entity> list = new ArrayList<Entity>();
		if(handler.getWorld().getCurrentWorld() == 1) {
    		list.addAll(handler.getWorld().getEntityManager().getEntities());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow1());
        	list.addAll(handler.getWorld().getEntityManager().getE1overflow2());
        	for(Entity e : list) {
        		if(e.getId() < 0) {
        			toRemove.add(e);
        		}
        	}
        	list.removeAll(toRemove);
        	toRemove.clear();
    	}
		return list;
	}
	
}
