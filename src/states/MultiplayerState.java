package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.util.Scanner;

import gfx.Assets;
import gfx.Text;
import main.Handler;
import server.Client;
import server.Server;
import ui.UIManager;
import worlds.World;

// Referenced classes of package states:
//            State

public class MultiplayerState extends State
{
	
	private static Server server1;
	private static Server server2;
	private static Client client1;
	private static Client client2;
	private UIManager uiManager;
	private World world;
	private static int id = 1;
	
	private static Scanner scanner = new Scanner(System.in);

    public MultiplayerState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
    }

    public void tick()
    {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_J)) {
        	
//        	world = new World(handler, "res/worlds/world1.txt");
//            handler.setWorld(world);
//            
//            handler.getWorld().setLoaded(false);
//            handler.getWorld().setCount(World.getCount() + 1);
//            handler.getWorld().setLoadedWorld(World.getCount());
//            handler.getWorld().setCurrentWorld(1);
//        	
//            handler.getWorld().getEntityManager().createPlayer2();
            
        	State.setState(handler.getGame().multiplayerWorldLoadState);
        }
    }

    public void render(Graphics g)
    {
    	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
        Text.drawString(g, "Would you like to", handler.getWidth() / 2, handler.getHeight() / 3 - 100, true, Color.WHITE, Assets.font28);
        Text.drawString(g, "Join or Create a Server?", handler.getWidth() / 2, handler.getHeight() / 3 - 75, true, Color.WHITE, Assets.font28);
        Text.drawString(g, "Press J to Join", handler.getWidth() / 2, 3 * (handler.getHeight() / 4), true, Color.WHITE, Assets.font28);
        uiManager.render(g);
    }

    public static String getIp() {
    	String ip = "";
    	if(id == 1) {
    		try {
    			ip = InetAddress.getLocalHost().getHostAddress();
    		} catch (java.net.UnknownHostException e) {
    			e.printStackTrace();
    		}
    	}else if(id == 2) {
    		//this is to target the computer with the server program running
    		System.out.println("Type IP Address: ");
    		ip = scanner.next();
    	}
		return ip;
    }
    
	public static Server getServer1() {
		return server1;
	}

	public static Server getServer2() {
		return server2;
	}

	public static Client getClient1() {
		return client1;
	}

	public static Client getClient2() {
		return client2;
	}
    
	public static void start() {
		if(id == 1) {
        	client1.start(5000, handler, getIp());
        	server1.start(5002, handler);
        }else if(id == 2) {
        	client2.start(5001, handler, getIp());
        	server2.start(5003, handler);
        }
	}
	
}
