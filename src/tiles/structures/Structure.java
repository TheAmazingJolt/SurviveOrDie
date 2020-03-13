package tiles.structures;

import java.awt.image.BufferedImage;

import items.Item;
import main.Handler;
import tiles.Tile;

public class Structure extends Tile{

	public int locationX;
	public int locationY;
	public int health = 50;
	private boolean locationSet = false;
	
	private Item structureItem;
	
	private Handler handler;
	
	public Structure(BufferedImage texture, int id, int locationX, int locationY, Item structureItem) {
		super(texture, id);
		this.structureItem = structureItem;
	}
	
	public void hurt(int dmg) {
		health -= dmg;
		if(health <= 0) {
			die();
		}
	}
	
	public void die() {
		structures.remove(this);
        handler.getWorld().getItemManager().addItem(structureItem.createNew((int)locationX * 64 + 32, (int)locationY * 64 + 32, false, false, false));
	}
	
	public void setLocation(int x, int y) {
		this.locationX = x;
		this.locationY = y;
		locationSet = true;
	}

	public void setHandler(Handler handle) {
		handler = handle;
	}
	
	public int getLocationX() {
		return locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public boolean isLocationSet() {
		return locationSet;
	}

}
