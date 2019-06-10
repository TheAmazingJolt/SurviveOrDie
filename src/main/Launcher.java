package main;

public class Launcher {
	
    public static final String VERSION_ID = "0.4.0 Alpha";
    public static final boolean MULTIPLAYER = false;
    public static final boolean DEVELOPER = true;

    public static void main(String[] args){
    	Game game = new Game("Survive Or Die" + " " + VERSION_ID, MULTIPLAYER,DEVELOPER,960,720);
        game.start();
    }
    
}
