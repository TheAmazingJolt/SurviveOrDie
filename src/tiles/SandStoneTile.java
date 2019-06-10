package tiles;

import gfx.Assets;

// Referenced classes of package tiles:
//            Tile

public class SandStoneTile extends Tile
{

    public SandStoneTile(int id)
    {
        super(Assets.sandStone, id);
    }

    public boolean isSolid()
    {
        return false;
    }
    
    public boolean isDeadly() {
		return false;
	}
}
