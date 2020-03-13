package worlds;

import java.util.ArrayList;
import java.util.Random;

import entities.Entity;
import entities.creatures.Penguin;
import main.Handler;
import tiles.Tile;
import worlds.features.Feature;

public class WorldGenerator {

	private int width;
	private int height;
	private int maxFeatureAmt;
	private int worldId;
	
	private int worldWidth;
	private int worldHeight;
	
	public int[][] tiles;
	
	private int currentX = 0;
	private int currentY = 0;
	
	private int x;
	private int y;
	private int fx;
	private int fy;
	private int fwidth;
	private int fheight;
	private int density;
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();

	private ArrayList<Feature> features;
	 
	private Random random = new Random();
	
	private Handler handler;
	
	public WorldGenerator(int width, int height, int maxFeatureAmt, int worldId, Handler handler) {
		this.handler = handler;
		this.width = width;
		this.height = height;
		this.worldId = worldId;
		this.maxFeatureAmt = maxFeatureAmt;
		this.worldWidth = width * 64;
		this.worldHeight = height * 64;
		features = new ArrayList<Feature>();
	}
	
	public void generate() {
		tiles = new int[width + 1][height + 1];
		if(worldId == 1) {
			while(currentY < width && currentX < height) {
				if(currentY == 98 && currentX == 50) {
					tiles[currentX][currentY] = Tile.warpTile.getId();
				}else if(currentY == 95 && currentX == 49) {
					tiles[currentX][currentY] = Tile.doorTile.getId();
				}else if((currentY == 96 || currentY == 97 || currentY == 98) && (currentX >= 48 && currentX <= 50)) {
					tiles[currentX][currentY] = Tile.grassTile.getId();
				}else if((currentY == 0 || currentY >= height - 5) || (currentX == 0 || currentX == width - 1)) {
					//creates outline of walls?
					tiles[currentX][currentY] = Tile.rockTile.getId();
				}else {
					tiles[currentX][currentY] = Tile.grassTile.getId();
				}
				currentX++;
				if(currentX >= width) {
					currentY++;
					currentX = 0;
				}
				continue;
			}
			
			Feature f1 = new Feature("normalForest", 1024, 1024, 64, 64, random.nextInt(40) + 10, handler, tiles);
			features.add(f1);
			f1.generate();
			entities.addAll(f1.getEntities());
			tiles = f1.getTiles();
			Feature f2 = new Feature("rockySpot", 1024, 1024, 5312, 64, random.nextInt(40) + 10, handler, tiles);
			features.add(f2);
			f2.generate();
			entities.addAll(f2.getEntities());
			tiles = f2.getTiles();
			Feature f3 = new Feature("flintSpot", 1024, 1024, 64, 5056, random.nextInt(40) + 10, handler, tiles);
			features.add(f3);
			f3.generate();
			entities.addAll(f3.getEntities());
			tiles = f3.getTiles();
			Feature f4 = new Feature("ironSpot", 1024, 1024, 5312, 5056, random.nextInt(40) + 10, handler, tiles);
			features.add(f4);
			f4.generate();
			entities.addAll(f4.getEntities());
			tiles = f4.getTiles();
			Feature f5 = new Feature("graveyard", 1024, 1024, 2624, 64, random.nextInt(40) + 10, handler, tiles);
			features.add(f5);
			f5.generate();
			entities.addAll(f5.getEntities());
			tiles = f5.getTiles();
			Feature f6 = new Feature("graveyard", 1024, 1024, 2624, 5056, random.nextInt(40) + 10, handler, tiles);
			features.add(f6);
			f6.generate();
			entities.addAll(f6.getEntities());
			tiles = f6.getTiles();
			Feature f7 = new Feature("graveyard", 1024, 1024, 64, 2624, random.nextInt(40) + 10, handler, tiles);
			features.add(f7);
			f7.generate();
			entities.addAll(f7.getEntities());
			tiles = f7.getTiles();
			Feature f8 = new Feature("graveyard", 1024, 1024, 5312, 2624, random.nextInt(40) + 10, handler, tiles);
			features.add(f8);
			f8.generate();
			entities.addAll(f8.getEntities());
			tiles = f8.getTiles();
			while(features.size() < maxFeatureAmt - 8) {
				setSizes();
				if(fy > 1024 && fy < 5312) {
					int featureNum = random.nextInt(7) + 1; //set number in parentheses to amount of features
					if(featureNum == 1 || featureNum == 2 || featureNum == 3) {
						Feature feature = new Feature("normalForest", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 4) {
						Feature feature = new Feature("rockyForest", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 5) {
						Feature feature = new Feature("rockySpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 6) {
						Feature feature = new Feature("ironSpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 7) {
						Feature feature = new Feature("flintSpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}
				}else if(!(fy > 1024 && fy < 5312)) {
					if(fx > 1024 && fx < 5312) {
						int featureNum = random.nextInt(7) + 1; //set number in parentheses to amount of features
						if(featureNum == 1 || featureNum == 2 || featureNum == 3) {
							Feature feature = new Feature("normalForest", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 4) {
							Feature feature = new Feature("rockyForest", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 5) {
							Feature feature = new Feature("rockySpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 6) {
							Feature feature = new Feature("ironSpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 7) {
							Feature feature = new Feature("flintSpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}
					}else if(!(fx > 1024 && fx < 5312)) {
						setSizes();
					}
				}
				
			}
		}else if(worldId == 2) {
			while(currentY < width && currentX < height) {
				if(currentY == 98 && currentX == 50) {
					tiles[currentX][currentY] = Tile.warpTile.getId();
				}else if(currentY == 98 && currentX == 48) {
					tiles[currentX][currentY] = Tile.warpDownTile.getId();
				}else if(currentY == 95 && currentX == 49) {
					tiles[currentX][currentY] = Tile.doorTile.getId();
				}else if((currentY == 96 || currentY == 97 || currentY == 98) && (currentX >= 48 && currentX <= 50)) {
					tiles[currentX][currentY] = Tile.hellGrassTile.getId();
				}else if((currentY == 0 || currentY >= height - 5) || (currentX == 0 || currentX == width - 1)) {
					//creates outline of walls?
					tiles[currentX][currentY] = Tile.hellRockTile.getId();
				}else {
					tiles[currentX][currentY] = Tile.hellGrassTile.getId();
				}
				currentX++;
				if(currentX >= width) {
					currentY++;
					currentX = 0;
				}
				continue;
			}
			
			Feature f1 = new Feature("normalForest", 1024, 1024, 64, 64, random.nextInt(40) + 10, handler, tiles);
			features.add(f1);
			f1.generate();
			entities.addAll(f1.getEntities());
			tiles = f1.getTiles();
			Feature f2 = new Feature("rockySpot", 1024, 1024, 5312, 64, random.nextInt(40) + 10, handler, tiles);
			features.add(f2);
			f2.generate();
			entities.addAll(f2.getEntities());
			tiles = f2.getTiles();
			Feature f3 = new Feature("flintSpot", 1024, 1024, 64, 5056, random.nextInt(40) + 10, handler, tiles);
			features.add(f3);
			f3.generate();
			entities.addAll(f3.getEntities());
			tiles = f3.getTiles();
			Feature f4 = new Feature("ironSpot", 1024, 1024, 5312, 5056, random.nextInt(40) + 10, handler, tiles);
			features.add(f4);
			f4.generate();
			entities.addAll(f4.getEntities());
			tiles = f4.getTiles();
			Feature f5 = new Feature("coalSpot", 1024, 1024, 2624, 64, random.nextInt(40) + 10, handler, tiles);
			features.add(f5);
			f5.generate();
			entities.addAll(f5.getEntities());
			tiles = f5.getTiles();
			Feature f6 = new Feature("graveyard", 1024, 1024, 2624, 5056, random.nextInt(40) + 10, handler, tiles);
			features.add(f6);
			f6.generate();
			entities.addAll(f6.getEntities());
			tiles = f6.getTiles();
			Feature f7 = new Feature("graveyard", 1024, 1024, 64, 2624, random.nextInt(40) + 10, handler, tiles);
			features.add(f7);
			f7.generate();
			entities.addAll(f7.getEntities());
			tiles = f7.getTiles();
			Feature f8 = new Feature("graveyard", 1024, 1024, 5312, 2624, random.nextInt(40) + 10, handler, tiles);
			features.add(f8);
			f8.generate();
			entities.addAll(f8.getEntities());
			tiles = f8.getTiles();
			while(features.size() < maxFeatureAmt - 8) {
				setSizes();
				if(fy > 1024 && fy < 5312) {
					int featureNum = random.nextInt(8) + 1; //set number in parentheses to amount of features
					if(featureNum == 1 || featureNum == 2 || featureNum == 3) {
						Feature feature = new Feature("normalForest", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 4) {
						Feature feature = new Feature("rockyForest", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 5) {
						Feature feature = new Feature("rockySpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 6) {
						Feature feature = new Feature("ironSpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 7) {
						Feature feature = new Feature("flintSpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 8) {
						Feature feature = new Feature("coalSpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}
				}else if(!(fy > 1024 && fy < 5312)) {
					if(fx > 1024 && fx < 5312) {
						int featureNum = random.nextInt(8) + 1; //set number in parentheses to amount of features
						if(featureNum == 1 || featureNum == 2 || featureNum == 3) {
							Feature feature = new Feature("normalForest", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 4) {
							Feature feature = new Feature("rockyForest", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 5) {
							Feature feature = new Feature("rockySpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 6) {
							Feature feature = new Feature("ironSpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 7) {
							Feature feature = new Feature("flintSpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 8) {
							Feature feature = new Feature("coalSpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}
					}else if(!(fx > 1024 && fx < 5312)) {
						setSizes();
					}
				}
				
			}
		}else if(worldId == 3) {
			while(currentY < width && currentX < height) {
				if(currentY == 98 && currentX == 50) {
					tiles[currentX][currentY] = Tile.warpTile.getId();
				}else if(currentY == 98 && currentX == 48) {
					tiles[currentX][currentY] = Tile.warpDownTile.getId();
				}else if(currentY == 95 && currentX == 49) {
					tiles[currentX][currentY] = Tile.doorTile.getId();
				}else if((currentY == 96 || currentY == 97 || currentY == 98) && (currentX >= 48 && currentX <= 50)) {
					tiles[currentX][currentY] = Tile.icyGrassTile.getId();
				}else if((currentY == 0 || currentY >= height - 5) || (currentX == 0 || currentX == width - 1)) {
					//creates outline of walls?
					tiles[currentX][currentY] = Tile.icyRockTile.getId();
				}else {
					tiles[currentX][currentY] = Tile.icyGrassTile.getId();
				}
				currentX++;
				if(currentX >= width) {
					currentY++;
					currentX = 0;
				}
				continue;
			}
			
			Feature f1 = new Feature("normalForest", 1024, 1024, 64, 64, random.nextInt(40) + 10, handler, tiles);
			features.add(f1);
			f1.generate();
			entities.addAll(f1.getEntities());
			tiles = f1.getTiles();
			Feature f2 = new Feature("rockySpot", 1024, 1024, 5312, 64, random.nextInt(40) + 10, handler, tiles);
			features.add(f2);
			f2.generate();
			entities.addAll(f2.getEntities());
			tiles = f2.getTiles();
			Feature f3 = new Feature("flintSpot", 1024, 1024, 64, 5056, random.nextInt(40) + 10, handler, tiles);
			features.add(f3);
			f3.generate();
			entities.addAll(f3.getEntities());
			tiles = f3.getTiles();
			Feature f4 = new Feature("ironSpot", 1024, 1024, 5312, 5056, random.nextInt(40) + 10, handler, tiles);
			features.add(f4);
			f4.generate();
			entities.addAll(f4.getEntities());
			tiles = f4.getTiles();
			Feature f5 = new Feature("graveyard", 1024, 1024, 2624, 64, random.nextInt(40) + 10, handler, tiles);
			features.add(f5);
			f5.generate();
			entities.addAll(f5.getEntities());
			tiles = f5.getTiles();
			Feature f6 = new Feature("graveyard", 1024, 1024, 2624, 5056, random.nextInt(40) + 10, handler, tiles);
			features.add(f6);
			f6.generate();
			entities.addAll(f6.getEntities());
			tiles = f6.getTiles();
			Feature f7 = new Feature("graveyard", 1024, 1024, 64, 2624, random.nextInt(40) + 10, handler, tiles);
			features.add(f7);
			f7.generate();
			entities.addAll(f7.getEntities());
			tiles = f7.getTiles();
			Feature f8 = new Feature("graveyard", 1024, 1024, 5312, 2624, random.nextInt(40) + 10, handler, tiles);
			features.add(f8);
			f8.generate();
			entities.addAll(f8.getEntities());
			tiles = f8.getTiles();
			while(features.size() < maxFeatureAmt - 8) {
				setSizes();
				if(fy > 1024 && fy < 5312) {
					int featureNum = random.nextInt(8) + 1; //set number in parentheses to amount of features
					if(featureNum == 1 || featureNum == 2 || featureNum == 3) {
						Feature feature = new Feature("normalForest", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 4) {
						Feature feature = new Feature("rockyForest", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 5) {
						Feature feature = new Feature("rockySpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 6) {
						Feature feature = new Feature("ironSpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 7) {
						Feature feature = new Feature("flintSpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 8) {
						Feature feature = new Feature("coalSpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}
				}else if(!(fy > 1024 && fy < 5312)) {
					if(fx > 1024 && fx < 5312) {
						int featureNum = random.nextInt(8) + 1; //set number in parentheses to amount of features
						if(featureNum == 1 || featureNum == 2 || featureNum == 3) {
							Feature feature = new Feature("normalForest", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 4) {
							Feature feature = new Feature("rockyForest", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 5) {
							Feature feature = new Feature("rockySpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 6) {
							Feature feature = new Feature("ironSpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 7) {
							Feature feature = new Feature("flintSpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 8) {
							Feature feature = new Feature("coalSpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}
					}else if(!(fx > 1024 && fx < 5312)) {
						setSizes();
					}
				}
				
			}
			int penguinAmt = random.nextInt(50) + 1;
			for(int i = 1; i<penguinAmt; i++) {
				entities.add(new Penguin(handler, random.nextInt(worldWidth - 64) + 64, random.nextInt(worldHeight - 256) + 64, entities.size() + 1));
			}
		}else if(worldId == 4) {
			while(currentY < width && currentX < height) {
				if(currentY == 98 && currentX == 48) {
					tiles[currentX][currentY] = Tile.warpDownTile.getId();
				}else if(currentY == 95 && currentX == 49) {
					tiles[currentX][currentY] = Tile.sandyDoorTile.getId();
				}else if((currentY == 96 || currentY == 97 || currentY == 98) && (currentX >= 48 && currentX <= 50)) {
					tiles[currentX][currentY] = Tile.sandTile.getId();
				}else if((currentY == 0 || currentY >= height - 5) || (currentX == 0 || currentX == width - 1)) {
					//creates outline of walls?
					tiles[currentX][currentY] = Tile.sandStoneWallTile.getId();
				}else {
					tiles[currentX][currentY] = Tile.sandTile.getId();
				}
				currentX++;
				if(currentX >= width) {
					currentY++;
					currentX = 0;
				}
				continue;
			}
			
			Feature f1 = new Feature("normalForest", 1024, 1024, 64, 64, random.nextInt(40) + 10, handler, tiles);
			features.add(f1);
			f1.generate();
			entities.addAll(f1.getEntities());
			tiles = f1.getTiles();
			Feature f2 = new Feature("rockySpot", 1024, 1024, 5312, 64, random.nextInt(40) + 10, handler, tiles);
			features.add(f2);
			f2.generate();
			entities.addAll(f2.getEntities());
			tiles = f2.getTiles();
			Feature f3 = new Feature("flintSpot", 1024, 1024, 64, 5056, random.nextInt(40) + 10, handler, tiles);
			features.add(f3);
			f3.generate();
			entities.addAll(f3.getEntities());
			tiles = f3.getTiles();
			Feature f4 = new Feature("ironSpot", 1024, 1024, 5312, 5056, random.nextInt(40) + 10, handler, tiles);
			features.add(f4);
			f4.generate();
			entities.addAll(f4.getEntities());
			tiles = f4.getTiles();
			Feature f5 = new Feature("graveyard", 1024, 1024, 2624, 64, random.nextInt(40) + 10, handler, tiles);
			features.add(f5);
			f5.generate();
			entities.addAll(f5.getEntities());
			tiles = f5.getTiles();
			Feature f6 = new Feature("graveyard", 1024, 1024, 2624, 5056, random.nextInt(40) + 10, handler, tiles);
			features.add(f6);
			f6.generate();
			entities.addAll(f6.getEntities());
			tiles = f6.getTiles();
			Feature f7 = new Feature("graveyard", 1024, 1024, 64, 2624, random.nextInt(40) + 10, handler, tiles);
			features.add(f7);
			f7.generate();
			entities.addAll(f7.getEntities());
			tiles = f7.getTiles();
			Feature f8 = new Feature("graveyard", 1024, 1024, 5312, 2624, random.nextInt(40) + 10, handler, tiles);
			features.add(f8);
			f8.generate();
			entities.addAll(f8.getEntities());
			tiles = f8.getTiles();
			while(features.size() < maxFeatureAmt - 8) {
				setSizes();
				if(fy > 1024 && fy < 5312) {
					int featureNum = random.nextInt(8) + 1; //set number in parentheses to amount of features
					if(featureNum == 1 || featureNum == 2 || featureNum == 3) {
						Feature feature = new Feature("normalForest", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 4) {
						Feature feature = new Feature("rockyForest", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 5) {
						Feature feature = new Feature("rockySpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 6) {
						Feature feature = new Feature("ironSpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 7) {
						Feature feature = new Feature("flintSpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}else if(featureNum == 8) {
						Feature feature = new Feature("coalSpot", fwidth, fheight, fx, fy, density, handler, tiles);
						features.add(feature);
						feature.generate();
						entities.addAll(feature.getEntities());
						tiles = feature.getTiles();
					}
				}else if(!(fy > 1024 && fy < 5312)) {
					if(fx > 1024 && fx < 5312) {
						int featureNum = random.nextInt(8) + 1; //set number in parentheses to amount of features
						if(featureNum == 1 || featureNum == 2 || featureNum == 3) {
							Feature feature = new Feature("normalForest", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 4) {
							Feature feature = new Feature("rockyForest", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 5) {
							Feature feature = new Feature("rockySpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 6) {
							Feature feature = new Feature("ironSpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 7) {
							Feature feature = new Feature("flintSpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}else if(featureNum == 8) {
							Feature feature = new Feature("coalSpot", fwidth, fheight, fx, fy, density, handler, tiles);
							features.add(feature);
							feature.generate();
							entities.addAll(feature.getEntities());
							tiles = feature.getTiles();
						}
					}else if(!(fx > 1024 && fx < 5312)) {
						setSizes();
					}
				}
			}
		}
	}
	
	private void setSizes() {
		x = random.nextInt(worldWidth - 64) + 1;
		y = random.nextInt(worldHeight - 64) + 1;
		fx = (int) (Math.floor(x/64) * 64);
		fy = (int) (Math.floor(y/64) * 64);
		fwidth = (int) (Math.floor((random.nextInt(1024) + 256) / 64) * 64);
		fheight = fwidth;
		density = random.nextInt(30) + 10;
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

	public ArrayList<Feature> getFeatures() {
		return features;
	}
	
}
