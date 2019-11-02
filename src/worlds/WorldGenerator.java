package worlds;

import java.util.ArrayList;
import java.util.Random;

import entities.Entity;
import entities.creatures.Zombie;
import entities.statics.Flint;
import entities.statics.Iron;
import entities.statics.Stone;
import entities.statics.Tree;
import main.Handler;
import tiles.Tile;
import worlds.features.Feature;

public class WorldGenerator {

	private int width;
	private int height;
	private int maxEntityAmt;
	private int maxFeatureAmt;
	private int worldId;
	
	private int worldWidth;
	private int worldHeight;
	
	public int[][] tiles;
	
	private int currentX = 0;
	private int currentY = 0;
	
	private int patchWidth = 0;
	private int patchHeight = 0;
	private int maxPatchSize = 7;
	
	private int patchStartX = 0;
	private int patchStartY = 0;
	
	private int patchTileType = -1;
	
	private int x;
	private int y;
	private int fx;
	private int fy;
	private int fwidth;
	private int fheight;
	private int tileX;
	private int tileY;
	private int tileWidth;
	private int tileHeight;
	private int density;
	
	private boolean sizesSet;
	
	private ArrayList<Tile> tiles1 = new ArrayList<Tile>();
	private ArrayList<Tile> tiles2 = new ArrayList<Tile>();
	private ArrayList<Tile> tiles3 = new ArrayList<Tile>();
	private ArrayList<Tile> tiles4 = new ArrayList<Tile>();
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();

	private ArrayList<Feature> features = new ArrayList<Feature>();
	 
	private Random random = new Random();
	
	private Handler handler;
	
	public WorldGenerator(int width, int height, int maxEntityAmt, int maxFeatureAmt, int worldId, Handler handler) {
		this.handler = handler;
		this.width = width;
		this.height = height;
		this.worldId = worldId;
		this.maxEntityAmt = maxEntityAmt;
		this.maxFeatureAmt = maxFeatureAmt;
		this.worldWidth = width * 64;
		this.worldHeight = height * 64;
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
					if(!(tiles[currentX][currentY] > 0)) {
						int rand = random.nextInt(150);//lower value means more patches
						if(rand == 0) {
							patchWidth = random.nextInt(maxPatchSize) + 1;
							patchHeight = random.nextInt(maxPatchSize) + 1;
							patchStartX = currentX;
							patchStartY = currentY;
							int rand2 = random.nextInt(tiles1.size());
							patchTileType = tiles1.get(rand2).getId();
							int x = patchStartX;
							int y = patchStartY;
							int endX = patchStartX + patchWidth;
							int endY = patchStartY + patchHeight;
							if(endX >= width) {
								endX = endX - 5;
							}
							if(endY >= height) {
								endY = endY - 5;
							}
							for(y = patchStartY; y < endY + 1; y++) {
								for(x = patchStartX; x < endX + 1; x++) {
									if(x == endX || x == patchStartX) {
										if(y == endY || y == patchStartY) {
											tiles[x][y] = 0;
											continue;
										}
									}
									tiles[x][y] = patchTileType;
								}
							}
							patchWidth = 0;
							patchHeight = 0;
							patchStartX = 0;
							patchStartY = 0;
							patchTileType = 0;
						}
					}
				}
				currentX++;
				if(currentX >= width) {
					currentY++;
					currentX = 0;
				}
				continue;
			}
//			while(entities.size() < maxEntityAmt) {
//				int x = random.nextInt(worldWidth) + 1;
//				int y = random.nextInt(worldHeight) + 1;
//				for(Entity e : entities) {
//					if(e.getX() >= x - 32) {
//						if(e.getY() >= y - 32) {
//							//top left
//							if(e.getName().contains("tree"))
//								x -= 48;
//							else
//								x -= 16;
//						}else if(e.getY() <= y + 32) {
//							//bottom left
//							if(e.getName().contains("tree"))
//								x -= 48;
//							else
//								x -= 16;
//						}
//					}else if(e.getX() <= x + 32) {
//						if(e.getY() >= y - 32) {
//							//top right
//							if(e.getName().contains("tree"))
//								x += 48;
//							else
//								x += 16;
//						}else if(e.getY() <= y + 32) {
//							//bottom right
//							if(e.getName().contains("tree"))
//								x += 48;
//							else
//								x += 16;
//						}
//					}
//				}
//				int entityNum = 0;
//				if(x > 64 && x < worldWidth - 64) {
//					if(y > 64 && y < worldHeight - 64) {
//						entityNum++;
//						int entityType = random.nextInt(20);
//						if(entityType == 0 || entityType == 1 || entityType == 2 || entityType == 3 || entityType == 4 || entityType == 5 || entityType == 6) {
//							entities.add(new Tree(handler, x, y, entityNum));
//						}else if(entityType == 7 || entityType == 8 || entityType == 9) {
//							entities.add(new Stone(handler, x, y, entityNum));
//						}else if(entityType == 10 || entityType == 11 || entityType == 12) {
//							entities.add(new Iron(handler, x, y, entityNum));
//						}else if(entityType == 13 || entityType == 14 || entityType == 15) {
//							entities.add(new Flint(handler, x, y, entityNum));
//						}else if(entityType == 16 || entityType == 17) {
//							entities.add(new Zombie(handler, x, y, entityNum, handler.getWorld().getEntityManager().getPlayer()));
//						}
//					}
//				}
//			}
			while(features.size() < maxFeatureAmt) {
				setSizes();
//				if(x + fwidth >= worldWidth || y + fheight >= worldHeight) {
//					setSizes();
//				}
				int featureNum = random.nextInt(3) + 1; //set number in parentheses to amount of features
				if(featureNum == 1) {
					Feature feature = new Feature("normalForest", fwidth, fheight, fx, fy, density, handler, tiles);
					features.add(feature);
					feature.generate();
					entities.addAll(feature.getEntities());
					tiles = feature.getTiles();
				}else if(featureNum == 2) {
					Feature feature = new Feature("rockyForest", fwidth, fheight, fx, fy, density, handler, tiles);
					features.add(feature);
					feature.generate();
					entities.addAll(feature.getEntities());
					tiles = feature.getTiles();
				}else if(featureNum == 3) {
					Feature feature = new Feature("rocks. just rocks", fwidth, fheight, fx, fy, density, handler, tiles);
					features.add(feature);
					feature.generate();
					entities.addAll(feature.getEntities());
					tiles = feature.getTiles();
				}
			}
		}else if(worldId == 2) {
			
		}else if(worldId == 3) {
			
		}else if(worldId == 4) {
			
		}
	}
	
	private void setTileLists() {
		//world1
		tiles1.add(Tile.dirtTile);
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
	
	private void setSizes() {
		x = random.nextInt(worldWidth - 64) + 1;
		y = random.nextInt(worldHeight - 64) + 1;
		fx = (int) (Math.floor(x/64) * 64);
		fy = (int) (Math.floor(y/64) * 64);
		fwidth = (int) (Math.floor((random.nextInt(1024) + 256) / 64) * 64);
		fheight = (int) (Math.floor((random.nextInt(1024) + 256) / 64) * 64);
		tileX = (int) Math.floor(fx / 64);
		tileY = (int) Math.floor(fy / 64);
		tileWidth = (int) Math.floor(fwidth / 64);
		tileHeight = (int) Math.floor(fheight / 64);
		density = random.nextInt(50) + 10;
		if(worldWidth - (fx + fwidth) <= 0) {
			setSizes();
		}else if(worldHeight - (fy + fheight) <= 0) {
			setSizes();
		}
	}
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
	
	public int getTile(int x, int y) {
		return tiles[x][y];
	}
	
}
