package tiles;

import gfx.Assets;

public class SandyDoorTile2 extends Tile
{

    public SandyDoorTile2(int id)
    {
        super(Assets.closedSandyDoor2, id);
    }

    public boolean isSolid()
    {
        return solid;
    }

    public static void open()
    {
        solid = false;
        for(Tile t : Tile.getTiles()) {
        	if(t.getId() == Tile.sandyDoorTile2.id) {
        		t.setTexture(Assets.openSandyDoor2);
        	}
        }
    }
    
    public static void close()
    {
        solid = true;
        for(Tile t : Tile.getTiles()) {
        	if(t.getId() == Tile.sandyDoorTile2.id) {
        		t.setTexture(Assets.closedSandyDoor2);
        	}
        }
    }

    static boolean solid = true;

}
