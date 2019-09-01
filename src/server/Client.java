package server;

import java.awt.event.KeyEvent;
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
				//keyboard keys
				if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
					out.writeUTF("W");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
					out.writeUTF("S");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)) {
					out.writeUTF("A");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)) {
					out.writeUTF("D");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1)) {
					out.writeUTF("1");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2)) {
					out.writeUTF("2");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3)) {
					out.writeUTF("3");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4)) {
					out.writeUTF("4");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_5)) {
					out.writeUTF("5");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SHIFT)) {
					out.writeUTF("shift");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
					out.writeUTF("e");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
					out.writeUTF("escape");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)) {
					out.writeUTF("right");
				}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)) {
					out.writeUTF("left");
				}
				
				//mouse
				if(handler.getMouseManager().isLeftPressed()) {
					out.writeUTF("left");
				}else if(handler.getMouseManager().isRightPressed()) {
					out.writeUTF("right");
				}
				out.writeFloat(handler.getMouseManager().getMouseX());
				out.writeFloat(handler.getMouseManager().getMouseY());
				out.writeFloat(handler.getGameCamera().getxOffset());
				out.writeFloat(handler.getGameCamera().getyOffset());
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
