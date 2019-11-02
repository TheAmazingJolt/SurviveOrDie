package worlds.features;

import java.util.ArrayList;
import java.util.Random;

import entities.Entity;
import entities.statics.Stone;
import entities.statics.Tree;
import main.Handler;
import tiles.Tile;

public class Feature {

	private int height;
	private int width;
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	
	private int density; //density is chance of each tile to have an entity. ranges from 0 to 100
	private int entityNum = 0;
	
	private String featureType;
	
	private String entityType;
	private String secondaryEntityType;
	private Tile tileType;
	private Tile secondaryTileType;
	
	private boolean hasMultipleEntities;
	private boolean hasMultipleTiles;
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();

	private int[][] tiles;
	
	private Handler handler;
	private Random random = new Random();
	
	public Feature(String featureType, int width, int height, int startX, int startY, int density, Handler handler, int[][] tiles) {
		this.featureType = featureType;
		this.height = height;
		this.width = width;
		this.density = density;
		this.handler = handler;
		this.startX = startX;
		this.startY = startY;
		this.endX = startX + this.width;
		this.endY = startY + this.height;
		this.tiles = tiles;
	}
	
	public void generate() {
		createFeatureData();
		for(int x = startX; x < endX; x += 64) {
			for(int y = startY; y < endY; y += 64) {
				if(!hasMultipleEntities) {
					if(!hasMultipleTiles) {
						int randInt = random.nextInt(100) + 1;
						if(randInt <= density) {
							entityNum++;
							if(entityType.contains("Tree")) {
								entities.add(new Tree(handler, x, y, entityNum));
							}else if(entityType.contains("Stone")) {
								entities.add(new Stone(handler, x, y, entityNum));
							}
						}
						if(featureType.contains("normalForest")) {
							tiles[x/64][y/64] = tileType.id;
						}else if(featureType.contains("rocks. just rocks")) {
							tiles[x/64][y/64] = tileType.id;
						}
					}else if(hasMultipleTiles) {
						int randInt = random.nextInt(100) + 1;
						if(randInt <= density) {
							entityNum++;
							if(entityType.contains("Tree")) {
								entities.add(new Tree(handler, x, y, entityNum));
							}else if(entityType.contains("Stone")) {
								entities.add(new Stone(handler, x, y, entityNum));
							}
						}
						//tile selection goes here
					}
				}else if(hasMultipleEntities) {
					if(!hasMultipleTiles) {
						int randInt = random.nextInt(100) + 1;
						if(randInt <= density) {
							entityNum++;
							if(entityType.contains("Tree")) {
								entities.add(new Tree(handler, x, y, entityNum));
							}else if(entityType.contains("Stone")) {
								entities.add(new Stone(handler, x, y, entityNum));
							}
						}
						//tile selection goes here
					}else if(hasMultipleTiles) {
						int randInt = random.nextInt(100) + 1;
						if(randInt <= density) {
							entityNum++;
							int rand = random.nextInt(4);
							if(rand < 3) {
								if(entityType.contains("Tree")) {
									entities.add(new Tree(handler, x, y, entityNum));
								}else if(entityType.contains("Stone")) {
									entities.add(new Stone(handler, x, y, entityNum));
								}
							}else if(rand == 3) {
								if(secondaryEntityType.contains("Tree")) {
									entities.add(new Tree(handler, x, y, entityNum));
								}else if(secondaryEntityType.contains("Stone")) {
									entities.add(new Stone(handler, x, y, entityNum));
								}
							}
						}
						if(featureType.contains("rockyForest")) {
							int rand = random.nextInt(4);
							if(rand < 3) {
								tiles[x/64][y/64] = tileType.id;
							}else if(rand == 3) {
								tiles[x/64][y/64] = secondaryTileType.id;
							}
						}
					}
				}
			}
		}
	}	
	
	private void createFeatureData() {
		if(featureType.contains("normalForest")) {
			entityType = "Tree";
			secondaryEntityType = "";
			tileType = Tile.grassTile;
			secondaryTileType = null;
			hasMultipleEntities = false;
			hasMultipleTiles = false;
		}else if(featureType.contains("rockyForest")) {
			entityType = "Tree";
			secondaryEntityType = "Stone";
			tileType = Tile.grassTile;
			secondaryTileType = Tile.stoneTile;
			hasMultipleEntities = true;
			hasMultipleTiles = true;
		}else if(featureType.contains("rocks. just rocks")) {
			entityType = "Stone";
			secondaryEntityType = "";
			tileType = Tile.stoneTile;
			secondaryTileType = null;
			hasMultipleEntities = false;
			hasMultipleTiles = false;
		}
	}
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
	
	public int[][] getTiles(){
		return tiles;
	}
	
}
