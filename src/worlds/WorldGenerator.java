package worlds;

import java.util.ArrayList;
import java.util.Random;

import tiles.Tile;

public class WorldGenerator {

	private int width;
	private int height;
	private int worldId;
	
	public int[][] tiles;
	
	private int currentX = 0;
	private int currentY = 0;
	
	private ArrayList<Tile> tiles1 = new ArrayList<Tile>();
	private ArrayList<Tile> tiles2 = new ArrayList<Tile>();
	private ArrayList<Tile> tiles3 = new ArrayList<Tile>();
	private ArrayList<Tile> tiles4 = new ArrayList<Tile>();
	
	private Random random = new Random();
	
	public WorldGenerator(int width, int height, int worldId) {
		this.width = width;
		this.height = height;
		this.worldId = worldId;
		setTileLists();
	}
	
	public void generate() {
		tiles = new int[width + 1][height + 1];
		if(worldId == 1) {
			while(currentY < width && currentX < height) {
				if((currentY == 0 || currentY == height - 1)) {
					//creates outline of walls?
					tiles[currentX][currentY] = Tile.rockTile.getId();
				}else if(currentX == 0 || currentX == width - 1) {
					tiles[currentX][currentY] = Tile.rockTile.getId();
				}else if(currentY > 0 && currentY < height - 1 && currentX > 0 && currentX < width - 1) {
					
				}
				currentX++;
				if(currentX >= width) {
					currentY++;
					currentX = 0;
				}
				continue;
			}
		}else if(worldId == 2) {
			
		}else if(worldId == 3) {
			
		}else if(worldId == 4) {
			
		}
	}
	
	private void setTileLists() {
		//world1
		tiles1.add(Tile.dirtTile);
		tiles1.add(Tile.grassTile);
		tiles1.add(Tile.stoneTile);
		tiles1.add(Tile.waterTile);
		//world2
		tiles2.add(Tile.dirtTile);
		tiles2.add(Tile.hellGrassTile);
		tiles2.add(Tile.hellStoneTile);
		//world3
		tiles3.add(Tile.dirtTile);
		tiles3.add(Tile.icyGrassTile);
		tiles3.add(Tile.icyStoneTile);
		//world4
		tiles4.add(Tile.sandTile);
		tiles4.add(Tile.sandyDoorTile);
		tiles4.add(Tile.sandyDoorTile2);
		tiles4.add(Tile.sandStoneTile);
		tiles4.add(Tile.waterTile);
	}
	
	public int getTile(int x, int y) {
		return tiles[x][y];
	}
	
}
