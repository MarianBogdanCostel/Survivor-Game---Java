package PaooGame.Items.Statics.Trees;

import PaooGame.Graphics.Assets;


import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class CutTree extends Item {
    public CutTree(RefLinks refLinks, float x, float y)
    {
        super(refLinks,x,y, Tile.TILE_WIDTH,Tile.TILE_HEIGHT);

        //Coliziuni
        bounds.x = 5;
        bounds.y = 5;
        bounds.width = width-9;
        bounds.height = (int) (height - 12);
    }

    @Override
    public void Update(){
        active = true;
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.cuttree, (int)x, (int)y, width, height,null);
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }

    @Override
    public void die() {

    }


    public boolean IsSolid()
    {
        return true;
    }
}
