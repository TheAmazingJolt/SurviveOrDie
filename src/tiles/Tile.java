package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import tiles.structures.Structure;
import tiles.structures.WoodStructure;

public class Tile {
	
	public static ArrayList<Tile> tiles = new ArrayList<Tile>();
	public static ArrayList<Structure> structures = new ArrayList<Structure>();
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	public static Tile stoneTile = new StoneTile(3);
	public static Tile waterTile = new WaterTile(4);
	public static Tile doorTile = new DoorTile(5);
	public static Tile warpTile = new WarpTile(6);
	public static Tile hellRockTile = new HellRockTile(7);
	public static Tile hellStoneTile = new HellStoneTile(8);
	public static Tile hellGrassTile = new HellGrassTile(9);
	public static Structure woodStructure = new WoodStructure(10);
	public static Tile icyRockTile = new IcyRockTile(11);
	public static Tile icyStoneTile = new IcyStoneTile(12);
	public static Tile icyGrassTile = new IcyGrassTile(13);
	public static Tile doorTile2 = new DoorTile2(14);
	public static Tile sandTile = new SandTile(15);
	public static Tile sandStoneTile = new SandStoneTile(16);
	public static Tile sandStoneWallTile = new SandStoneWallTile(17);
	public static Tile sandyDoorTile = new SandyDoorTile(18);
	public static Tile sandyDoorTile2 = new SandyDoorTile2(19);
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	public final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public boolean isDeadly() {
		return false;
	}
	
	public int getId() {
		return id;
	}

	public static ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	public static ArrayList<Structure> getStructures() {
		return structures;
	}

	public void setTexture(BufferedImage textur) {
		texture = textur;
	}
	
}
