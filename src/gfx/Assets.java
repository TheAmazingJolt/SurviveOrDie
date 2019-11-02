package gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// Referenced classes of package gfx:
//            FontLoader, SpriteSheet, ImageLoader

public class Assets
{
	
	private static final int width = 32;
    private static final int height = 32;
    
    public static Font font11;
    public static Font font16;
    public static Font font20;
    public static Font font24;
    public static Font font28;
    public static Font font32;
    public static Font font40;
    public static Font font60;
    
    public static BufferedImage dirt;
    public static BufferedImage grass;
    public static BufferedImage sand;
    public static BufferedImage hellGrass;
    public static BufferedImage stoneWall;
    public static BufferedImage sandStoneWall;
    public static BufferedImage hellStoneWall;
    public static BufferedImage stone;
    public static BufferedImage sandStone;
    public static BufferedImage hellStone;
    public static BufferedImage water;
    public static BufferedImage woodenStructure;
    public static BufferedImage tree;
    public static BufferedImage hellTree;
    public static BufferedImage rock;
    public static BufferedImage ironOre;
    public static BufferedImage flintRock;
    public static BufferedImage openDoor;
    public static BufferedImage closedDoor;
    public static BufferedImage openDoor2;
    public static BufferedImage closedDoor2;
    public static BufferedImage openSandyDoor;
    public static BufferedImage closedSandyDoor;
    public static BufferedImage openSandyDoor2;
    public static BufferedImage closedSandyDoor2;
    public static BufferedImage warp;
    public static BufferedImage icyWall;
    public static BufferedImage icyGrass;
    public static BufferedImage icyStone;
    public static BufferedImage icyTree;
    public static BufferedImage grave;
    
    public static BufferedImage wood;
    public static BufferedImage ashyWood;
    public static BufferedImage rottenFlesh;
    public static BufferedImage axe;
    public static BufferedImage fire;
    public static BufferedImage ironBar;
    public static BufferedImage steelBar;
    public static BufferedImage cutWood;
    public static BufferedImage ironAxe;
    public static BufferedImage woodenHandle;
    public static BufferedImage charcoal;
    public static BufferedImage crushedIron;
    public static BufferedImage healingPowder;
    public static BufferedImage healingPotion;
    public static BufferedImage bottle;
    public static BufferedImage waterBottle;
    public static BufferedImage healthBoostPotion;
    public static BufferedImage coal;
    public static BufferedImage coalDust;
    public static BufferedImage steelAxe;
    public static BufferedImage steelGun;
    public static BufferedImage steelHealthBoostPotion;
    public static BufferedImage steelBullet;
    public static BufferedImage inventoryUpgrade;
    public static BufferedImage speedUpgrade;
    public static BufferedImage cutAshyWood;
    public static BufferedImage steelHammer;
    public static BufferedImage ashyWoodHandle;
    public static BufferedImage steelPlate;
    public static BufferedImage steelRod;
    public static BufferedImage gunPowder;
    public static BufferedImage snow;
    public static BufferedImage smg;
    public static BufferedImage smgBullet;
    
    public static BufferedImage player_down[];
    public static BufferedImage player_up[];
    public static BufferedImage player_left[];
    public static BufferedImage player_right[];
    
    public static BufferedImage player_dead;
    public static BufferedImage player_face;
    
    public static BufferedImage fran_down[];
    public static BufferedImage fran_up[];
    public static BufferedImage fran_left[];
    public static BufferedImage fran_right[];
    
    public static BufferedImage angry_fran_down[];
    public static BufferedImage angry_fran_up[];
    public static BufferedImage angry_fran_left[];
    public static BufferedImage angry_fran_right[];

    public static BufferedImage fran_face;
    
    public static BufferedImage sierra_down[];
    public static BufferedImage sierra_up[];
    
    public static BufferedImage sierra_face;
    
    public static BufferedImage chris_down[];
    public static BufferedImage chris_up[];
    public static BufferedImage chris_left[];
    public static BufferedImage chris_right[];
    
    public static BufferedImage chris_face;
    
    public static BufferedImage penguin_down[];
    public static BufferedImage penguin_up[];
    public static BufferedImage penguin_left[];
    public static BufferedImage penguin_right[];
    
    public static BufferedImage zombie_down[];
    public static BufferedImage zombie_up[];
    public static BufferedImage zombie_left[];
    public static BufferedImage zombie_right[];
    
    public static BufferedImage hell_zombie_down[];
    public static BufferedImage hell_zombie_up[];
    public static BufferedImage hell_zombie_left[];
    public static BufferedImage hell_zombie_right[];
    
    public static BufferedImage ashy_zombie_down[];
    public static BufferedImage ashy_zombie_up[];
    public static BufferedImage ashy_zombie_left[];
    public static BufferedImage ashy_zombie_right[];
    
    public static BufferedImage icy_zombie_down[];
    public static BufferedImage icy_zombie_up[];
    public static BufferedImage icy_zombie_left[];
    public static BufferedImage icy_zombie_right[];
    
    public static BufferedImage btn_start[];
    public static BufferedImage btn_exit[];
    
    public static BufferedImage hotbar;
    public static BufferedImage inventoryScreen;
    public static BufferedImage settingScreen;
    public static BufferedImage deathScreen;
    public static BufferedImage weaponScreen;
    public static BufferedImage startScreen;
    public static BufferedImage saveSelectScreen;
    public static BufferedImage saveSelectBar;
    public static BufferedImage textbox;
    public static BufferedImage outline;
    public static BufferedImage redOutline;
    public static BufferedImage button;
    public static BufferedImage smallButton;
    public static BufferedImage smallestButton;
    public static BufferedImage title;
	
    public static void init()
    {
    	ImageIO.setCacheDirectory(new File("res/cache"));
    	
    	font11 = FontLoader.loadFont("res/fonts/slkscr.ttf", 11F);
    	font16 = FontLoader.loadFont("res/fonts/slkscr.ttf", 16F);
        font20 = FontLoader.loadFont("res/fonts/slkscr.ttf", 20F);
        font24 = FontLoader.loadFont("res/fonts/slkscr.ttf", 24F);
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28F);
        font32 = FontLoader.loadFont("res/fonts/slkscr.ttf", 32F);
        font40 = FontLoader.loadFont("res/fonts/slkscr.ttf", 40F);
        font60 = FontLoader.loadFont("res/fonts/slkscr.ttf", 60F);
        
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        SpriteSheet animations = new SpriteSheet(ImageLoader.loadImage("/textures/animationSheet.png"));
        
        settingScreen = ImageLoader.loadImage("/textures/settingScreen.png");
        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
        weaponScreen = ImageLoader.loadImage("/textures/weaponScreen.png");
        deathScreen = ImageLoader.loadImage("/textures/deathScreen.png");
        startScreen = ImageLoader.loadImage("/textures/startScreen.png");
        saveSelectScreen = ImageLoader.loadImage("/textures/saveSelectionScreen.png");
        saveSelectBar = ImageLoader.loadImage("/textures/saveSelectionBar.png");
        textbox = ImageLoader.loadImage("/textures/textbox.png");
        hotbar = ImageLoader.loadImage("/textures/hotbar.png");
        outline = ImageLoader.loadImage("/textures/outline.png");
        redOutline = ImageLoader.loadImage("/textures/outline2.png");
        button = ImageLoader.loadImage("/textures/button.png");
        smallButton = ImageLoader.loadImage("/textures/smallButton.png");
        smallestButton = ImageLoader.loadImage("/textures/smallestButton.png");
        title = ImageLoader.loadImage("/textures/title.png");
        
        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];
        player_down[0] = animations.crop(width * 4, 0, 32, 32);
        player_down[1] = animations.crop(width * 5, 0, 32, 32);
        player_up[0] = animations.crop(width * 6, 0, 32, 32);
        player_up[1] = animations.crop(width * 7, 0, 32, 32);
        player_right[0] = animations.crop(width * 4, 32, 32, 32);
        player_right[1] = animations.crop(width * 5, 32, 32, 32);
        player_left[0] = animations.crop(width * 6, 32, 32, 32);
        player_left[1] = animations.crop(width * 7, 32, 32, 32);
        player_dead = animations.crop(0, height * 15, width, height);
        player_face = animations.crop(width, height * 15, width, height);

        fran_down = new BufferedImage[2];
        fran_up = new BufferedImage[2];
        fran_left = new BufferedImage[2];
        fran_right = new BufferedImage[2];
        fran_down[0] = animations.crop(width * 8, height * 6, 32, 32);
        fran_down[1] = animations.crop(width * 9, height * 6, 32, 32);
        fran_up[0] = animations.crop(width * 10, height * 6, 32, 32);
        fran_up[1] = animations.crop(width * 11, height * 6, 32, 32);
        fran_right[0] = animations.crop(width * 8, height * 7, 32, 32);
        fran_right[1] = animations.crop(width * 9, height * 7, 32, 32);
        fran_left[0] = animations.crop(width * 10, height * 7, 32, 32);
        fran_left[1] = animations.crop(width * 11, height * 7, 32, 32);
        fran_face = animations.crop(width * 2, height * 15, width, height);
        
        angry_fran_down = new BufferedImage[2];
        angry_fran_up = new BufferedImage[2];
        angry_fran_left = new BufferedImage[2];
        angry_fran_right = new BufferedImage[2];
        angry_fran_down[0] = animations.crop(width * 12, height * 6, 32, 32);
        angry_fran_down[1] = animations.crop(width * 13, height * 6, 32, 32);
        angry_fran_up[0] = animations.crop(width * 14, height * 6, 32, 32);
        angry_fran_up[1] = animations.crop(width * 15, height * 6, 32, 32);
        angry_fran_right[0] = animations.crop(width * 12, height * 7, 32, 32);
        angry_fran_right[1] = animations.crop(width * 13, height * 7, 32, 32);
        angry_fran_left[0] = animations.crop(width * 14, height * 7, 32, 32);
        angry_fran_left[1] = animations.crop(width * 15, height * 7, 32, 32);
        
        sierra_down = new BufferedImage[2];
        sierra_up = new BufferedImage[2];
        sierra_down[0] = animations.crop(0, height * 10, width, height);
        sierra_down[1] = animations.crop(width, height * 10, width, height);
        sierra_up[0] = animations.crop(width * 2, height * 10, width, height);
        sierra_up[1] = animations.crop(width * 3, height * 10, width, height);
        sierra_face = animations.crop(width * 3, height * 15, width, height);
        
        chris_down = new BufferedImage[2];
        chris_up = new BufferedImage[2];
        chris_left = new BufferedImage[2];
        chris_right = new BufferedImage[2];
        chris_down[0] = animations.crop(width * 4, height * 10, 32, 32);
        chris_down[1] = animations.crop(width * 5, height * 10, 32, 32);
        chris_up[0] = animations.crop(width * 6, height * 10, 32, 32);
        chris_up[1] = animations.crop(width * 7, height * 10, 32, 32);
        chris_right[0] = animations.crop(width * 4, height * 11, 32, 32);
        chris_right[1] = animations.crop(width * 5, height * 11, 32, 32);
        chris_left[0] = animations.crop(width * 6, height * 11, 32, 32);
        chris_left[1] = animations.crop(width * 7, height * 11, 32, 32);
        chris_face = animations.crop(width * 4, height * 15, width, height);
        
        penguin_down = new BufferedImage[2];
        penguin_up = new BufferedImage[2];
        penguin_left = new BufferedImage[2];
        penguin_right = new BufferedImage[2];
        penguin_down[0] = animations.crop(0, height * 6, width, height);
        penguin_down[1] = animations.crop(width, height * 6, width, height);
        penguin_up[0] = animations.crop(width * 2, height * 6, width, height);
        penguin_up[1] = animations.crop(width * 3, height * 6, width, height);
        penguin_left[0] = animations.crop(0, height * 7, width, height);
        penguin_left[1] = animations.crop(width, height * 7, width, height);
        penguin_right[0] = animations.crop(width * 2, height * 7, width, height);
        penguin_right[1] = animations.crop(width * 3, height * 7, width, height);
        
        zombie_down = new BufferedImage[2];
        zombie_up = new BufferedImage[2];
        zombie_left = new BufferedImage[2];
        zombie_right = new BufferedImage[2];
        zombie_down[0] = animations.crop(0, 0, 32, 32);
        zombie_down[1] = animations.crop(width, 0, 32, 32);
        zombie_up[0] = animations.crop(width * 2, 0, 32, 32);
        zombie_up[1] = animations.crop(width * 3, 0, 32, 32);
        zombie_right[0] = animations.crop(0, 32, 32, 32);
        zombie_right[1] = animations.crop(width, 32, 32, 32);
        zombie_left[0] = animations.crop(width * 2, 32, 32, 32);
        zombie_left[1] = animations.crop(width * 3, 32, 32, 32);
        
        hell_zombie_down = new BufferedImage[2];
        hell_zombie_up = new BufferedImage[2];
        hell_zombie_left = new BufferedImage[2];
        hell_zombie_right = new BufferedImage[2];
        hell_zombie_down[0] = animations.crop(width * 8, 0, 32, 32);
        hell_zombie_down[1] = animations.crop(width * 9, 0, 32, 32);
        hell_zombie_up[0] = animations.crop(width * 10, 0, 32, 32);
        hell_zombie_up[1] = animations.crop(width * 11, 0, 32, 32);
        hell_zombie_right[0] = animations.crop(width * 8, height, 32, 32);
        hell_zombie_right[1] = animations.crop(width * 9, height, 32, 32);
        hell_zombie_left[0] = animations.crop(width * 10, height, 32, 32);
        hell_zombie_left[1] = animations.crop(width * 11, height, 32, 32);
        
        ashy_zombie_down = new BufferedImage[2];
        ashy_zombie_up = new BufferedImage[2];
        ashy_zombie_left = new BufferedImage[2];
        ashy_zombie_right = new BufferedImage[2];
        ashy_zombie_down[0] = animations.crop(width * 12, 0, 32, 32);
        ashy_zombie_down[1] = animations.crop(width * 13, 0, 32, 32);
        ashy_zombie_up[0] = animations.crop(width * 14, 0, 32, 32);
        ashy_zombie_up[1] = animations.crop(width * 15, 0, 32, 32);
        ashy_zombie_right[0] = animations.crop(width * 12, height, 32, 32);
        ashy_zombie_right[1] = animations.crop(width * 13, height, 32, 32);
        ashy_zombie_left[0] = animations.crop(width * 14, height, 32, 32);
        ashy_zombie_left[1] = animations.crop(width * 15, height, 32, 32);
        
        icy_zombie_down = new BufferedImage[2];
        icy_zombie_up = new BufferedImage[2];
        icy_zombie_left = new BufferedImage[2];
        icy_zombie_right = new BufferedImage[2];
        icy_zombie_down[0] = animations.crop(width * 4, height * 6, 32, 32);
        icy_zombie_down[1] = animations.crop(width * 5, height * 6, 32, 32);
        icy_zombie_up[0] = animations.crop(width * 6, height * 6, 32, 32);
        icy_zombie_up[1] = animations.crop(width * 7, height * 6, 32, 32);
        icy_zombie_right[0] = animations.crop(width * 4, height * 7, 32, 32);
        icy_zombie_right[1] = animations.crop(width * 5, height * 7, 32, 32);
        icy_zombie_left[0] = animations.crop(width * 6, height * 7, 32, 32);
        icy_zombie_left[1] = animations.crop(width * 7, height * 7, 32, 32);
        
        dirt = sheet.crop(32, 0, 32, 32);
        grass = sheet.crop(64, 0, 32, 32);
        sand = sheet.crop(width * 4, height * 2, width, height);
        hellGrass = sheet.crop(0, width * 2, width, height);
        stoneWall = sheet.crop(96, 0, 32, 32);
        sandStoneWall = sheet.crop(width * 5, height * 3, width, height);
        hellStoneWall = sheet.crop(0, height, width, height);
        stone = sheet.crop(96, 32, 32, 32);
        sandStone = sheet.crop(width * 5, height * 2, width, height);
        hellStone = sheet.crop(width, height * 2, width, height);
        water = sheet.crop(width * 2, height * 3, width, height);
        woodenStructure = sheet.crop(width * 2, height * 2, width, height);
        closedDoor = sheet.crop(width, height, 32, 32);
        openDoor = sheet.crop(width * 2, height, 32, 32);
        closedDoor2 = sheet.crop(width * 3, height * 3, 32, 32);
        openDoor2 = sheet.crop(width * 4, height * 3, 32, 32);
        closedSandyDoor = sheet.crop(width * 4, height, 32, 32);
        openSandyDoor = sheet.crop(width * 5, height, 32, 32);
        closedSandyDoor2 = sheet.crop(width * 4, 0, 32, 32);
        openSandyDoor2 = sheet.crop(width * 5, 0, 32, 32);
        warp = sheet.crop(0, 0, 32, 32);
        icyGrass = sheet.crop(width * 3, height * 2, width, height);
        icyStone = sheet.crop(0, height * 3, width, height);
        icyWall = sheet.crop(width, height * 3, width, height);
        grave = sheet.crop(width * 3, height * 15, width, height);
        
        tree = sheet.crop(0, height * 12, 32, 64);
        hellTree = sheet.crop(0, height * 14, width, height * 2);
        rock = sheet.crop(width, height * 13, 32, 32);
        ironOre = sheet.crop(width, height * 14, 32, 32);
        flintRock = sheet.crop(width, height * 15, 32, 32);
        coal = sheet.crop(width, height * 12, width, height);
        icyTree = sheet.crop(width * 2, height * 13, width, height * 2);
        
        rottenFlesh = sheet.crop(width * 11, height * 15, 32, 32);
        wood = sheet.crop(width * 14, height * 15, 32, 32);
        ashyWood = sheet.crop(width * 11, height * 13, width, height);
        axe = sheet.crop(width * 15, height * 15, 32, 32);
        fire = sheet.crop(width * 15, height * 14, 32, 32);
        ironBar = sheet.crop(width * 14, height * 14, 32, 32);
        steelBar = sheet.crop(width * 10, height * 14, width , height);
        cutWood = sheet.crop(width * 10, height * 15, 32, 32);
        ironAxe = sheet.crop(width * 15, height * 13, 32, 32);
        woodenHandle = sheet.crop(width * 14, height * 13, 32, 32);
        charcoal = sheet.crop(width * 2, height * 15, 32, 32);
        crushedIron = sheet.crop(width * 12, height * 14, 32, 32);
        healingPowder = sheet.crop(width * 13, height * 15, 32, 32);
        healingPotion = sheet.crop(width * 12, height * 15, 32, 32);
        bottle = sheet.crop(width * 13, height * 14, 32, 32);
        waterBottle = sheet.crop(width * 14, height * 11, 32, 32);
        healthBoostPotion = sheet.crop(width * 13, height * 13, 32, 32);
        coalDust = sheet.crop(width * 11, height * 14, width, height);
        steelAxe = sheet.crop(width * 12, height * 13, width, height);
        steelHealthBoostPotion = sheet.crop(width * 10, height * 13, width, height);
        steelGun = sheet.crop(width * 15, height * 12, width, height);
        steelBullet = sheet.crop(width * 14, height * 12, width, height);
        inventoryUpgrade = sheet.crop(width * 13, height * 12, width, height);
        speedUpgrade = sheet.crop(width * 12, height * 12, width, height);
        cutAshyWood = sheet.crop(width * 11, height * 12, width, height);
        steelHammer = sheet.crop(width * 10, height * 12, width, height);
        ashyWoodHandle = sheet.crop(width * 9, height * 12, width, height);
        steelPlate = sheet.crop(width * 9, height * 13, width, height);
        steelRod = sheet.crop(width * 9, height * 14, width, height);
        gunPowder = sheet.crop(width * 9, height * 15, width, height);
        snow = sheet.crop(width * 15, height * 11, width, height);
        smg = sheet.crop(width * 13, height * 11, width, height);
        smgBullet = sheet.crop(width * 12, height * 11, width, height);
    }

    
}