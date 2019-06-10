package tiles;

import gfx.Assets;

public class SandStoneWallTile extends Tile{

	public SandStoneWallTile( int id) {
		super(Assets.sandStoneWall, id);
	}
	public boolean isSolid() {
		return true;
	}

}
