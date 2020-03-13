package worlds.features;

import java.util.ArrayList;
import java.util.Random;

import entities.Entity;
import entities.creatures.HellZombie;
import entities.creatures.IcyZombie;
import entities.creatures.Zombie;
import entities.statics.Coal;
import entities.statics.DeadTree;
import entities.statics.Flint;
import entities.statics.HellTree;
import entities.statics.IcyTree;
import entities.statics.Iron;
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
						if(handler.getWorld().getCurrentWorld() == 1) {
							int randInt = random.nextInt(100) + 1;
							if(randInt <= density) {
								entityNum++;
								if(entityType.contains("Tree")) {
									entities.add(new Tree(handler, x, y, entityNum));
								}
							}
							if(featureType.contains("forest")) {
								tiles[x/64][y/64] = Tile.grassTile.id;
							}
						}else if(handler.getWorld().getCurrentWorld() == 2) {
							int randInt = random.nextInt(100) + 1;
							if(randInt <= density) {
								entityNum++;
								if(entityType.contains("Tree")) {
									entities.add(new HellTree(handler, x, y, entityNum));
								}
							}
							if(featureType.contains("forest")) {
								tiles[x/64][y/64] = Tile.hellGrassTile.id;
							}
						}else if(handler.getWorld().getCurrentWorld() == 3) {
							int randInt = random.nextInt(100) + 1;
							if(randInt <= density) {
								entityNum++;
								if(entityType.contains("Tree")) {
									entities.add(new IcyTree(handler, x, y, entityNum));
								}
							}
							if(featureType.contains("forest")) {
								tiles[x/64][y/64] = Tile.icyGrassTile.id;
							}
						}
					}else if(hasMultipleTiles) {
						if(handler.getWorld().getCurrentWorld() == 1) {
							int randInt = random.nextInt(100) + 1;
							if(randInt <= density) {
								entityNum++;
								if(entityType.contains("Stone")) {
									entities.add(new Stone(handler, x, y, entityNum));
								}else if(entityType.contains("Iron")) {
									entities.add(new Iron(handler, x, y, entityNum));
								}else if(entityType.contains("Flint")) {
									entities.add(new Flint(handler, x, y, entityNum));
								}
							}
							if(featureType.contains("rockySpot")) {
								int rand = random.nextInt(4);
								if(rand < 3) {
									tiles[x/64][y/64] = Tile.stoneTile.id;
								}else if(rand == 3) {
									tiles[x/64][y/64] = Tile.grassTile.id;
								}
							}else if(featureType.contains("ironSpot")) {
								int rand = random.nextInt(4);
								if(rand < 3) {
									tiles[x/64][y/64] = Tile.stoneTile.id;
								}else if(rand == 3) {
									tiles[x/64][y/64] = Tile.grassTile.id;
								}
							}else if(featureType.contains("flintSpot")) {
								int rand = random.nextInt(4);
								if(rand < 3) {
									tiles[x/64][y/64] = Tile.stoneTile.id;
								}else if(rand == 3) {
									tiles[x/64][y/64] = Tile.grassTile.id;
								}
							}
						}else if(handler.getWorld().getCurrentWorld() == 2) {
							int randInt = random.nextInt(100) + 1;
							if(randInt <= density) {
								entityNum++;
								if(entityType.contains("Stone")) {
									entities.add(new Stone(handler, x, y, entityNum));
								}else if(entityType.contains("Iron")) {
									entities.add(new Iron(handler, x, y, entityNum));
								}else if(entityType.contains("Flint")) {
									entities.add(new Flint(handler, x, y, entityNum));
								}else if(entityType.contains("Coal")) {
									entities.add(new Coal(handler, x, y, entityNum));
								}
							}
							if(featureType.contains("rockySpot") || featureType.contains("ironSpot") || featureType.contains("flintSpot") || featureType.contains("coalSpot")) {
								int rand = random.nextInt(4);
								if(rand < 3) {
									tiles[x/64][y/64] = Tile.hellStoneTile.id;
								}else if(rand == 3) {
									tiles[x/64][y/64] = Tile.hellGrassTile.id;
								}
							}
						}else if(handler.getWorld().getCurrentWorld() == 3) {
							int randInt = random.nextInt(100) + 1;
							if(randInt <= density) {
								entityNum++;
								if(entityType.contains("Stone")) {
									entities.add(new Stone(handler, x, y, entityNum));
								}else if(entityType.contains("Iron")) {
									entities.add(new Iron(handler, x, y, entityNum));
								}else if(entityType.contains("Flint")) {
									entities.add(new Flint(handler, x, y, entityNum));
								}
							}
							if(featureType.contains("rockySpot") || featureType.contains("ironSpot") || featureType.contains("flintSpot")) {
								int rand = random.nextInt(4);
								if(rand < 3) {
									tiles[x/64][y/64] = Tile.icyStoneTile.id;
								}else if(rand == 3) {
									tiles[x/64][y/64] = Tile.icyGrassTile.id;
								}
							}
						}
					}
				}else if(hasMultipleEntities) {
					if(!hasMultipleTiles) {
						if(handler.getWorld().getCurrentWorld() == 1) {
							int randInt = random.nextInt(100) + 1;
							if(randInt <= density) {
								entityNum++;
								//entity generation here
							}
							//tile gen here
						}
					}else if(hasMultipleTiles) {
						if(handler.getWorld().getCurrentWorld() == 1) {
							int randInt = random.nextInt(100) + 1;
							if(randInt <= density) {
								entityNum++;
								int rand = random.nextInt(4);
								if(featureType.contains("rockyForest")) {
									if(rand < 3) {
										if(entityType.contains("Tree")) {
											entities.add(new Tree(handler, x, y, entityNum));
										}
									}else if(rand == 3) {
										if(secondaryEntityType.contains("Stone")) {
											entities.add(new Stone(handler, x, y, entityNum));
										}
									}
								}else if(featureType.contains("graveyard")) {
									if(rand < 3) {
										if(entityType.contains("DeadTree")) {
											entities.add(new DeadTree(handler, x, y - 64, entityNum));
										}
									}else if(rand == 3) {
										if(secondaryEntityType.contains("Zombie")) {
											entities.add(new Zombie(handler, x, y, entityNum, handler.getWorld().getEntityManager().getPlayer()));
										}
									}
								}
							}
							if(featureType.contains("rockyForest")) {
								int rand = random.nextInt(4);
								if(rand < 3) {
									tiles[x/64][y/64] = Tile.grassTile.id;
								}else if(rand == 3) {
									tiles[x/64][y/64] = Tile.stoneTile.id;
								}
							}else if(featureType.contains("graveyard")) {
								int rand = random.nextInt(4);
								if(rand < 3) {
									tiles[x/64][y/64] = Tile.dirtTile.id;
								}else if(rand == 3) {
									tiles[x/64][y/64] = Tile.grassTile.id;
								}
							}
						}else if(handler.getWorld().getCurrentWorld() == 2) {
							int randInt = random.nextInt(100) + 1;
							if(randInt <= density) {
								entityNum++;
								int rand = random.nextInt(4);
								if(featureType.contains("rockyForest")) {
									if(rand < 3) {
										if(entityType.contains("Tree")) {
											entities.add(new HellTree(handler, x, y, entityNum));
										}
									}else if(rand == 3) {
										if(secondaryEntityType.contains("Stone")) {
											entities.add(new Stone(handler, x, y, entityNum));
										}
									}
								}else if(featureType.contains("graveyard")) {
									if(rand < 3) {
										if(entityType.contains("DeadTree")) {
											entities.add(new DeadTree(handler, x, y - 64, entityNum));
										}
									}else if(rand == 3) {
										if(secondaryEntityType.contains("Zombie")) {
											entities.add(new HellZombie(handler, x, y, entityNum, handler.getWorld().getEntityManager().getPlayer()));
										}
									}
								}
							}
							if(featureType.contains("rockyForest")) {
								int rand = random.nextInt(4);
								if(rand < 3) {
									tiles[x/64][y/64] = Tile.hellGrassTile.id;
								}else if(rand == 3) {
									tiles[x/64][y/64] = Tile.hellStoneTile.id;
								}
							}else if(featureType.contains("graveyard")) {
								int rand = random.nextInt(4);
								if(rand < 3) {
									tiles[x/64][y/64] = Tile.dirtTile.id;
								}else if(rand == 3) {
									tiles[x/64][y/64] = Tile.hellGrassTile.id;
								}
							}
						}else if(handler.getWorld().getCurrentWorld() == 3) {
							int randInt = random.nextInt(100) + 1;
							if(randInt <= density) {
								entityNum++;
								int rand = random.nextInt(4);
								if(featureType.contains("rockyForest")) {
									if(rand < 3) {
										if(entityType.contains("Tree")) {
											entities.add(new IcyTree(handler, x, y, entityNum));
										}
									}else if(rand == 3) {
										if(secondaryEntityType.contains("Stone")) {
											entities.add(new Stone(handler, x, y, entityNum));
										}
									}
								}else if(featureType.contains("graveyard")) {
									if(rand < 3) {
										if(entityType.contains("DeadTree")) {
											entities.add(new DeadTree(handler, x, y - 64, entityNum));
										}
									}else if(rand == 3) {
										if(secondaryEntityType.contains("Zombie")) {
											entities.add(new IcyZombie(handler, x, y, entityNum, handler.getWorld().getEntityManager().getPlayer()));
										}
									}
								}
							}
							if(featureType.contains("rockyForest")) {
								int rand = random.nextInt(4);
								if(rand < 3) {
									tiles[x/64][y/64] = Tile.icyGrassTile.id;
								}else if(rand == 3) {
									tiles[x/64][y/64] = Tile.icyStoneTile.id;
								}
							}else if(featureType.contains("graveyard")) {
								int rand = random.nextInt(4);
								if(rand < 3) {
									tiles[x/64][y/64] = Tile.dirtTile.id;
								}else if(rand == 3) {
									tiles[x/64][y/64] = Tile.icyGrassTile.id;
								}
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
		}else if(featureType.contains("rockySpot")) {
			entityType = "Stone";
			secondaryEntityType = "";
			tileType = Tile.stoneTile;
			secondaryTileType = Tile.grassTile;
			hasMultipleEntities = false;
			hasMultipleTiles = true;
		}else if(featureType.contains("graveyard")) {
			entityType = "DeadTree";
			secondaryEntityType = "Zombie";
			tileType = Tile.dirtTile;
			secondaryTileType = Tile.grassTile;
			hasMultipleEntities = true;
			hasMultipleTiles = true;
		}else if(featureType.contains("ironSpot")) {
			entityType = "Iron";
			secondaryEntityType = "";
			tileType = Tile.stoneTile;
			secondaryTileType = Tile.grassTile;
			hasMultipleEntities = false;
			hasMultipleTiles = true;
		}else if(featureType.contains("flintSpot")) {
			entityType = "Flint";
			secondaryEntityType = "";
			tileType = Tile.stoneTile;
			secondaryTileType = Tile.grassTile;
			hasMultipleEntities = false;
			hasMultipleTiles = true;
		}else if(featureType.contains("coalSpot")) {
			entityType = "Coal";
			secondaryEntityType = "";
			tileType = Tile.stoneTile;
			secondaryTileType = Tile.grassTile;
			hasMultipleEntities = false;
			hasMultipleTiles = true;
		}
	}
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
	
	public int[][] getTiles(){
		return tiles;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}
	
}
