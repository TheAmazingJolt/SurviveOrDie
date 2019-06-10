package tiles;

import gfx.Assets;

public class SandyDoorTile extends Tile
{

    public SandyDoorTile(int id)
    {
        super(Assets.closedSandyDoor, id);
    }

    public boolean isSolid()
    {
        return solid;
    }

    public static void open()
    {
        solid = false;
        for(Tile t : Tile.getTiles()) {
        	if(t.getId() == Tile.sandyDoorTile.id) {
        		t.setTexture(Assets.openSandyDoor);
        	}
        }
    }
    
    public static void close()
    {
        solid = true;
        for(Tile t : Tile.getTiles()) {
        	if(t.getId() == Tile.sandyDoorTile.id) {
        		t.setTexture(Assets.closedSandyDoor);
        	}
        }
    }

    static boolean solid = true;

}
