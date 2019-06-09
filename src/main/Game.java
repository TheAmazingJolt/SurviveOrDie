package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

import audio.AudioManager;
import display.Display;
import gfx.Assets;
import gfx.GameCamera;
import guis.GuiManager;
import input.KeyManager;
import input.MouseManager;
import server.Client;
import server.Server;
import states.DeathState;
import states.GameState;
import states.LoadState;
import states.MenuState;
import states.MultiplayerGameState;
import states.MultiplayerState;
import states.MultiplayerWorldLoadState;
import states.PauseState;
import states.SaveState;
import states.SettingsState;
import states.State;
import states.WorldLoadState;
import utils.Timer;

// Referenced classes of package main:
//            Handler

public class Game implements Runnable {
	
	private Display display;
    private int width;
    private int height;
    public String title;
    private boolean running;
    private boolean multiplayer;
    private boolean debug = false;
    private boolean developer;
    
    private ArrayList<Timer> timers = new ArrayList<Timer>();
    private ArrayList<Timer> timersToRemove = new ArrayList<Timer>();
    
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    
    public State gameState;
    public State menuState;
    public State pauseState;
    public State settingState;
    public State saveState;
    public State loadState;
    public State worldLoadState;
    public State multiplayerState;
    public State multiplayerGameState;
    public State multiplayerWorldLoadState;
    public State deathState;
    public State settingsState;
    
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private GuiManager guiManager;
    private GameCamera gameCamera;
    private Handler handler;
    private AudioManager audioManager;

    public Game(String title, boolean multiplayer, boolean developer, int width, int height) {
        running = false;
        this.width = width;
        this.height = height;
        this.title = title;
        this.multiplayer = multiplayer;
        this.developer = developer;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        guiManager = new GuiManager();
        audioManager = new AudioManager();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0.0F, 0.0F);
        gameState = new GameState(handler);
        pauseState = new PauseState(handler);
        menuState = new MenuState(handler);
        saveState = new SaveState(handler);
        loadState = new LoadState(handler);
        worldLoadState = new WorldLoadState(handler);
        multiplayerState = new MultiplayerState(handler);
        multiplayerGameState = new MultiplayerGameState(handler);
        multiplayerWorldLoadState = new MultiplayerWorldLoadState(handler);
        deathState = new DeathState(handler);
        settingsState = new SettingsState(handler);
        State.setState(menuState);
    }

    private void tick() {
        audioManager.tick();
        keyManager.tick();
        if(State.getState() != null)
            State.getState().tick();
        if(Client.isActive()) 
        	Client.tick();
        if(Server.isActive())
        	Server.tick();
        if(developer)
        	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F1))
        		debug = !debug;
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        if(State.getState() != null)
            State.getState().render(g);
        bs.show();
        g.dispose();
    }

	public void run() {
        init();
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0.0D;
        long lastTime = System.nanoTime();
        long timer = 0L;
        int ticks = 0;
        while(running) 
        {
            long now = System.nanoTime();
            delta += (double)(now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if(delta >= 1.0D)
            {
            	if(ticks>60) {
            		delta--;
            	}
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000)
            {
                System.out.println((new StringBuilder("Ticks and Frames: ")).append(ticks).toString());
                ticks = 0;
                timer = 0L;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public GuiManager getGuiManager() {
		return guiManager;
	}

	public int getHeight() {
        return height;
    }

    public boolean isMultiplayer() {
		return multiplayer;
	}

	public synchronized void start(){
        if(running)
        {
            return;
        } else
        {
            running = true;
            thread = new Thread(this);
            thread.start();
            return;
        }
    }

	public void addTimer(Timer t) {
		timers.add(t);
	}
	
	public void removeTimer(Timer t) {
		timersToRemove.add(t);
	}
	
    public synchronized void stop() {
        if(!running)
            return;
        running = false;
        try
        {
            thread.join();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

	public AudioManager getAudioManager() {
		return audioManager;
	}
    
}